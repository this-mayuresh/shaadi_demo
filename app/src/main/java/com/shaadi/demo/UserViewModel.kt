package com.shaadi.demo

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.shaadi.demo.data.cache.model.UserCache
import com.shaadi.demo.data.cache.repository.UserRepo
import com.shaadi.demo.data.model.User
import com.shaadi.demo.utility.DataState
import com.shaadi.demo.utility.GENERIC_NETWORK_ERROR
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject
constructor(
    @SuppressLint("StaticFieldLeak")
    @ApplicationContext
    private val applicationContext: Context,
    private val userRepo: UserRepo,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(UserListState())

    val state: State<UserListState> = _state

    val users: LiveData<List<UserCache>> = userRepo.getUsers()


    fun getUserData() {

        viewModelScope.launch {
            userRepo.saveUsers(isNetworkAvailable()).onEach { dataState ->
                when (dataState) {
                    is DataState.Success -> {
                        _state.value = UserListState(isLoading = false)

                    }
                    is DataState.Error -> {
                        _state.value = UserListState(
                            error = dataState.exception.message ?: GENERIC_NETWORK_ERROR
                        )
                    }
                    is DataState.Loading -> {
                        println("loading...")
                        _state.value = UserListState(isLoading = true)
                    }
                }

            }.launchIn(viewModelScope)
        }


    }

    fun updateUserStatus(user: User) {
        viewModelScope.launch {
            userRepo.updateUser(user)
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false

            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
                else -> false
            }
        }else {
            connectivityManager.activeNetworkInfo?.run{
                return when (type){
                    TYPE_WIFI -> true
                    TYPE_MOBILE -> true
                    TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }
}


/**Creating data class in same file because it wont be used anywhere else*/

data class UserListState(
    val isLoading: Boolean = false,
    val error: String = ""
)
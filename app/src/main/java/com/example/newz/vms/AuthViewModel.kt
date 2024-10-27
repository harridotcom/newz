package com.example.newz.vms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel: ViewModel() {
    private var auth = FirebaseAuth.getInstance()
    private var _authState = MutableLiveData<AuthState>()
    var authState : LiveData<AuthState> = _authState

    init {
        CheckAuthState()
    }

    fun CheckAuthState(){
        if (auth.currentUser == null){
            _authState.value = AuthState.UnAuthenticated
        }else{
            _authState.value = AuthState.Authenticated
        }
    }

    fun Login(email: String, password: String){
        if (email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or Password Field is Empty")
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                task ->
                if (task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.UnAuthenticated
                }
            }
    }

    fun Register(email: String, password: String){
        if (email.isEmpty() || password.isEmpty()){
            _authState.value = AuthState.Error("Email or Password Field is Empty")
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                    task ->
                if (task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }else{
                    _authState.value = AuthState.UnAuthenticated
                }
            }
    }

    fun SignOut(){
        auth.signOut()
        _authState.value = AuthState.UnAuthenticated
    }
}

sealed class AuthState{
    object UnAuthenticated: AuthState()
    object Authenticated: AuthState()
    data class Error(val message: String): AuthState()
}
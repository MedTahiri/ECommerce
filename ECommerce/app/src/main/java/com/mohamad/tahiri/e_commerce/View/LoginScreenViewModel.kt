package com.mohamad.tahiri.e_commerce.View

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mohamad.tahiri.e_commerce.User

class LoginScreenViewModel:ViewModel() {

    fun addUser(user: User) {
        Firebase.firestore.collection(Constants.User).document(user.gmail).set(user)
    }

}
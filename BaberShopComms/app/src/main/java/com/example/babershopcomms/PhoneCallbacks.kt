package com.example.babershopcomms

import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class PhoneCallbacks(private val listener : PhoneCallbacksListener) : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

    interface PhoneCallbacksListener {
        fun onVerificationCompleted(credential: PhoneAuthCredential?)
        fun onVerificationFailed(exception: FirebaseException?)
        fun onCodeSent(
            verificationId: String?,
            token: PhoneAuthProvider.ForceResendingToken?
        )
    }

    override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
        listener.onCodeSent(p0,p1)
    }

    override fun onVerificationCompleted(p0: PhoneAuthCredential) {
        listener.onVerificationCompleted(p0)
    }

    override fun onVerificationFailed(p0: FirebaseException) {
        listener.onVerificationFailed(p0)
    }
}
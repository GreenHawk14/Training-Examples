@file:Suppress("DEPRECATION")

package com.example.babershopcomms
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.PhoneAuthProvider.*
import java.util.*
import java.util.concurrent.TimeUnit


private const val TAG = "CustomerVerify"
@Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
class CustomerVerify : AppCompatActivity() {

    //These are the objects needed
    //It is the verification id that will be sent to the user
    private var mVerificationId: String? = null

    //The EditText to input the code
    private lateinit var editTextCode: EditText

    //firebase auth object
    private var mAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_verify)

        //initializing objects
        mAuth = FirebaseAuth.getInstance()
        mAuth!!.setLanguageCode(Locale.getDefault().language)

        val numConfirm = findViewById<EditText>(R.id.txtConfirmPhone)


        //getting mobile number from the previous activity
        //and sending the verification code to the number
        //val phoneCallbacks = PhoneCallbacks(CustomerVerify::class.java)
        val intent = intent
        val mobile = intent.getStringExtra("mobile")
        if (mobile != null) {
            sendVerificationCode("+1$mobile")
        }


        //if the automatic sms detection did not work, user can also enter the code manually
        //so adding a click listener to the button
        findViewById<Button>(R.id.btn_CustVerify).setOnClickListener(View.OnClickListener {
            val code = numConfirm.text.toString().trim { it <= ' ' }
            if (code.isEmpty() || code.length < 6) {
                numConfirm.error = "Enter valid code"
                numConfirm.requestFocus()
                return@OnClickListener
            }

            //verifying the code entered manually
            verifyVerificationCode(code)
        })
    }

    //the method is sending verification code
    //the country id is concatenated
    //you can take the country id as user input as well
    private fun sendVerificationCode(no: String) {
        /*
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            no,     //phone number to verify
            60,     //Timeout duration
            TimeUnit.SECONDS,   //Unit of Timeout
            this,               //Activity for callback binding
            mCallbacks )       //OnVerificationChange Callbacks
*/
        val options = PhoneAuthOptions.newBuilder(mAuth!!)
            .setPhoneNumber(no)       // Phone number to verify
            .setTimeout(120L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this@CustomerVerify)                 // Activity (for callback binding)
            .setCallbacks(object : OnVerificationStateChangedCallbacks() {

                override fun onCodeSent(
                    s: String,
                    forceResendingToken: ForceResendingToken)
                {
                    super.onCodeSent(s, forceResendingToken)

                //storing the verification id that is sent to the user
               mVerificationId = s
                Log.d("Code Sent Cred: ", "$s")
                //Log.d("onCodeSent", "ForceToken: \n $forceResendingToken")
                    this@CustomerVerify.enableUserManuallyInputCode()
                }

                override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                    // Sign in with the credential
                    // ...
                    Log.d(TAG, "onVerificationCompleted: ")

                //Getting the code sent by SMS
                val code = phoneAuthCredential.smsCode

                //sometime the code is not detected automatically
                //in this case the code will be null
                //so user has to manually enter the code
                if (code != null) {
                    editTextCode.setText(code)
                    //verifying the code
                    verifyVerificationCode(code)
                    }
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    // ...
                }
            })       // OnVerificationStateChangedCallbacks
            .build()
        verifyPhoneNumber(options)
    }

    private fun enableUserManuallyInputCode() {
        //No-op
    }


//    //the callback to detect the verification status
//    private var mCallbacks: OnVerificationStateChangedCallbacks =
//        object : OnVerificationStateChangedCallbacks() {
//            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
//                Log.d(TAG, "onVerificationCompleted: ")
//
//                //Getting the code sent by SMS
//                val code = phoneAuthCredential.smsCode
//
//                //sometime the code is not detected automatically
//                //in this case the code will be null
//                //so user has to manually enter the code
//                if (code != null) {
//                    editTextCode.setText(code)
//                    //verifying the code
//                    verifyVerificationCode(code)
//                }
//            }
//
//            override fun onVerificationFailed(e: FirebaseException) {
//                Log.d(TAG, "onVerificationFailed: ")
//                Toast.makeText(this@CustomerVerify, e.message, Toast.LENGTH_LONG).show()
//            }
//
//            override fun onCodeSent(s: String, forceResendingToken: ForceResendingToken) {
//                super.onCodeSent(s, forceResendingToken)
//
//                //storing the verification id that is sent to the user
//                mVerificationId = s
//                Log.d("Code Sent Cred: ", "$s")
//                //Log.d("onCodeSent", "ForceToken: \n $forceResendingToken")
//            }
//        }

    private fun verifyVerificationCode(code: String) {
        //creating the credential
        val credential = mVerificationId?.let { getCredential(it, code) }

        //signing the user
        if (credential != null) {
            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        mAuth?.signInWithCredential(credential)
            ?.addOnCompleteListener(this@CustomerVerify,
                OnCompleteListener<AuthResult?> { task ->
                    if (task.isSuccessful) {
                        //verification successful we will start the Appointment activity
                        val intent = Intent(this@CustomerVerify, StylesHaircutscreen::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    } else {

                        //verification unsuccessful.. display an error message
                        var message = "Somthing is wrong, we will fix it soon..."
                        if (task.exception is FirebaseAuthInvalidCredentialsException) {
                            "Invalid code entered...".also { message = it }
                        }
                    }
                })
    }

}
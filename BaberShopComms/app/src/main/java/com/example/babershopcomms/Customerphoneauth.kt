package com.example.babershopcomms

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class Customerphoneauth : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customerphoneauth)

        val mobileNum: EditText = findViewById(R.id.txtphoneRef)
        findViewById<View>(R.id.btn_Custphone).setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val mobile = mobileNum.text.toString().trim()
                if (mobile.isEmpty() || mobile.length < 10) {
                    mobileNum.error = "Enter a valid mobile"
                    mobileNum.requestFocus()
                    return
                }
                val intent = Intent(this@Customerphoneauth, CustomerVerify::class.java)
                intent.putExtra("mobile", mobile)
                startActivity(intent)
            }
        })
    }

}
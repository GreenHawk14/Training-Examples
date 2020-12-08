package com.example.minimalistcp

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.minimalistcp.databinding.ActivityMainBinding
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSelectAll.setOnClickListener {
            //val contentResolver = contentResolver
            val uri = CONTENT_URI
            val projection = arrayOf(CONTENT_PATH)

            val cursor =
            contentResolver.query(uri,
            projection,
            null,
            null,
            null)
            displayFromCursor(cursor)
        }

        binding.btnSelectFirst.setOnClickListener {
            val uri = CONTENT_URI
            val projection = arrayOf(CONTENT_PATH)
            val selection = "$WORD_ID = ?"
            val selArgs = arrayOf("0")

            val cursor =
            contentResolver.query(uri,
            projection,
            selection,
            selArgs,
            null)
            displayFromCursor(cursor)

        }
    }

    private fun displayFromCursor(cursor: Cursor?) {
        val stringBuilder = StringBuilder()
        cursor?.let {
            while (it.moveToNext()){
                stringBuilder.append(
                    it.getString(it.getColumnIndexOrThrow(CONTENT_PATH))
                )
                stringBuilder.append("\n")
            }
        }
        if(stringBuilder.isNotEmpty())
            binding.tvDisplay.text = stringBuilder.toString()
        else
            binding.tvDisplay.text = "Error!!"
        cursor?.close()
    }
}
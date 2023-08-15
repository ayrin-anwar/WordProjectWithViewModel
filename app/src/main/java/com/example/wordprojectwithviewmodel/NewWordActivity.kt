package com.example.wordprojectwithviewmodel

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.wordprojectwithviewmodel.databinding.ActivityMainBinding
import com.example.wordprojectwithviewmodel.databinding.ActivityNewWordBinding

class NewWordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewWordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewWordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener {
            val msg = binding.editTextTextPersonName.text.toString()
            val intent = Intent()
            if(msg == null) {
                setResult(RESULT_CANCELED,intent)
            } else {
                intent.putExtra("Word",msg)
                setResult(RESULT_OK,intent)

            }
            finish()
        }
    }

}
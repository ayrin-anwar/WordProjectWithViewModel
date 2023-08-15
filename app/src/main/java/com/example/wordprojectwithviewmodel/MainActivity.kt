package com.example.wordprojectwithviewmodel

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordprojectwithviewmodel.data.dao.Word
import com.example.wordprojectwithviewmodel.databinding.ActivityMainBinding
import com.example.wordprojectwithviewmodel.ui.WordAdapter
import com.example.wordprojectwithviewmodel.viewmodel.WordViewModel
import com.example.wordprojectwithviewmodel.viewmodel.WordViewModelFactory

class MainActivity : AppCompatActivity() {
    private val viewModel: WordViewModel by viewModels {
       WordViewModelFactory((application as WordApplication).repository)
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = WordAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity,NewWordActivity::class.java)
            resultLauncher.launch(intent)

        }
        viewModel.allWords.observe(this, Observer { words ->
            words.let { adapter.submitList(it) }
        })


    }
    val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        run {
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val word = data?.getStringExtra("Word")
                val word1 = Word(word!!)
                viewModel.insert(word1)

            }
        }
    }
}
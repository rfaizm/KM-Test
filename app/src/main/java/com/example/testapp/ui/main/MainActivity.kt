package com.example.testapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testapp.R
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.ui.secondscreen.SecondPageActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        getSupportActionBar()!!.hide();
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupAction()
    }

    private fun setupAction() {
        val buttonCheck = binding.buttonCheck
        val titleTextView = binding.titleTextView

        titleTextView.visibility = View.GONE

        buttonCheck.setOnClickListener {
            val inputPalindrome = binding.polindromeEditText.text.toString().trim()



            if (inputPalindrome.isEmpty()) {
                titleTextView.text = "not palindrome"
            } else {
                // Check if the sentence is a palindrome
                val isPalindromeInput = isPalindrome(inputPalindrome)
                val message = if (isPalindromeInput) {
                    "isPalindrome"
                } else {
                    "not palindrome"
                }

                titleTextView.text = message
            }

            titleTextView.visibility = View.VISIBLE
        }

        binding.buttonNext.setOnClickListener {
            // Get the latest text from nameEditText
            val nameInput = binding.nameEditText.text.toString().trim()
            if (nameInput.isEmpty()) {
                showToast("Name field is empty")
            } else {
                val intent = Intent(this, SecondPageActivity::class.java)
                intent.putExtra(SecondPageActivity.EXTRA_NAME, nameInput)
                startActivity(intent)
            }
        }
    }

    private fun isPalindrome(input: String): Boolean {
        val sanitizedInput = input.replace(" ", "").lowercase()
        return sanitizedInput == sanitizedInput.reversed()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
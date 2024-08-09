package com.example.testapp.ui.secondscreen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testapp.R
import com.example.testapp.databinding.ActivitySecondPageBinding
import com.example.testapp.ui.thirdscreen.ThirdPageActivity

class SecondPageActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySecondPageBinding

    private val resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == ThirdPageActivity.RESULT_CODE && result.data != null) {
            val selectedValue = result.data?.getStringExtra(ThirdPageActivity.EXTRA_SELECTED_VALUE) ?: ""
            binding.layoutUsernameTextView.text = selectedValue
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = intent.getStringExtra(EXTRA_NAME) ?: ""

        binding.nameTextView.text = name
        binding.buttonNext.setOnClickListener {
            val intent = Intent(this, ThirdPageActivity::class.java)
            resultLauncher.launch(intent)
        }
    }


    companion object {
        var EXTRA_NAME = "extra_name"
        const val REQUEST_CODE = 1
    }
}
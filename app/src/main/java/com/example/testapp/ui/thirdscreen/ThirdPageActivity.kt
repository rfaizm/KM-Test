package com.example.testapp.ui.thirdscreen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.R
import com.example.testapp.data.adapter.LoadingStateAdapter
import com.example.testapp.data.adapter.UsernameAdapter
import com.example.testapp.databinding.ActivityThirdPageBinding
import com.example.testapp.ui.ViewModelFactory

class ThirdPageActivity : AppCompatActivity() {

    private lateinit var binding : ActivityThirdPageBinding

    private val viewModel by viewModels<ThirdPageViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.rvQuote.layoutManager = LinearLayoutManager(this)

        getData()
    }

    private fun getData() {
        showLoading(true) // Menampilkan indikator loading

        val adapter = UsernameAdapter { selectedValue ->
            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_SELECTED_VALUE, selectedValue)
            setResult(RESULT_CODE, resultIntent)
            finish() // Mengakhiri aktivitas dan kembali ke SecondPageActivity
        }

        binding.rvQuote.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )

        adapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                showLoading(true) // Masih loading
            } else {
                showLoading(false) // Selesai loading
            }
        }

        viewModel.quote.observe(this, {
            adapter.submitData(lifecycle, it)
        })
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressIndicator.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val RESULT_CODE = 1
        const val EXTRA_SELECTED_VALUE = "extra_selected_value"
    }
}
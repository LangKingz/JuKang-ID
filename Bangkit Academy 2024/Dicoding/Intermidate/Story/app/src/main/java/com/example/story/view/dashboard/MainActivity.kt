package com.example.story.view.dashboard

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.story.data.RetrofitClient
import com.example.story.databinding.ActivityMainBinding
import com.example.story.helper.AdapterList
import com.example.story.view.auth.WelcomeActivity
import com.example.story.view.auth.login.LoginActivity
import com.example.story.view.tambah.TambahActivity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        checkStatus()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val sharedPref = getSharedPreferences("AUTH", MODE_PRIVATE)
        val name = sharedPref.getString("NAME", "no user detected")
        val token = sharedPref.getString("TOKEN", null)

        binding.username.text = "Hello , $name"
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.btnlogOut.setOnClickListener {
            logOut()
        }

        binding.btnTambah.setOnClickListener{
            val intent = Intent(this@MainActivity,TambahActivity::class.java)
            startActivity(intent)
        }

        Log.d(TAG, "token: $token")

        token?.let {
            viewModel.fetchStory(it)
        }

        viewModel.data.observe(this){story->
            binding.progressBar2.visibility = View.GONE
            if (story != null){
                binding.recyclerView.adapter = AdapterList(story)
            }else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
            }

        }
    }


    fun logOut() {
        val sharedPref = getSharedPreferences("AUTH", MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()

        // Navigasi kembali ke LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Menghapus stack activity
        startActivity(intent)
    }

    fun checkStatus() {
        val sharedPref = getSharedPreferences("AUTH", MODE_PRIVATE)
        val token = sharedPref.getString("TOKEN", null)
        if (token == null) {
            val intent = Intent(this, WelcomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            Toast.makeText(this, "Berhasil Logout", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
package com.project.suitmediaaryabagusf.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.suitmediaaryabagusf.R
import com.project.suitmediaaryabagusf.databinding.ActivityMainBinding
import com.project.suitmediaaryabagusf.utils.Utils

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
    }

    private fun initViews() {
        binding.apply {

            btnCheck.setOnClickListener {
                val toastText = if (isPalindrome(etPalindrome.text.toString())) {
                    "isPalindrome"
                } else {
                    "not palindrome"
                }
                Utils.shortToast(this@MainActivity, toastText)
            }

            btnNext.setOnClickListener {
                val name = etName.text.toString()
                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                intent.putExtra(MAIN_NAME, name)
                startActivity(intent)
            }

        }
    }

    private fun isPalindrome(text: String): Boolean {
        val spaceRemoved = text.replace("\\s+".toRegex(), "").lowercase()
        return spaceRemoved == spaceRemoved.reversed()
    }

    companion object {
        const val MAIN_NAME = "main_name"
    }

}
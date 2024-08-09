package com.project.suitmediaaryabagusf.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.suitmediaaryabagusf.R
import com.project.suitmediaaryabagusf.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private var nameIntentValue: String? = null
    private val requestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        claimIntentValue()
        initViews()

    }

    private fun claimIntentValue() {
        if (intent != null) {
            nameIntentValue = intent.getStringExtra(MainActivity.MAIN_NAME)
            binding.tvName.text = nameIntentValue
        }
    }

    private fun initViews() {
        binding.apply {
            btnBack.setOnClickListener { finish() }
            btnChoose.setOnClickListener {
                startActivityForResult(
                    Intent(
                        this@HomeActivity,
                        ListActivity::class.java
                    ),
                    requestCode
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == this.requestCode && resultCode == Activity.RESULT_OK) {
            val selectedName = data?.getStringExtra("selectedUser")
            binding.tvSelected.text = selectedName
        }
    }

}
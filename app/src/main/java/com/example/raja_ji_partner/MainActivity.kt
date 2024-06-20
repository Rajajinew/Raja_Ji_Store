package com.example.raja_ji_partner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.raja_ji_partner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.AddMenu.setOnClickListener{
            val intent=Intent(this,additemactivity::class.java)
            startActivity(intent)
        }
        binding.Seeall.setOnClickListener{
            val intent=Intent(this,SeeDetailActivity::class.java)
            startActivity(intent)
        }
        binding.pendingorder.setOnClickListener {
            val intent=Intent(this ,pendingActivity::class.java)
            startActivity(intent)
        }
    }
}
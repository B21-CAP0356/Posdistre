package com.projek.posditre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.projek.posditre.databinding.ActivityDetailLaporanBinding
import com.projek.posditre.databinding.ActivityLoginPuprBinding

class LoginPUPR : AppCompatActivity() {

    private lateinit var loginPuprBinding: ActivityLoginPuprBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val loginPuprBinding = ActivityLoginPuprBinding.inflate(layoutInflater)
        setContentView(loginPuprBinding.root)

        setSupportActionBar(loginPuprBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
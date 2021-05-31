package com.projek.posditre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.projek.posditre.databinding.ActivityDetailLaporanBinding

class DetailLaporan : AppCompatActivity() {

    private lateinit var detailActivityBinding: ActivityDetailLaporanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val detailActivityBinding = ActivityDetailLaporanBinding.inflate(layoutInflater)
        setContentView(detailActivityBinding.root)

        setSupportActionBar(detailActivityBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
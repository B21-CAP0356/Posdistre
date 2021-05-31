package com.projek.posditre

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_dashboard_pengguna.*

class DashboardPengguna : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_pengguna)

        val dashboardPenggunaFragment = DashboardPenggunaFragment()
        val laporPenggunaFragment = LaporPenggunaFragment()

        makeCurrentFragment(dashboardPenggunaFragment)
        bottom_navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_dashboard -> makeCurrentFragment(dashboardPenggunaFragment)
                R.id.nav_lapor -> makeCurrentFragment(laporPenggunaFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment){
        if (fragment != null){
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_login) {
            startActivity(Intent(applicationContext, LoginPUPR::class.java))
        }

        return super.onOptionsItemSelected(item)
    }
}
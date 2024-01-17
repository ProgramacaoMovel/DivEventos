package com.example.eventos

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import android.widget.Button

class DashboardActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        // Configuração do botão para criar notícias
        val btnCriarnoti: Button = this.findViewById(R.id.btnCriarnoti)
        btnCriarnoti.setOnClickListener {
            val intent = Intent(this, CriarNoticiaActivity::class.java)
            startActivity(intent)
        }

        // Configuração da Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Configuração do DrawerLayout e ActionBarDrawerToggle
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Carregar o fragmento inicial (HomeFragment)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
            navView.setCheckedItem(R.id.dashboard)
        }

        // Configuração do listener para itens do NavigationView
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.dashboard -> {
                    toolbar.title = "Home"
                    val fragment = HomeFragment()
                    supportFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                        .replace(R.id.fragment_container, fragment)
                        .commit()
                }
                R.id.highlights-> {
                    toolbar.title = "Home"
                    val fragment = HomeFragment()
                    supportFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                        .replace(R.id.fragment_container, fragment)
                        .commit()
                }
                R.id.seguir -> {
                    toolbar.title = "Home"
                    val fragment = HomeFragment()
                    supportFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                        .replace(R.id.fragment_container, fragment)
                        .commit()
                }
                R.id.feedback -> {
                    toolbar.title = "Home"
                    val fragment = HomeFragment()
                    supportFragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left)
                        .replace(R.id.fragment_container, fragment)
                        .commit()
                }
                // Adicione mais casos aqui para outros itens do menu
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
}
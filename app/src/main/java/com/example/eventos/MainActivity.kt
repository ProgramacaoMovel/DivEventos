package com.example.eventos
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNavegarParaSegunda: Button = findViewById(R.id.btnNavegarParaSegunda)
        val btnNavReg: Button = findViewById(R.id.btnreg)

        btnNavegarParaSegunda.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnNavReg.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

       // Inicializando DrawerLayout e NavigationView
       // drawerLayout = findViewById(R.id.drawer_layout)
        //navView = findViewById(R.id.nav_view)

        // Configurando o listener para os itens da NavigationView
        //navView.setNavigationItemSelectedListener { menuItem ->
          //  menuItem.isChecked = true
            //drawerLayout.closeDrawers()
            // Lidar com os cliques dos itens aqui
            //true
        //}

        // Aqui você pode adicionar o código para o botão hamburger, se necessário
    }

    // Outros métodos da Activity, se houver
}

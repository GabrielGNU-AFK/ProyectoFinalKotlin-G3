package pe.edu.idat.tiendamonturas_v01.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import pe.edu.idat.tiendamonturas_v01.R
import pe.edu.idat.tiendamonturas_v01.databinding.ActivityHomeBinding
import pe.edu.idat.tiendamonturas_v01.view.fragment.CarritoFragmentDirections
import pe.edu.idat.tiendamonturas_v01.viewmodel.CarritoViewModel
import pe.edu.idat.tiendamonturas_v01.viewmodel.ClienteViewModel
import pe.edu.idat.tiendamonturas_v01.viewmodel.ItemViewModel


class HomeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private lateinit var clienteViewModel: ClienteViewModel
    private lateinit var itemViewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clienteViewModel=ViewModelProvider(this)[ClienteViewModel::class.java]
        itemViewModel=ViewModelProvider(this)[ItemViewModel::class.java]

        setSupportActionBar(binding.appBarHome.toolbar)


        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_monturas, R.id.nav_carrito, R.id.nav_orden
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        mostrarInfoAuth()
        limpiarCarrito()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    fun mostrarInfoAuth(){
        val tvNomUser:TextView=binding.navView.getHeaderView(0).findViewById(R.id.tvnavnomapll)
        val tvEmailUser:TextView=binding.navView.getHeaderView(0).findViewById(R.id.tvnavemail)

        clienteViewModel.obtener().observe(this,
        Observer{
            cliente->cliente?.let {
                tvNomUser.text=cliente.nombres+" "+cliente.apellidos
                tvEmailUser.text=cliente.correo
        }
        })
    }

    private fun limpiarCarrito() {
        itemViewModel.eliminarTodo()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val idItem=item.itemId
        if (idItem==R.id.action_settings){
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
package pe.edu.idat.tiendamonturas_v01.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import pe.edu.idat.apppatitasidat2022.utilitarios.AppMensaje
import pe.edu.idat.apppatitasidat2022.utilitarios.TipoMensaje
import pe.edu.idat.tiendamonturas_v01.R
import pe.edu.idat.tiendamonturas_v01.databinding.ActivityLoginBinding
import pe.edu.idat.tiendamonturas_v01.db.entity.ClienteEntity
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseLogin

import pe.edu.idat.tiendamonturas_v01.viewmodel.AuthViewModel
import pe.edu.idat.tiendamonturas_v01.viewmodel.ClienteViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var clienteViewModel: ClienteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        clienteViewModel=ViewModelProvider(this).get(ClienteViewModel::class.java)

        limpiarDatos()

        binding.btnlogin.setOnClickListener(this)
        binding.btnregistrar.setOnClickListener(this)
        binding.chkrecordar.setOnClickListener(this)
        authViewModel.responseLogin.observe(this, Observer{
                response -> obtenerDatosLogin(response)
        })
    }

    private fun obtenerDatosLogin(response: ResponseLogin) {
        if(response.rpta){

            val clienteEntity=ClienteEntity(response.cliente.idCliente,response.cliente.nombres,
            response.cliente.apellidos,response.cliente.edad,response.cliente.nroCelular,
            response.cliente.direccion,response.cliente.dni,response.cliente.correo)

            clienteViewModel.insertar(clienteEntity)
            startActivity(Intent(applicationContext,HomeActivity::class.java))
            finish()

        }else{
            AppMensaje.enviarMensaje(binding.root,
                "USUARIO INCORRECTO",
                TipoMensaje.ERROR)
        }
        binding.btnregistrar.isEnabled = true
        binding.btnlogin.isEnabled = true
    }

    fun validarUsuarioPassword() : Boolean{
        var okValidacion = true
        if(binding.etusuario.text.toString().trim().isEmpty()){
            binding.etusuario.isFocusableInTouchMode = true
            binding.etusuario.requestFocus()
            okValidacion = false
        } else if(binding.etpassword.text.toString().trim().isEmpty()){
            binding.etpassword.isFocusableInTouchMode = true
            binding.etpassword.requestFocus()
            okValidacion = false
        }
        return okValidacion
    }

    override fun onClick(vista: View) {
        when(vista.id){
            R.id.btnlogin -> autenticarUsuario()
            R.id.btnregistrar->startActivity(Intent(applicationContext,RegistroActivity::class.java))

        }
    }

    private fun limpiarDatos() {
        clienteViewModel.eliminarTodo()

    }

    private fun autenticarUsuario() {
        binding.btnregistrar.isEnabled = false
        binding.btnlogin.isEnabled = false
        if(validarUsuarioPassword()){
            authViewModel.autenticarUsuario(binding.etusuario.text.toString(),
                binding.etpassword.text.toString())
        }else{
            AppMensaje.enviarMensaje(binding.root,
                getString(R.string.msloginincompleto),
                TipoMensaje.ERROR)
            binding.btnregistrar.isEnabled = true
            binding.btnlogin.isEnabled = true
        }
    }
}
package pe.edu.idat.tiendamonturas_v01.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pe.edu.idat.tiendamonturas_v01.repositorio.UsuarioRepository
import pe.edu.idat.tiendamonturas_v01.retrofit.request.RequestLogin
import pe.edu.idat.tiendamonturas_v01.retrofit.request.RequestRegistro
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseLogin
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseRegistro


class AuthViewModel: ViewModel() {

    var responseLogin: LiveData<ResponseLogin>
    var responseRegistro: LiveData<ResponseRegistro>
    private var repository= UsuarioRepository()
    init {
        responseLogin=repository.loginResponse
        responseRegistro=repository.registroResponse
    }

    fun registrarUsuario(nombres:String,apellidos:String,edad:Int,nroCelular:String,
                         direcion:String,dni:String,correo:String,usuario:String,password:String){
        responseRegistro=repository.registrarUsuario(RequestRegistro(nombres,apellidos,edad,nroCelular
            ,direcion,dni,correo,usuario,password))
    }

    fun autenticarUsuario(usuario: String, password: String){
        responseLogin = repository.autenticarUsuario(
            RequestLogin(usuario, password)
        )
    }

}

/*
* {
    "nombres": "juanito alcachofa",
    "apellidos": "de la Vega",
    "edad":"54",
	"nroCelular":"9514789",
	"direccion":"Av siemrp Viva",
	"dni":"75444144",
	"correo":"juanito3@gmail",
    "usuario": "juanito3",
    "password": "123"*/
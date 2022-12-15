package pe.edu.idat.tiendamonturas_v01.repositorio

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pe.edu.idat.tiendamonturas_v01.retrofit.TiendaCliente
import pe.edu.idat.tiendamonturas_v01.retrofit.request.RequestLogin
import pe.edu.idat.tiendamonturas_v01.retrofit.request.RequestRegistro
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseLogin
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseRegistro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuarioRepository {

    var loginResponse= MutableLiveData<ResponseLogin>()
    var registroResponse = MutableLiveData<ResponseRegistro>()


    fun registrarUsuario(requestRegistro: RequestRegistro)
            : MutableLiveData<ResponseRegistro> {
        val call: Call<ResponseRegistro> = TiendaCliente.retrofiteService.registro(requestRegistro)
        call.enqueue(object : Callback<ResponseRegistro> {
            override fun onResponse(
                call: Call<ResponseRegistro>,
                response: Response<ResponseRegistro>
            ) {
                registroResponse.value=response.body()
            }

            override fun onFailure(call: Call<ResponseRegistro>, t: Throwable) {
                Log.e("ErrorLOGIN",  t.message.toString())

            }


        })
        return registroResponse
    }

    fun autenticarUsuario(requestLogin: RequestLogin)
            : MutableLiveData<ResponseLogin>{
        val call: Call<ResponseLogin> =
            TiendaCliente.retrofiteService.login(requestLogin)
        call.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                loginResponse.value = response.body()
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                Log.e("ErrorLogin", t.message.toString())
            }

        })

        return loginResponse
    }
}
package pe.edu.idat.tiendamonturas_v01.repositorio

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pe.edu.idat.tiendamonturas_v01.retrofit.TiendaCliente
import pe.edu.idat.tiendamonturas_v01.retrofit.model.Montura
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MonturaRepository {

    var monturaResponse=MutableLiveData<List<Montura>>()
    var monturaNomResponse=MutableLiveData<List<Montura>>()

    fun listarMonturas():MutableLiveData<List<Montura>>{
        val call: Call<List<Montura>> = TiendaCliente.retrofiteService.listarMonturas()
        call.enqueue(object :Callback<List<Montura>>{
            override fun onResponse(
                call: Call<List<Montura>>,
                response: Response<List<Montura>>) {
                monturaResponse.value=response.body()
            }

            override fun onFailure(call: Call<List<Montura>>, t: Throwable) {
                Log.e("ErrorLISTADOMONTURAS",  t.message.toString())
            }

        })
        return monturaResponse
    }

    fun listarByNombre(nomb:String):MutableLiveData<List<Montura>>{
        val call:Call<List<Montura>> = TiendaCliente.retrofiteService.listarMonturasByName(nomb)
        call.enqueue(object :Callback<List<Montura>>{
            override fun onResponse(call: Call<List<Montura>>, response: Response<List<Montura>>) {
              monturaNomResponse.value=response.body()
            }

            override fun onFailure(call: Call<List<Montura>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        return monturaNomResponse
    }


}
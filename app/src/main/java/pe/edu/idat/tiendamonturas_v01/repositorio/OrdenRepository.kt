package pe.edu.idat.tiendamonturas_v01.repositorio

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pe.edu.idat.tiendamonturas_v01.retrofit.TiendaCliente
import pe.edu.idat.tiendamonturas_v01.retrofit.request.RequestGuardarOrden
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseGuardarOrden
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseItemOrder
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseListMontura
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseOrden
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrdenRepository {
    var ordenResponse=MutableLiveData<ResponseGuardarOrden>()
    var listordenResponse=MutableLiveData<List<ResponseOrden>>()
    var listItemOrderResponse=MutableLiveData<List<ResponseItemOrder>>()


    fun guardarOrden(requestGuardarOrden: RequestGuardarOrden)
            :MutableLiveData<ResponseGuardarOrden>{
        val call: Call<ResponseGuardarOrden> = TiendaCliente.retrofiteService.guardarOrden(requestGuardarOrden)
        call.enqueue(object :Callback<ResponseGuardarOrden>{
            override fun onResponse(
                call: Call<ResponseGuardarOrden>,
                response: Response<ResponseGuardarOrden>
            ) {
                ordenResponse.value=response.body()
            }

            override fun onFailure(call: Call<ResponseGuardarOrden>, t: Throwable) {
                Log.e("ErrorSave", t.message.toString())
            }

        })
        return ordenResponse
    }
    fun listaritemsCarritOrden():MutableLiveData<List<ResponseItemOrder>>{
        val call:Call<List<ResponseItemOrder>> = TiendaCliente.retrofiteService.itemOrderCarrito()
        call.enqueue(object :Callback<List<ResponseItemOrder>>{
            override fun onResponse(
                call: Call<List<ResponseItemOrder>>,
                response: Response<List<ResponseItemOrder>>
            ) {
                listItemOrderResponse.value=response.body()
            }

            override fun onFailure(call: Call<List<ResponseItemOrder>>, t: Throwable) {
                Log.e("ErrItemCART", t.message.toString())
            }

        })
        return listItemOrderResponse
    }

    fun listarOrdenByIdClient( idCliente:Int):MutableLiveData<List<ResponseOrden>>{
        val call:Call<List<ResponseOrden>> = TiendaCliente.retrofiteService.listOrdenByClienteId(idCliente)
        call.enqueue(object : Callback<List<ResponseOrden>>{
            override fun onResponse(
                call: Call<List<ResponseOrden>>,
                response: Response<List<ResponseOrden>>
            ) {
                listordenResponse.value=response.body()
            }

            override fun onFailure(call: Call<List<ResponseOrden>>, t: Throwable) {
                Log.e("ErrorListIdCli",  t.message.toString())
            }

        })
        return listordenResponse
    }
}
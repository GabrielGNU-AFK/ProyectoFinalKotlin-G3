package pe.edu.idat.tiendamonturas_v01.repositorio

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pe.edu.idat.tiendamonturas_v01.retrofit.TiendaCliente
import pe.edu.idat.tiendamonturas_v01.retrofit.request.RequestGuardarOrden
import pe.edu.idat.tiendamonturas_v01.retrofit.request.RequestItemCarrito
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseCarrito
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseItemCarrito
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CarritoRepository {
    var carritoListResponse=MutableLiveData<List<ResponseItemCarrito>>()
    var carritoAddResponse=MutableLiveData<ResponseCarrito>()
    var carritoFindByIdResponse=MutableLiveData<ResponseItemCarrito>()
    var carritoFindByNombreResponse=MutableLiveData<ResponseItemCarrito>()
    var carritoElimResponse=MutableLiveData<ResponseCarrito>()
    var carritoEditResponse=MutableLiveData<ResponseCarrito>()
    var carritoDltRespons=MutableLiveData<ResponseCarrito>()

    fun limpiarCarrito():MutableLiveData<ResponseCarrito>{
        val call : Call<ResponseCarrito> = TiendaCliente.retrofiteService.deleteAllItems()
        call.enqueue(object : Callback<ResponseCarrito>{
            override fun onResponse(
                call: Call<ResponseCarrito>,
                response: Response<ResponseCarrito>
            ) {
                carritoDltRespons.value=response.body()
            }

            override fun onFailure(call: Call<ResponseCarrito>, t: Throwable) {
                Log.e("ErrorDltAllCart",  t.message.toString())
            }

        })
        return carritoDltRespons
    }

    fun editItemCarrito(requestItemCarrito: ResponseItemCarrito):
            MutableLiveData<ResponseCarrito>{
        val call: Call<ResponseCarrito> = TiendaCliente.retrofiteService.editarItemCarrito(requestItemCarrito)
        call.enqueue(object :Callback<ResponseCarrito>{
            override fun onResponse(
                call: Call<ResponseCarrito>,
                response: Response<ResponseCarrito>
            ) {
                carritoEditResponse.value=response.body()
            }

            override fun onFailure(call: Call<ResponseCarrito>, t: Throwable) {
                Log.e("ErrorEdit",  t.message.toString())
            }

        })
        return carritoEditResponse
    }

    fun addItemCarrito(requestItemCarrito: RequestItemCarrito)
            :MutableLiveData<ResponseCarrito>{
        val call:Call<ResponseCarrito> = TiendaCliente.retrofiteService.agregarAlCarrito(requestItemCarrito)
        call.enqueue(object :Callback<ResponseCarrito>{
            override fun onResponse(
                call: Call<ResponseCarrito>,
                response: Response<ResponseCarrito>
            ) {
                carritoAddResponse.value=response.body()
            }

            override fun onFailure(call: Call<ResponseCarrito>, t: Throwable) {
                Log.e("ErrorAddCart",  t.message.toString())
            }

        })
        return carritoAddResponse
    }


    fun listarCarrito():MutableLiveData<List<ResponseItemCarrito>>{
        val call:Call<List<ResponseItemCarrito>> = TiendaCliente.retrofiteService.itemsCarrito()
        call.enqueue(object :Callback<List<ResponseItemCarrito>>{
            override fun onResponse(
                call: Call<List<ResponseItemCarrito>>,
                response: Response<List<ResponseItemCarrito>>
            ) {
                carritoListResponse.value=response.body()
            }

            override fun onFailure(call: Call<List<ResponseItemCarrito>>, t: Throwable) {
                Log.e("ErrorListCart",  t.message.toString())
            }

        })
        return carritoListResponse
    }

    fun findById(idCarrito:Int):MutableLiveData<ResponseItemCarrito>{
        val call:Call<ResponseItemCarrito> = TiendaCliente.retrofiteService.itemCarritoById(idCarrito)
        call.enqueue(object :Callback<ResponseItemCarrito>{
            override fun onResponse(
                call: Call<ResponseItemCarrito>,
                response: Response<ResponseItemCarrito>
            ) {
                carritoFindByIdResponse.value=response.body()
            }

            override fun onFailure(call: Call<ResponseItemCarrito>, t: Throwable) {
                Log.e("ErrorFindId",  t.message.toString())
            }

        })
        return carritoFindByIdResponse
    }

    fun findByNombre(nombre:String):MutableLiveData<ResponseItemCarrito>{
        val call:Call<ResponseItemCarrito> = TiendaCliente.retrofiteService.itemCarritoByNombre(nombre)
        call.enqueue(object :Callback<ResponseItemCarrito>{
            override fun onResponse(
                call: Call<ResponseItemCarrito>,
                response: Response<ResponseItemCarrito>
            ) {
                carritoFindByNombreResponse.value=response.body()
            }

            override fun onFailure(call: Call<ResponseItemCarrito>, t: Throwable) {
                Log.e("ErrorFindName",  t.message.toString())
            }

        })
        return  carritoFindByNombreResponse

    }















    fun eliminaItem(id:Int):MutableLiveData<ResponseCarrito>{
        val call:Call<ResponseCarrito> = TiendaCliente.retrofiteService.deleteItemByCarritoId(id)
        call.enqueue(object : Callback<ResponseCarrito>{
            override fun onResponse(
                call: Call<ResponseCarrito>,
                response: Response<ResponseCarrito>
            ) {
                carritoElimResponse.value=response.body()
            }

            override fun onFailure(call: Call<ResponseCarrito>, t: Throwable) {
                Log.e("ErrorElimItm",  t.message.toString())
            }

        })
        return carritoElimResponse
    }
}
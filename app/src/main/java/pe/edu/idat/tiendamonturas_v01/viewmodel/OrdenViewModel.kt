package pe.edu.idat.tiendamonturas_v01.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pe.edu.idat.tiendamonturas_v01.db.entity.ItemEntity
import pe.edu.idat.tiendamonturas_v01.repositorio.OrdenRepository
import pe.edu.idat.tiendamonturas_v01.retrofit.request.RequestGuardarOrden
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseGuardarOrden
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseItemOrder
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseOrden

class OrdenViewModel:ViewModel() {
    var responseGuardarOrden:LiveData<ResponseGuardarOrden>
    private  var repository=OrdenRepository()
    init {
        responseGuardarOrden=repository.ordenResponse
    }

    fun listarOrden(idCliente:Int):LiveData<List<ResponseOrden>>{
        return repository.listarOrdenByIdClient(idCliente)
    }

    fun guardarOrder(idCliente: Int,descuento:Double,monturaRequest:List<ItemEntity>){
        responseGuardarOrden=repository.guardarOrden(RequestGuardarOrden(idCliente,descuento,monturaRequest))
    }



}
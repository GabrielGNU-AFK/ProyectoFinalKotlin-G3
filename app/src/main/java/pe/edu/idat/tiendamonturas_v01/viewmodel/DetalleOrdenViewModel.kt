package pe.edu.idat.tiendamonturas_v01.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pe.edu.idat.tiendamonturas_v01.repositorio.DetalleOrdenRepository
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseDetalleOrden

class DetalleOrdenViewModel:ViewModel() {
    private var repository=DetalleOrdenRepository()

    fun listarDetalle(idOrden:Int):LiveData<List<ResponseDetalleOrden>>{
        return repository.listarDetalleOrdByIdOrd(idOrden)
    }
}
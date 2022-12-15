package pe.edu.idat.tiendamonturas_v01.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pe.edu.idat.tiendamonturas_v01.repositorio.CarritoRepository
import pe.edu.idat.tiendamonturas_v01.retrofit.request.RequestItemCarrito
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseCarrito
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseItemCarrito


class DetalleProductoViewModel:ViewModel() {
    var responseCarrito:LiveData<ResponseCarrito>
    var responseItemCarrito:LiveData<ResponseItemCarrito>
    private var repository=CarritoRepository()
    init {
        responseCarrito=repository.carritoAddResponse
        responseItemCarrito=repository.carritoFindByIdResponse
    }


    fun agregarItemCarrito(idMontura:Int,nombre:String, descripcion:String,
                           color:String, material:String,  forma:String,
                           genero:String, urlImagen:String,  precio:Double,
                           quantity:Int){
        responseCarrito=repository.addItemCarrito(RequestItemCarrito(idMontura,nombre,descripcion, color, material, forma, genero, urlImagen, precio, quantity))
    }

    fun verificarItem(nombre:String):LiveData<ResponseItemCarrito>{
        responseItemCarrito=repository.findByNombre(nombre)
        return responseItemCarrito
    }
}
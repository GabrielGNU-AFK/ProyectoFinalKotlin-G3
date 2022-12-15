package pe.edu.idat.tiendamonturas_v01.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.idat.tiendamonturas_v01.repositorio.CarritoRepository
import pe.edu.idat.tiendamonturas_v01.repositorio.OrdenRepository
import pe.edu.idat.tiendamonturas_v01.retrofit.request.RequestItemCarrito
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseCarrito
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseItemCarrito
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseItemOrder


class CarritoViewModel:ViewModel() {

    var responseEdit:LiveData<ResponseCarrito>

    private var repository=CarritoRepository()
    private var repositoryOrd=OrdenRepository()

    init{
        responseEdit=repository.carritoEditResponse
    }

    fun listarItems():LiveData<List<ResponseItemCarrito>>{

        return repository.listarCarrito()
    }

    fun listarOrderItem():LiveData<List<ResponseItemOrder>>{
        return repositoryOrd.listaritemsCarritOrden()
    }



    fun eliminarItem(idItem:Int):LiveData<ResponseCarrito>{
        return repository.eliminaItem(idItem)
    }

    fun incrementar(idCarrito:Int, idmontura:Int, nombre:String,  descripcion:String,
                     color:String, material:String,  forma:String,
                     genero:String,urlImagen:String, precio:Double,
                      quantity:Int){

        responseEdit=repository.editItemCarrito(ResponseItemCarrito(idCarrito,idmontura, nombre, descripcion, color, material, forma, genero, urlImagen, precio, quantity))

    }

    fun eliminarTodo():LiveData<ResponseCarrito>{
        return repository.limpiarCarrito()
    }


}
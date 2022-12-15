package pe.edu.idat.tiendamonturas_v01.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.edu.idat.tiendamonturas_v01.repositorio.MonturaRepository
import pe.edu.idat.tiendamonturas_v01.retrofit.model.Montura
import pe.edu.idat.tiendamonturas_v01.retrofit.model.ProductItem
import retrofit2.Response

class MonturaViewModel:ViewModel() {
    private var repository=MonturaRepository()

    fun listarMonturas():LiveData<List<Montura>>{
        return repository.listarMonturas()
    }

    fun listarByNombre(nomb:String):LiveData<List<Montura>>{
        return repository.listarByNombre(nomb)
    }
}
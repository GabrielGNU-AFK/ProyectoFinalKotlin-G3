package pe.edu.idat.tiendamonturas_v01.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.idat.tiendamonturas_v01.db.TiendaMonturasRoomDataBase
import pe.edu.idat.tiendamonturas_v01.db.entity.ClienteEntity
import pe.edu.idat.tiendamonturas_v01.repositorio.ClienteRepository

class ClienteViewModel (application: Application): AndroidViewModel(application) {
    private val repository:ClienteRepository
    init {
        val dao= TiendaMonturasRoomDataBase.getDatabase(application).clienteDao()
        repository= ClienteRepository(dao)
    }
    fun insertar(clienteEntity: ClienteEntity)=viewModelScope.launch(Dispatchers.IO){
        repository.insertar(clienteEntity)
    }
    fun actualizar(clienteEntity: ClienteEntity)=viewModelScope.launch(Dispatchers.IO){
        repository.actualizar(clienteEntity)
    }
    fun eliminarTodo()=viewModelScope.launch(Dispatchers.IO){
        repository.eliminartodo()
    }
    fun obtener():LiveData<ClienteEntity>{
        return repository.obtener()
    }

    fun obtenerId():LiveData<Int>{
        return repository.obtenerId()
    }


}
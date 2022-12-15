package pe.edu.idat.tiendamonturas_v01.repositorio

import androidx.lifecycle.LiveData
import pe.edu.idat.tiendamonturas_v01.db.dao.ClienteDao
import pe.edu.idat.tiendamonturas_v01.db.entity.ClienteEntity

class ClienteRepository(private val clienteDao: ClienteDao) {

    suspend fun insertar(clienteEntity: ClienteEntity){
        clienteDao.insertar(clienteEntity)
    }
    suspend fun actualizar(clienteEntity: ClienteEntity){
        clienteDao.actualizar(clienteEntity)
    }
    suspend fun eliminartodo(){
        clienteDao.eliminartodo()
    }
    fun obtener():
            LiveData<ClienteEntity>{
        return  clienteDao.obtener()
    }

    fun obtenerId():LiveData<Int>{
        return  clienteDao.obtenerId()
    }
}
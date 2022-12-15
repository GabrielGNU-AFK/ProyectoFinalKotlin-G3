package pe.edu.idat.tiendamonturas_v01.repositorio

import androidx.lifecycle.LiveData
import pe.edu.idat.tiendamonturas_v01.db.dao.ItemDao
import pe.edu.idat.tiendamonturas_v01.db.entity.ItemEntity

class ItemRepository(private val itemDao: ItemDao) {

    suspend fun insertar(itemEntity: ItemEntity){
        itemDao.insertar(itemEntity)
    }

    suspend fun actualizar(itemEntity: ItemEntity){
        itemDao.actualizar(itemEntity)
    }

    suspend fun eliminarTodo(){
        itemDao.eliminarTodo()
    }

    suspend fun eliminarById(idMont:Int){
        itemDao.eliminarByID(idMont)
    }

   fun obtener():LiveData<List<ItemEntity>>{
        return  itemDao.obtener()
    }
}
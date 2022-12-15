package pe.edu.idat.tiendamonturas_v01.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.edu.idat.tiendamonturas_v01.db.entity.ClienteEntity

@Dao
interface ClienteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertar(vararg persona: ClienteEntity)

    @Update
    fun actualizar(vararg persona: ClienteEntity)

    @Query("DELETE FROM cliente")
    fun eliminartodo()

    @Query("SELECT * FROM cliente LIMIT 1")
    fun obtener(): LiveData<ClienteEntity>

    @Query("SELECT idCliente FROM cliente LIMIT 1")
    fun obtenerId():LiveData<Int>
}
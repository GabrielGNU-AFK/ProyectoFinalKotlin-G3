package pe.edu.idat.tiendamonturas_v01.db.dao

import android.content.ClipData.Item
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import pe.edu.idat.tiendamonturas_v01.db.entity.ItemEntity

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertar( vararg item:ItemEntity)

    @Update
    fun actualizar( vararg item:ItemEntity)

    @Query("DELETE FROM item")
    fun eliminarTodo()

    @Query("DELETE FROM item WHERE idmontura= :idMont")
    fun eliminarByID(idMont:Int)

    @Query("SELECT * FROM item ")
    fun obtener(): LiveData<List<ItemEntity>>

    @Query("SELECT * FROM item WHERE nombre == :nom ")
    fun obtenerByName(nom:String):LiveData<ItemEntity>
}
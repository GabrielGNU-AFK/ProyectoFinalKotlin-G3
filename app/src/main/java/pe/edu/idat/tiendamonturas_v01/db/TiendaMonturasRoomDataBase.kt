package pe.edu.idat.tiendamonturas_v01.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pe.edu.idat.tiendamonturas_v01.db.dao.ClienteDao
import pe.edu.idat.tiendamonturas_v01.db.dao.ItemDao
import pe.edu.idat.tiendamonturas_v01.db.entity.ClienteEntity
import pe.edu.idat.tiendamonturas_v01.db.entity.ItemEntity

@Database(entities = [ClienteEntity::class,ItemEntity::class], version = 1)
abstract class TiendaMonturasRoomDataBase:RoomDatabase() {
    abstract fun clienteDao():ClienteDao
    abstract fun itemDao():ItemDao

    companion object{

        private var INSTANCE: TiendaMonturasRoomDataBase?=null

        fun getDatabase(context: Context):TiendaMonturasRoomDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TiendaMonturasRoomDataBase::class.java,
                    "tiendamonturasdb"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
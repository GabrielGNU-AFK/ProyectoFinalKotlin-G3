package pe.edu.idat.tiendamonturas_v01.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
data class ItemEntity (
    @PrimaryKey
    var idmontura:Int,
    var nombre:String,
    var descripcion:String,
    var color:String,
    var material:String,
    var forma:String,
    var genero:String,
    var urlImagen:String,
    var precio:Double,
    var quantity:Int)


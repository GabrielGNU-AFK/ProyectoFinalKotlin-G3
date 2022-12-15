package pe.edu.idat.tiendamonturas_v01.retrofit.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RequestItemCarrito( var idmontura:Int, var nombre:String, var descripcion:String,
var color:String,var material:String, var forma:String,
var genero:String,var urlImagen:String, var precio:Double,
var quantity:Int):Parcelable

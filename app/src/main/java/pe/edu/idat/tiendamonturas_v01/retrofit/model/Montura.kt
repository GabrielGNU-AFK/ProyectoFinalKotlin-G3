package pe.edu.idat.tiendamonturas_v01.retrofit.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Montura (var idMontura: Int,var nombre:String,var descripcion: String,var color:String,
                    var material:String,var forma:String,var genero:String,
                    var urlImagen:String,var precio:Double, var stock:Integer,var estado:Boolean ):Parcelable


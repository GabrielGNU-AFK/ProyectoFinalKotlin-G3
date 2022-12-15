package pe.edu.idat.tiendamonturas_v01.retrofit.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseItemCarrito ( var idCarrito:Int,var idmontura:Int,var nombre:String, var descripcion:String,
                                 var color:String,var material:String, var forma:String,
                                 var genero:String,var urlImagen:String, var precio:Double,
                                 var quantity:Int):Parcelable
/**
 * "idCarrito": 1,
"nombre": "sadasd",
"descripcion": "asdsad",
"color": "sdadsa",
"material": "sadsad",
"forma": "asdas",
"genero": "asdasdsa",
"urlImagen": "sadsada",
"precio": 22,
"quantity": 2
 */

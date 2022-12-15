package pe.edu.idat.tiendamonturas_v01.retrofit.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
data class ResponseOrden (var idOrden:Integer, var numeroOrden:String,var fechaOrden:Date,
                          var estadoOrden:String,var totalOrden:Double):Parcelable

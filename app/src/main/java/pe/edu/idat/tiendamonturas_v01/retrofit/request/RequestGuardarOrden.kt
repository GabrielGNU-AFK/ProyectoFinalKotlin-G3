package pe.edu.idat.tiendamonturas_v01.retrofit.request

import pe.edu.idat.tiendamonturas_v01.db.entity.ItemEntity
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseItemOrder

data class RequestGuardarOrden( var id_Cliente:Int,var descuento:Double,var monturaRequest: List<ItemEntity>)

package pe.edu.idat.tiendamonturas_v01.retrofit.request

data class MonturaRequest (var idMontura:Integer,var nombre:String,var descripcion:String,
                           var color:String,var material:String,var forma:String,var genero:String,
                            var urlImagen:String,var precio:Double,var quantity:Integer)

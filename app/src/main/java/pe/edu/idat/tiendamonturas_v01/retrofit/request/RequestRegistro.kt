package pe.edu.idat.tiendamonturas_v01.retrofit.request

data class RequestRegistro(
    var nombres: String,
    var apellidos: String,
    var edad: Int,
    var nroCelular: String,
    var direccion: String,
    var dni:String,
    var correo: String,
    var usuario: String,
    var password: String
)


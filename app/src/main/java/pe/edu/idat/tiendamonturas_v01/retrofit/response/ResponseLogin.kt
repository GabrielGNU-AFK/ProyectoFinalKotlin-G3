package pe.edu.idat.tiendamonturas_v01.retrofit.response

import pe.edu.idat.tiendamonturas_v01.retrofit.model.Cliente

data class ResponseLogin(var token:String, var cliente:Cliente,var rpta:Boolean)


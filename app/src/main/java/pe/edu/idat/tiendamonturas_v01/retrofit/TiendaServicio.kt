package pe.edu.idat.tiendamonturas_v01.retrofit

import pe.edu.idat.tiendamonturas_v01.retrofit.model.Montura
import pe.edu.idat.tiendamonturas_v01.retrofit.request.RequestLogin
import pe.edu.idat.tiendamonturas_v01.retrofit.request.RequestGuardarOrden
import pe.edu.idat.tiendamonturas_v01.retrofit.request.RequestItemCarrito
import pe.edu.idat.tiendamonturas_v01.retrofit.request.RequestRegistro
import pe.edu.idat.tiendamonturas_v01.retrofit.response.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TiendaServicio {

    @POST("usuarios/v1/crearToken")
    fun login(@Body requestLogin: RequestLogin): Call<ResponseLogin>

    @POST("usuarios/v1/guardar")
    fun registro(@Body requestRegistro: RequestRegistro): Call<ResponseRegistro>

    @GET("monturas/v1/listar")
    fun listarMonturas():Call<List<Montura>>

    @GET("monturas/v1/listarByNombre/{nomb}")
    fun listarMonturasByName(@Path("nomb") nomb:String):Call<List<Montura>>

    @POST("ordenes/v1/guardar")
    fun guardarOrden(@Body requestGuardarOrden: RequestGuardarOrden): Call<ResponseGuardarOrden>

    @GET("ordenes/v1/listarByClienteId/{id}")
    fun listOrdenByClienteId(@Path("id") id:Int):Call<List<ResponseOrden>>

    @GET("detalles/v1/listar/{id}")
    fun listDetalleByOrdenId(@Path("id") id:Int): Call<List<ResponseDetalleOrden>>

    //carrito
    @POST("carrito/v1/guardar")
    fun agregarAlCarrito(@Body requestItemCarrito: RequestItemCarrito):Call<ResponseCarrito>
    @GET("carrito/v1/listar")
    fun itemsCarrito():Call<List<ResponseItemCarrito>>
    @GET("carrito/v1/listar/{id}")
    fun itemCarritoById(@Path("id") id:Int):Call<ResponseItemCarrito>
    @GET("carrito/v1/obtenerByNombre/{nombre}")
    fun itemCarritoByNombre(@Path("nombre") nombre:String):Call<ResponseItemCarrito>
    @PUT("carrito/v1/editar")
    fun editarItemCarrito(@Body requestItemCarrito: ResponseItemCarrito):Call<ResponseCarrito>
    @DELETE("carrito/v1/eliminar/{id}")
    fun deleteItemByCarritoId(@Path("id") id:Int):Call<ResponseCarrito>
    @DELETE("carrito/v1/limpiarcarrito")
    fun deleteAllItems():Call<ResponseCarrito>
    @GET("carrito/v1/listarItems")
    fun itemOrderCarrito():Call<List<ResponseItemOrder>>




}
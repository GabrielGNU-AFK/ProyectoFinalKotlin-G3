package pe.edu.idat.tiendamonturas_v01.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_pasarela.*
import pe.edu.idat.apppatitasidat2022.utilitarios.AppMensaje
import pe.edu.idat.apppatitasidat2022.utilitarios.TipoMensaje
import pe.edu.idat.tiendamonturas_v01.R
import pe.edu.idat.tiendamonturas_v01.databinding.FragmentCarritoBinding
import pe.edu.idat.tiendamonturas_v01.databinding.FragmentPasarelaBinding
import pe.edu.idat.tiendamonturas_v01.db.entity.ItemEntity
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseGuardarOrden
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseItemOrder
import pe.edu.idat.tiendamonturas_v01.viewmodel.CarritoViewModel
import pe.edu.idat.tiendamonturas_v01.viewmodel.ClienteViewModel
import pe.edu.idat.tiendamonturas_v01.viewmodel.ItemViewModel
import pe.edu.idat.tiendamonturas_v01.viewmodel.OrdenViewModel


class PasarelaFragment : Fragment(),View.OnClickListener {
    //private lateinit var carritoViewModel: CarritoViewModel
    private lateinit var itemViewModel:ItemViewModel
    private lateinit var ordenViewModel: OrdenViewModel
    private lateinit var binding: FragmentPasarelaBinding
    private lateinit var clienteViewModel: ClienteViewModel
    private var idClientex:Int = 0
    private var lista:List<ItemEntity> = emptyList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pasarela, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentPasarelaBinding.bind(view)
        ordenViewModel= ViewModelProvider(this)[OrdenViewModel::class.java]
        itemViewModel=ViewModelProvider(this)[ItemViewModel::class.java]
        clienteViewModel=ViewModelProvider(this)[ClienteViewModel::class.java]



        obtenerIdCliente()
        binding.btnpagar.setOnClickListener(this)
        binding.btnPassScanQR.setOnClickListener(this)

        ordenViewModel.responseGuardarOrden.observe(viewLifecycleOwner,Observer{
            response->obtenerResultado(response)
        })


    }

    private fun obtenerResultado(response: ResponseGuardarOrden) {
        if (response.respuesta){
            AppMensaje.enviarMensaje(binding.root,response.mensaje,TipoMensaje.EXITO)
            val action=PasarelaFragmentDirections.actionPasarelaToMonturas()
            itemViewModel.eliminarTodo()
            findNavController().navigate(action)

        }else{
            AppMensaje.enviarMensaje(binding.root,response.mensaje,TipoMensaje.ERROR)
        }

    }


    fun returnItems(){


        if(validar()){
            val num=tvDescuentoQR.text.toString().toDouble()
            if (num>0.0){
                itemViewModel.obtener().observe(viewLifecycleOwner,
                    Observer {
                        ordenViewModel.guardarOrder(idClientex,num,it)
                    })

            }else{
                itemViewModel.obtener().observe(viewLifecycleOwner,
                    Observer {
                        ordenViewModel.guardarOrder(idClientex,0.0,it)
                    })
            }

        }
    }


    private fun validar(): Boolean {
        var respuesta=true
        var mensaje=""
        when{
            binding.edtnrotarjeta.text.toString().trim().isEmpty()->{
                binding.edtnrotarjeta.isFocusableInTouchMode
                binding.edtnrotarjeta.requestFocus()
                mensaje="Ingrese su numero de tarjeta"
                respuesta=false
            }
            binding.edtnrotarjeta.text.toString().trim().length<16->{
                binding.edtnrotarjeta.isFocusableInTouchMode
                binding.edtnrotarjeta.requestFocus()
                mensaje="Ingrese un numero de tarjeta valido"
                respuesta=false
            }
            binding.edtAnioTarjeta.text.toString().trim().isEmpty()->{
                binding.edtAnioTarjeta.isFocusableInTouchMode
                binding.edtAnioTarjeta.requestFocus()
                mensaje="Ingrese el año de su tarjeta"
                respuesta=false
            }
            binding.edtmestarjeta.text.toString().trim().isEmpty()->{
                binding.edtmestarjeta.isFocusableInTouchMode
                binding.edtmestarjeta.requestFocus()
                mensaje="Ingrese el numero del mes de su tarjeta"
                respuesta=false
            }
            binding.edtclave.text.toString().trim().isEmpty()->{
                binding.edtclave.isFocusableInTouchMode
                binding.edtclave.requestFocus()
                mensaje="Ingrese el cvv de su tarjeta"
                respuesta=false
            }
        }
        if (!respuesta) AppMensaje.enviarMensaje(binding.root,mensaje,TipoMensaje.ERROR)
        return respuesta
    }

    fun obtenerIdCliente(){
        clienteViewModel.obtener().observe(viewLifecycleOwner,
            Observer{
                    cliente->cliente?.let {
                        idClientex=cliente.idCliente
                    }
            })
    }
    private fun initScan(){
        IntentIntegrator.forSupportFragment(this).initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if(result!=null){
            val tvEmailUser: TextView =binding.tvDescuentoQR
            if(result.contents==null){
                Toast.makeText(activity,"Cancelado", Toast.LENGTH_SHORT).show()

            }else{

                val promo:String=result.contents
                val delimit=":"
                val list= promo.split(delimit)
                if (list[0] == "buenavision"){
                    tvEmailUser.text=list[1]
                    AppMensaje.enviarMensaje(binding.root,"El descuento es: ${list[1]}",TipoMensaje.EXITO)
                }else{
                    AppMensaje.enviarMensaje(binding.root,"El codigo QR no es valido",TipoMensaje.ERROR)
                }

            }
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }



    override fun onClick(vista: View) {
        when(vista.id){
            R.id.btnpagar -> returnItems()
            R.id.btnPassScanQR->initScan()
        }

    }


}
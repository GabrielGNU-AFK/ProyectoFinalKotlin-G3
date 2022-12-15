package pe.edu.idat.tiendamonturas_v01.view.fragment

import android.content.Intent
import android.icu.number.IntegerWidth
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.fragment_carrito.*
import pe.edu.idat.apppatitasidat2022.utilitarios.AppMensaje
import pe.edu.idat.apppatitasidat2022.utilitarios.MiApp
import pe.edu.idat.apppatitasidat2022.utilitarios.MiApp.Companion.applicationContext
import pe.edu.idat.apppatitasidat2022.utilitarios.TipoMensaje
import pe.edu.idat.tiendamonturas_v01.R
import pe.edu.idat.tiendamonturas_v01.databinding.FragmentCarritoBinding
import pe.edu.idat.tiendamonturas_v01.databinding.FragmentOrdenBinding
import pe.edu.idat.tiendamonturas_v01.databinding.ItemsCartBinding
import pe.edu.idat.tiendamonturas_v01.db.entity.ItemEntity
import pe.edu.idat.tiendamonturas_v01.retrofit.response.*
import pe.edu.idat.tiendamonturas_v01.view.RegistroActivity
import pe.edu.idat.tiendamonturas_v01.view.ScannerActivity
import pe.edu.idat.tiendamonturas_v01.view.adapter.CarritoAdapter
import pe.edu.idat.tiendamonturas_v01.view.adapter.ItemAdapter
import pe.edu.idat.tiendamonturas_v01.viewmodel.CarritoViewModel
import pe.edu.idat.tiendamonturas_v01.viewmodel.ItemViewModel
import pe.edu.idat.tiendamonturas_v01.viewmodel.OrdenViewModel

class CarritoFragment : Fragment(),View.OnClickListener {


    private lateinit var ordenViewModel: OrdenViewModel
    private lateinit var itemViewModel: ItemViewModel
    //private lateinit var adapter: CarritoAdapter
    private lateinit var adapter: ItemAdapter
    private lateinit var binding: FragmentCarritoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carrito, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentCarritoBinding.bind(view)
        ordenViewModel=ViewModelProvider(this)[OrdenViewModel::class.java]
        itemViewModel=ViewModelProvider(this)[ItemViewModel::class.java]

        cargar()
        adapter.notifyDataSetChanged()

        //eventos carrito
        adapter.setOnRemoveClickListener {
            itemViewModel.eliminarById(it.idmontura)
            adapter.notifyDataSetChanged()
            val action=CarritoFragmentDirections.actionDetalleFragmentToCarritoFragment()

            findNavController().navigate(action)
        }
        adapter.incrementClickListener {
            var sum= it.quantity +1
            itemViewModel.actualizar(ItemEntity(it.idmontura,it.nombre,it.descripcion,it.color,it.material,it.forma,it.genero
                ,it.urlImagen,it.precio,sum))

            adapter.notifyDataSetChanged()
            val action=CarritoFragmentDirections.actionDetalleFragmentToCarritoFragment()

            findNavController().navigate(action)
        }

        adapter.decrementClickListener {
            if (it.quantity>1){
            var rest=it.quantity-1

         itemViewModel.actualizar(ItemEntity(it.idmontura,it.nombre,it.descripcion,it.color,it.material,it.forma,it.genero
                    ,it.urlImagen,it.precio,rest))
                adapter.notifyDataSetChanged()
            val action=CarritoFragmentDirections.actionDetalleFragmentToCarritoFragment()
            findNavController().navigate(action)
        }else{
            AppMensaje.enviarMensaje(binding.root,"La cantidad minima es 1",TipoMensaje.ERROR)
            }
        }



        binding.cartLimpiar.setOnClickListener(this)
        binding.cartCheckout.setOnClickListener(this)



    }

    private fun cargar(){

        adapter= ItemAdapter()
        itemViewModel.obtener().observe(viewLifecycleOwner,
            Observer {response->
                adapter.differ.submitList(response)
                total(response)
            })

        binding.rvCarrito.adapter=adapter

    }

    fun total(items:List<ItemEntity>){
        var price =0.00
        var quantiy=0
        var totalquantity=0
        var subtotal=0.00
        var total=0.00
        items.forEach{p->
            price=p.precio
            quantiy=p.quantity.toInt()
            subtotal=quantiy*price
            total+=subtotal
            totalquantity+=quantiy
        }

        var igv=total*0.18
        var totalIGV=total+igv
        binding.cartItemsPrecio.text = totalIGV.toString()

        binding.cartItemsInfo.text="IGV: $igv, Nr° Productos: $totalquantity"
        if (binding.cartItemsPrecio.text.toString() == "0.0"){
            binding.cartLimpiar.visibility=View.INVISIBLE
            binding.cartItemsInfo.visibility=View.INVISIBLE
            binding.cartEmpty.visibility=View.VISIBLE

        }
        else{

            binding.cartLimpiar.visibility=View.VISIBLE
            binding.cartItemsInfo.visibility=View.VISIBLE
            binding.cartEmpty.visibility=View.INVISIBLE

        }

    }

    override fun onClick(vista: View) {
        when(vista.id){
            R.id.cart_limpiar-> limpiarCarrito()

            R.id.cart_checkout-> irPagarOrden()
        }
    }



    private fun irPagarOrden() {
        if(adapter.differ.currentList.size==0){
            AppMensaje.enviarMensaje(binding.root,"Añada productos a su carrito",TipoMensaje.ERROR)
        }else{

            val action=CarritoFragmentDirections.actionCarritoToPasarella()
            findNavController().navigate(action)
        }

    }
    private fun limpiarCarrito() {
        itemViewModel.eliminarTodo()
        adapter.notifyDataSetChanged()
        val action=CarritoFragmentDirections.actionDetalleFragmentToCarritoFragment()
        findNavController().navigate(action)
    }








}
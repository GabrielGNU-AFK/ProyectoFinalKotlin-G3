package pe.edu.idat.tiendamonturas_v01.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.items_montura.*
import pe.edu.idat.apppatitasidat2022.utilitarios.AppMensaje
import pe.edu.idat.apppatitasidat2022.utilitarios.TipoMensaje
import pe.edu.idat.tiendamonturas_v01.R
import pe.edu.idat.tiendamonturas_v01.databinding.FragmentDetalleMonturaBinding
import pe.edu.idat.tiendamonturas_v01.db.entity.ItemEntity
import pe.edu.idat.tiendamonturas_v01.retrofit.model.Montura
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseCarrito
import pe.edu.idat.tiendamonturas_v01.viewmodel.DetalleProductoViewModel
import pe.edu.idat.tiendamonturas_v01.viewmodel.ItemViewModel


class DetalleMonturaFragment : Fragment(),View.OnClickListener {
    private lateinit var detalleProductoViewModel: DetalleProductoViewModel
    private lateinit var itemViewModel: ItemViewModel

    private lateinit var binding: FragmentDetalleMonturaBinding

    private lateinit var producItem: Montura

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        producItem=DetalleMonturaFragmentArgs.fromBundle(requireArguments()).productItem
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_montura, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentDetalleMonturaBinding.bind(view)
        detalleProductoViewModel=ViewModelProvider(this)[DetalleProductoViewModel::class.java]
        itemViewModel=ViewModelProvider(this)[ItemViewModel::class.java]

        binding.btnDetalleAgregar.setOnClickListener(this)
        updateVista()
    }


    private fun updateVista(){
        binding.tvDetalleColor.text=producItem.color
        binding.tvDetallePrecio.text="SOLES ${producItem.precio}"
        binding.tvDetallGenero.text=producItem.genero
        binding.tvDetalleForm.text=producItem.forma
        binding.tvDetalleNomb.text=producItem.nombre

        binding.tvDetalleMaterial.text=producItem.material
        Glide.with(binding.ivDetalleProd)
            .load(producItem.urlImagen)
            .into(binding.ivDetalleProd)
    }

    override fun onClick(vista: View) {
        when(vista.id){
            R.id.btnDetalleAgregar-> agregarItem2()
        }
    }
    private fun agregarItem2(){
        val tex=binding.edtDetalleCantidad.text.toString()


        if (tex.isNullOrEmpty()|| tex == ""){
            AppMensaje.enviarMensaje(binding.root,"Ingrese minimo 1",TipoMensaje.ERROR);

        }else{

        itemViewModel.insertar(ItemEntity(producItem.idMontura,producItem.nombre,producItem.descripcion,producItem.color,producItem.material,
        producItem.forma,producItem.genero,producItem.urlImagen,producItem.precio,Integer.parseInt(binding.edtDetalleCantidad.text.toString())))
        AppMensaje.enviarMensaje(binding.root,"El producto ah sido agregado",TipoMensaje.EXITO)
        }
    }





}
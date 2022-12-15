package pe.edu.idat.tiendamonturas_v01.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import pe.edu.idat.tiendamonturas_v01.R
import pe.edu.idat.tiendamonturas_v01.databinding.FragmentDetalleOrdenBinding
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseOrden
import pe.edu.idat.tiendamonturas_v01.view.adapter.DetalleOrdenAdapter
import pe.edu.idat.tiendamonturas_v01.viewmodel.DetalleOrdenViewModel


class DetalleOrdenFragment : Fragment() {
    private lateinit var detalleViewModel:DetalleOrdenViewModel
    private lateinit var adapter:DetalleOrdenAdapter
    private lateinit var binding:FragmentDetalleOrdenBinding

    private lateinit var idOrden:ResponseOrden

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idOrden=DetalleOrdenFragmentArgs.fromBundle(requireArguments()).idOrden
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_orden, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentDetalleOrdenBinding.bind(view)
        detalleViewModel=ViewModelProvider(this)[DetalleOrdenViewModel::class.java]
        adapter= DetalleOrdenAdapter()
        detalleViewModel.listarDetalle(idOrden.idOrden.toInt()).observe(viewLifecycleOwner,
            Observer {
                adapter.differ.submitList(it)
            })
        adapter.setOnItemClickListener {

        }
        binding.rvListDetalleOrd.layoutManager=
            StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL)
        binding.rvListDetalleOrd.adapter=adapter
    }


}
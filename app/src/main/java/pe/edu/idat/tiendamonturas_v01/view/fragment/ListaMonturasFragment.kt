package pe.edu.idat.tiendamonturas_v01.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView

import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_home.*
import pe.edu.idat.apppatitasidat2022.utilitarios.AppMensaje
import pe.edu.idat.apppatitasidat2022.utilitarios.TipoMensaje
import pe.edu.idat.tiendamonturas_v01.MainActivity
import pe.edu.idat.tiendamonturas_v01.R
import pe.edu.idat.tiendamonturas_v01.databinding.FragmentListaMonturasBinding
import pe.edu.idat.tiendamonturas_v01.retrofit.model.Montura
import pe.edu.idat.tiendamonturas_v01.utilitarios.Resource
import pe.edu.idat.tiendamonturas_v01.view.HomeActivity

import pe.edu.idat.tiendamonturas_v01.view.adapter.MonturasAdapter
import pe.edu.idat.tiendamonturas_v01.viewmodel.MonturaViewModel


class ListaMonturasFragment : Fragment() {

    private lateinit var monturaViewModel: MonturaViewModel
    private lateinit var adapter:MonturasAdapter
    private lateinit var binding:FragmentListaMonturasBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_lista_monturas,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding=FragmentListaMonturasBinding.bind(view)
        monturaViewModel=ViewModelProvider(this)[MonturaViewModel::class.java]
        adapter=MonturasAdapter()
        initRecycler()

        binding.svBuscar.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                 if (!query.isNullOrEmpty()){
                    monturaViewModel.listarByNombre(query.toLowerCase()).observe(viewLifecycleOwner,Observer{
                        adapter.differ.submitList(it)
                    })
                }

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()){
                    initRecycler()
                }else{
                    monturaViewModel.listarByNombre(newText.toString()).observe(viewLifecycleOwner,Observer{
                        adapter.differ.submitList(it)
                    })

                }

                return false

            }

        })





        adapter.setOnItemClcickListener {
            val action=ListaMonturasFragmentDirections.actionListaMonturaFragmentToDetailMonturaFragmet(it)
            findNavController().navigate(action)
        }



    }

    fun initRecycler(){
        monturaViewModel.listarMonturas().observe(viewLifecycleOwner,
            Observer {
                adapter.differ.submitList(it)
            })
        binding.rvListaMontura.layoutManager=
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        binding.rvListaMontura.adapter=adapter
    }


}
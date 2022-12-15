package pe.edu.idat.tiendamonturas_v01.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.idat.tiendamonturas_v01.databinding.ItemsHomeBinding
import pe.edu.idat.tiendamonturas_v01.databinding.ItemsMonturaBinding
import pe.edu.idat.tiendamonturas_v01.retrofit.model.Montura

class MonturasAdapter: RecyclerView.Adapter<MonturasAdapter.MonturaViewHolder>(){
    private val callback=object :DiffUtil.ItemCallback<Montura>(){
        override fun areItemsTheSame(oldItem: Montura, newItem: Montura): Boolean {
            return oldItem.idMontura==newItem.idMontura
        }

        override fun areContentsTheSame(oldItem: Montura, newItem: Montura): Boolean {
            return oldItem==newItem
        }

    }
    val differ=AsyncListDiffer(this,callback)
    private var onItemClickListener:((Montura)->Unit)={}
    fun setOnItemClcickListener(listener:(Montura)->Unit){
        onItemClickListener=listener
    }

    inner class MonturaViewHolder(private val binding:ItemsHomeBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bindData(montura: Montura){
                    Glide.with(binding.v2Mimage)
                        .load(montura.urlImagen)
                        .into(binding.v2Mimage)
                    binding.v2Mname.text=montura.nombre
                    binding.v2Mprice.text="S/. ${montura.precio}"
                    binding.cardView.setOnClickListener{
                        onItemClickListener(montura)
                    }

                }

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonturaViewHolder {
       val binding=ItemsHomeBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MonturaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MonturaViewHolder, position: Int) {
        val product= differ.currentList[position]
        holder.bindData(product)
    }

    override fun getItemCount()= differ.currentList.size


}
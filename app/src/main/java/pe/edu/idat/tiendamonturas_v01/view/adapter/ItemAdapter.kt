package pe.edu.idat.tiendamonturas_v01.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.idat.tiendamonturas_v01.databinding.ItemsCartBinding
import pe.edu.idat.tiendamonturas_v01.db.entity.ItemEntity
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseItemCarrito

class ItemAdapter:RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private val callback=object  :DiffUtil.ItemCallback<ItemEntity>(){
        override fun areItemsTheSame(oldItem: ItemEntity, newItem: ItemEntity): Boolean {
            return oldItem.idmontura==newItem.idmontura
        }

        override fun areContentsTheSame(oldItem: ItemEntity, newItem: ItemEntity): Boolean {
            return oldItem==newItem
        }

    }
    val differ= AsyncListDiffer(this,callback)
    private var incrementListener:((ItemEntity)->Unit)={}
    private var decrementListener:((ItemEntity)->Unit)={}
    private var removeListener:((ItemEntity)->Unit)={}

    fun setOnRemoveClickListener(listener: (ItemEntity)->Unit){
        removeListener=listener
    }
    fun incrementClickListener(listener:(ItemEntity)->Unit){
        incrementListener=listener
    }

    fun decrementClickListener(listener:(ItemEntity)->Unit){
        decrementListener=listener
    }


    inner class ItemViewHolder(private val binding: ItemsCartBinding):RecyclerView.ViewHolder(binding.root){
        fun binData(itemCarrito:ItemEntity){
            binding.cartItemName.text=itemCarrito.nombre
            binding.cartItemPrice.text= "S/ ${itemCarrito.precio}"
            binding.cartItemCantidad.text="${itemCarrito.quantity}"
            Glide.with(binding.cartItemImage)
                .load(itemCarrito.urlImagen)
                .into(binding.cartItemImage)

            binding.cartItemEliminar.setOnClickListener{
                removeListener(itemCarrito)
            }
            binding.cartItemMas.setOnClickListener{
                incrementListener(itemCarrito)
            }
            binding.cartItemMenos.setOnClickListener{
                decrementListener(itemCarrito)
            }
        }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
       val binding=ItemsCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val cartItem=differ.currentList[position]
        holder.binData(cartItem)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}
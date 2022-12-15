package pe.edu.idat.tiendamonturas_v01.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.edu.idat.tiendamonturas_v01.databinding.ItemsCartBinding
import pe.edu.idat.tiendamonturas_v01.retrofit.response.ResponseItemCarrito

class CarritoAdapter: RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>() {

    private val callback= object : DiffUtil.ItemCallback<ResponseItemCarrito>(){
        override fun areItemsTheSame(
            oldItem: ResponseItemCarrito,
            newItem: ResponseItemCarrito
        ): Boolean {
            return oldItem.idCarrito==newItem.idCarrito
        }

        override fun areContentsTheSame(
            oldItem: ResponseItemCarrito,
            newItem: ResponseItemCarrito
        ): Boolean {
            return oldItem==newItem
        }

    }
    val differ=AsyncListDiffer(this,callback)
    private var incrementListener:((ResponseItemCarrito)->Unit)={}
    private var decrementListener:((ResponseItemCarrito)->Unit)={}
    private var removeListener:((ResponseItemCarrito)->Unit)={}

    fun setOnRemoveClickListener(listener: (ResponseItemCarrito)->Unit){
        removeListener=listener
    }
    fun incrementClickListener(listener:(ResponseItemCarrito)->Unit){
        incrementListener=listener
    }

    fun decrementClickListener(listener:(ResponseItemCarrito)->Unit){
        decrementListener=listener
    }

    inner class CarritoViewHolder(private val binding: ItemsCartBinding): RecyclerView.ViewHolder(binding.root){
        fun binData(itemCarrito:ResponseItemCarrito){

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val binding=ItemsCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CarritoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val cartItem=differ.currentList[position]
        holder.binData(cartItem)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}
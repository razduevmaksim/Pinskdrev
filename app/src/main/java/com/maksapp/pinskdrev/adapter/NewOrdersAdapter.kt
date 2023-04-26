package com.maksapp.pinskdrev.adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maksapp.pinskdrev.R
import com.maksapp.pinskdrev.database.CartDatabase
import com.maksapp.pinskdrev.database.CartItem
import kotlinx.android.synthetic.main.layout_orders_item.view.*

class NewOrdersAdapter(internal var context: Context) :
    RecyclerView.Adapter<NewOrdersAdapter.NewOrdersAdapterViewHolder>() {
    private var listOrders = emptyList<CartItem>()

    class NewOrdersAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewOrdersAdapterViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.layout_orders_item, parent, false)
        return NewOrdersAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewOrdersAdapterViewHolder, position: Int) {
        //установка значений в TextView
        holder.itemView.item_title.text = listOrders[position].productName
        Glide.with(context).load(listOrders[position].productImage).into(holder.itemView.item_image)
        holder.itemView.text_view_detail_price.text = listOrders[position].productPrice
        holder.itemView.text_view_detail_quantity.text = listOrders[position].productQuantity

        //вызов диалогового окна при клике на кнопку Delete.
        holder.itemView.button_delete.setOnClickListener {
            val builder = AlertDialog.Builder(it.context)
            builder.setTitle("Удаление данных")
            builder.setMessage("Вы действительно хотите удалить этот предмет из заказов'?")

            //события при клике на "отмена". Выход из диалогового окна
            builder.setNegativeButton("Отмена") { dialog, _ ->
                dialog.cancel()
            }

            //события при клике на "удалить". Удаление данных из room
            builder.setPositiveButton("Удалить") { _, _ ->
                val id = listOrders[position].productId
                CartDatabase.getInstance(Application()).cartDao().deleteById(id.toString())
            }
            builder.show()
        }
    }

    override fun getItemCount(): Int {
        //Количество возвращаемых значений
        return listOrders.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<CartItem>) {
        listOrders = list
        //обновление при изменении данных
        notifyDataSetChanged()
    }
}
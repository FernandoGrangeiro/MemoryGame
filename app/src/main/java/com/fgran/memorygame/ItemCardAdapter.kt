package com.fgran.memorygame

import android.content.Context
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_card.view.*

class ItemCardAdapter(private val context: Context)
    : RecyclerView.Adapter<ItemCardAdapter.ViewHolder>() {

    private val itens: MutableList<ItemModel> = mutableListOf()
    private val itemInterface: ItemInterface = context as ItemInterface

    private var firstClick = true
    private var firstPosition = ""
    private var isEnd = false

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(itens[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_card, parent, false))
    }

    override fun getItemCount(): Int {
        return itens.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: ItemModel) {
            itemView.imageView?.run {
                if (item.isCliked) {
                    setImageDrawable(ContextCompat.getDrawable(context, item.url))
                } else {
                    setImageDrawable(ContextCompat.getDrawable(context, R.drawable.logo))
                }
                setOnClickListener {
                    if (!firstClick) {
                        setImageDrawable(ContextCompat.getDrawable(context, item.url))
                        val handler = Handler()
                        handler.postDelayed({
                            if (!itemInterface.onClicked(item.itemId)) {
                                item.isCliked = false
                                itens.forEach {
                                    if (it.itemId == firstPosition) {
                                        it.isCliked = false
                                    }
                                }
                            } else {
                                item.isCliked = true
                            }
                            itemInterface.setIdClicked("")
                            firstPosition = ""
                            firstClick = true
                            notifyDataSetChanged()
                            itens.forEach {
                                if (!it.isCliked) {
                                    isEnd = false
                                    return@forEach
                                } else {
                                    isEnd = true
                                }

                            }
                            itemInterface.endGame(isEnd)
                        }, 500)

                    } else {
                        itemInterface.setIdClicked(item.itemId)
                        firstClick = false
                        setImageDrawable(ContextCompat.getDrawable(context, item.url))
                        firstPosition = item.itemId
                        item.isCliked = true
                        notifyDataSetChanged()
                    }
                }
            }
        }
    }

    fun add(list: List<ItemModel>) {
        itens.addAll(list)
        notifyDataSetChanged()
    }

}
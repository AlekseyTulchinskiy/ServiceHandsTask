package com.dippy_project.servicehandstask

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val dataList: MutableList<Int>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    companion object {
        const val TYPE_CARD1 = 1
        const val TYPE_CARD2 = 2
        const val TYPE_FINAL_DEAD = 3
        const val TYPE_FINAL_LIVE = 4
    }

    private var consecutiveCard1Count = 0
    private var consecutiveCard2Count = 0

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleTextView: TextView = view.findViewById(R.id.h6)
        private val descriptionTextView: TextView = view.findViewById(R.id.boby2)
        private val iconImageView: ImageView = view.findViewById(R.id.icon)
        private val ellipseContainer: View = view.findViewById(R.id.ellipse_container)

        fun bind(viewType: Int) {
            when (viewType) {
                TYPE_CARD1 -> {
                    titleTextView.text = "Мёртвая"
                    descriptionTextView.text = "или прикидывается"
                    iconImageView.setImageResource(R.drawable.ic_skull)
                    ellipseContainer.setBackgroundResource(R.drawable.dead_gradient_color)
                }
                TYPE_CARD2 -> {
                    titleTextView.text = "Живая"
                    descriptionTextView.text = "И шевелится!"
                    iconImageView.setImageResource(R.drawable.ic_explosion)
                    ellipseContainer.setBackgroundResource(R.drawable.live_gradient_color)
                }
                TYPE_FINAL_DEAD -> {
                    titleTextView.text = "Сдохла!"
                    descriptionTextView.text = "бай-бай!"
                    iconImageView.setImageResource(R.drawable.ic_skull)
                    ellipseContainer.setBackgroundResource(R.drawable.dead_gradient_color)
                }
                TYPE_FINAL_LIVE -> {
                    titleTextView.text = "Жизнь"
                    descriptionTextView.text = "Ку-ку!"
                    iconImageView.setImageResource(R.drawable.final_live)
                    ellipseContainer.setBackgroundResource(R.drawable.final_live_color)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_card, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    override fun getItemViewType(position: Int): Int {
        return dataList[position]
    }

    fun addData(type: Int) {
        dataList.add(type)
        notifyItemInserted(dataList.size - 1)

        if (type == TYPE_CARD1) {
            consecutiveCard1Count++
            consecutiveCard2Count = 0
            if (consecutiveCard1Count == 3) {
                addSpecialCard(TYPE_FINAL_DEAD)
                consecutiveCard1Count = 0
            }
        } else if (type == TYPE_CARD2) {
            consecutiveCard2Count++
            consecutiveCard1Count = 0
            if (consecutiveCard2Count == 3) {
                addSpecialCard(TYPE_FINAL_LIVE)
                consecutiveCard2Count = 0
            }
        }
    }

    private fun addSpecialCard(type: Int) {
        dataList.add(type)
        notifyItemInserted(dataList.size - 1)
    }
}
package com.fcmchat.fcmchat.chains.presentation.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fcmchat.fcmchat.R
import com.fcmchat.fcmchat.chains.interactor.Microchain

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class ChainsListAdapter(val chainsList: ArrayList<Microchain>) : RecyclerView.Adapter<ChainsListAdapter.ChainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChainViewHolder =
            ChainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.chain_item, parent, false))

    override fun getItemCount(): Int = chainsList.size

    override fun onBindViewHolder(holder: ChainViewHolder, position: Int) {
        val item = chainsList[position]
        holder.chainNameTv.text = item.chainName
    }

    class ChainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chainNameTv: TextView = itemView.findViewById(R.id.chain_name_tv)
    }
}
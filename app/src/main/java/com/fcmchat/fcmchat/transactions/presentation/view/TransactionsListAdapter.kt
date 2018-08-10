package com.fcmchat.fcmchat.transactions.presentation.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fcmchat.fcmchat.R
import com.fcmchat.fcmchat.transactions.interactor.TransactionItem

/**
 * Created by Voronin Igor on 10.08.2018.
 */
class TransactionsListAdapter(val transactions: ArrayList<TransactionItem>) : RecyclerView.Adapter<TransactionsListAdapter.TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.transaction_list_item, parent, false)
        return TransactionViewHolder(view)
    }

    override fun getItemCount(): Int = transactions.size

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val item = transactions[position]
        holder.messageTv.text = item.message
        holder.transactionTypeTv.text = item.type

    }

    class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val transactionTypeTv = view.findViewById<TextView>(R.id.transaction_type)!!
        val messageTv = view.findViewById<TextView>(R.id.message)!!
    }
}
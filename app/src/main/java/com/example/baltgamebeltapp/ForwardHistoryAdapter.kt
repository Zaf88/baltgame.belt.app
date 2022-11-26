package com.example.baltgamebeltapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView

class ForwardHistoryAdapter(val dialog: HistoryDialogFragment, val webview: WebView): RecyclerView.Adapter<ForwardHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val webHistoryView = layoutInflater.inflate(R.layout.web_item_history, parent, false)
        return ViewHolder(webHistoryView)
    }

    override fun getItemCount() = webview.copyBackForwardList().size - webview.copyBackForwardList().currentIndex - 1

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val webHistory = webview.copyBackForwardList()
        holder.title.text = webHistory.getItemAtIndex(webHistory.currentIndex + position + 1).title
        holder.favicon.setImageBitmap(webHistory.getItemAtIndex(webHistory.currentIndex + position + 1).favicon)

        holder.itemView.setOnClickListener {
            for (i in webHistory.currentIndex+1 until webHistory.size) {
                if (holder.title.text.equals(webHistory.getItemAtIndex(i).title)) {
                    webview.goBackOrForward(i - webHistory.currentIndex)
                    dialog.dismiss()
                    break
                }
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.title
        val favicon = itemView.favicon
    }
}
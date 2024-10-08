package kz.farad2020.repoloader.ui.dashboard.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.farad2020.repoloader.databinding.LiRepoBinding
import java.io.File

class DownloadsAdapter(
    private val items: MutableList<File>,
    private val listener: OnClickDownloadedRepo
) : RecyclerView.Adapter<DownloadsAdapter.ItemViewHolder>() {

    interface OnClickDownloadedRepo{
        fun onClickRepository(data: File)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LiRepoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return ItemViewHolder(view)
    }

    inner class ItemViewHolder(binding: LiRepoBinding)
        : RecyclerView.ViewHolder(binding.root) {
        val title = binding.titleText
        val subtitle = binding.subtitleText
        val root = binding.root
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.name
        holder.subtitle.text = item.path

        holder.root.setOnClickListener{
            listener.onClickRepository(item)
        }
    }

//    TODO redo this
    fun replaceDownloads(newRepositories: List<File>) {
        val oldSize = items.size
        items.clear()
        notifyItemRangeRemoved(0, oldSize)


        // Add new data and notify about the newly added items
        items.addAll(newRepositories)
        notifyItemRangeInserted(0, newRepositories.size)
    }


    override fun getItemCount(): Int = items.size
}
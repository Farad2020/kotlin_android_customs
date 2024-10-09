package kz.farad2020.repoloader.ui.download.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kz.farad2020.repoloader.databinding.LiDownloadedBinding
import kz.farad2020.repoloader.ui.search.adapters.RepositoriesDiffUtil
import java.io.File

class DownloadsAdapter(
    private var items: MutableList<File>,
    private val listener: OnClickDownloadedRepo
) : RecyclerView.Adapter<DownloadsAdapter.ItemViewHolder>() {

    interface OnClickDownloadedRepo{
        fun onClickRepository(data: File)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LiDownloadedBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return ItemViewHolder(view)
    }

    inner class ItemViewHolder(binding: LiDownloadedBinding)
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
        val diffCallback = DownloadsDiffUtil(items, newRepositories)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items = newRepositories.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }


    override fun getItemCount(): Int = items.size
}
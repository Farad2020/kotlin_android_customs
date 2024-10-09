package kz.farad2020.repoloader.ui.search.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kz.farad2020.domain.model.GitHubRepository
import kz.farad2020.repoloader.databinding.LiRepoBinding
import kz.farad2020.repoloader.ui.base.gone
import kz.farad2020.repoloader.ui.base.invisible
import kz.farad2020.repoloader.ui.base.visible

class RepositoriesAdapter(
    private var items: MutableList<GitHubRepository>,
    private val listener: OnClickRepo
) : RecyclerView.Adapter<RepositoriesAdapter.ItemViewHolder>() {

    interface OnClickRepo{
        fun onDownloadRepository(data: GitHubRepository, onDownloadFinish: () -> Unit)
        fun onOpenBrowser(data: GitHubRepository)
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
        val icWeb = binding.webIcon
        val icDownload = binding.downloadIcon
        val loader = binding.pbCircle
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.name
        holder.subtitle.text = item.description

        holder.icWeb.setOnClickListener{
            listener.onOpenBrowser(item)
        }

        holder.icDownload.setOnClickListener{
            shouldShowDownloadButton(false, holder)

            listener.onDownloadRepository(item){
                shouldShowDownloadButton(true, holder)
            }
        }
    }

    private fun shouldShowDownloadButton(show: Boolean, holder: ItemViewHolder){
        if(show){
            holder.loader.gone()
            holder.icDownload.visible()
        }else{
            holder.loader.visible()
            holder.icDownload.invisible()
        }
    }

    fun replaceRepositories(newRepositories: List<GitHubRepository>) {
        val diffCallback = RepositoriesDiffUtil(items, newRepositories)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        items = newRepositories.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }


    override fun getItemCount(): Int = items.size
}
package kz.farad2020.repoloader.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kz.farad2020.domain.model.GitHubRepository
import kz.farad2020.repoloader.databinding.LiRepoBinding

class RepositoriesAdapter(
    private val items: MutableList<GitHubRepository>
) : RecyclerView.Adapter<RepositoriesAdapter.ItemViewHolder>() {


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
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.name
        holder.subtitle.text = item.description
    }

    fun replaceRepositories(newRepositories: List<GitHubRepository>) {
        val oldSize = items.size
        items.clear()
        notifyItemRangeRemoved(0, oldSize)


        // Add new data and notify about the newly added items
        items.addAll(newRepositories)
        notifyItemRangeInserted(0, newRepositories.size)
    }


    override fun getItemCount(): Int = items.size
}
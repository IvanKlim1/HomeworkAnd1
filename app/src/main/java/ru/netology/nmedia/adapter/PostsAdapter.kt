package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.post.Post
import java.text.DecimalFormat

private val df = DecimalFormat("#.#")
private val dff = DecimalFormat("#")

interface ExtensionForAdapterFunctions{
    fun liked(post:Post){}
    fun reposted(post:Post){}
    fun onEdit(post: Post) {}
    fun onRemove(post: Post) {}
}
class PostDiffCallback: DiffUtil.ItemCallback<Post>(){
override fun areItemsTheSame(oldItem:Post,newItem:Post):Boolean{
    return oldItem.id==newItem.id
}

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem==newItem
    }
}

class PostsAdapter(private val callBack: ExtensionForAdapterFunctions) :
    ListAdapter<Post,PostViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, callBack)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onInteractionListener: ExtensionForAdapterFunctions
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
            )
            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.options_post)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId) {
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }

                            else -> false
                        }
                    }
                }.show()
            }
            likeCount.text = countFormat(post.likes)
            repostCount.text = countFormat(post.reposts)
            like.setOnClickListener {
                onInteractionListener.liked(post)
            }
            repost.setOnClickListener {
                onInteractionListener.reposted(post)
            }
        }
    }

    private fun countFormat(count: Int): String =

    when
    {
        count < 1_000 -> count.toString()
        count == 1_000 || count < 1_100 -> dff.format(count / 1000)+"K"
        count in 1_100..9_999 -> df.format(count / 1000)+"K"
        count in 10_000..999_999 -> dff.format(count / 1000)+"K"
        count in 1000000..1099999 -> dff.format(count / 1000000)+"M"
        count > 1099999 -> df.format(count / 1000000)+"M"
        else -> count.toString()
    }
}
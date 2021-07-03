package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.post.Post
import java.text.DecimalFormat

private val df = DecimalFormat("#.#")
private val dff = DecimalFormat("#")

interface Callback{
    fun liked(post:Post)
    fun reposted(post:Post)
}

class PostsAdapter(private val callBack: Callback) :
    RecyclerView.Adapter<PostViewHolder>() {

    var list = emptyList<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = CardPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding, callBack)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = list[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int = list.size
}

class PostViewHolder(
    private val binding: CardPostBinding,
    private val onLikeListener: Callback
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            like.setImageResource(
                if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
            )
            likeCount.text = countFormat(post.likes)
            repostCount.text = countFormat(post.reposts)
            like.setOnClickListener {
                onLikeListener.liked(post)
            }
            repost.setOnClickListener {
                onLikeListener.reposted(post)
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
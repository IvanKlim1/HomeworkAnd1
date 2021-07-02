package ru.netology.nmedia.activity


import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.PostsAdapter
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel
import java.text.DecimalFormat
class MainActivity : AppCompatActivity() {
    private val df = DecimalFormat("#.#")
    private val dff = DecimalFormat("#")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter {
            viewModel.like(it.id)
            viewModel.repost(it.id)

        }

        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.list = posts
        }
    }

    //class MainActivity : AppCompatActivity() {
    //  private val df = DecimalFormat("#.#")
    // private val dff = DecimalFormat("#")
    //override fun onCreate(savedInstanceState: Bundle?) {
    //   super.onCreate(savedInstanceState)
    //  val binding = ActivityMainBinding.inflate(layoutInflater)
    // setContentView(binding.root)
    //val viewModel: PostViewModel by viewModels()
    // viewModel.data.observe(this) { post ->
    //    with(binding) {
    //         author.text = post.author
    //         published.text = post.published
    //         content.text = post.content
    //         like.setImageResource(
    //             if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
    //        )
    //           likeCount.text = countFormat(post.likes)
    //           repostCount.text = countFormat(post.reposts)
    //      }
    //  }
    //   binding.like.setOnClickListener {
    //       viewModel.like()
    //   }
    //   binding.repost.setOnClickListener {
    //       viewModel.repost()
    //   }
    //  }
    private fun countFormat(count: Int): String =
        when {
            count < 1_000 -> count.toString()
            count == 1_000 || count < 1_100 -> dff.format(count / 1000) + "K"
            count in 1_100..9_999 -> df.format(count / 1000) + "K"
            count in 10_000..999_999 -> dff.format(count / 1000) + "K"
            count in 1000000..1099999 -> dff.format(count / 1000000) + "M"
            count > 1099999 -> df.format(count / 1000000) + "M"
            else -> count.toString()
        }
}






package ru.netology.nmedia.activity

import ru.netology.nmedia.adapter.Callback
import ru.netology.nmedia.post.Post
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
        val adapter = PostsAdapter(object : Callback {
            override fun liked(post: Post) {
                viewModel.like(post.id)
            }
            override fun reposted(post: Post) {
                viewModel.repost(post.id)
            }
        })

        binding.list.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.list = posts
        }
    }
}






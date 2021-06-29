package ru.netology.nmedia

import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this) { post ->
            with(binding) {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                with(like) {
                    setImageResource(
                        if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24

                    )
                }
            }
        }
        binding.like.setOnClickListener {
            viewModel.like()


        }
        binding.repost.setOnClickListener {
            viewModel.repost()
        }
    }
}


//with(binding) {
//author.text = post.author
//published.text = post.published
//content.text = post.content
//if (post.likedByMe) {
//like?.setImageResource(R.drawable.ic_liked_24)
//}
//likeCount?.text = post.likes.toString()

//like?.setOnClickListener {
//post.likedByMe = !post.likedByMe
//like.setImageResource(
//if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
//)
//if (post.likedByMe) post.likes++ else post.likes--
//likeCount?.text = post.likes.toString()
//countFormat(likeCount)
//}
//repost?.setOnClickListener {
//post.repostByMe = !post.repostByMe
//if (post.repostByMe) post.reposts++
//repostCount?.text = post.reposts.toString()
//countFormat(repostCount)
//}
//@RequiresApi(Build.VERSION_CODES.N)
//fun countFormat(count: TextView) {
//    val countFormat = count.text.toString()
  //      .toInt()
    //val df = DecimalFormat("#.#")
    //val dff = DecimalFormat("#")
    //if (countFormat < 1_000) {
     //   println("$countFormat")
    //} else if (countFormat == 1_000 || countFormat < 1_100) {
     //   countFormat / 1000
     //   println(dff.format(countFormat) + "K")
  //  } else if (countFormat in 1100..9999
  //  ) {
  //      countFormat / 1000
  //      println(df.format(countFormat) + "K")
 //   } else if (countFormat in 10000..999999
 //   ) {
 //       countFormat / 1000
 //       println(dff.format(countFormat) + "K")
 //   } else if (countFormat in 1000000..1099999
 //   ) {
 //       countFormat / 1000000
 //       println(dff.format(countFormat) + "M")
 //   } else if (countFormat > 1099999
 //   ) {
 //       countFormat / 1000000
 //       println(df.format(countFormat) + "M")
 //   }
//}
//}

//}




package ru.netology.nmedia

import android.icu.text.DecimalFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import ru.netology.nmedia.databinding.ActivityMainBinding
import java.math.RoundingMode


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false
        )
        with(binding) {
            author.text = post.author
            published.text = post.published
            content.text = post.content
            if (post.likedByMe) {
                like?.setImageResource(R.drawable.ic_liked_24)
            }
            likeCount?.text = post.likes.toString()

            root.setOnClickListener {
                Log.d("stuff", "stuff")
            }

            avatar.setOnClickListener {
                Log.d("stuff", "avatar")
            }

            like?.setOnClickListener {
                Log.d("stuff", "like")
                post.likedByMe = !post.likedByMe
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
                if (post.likedByMe) post.likes++ else post.likes--
                likeCount?.text = post.likes.toString()
                countFormat(likeCount)
            }
            repost?.setOnClickListener {
                Log.d("stuff", "repost")
                post.repostByMe = !post.repostByMe
                if (post.repostByMe) post.reposts++
                repostCount?.text = post.reposts.toString()
                countFormat(repostCount)
            }
        }

    }
@RequiresApi(Build.VERSION_CODES.N)
fun countFormat (count: TextView){
    val countFormat=count.text.toString()
        .toInt()
    val df=DecimalFormat("#.#")
    val dff=DecimalFormat("#")
    if (countFormat < 1_000){
        println("$countFormat")
    }
    else if (countFormat == 1_000 ||countFormat < 1_100){
        countFormat/1000
        println(dff.format(countFormat) + "K")
    } else if (countFormat in 1100..9999
    ){
        countFormat/1000
        println(df.format(countFormat) + "K")
    } else if (countFormat in 10000..999999
    ){
        countFormat/1000
        println(dff.format(countFormat) + "K")
    }
    else if (countFormat in 1000000..1099999
    ){
        countFormat/1000000
        println(dff.format(countFormat) + "M")
    }
    else if (countFormat > 1099999
    ){
        countFormat/1000000
        println(df.format(countFormat) + "M")
    }
}
}
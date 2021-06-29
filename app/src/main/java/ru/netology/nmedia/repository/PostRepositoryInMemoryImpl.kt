package ru.netology.nmedia.repository

import android.icu.text.DecimalFormat
import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.post.Post
import ru.netology.nmedia.PostRepository

class PostRepositoryInMemoryImpl : PostRepository {
    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        published = "21 мая в 18:36",
        likedByMe = false,
        repostByMe = false
    )
    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data
    override fun like() {
        post = post.copy(likedByMe = !post.likedByMe)
        if (post.likedByMe) post.likes++ else post.likes--
        data.value = post
    }
    override fun repost() {
        post = post.copy(repostByMe = !post.repostByMe)
        if (post.repostByMe) post.reposts++
        data.value = post
    }
    @RequiresApi(Build.VERSION_CODES.N)
    fun countFormat(count: TextView) {
        val countFormat = count.text.toString()
            .toInt()
        val df = DecimalFormat("#.#")
        val dff = DecimalFormat("#")
        if (countFormat < 1_000) {
            println("$countFormat")
        } else if (countFormat == 1_000 || countFormat < 1_100) {
            countFormat / 1000
            println(dff.format(countFormat) + "K")
        } else if (countFormat in 1100..9999
        ) {
            countFormat / 1000
            println(df.format(countFormat) + "K")
        } else if (countFormat in 10000..999999
        ) {
            countFormat / 1000
            println(dff.format(countFormat) + "K")
        } else if (countFormat in 1000000..1099999
        ) {
            countFormat / 1000000
            println(dff.format(countFormat) + "M")
        } else if (countFormat > 1099999
        ) {
            countFormat / 1000000
            println(df.format(countFormat) + "M")
        }
    }

}
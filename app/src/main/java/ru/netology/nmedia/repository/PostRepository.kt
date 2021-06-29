package ru.netology.nmedia

import androidx.lifecycle.LiveData
import ru.netology.nmedia.post.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun repost()
}
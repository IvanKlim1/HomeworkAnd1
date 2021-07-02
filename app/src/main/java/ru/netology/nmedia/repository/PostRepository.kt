package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import ru.netology.nmedia.post.Post

interface PostRepository {
    fun get(): LiveData<Post>
    fun like()
    fun repost()
}
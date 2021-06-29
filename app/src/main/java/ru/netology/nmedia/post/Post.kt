package ru.netology.nmedia.post

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int = 0,
    var reposts: Int = 0,
    val likedByMe: Boolean = false,
    val repostByMe: Boolean = false
)
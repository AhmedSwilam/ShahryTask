package com.shahry.com.eg.network.repo

import com.shahry.com.eg.model.AuthorModel
import com.shahry.com.eg.model.PostsModel
import retrofit2.Response



interface RemoteRepository {

    suspend fun getAllAuthors():Response<ArrayList<AuthorModel>>

    suspend fun getPosts(authorId: String): Response<ArrayList<PostsModel>>

}
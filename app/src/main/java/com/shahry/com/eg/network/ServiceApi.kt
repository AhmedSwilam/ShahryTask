package com.shahry.com.eg.network

import com.shahry.com.eg.model.AuthorModel
import com.shahry.com.eg.model.PostsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query



interface ServiceApi {

    @GET("authors")
    suspend fun getAuthorsList(): Response<ArrayList<AuthorModel>>

    @GET("posts/")
    suspend fun getPosts(@Query("authorId") authorId: String): Response<ArrayList<PostsModel>>

}
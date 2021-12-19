package com.shahry.com.eg.network.repo

import com.shahry.com.eg.model.AuthorModel
import com.shahry.com.eg.model.PostsModel
import com.shahry.com.eg.network.ServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


class RemoteRepositoryImp(private val api: ServiceApi) : RemoteRepository {
    override suspend fun getAllAuthors(): Response<ArrayList<AuthorModel>> {
        return withContext(Dispatchers.IO) {
            api.getAuthorsList()
        }
    }

    override suspend fun getPosts(authorId: String): Response<ArrayList<PostsModel>> {
        return withContext(Dispatchers.IO) {
            api.getPosts(authorId)
        }
    }
}
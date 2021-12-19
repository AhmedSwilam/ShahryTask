package com.shahry.com.eg.model

import com.shahry.com.eg.network.repo.RemoteRepository
import retrofit2.Response


class RepositoryImp(private val remoteRepo: RemoteRepository) : Repository {
    override suspend fun getAllAuthors(): Response<ArrayList<AuthorModel>> {
        return remoteRepo.getAllAuthors()
    }

    override suspend fun getPosts(authorId: String): Response<ArrayList<PostsModel>> {
        return remoteRepo.getPosts(authorId)
    }
}
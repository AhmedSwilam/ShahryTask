package com.shahry.com.eg.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shahry.com.eg.model.AuthorModel
import com.shahry.com.eg.model.PostsModel
import com.shahry.com.eg.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AuthorViewModel(private val repository: Repository) : ViewModel() {

//    private var remoteRepositoryImp: RemoteRepositoryImp
//
//    init {
//        val service = RetroBuilder.getRetroInstance().create(ServiceApi::class.java)
//        remoteRepositoryImp = RemoteRepositoryImp(service)
//    }

    private var authorsMutableLiveData = MutableLiveData<ArrayList<AuthorModel>>()
    val authorsLiveDataList: LiveData<ArrayList<AuthorModel>> get() = authorsMutableLiveData

    private var postsMutableLiveData = MutableLiveData<ArrayList<PostsModel>>()
    val postsLiveData: LiveData<ArrayList<PostsModel>> get() = postsMutableLiveData


    fun getAuthorsList() {
        viewModelScope.launch(Dispatchers.IO) {
            var result = repository.getAllAuthors()
            if (result.isSuccessful) {
                if (result.body() != null) {
                    authorsMutableLiveData.postValue(result.body())
                }
            } else {
                Log.i("errMsg", result.message())
            }
        }
    }

    fun getPosts(authorId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var result = repository.getPosts(authorId)
            if (result.isSuccessful) {
                if (result.body() != null) {
                    postsMutableLiveData.postValue(result.body())
                }
            } else {
                Log.i("errMsg", result.message())
            }
        }
    }

}
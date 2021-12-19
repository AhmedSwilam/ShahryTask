package com.shahry.com.eg.di

import com.shahry.com.eg.model.Repository
import com.shahry.com.eg.model.RepositoryImp
import com.shahry.com.eg.network.ServiceApi
import com.shahry.com.eg.network.repo.RemoteRepository
import com.shahry.com.eg.network.repo.RemoteRepositoryImp
import com.shahry.com.eg.viewmodel.AuthorViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val baseUrl = "https://sym-json-server.herokuapp.com/"
val viewModelModule: Module = module {

    viewModel { AuthorViewModel(repository = get()) }

}
val repositoryModule: Module = module {
    single<RemoteRepository> { RemoteRepositoryImp(api = get()) }
    single<Repository> { RepositoryImp(remoteRepo = get()) }

}

val serviceApiModule: Module = module {
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { getRetroInstance() }

    fun getServiceApiInstance(retrofit: Retrofit): ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }
    single { getServiceApiInstance(retrofit = get()) }
}
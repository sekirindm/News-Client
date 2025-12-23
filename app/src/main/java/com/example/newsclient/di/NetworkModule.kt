package com.example.newsclient.di

import com.example.data.api.NetworkModule
import com.example.data.api.NewsApi
import com.example.data.api.NewsApiImpl
import com.example.data.app_params.AppParams
import com.example.newsclient.app_settings.AppParamsImpl
import org.koin.core.scope.get
import org.koin.dsl.module

internal val networkModule = module {

    single {
        NetworkModule.httpClient
    }

    single<NewsApi> {
        NewsApiImpl(
            client = get(),
            apiKey = get<AppParams>().apiKey
        )
    }
}

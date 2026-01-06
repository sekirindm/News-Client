package com.example.newsclient.di

import com.example.repository_api.home.HomeRepository
import com.example.repository_impl.HomeRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<HomeRepository> {
        HomeRepositoryImpl(get(), get(), get())
    }
}
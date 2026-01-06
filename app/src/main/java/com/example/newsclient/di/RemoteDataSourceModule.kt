package com.example.newsclient.di

import com.example.remote_data_source_api.home.HomeRemoteDataSource
import com.example.remote_data_source_impl.home.HomeRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataSourceModule = module {

    single<HomeRemoteDataSource> {
        HomeRemoteDataSourceImpl(get())
    }
}
package com.example.newsclient.di

import com.example.mapper.home.NewsListDataToDomainMapper
import org.koin.dsl.module

val mapperModule = module {

    single {
        NewsListDataToDomainMapper()
    }
}

package com.example.newsclient.di

import com.example.mapper.home.NewsCarouselListDataToDomainMapper
import com.example.mapper.home.NewsMainListDataToDomainMapper
import org.koin.dsl.module

val mapperModule = module {

    single {
        NewsMainListDataToDomainMapper()
    }
    single {
    NewsCarouselListDataToDomainMapper()
    }
}

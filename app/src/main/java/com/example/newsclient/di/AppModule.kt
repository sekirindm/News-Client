package com.example.newsclient.di

import com.example.data.app_params.AppParams
import com.example.newsclient.app_settings.AppParamsImpl
import org.koin.dsl.module

internal val appModule = module {

    single<AppParams> {
        AppParamsImpl()
    }
}
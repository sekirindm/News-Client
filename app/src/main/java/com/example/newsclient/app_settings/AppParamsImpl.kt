package com.example.newsclient.app_settings

import com.example.newsclient.BuildConfig


class AppParamsImpl: com.example.data.app_params.AppParams {
    override val apiKey: String = BuildConfig.API_KEY
}
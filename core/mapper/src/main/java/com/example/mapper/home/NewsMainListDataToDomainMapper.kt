package com.example.mapper.home

import android.os.Bundle
import com.example.data.dto.ArticleResponse
import com.example.model.base.Abstract
import com.example.model.entity.NewsEntity
import com.example.utils.BusinessConstants

class NewsMainListDataToDomainMapper : Abstract.Mapper.DataToDomain<ArticleResponse, NewsEntity> {
    override fun map(
        data: ArticleResponse,
        arg: Bundle
    ) = NewsEntity(
        title = data.title,
        content = data.content,
        author = data.author,
        sourceName = data.source.name,
        imageUrl = data.urlToImage,
        publishedAt = data.publishedAt,
        type = BusinessConstants.NEWS_TYPE_MAIN
    )
}
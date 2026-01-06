package com.example.model.base

class ResponseError @JvmOverloads constructor(
    val info: String,
    val code: Int = 0
) : Exception() {
    override fun toString() = "ResponseException(info='$info', code=$code)"
}
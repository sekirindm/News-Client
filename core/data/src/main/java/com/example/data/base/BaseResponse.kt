package com.example.data.base

import com.example.model.base.Abstract

open class BaseResponse : Abstract.DataObject {
    open val code = 0
    open val info: String = ""
}
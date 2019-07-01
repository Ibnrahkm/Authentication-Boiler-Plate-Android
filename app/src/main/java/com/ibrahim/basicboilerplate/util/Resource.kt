package com.ibrahim.basicboilerplate.util

import com.ibrahim.basicboilerplate.service.model.ErrorModel

class Resource constructor(val status: Resource.Status, val data: Any?, val error: ErrorModel?) {

    enum class Status {
        LOADING, SUCCESS, ERROR
    }

    companion object {
        fun <T> success(data: T?): Resource {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> loading(data: T?): Resource {
            return Resource(Status.LOADING, data, null)
        }


        fun <T> error(error: ErrorModel): Resource {
            return Resource(Status.ERROR, null, error)
        }
    }

}
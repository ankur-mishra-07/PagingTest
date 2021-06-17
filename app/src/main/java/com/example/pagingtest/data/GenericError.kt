package com.example.pagingtest.data


data class GenericError(
    var details: String = "",
    var errorCode: String = "",
    var httpStatus: String = ""
)
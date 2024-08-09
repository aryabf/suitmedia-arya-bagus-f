package com.project.suitmediaaryabagusf.models

import com.google.gson.annotations.SerializedName

data class UserResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("per_page")
    val perPage: Int,

    @field:SerializedName("total")
    val total: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("data")
    val data: MutableList<User>

)

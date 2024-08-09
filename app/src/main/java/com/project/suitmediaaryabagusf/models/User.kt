package com.project.suitmediaaryabagusf.models

import com.google.gson.annotations.SerializedName

data class User(

    @field:SerializedName("first_name")
    val firstName: String,

    @field:SerializedName("last_name")
    val lastName: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("avatar")
    val photo: String
)

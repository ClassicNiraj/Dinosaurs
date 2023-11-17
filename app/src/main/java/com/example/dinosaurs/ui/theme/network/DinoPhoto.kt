package com.example.dinosaurs.ui.theme.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DinoPhoto(
    val id: String,
    @SerialName(value = "img_src")
    val img_src: String
)
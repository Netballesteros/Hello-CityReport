package com.example.dispositivos.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Incidencia(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val fecha: String,
    val prioridad: String,
    val estado: String
) : Parcelable

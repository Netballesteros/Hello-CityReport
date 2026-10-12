package com.example.dispositivos.ui.detalle

import com.example.dispositivos.data.model.Incidencia

interface DetalleIncidenciaContract {
    interface View {
        fun mostrarDetalleIncidencia(incidencia: Incidencia)
    }

    interface Presenter {
        fun cargarDetalle(incidencia: Incidencia?)
    }
}

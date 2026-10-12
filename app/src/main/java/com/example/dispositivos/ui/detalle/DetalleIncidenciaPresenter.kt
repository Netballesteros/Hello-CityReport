package com.example.dispositivos.ui.detalle

import com.example.dispositivos.data.model.Incidencia

class DetalleIncidenciaPresenter(private val view: DetalleIncidenciaContract.View) : DetalleIncidenciaContract.Presenter {
    override fun cargarDetalle(incidencia: Incidencia?) {
        incidencia?.let {
            view.mostrarDetalleIncidencia(it)
        }
    }
}

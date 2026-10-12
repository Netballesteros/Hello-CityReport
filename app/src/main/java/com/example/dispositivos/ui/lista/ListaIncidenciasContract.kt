package com.example.dispositivos.ui.lista

import com.example.dispositivos.data.model.Incidencia

interface ListaIncidenciasContract {
    interface View {
        fun mostrarIncidencias(lista: List<Incidencia>)
        fun mostrarMensaje(mensaje: String)
        fun actualizarEstadoVacio(isEmpty: Boolean)
    }

    interface Presenter {
        fun cargarIncidencias()
        fun agregarIncidencia(titulo: String, descripcion: String, prioridad: String)
    }
}

package com.example.dispositivos.ui.lista

import com.example.dispositivos.data.model.Incidencia
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ListaIncidenciasPresenter(private val view: ListaIncidenciasContract.View) : ListaIncidenciasContract.Presenter {

    private val incidencias = mutableListOf(
        Incidencia(1, "Fallo en el servidor", "El servidor principal no responde a las peticiones.", "20/09/2026", "Alta", "Abierta"),
        Incidencia(2, "Error de login", "Los usuarios no pueden iniciar sesión con Google.", "20/09/2026", "Media", "En Proceso"),
        Incidencia(3, "Actualización de interfaz", "Cambiar el color del botón de envío.", "19/09/2026", "Baja", "Resuelta"),
        Incidencia(4, "Base de datos lenta", "Las consultas tardan más de 5 segundos.", "18/09/2026", "Alta", "En Proceso")
    )

    override fun cargarIncidencias() {
        view.mostrarIncidencias(incidencias)
        view.actualizarEstadoVacio(incidencias.isEmpty())
    }

    override fun agregarIncidencia(titulo: String, descripcion: String, prioridad: String) {
        val cleanTitulo = titulo.trim()
        val cleanDesc = descripcion.trim()

        if (cleanTitulo.isEmpty() || cleanDesc.isEmpty()) {
            view.mostrarMensaje("El título y la descripción son obligatorios")
            return
        }

        val fechaActual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        val newId = if (incidencias.isEmpty()) 1 else incidencias.maxOf { it.id } + 1

        val nuevaIncidencia = Incidencia(
            id = newId,
            titulo = cleanTitulo,
            descripcion = cleanDesc,
            fecha = fechaActual,
            prioridad = prioridad.ifEmpty { "Media" },
            estado = "Abierta"
        )

        incidencias.add(0, nuevaIncidencia)
        view.mostrarIncidencias(incidencias)
        view.actualizarEstadoVacio(false)
        view.mostrarMensaje("Incidencia creada con éxito")
    }
}

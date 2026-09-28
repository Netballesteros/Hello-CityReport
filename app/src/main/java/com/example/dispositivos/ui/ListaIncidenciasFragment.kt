package com.example.dispositivos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dispositivos.R
import com.example.dispositivos.data.model.Incidencia
import com.example.dispositivos.databinding.FragmentListaIncidenciasBinding
import com.example.dispositivos.ui.adapter.IncidenciaAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ListaIncidenciasFragment : Fragment() {

    private var _binding: FragmentListaIncidenciasBinding? = null
    private val binding get() = _binding!!

    private val incidencias = mutableListOf(
        Incidencia(1, "Fallo en el servidor", "El servidor principal no responde a las peticiones.", "20/09/2026", "Alta", "Abierta"),
        Incidencia(2, "Error de login", "Los usuarios no pueden iniciar sesión con Google.", "20/09/2026", "Media", "En Proceso"),
        Incidencia(3, "Actualización de interfaz", "Cambiar el color del botón de envío.", "19/09/2026", "Baja", "Resuelta"),
        Incidencia(4, "Base de datos lenta", "Las consultas tardan más de 5 segundos.", "18/09/2026", "Alta", "En Proceso")
    )

    private lateinit var adapter: IncidenciaAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListaIncidenciasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        binding.fabAgregarIncidencia.setOnClickListener {
            showCrearIncidenciaDialog()
        }
    }

    private fun setupRecyclerView() {
        adapter = IncidenciaAdapter(incidencias) { incidencia ->
            val bundle = bundleOf("incidencia" to incidencia)
            findNavController().navigate(
                R.id.action_listaIncidenciasFragment_to_detalleIncidenciaFragment,
                bundle
            )
        }

        binding.rvIncidencias.layoutManager = LinearLayoutManager(requireContext())
        binding.rvIncidencias.adapter = adapter

        updateEmptyState()
    }

    private fun showCrearIncidenciaDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_crear_incidencia, null)
        val etTitulo = dialogView.findViewById<TextInputEditText>(R.id.etTitulo)
        val etDescripcion = dialogView.findViewById<TextInputEditText>(R.id.etDescripcion)
        val etPrioridad = dialogView.findViewById<TextInputEditText>(R.id.etPrioridad)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Crear Nueva Incidencia")
            .setView(dialogView)
            .setPositiveButton("Crear") { _, _ ->
                val titulo = etTitulo.text.toString().trim()
                val descripcion = etDescripcion.text.toString().trim()
                val prioridad = etPrioridad.text.toString().trim().ifEmpty { "Media" }

                if (titulo.isEmpty() || descripcion.isEmpty()) {
                    Toast.makeText(requireContext(), "El título y la descripción son obligatorios", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }

                val fechaActual = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
                val newId = if (incidencias.isEmpty()) 1 else incidencias.maxOf { it.id } + 1

                val nuevaIncidencia = Incidencia(
                    id = newId,
                    titulo = titulo,
                    descripcion = descripcion,
                    fecha = fechaActual,
                    prioridad = prioridad,
                    estado = "Abierta"
                )

                incidencias.add(0, nuevaIncidencia)
                adapter.notifyItemInserted(0)
                binding.rvIncidencias.scrollToPosition(0)
                updateEmptyState()
                Toast.makeText(requireContext(), "Incidencia creada con éxito", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun updateEmptyState() {
        if (incidencias.isEmpty()) {
            binding.tvEmptyMessage.visibility = View.VISIBLE
            binding.rvIncidencias.visibility = View.GONE
        } else {
            binding.tvEmptyMessage.visibility = View.GONE
            binding.rvIncidencias.visibility = View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

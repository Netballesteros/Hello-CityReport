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
import com.example.dispositivos.ui.lista.ListaIncidenciasContract
import com.example.dispositivos.ui.lista.ListaIncidenciasPresenter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class ListaIncidenciasFragment : Fragment(), ListaIncidenciasContract.View {

    private var _binding: FragmentListaIncidenciasBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: ListaIncidenciasContract.Presenter
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
        presenter = ListaIncidenciasPresenter(this)
        
        setupRecyclerView()

        binding.fabAgregarIncidencia.setOnClickListener {
            showCrearIncidenciaDialog()
        }

        presenter.cargarIncidencias()
    }

    private fun setupRecyclerView() {
        adapter = IncidenciaAdapter(emptyList()) { incidencia ->
            val bundle = bundleOf("incidencia" to incidencia)
            findNavController().navigate(
                R.id.action_listaIncidenciasFragment_to_detalleIncidenciaFragment,
                bundle
            )
        }

        binding.rvIncidencias.layoutManager = LinearLayoutManager(requireContext())
        binding.rvIncidencias.adapter = adapter
    }

    override fun mostrarIncidencias(lista: List<Incidencia>) {
        adapter.actualizarLista(lista)
    }

    override fun mostrarMensaje(mensaje: String) {
        Toast.makeText(requireContext(), mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun actualizarEstadoVacio(isEmpty: Boolean) {
        if (isEmpty) {
            binding.tvEmptyMessage.visibility = View.VISIBLE
            binding.rvIncidencias.visibility = View.GONE
        } else {
            binding.tvEmptyMessage.visibility = View.GONE
            binding.rvIncidencias.visibility = View.VISIBLE
        }
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
                val titulo = etTitulo.text.toString()
                val descripcion = etDescripcion.text.toString()
                val prioridad = etPrioridad.text.toString()

                presenter.agregarIncidencia(titulo, descripcion, prioridad)
                binding.rvIncidencias.scrollToPosition(0)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

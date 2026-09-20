package com.example.dispositivos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dispositivos.R
import com.example.dispositivos.data.model.Incidencia
import com.example.dispositivos.databinding.FragmentListaIncidenciasBinding
import com.example.dispositivos.ui.adapter.IncidenciaAdapter

class ListaIncidenciasFragment : Fragment() {

    private var _binding: FragmentListaIncidenciasBinding? = null
    private val binding get() = _binding!!

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
    }

    private fun setupRecyclerView() {
        val incidencias = listOf(
            Incidencia(1, "Fallo en el servidor", "El servidor principal no responde a las peticiones.", "20/09/2026", "Alta", "Abierta"),
            Incidencia(2, "Error de login", "Los usuarios no pueden iniciar sesión con Google.", "20/09/2026", "Media", "En Proceso"),
            Incidencia(3, "Actualización de interfaz", "Cambiar el color del botón de envío.", "19/09/2026", "Baja", "Resuelta"),
            Incidencia(4, "Base de datos lenta", "Las consultas tardan más de 5 segundos.", "18/09/2026", "Alta", "En Proceso")
        )

        val adapter = IncidenciaAdapter(incidencias) { incidencia ->
            val bundle = bundleOf("incidencia" to incidencia)
            findNavController().navigate(
                R.id.action_listaIncidenciasFragment_to_detalleIncidenciaFragment,
                bundle
            )
        }

        binding.rvIncidencias.layoutManager = LinearLayoutManager(requireContext())
        binding.rvIncidencias.adapter = adapter

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

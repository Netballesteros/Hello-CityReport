package com.example.dispositivos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dispositivos.data.model.Incidencia
import com.example.dispositivos.databinding.FragmentDetalleIncidenciaBinding

class DetalleIncidenciaFragment : Fragment() {

    private var _binding: FragmentDetalleIncidenciaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetalleIncidenciaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val incidencia = arguments?.getParcelable<Incidencia>("incidencia")
        incidencia?.let {
            binding.tvDetalleTitulo.text = it.titulo
            binding.tvDetalleFecha.text = "Fecha: ${it.fecha}"
            binding.tvDetalleDescripcion.text = it.descripcion
            binding.tvDetallePrioridad.text = "Prioridad: ${it.prioridad}"
            binding.tvDetalleEstado.text = "Estado: ${it.estado}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

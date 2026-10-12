package com.example.dispositivos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dispositivos.data.model.Incidencia
import com.example.dispositivos.databinding.FragmentDetalleIncidenciaBinding
import com.example.dispositivos.ui.detalle.DetalleIncidenciaContract
import com.example.dispositivos.ui.detalle.DetalleIncidenciaPresenter

class DetalleIncidenciaFragment : Fragment(), DetalleIncidenciaContract.View {

    private var _binding: FragmentDetalleIncidenciaBinding? = null
    private val binding get() = _binding!!

    private lateinit var presenter: DetalleIncidenciaContract.Presenter

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
        presenter = DetalleIncidenciaPresenter(this)

        val incidencia = arguments?.getParcelable<Incidencia>("incidencia")
        presenter.cargarDetalle(incidencia)
    }

    override fun mostrarDetalleIncidencia(incidencia: Incidencia) {
        binding.tvDetalleTitulo.text = incidencia.titulo
        binding.tvDetalleFecha.text = "Fecha: ${incidencia.fecha}"
        binding.tvDetalleDescripcion.text = incidencia.descripcion
        binding.tvDetallePrioridad.text = "Prioridad: ${incidencia.prioridad}"
        binding.tvDetalleEstado.text = "Estado: ${incidencia.estado}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

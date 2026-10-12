package com.example.dispositivos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dispositivos.R
import com.example.dispositivos.databinding.FragmentEstadisticasBinding

class EstadisticasFragment : Fragment() {

    private var _binding: FragmentEstadisticasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEstadisticasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvTotalIncidencias.text = getString(R.string.total_incidencias)
        binding.tvAbiertas.text = getString(R.string.abiertas_incidencias)
        binding.tvResueltas.text = getString(R.string.resueltas_incidencias)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

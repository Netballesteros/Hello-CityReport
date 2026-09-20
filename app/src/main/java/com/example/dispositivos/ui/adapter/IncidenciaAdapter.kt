package com.example.dispositivos.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dispositivos.data.model.Incidencia
import com.example.dispositivos.databinding.ItemIncidenciaBinding

class IncidenciaAdapter(
    private val incidencias: List<Incidencia>,
    private val onIncidenciaClick: (Incidencia) -> Unit
) : RecyclerView.Adapter<IncidenciaAdapter.IncidenciaViewHolder>() {

    inner class IncidenciaViewHolder(private val binding: ItemIncidenciaBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(incidencia: Incidencia) {
            binding.tvTitulo.text = incidencia.titulo
            binding.tvPrioridad.text = "Prioridad: ${incidencia.prioridad}"
            binding.tvEstado.text = incidencia.estado
            binding.tvFecha.text = incidencia.fecha

            binding.root.setOnClickListener {
                onIncidenciaClick(incidencia)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncidenciaViewHolder {
        val binding = ItemIncidenciaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return IncidenciaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IncidenciaViewHolder, position: Int) {
        holder.bind(incidencias[position])
    }

    override fun getItemCount(): Int = incidencias.size
}

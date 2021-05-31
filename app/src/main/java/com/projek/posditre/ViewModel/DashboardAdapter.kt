package com.projek.posditre.ViewModel

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.projek.posditre.DetailLaporan
import com.projek.posditre.Model.DashboardModel
import com.projek.posditre.databinding.ItemsDashboardBinding

class DashboardAdapter : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {
    private var listDashboard = ArrayList<DashboardModel>()

    fun setCourses(courses: List<DashboardModel>?) {
        if (courses == null) return
        this.listDashboard.clear()
        this.listDashboard.addAll(courses)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemsAcademyBinding = ItemsDashboardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemsAcademyBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = listDashboard[position]
        holder.bind(course)
    }

    override fun getItemCount(): Int = listDashboard.size


    class ViewHolder(private val binding: ItemsDashboardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dashboardModel: DashboardModel) {
            with(binding) {
                tvKeterangan.text = dashboardModel.keterangan
                tvAlamat.text = dashboardModel.alamat_lengkap
                tvNamaLengkap.text = dashboardModel.nama_lengkap
                tvTanggal.text = dashboardModel.tanggal_lapor

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailLaporan::class.java)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}
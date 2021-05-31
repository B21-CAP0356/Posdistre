package com.projek.posditre

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import com.projek.posditre.Utils.DataDummy
import com.projek.posditre.ViewModel.DashboardAdapter
import com.projek.posditre.databinding.FragmentDashboardPenggunaBinding
import java.io.File

class DashboardPenggunaFragment : Fragment() {

    private lateinit var fragmentDashboardPenggunaBinding: FragmentDashboardPenggunaBinding
    private val fusedLocationProviderClient: FusedLocationProviderClient? = null
    private val file: File? = null
    private val fileUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentDashboardPenggunaBinding = FragmentDashboardPenggunaBinding.inflate(
            layoutInflater,
            container,
            false
        )
        return fragmentDashboardPenggunaBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val courses = DataDummy.generate_dashboard()
            val academyAdapter = DashboardAdapter()
            academyAdapter.setCourses(courses)
            with(fragmentDashboardPenggunaBinding.rvBerita) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = academyAdapter
            }
        }

        fragmentDashboardPenggunaBinding.imgFilter.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(it.context)
            val view1 = layoutInflater.inflate(R.layout.popup_filter, null)

            view1.findViewById<View>(R.id.btn_pilih).setOnClickListener { view ->
                val radioGroup = view1.findViewById<RadioGroup>(R.id.radioGroup)
                val radioButton: RadioButton
                val radioId = radioGroup.checkedRadioButtonId
                radioButton = view1.findViewById(radioId)
                if (radioId == -1) {
                    val snackbar = Snackbar.make(view, "Harap pilih filter.", Snackbar.LENGTH_LONG)
                    snackbar.show()
                } else {
                    val pilih = radioButton.text.toString()
                    Toast.makeText(activity, "Anda memilih $pilih", Toast.LENGTH_SHORT).show()
                    bottomSheetDialog.dismiss()
                }
            }

            bottomSheetDialog.setContentView(view1)
            bottomSheetDialog.show()
        }
    }
}
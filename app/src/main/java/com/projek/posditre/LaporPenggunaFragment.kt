package com.projek.posditre

import android.Manifest
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.projek.posditre.databinding.FragmentLaporPenggunaBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class LaporPenggunaFragment : Fragment() {

    private lateinit var fragmentLaporPenggunaBinding: FragmentLaporPenggunaBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var file: File
    private lateinit var fileUri: Uri

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentLaporPenggunaBinding = FragmentLaporPenggunaBinding.inflate(
                layoutInflater,
                container,
                false
        )
        return fragmentLaporPenggunaBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        get_tanggal_lapor()
        get_lokasi_pengguna()

        fragmentLaporPenggunaBinding.imgUpload.setOnClickListener {
            fragmentLaporPenggunaBinding.fotoLapor.visibility = View.VISIBLE
            fragmentLaporPenggunaBinding.tvTambah.visibility = View.INVISIBLE
            fragmentLaporPenggunaBinding.imgUpload.visibility = View.INVISIBLE
            get_izin_kamera()
        }

        fragmentLaporPenggunaBinding.cardView.setOnClickListener {
            fragmentLaporPenggunaBinding.fotoLapor.visibility = View.VISIBLE
            fragmentLaporPenggunaBinding.tvTambah.visibility = View.INVISIBLE
            fragmentLaporPenggunaBinding.imgUpload.visibility = View.INVISIBLE
            get_izin_kamera()
        }
    }

    private fun get_izin_kamera(){
        if (ActivityCompat.checkSelfPermission(
                        activity!!,
                        Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                    activity!!, arrayOf(
                    Manifest.permission.CAMERA
            ), 100
            )
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 100)
        } else {
            ActivityCompat.requestPermissions(
                    activity!!, arrayOf(Manifest.permission.CAMERA), 44
            )
        }
    }

    private fun get_tanggal_lapor() {
        val dateFormat = SimpleDateFormat("dd-MM-yyyy")
        val date = dateFormat.format(Date())
        fragmentLaporPenggunaBinding.tvTanggalLapor.text = date
    }

    private fun get_lokasi_pengguna(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.requireContext())
        if (ActivityCompat.checkSelfPermission(
                        this.requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
        ) {
            getLocation()
        } else {
            ActivityCompat.requestPermissions(
                    this.requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 44
            )
        }
    }

    private fun  getLocation(){
        if (ActivityCompat.checkSelfPermission(
                        this.requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this.requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val pd = ProgressDialog(this.requireContext())
        pd.setMessage("Sedang mengambil alamat...")
        pd.show()

        fusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
            val location : Location
            location = task.result!!
            if (location != null) {
                try {
                    val geocoder = Geocoder(this.requireContext(), Locale.getDefault())
                    val addresses =
                        geocoder.getFromLocation(location.latitude, location.longitude, 1)
                    fragmentLaporPenggunaBinding.tvAlamatLengkap.text = addresses.get(0).getAddressLine(0)
                    pd.dismiss()
                } catch (e: IOException) {
                    e.printStackTrace()
                    pd.dismiss()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 100) {
            val foto = data!!.extras!!["data"] as Bitmap?
            file = File(activity!!.externalCacheDir, System.currentTimeMillis().toString() + ".jpg")
            fileUri = Uri.fromFile(file)
            Log.d("TAG", fileUri.toString())
            fragmentLaporPenggunaBinding.fotoLapor.setImageBitmap(foto)
        }
    }
}
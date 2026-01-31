package com.example.remedialucp2_042.tampilan.view

import android.R.attr.padding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class KategoriFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            padding = 16
            addView(TextView(requireContext()).apply {
                text = "Manajemen Buku - Input Pustakawan"
            })
        }
    }
}
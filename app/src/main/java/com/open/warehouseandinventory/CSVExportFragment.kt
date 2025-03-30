package com.open.warehouseandinventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat

class CSVExportFragment : Fragment() {  //TODO this is a PreferenceFragmentCompat

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_csv_export, container, false)
    }
}
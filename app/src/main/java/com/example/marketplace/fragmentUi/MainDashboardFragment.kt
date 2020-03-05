package com.example.marketplace.fragmentUi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner

import com.example.marketplace.R
import kotlinx.android.synthetic.main.fragment_main_dashboard.*
import kotlinx.android.synthetic.main.fragment_main_dashboard.view.*

/**
 * A simple [Fragment] subclass.
 */
class MainDashboardFragment : Fragment() {

    lateinit var main_view:View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        main_view = inflater.inflate(R.layout.fragment_main_dashboard, container, false)

        val spinner: Spinner = main_view.sp_category
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this.requireContext(),
            R.array.planets_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        return main_view
    }

}

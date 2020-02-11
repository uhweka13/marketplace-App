package com.codeplateau.whatsappclone

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class viewMessageInput : Fragment() {

    companion object {
        fun newInstance() = viewMessageInput()
    }

    private lateinit var viewModel: ViewMessageInputViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.view_message_input_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ViewMessageInputViewModel::class.java)
        // TODO: Use the ViewModel
    }

}

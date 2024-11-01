package com.project.ihearyou.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.project.ihearyou.R



/**
 * A simple [Fragment] subclass.
 * Use the [GettingStartedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GettingStartedFragment : Fragment() {

    private lateinit var getStartedButton:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view= inflater.inflate(R.layout.fragment_getting_started, container, false)
        getStartedButton=view.findViewById(R.id.getStartedButton)


        getStartedButton.setOnClickListener(){
            findNavController().navigate(R.id.action_gettingStartedFragment_to_recordingVoiceFragment)
        }

        return view
    }


}
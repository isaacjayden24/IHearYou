package com.project.ihearyou.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.project.ihearyou.R


/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : Fragment() {

    private lateinit var titleText:TextView
    private lateinit var illustrationImageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_splash, container, false)
        titleText=view.findViewById(R.id.titleText)
        illustrationImageView=view.findViewById(R.id.illustration)


        titleText.setOnClickListener(){
            findNavController().navigate(R.id.action_splashFragment_to_gettingStartedFragment)
        }
        illustrationImageView.setOnClickListener(){
            findNavController().navigate(R.id.action_splashFragment_to_gettingStartedFragment)
        }


        return view
    }


}
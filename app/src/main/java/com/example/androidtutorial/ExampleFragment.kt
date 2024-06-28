package com.example.androidtutorial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView


class ExampleFragment : Fragment() {

    private lateinit var buttonFragment: Button
    private lateinit var textViewFragment: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_example, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         buttonFragment = view.findViewById(R.id.buttonFragment)
         textViewFragment = view.findViewById(R.id.textViewFragment)

        // Set up click listener for button
        buttonFragment.setOnClickListener {
            // Handle button click inside fragment
            textViewFragment.text = "Button clicked!"
        }
    }
}

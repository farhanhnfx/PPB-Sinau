package com.example.ppb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ppb.databinding.FragmentOrderBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrderBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            val ticketArray = resources.getStringArray(R.array.ticket_type)
            val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, ticketArray)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            adapter.insert("Ticket Type...", 0)
            spinnerTicket.adapter = adapter
            btnDone.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

}
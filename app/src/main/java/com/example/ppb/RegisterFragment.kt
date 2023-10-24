package com.example.ppb

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.ppb.databinding.FragmentRegisterBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_USERNAME = "username"
private const val ARG_PASSWORD = "password"
private const val ARG_EMAIL = "email"
private const val ARG_PHONE = "phone"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    // TODO: Rename and change types of parameters
    private var username: String? = null
    private var password: String? = null
    private var email: String? = null
    private var phone: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(ARG_USERNAME)
            password = it.getString(ARG_PASSWORD)
            email = it.getString(ARG_EMAIL)
            phone = it.getString(ARG_PHONE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_register, container, false)
        val main = activity as MainActivity

        view.findViewById<Button>(R.id.btn_register).setOnClickListener {
            val agree = view.findViewById<CheckBox>(R.id.checkbox_agree_tnc)
            if (isFilled()) {
                if (agree.isChecked) {
                    val username = view.findViewById<EditText>(R.id.edit_username).text.toString()
                    val password = view.findViewById<EditText>(R.id.edit_password).text.toString()

                    main.viewPager.setCurrentItem(1)
                    MainActivity.USERNAME = username
                    MainActivity.PASSWORD = password

//                    Toast.makeText(requireContext(), username, Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(requireContext(), "Please agree our Terms and Condition...", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(requireContext(), "Please fill in all columns", Toast.LENGTH_SHORT).show()
            }
        }

        view.findViewById<TextView>(R.id.txt_already_have).setOnClickListener {
            main.viewPager.setCurrentItem(1)
        }
        return view
    }

    private fun isFilled(): Boolean {
        val editUsername = view?.findViewById<EditText>(R.id.edit_username)?.text.toString()
        val editPassword = view?.findViewById<EditText>(R.id.edit_password)?.text.toString()
        val editEmail = view?.findViewById<EditText>(R.id.edit_email)?.text.toString()
        val editPhone = view?.findViewById<EditText>(R.id.edit_phone)?.text.toString()
        if (editUsername != "" && editPassword != "" && editEmail != "" && editPhone != "") return true
        return false
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param username Parameter 1: username.
         * @param password Parameter 2: password.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(username: String, password: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USERNAME, username)
                    putString(ARG_PASSWORD, password)
                }
            }
    }
}
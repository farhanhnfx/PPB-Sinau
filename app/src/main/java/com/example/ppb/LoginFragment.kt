package com.example.ppb

import android.content.Intent
import android.os.Bundle
import android.text.style.TtsSpan.ARG_PASSWORD
import android.text.style.TtsSpan.ARG_USERNAME
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.ppb.databinding.FragmentLoginBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_USERNAME = "username"
//private const val ARG_PASSWORD = "password"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    // TODO: Rename and change types of parameters
    private var username: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(ARG_USERNAME)
            password = it.getString(ARG_PASSWORD)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)
//        view.findViewById<EditText>(R.id.edit_username).setText(MainActivity.USERNAME)
//        Toast.makeText(requireContext(), "logins", Toast.LENGTH_SHORT).show()
        view.findViewById<Button>(R.id.btn_login).setOnClickListener {
            val username = MainActivity.USERNAME
            val password = MainActivity.PASSWORD

            val editUsername = view.findViewById<EditText>(R.id.edit_username).text.toString()
            val editPassword = view.findViewById<EditText>(R.id.edit_password).text.toString()

            if (editUsername == username && editPassword == password) {
                val intent = Intent(requireContext(), DashboardActivity::class.java)
                startActivity(intent)
                Toast.makeText(requireContext(), "Login success", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
            }

        }
        view.findViewById<TextView>(R.id.txt_new_member).setOnClickListener {
            val main = activity as MainActivity
            main.viewPager.setCurrentItem(0)
        }
        return view
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param username Parameter 1.
         * @param password Parameter 2.
         * @return A new instance of fragment LoginFragment.
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
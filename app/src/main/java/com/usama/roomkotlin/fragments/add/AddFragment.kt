package com.usama.roomkotlin.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.usama.roomkotlin.R
import com.usama.roomkotlin.data.User
import com.usama.roomkotlin.data.UserViewModel

class AddFragment : Fragment() {

    private lateinit var addButton: Button
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var age: EditText
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_add, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {

        addButton = view.findViewById(R.id.addButton)
        firstName = view.findViewById(R.id.firstName)
        lastName = view.findViewById(R.id.lastName)
        age = view.findViewById(R.id.age)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        addButton.setOnClickListener {
            insertDataToDatabase()
        }

    }

    private fun insertDataToDatabase() {
        val firstN = firstName.text.toString()
        val lastN = lastName.text.toString()
        val ag = age.text


        if(inputCheck(firstN,lastN,ag)){
            val user = User(0, firstN, lastN, Integer.parseInt(ag.toString()))
            userViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully Added!!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(),"Please fill out all fields",Toast.LENGTH_SHORT).show()
        }


    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())

    }

}
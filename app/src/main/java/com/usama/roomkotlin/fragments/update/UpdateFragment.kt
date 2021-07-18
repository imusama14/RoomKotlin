package com.usama.roomkotlin.fragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.usama.roomkotlin.R
import com.usama.roomkotlin.data.User
import com.usama.roomkotlin.data.UserViewModel


class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var updateFirstName: EditText
    private lateinit var updateLastName: EditText
    private lateinit var updateAge: EditText
    private lateinit var updateButton: Button
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_update, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View) {

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        updateFirstName = view.findViewById(R.id.updateFirstName)
        updateLastName = view.findViewById(R.id.updateLastName)
        updateAge = view.findViewById(R.id.updateAge)
        updateButton = view.findViewById(R.id.updateButton)

        updateFirstName.setText(args.currentUser.firstName)
        updateLastName.setText(args.currentUser.lastName)
        updateAge.setText(args.currentUser.age.toString())

        //Add menu
        setHasOptionsMenu(true)

        updateButton.setOnClickListener {
            updateItem()
        }
    }

    private fun updateItem() {

        val firstN = updateFirstName.text.toString()
        val lastN = updateLastName.text.toString()
        val ag = Integer.parseInt(updateAge.text.toString())

        if (inputCheck(firstN, lastN, updateAge.text)) {
            val updateUser = User(args.currentUser.id, firstN, lastN, ag)
            mUserViewModel.updateUser(updateUser)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            Toast.makeText(requireContext(), "Update Successfully", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Successfully removed: ${args.currentUser.firstName}",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ ->

        }
        builder.setTitle("Delete ${args.currentUser.firstName}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.firstName}?")
        builder.create().show()
    }

}
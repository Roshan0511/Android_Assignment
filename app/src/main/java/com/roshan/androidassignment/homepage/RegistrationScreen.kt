package com.roshan.androidassignment.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.roshan.androidassignment.R
import com.roshan.androidassignment.databinding.FragmentRegistrationScreenBinding
import org.json.JSONObject


class RegistrationScreen : Fragment() {

    private lateinit var binding: FragmentRegistrationScreenBinding
    private var gender: String = "Male"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationScreenBinding.inflate(inflater, container, false)

        binding.tvMale.setOnClickListener {
            binding.tvMale.setBackgroundResource(R.drawable.orange_bg)
            binding.tvFemale.setBackgroundResource(R.drawable.round_color)
            binding.tvOthers.setBackgroundResource(R.drawable.round_color)

            binding.tvMale.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            binding.tvFemale.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
            binding.tvOthers.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
            gender = "Male"
        }

        binding.tvFemale.setOnClickListener {
            binding.tvMale.setBackgroundResource(R.drawable.round_color)
            binding.tvFemale.setBackgroundResource(R.drawable.orange_bg)
            binding.tvOthers.setBackgroundResource(R.drawable.round_color)

            binding.tvMale.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
            binding.tvFemale.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            binding.tvOthers.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
            gender = "Female"
        }

        binding.tvOthers.setOnClickListener {
            binding.tvMale.setBackgroundResource(R.drawable.round_color)
            binding.tvFemale.setBackgroundResource(R.drawable.round_color)
            binding.tvOthers.setBackgroundResource(R.drawable.orange_bg)

            binding.tvMale.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
            binding.tvFemale.setTextColor(ContextCompat.getColor(requireContext(), R.color.orange))
            binding.tvOthers.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            gender = "Others"
        }

        binding.btnContinue.setOnClickListener {
            if (binding.etName.text.isEmpty() || binding.etPhone.text.isEmpty() || binding.etEmail.text.isEmpty() ||
                binding.etAddress.text.isEmpty()){
                Toast.makeText(context, "Fields must not be empty", Toast.LENGTH_SHORT).show()
            } else {
                callingRegistrationApi()
            }
        }

        return binding.root
    }

    private fun callingRegistrationApi(){
        try {
            val requestQueue = Volley.newRequestQueue(context)
            val URL = "https://aspireinfotechs.in/filmdial_app/public/api/auth/register"
            val jsonBody = JSONObject()
            jsonBody.put("department_id", 2)
            jsonBody.put("name", binding.etName.text.trim().toString())
            jsonBody.put("phone", binding.etPhone.text.trim().toString())
            jsonBody.put("gender", gender)
            jsonBody.put("email", binding.etEmail.text.trim().toString())
            jsonBody.put("address", binding.etAddress.text.trim().toString())

            val jsonObject: JsonObjectRequest =
                object : JsonObjectRequest(Method.POST, URL, jsonBody,
                    Response.Listener { response ->
                        run {
                            Toast.makeText(context,
                                "Response:  ${response.getString("status")} -> Registration Success", Toast.LENGTH_SHORT)
                                .show()

                            val fragment = ListScreen()
                            val transaction =  ((context as FragmentActivity).supportFragmentManager).beginTransaction()
                            transaction.replace(R.id.homePageFrame, fragment)
                            transaction.commit()
                        }
                    },
                    Response.ErrorListener { Toast.makeText(context,
                        "Something went wrong!!", Toast.LENGTH_SHORT).show() }) {
                }
            requestQueue.add(jsonObject)

        } catch (e: Exception){
            e.printStackTrace()
        }
    }
}
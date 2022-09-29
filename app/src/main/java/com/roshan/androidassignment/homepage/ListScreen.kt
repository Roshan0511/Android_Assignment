package com.roshan.androidassignment.homepage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.roshan.androidassignment.adapter.DataAdapter
import com.roshan.androidassignment.databinding.FragmentListScreenBinding
import com.roshan.androidassignment.model.DataModel
import org.json.JSONException
import org.json.JSONObject

class ListScreen : Fragment() {

    private lateinit var binding: FragmentListScreenBinding
    private lateinit var list: ArrayList<DataModel>
    private lateinit var mAdapter : DataAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListScreenBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        callListApi()

        mAdapter = DataAdapter(context, list)
        binding.rvData.adapter = mAdapter
        binding.rvData.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    private fun callListApi() {
        list = ArrayList()
        val url = "https://aspireinfotechs.in/filmdial_app/public/api/auth/get_department"

        val queue: RequestQueue = Volley.newRequestQueue(context)

        val request = StringRequest(Request.Method.GET, url,
            { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val array = jsonObject.getJSONArray("data")
                    for (i in 0 until array.length()) {
                        val object1 = array.getJSONObject(i)
                        val id = object1.getString("id")
                        val name = object1.getString("name")
                        list.add(DataModel(id.toInt(), name))
                    }
                    mAdapter.notifyDataSetChanged()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        ) { error -> Log.d("error", error.toString()) }

        queue.add(request)
    }
}
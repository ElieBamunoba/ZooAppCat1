package com.etb.zooappcat1.ui.home

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.ClipDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.etb.zooappcat1.AnimalViewModel
import com.etb.zooappcat1.adapters.AnimalAdapter
import com.etb.zooappcat1.databinding.FragmentHomeBinding
import com.etb.zooappcat1.models.AnimalModel
import org.json.JSONObject

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var animalList: ArrayList<AnimalModel>
    private lateinit var animalsAdapter: AnimalAdapter
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerview = binding.animalListRecyclerView
        val itemDecor = DividerItemDecoration(activity, ClipDrawable.HORIZONTAL)
        recyclerview.addItemDecoration(itemDecor)
        animalsAdapter = AnimalAdapter(requireActivity())
        recyclerview.adapter = animalsAdapter
        //you can use a button for each method
        getAllAnimals()
        createAnimal()
//        deleteAnimal()
//        updateAnimal()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun getAllAnimals() {
        val viewModel: AnimalViewModel =
            ViewModelProvider(this).get(AnimalViewModel::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                animalsAdapter.setAnimalList(it)
                Log.i("data", "" + it.size)
                animalsAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.getAllAnimals()
    }

    private fun createAnimal() {
        val viewModel: AnimalViewModel =
            ViewModelProvider(this).get(AnimalViewModel::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                animalsAdapter.setAnimalList(it)
                animalsAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "Error Creating Animal", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.createAnimal()
    }

    private fun deleteAnimal() {
        val viewModel: AnimalViewModel =
            ViewModelProvider(this).get(AnimalViewModel::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                animalsAdapter.setAnimalList(it)
                animalsAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "Error Deleting Animal", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.deleteAnimal()
    }

    private fun updateAnimal() {
        val viewModel: AnimalViewModel =
            ViewModelProvider(this).get(AnimalViewModel::class.java)
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                animalsAdapter.setAnimalList(it)
                animalsAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "Error Updating Movie", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.updateAnimal()
    }

}



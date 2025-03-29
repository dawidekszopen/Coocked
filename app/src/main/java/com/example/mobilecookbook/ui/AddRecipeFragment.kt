package com.example.mobilecookbook.ui

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import androidx.activity.result.contract.ActivityResultContracts
import com.example.mobilecookbook.MainActivity
import com.example.mobilecookbook.R
import com.example.mobilecookbook.RecipeData
import java.io.File
import java.io.FileOutputStream

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddRecipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRecipeFragment() : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_recipe, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()){ uri: Uri? ->
            uri?.let{
                saveImage(it)
            }
        }

        val button: Button = view.findViewById(R.id.AddRecipeBtn)


        val nazwa: EditText = view.findViewById(R.id.nazwaNR)
        val opis: EditText = view.findViewById(R.id.opisNR)
        val skladniki: EditText = view.findViewById(R.id.skladnikiNR)
        val instrukcja: EditText = view.findViewById(R.id.instrukcjaNR)
        val ocena: RatingBar = view.findViewById(R.id.ocenaNR)

        image = view.findViewById(R.id.imageNR)

        image.setOnClickListener {
            pickImage.launch("image/*")
        }

        button.setOnClickListener {
            val newRecipe = RecipeData(
                nazwa.text.toString(),
                opis.text.toString(),
                skladniki.text.toString(),
                instrukcja.text.toString(),
                ocena.rating,
            )


            (requireActivity() as MainActivity).saveNewRecipe(newRecipe)
        }
    }


    private fun saveImage(imageUri: Uri){
        try {
            val inputStream = requireContext().contentResolver.openInputStream(imageUri)
            val file = File(requireContext().filesDir, "${(requireActivity() as MainActivity).getLenRecipy()}.png")
            val outputstream = FileOutputStream(file)

            inputStream?.copyTo(outputstream)
            inputStream?.close()
            outputstream.close()
            image.setImageBitmap((requireActivity() as MainActivity).loadSavedImage((requireActivity() as MainActivity).getLenRecipy()))
        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddRecipeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddRecipeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
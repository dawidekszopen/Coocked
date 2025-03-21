package com.example.mobilecookbook.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import com.example.mobilecookbook.MainActivity
import com.example.mobilecookbook.R
import com.example.mobilecookbook.RecipeData

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [RecipeDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipeDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_recipe_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val przepis: RecipeData = (requireActivity() as MainActivity).getRecipe(arguments?.getInt("nrPrzepisu"))

        val nazwa: TextView = view.findViewById(R.id.nazwaR)
        val opis: TextView = view.findViewById(R.id.opisR)
        val skladniki: TextView = view.findViewById(R.id.skladnikiR)
        val intrukcja: TextView = view.findViewById(R.id.instrukcjeR)
        val ocena: RatingBar = view.findViewById(R.id.ocenaR)

        nazwa.text = przepis.nazwa
        opis.text = przepis.opis
        skladniki.text = przepis.skladniki
        intrukcja.text = przepis.intrukcja
        ocena.rating = przepis.ocena
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecipeDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String,
                        nazwa: String,
                        opis: String,
                        skladniki: String,
                        intrukcja: String,
                        ocena: Float,) =
            RecipeDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
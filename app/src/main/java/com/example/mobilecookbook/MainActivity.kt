package com.example.mobilecookbook


import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.mobilecookbook.ui.AddRecipeFragment
import com.example.mobilecookbook.ui.RecipeListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var MAINSP: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        MAINSP = getSharedPreferences("init", MODE_PRIVATE)


        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFrameLayout, RecipeListFragment())
            .commit()

        val DodajPrzepis: Button = findViewById(R.id.DodajPrzepis)

        DodajPrzepis.setOnClickListener {
            replaceFragment(AddRecipeFragment())
        }
    }

    fun replaceFragment(fragment: Fragment, withArg: Boolean = false, nrPrzepisu: Int? = 0){
        if(withArg){
            val newFragment = fragment.apply {
              arguments = Bundle().apply {
                  if (nrPrzepisu != null) {
                      putInt("nrPrzepisu", nrPrzepisu)
                  }
              }
            }

            supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, newFragment)
                .addToBackStack(null).commit()
        }
        else{
            supportFragmentManager.beginTransaction().replace(R.id.mainFrameLayout, fragment)
                .addToBackStack(null).commit()
        }
    }


    fun saveNewRecipe(Przepis: RecipeData){
        Log.i("test", "dizała")

        val recipeSP = getSharedPreferences(MAINSP.getInt("recipeLen", 0).toString(), MODE_PRIVATE)

        val editorRecipe = recipeSP.edit()
        editorRecipe.putString("nazwa", Przepis.nazwa)
        editorRecipe.putString("opis", Przepis.opis)
        editorRecipe.putString("skladniki", Przepis.skladniki)
        editorRecipe.putString("instrukcje", Przepis.intrukcja)
        editorRecipe.putFloat("ocena", Przepis.ocena)
        editorRecipe.apply()

        val editorMAINSP = MAINSP.edit()
        editorMAINSP.putInt("recipeLen", MAINSP.getInt("recipeLen", 0)+1)
        editorMAINSP.apply()

        replaceFragment(RecipeListFragment())
    }

    fun getRecipe(nrPrzepisu: Int?): RecipeData {
        val recipeSP = getSharedPreferences(nrPrzepisu.toString(), MODE_PRIVATE)

        return RecipeData(
            recipeSP.getString("nazwa", "ups coś poszło nie tak")!!,
            recipeSP.getString("opis", "ups coś poszło nie tak")!!,
            recipeSP.getString("skladniki", "ups coś poszło nie tak")!!,
            recipeSP.getString("instrukcje", "ups coś poszło nie tak")!!,
            recipeSP.getFloat("ocena", 0f)
        )
    }

    fun getLenRecipy(): Int = MAINSP.getInt("recipeLen", 0)

    fun edidRecipe(Przepis: RecipeData, id: Int?){

        val recipeSP = getSharedPreferences(id.toString(), MODE_PRIVATE)

        val editorRecipe = recipeSP.edit()
        editorRecipe.putString("nazwa", Przepis.nazwa)
        editorRecipe.putString("opis", Przepis.opis)
        editorRecipe.putString("skladniki", Przepis.skladniki)
        editorRecipe.putString("instrukcje", Przepis.intrukcja)
        editorRecipe.putFloat("ocena", Przepis.ocena)
        editorRecipe.apply()
    }
}
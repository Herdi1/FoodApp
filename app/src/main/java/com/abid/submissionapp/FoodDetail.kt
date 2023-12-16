package com.abid.submissionapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.abid.submissionapp.databinding.ActivityFoodDetailBinding

class FoodDetail : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityFoodDetailBinding

    companion object{
        const val EXTRA_FOOD = "extra_food"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val foods = if(Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra<Food>(EXTRA_FOOD, Food::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Food>(EXTRA_FOOD)
        }

        binding.detailImageItem.setImageResource(foods!!.img)
        binding.detailTitleItem.text = foods.name
        binding.detailDescriptionItem.text = foods.description
        binding.detailPlaceItem.text = foods.place
        binding.detailIngredientItem.text = foods.ingredient

        binding.actionShare.setOnClickListener (this)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onClick(v: View?) {
        val foods = if(Build.VERSION.SDK_INT >= 33){
            intent.getParcelableExtra<Food>(EXTRA_FOOD, Food::class.java)
        }else{
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Food>(EXTRA_FOOD)
        }
        val shareText = foods?.name
        val shareIntent: Intent = Intent().apply{
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "plain/Text"
        }
        startActivity(Intent.createChooser(shareIntent, "Berbagi melalui "))
    }
}

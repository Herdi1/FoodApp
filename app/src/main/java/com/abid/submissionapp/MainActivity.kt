package com.abid.submissionapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.abid.submissionapp.databinding.ActivityMainBinding
import androidx.recyclerview.widget.RecyclerView
import com.abid.submissionapp.FoodDetail.Companion.EXTRA_FOOD

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var rvFood: RecyclerView
    private val list = ArrayList<Food>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvFood = binding.rvFood
        rvFood.setHasFixedSize(true)

        list.addAll(getListFood())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showRecyclerList() {
        rvFood.layoutManager = LinearLayoutManager(this)
        val listFoodAdapter = ListFoodAdapter(list)
        rvFood.adapter = listFoodAdapter

        listFoodAdapter.setOnItemClickCallback(object : ListFoodAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Food) {
                selectedFood(data)
            }
        })
    }

    private fun getListFood(): Collection<Food> {
        val dataName = resources.getStringArray(R.array.data_title)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataImg = resources.obtainTypedArray(R.array.data_img)
        val dataPlace = resources.getStringArray(R.array.data_place)
        val dataIngredient = resources.getStringArray(R.array.data_ingredient)
        val listFood = ArrayList<Food>()
        for(i in dataName.indices){
            val food = Food(dataName[i], dataDescription[i], dataImg.getResourceId(i, 0), dataPlace[i], dataIngredient[i])
            listFood.add(food)
        }
        return listFood
    }

    private fun selectedFood(food: Food){
        val moveToDetail = Intent(this@MainActivity, FoodDetail::class.java)
        moveToDetail.putExtra(EXTRA_FOOD, food)
        startActivity(moveToDetail)
    }
}

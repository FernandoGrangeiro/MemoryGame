package com.fgran.memorygame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class GameActivity : AppCompatActivity(), ItemInterface {


    var lastIdClicked = ""
    var userModel = UserModel(5)
    var dificulty = 0
    val recyclerAdapter = ItemCardAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        dificulty = intent.getIntExtra("dificulty", 1)
        setupGame()

    }

    private fun setupGame() {
        userModel.life = when (dificulty) {
            1 -> 5
            2 -> 4
            else -> 3
        }
        setLifeText(userModel.life.toString())
        setDificultyText()
        setAdapter()
    }

    private fun setLifeText(life: String) {
        lifeText.text = life
    }

    private fun setDificultyText() {
        dificultyText.text = when (dificulty) {
            1 -> "Dificuldade: Fácil"
            2 -> "Dificuldade: Médio"
            else -> "Dificuldade: Difícil"
        }
    }

    private fun getArrayList(): ArrayList<ItemModel> {
        return when (dificulty) {
            1 -> arrayListOf(ItemModel("1", R.drawable.stark), ItemModel("5", R.drawable.bolton)
                    , ItemModel("4", R.drawable.martell), ItemModel("2", R.drawable.targaryen)
                    , ItemModel("3", R.drawable.lannister), ItemModel("1", R.drawable.stark)
                    , ItemModel("6", R.drawable.baratheon), ItemModel("5", R.drawable.bolton)
                    , ItemModel("2", R.drawable.targaryen), ItemModel("3", R.drawable.lannister)
                    , ItemModel("4", R.drawable.martell), ItemModel("6", R.drawable.baratheon))
            2 -> arrayListOf(
                    ItemModel("1", R.drawable.stark), ItemModel("2", R.drawable.targaryen)
                    , ItemModel("4", R.drawable.martell), ItemModel("6", R.drawable.baratheon)
                    , ItemModel("5", R.drawable.bolton), ItemModel("1", R.drawable.stark)
                    , ItemModel("2", R.drawable.targaryen), ItemModel("3", R.drawable.lannister)
                    , ItemModel("5", R.drawable.bolton), ItemModel("4", R.drawable.martell)
                    , ItemModel("6", R.drawable.baratheon), ItemModel("3", R.drawable.lannister))
            else -> {
                arrayListOf(
                        ItemModel("1", R.drawable.stark), ItemModel("2", R.drawable.targaryen)
                        , ItemModel("4", R.drawable.martell), ItemModel("6", R.drawable.baratheon)
                        , ItemModel("5", R.drawable.bolton), ItemModel("1", R.drawable.stark)
                        , ItemModel("2", R.drawable.targaryen), ItemModel("3", R.drawable.lannister)
                        , ItemModel("5", R.drawable.bolton), ItemModel("4", R.drawable.martell)
                        , ItemModel("6", R.drawable.baratheon), ItemModel("3", R.drawable.lannister))
            }
        }
    }

    private fun setAdapter() {
        val layoutManager = GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false)
        lista?.run {
            this.layoutManager = layoutManager
            adapter = recyclerAdapter.apply { add(embaralharArray()) }
            clearOnScrollListeners()
            stopNestedScroll()
        }
    }

    private fun embaralharArray(): ArrayList<ItemModel> {
        val array = getArrayList()
        val random = Random()
        for (i in array.indices) {
            val j = random.nextInt(array.size)
            val x = array[i]
            array[i] = array[j]
            array[j] = x
        }
        return array
    }


    override fun onClicked(id: String): Boolean {
        return if (lastIdClicked == id) {
            true
        } else {
            verifyUserLife()
            false
        }
    }

    private fun verifyUserLife() {
        userModel.life--
        setLifeText(userModel.life.toString())
        if (userModel.life == 0) {
            startActivity(Intent(this, EndGameActivity::class.java)
                    .apply { putExtra("victory", false) })
            finish()
        }
    }

    override fun setIdClicked(id: String) {
        lastIdClicked = id
    }

    override fun endGame(isEnd: Boolean) {
        if (isEnd) {
            finish()
            startActivity(Intent(this, EndGameActivity::class.java)
                    .apply { putExtra("victory", true) })
        }
    }

}

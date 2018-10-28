package com.fgran.memorygame

interface ItemInterface {
    fun onClicked(id:String):Boolean
    fun setIdClicked(id: String)
    fun endGame(isEnd: Boolean)
}
package com.bignerdranch.nyethack

open class Room(val name: String) {
    protected open  val dangerlevel=5
    var monster: Monster?=Goblin()
    fun description()= "Room: $name\nDanger level:$dangerlevel\nCreature:${monster?.description?:"none"}"
   open fun load()="Nothing much to se here..."
}
class TownSquare: Room("Town Square"){
    override val dangerlevel = super.dangerlevel-3
    private var bellsound="GWONG"

    final override fun load()="The villager rally and cheer as you enter!\n${ringbell()}"
    private fun ringbell()="The bell tower announces ypur arrival. $bellsound"
}
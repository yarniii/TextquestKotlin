package com.bignerdranch.nyethack
import com.bignerdranch.nyethack.extensions.random as randomizer

import java.io.File

class Player(_name: String, override var healthPoints: Int=100, val isBlessed:Boolean, private val isImmortal:Boolean):Fightable {
    var name = _name

        get() = "${field.capitalize()} of $homeTown"
        private set(value) {
            field = value.trim()
        }
    val homeTown by lazy {  selectHomeTown()}
    var currentPosition=Coordinate(0,0)
    init{
        require(healthPoints>0,{"healthpoints must be greater thna zero"})
        require(name.isNotBlank(),{"Player mist have a name"})
    }
      constructor(name: String) : this(name,
              
              isBlessed = true,
              isImmortal = false){
          if(name.toLowerCase()=="kar") healthPoints=40
      }


  fun auraColor(): String {
        val auraVisible = isBlessed && healthPoints > 50 || isImmortal
        val auraColor = if (auraVisible) "GREEN" else "NONE"
        return auraColor
    }
   fun formatHealthStatus() =
            when (healthPoints) {
                100 -> "is in excellent condition!"
                in 90..99 -> "has a few scratches."
                in 75..89 -> if (isBlessed) {
                    "has some minor wounds, but is healing quite quickly!"
                } else {
                    "has some minor wounds."
                }
                in 15..74 -> "looks pretty hurt."
                else -> "is in awful condition!"
            }
    fun castFireball(numFireballs: Int = 2) =
            println("A glass of Fireball springs into existence. (x$numFireballs)")
private fun selectHomeTown()=File("data/towns.txt")
        .readText()
        .split("\n")
    .randomizer()

    override val diceCount: Int=3

    override val diceSides: Int=6



    override fun attack(opponent: Player): Int {
      val damageDealt= if (isBlessed) {
          damageRoll * 2
      } else {
          damageRoll
      }
        opponent.healthPoints-=damageDealt
        return damageDealt
      }
    }




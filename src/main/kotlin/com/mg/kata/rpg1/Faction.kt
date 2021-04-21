package com.mg.kata.rpg1

class Faction {

    private val members = mutableListOf<Character>()

    fun join(character: Character) {
        character.factions.add(this)
        members.add(character)
    }

    fun leave(character: Character) {
        character.factions.remove(this)
        members.remove(character)
    }

    fun getAllies() = members.toList()
}
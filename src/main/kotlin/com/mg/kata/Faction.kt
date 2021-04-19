package com.mg.kata

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
package com.mg.kata

class Faction {

    private val members = mutableListOf<Character>()

    fun join(character: Character) {
        members.add(character)
    }

    fun getMembers() = members.toList()
}
package com.mg.kata

class Faction {

    private val members = mutableListOf<Character>()

    fun join(character: Character) {
        character.addFaction(this)
        members.add(character)
    }

    fun getMembers() = members.toList()
}

private fun Character.addFaction(faction: Faction) {
    factions.add(faction)
}
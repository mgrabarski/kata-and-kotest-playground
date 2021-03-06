package com.mg.kata.rpg1

import com.mg.kata.rpg1.Character
import com.mg.kata.rpg1.Faction
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldNotContain

class FactionTest : FunSpec({

    test("Character can join the faction") {
        val character = Character()
        val faction = Faction()

        faction.join(character)

        faction.getAllies() shouldContain character
    }

    test("Character should contain faction to which it belongs") {
        val character = Character()
        val faction = Faction()

        faction.join(character)

        character.getFactions() shouldContain faction
    }

    test("Character can leave the faction") {
        val character = Character()
        val faction = Faction()
        faction.join(character)

        faction.leave(character)

        faction.getAllies() shouldNotContain character
    }

    test("Character after leave the faction should not belong to them") {
        val character = Character()
        val faction = Faction()
        faction.join(character)

        faction.leave(character)

        character.getFactions() shouldNotContain faction
    }

    test("Character belong to more then one faction") {
        val character = Character()
        val faction1 = Faction()
        val faction2 = Faction()

        faction1.join(character)
        faction2.join(character)

        character.getFactions().shouldContainAll(faction1, faction2)
    }
})

package com.mg.kata

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain

class FactionTest : FunSpec({

    test("Character can join the faction") {
        val character = Character()
        val faction = Faction()

        faction.join(character)

        faction.getMembers().shouldContain(character)
    }

    test("Character should contain faction to which it belongs") {
        val character = Character()
        val faction = Faction()

        faction.join(character)

        character.getFactions().shouldContain(faction)
    }

    test("Character can leave the faction") {
        val character = Character()
        val faction = Faction()
        faction.join(character)

        faction.leave(character)

        faction.getMembers().shouldNotContain(character)
    }

    test("Character after leave the faction should not belong to them") {
        val character = Character()
        val faction = Faction()
        faction.join(character)

        faction.leave(character)

        character.getFactions().shouldNotContain(faction)
    }
})

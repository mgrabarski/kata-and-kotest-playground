package com.mg.kata

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContain

class FactionTest : FunSpec({

    test("Character can join the faction") {
        val character = Character()
        val faction = Faction()

        faction.join(character)

        faction.getMembers().shouldContain(character)
    }
})

package com.mg.kata

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CharacterTest : FunSpec({

    test("character has health 1000, level 1 and is alive") {
        val newCharacter = Character()
        newCharacter.health.value shouldBe Health().value
        newCharacter.level.value shouldBe Level().value
        newCharacter.alive shouldBe true
    }
})
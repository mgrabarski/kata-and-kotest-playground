package com.mg.kata

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlin.test.assertEquals

class CharacterTest : FunSpec({

    test("character has health 1000, level 1 and is alive") {
        val newCharacter = Character()
        newCharacter.health shouldBeSameAs Health()
        newCharacter.level.value shouldBe Level().value
        newCharacter.alive shouldBe true
    }

    test("character can deal damage to character. Damage is subtracted from healt") {
        val attacker = Character()
        val enemy = Character()

        attacker.dealDamage(enemy)

        enemy.health shouldBeSameAs Health().subtract(Health(attacker.attack.value))
    }
})

private infix fun Health.shouldBeSameAs(health: Health) {
    assertEquals(health.value, this.value)
}

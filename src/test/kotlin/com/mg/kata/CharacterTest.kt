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

    test("given damage bigger then healt then character should be death") {
        val attacker = Character(_attack = Attack(1000))
        val enemy = Character(Health(100))

        attacker.dealDamage(enemy)

        enemy.alive shouldBe false
    }

    test("when health is zero then character is death") {
        val attacker = Character(_attack = Attack(100))
        val enemy = Character(Health(100))

        attacker.dealDamage(enemy)

        enemy.alive shouldBe false
    }

    test("character can heal other character") {
        val healer = Character(_heal = Heal(100))
        val injured = Character(Health(100))

        healer.heal(injured)

        injured.health shouldBeSameAs Health(200)
    }
})

private infix fun Health.shouldBeSameAs(health: Health) {
    assertEquals(health.value, this.value)
}

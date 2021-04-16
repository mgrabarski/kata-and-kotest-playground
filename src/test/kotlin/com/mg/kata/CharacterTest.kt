package com.mg.kata

import com.mg.kata.AttackType.Melee
import com.mg.kata.AttackType.Rangea
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlin.test.assertEquals

class CharacterTest : FunSpec({

    test("character has health MAX_HEALTH, level 1 and is alive") {
        val newCharacter = Character()
        newCharacter.health shouldBeSameAs Health()
        newCharacter.level.value shouldBe Level().value
        newCharacter.alive shouldBe true
    }

    test("character can deal damage to character. Damage is subtracted from healt") {
        val attacker = Character()
        val enemy = Character()

        attacker dealDamage enemy

        enemy.health shouldBeSameAs Health().subtract(Health(attacker.attack.value))
    }

    test("given damage bigger then healt then character should be death") {
        val attacker = Character(_attack = Attack(Melee, 1000))
        val enemy = Character(Health(100))

        attacker dealDamage enemy

        enemy.alive shouldBe false
    }

    test("when health is zero then character is death") {
        val attacker = Character(_attack = Attack(Melee, 100))
        val enemy = Character(Health(100))

        attacker dealDamage enemy

        enemy.alive shouldBe false
    }

    test("Death character can not be healed") {
        val healer = Character()
        val death = Character(Health(0))

        healer heal death

        death.health shouldBeSameAs Health(0)
        death.alive shouldBe false
    }

    test("Character can not deal damage to itself") {
        val character = Character()

        character dealDamage character

        character.health shouldBeSameAs Health()
    }

    test("Character heal only itself") {
        val character = Character(Health(100))

        character heal character

        character.health shouldBeSameAs Health(100 + character.heal.value)
    }

    test("Heal other character not changing heal of this character") {
        val healer = Character()
        val injured = Character(Health(100))

        healer heal injured

        injured.health shouldBeSameAs Health(100)
    }

    test("Deal damage: if the target is 5 levels above the attacker - damage is reduce by 50%") {
        val attack = Attack(Melee, 100)
        val attacker = Character(_level = Level(1), _attack = attack)
        val target = Character(_level = Level(6), _attack = attack)

        attacker dealDamage target

        target.health shouldBeSameAs Health(950)
    }

    test("Deal damage: if the target is more then 5 levels above the attacker - damage is reduce by 50%") {
        val attack = Attack(Melee, 100)
        val attacker = Character(_level = Level(1), _attack = attack)
        val target = Character(_level = Level(10), _attack = attack)

        attacker dealDamage target

        target.health shouldBeSameAs Health(950)
    }

    test("Deal damage: if the target is 5 levels velow the attacked - damage is increased by 50%") {
        val attack = Attack(Melee, 100)
        val attacker = Character(_level = Level(6), _attack = attack)
        val target = Character(_level = Level(1), _attack = attack)

        attacker dealDamage target

        target.health shouldBeSameAs Health(850)
    }

    test("Deal damage: if the target is more then 5 levels velow the attacked - damage is increased by 50%") {
        val attack = Attack(Melee, 100)
        val attacker = Character(_level = Level(10), _attack = attack)
        val target = Character(_level = Level(1), _attack = attack)

        attacker dealDamage target

        target.health shouldBeSameAs Health(850)
    }

    test("Character has max range attack") {
        Character(_attack = Attack(Melee)).attack.range shouldBe AttackType.Melee.range
        Character(_attack = Attack(Rangea)).attack.range shouldBe AttackType.Rangea.range
    }
})

infix fun Health.shouldBeSameAs(health: Health) {
    assertEquals(health.value, this.value)
}

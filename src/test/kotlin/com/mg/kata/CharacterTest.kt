package com.mg.kata

import com.mg.kata.AttackType.Melee
import com.mg.kata.AttackType.Rangea
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeEmpty
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

    test("Characker must be in range of melee attack to deal damage to a target") {
        val attacker = Character(_position = Position(0, 0), _attack = Attack(Melee, 100))
        val target = Character(_position = Position(1, 1), _attack = Attack(Melee, 100))

        attacker dealDamage target

        target.health shouldBeSameAs Health(900)
    }

    test("Characker must be in range of rangea attack to deal damage to a target") {
        val attacker = Character(_position = Position(0, 0), _attack = Attack(Rangea, 100))
        val target = Character(_position = Position(10, 10), _attack = Attack(Melee, 100))

        attacker dealDamage target

        target.health shouldBeSameAs Health(900)
    }

    test("Target character is out of the melee attack range. Damage should not be deal") {
        val attacker = Character(_position = Position(0, 0), _attack = Attack(Melee, 100))
        val target = Character(_position = Position(10, 10), _attack = Attack(Melee, 100))

        attacker dealDamage target

        target.health shouldBeSameAs Health()
    }

    test("Target character is out of the rangea attack range. Damage should not be deal") {
        val attacker = Character(_position = Position(0, 0), _attack = Attack(Rangea, 100))
        val target = Character(_position = Position(25, 25), _attack = Attack(Melee, 100))

        attacker dealDamage target

        target.health shouldBeSameAs Health()
    }

    test("Newly created character belong to no faction") {
        val chatacter = Character()

        val characterFactions = chatacter.getFactions()

        characterFactions.shouldBeEmpty()
    }

    test("Characters can not deal damage to character from the same faction") {
        val attacker = Character()
        val target = Character()
        val faction = Faction()
        faction.join(attacker)
        faction.join(target)

        attacker dealDamage target

        target.health shouldBeSameAs Health()
    }

    test("Character can heal allies from faction") {
        val healer = Character()
        val target = Character(_health = Health(900))
        val faction = Faction()
        faction.join(healer)
        faction.join(target)

        healer heal target

        target.health shouldBeSameAs Health(1000)
    }
})

infix fun Health.shouldBeSameAs(health: Health) {
    assertEquals(health.value, this.value)
}

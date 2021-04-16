package com.mg.kata

import com.mg.kata.AttackType.Melee

const val MAX_HEALTH = 1000

class Character(
    private val _health: Health = Health(),
    private val _level: Level = Level(),
    private val _attack: Attack = Attack(Melee),
    private val _heal: Heal = Heal(),
    private val _position: Position = Position()
) {

    val health
        get() = _health

    val alive: Boolean
        get() = health.isAlive()

    val level
        get() = _level

    val attack
        get() = _attack

    val heal
        get() = _heal

    val position
        get() = _position

    infix fun dealDamage(enemy: Character) {
        if (enemy === this) {
            return
        }
        val attackExtraDamage: Int = calculateLevelDamageBouns(enemy)
        enemy.health.subtract(Health(attack.value + attackExtraDamage))
    }

    private fun calculateLevelDamageBouns(enemy: Character) = if (enemy.level.value >= this.level.value + 5) {
        -(attack.value * 0.5).toInt()
    } else if (enemy.level.value <= this.level.value - 5) {
        (attack.value * 0.5).toInt()
    } else {
        0
    }

    infix fun heal(injured: Character) {
        if (injured.alive) {
            if (injured === this) {
                injured.health.add(Health(heal.value))
            }
        }
    }
}

class Health(
    private var _value: Int = MAX_HEALTH
) {
    val value
        get() = _value

    fun isAlive() = _value > 0

    fun subtract(health: Health): Health {
        _value -= health.value
        return this
    }

    fun add(health: Health): Health {
        _value += health.value
        if (_value > MAX_HEALTH) {
            _value = MAX_HEALTH
        }
        return this
    }
}

class Level(private val _value: Int = 1) {
    val value
        get() = _value
}


enum class AttackType(val range: Int) {
    Melee(2),
    Rangea(10)
}

class Attack(
    val type: AttackType,
    private var _value: Int = 100
) {
    val value
        get() = _value

    val range = type.range
}

class Heal(
    private var _value: Int = 100
) {
    val value
        get() = _value
}

class Position(
    private var _x: Int = 0,
    private var _y: Int = 0
) {
    val x
        get() = _x

    val y
        get() = _y
}
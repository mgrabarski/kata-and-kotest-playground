package com.mg.kata

const val MAX_HEALTH = 1000

class Character(
    private val _health: Health = Health(),
    private val _level: Level = Level(),
    private val _attack: Attack = Attack(),
    private val _heal: Heal = Heal()
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

    infix fun dealDamage(enemy: Character) {
        if (enemy === this) {
            return
        }
        enemy.health.subtract(Health(attack.value))
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

class Attack(
    private var _value: Int = 100
) {
    val value
        get() = _value
}

class Heal(
    private var _value: Int = 100
) {
    val value
        get() = _value
}
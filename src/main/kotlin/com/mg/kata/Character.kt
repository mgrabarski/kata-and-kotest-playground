package com.mg.kata

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

    fun dealDamage(enemy: Character) {
        enemy.health.subtract(Health(attack.value))
    }

    fun heal(injured: Character) {
    }
}

class Health(
    private var _value: Int = 1000
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
        return this
    }
}

class Level {

    private var _value = 1
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
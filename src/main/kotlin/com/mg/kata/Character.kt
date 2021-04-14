package com.mg.kata

class Character(
    private val _health: Health = Health(),
    private val _level: Level = Level(),
    private val _attack: Attack = Attack()
) {

    val health
        get() = _health

    val alive: Boolean
        get() = health.isAlive()

    val level
        get() = _level

    val attack
        get() = _attack

    fun dealDamage(enemy: Character) {
        enemy.health.subtract(Health(attack.value))
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
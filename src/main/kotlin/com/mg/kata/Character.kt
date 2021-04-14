package com.mg.kata

class Character {

    var health = Health()
    var level = Level()

    val alive: Boolean
        get() = health.isAlive()
}

class Health {

    private var _value = 1000
    val value
        get() = _value

    fun isAlive() = _value > 0
}

class Level {

    private var _value = 1
    val value
        get() = _value
}
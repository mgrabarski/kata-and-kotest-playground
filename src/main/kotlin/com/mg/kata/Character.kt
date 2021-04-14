package com.mg.kata

class Character {

    var health = Health()
    var level = Level()

    val live: Boolean
        get() = if (health.value > 0) true else false
}

class Health {

    private var _value = 1000
    val value
        get() = _value
}

class Level {

    private var _value = 1
    val value
        get() = _value
}
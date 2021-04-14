package com.mg.kata

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class HealthTest : FunSpec({

    test("By default health is 1000") {
        Health().value shouldBe 1000
    }

    test("Subtract health") {
        Health(100).subtract(Health(20)) shouldBeSameAs Health(80)
    }

    test("Add health") {
        Health(100).add(Health(100)) shouldBeSameAs Health(200)
    }

    test("Health can not be bigger then MAX_HEALTH") {
        Health(MAX_HEALTH).add(Health(100)) shouldBeSameAs Health(MAX_HEALTH)
    }
})

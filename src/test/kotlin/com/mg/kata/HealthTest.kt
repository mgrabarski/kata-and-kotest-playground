package com.mg.kata

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class HealthTest : FunSpec({

    test("By default health is 1000") {
        Health().value shouldBe 1000
    }

    test("Subtract healt") {
        Health(100).subtract(Health(20)).value shouldBe Health(80).value
    }
})

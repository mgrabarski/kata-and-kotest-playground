package com.mg.kata.rpg1

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class PositionTest : FreeSpec({

    /*
        0   1    2    3    4    5    6
      +---+----+----+----+----+----+---+
    0 | - | -  | -  | -  | -  | -  | - |
      +---+----+----+----+----+----+---+
    1 | - | ok | ok | ok | ok | ok | - |
      +---+----+----+----+----+----+---+
    2 | - | ok | ok | ok | ok | ok | - |
      +---+----+----+----+----+----+---+
    3 | - | ok | ok | XX | ok | ok | - |
      +---+----+----+----+----+----+---+
    4 | - | ok | ok | ok | ok | ok | - |
      +---+----+----+----+----+----+---+
    5 | - | ok | ok | ok | ok | ok | - |
      +---+----+----+----+----+----+---+
    6 | - | -  | -  | -  | -  | -  | - |
      +---+----+----+----+----+----+---+
     */
    "check position in range 2" - {
        listOf(
            TestData(Position(3, 3), Position(0, 0), false),
            TestData(Position(3, 3), Position(0, 1), false),
            TestData(Position(3, 3), Position(0, 2), false),
            TestData(Position(3, 3), Position(0, 3), false),
            TestData(Position(3, 3), Position(0, 4), false),
            TestData(Position(3, 3), Position(0, 5), false),
            TestData(Position(3, 3), Position(0, 6), false),
            TestData(Position(3, 3), Position(1, 0), false),
            TestData(Position(3, 3), Position(1, 1), true),
            TestData(Position(3, 3), Position(1, 2), true),
            TestData(Position(3, 3), Position(1, 3), true),
            TestData(Position(3, 3), Position(1, 4), true),
            TestData(Position(3, 3), Position(1, 5), true),
            TestData(Position(3, 3), Position(1, 6), false),
            TestData(Position(3, 3), Position(2, 0), false),
            TestData(Position(3, 3), Position(2, 1), true),
            TestData(Position(3, 3), Position(2, 2), true),
            TestData(Position(3, 3), Position(2, 3), true),
            TestData(Position(3, 3), Position(2, 4), true),
            TestData(Position(3, 3), Position(2, 5), true),
            TestData(Position(3, 3), Position(2, 6), false),
            TestData(Position(3, 3), Position(3, 0), false),
            TestData(Position(3, 3), Position(3, 1), true),
            TestData(Position(3, 3), Position(3, 2), true),
            TestData(Position(3, 3), Position(3, 3), true),
            TestData(Position(3, 3), Position(3, 4), true),
            TestData(Position(3, 3), Position(3, 5), true),
            TestData(Position(3, 3), Position(3, 6), false),
            TestData(Position(3, 3), Position(4, 0), false),
            TestData(Position(3, 3), Position(4, 1), true),
            TestData(Position(3, 3), Position(4, 2), true),
            TestData(Position(3, 3), Position(4, 3), true),
            TestData(Position(3, 3), Position(4, 4), true),
            TestData(Position(3, 3), Position(4, 5), true),
            TestData(Position(3, 3), Position(4, 6), false),
            TestData(Position(3, 3), Position(5, 0), false),
            TestData(Position(3, 3), Position(5, 1), true),
            TestData(Position(3, 3), Position(5, 2), true),
            TestData(Position(3, 3), Position(5, 3), true),
            TestData(Position(3, 3), Position(5, 4), true),
            TestData(Position(3, 3), Position(5, 5), true),
            TestData(Position(3, 3), Position(5, 6), false),
            TestData(Position(3, 3), Position(6, 0), false),
            TestData(Position(3, 3), Position(6, 1), false),
            TestData(Position(3, 3), Position(6, 2), false),
            TestData(Position(3, 3), Position(6, 3), false),
            TestData(Position(3, 3), Position(6, 4), false),
            TestData(Position(3, 3), Position(6, 5), false),
            TestData(Position(3, 3), Position(6, 6), false)
        ).forEach {
            val (position, to, expextedResult) = it
            "check $to is in range of $position result should be $expextedResult"{
                position.isInRange(2, to) shouldBe expextedResult
            }
        }
    }
})

private data class TestData(
    val position: Position,
    val to: Position,
    val expextedResult: Boolean
)

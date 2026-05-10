package 프로그래머스.입문.Day20.캐릭터의좌표

import util.validate


class Solution {

  val U = "up"
  val D = "down"
  val L = "left"
  val R = "right"

  fun solution(input: Array<String>, board: IntArray): IntArray {
    var x = 0
    var y = 0

    val MX = board[0] / 2
    val MY = board[1] / 2
    for (dir in input) {
      when (dir) {
        U -> if (y < MY) y++
        D -> if (y > -MY) y--
        R -> if (x < MX) x++
        L -> if (x > -MX) x--
      }
    }
    return intArrayOf(x, y)
  }
}

fun main() {
  val s = Solution()

  validate(s.solution(
    arrayOf<String>("left", "right", "up", "right", "right"), intArrayOf(11, 11)), intArrayOf(2, 1))

  validate(s.solution(arrayOf<String>("down"), intArrayOf(3, 3)), intArrayOf(0, -1))
  validate(s.solution(arrayOf<String>("down", "down", "down", "down", "down"), intArrayOf(3, 3)),
    intArrayOf(0, -1))
  validate(s.solution(arrayOf<String>("down", "down"), intArrayOf(1, 1)), intArrayOf(0, 0))

  validate(s.solution(arrayOf<String>("up", "down", "left", "right"), intArrayOf(3, 3)),
    intArrayOf(0, 0))
}

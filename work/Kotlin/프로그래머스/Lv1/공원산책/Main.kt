package 프로그래머스.Lv1.공원산책

import util.validate

class Solution {

  val ZERO = 48
  val START = 'S'
  val X = 'X'
  fun solution(park: Array<String>, routes: Array<String>): IntArray {
    val H = park.size
    val W = park[0].length
    fun inRow(row: Int): Boolean = row in 0 until H
    fun inCol(col: Int): Boolean = col in 0 until W

    var r = 0
    var c = 0

    l@ for (i in 0 until H) {
      val col = park[i]
      for (j in 0 until W) {
        if (col[j] == START) {
          r = i
          c = j
          break@l
        }
      }
    }

    l@ for (route in routes) {
      val op = route[0]
      val n = route[2].code - ZERO
      when (op) {
        'N' -> {
          val nr = r - n
          if (!inRow(nr)) continue@l
          for (tr in nr until r) if (park[tr][c] == X) continue@l
          r = nr
        }
        'S' -> {
          val nr = r + n
          if (!inRow(nr)) continue@l
          for (tr in r + 1..nr) if (park[tr][c] == X) continue@l
          r = nr
        }
        'W' -> {
          val nc = c - n
          if (!inCol(nc)) continue@l
          for (tc in nc until c) if (park[r][tc] == X) continue@l
          c = nc
        }
        'E' -> {
          val nc = c + n
          if (!inCol(nc)) continue@l
          for (tc in c + 1..nc) if (park[r][tc] == X) continue@l
          c = nc
        }
      }
    }

    return intArrayOf(r, c)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 58.3MB)
 * 테스트 2 〉	통과 (0.02ms, 58.3MB)
 * 테스트 3 〉	통과 (0.03ms, 57.9MB)
 * 테스트 4 〉	통과 (0.03ms, 59.1MB)
 * 테스트 5 〉	통과 (0.03ms, 58.1MB)
 * 테스트 6 〉	통과 (0.05ms, 59.9MB)
 * 테스트 7 〉	통과 (0.04ms, 59.8MB)
 * 테스트 8 〉	통과 (0.04ms, 59.6MB)
 * 테스트 9 〉	통과 (0.05ms, 58.3MB)
 * 테스트 10 〉	통과 (0.04ms, 60.2MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     private fun findStart(park: Array<String>): MutableList<Int> {
 *         for (i in park.indices)
 *             for (j in park[i].indices)
 *                 if (park[i][j] == 'S')
 *                     return mutableListOf(i, j)
 *         return mutableListOf(0, 0)
 *     }
 *
 *     fun solution(park: Array<String>, routes: Array<String>): IntArray {
 *         val directions = mapOf('E' to (0 to 1), 'W' to (0 to -1), 'N' to (-1 to 0), 'S' to (1 to 0))
 *         return routes.map { it[0] to it.drop(2).toInt() }
 *                 .fold(findStart(park)) { pos, (direction, distance) ->
 *                     val prevPos = pos.toMutableList()
 *                     val nextPos = pos.toMutableList()
 *                     repeat(distance) {
 *                         nextPos[0] += directions[direction]!!.first
 *                         nextPos[1] += directions[direction]!!.second
 *                         if (!(0 <= nextPos[0] && nextPos[0] < park.size && 0 <= nextPos[1] && nextPos[1] < park[0].length && park[nextPos[0]][nextPos[1]] != 'X'))
 *                             return@fold prevPos
 *                     }
 *                     return@fold nextPos
 *                 }.toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (12.12ms, 61.8MB)
 * 테스트 2 〉	통과 (11.70ms, 62.5MB)
 * 테스트 3 〉	통과 (11.29ms, 61.1MB)
 * 테스트 4 〉	통과 (12.31ms, 62.5MB)
 * 테스트 5 〉	통과 (12.12ms, 61.9MB)
 * 테스트 6 〉	통과 (16.71ms, 60.5MB)
 * 테스트 7 〉	통과 (20.75ms, 61.4MB)
 * 테스트 8 〉	통과 (21.31ms, 61.4MB)
 * 테스트 9 〉	통과 (14.35ms, 60.4MB)
 * 테스트 10 〉	통과 (15.77ms, 63.5MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(
      arrayOf("SOO", "OOO", "OOO"),
      arrayOf("E 2", "S 2", "W 1"),
    ),
    intArrayOf(2, 1)
  )

  validate(
    s.solution(
      arrayOf("SOO", "OXX", "OOO"),
      arrayOf("E 2", "S 2", "W 1"),
    ),
    intArrayOf(0, 1)
  )

  validate(
    s.solution(
      arrayOf("OSO", "OOO", "OXO", "OOO"),
      arrayOf("E 2", "S 3", "W 1"),
    ),
    intArrayOf(0, 0)
  )

}

//    println("[STT] $r, $c (W=$W, H=$H)")

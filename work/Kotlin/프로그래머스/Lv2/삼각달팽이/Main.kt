package 프로그래머스.Lv2.삼각달팽이

import util.validate

class Solution {
  companion object {

    const val D = 0
    const val R = 1
    const val U = 2
    const val EMPTY = 0
  }

  fun solution(n: Int): IntArray {
    var end = n * (n + 1) / 2
    var ans = IntArray(end)

    var r = -1
    var c = 0
    var x = 1
    var dir = D

    while (x <= end) {
      when (dir) {
        D -> r++
        R -> c++
        else -> {
          r--
          c--
        }
      }

      val pos = r * (r + 1) / 2 + c
      if (r in 0 until n && c in 0..r && ans[pos] == EMPTY) {
        ans[pos] = x++
      } else {
        dir = when (dir) {
          D -> {
            r--
            R
          }
          R -> {
            c--
            U
          }
          else -> {
            r++
            c++
            D
          }
        }
      }
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 60.5MB)
 * 테스트 2 〉	통과 (0.01ms, 60.3MB)
 * 테스트 3 〉	통과 (0.01ms, 59.7MB)
 * 테스트 4 〉	통과 (0.29ms, 61.4MB)
 * 테스트 5 〉	통과 (0.26ms, 61MB)
 * 테스트 6 〉	통과 (0.24ms, 62.3MB)
 * 테스트 7 〉	통과 (8.43ms, 109MB)
 * 테스트 8 〉	통과 (7.16ms, 109MB)
 * 테스트 9 〉	통과 (9.58ms, 108MB)
 * ```
 *
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(n: Int): Array<Int> {
 *         val array: Array<Array<Int>> = Array(n) { arrayOf(0) }
 *         val answer: ArrayList<Int> = arrayListOf()
 *         var line = Line.LEFT
 *         var num = 0
 *         var repeated = 0
 *
 *         for (i in 0 until n) {
 *             array[i] = Array(i + 1) { 0 }
 *         }
 *         for (r in 0 until n) {
 *             when (line) {
 *                 Line.LEFT -> {
 *                     line = Line.BOTTOM
 *
 *                     for (i in repeated * 2 until array.count() - repeated) {
 *                         num += 1
 *                         array[i][repeated] = num
 *                     }
 *                 }
 *                 Line.BOTTOM -> {
 *                     line = Line.RIGHT
 *
 *                     for (j in repeated + 1 until array[array.count() - 1 - repeated].count() - repeated) {
 *                         num += 1
 *                         array[array.count() - 1 - repeated][j] = num
 *                     }
 *                 }
 *                 Line.RIGHT -> {
 *                     line = Line.LEFT
 *                     repeated += 1
 *
 *                     for (k in array.count() - 1 - repeated downTo 2 * repeated - 1 step 1) {
 *                         num += 1
 *                         array[k][array[k].count() - repeated] = num
 *                     }
 *                 }
 *             }
 *         }
 *         array.forEach { childArray ->
 *             childArray.forEach {
 *                 answer.add(it)
 *             }
 *         }
 *         return answer.toTypedArray()
 *     }
 * }
 *
 * enum class Line {
 *     LEFT, RIGHT, BOTTOM
 * }
 * 테스트 1 〉	통과 (1.62ms, 60.3MB)
 * 테스트 2 〉	통과 (1.63ms, 59.4MB)
 * 테스트 3 〉	통과 (1.73ms, 59MB)
 * 테스트 4 〉	통과 (2.44ms, 59.8MB)
 * 테스트 5 〉	통과 (2.59ms, 59.1MB)
 * 테스트 6 〉	통과 (2.21ms, 61.3MB)
 *
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(n: Int) = Array(n) { num -> IntArray(num + 1) { 0 } }
 *         .apply {
 *             var row = 0
 *             var column = 0
 *             var movingMode = 0 // 0: 아래쪽, 1: 오른쪽, 2: 왼쪽 위 대각선
 *             for (i in 1..(n * (n + 1) / 2)) {
 *                 this[row][column] = i
 *                 when(movingMode) {
 *                     0 -> if(row + 1 >= n || this[row + 1][column] != 0) movingMode = 1
 *                     1 -> if (column + 1 > row || this[row][column + 1] != 0) movingMode = 2
 *                     2 -> if (row - 1 < 0 || column - 1 < 0 || this[row-1][column - 1] != 0) movingMode = 0
 *                 }
 *                 when (movingMode) {
 *                     0 -> row++ // 아리쪽으로 이동
 *                     1 -> column++ // 오른쪽으로 이동
 *                     2 -> row-- and column-- // 왼쪽 위 대각선으로 이동
 *                 }
 *             }
 *         }
 *         .fold(arrayListOf<Int>()) { acc, ints -> acc.apply { addAll(ints.toList()) } }
 *         .toIntArray()
 * }
 * 테스트 1 〉	통과 (12.42ms, 63.6MB)
 * 테스트 2 〉	통과 (12.38ms, 64.4MB)
 * 테스트 3 〉	통과 (12.23ms, 64.6MB)
 * 테스트 4 〉	통과 (13.12ms, 65.3MB)
 * 테스트 5 〉	통과 (13.65ms, 64.5MB)
 * 테스트 6 〉	통과 (14.85ms, 65.1MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(1),
    intArrayOf(1)
  )

  validate(s.solution(2),
    intArrayOf(1, 2, 3)
  )

  validate(s.solution(3),
    intArrayOf(1, 2, 6, 3, 4, 5)
  )

  validate(s.solution(4),
    intArrayOf(1, 2, 9, 3, 10, 8, 4, 5, 6, 7)
  )

  validate(s.solution(5),
    intArrayOf(1, 2, 12, 3, 13, 11, 4, 14, 15, 10, 5, 6, 7, 8, 9)
  )

  validate(s.solution(6),
    intArrayOf(1, 2, 15, 3, 16, 14, 4, 17, 21, 13, 5, 18, 19, 20, 12, 6, 7, 8, 9, 10, 11)
  )


}

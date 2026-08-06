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
    fun getPos(r: Int, c: Int): Int = (r - 1) * r / 2 + c

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

      val pos = getPos(r, c)
      if (r in 0 until n && c in 0..r && ans[pos] == EMPTY) ans[pos] = x++
      else {
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
 * 테스트 1 〉	통과 (0.01ms, 60.4MB)
 * 테스트 2 〉	실패 (시간 초과)
 * 테스트 3 〉	실패 (시간 초과)
 * 테스트 4 〉	실패 (시간 초과)
 * 테스트 5 〉	실패 (시간 초과)
 * 테스트 6 〉	실패 (시간 초과)
 * 테스트 7 〉	실패 (시간 초과)
 * 테스트 8 〉	실패 (시간 초과)
 * 테스트 9 〉	실패 (시간 초과)
 * ```
 *
 *
 *
 * ```
 * [RIVAL]
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

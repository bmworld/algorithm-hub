package 프로그래머스.Lv2.쿼드압축후개수세기

import util.validate

class Solution {

  companion object {

    var dr = intArrayOf(0, 0, 1, 1)
    var dc = intArrayOf(0, 1, 0, 1)
  }

  fun solution(arr: Array<IntArray>): IntArray {
    var zero = 0
    var one = 0

    fun compress(cnt: Int, w: Int) {
      if (cnt == 0) zero -= 3
      if (cnt == w * w) one -= 3
    }

    fun dfs(r: Int, c: Int, w: Int): Int {
      if (w == 1) return arr[r][c].also { if (it == 0) zero++ else one++ }

      var cnt = 0
      val nw = w / 2
      repeat(4) {
        cnt += dfs(r + nw * dr[it], c + nw * dc[it], nw)
      }

      compress(cnt, w)

      return cnt
    }

    dfs(0, 0, arr[0].size)

    return intArrayOf(zero, one)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.26ms, 60MB)
 * 테스트 2 〉	통과 (0.26ms, 60.1MB)
 * 테스트 3 〉	통과 (0.48ms, 59.3MB)
 * 테스트 4 〉	통과 (0.19ms, 60.3MB)
 * 테스트 5 〉	통과 (6.18ms, 65.5MB)
 * 테스트 6 〉	통과 (3.06ms, 67.2MB)
 * 테스트 7 〉	통과 (2.67ms, 68.5MB)
 * 테스트 8 〉	통과 (2.46ms, 68.4MB)
 * 테스트 9 〉	통과 (2.41ms, 67.8MB)
 * 테스트 10 〉	통과 (6.70ms, 92.8MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     private lateinit var map: Array<IntArray>
 *     private var zero = 0
 *     private var one = 0
 *     fun solution(arr: Array<IntArray>): IntArray {
 *         val answer = IntArray(2)
 *         map = arr
 *         dfs(arr.size, 0, 0)
 *         answer[0] = zero
 *         answer[1] = one
 *         return answer
 *     }
 *
 *     private fun dfs(n: Int, x: Int, y: Int) {
 *         if (n == 1) {
 *             if (map[x][y] == 1) {
 *                 one++
 *             } else {
 *                 zero++
 *             }
 *             return
 *         }
 *         if (isSame(n, x, y)) {
 *             return
 *         }
 *         dfs(n / 2, x, y)
 *         dfs(n / 2, x + n / 2, y)
 *         dfs(n / 2, x, y + n / 2)
 *         dfs(n / 2, x + n / 2, y + n / 2)
 *     }
 *
 *     private fun isSame(n: Int, x: Int, y: Int): Boolean {
 *         val first = map[x][y]
 *         for (i in x until x + n) {
 *             for (j in y until y + n) {
 *                 if (first != map[i][j]) {
 *                     return false
 *                 }
 *             }
 *         }
 *         if (first == 0) {
 *             zero += 1
 *         } else {
 *             one += 1
 *         }
 *         return true
 *     }
 *
 * }
 * 테스트 1 〉	통과 (0.10ms, 60.3MB)
 * 테스트 2 〉	통과 (0.10ms, 61MB)
 * 테스트 3 〉	통과 (0.05ms, 59.3MB)
 * 테스트 4 〉	통과 (0.02ms, 59.3MB)
 * 테스트 5 〉	통과 (5.35ms, 68.2MB)
 * 테스트 6 〉	통과 (2.78ms, 68.2MB)
 * 테스트 7 〉	통과 (2.89ms, 68.5MB)
 * 테스트 8 〉	통과 (1.79ms, 68.7MB)
 * 테스트 9 〉	통과 (2.13ms, 68.8MB)
 * 테스트 10 〉	통과 (5.66ms, 93.5MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      intArrayOf(1)
    )
  ),
    intArrayOf(0, 1)
  )

  validate(s.solution(
    arrayOf(
      intArrayOf(0)
    )
  ),
    intArrayOf(1, 0)
  )


  validate(s.solution(
    arrayOf(
      intArrayOf(1, 1, 0, 0),
      intArrayOf(1, 0, 0, 0),
      intArrayOf(1, 0, 0, 1),
      intArrayOf(1, 1, 1, 1),
    )
  ),
    intArrayOf(4, 9)
  )

  validate(s.solution(
    arrayOf(
      intArrayOf(1, 1, 1, 1, 1, 1, 1, 1),
      intArrayOf(0, 1, 1, 1, 1, 1, 1, 1),
      intArrayOf(0, 0, 0, 0, 1, 1, 1, 1),
      intArrayOf(0, 1, 0, 0, 1, 1, 1, 1),
      intArrayOf(0, 0, 0, 0, 0, 0, 1, 1),
      intArrayOf(0, 0, 0, 0, 0, 0, 0, 1),
      intArrayOf(0, 0, 0, 0, 1, 0, 0, 1),
      intArrayOf(0, 0, 0, 0, 1, 1, 1, 1),
    )
  ), intArrayOf(10, 15))
}

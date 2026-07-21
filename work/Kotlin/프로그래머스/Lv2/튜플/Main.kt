package 프로그래머스.Lv2.튜플

import util.validate

class Solution {

  companion object {

    const val op = '{'.code
    const val sep = ','.code
    const val close = '}'.code
    const val ZERO = 48
    val NUM = ZERO..ZERO + 9

    const val ADDED_SKIP = 2
    const val MAX_ELEMS = 500
  }

  fun solution(s: String): IntArray {
    var total = 0
    val map = HashMap<Int, IntArray>()
    val buf = IntArray(MAX_ELEMS)
    var maxX = 0

    var fr = 2
    val to = s.length - 2
    var x = 0
    var i = 0
    var elems = 0


    fun add() {
      buf[i++] = x.also { if (it > maxX) maxX = it }
      x = 0
      elems++
    }

    while (fr <= to) {
      val c = s[fr++].code
      when (c) {
        close -> {
          add()
          map[elems] = IntArray(elems) { buf[it] }
          i = 0
          elems = 0
          total++
          fr += ADDED_SKIP
        }
        sep -> add()
        in NUM -> x = x * 10 + (c - ZERO)
      }
    }

    var ans = IntArray(total)
    val used = BooleanArray(maxX + 1)
    repeat(total) {
      for (x in map[it + 1]!!) {
        if (used[x]) continue
        used[x] = true
        ans[it] = x
        break
      }
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.23ms, 60.4MB)
 * 테스트 2 〉	통과 (0.30ms, 59.5MB)
 * 테스트 3 〉	통과 (0.37ms, 59MB)
 * 테스트 4 〉	통과 (0.44ms, 58.6MB)
 * 테스트 5 〉	통과 (0.46ms, 59.8MB)
 * 테스트 6 〉	통과 (2.09ms, 59.7MB)
 * 테스트 7 〉	통과 (5.98ms, 60.3MB)
 * 테스트 8 〉	통과 (11.71ms, 59.6MB)
 * 테스트 9 〉	통과 (5.25ms, 61.1MB)
 * 테스트 10 〉	통과 (6.39ms, 64.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(s: String): IntArray {
 *         return s.substring(2 until s.length-2)
 *             .split("},{")
 *             .asSequence()
 *             .map { it.split(",").map { num -> num.toInt() } }
 *             .toList()
 *             .sortedBy { it.size }
 *             .fold(setOf<Int>()) { acc, list -> acc.union(list) }
 *             .toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (36.49ms, 65.5MB)
 * 테스트 2 〉	통과 (35.40ms, 65MB)
 * 테스트 3 〉	통과 (15.76ms, 62.3MB)
 * 테스트 4 〉	통과 (29.81ms, 66.3MB)
 * 테스트 5 〉	통과 (30.18ms, 66.6MB)
 * 테스트 6 〉	통과 (28.63ms, 66.7MB)
 * 테스트 7 〉	통과 (46.23ms, 71.6MB)
 * 테스트 8 〉	통과 (49.59ms, 79.4MB)
 * 테스트 9 〉	통과 (39.98ms, 75.6MB)
 * 테스트 10 〉	통과 (53.83ms, 81MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}"), intArrayOf(2, 1, 3, 4))
  validate(s.solution("{{1,2,3},{2,1},{1,2,4,3},{2}}"), intArrayOf(2, 1, 3, 4))
  validate(s.solution("{{20,111},{111}}"), intArrayOf(111, 20))
  validate(s.solution("{{123}}"), intArrayOf(123))
  validate(s.solution("{{4,2,3},{3},{2,3,4,1},{2,3}}"), intArrayOf(3, 2, 4, 1))
}

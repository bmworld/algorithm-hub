package 프로그래머스.Lv0._1로만들기

import util.validate

class Solution {

  companion object {

    const val EMPTY = -1
    const val MAX_NUM = 30
  }

  fun solution(num_list: IntArray): Int {
    var ans = 0
    val cache = IntArray(MAX_NUM + 1) { EMPTY }
    cache[1] = 0
    for (x in num_list) {
      val i = cache[x]
      ans += if (i != EMPTY) i else count(x).also { cache[x] = it }
    }
    return ans
  }

  private fun count(x: Int): Int {
    var r = 0
    var t = x
    while (t != 1) {
      t = if (t % 2 == 0) t / 2 else (t - 1) / 2
      r++
    }

    return r
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.22ms, 59.1MB)
 * 테스트 2 〉	통과 (0.16ms, 58.9MB)
 * 테스트 3 〉	통과 (0.21ms, 60MB)
 * 테스트 4 〉	통과 (0.17ms, 60.1MB)
 * 테스트 5 〉	통과 (0.16ms, 59.2MB)
 * 테스트 6 〉	통과 (0.16ms, 59.5MB)
 * 테스트 7 〉	통과 (0.15ms, 58.1MB)
 * 테스트 8 〉	통과 (0.15ms, 59.9MB)
 * 테스트 9 〉	통과 (0.21ms, 59.7MB)
 * 테스트 10 〉	통과 (0.22ms, 60MB)
 * 테스트 11 〉	통과 (0.18ms, 60.1MB)
 * 테스트 12 〉	통과 (0.15ms, 59.4MB)
 * 테스트 13 〉	통과 (0.17ms, 57.7MB)
 * 테스트 14 〉	통과 (0.16ms, 60.5MB)
 * 테스트 15 〉	통과 (0.24ms, 60.2MB)
 * 테스트 16 〉	통과 (0.14ms, 60.4MB)
 * 테스트 17 〉	통과 (0.16ms, 57.5MB)
 * 테스트 18 〉	통과 (0.15ms, 59.2MB)
 * 테스트 19 〉	통과 (0.17ms, 59MB)
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(12, 4, 15, 1, 1, 14)), 11)
}

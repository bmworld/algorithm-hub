package 프로그래머스.Lv0.배열만들기5

import util.validate

class Solution {
  companion object {

    const val ZERO = 48
  }

  fun solution(intStrs: Array<String>, k: Int, s: Int, l: Int): IntArray {
    val buf = IntArray(intStrs.size)
    var cnt = 0
    l@ for (str in intStrs) {
      var x = 0
      for (i in s until s + l)
        x = x * 10 + (str[i].code - ZERO)
      if (x > k) buf[cnt++] = x
    }
    return buf.copyOf(cnt)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (1.12ms, 72.2MB)
 * 테스트 2 〉	통과 (0.98ms, 70.8MB)
 * 테스트 3 〉	통과 (0.21ms, 60.3MB)
 * 테스트 4 〉	통과 (0.16ms, 60.8MB)
 * 테스트 5 〉	통과 (0.62ms, 64.3MB)
 * 테스트 6 〉	통과 (0.69ms, 65.9MB)
 * 테스트 7 〉	통과 (0.68ms, 63.8MB)
 * 테스트 8 〉	통과 (1.07ms, 68.3MB)
 * 테스트 9 〉	통과 (0.71ms, 66.3MB)
 * 테스트 10 〉	통과 (0.93ms, 70.2MB)
 * 테스트 11 〉	통과 (0.67ms, 66.6MB)
 *
 * v2:
 * 테스트 1 〉	통과 (1.09ms, 70.8MB)
 * 테스트 2 〉	통과 (0.90ms, 69.3MB)
 * 테스트 3 〉	통과 (0.33ms, 61.6MB)
 * 테스트 4 〉	통과 (0.19ms, 62.1MB)
 * 테스트 5 〉	통과 (0.70ms, 63.3MB)
 * 테스트 6 〉	통과 (0.59ms, 64.6MB)
 * 테스트 7 〉	통과 (0.70ms, 64.7MB)
 * 테스트 8 〉	통과 (0.77ms, 67.6MB)
 * 테스트 9 〉	통과 (0.96ms, 65.4MB)
 * 테스트 10 〉	통과 (1.12ms, 70.3MB)
 * 테스트 11 〉	통과 (0.74ms, 67MB)
 *
 *
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(intStrs: Array<String>, k: Int, s: Int, l: Int): List<Int> {
 *         return intStrs.map { it.substring(s, s + l).toInt() }.filter { it > k }
 *     }
 * }
 * 테스트 1 〉	통과 (4.72ms, 73.7MB)
 * 테스트 2 〉	통과 (3.44ms, 71.9MB)
 * 테스트 3 〉	통과 (1.52ms, 60.9MB)
 * 테스트 4 〉	통과 (1.13ms, 60.9MB)
 * 테스트 5 〉	통과 (2.30ms, 64.1MB)
 * 테스트 6 〉	통과 (3.51ms, 65.5MB)
 * 테스트 7 〉	통과 (2.54ms, 64.3MB)
 * 테스트 8 〉	통과 (2.86ms, 67.1MB)
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf(
    "0123456789", "9876543210", "9999999999999"
  ), 50000, 5, 5),
    intArrayOf(56789, 99999)
  )

  validate(s.solution(arrayOf(
    "011", "912", "913"
  ), 13, 1, 2),
    intArrayOf()
  )

  validate(s.solution(arrayOf(
    "011", "912", "913"
  ), 12, 1, 2),
    intArrayOf(13)
  )
}

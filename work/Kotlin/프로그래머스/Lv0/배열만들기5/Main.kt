package 프로그래머스.Lv0.배열만들기5

import util.validate

class Solution {
  companion object {

    const val MAX_LEN = 8
    const val ZERO = 48
  }

  fun solution(intStrs: Array<String>, k: Int, s: Int, l: Int): IntArray {
    val kArr = IntArray(MAX_LEN)
    var x = k
    var kLen = 0
    while (x > 0) {
      kArr[kLen++] = x % 10
      x /= 10
    }

    val buf = IntArray(intStrs.size)
    var cnt = 0
    l@ for (str in intStrs) {
      var x = 0
      for (i in s until s + l) {
        val d = str[i].code - ZERO
        if (i == s && d < kArr[kLen - 1]) continue@l
        x = x * 10 + d
      }
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
 * [RIVAL 1]
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

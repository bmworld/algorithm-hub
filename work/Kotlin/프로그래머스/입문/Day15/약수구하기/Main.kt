package 프로그래머스.입문.Day15.약수구하기

import kotlin.math.sqrt

class Solution {

  val min = 1
  fun solution(n: Int): IntArray {

    val tmp = IntArray((sqrt(n.toDouble()).toInt() + 1) * 2)
    var len = 0
    tmp[len++] = 1

    if (n > min) {
      var d = 2
      while (d <= n / d) {
        if (n % d == 0) tmp[len++] = d
        d++
      }
      val times = len
      repeat(times) {
        val i = times - (it + 1)
        val x = tmp[i]
        if (x * x != n) tmp[len++] = n / x
      }
    }

    return IntArray(len) { tmp[it] }
  }
}

fun main() {
  val s = Solution()
  val act = s.solution(24)
  val exp = intArrayOf(1, 2, 3, 4, 6, 8, 12, 24)
  repeat(exp.size) {
    check(act[it] == exp[it])
  }

  val act2 = s.solution(8)
  val exp2 = intArrayOf(1, 2, 4, 8)
  repeat(exp2.size) {
    check(act2[it] == exp2[it])
  }
}

/**
 * AS IS
 * 테스트 1 〉	통과 (4.21ms, 63.4MB)
 * 테스트 2 〉	통과 (3.95ms, 62.3MB)
 * 테스트 3 〉	통과 (4.08ms, 63.8MB)
 * 테스트 4 〉	통과 (4.12ms, 61.7MB)
 * 테스트 5 〉	통과 (3.96ms, 61.8MB)
 * 테스트 6 〉	통과 (5.60ms, 64MB)
 * 테스트 7 〉	통과 (3.77ms, 64MB)
 * 테스트 8 〉	통과 (4.42ms, 63.5MB)
 * 테스트 9 〉	통과 (3.88ms, 62.9MB)
 * 테스트 10 〉	통과 (4.09ms, 62.2MB)
 * 테스트 11 〉	통과 (3.61ms, 62.6MB)
 * 테스트 12 〉	통과 (3.65ms, 62.5MB)
 *
 *
 * TO BE
 * 테스트 1 〉	통과 (0.03ms, 64MB)
 * 테스트 2 〉	통과 (0.02ms, 61.6MB)
 * 테스트 3 〉	통과 (0.08ms, 61.6MB)
 * 테스트 4 〉	통과 (0.02ms, 64.7MB)
 * 테스트 5 〉	통과 (0.02ms, 61.7MB)
 * 테스트 6 〉	통과 (0.02ms, 61.1MB)
 * 테스트 7 〉	통과 (0.02ms, 61.7MB)
 * 테스트 8 〉	통과 (0.02ms, 61.8MB)
 * 테스트 9 〉	통과 (0.02ms, 62.5MB)
 * 테스트 10 〉	통과 (0.02ms, 61.3MB)
 * 테스트 11 〉	통과 (0.08ms, 62.1MB)
 * 테스트 12 〉	통과 (0.03ms, 61.2MB)
 *
 */

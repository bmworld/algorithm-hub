package 프로그래머스.입문.Day11.합성수찾기

class Solution {

  fun solution(n: Int): Int {
    val cutOff = 3
    if (n <= cutOff) return 0
    var ans = n - cutOff

    val primes = BooleanArray(n + 1) {
      if (it <= 1) false
      if (it <= 3) true
      else it % 2 != 0
    }.also {
      var d = cutOff
      while (d <= n / d) {
        for (i in d * d..n step d * 2) it[i] = false
        d += 2
      }
    }

    for (i in cutOff + 1..n) if (primes[i]) ans--

    return ans
  }
}

fun main() {
  val s = Solution()
  check(s.solution(10) == 5)
  check(s.solution(15) == 8)
}

/**
 * 테스트 1 〉	통과 (0.27ms, 63.6MB)
 * 테스트 2 〉	통과 (0.01ms, 64.6MB)
 * 테스트 3 〉	통과 (0.02ms, 61.3MB)
 * 테스트 4 〉	통과 (0.01ms, 63.4MB)
 * 테스트 5 〉	통과 (0.38ms, 63.4MB)
 * 테스트 6 〉	통과 (0.43ms, 63.3MB)
 */

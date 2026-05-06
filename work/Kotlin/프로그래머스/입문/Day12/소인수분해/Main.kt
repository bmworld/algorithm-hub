package 프로그래머스.입문.Day12.소인수분해

class Solution {

  fun solution(n: Int): IntArray {

    val primes = BooleanArray(n + 1) {
      if (it <= 1) false
      if (it <= 3) true
      else it % 2 != 0
    }

    var d = 3
    while (d <= n / d) {
      for (i in d * d..n step d * 2) primes[i] = false
      d += 2
    }

    val MAX = 10
    val tmp = IntArray(MAX)
    var len = 0
    for (v in 2..n) if (primes[v] && n % v == 0) tmp[len++] = v

    val ans = IntArray(len)
    System.arraycopy(tmp, 0, ans, 0, len)
    return ans
  }
}

fun main() {
  val s = Solution()
  validate(s.solution(420), intArrayOf(2, 3, 5, 7))
  validate(s.solution(2), intArrayOf(2))
  validate(s.solution(3), intArrayOf(3))
  validate(s.solution(5), intArrayOf(5))
  validate(s.solution(6), intArrayOf(2, 3))
  validate(s.solution(9), intArrayOf(3))
  validate(s.solution(10), intArrayOf(2, 5))
}

fun validate(act: IntArray, exp: IntArray) {
  println("act = ${act.size}")
  repeat(act.size) {
    val a = act[it]
    val b = exp[it]
    check(a == b)
    println("->$a, $b")
  }
}

/**
 * 테스트 1 〉	통과 (1.76ms, 61.6MB)
 * 테스트 2 〉	통과 (0.04ms, 61.6MB)
 * 테스트 3 〉	통과 (0.34ms, 61MB)
 * 테스트 4 〉	통과 (0.39ms, 63MB)
 * 테스트 5 〉	통과 (1.76ms, 61.4MB)
 * 테스트 6 〉	통과 (0.53ms, 61.4MB)
 * 테스트 7 〉	통과 (0.35ms, 62.8MB)
 * 테스트 8 〉	통과 (0.52ms, 63.4MB)
 * 테스트 9 〉	통과 (1.56ms, 61.2MB)
 * 테스트 10 〉	통과 (0.50ms, 63.7MB)
 * 테스트 11 〉	통과 (0.65ms, 64.3MB)
 * 테스트 12 〉	통과 (0.62ms, 65.4MB)
 * 테스트 13 〉	통과 (0.84ms, 65.8MB)
 * 테스트 14 〉	통과 (1.02ms, 61.2MB)
 * 테스트 15 〉	통과 (0.81ms, 63.7MB)
 * 테스트 16 〉	통과 (1.24ms, 63.1MB)
 * 테스트 17 〉	통과 (0.03ms, 64.1MB)
 * 테스트 18 〉	통과 (0.03ms, 63.3MB)
 * 테스트 19 〉	통과 (0.12ms, 63MB)
 * 테스트 20 〉	통과 (0.40ms, 63.1MB)
 * 테스트 21 〉	통과 (0.03ms, 64.9MB)
 * 테스트 22 〉	통과 (0.34ms, 62.9MB)
 * 테스트 23 〉	통과 (0.40ms, 64.6MB)
 * 테스트 24 〉	통과 (0.90ms, 61.9MB)
 */

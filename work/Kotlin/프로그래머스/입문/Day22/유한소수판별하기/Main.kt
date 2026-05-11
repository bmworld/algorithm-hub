package 프로그래머스.입문.Day22.유한소수판별하기

class Solution {

  val FIN = 1
  val INF = 2
  fun solution(a: Int, b: Int): Int {
    var a = a
    var b = b

    var d = 2
    while (d <= a) {
      while (a % d == 0 && b % d == 0) {
        a /= d
        b /= d
      }
      d++
    }

    return if (isINF(b)) INF else FIN
  }

  val witnesses = intArrayOf(2, 5)
  fun isINF(n: Int): Boolean {
    if (n <= 2) return false
    var x = n
    repeat(witnesses.size) {
      val d = witnesses[it]
      while (x % d == 0) x /= d
    }
    return x > 1
  }
}

fun main() {
  val s = Solution()
  check(s.solution(7, 2 * 2 * 5) == 1)
  check(s.solution(11, 22) == 1)
  check(s.solution(3 * 2 * 2, 3 * 7) == 2)
  check(s.solution(1, 1) == 1)
  check(s.solution(1, 9) == 2)
  check(s.solution(2, 2 * 3) == 2)
  check(s.solution(2, 4 * 3) == 2)
  check(s.solution(2, 2) == 1)
  check(s.solution(1, 5 * 5) == 1)
}

/**
 * ME:
 * 테스트 1 〉	통과 (0.01ms, 63.9MB)
 * 테스트 2 〉	통과 (0.01ms, 61.6MB)
 * 테스트 3 〉	통과 (0.01ms, 62.2MB)
 * 테스트 4 〉	통과 (0.01ms, 62.1MB)
 * 테스트 5 〉	통과 (0.02ms, 62.3MB)
 * 테스트 6 〉	통과 (0.01ms, 60.9MB)
 * 테스트 7 〉	통과 (0.01ms, 64.3MB)
 * 테스트 8 〉	통과 (0.01ms, 62.7MB)
 * 테스트 9 〉	통과 (0.01ms, 63.5MB)
 * 테스트 10 〉	통과 (0.01ms, 63.1MB)
 * 테스트 11 〉	통과 (0.01ms, 65.5MB)
 * 테스트 12 〉	통과 (0.01ms, 63.9MB)
 * 테스트 13 〉	통과 (0.02ms, 62.4MB)
 * 테스트 14 〉	통과 (0.01ms, 63.8MB)
 * 테스트 15 〉	통과 (0.01ms, 62.4MB)
 * 테스트 16 〉	통과 (0.02ms, 63MB)
 * 테스트 17 〉	통과 (0.02ms, 62.4MB)
 * 테스트 18 〉	통과 (0.02ms, 64.2MB)
 * 테스트 19 〉	통과 (0.02ms, 64.8MB)
 * 테스트 20 〉	통과 (0.02ms, 62MB)
 * 테스트 21 〉	통과 (0.02ms, 63.3MB)
 * 테스트 22 〉	통과 (0.02ms, 63.6MB)
 * 테스트 23 〉	통과 (0.03ms, 62.7MB)
 * 테스트 24 〉	통과 (0.01ms, 63.5MB)
 * 테스트 25 〉	통과 (0.01ms, 63.4MB)
 * 테스트 26 〉	통과 (0.02ms, 62.7MB)
 * 테스트 27 〉	통과 (0.02ms, 63.8MB)
 * 테스트 28 〉	통과 (0.01ms, 63.3MB)
 * 테스트 29 〉	통과 (0.02ms, 62MB)
 * 테스트 30 〉	통과 (0.02ms, 62.8MB)
 * 테스트 31 〉	통과 (0.01ms, 61.8MB)
 * 테스트 32 〉	통과 (0.02ms, 62.2MB)
 * 테스트 33 〉	통과 (0.02ms, 64.4MB)
 * 테스트 34 〉	통과 (0.03ms, 62.1MB)
 * 테스트 35 〉	통과 (0.02ms, 63.8MB)
 * 테스트 36 〉	통과 (0.02ms, 63.1MB)
 * 테스트 37 〉	통과 (0.03ms, 61.4MB)
 * 테스트 38 〉	통과 (0.02ms, 63.9MB)
 * 테스트 39 〉	통과 (0.02ms, 62.2MB)
 * 테스트 40 〉	통과 (0.03ms, 62.1MB)
 * 테스트 41 〉	통과 (0.02ms, 61.8MB)
 * 테스트 42 〉	통과 (0.02ms, 64.5MB)
 * 테스트 43 〉	통과 (0.02ms, 61.9MB)
 * 테스트 44 〉	통과 (0.02ms, 63.2MB)
 * 테스트 45 〉	통과 (0.01ms, 62.6MB)
 * 테스트 46 〉	통과 (0.02ms, 64MB)
 * 테스트 47 〉	통과 (0.01ms, 65MB)
 * 테스트 48 〉	통과 (0.01ms, 63MB)
 */

package 프로그래머스.입문.Day18.제곱수판별하기

class Solution {

  fun solution(n: Int): Int = binarySearch(n)

  fun binarySearch(n: Int): Int {
    var l = 1
    var r = n

    while (l <= r) {
      val m = (l + r) shr 1
      when {
        m > n / m -> r = m - 1
        m * m < n -> l = m + 1
        else -> return 1
      }
    }
    return 2
  }
}

fun main() {
  val s = Solution()
  check(s.solution(1) == 1)
  check(s.solution(2) == 2)
  check(s.solution(3) == 2)
  check(s.solution(144) == 1)
  check(s.solution(976) == 2)
}

/**
 * AS IS
 * 테스트 1 〉	통과 (0.02ms, 61.8MB)
 * 테스트 2 〉	통과 (0.01ms, 61.5MB)
 * 테스트 3 〉	통과 (0.01ms, 61.5MB)
 * 테스트 4 〉	통과 (0.01ms, 62.2MB)
 * 테스트 5 〉	통과 (0.02ms, 61.1MB)
 * 테스트 6 〉	통과 (0.01ms, 61.2MB)
 * 테스트 7 〉	통과 (0.01ms, 62MB)
 * 테스트 8 〉	통과 (0.01ms, 62.2MB)
 * 테스트 9 〉	통과 (0.01ms, 61.9MB)
 * 테스트 10 〉	통과 (0.01ms, 61MB)
 *
 * TO BE
 * 테스트 1 〉	통과 (0.01ms, 61.8MB)
 * 테스트 2 〉	통과 (0.01ms, 61.6MB)
 * 테스트 3 〉	통과 (0.01ms, 60.9MB)
 * 테스트 4 〉	통과 (0.01ms, 62.5MB)
 * 테스트 5 〉	통과 (0.02ms, 62MB)
 * 테스트 6 〉	통과 (0.01ms, 62.2MB)
 * 테스트 7 〉	통과 (0.01ms, 62.9MB)
 * 테스트 8 〉	통과 (0.01ms, 64.3MB)
 * 테스트 9 〉	통과 (0.01ms, 61.6MB)
 * 테스트 10 〉	통과 (0.01ms, 62.5MB)
 */

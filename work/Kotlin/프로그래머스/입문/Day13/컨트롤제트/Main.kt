package 프로그래머스.입문.Day13.컨트롤제트

class Solution {

  val SPACE = 32
  val MINUS = 45
  val ZERO = 48
  val NUM = ZERO..57
  val Z = 90
  fun solution(str: String): Int {
    var ans = 0
    var prv = 0
    var cur = 0
    var s = 1

    for (i in 0 until str.length) {
      val code = str[i].code
      when (code) {
        SPACE -> {
          ans += (s * cur).also { prv = it }
          cur = 0
          s = 1
        }
        Z -> ans -= prv
        MINUS -> s = -1
        in NUM -> cur = cur * 10 + (code - ZERO)
      }
    }

    ans += s * cur
    return ans
  }
}

fun main() {
  val s = Solution()
  check(s.solution("1 2 Z 3") == 4)
  check(s.solution("10 20 30 40") == 100)
  check(s.solution("10 Z 20 Z 1") == 1)
  check(s.solution("10 Z 20 Z") == 0)
  check(s.solution("-1 -2 -3 Z") == -3)
  check(s.solution("-1 -2 -3 Z -1") == -4)
  check(s.solution("-1000 -3 Z 1") == -999)
}

/**
 * 테스트 1 〉	통과 (0.10ms, 61.5MB)
 * 테스트 2 〉	통과 (0.13ms, 61.9MB)
 * 테스트 3 〉	통과 (0.10ms, 61.9MB)
 * 테스트 4 〉	통과 (0.12ms, 62.6MB)
 * 테스트 5 〉	통과 (0.14ms, 62.2MB)
 * 테스트 6 〉	통과 (0.02ms, 62.1MB)
 * 테스트 7 〉	통과 (0.02ms, 60.9MB)
 * 테스트 8 〉	통과 (0.03ms, 61.2MB)
 * 테스트 9 〉	통과 (0.31ms, 61.2MB)
 * 테스트 10 〉	통과 (0.10ms, 62.6MB)
 * 테스트 11 〉	통과 (0.10ms, 61.3MB)
 * 테스트 12 〉	통과 (0.20ms, 62.1MB)
 * 테스트 13 〉	통과 (0.10ms, 63.1MB)
 * 테스트 14 〉	통과 (0.10ms, 62MB)
 * 테스트 15 〉	통과 (0.13ms, 62MB)
 * 테스트 16 〉	통과 (0.12ms, 60.9MB)
 * 테스트 17 〉	통과 (0.16ms, 63.6MB)
 * 테스트 18 〉	통과 (0.11ms, 61.3MB)
 * 테스트 19 〉	통과 (0.17ms, 62.1MB)
 * 테스트 20 〉	통과 (0.11ms, 60.9MB)
 */

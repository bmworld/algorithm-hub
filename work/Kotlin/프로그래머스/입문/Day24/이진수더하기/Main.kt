package 프로그래머스.입문.Day24.이진수더하기

import util.validate

class Solution {

  val BASE = 2
  val ZERO = 48
  fun solution(b1: String, b2: String): String {
    val ansLen = maxOf(b1.length, b2.length) + 1
    val a = CharArray(ansLen) { ZERO.toChar() }

    var carry = 0
    var i = b1.lastIndex
    var j = b2.lastIndex
    var pos = ansLen - 1
    while (i >= 0 || j >= 0 || carry > 0) {
      val d1 = if (i < 0) 0 else b1[i--].code - ZERO
      val d2 = if (j < 0) 0 else b2[j--].code - ZERO
      val sum = d1 + d2 + carry
      a[pos--] = (sum % BASE + ZERO).toChar()

      carry = sum / BASE
    }
    return a.concatToString(++pos, ansLen)
  }
}

//      println("[$pos] $d1 + $d2 + $carry = $sum ---> ${a[pos]}")
/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (5.87ms, 63.3MB)
 * 테스트 2 〉	통과 (6.11ms, 63.7MB)
 * 테스트 3 〉	통과 (6.14ms, 63.5MB)
 * 테스트 4 〉	통과 (5.70ms, 62.7MB)
 * 테스트 5 〉	통과 (11.93ms, 64.4MB)
 * 테스트 6 〉	통과 (7.55ms, 63.1MB)
 * 테스트 7 〉	통과 (12.41ms, 64.2MB)
 * 테스트 8 〉	통과 (9.33ms, 64MB)
 * 테스트 9 〉	통과 (5.35ms, 62.8MB)
 *
 * 개선:
 * 테스트 1 〉	통과 (8.23ms, 63.5MB)
 * 테스트 2 〉	통과 (5.07ms, 64.1MB)
 * 테스트 3 〉	통과 (7.32ms, 63.7MB)
 * 테스트 4 〉	통과 (5.58ms, 63MB)
 * 테스트 5 〉	통과 (6.05ms, 63.4MB)
 * 테스트 6 〉	통과 (4.92ms, 64.3MB)
 * 테스트 7 〉	통과 (5.07ms, 62.4MB)
 * 테스트 8 〉	통과 (5.03ms, 64.3MB)
 * 테스트 9 〉	통과 (5.07ms, 63.4MB)
 * ```
 * ```
 * RIVAL:
 * class Solution {
 *   fun solution(bin1: String, bin2: String)
 *   = Integer.toBinaryString(bin1.toInt(2) + bin2.toInt(2))
 * }
 *
 * 테스트 1 〉	통과 (2.37ms, 62.1MB)
 * 테스트 2 〉	통과 (0.57ms, 62.5MB)
 * 테스트 3 〉	통과 (0.57ms, 62.7MB)
 * 테스트 4 〉	통과 (0.79ms, 62.4MB)
 * 테스트 5 〉	통과 (0.53ms, 63.4MB)
 * 테스트 6 〉	통과 (0.51ms, 63.7MB)
 * 테스트 7 〉	통과 (0.50ms, 62.3MB)
 * 테스트 8 〉	통과 (0.44ms, 62.6MB)
 * 테스트 9 〉	통과 (0.74ms, 60.8MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("1", "0"), "1")
  validate(s.solution("0", "0"), "0")
  validate(s.solution("1", "1"), "10")
  validate(s.solution("10", "11"), "101")
  validate(s.solution("1001", "1111"), "11000")
  validate(s.solution("10000", "10000"), "100000")
}

//      println("[${ansLen - (it + 1)}] $d1 + $d2 + $caret -> ${a[ansLen - (it + 1)]}")

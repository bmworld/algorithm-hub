package 프로그래머스.Lv2.이진변환반복하기

import util.validate

class Solution {

  val ZERO = '0'
  val INT_BIN = 32
  fun solution(s: String): IntArray {
    var times = 1
    var del = 0
    var len = 0
    for (x in s) if (x == ZERO) del++ else len++

    while (len > 1) {
      val cnt1 = len.countOneBits()
      val cnt0 = INT_BIN - len.countLeadingZeroBits() - cnt1
      del += cnt0
      len = cnt1
      times++
    }

    return intArrayOf(times, del)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 58.3MB)
 * 테스트 2 〉	통과 (2.22ms, 60MB)
 * 테스트 3 〉	통과 (0.02ms, 61.3MB)
 * 테스트 4 〉	통과 (0.02ms, 60MB)
 * 테스트 5 〉	통과 (0.01ms, 59.5MB)
 * 테스트 6 〉	통과 (0.03ms, 58.1MB)
 * 테스트 7 〉	통과 (0.05ms, 59.9MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(s: String): IntArray {
 *
 *         var str = s
 *         var zeroCount = 0
 *         var count = 0
 *
 *         while (str != "1") {
 *             count++
 *             val before = str.length
 *             val after = str.replace("0", "").length
 *             zeroCount += before - after
 *
 *             str = make(after)
 *         }
 *
 *
 *         return intArrayOf(count,zeroCount)
 *     }
 *
 *     fun make(input: Int): String {
 *
 *         var n = input
 *         var ret = ""
 *         while (n != 0) {
 *             ret += n % 2
 *             n /= 2
 *         }
 *
 *         return ret.reversed()
 *     }
 * }
 * 테스트 1 〉	통과 (8.38ms, 61MB)
 * 테스트 2 〉	통과 (17.20ms, 63.7MB)
 * 테스트 3 〉	통과 (8.34ms, 61.2MB)
 * 테스트 4 〉	통과 (8.76ms, 60.2MB)
 * 테스트 5 〉	통과 (9.06ms, 61.4MB)
 * 테스트 6 〉	통과 (9.24ms, 61.2MB)
 * 테스트 7 〉	통과 (8.96ms, 61.9MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("0111010"), intArrayOf(2, 5))
  validate(s.solution("110010101001"), intArrayOf(3, 8))
  validate(s.solution("01110"), intArrayOf(3, 3))
  validate(s.solution("1111111"), intArrayOf(4, 1))
  validate(s.solution("100100100101100000"), intArrayOf(3, 14))
}

//      println("-- [${times}] len=$len, del= $del")

package 프로그래머스.알고리즘고득점Kit.Greedy.조이스틱

import util.validate

class Solution {

  val A = 65
  val ALPHABETS = 26
  fun solution(name: String): Int {
    var ans = 0
    var rmn = 0
    val cap = name.length

    fun left(pos: Int): Int = (if (pos > 0) pos else cap) - 1
    fun right(pos: Int): Int = if (pos + 1 < cap) pos + 1 else 0
    val CNT = IntArray(cap)
    for (i in 0 until cap) {
      val code = name[i].code
      val diff = minOf(code - A, A + ALPHABETS - code)
      CNT[i] = diff.also { if (it > 0) rmn++ }
    }

    var pos = 0
    while (rmn-- > 0) {

      var l = pos
      var ld = 0
      while (CNT[l] == 0) {
        l = left(l)
        ld++
      }
      var r = pos
      var rd = 0
      while (CNT[r] == 0) {
        r = right(r)
        rd++
      }

      pos = if (ld < rd) l else r

      val cnt = CNT[pos]
      if (cnt > 0) {
        CNT[pos] = 0
        ans += cnt + minOf(ld, rd)
      }
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.19ms, 58.8MB)
 * 테스트 2 〉	통과 (0.17ms, 59.2MB)
 * 테스트 3 〉	통과 (0.24ms, 59.3MB)
 * 테스트 4 〉	통과 (0.17ms, 59.6MB)
 * 테스트 5 〉	통과 (0.23ms, 57.8MB)
 * 테스트 6 〉	통과 (0.27ms, 58.1MB)
 * 테스트 7 〉	통과 (0.18ms, 58.9MB)
 * 테스트 8 〉	통과 (0.19ms, 59.1MB)
 * 테스트 9 〉	통과 (0.29ms, 58.6MB)
 * 테스트 10 〉	통과 (0.20ms, 58.9MB)
 * 테스트 11 〉	통과 (0.18ms, 58.2MB)
 * 테스트 12 〉	통과 (0.21ms, 59.4MB)
 * 테스트 13 〉	실패 (0.20ms, 58.7MB)
 * 테스트 14 〉	통과 (0.17ms, 58.2MB)
 * 테스트 15 〉	통과 (0.19ms, 59MB)
 * 테스트 16 〉	통과 (0.17ms, 59.3MB)
 * 테스트 17 〉	통과 (0.20ms, 58.8MB)
 * 테스트 18 〉	실패 (0.17ms, 58.6MB)
 * 테스트 19 〉	통과 (0.20ms, 59.4MB)
 * 테스트 20 〉	통과 (0.16ms, 59.2MB)
 * 테스트 21 〉	통과 (0.18ms, 59MB)
 * 테스트 22 〉	실패 (0.21ms, 58.1MB)
 * 테스트 23 〉	실패 (0.18ms, 59MB)
 * 테스트 24 〉	실패 (0.18ms, 59.3MB)
 * 테스트 25 〉	실패 (0.16ms, 58.6MB)
 * 테스트 26 〉	통과 (0.20ms, 58.9MB)
 * 테스트 27 〉	실패 (0.16ms, 58.5MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("JAZ"), 11)
  validate(s.solution("JEROEN"), 56)
  validate(s.solution("JAN"), 23)
  validate(s.solution("A"), 0)
  validate(s.solution("AAAA"), 0)
}

//      println("---[$rmn] pos = $pos, l=$l ($ld) r=$r($rd) -> cnt = $cnt")

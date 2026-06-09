package 프로그래머스.알고리즘고득점Kit.Greedy.큰수만들기

import util.validate

class Solution {

  val ZERO = 48
  val DIGITS = 10
  fun solution(number: String, k: Int): String {
    val len = number.length - k
    val ans = CharArray(len)
    val cnts = IntArray(DIGITS)

    l@ for (i in 0 until number.length) {
      cnts[number[i].code - ZERO]++
      val j = i - k
      if (j >= 0) {
        for (d in 9 downTo 0) {
          if (cnts[d] > 0) {
            cnts[d]--
            ans[j] = (d + ZERO).toChar()
            continue@l
          }
        }
      }
    }

    return ans.concatToString()
  }
}

/**
 * ```
 * [ME]
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("1924", 2), "94")
  validate(s.solution("1231234", 3), "3234")
  validate(s.solution("4177252841", 4), "775841")
}

//          println("$number[$i] j=$j -> cnts[$d] = ${cnts[d]}")

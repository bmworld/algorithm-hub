package 프로그래머스.Lv1.옹알이2

import util.validate

class Solution {

  val babbling = arrayOf(
    charArrayOf('a', 'y', 'a'),
    charArrayOf('y', 'e'),
    charArrayOf('w', 'o', 'o'),
    charArrayOf('m', 'a')
  )

  fun solution(strs: Array<String>): Int {

    var ans = 0

    loop@ for (str in strs) {
      var i = 0
      var exp: CharArray
      var prv: CharArray? = null
      while (i < str.length) {
        i += when (str[i]) {
          'a' -> {
            exp = babbling[0]
            if (!validate(i, str, exp, prv)) continue@loop
            exp.size
          }
          'y' -> {
            exp = babbling[1]
            if (!validate(i, str, exp, prv)) continue@loop
            exp.size
          }
          'w' -> {
            exp = babbling[2]
            if (!validate(i, str, exp, prv)) continue@loop
            exp.size
          }
          'm' -> {
            exp = babbling[3]
            if (!validate(i, str, exp, prv)) continue@loop
            exp.size
          }
          else -> continue@loop
        }

        prv = exp
      }

      ans++
    }
    return ans
  }

  fun validate(stt: Int, act: String, exp: CharArray, prv: CharArray?): Boolean {
    if (prv != null && prv[0] == act[stt]) return false

    for (i in 1 until exp.size) {
      val ai = stt + i
      if (ai >= act.length || act[ai] != exp[i]) return false
    }

    return true
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 58.9MB)
 * 테스트 2 〉	통과 (0.02ms, 57.8MB)
 * 테스트 3 〉	통과 (0.02ms, 58.2MB)
 * 테스트 4 〉	통과 (0.01ms, 58.4MB)
 * 테스트 5 〉	통과 (0.02ms, 57.8MB)
 * 테스트 6 〉	통과 (0.02ms, 58.4MB)
 * 테스트 7 〉	통과 (0.02ms, 58MB)
 * 테스트 8 〉	통과 (0.02ms, 58.3MB)
 * 테스트 9 〉	통과 (0.01ms, 59.4MB)
 * 테스트 10 〉	통과 (0.02ms, 59MB)
 * 테스트 11 〉	통과 (0.02ms, 59.5MB)
 * 테스트 12 〉	통과 (0.13ms, 59.3MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(병신소리: Array<String>) = 병신소리.count { it.matches("^(aya(?!aya)|ye(?!ye)|woo(?!woo)|ma(?!ma))+$".toRegex()) }
 * }
 * 테스트 1 〉	통과 (1.19ms, 59.2MB)
 * 테스트 2 〉	통과 (1.77ms, 60.2MB)
 * 테스트 3 〉	통과 (1.29ms, 58MB)
 * 테스트 4 〉	통과 (1.08ms, 60MB)
 * 테스트 5 〉	통과 (1.15ms, 59.1MB)
 * 테스트 6 〉	통과 (1.44ms, 59.6MB)
 * 테스트 7 〉	통과 (1.10ms, 59.1MB)
 * 테스트 8 〉	통과 (1.22ms, 59.2MB)
 * 테스트 9 〉	통과 (1.45ms, 58.9MB)
 * 테스트 10 〉	통과 (1.22ms, 58.3MB)
 * 테스트 11 〉	통과 (1.22ms, 58MB)
 * 테스트 12 〉	통과 (3.36ms, 59MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf(
    "ayayeaya", "yewooye"
  )), 2)
  validate(s.solution(arrayOf(
    "yeye"
  )), 0)

  validate(s.solution(arrayOf(
    "aya", "yee", "u", "maa"
  )), 1)

  validate(s.solution(arrayOf(
    "ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"
  )), 2)

  validate(s.solution(arrayOf(
    "maaya", "ayama", "ayma"
  )), 2)

  validate(s.solution(arrayOf(
    "aya", "yee", "u", "mama", "wyeoo"
  )), 1)

  validate(s.solution(arrayOf(
    "ayaye", "uuuma", "ye", "yemawoo", "ayaa")), 3)

  validate(s.solution(arrayOf(
    "aya", "ayaaya", "ayayewooma")), 2)
}

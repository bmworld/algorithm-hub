package 프로그래머스.Lv1.옹알이2

import util.validate

class Solution {

  val REMOVED = ' '

  fun solution(strs: Array<String>): Int {

    var ans = 0

    loop@ for (str in strs) {
      val cnds = arrayOf(
        charArrayOf('a', 'y', 'a'),
        charArrayOf('y', 'e'),
        charArrayOf('w', 'o', 'o'),
        charArrayOf('m', 'a')
      )

      var i = 0
      while (i < str.length) {
        i += when (str[i]) {
          'a' -> {
            val exp = cnds[0]
            if (!validate(str, exp, i)) continue@loop
            exp.size
          }
          'y' -> {
            val exp = cnds[1]
            if (!validate(str, exp, i)) continue@loop
            exp.size
          }
          'w' -> {
            val exp = cnds[2]
            if (!validate(str, exp, i)) continue@loop
            exp.size
          }
          'm' -> {
            val exp = cnds[3]
            if (!validate(str, exp, i)) continue@loop
            exp.size
          }
          else -> continue@loop
        }
      }

      ans++
    }
    return ans
  }

  fun validate(act: String, exp: CharArray, stt: Int): Boolean {
    var r = true

    exp[0] = REMOVED
    val actLen = act.length
    for (i in 1 until exp.size) {
      val ai = stt + i
      if (ai < actLen && act[ai] == exp[i]) {
        exp[i] = REMOVED
      } else {
        r = false
        break
      }
    }

    return r
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	실패 (0.03ms, 57.5MB)
 * 테스트 2 〉	통과 (0.02ms, 57.6MB)
 * 테스트 3 〉	통과 (0.02ms, 58MB)
 * 테스트 4 〉	통과 (0.03ms, 59.1MB)
 * 테스트 5 〉	통과 (0.02ms, 58.7MB)
 * 테스트 6 〉	통과 (0.02ms, 60MB)
 * 테스트 7 〉	통과 (0.02ms, 58.6MB)
 * 테스트 8 〉	통과 (0.05ms, 59.7MB)
 * 테스트 9 〉	통과 (0.02ms, 59.3MB)
 * 테스트 10 〉	통과 (0.03ms, 58.2MB)
 * 테스트 11 〉	실패 (0.03ms, 60MB)
 * 테스트 12 〉	통과 (0.11ms, 59.3MB)
 * 테스트 13 〉	통과 (0.10ms, 58.4MB)
 * 테스트 14 〉	실패 (0.09ms, 59.3MB)
 * 테스트 15 〉	통과 (0.05ms, 58.5MB)
 * 테스트 16 〉	실패 (0.05ms, 59.5MB)
 * 테스트 17 〉	실패 (0.11ms, 59MB)
 * 테스트 18 〉	통과 (0.08ms, 58.4MB)
 * 테스트 19 〉	통과 (0.04ms, 58.3MB)
 * 테스트 20 〉	통과 (0.06ms, 59.3MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
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

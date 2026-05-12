package 프로그래머스.입문.Day23.옹알이1

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

/**
 * ME:
 * 테스트 1 〉	통과 (0.07ms, 62.4MB)
 * 테스트 2 〉	통과 (0.13ms, 62.2MB)
 * 테스트 3 〉	통과 (0.06ms, 65.1MB)
 * 테스트 4 〉	통과 (0.12ms, 64.4MB)
 * 테스트 5 〉	통과 (0.06ms, 63.7MB)
 * 테스트 6 〉	통과 (0.08ms, 62.7MB)
 * 테스트 7 〉	통과 (0.09ms, 62.6MB)
 * 테스트 8 〉	통과 (0.09ms, 61.9MB)
 * 테스트 9 〉	통과 (0.07ms, 63.7MB)
 * 테스트 10 〉	통과 (0.04ms, 63.9MB)
 * 테스트 11 〉	통과 (0.03ms, 63.9MB)
 * 테스트 12 〉	통과 (0.04ms, 62.6MB)
 * 테스트 13 〉	통과 (0.03ms, 63.9MB)
 * 테스트 14 〉	통과 (0.03ms, 61.9MB)
 * 테스트 15 〉	통과 (0.03ms, 62.3MB)
 * 테스트 16 〉	통과 (0.03ms, 63.6MB)
 * 테스트 17 〉	통과 (0.04ms, 62.3MB)
 */

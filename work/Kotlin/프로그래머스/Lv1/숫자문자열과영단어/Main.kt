package 프로그래머스.Lv1.숫자문자열과영단어

import util.validate

class Solution {

  val ZERO = 48
  val NUM = ZERO..57
  fun solution(s: String): Int {
    var ans = 0
    var i = 0
    while (i < s.length) {
      val c = s[i]

      var move = 0
      ans = ans * 10 + when {
        c.code in NUM -> {
          move = 1
          c.code - ZERO
        }
        c == 'z' -> {
          move = 4
          0
        }
        c == 'o' -> {
          move = 3
          1
        }
        c == 't' -> if (s[i + 1] == 'w') {
          move = 3
          2
        } else {
          move = 5
          3
        }
        c == 'f' -> if (s[i + 1] == 'o') {
          move = 4
          4
        } else {
          move = 4
          5
        }
        c == 's' -> if (s[i + 1] == 'i') {
          move = 3
          6
        } else {
          move = 5
          7
        }
        c == 'e' -> {
          move = 5
          8
        }
        c == 'n' -> {
          move = 4
          9
        }
        else -> break
      }

      i += move
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 58.3MB)
 * 테스트 2 〉	통과 (0.01ms, 59MB)
 * 테스트 3 〉	통과 (0.01ms, 58.3MB)
 * 테스트 4 〉	통과 (0.01ms, 60.2MB)
 * 테스트 5 〉	통과 (0.02ms, 59.1MB)
 * 테스트 6 〉	통과 (0.01ms, 59.7MB)
 * 테스트 7 〉	통과 (0.01ms, 58.1MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(s: String): Int = s
 *         .replace("one", "1")
 *         .replace("two", "2")
 *         .replace("three", "3")
 *         .replace("four", "4")
 *         .replace("five", "5")
 *         .replace("six", "6")
 *         .replace("seven", "7")
 *         .replace("eight", "8")
 *         .replace("nine", "9")
 *         .replace("zero", "0")
 *         .toInt()
 * }
 * 테스트 1 〉	통과 (5.32ms, 61.1MB)
 * 테스트 2 〉	통과 (5.11ms, 60.2MB)
 * 테스트 3 〉	통과 (5.09ms, 60.1MB)
 * 테스트 4 〉	통과 (5.10ms, 59.9MB)
 * 테스트 5 〉	통과 (5.13ms, 59.6MB)
 * 테스트 6 〉	통과 (5.10ms, 60MB)
 * 테스트 7 〉	통과 (5.10ms, 61MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("1two3four567890"), 1234567890)
  validate(s.solution("onetwothreefourfivesixseveneightninezero"), 1234567890)
  validate(s.solution("23four5six7"), 234567)

}

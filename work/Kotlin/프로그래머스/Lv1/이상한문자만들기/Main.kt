package 프로그래머스.Lv1.이상한문자만들기

import util.validate

class Solution {

  val INIT = 0
  val SPACE = 32
  val a = 97
  val ALPHABETS = 26
  val BETWEEN_UPPER_AND_LOWER = 32
  val LOWERCASE = a until a + ALPHABETS
  fun solution(s: String): String {
    val ans = s.toCharArray()

    var i = INIT
    for (j in 0 until s.length) {
      val code = s[j].code
      when (code) {
        SPACE -> i = INIT
        else -> {
          val even = i % 2 == 0
          if (even && code in LOWERCASE) ans[j] = (code - BETWEEN_UPPER_AND_LOWER).toChar()
          else if (!even && code !in LOWERCASE) ans[j] = (code + BETWEEN_UPPER_AND_LOWER).toChar()

          i++
        }
      }
    }
    return String(ans)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.8MB)
 * 테스트 2 〉	통과 (0.02ms, 58.8MB)
 * 테스트 3 〉	통과 (0.02ms, 58.1MB)
 * 테스트 4 〉	통과 (0.03ms, 58.7MB)
 * 테스트 5 〉	통과 (0.02ms, 59.4MB)
 * 테스트 6 〉	통과 (0.02ms, 59.9MB)
 * 테스트 7 〉	통과 (0.02ms, 58.2MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(s: String): String {
 *         var blankIndex = 0
 *         var answer = ""
 *         for(i in 0 until s.length) {
 *             if(s[i] == ' ') blankIndex = i + 1
 *             answer += getChar(s[i], i - blankIndex)
 *         }
 *         return answer
 *     }
 *
 *     fun getChar(c: Char, index: Int): Char {
 *         if(c == ' ') return c
 *         if(index % 2 == 1) {
 *             if(97 <= c.toInt() && 122 >= c.toInt()) return c
 *             else return (c.toInt() + 32).toChar()
 *         } else {
 *             if(65 <= c.toInt() && 90 >= c.toInt()) return c
 *             else return (c.toInt() - 32).toChar()
 *         }
 *     }
 * }
 * 테스트 1 〉	통과 (3.93ms, 58.2MB)
 * 테스트 2 〉	통과 (2.90ms, 58.5MB)
 * 테스트 3 〉	통과 (3.36ms, 57.6MB)
 * 테스트 4 〉	통과 (3.47ms, 60.1MB)
 * 테스트 5 〉	통과 (2.95ms, 58.4MB)
 * 테스트 6 〉	통과 (2.68ms, 59.5MB)
 * 테스트 7 〉	통과 (3.35ms, 58.5MB)
 * 테스트 8 〉	통과 (3.01ms, 59.8MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("try hello world"), "TrY HeLlO WoRlD")
  validate(s.solution("hellow z zk"), "HeLlOw Z Zk")
}

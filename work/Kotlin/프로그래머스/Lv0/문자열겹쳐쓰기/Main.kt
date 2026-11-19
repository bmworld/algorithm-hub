package 프로그래머스.Lv0.문자열겹쳐쓰기

import util.validate

class Solution {

  fun solution(my_string: String, overwrite_string: String, s: Int): String {
    val ans = my_string.toCharArray()
    val N = my_string.length
    val M = overwrite_string.length
    for (i in 0 until M) if (s + i < N) ans[s + i] = overwrite_string[i] else break

    return String(ans)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.5MB)
 * 테스트 2 〉	통과 (0.02ms, 59.8MB)
 * 테스트 3 〉	통과 (0.02ms, 58.8MB)
 * 테스트 4 〉	통과 (0.02ms, 59.9MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(myString: String, overwriteString: String, s: Int): String =
 *         myString.replaceRange(s, s + overwriteString.length, overwriteString)
 * }
 * 테스트 1 〉	통과 (4.90ms, 60.4MB)
 * 테스트 2 〉	통과 (5.13ms, 60.6MB)
 * 테스트 3 〉	통과 (5.72ms, 61.4MB)
 * 테스트 4 〉	통과 (5.14ms, 62MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(my_string: String, overwrite_string: String, s: Int): String {
 *         var answer: String = ""
 *
 *         for (i in 0 until my_string.length) {
 *             if (i >= s && i < s + overwrite_string.length) {
 *                 answer += overwrite_string[i-s]
 *             } else {
 *                 answer += my_string[i]
 *             }
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (2.71ms, 57.8MB)
 * 테스트 2 〉	통과 (2.75ms, 61.7MB)
 * 테스트 3 〉	통과 (3.45ms, 59.7MB)
 * 테스트 4 〉	통과 (2.99ms, 59.8MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("He11oWor1d", "lloWorl", 2), "HelloWorld")
  validate(s.solution("Program29b8UYP", "merS123", 7), "ProgrammerS123")
  validate(s.solution("Program1111", "9", 7), "Program9111")
  validate(s.solution("Program1111", "zzzzzzzzzzzzzzzzz", 7), "Programzzzz")
}

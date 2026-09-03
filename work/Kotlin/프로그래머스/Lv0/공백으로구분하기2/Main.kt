package 프로그래머스.Lv0.공백으로구분하기2

import util.validate

class Solution {
  companion object {

    const val MAX_LEN = 1_000
    const val SPACE = 32
  }

  fun solution(my_string: String): Array<String> {
    val tmp = Array(MAX_LEN / 2) { "" }
    var ai = 0

    val word = CharArray(MAX_LEN)
    var wi = 0
    for (x in my_string) {
      when (x.code) {
        SPACE -> if (wi > 0) {
          tmp[ai++] = String(word, 0, wi)
          wi = 0
        }
        else -> word[wi++] = x
      }
    }
    if (wi > 0) tmp[ai++] = String(word, 0, wi)

    return Array(ai) { tmp[it] }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.13ms, 60.1MB)
 * 테스트 2 〉	통과 (0.07ms, 59.8MB)
 * 테스트 3 〉	통과 (0.06ms, 57.9MB)
 * 테스트 4 〉	통과 (0.09ms, 60.4MB)
 * 테스트 5 〉	통과 (0.07ms, 57.7MB)
 * 테스트 6 〉	통과 (0.07ms, 59.5MB)
 * 테스트 7 〉	통과 (0.07ms, 60.3MB)
 * 테스트 8 〉	통과 (0.10ms, 60.1MB)
 * v2:
 * 테스트 1 〉	통과 (0.13ms, 59.5MB)
 * 테스트 2 〉	통과 (0.07ms, 60.6MB)
 * 테스트 3 〉	통과 (0.05ms, 60.2MB)
 * 테스트 4 〉	통과 (0.08ms, 60MB)
 * 테스트 5 〉	통과 (0.06ms, 60.4MB)
 * 테스트 6 〉	통과 (0.06ms, 60.6MB)
 * 테스트 7 〉	통과 (0.06ms, 60.4MB)
 * 테스트 8 〉	통과 (0.09ms, 60.4MB)
 *
 * [RIVAL]
 * class Solution {
 *     fun solution(myString: String): List<String> {
 *         return myString.trim().split("\\s+".toRegex())
 *     }
 * }
 * 테스트 1 〉	통과 (6.23ms, 60.9MB)
 * 테스트 2 〉	통과 (6.19ms, 61.3MB)
 * 테스트 3 〉	통과 (5.83ms, 61.4MB)
 * 테스트 4 〉	통과 (6.72ms, 61.3MB)
 * 테스트 5 〉	통과 (6.12ms, 61.1MB)
 * 테스트 6 〉	통과 (5.98ms, 60.7MB)
 * 테스트 7 〉	통과 (6.37ms, 60.6MB)
 * 테스트 8 〉	통과 (6.00ms, 61.2MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(" i    love  you"), arrayOf("i", "love", "you"))
  validate(s.solution("    programmers  "), arrayOf("programmers"))
  validate(s.solution("   i  s "), arrayOf("i", "s"))
  validate(s.solution("i kk "), arrayOf("i", "kk"))
  validate(s.solution(" hello "), arrayOf("hello"))
}

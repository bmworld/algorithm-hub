package 프로그래머스.Lv0.문자열뒤집기

import util.validate

class Solution {

  fun solution(str: String, s: Int, e: Int): String =
    String(CharArray(str.length) {
      when {
        it in s..e -> str[s + (e - it)]
        else -> str[it]
      }
    })
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.6MB)
 * 테스트 2 〉	통과 (0.02ms, 59.6MB)
 * 테스트 3 〉	통과 (0.04ms, 58.7MB)
 * 테스트 4 〉	통과 (0.02ms, 59.9MB)
 * 테스트 5 〉	통과 (0.02ms, 58.6MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(myString: String, s: Int, e: Int) = myString.substring(0, s) + myString.substring(s..e).reversed() + myString.substring(e + 1, myString.length)
 * }
 * 테스트 1 〉	통과 (8.56ms, 60.9MB)
 * 테스트 2 〉	통과 (8.15ms, 60.1MB)
 * 테스트 3 〉	통과 (8.39ms, 60.4MB)
 * 테스트 4 〉	통과 (9.07ms, 60.3MB)
 * 테스트 5 〉	통과 (9.19ms, 59.8MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(my_string: String, s: Int, e: Int) = buildString {
 *         val arr = my_string.toCharArray()
 *         for (i in 0 until s){ append(arr[i]) }
 *         for (i in e downTo s ){ append(arr[i]) }
 *         for (i in e+1 until arr.size){ append(arr[i]) }
 *     }
 * }
 * 테스트 1 〉	통과 (0.07ms, 60.1MB)
 * 테스트 2 〉	통과 (0.03ms, 59.5MB)
 * 테스트 3 〉	통과 (0.03ms, 60.7MB)
 * 테스트 4 〉	통과 (0.03ms, 59.8MB)
 * 테스트 5 〉	통과 (0.03ms, 59.6MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("Progra21Sremm3", 6, 12), "ProgrammerS123")
  validate(s.solution("Stanley1yelnatS", 4, 10), "Stanley1yelnatS")
  validate(s.solution("abcde", 1, 1), "abcde")
  validate(s.solution("abcde", 1, 2), "acbde")
}

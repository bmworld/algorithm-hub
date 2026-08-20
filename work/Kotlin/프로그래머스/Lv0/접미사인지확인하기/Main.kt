package 프로그래머스.Lv0.접미사인지확인하기

import util.validate

class Solution {

  fun solution(my_string: String, is_suffix: String): Int {
    val N = my_string.length
    val M = is_suffix.length
    if (N < M) return 0

    val diff = N - M
    for (i in M - 1 downTo 0) if (is_suffix[i] != my_string[i + diff]) return 0

    return 1
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 59.4MB)
 * 테스트 2 〉	통과 (0.01ms, 60.1MB)
 * 테스트 3 〉	통과 (0.01ms, 60.1MB)
 * 테스트 4 〉	통과 (0.01ms, 60.4MB)
 * 테스트 5 〉	통과 (0.01ms, 59.8MB)
 * 테스트 6 〉	통과 (0.01ms, 59.7MB)
 * 테스트 7 〉	통과 (0.01ms, 60MB)
 * 테스트 8 〉	통과 (0.01ms, 59.6MB)
 * 테스트 9 〉	통과 (0.01ms, 59.7MB)
 * 테스트 10 〉	통과 (0.01ms, 60.3MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(myString: String, isSuffix: String) = if (myString.endsWith(isSuffix)) 1 else 0
 * }
 * 테스트 1 〉	통과 (4.62ms, 59.5MB)
 * 테스트 2 〉	통과 (4.11ms, 60.9MB)
 * 테스트 3 〉	통과 (4.91ms, 61.2MB)
 * 테스트 4 〉	통과 (4.45ms, 61MB)
 * 테스트 5 〉	통과 (5.63ms, 60.8MB)
 * 테스트 6 〉	통과 (5.33ms, 58.7MB)
 * 테스트 7 〉	통과 (4.26ms, 60.1MB)
 * 테스트 8 〉	통과 (4.12ms, 60.8MB)
 * 테스트 9 〉	통과 (5.89ms, 60.3MB)
 * 테스트 10 〉	통과 (4.17ms, 60.8MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("banana", "ana"), 1)
  validate(s.solution("banana", "nan"), 0)
  validate(s.solution("banana", "xva"), 0)
  validate(s.solution("banana", "abanana"), 0)
  validate(s.solution("banana", "bananz"), 0)
  validate(s.solution("banana", "aanana"), 0)
}

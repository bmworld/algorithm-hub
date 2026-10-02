package 프로그래머스.Lv0.접두사인지확인하기

import util.validate

class Solution {

  fun solution(str: String, prfx: String): Int {
    if (prfx.length > str.length) return 0
    for (i in prfx.indices)
      if (str[i] != prfx[i]) return 0

    return 1
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 58.1MB)
 * 테스트 2 〉	통과 (0.01ms, 57.8MB)
 * 테스트 3 〉	통과 (0.01ms, 57.9MB)
 * 테스트 4 〉	통과 (0.01ms, 57.9MB)
 * 테스트 5 〉	통과 (0.02ms, 59.7MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(my_string: String, is_prefix: String): Int = if (my_string.startsWith(is_prefix)) 1 else 0
 * }
 * 테스트 1 〉	통과 (4.06ms, 60.6MB)
 * 테스트 2 〉	통과 (3.99ms, 60.4MB)
 * 테스트 3 〉	통과 (4.10ms, 60.2MB)
 * 테스트 4 〉	통과 (4.40ms, 60.7MB)
 * 테스트 5 〉	통과 (4.56ms, 60.3MB)
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("banana", "b"), 1)
  validate(s.solution("banana", "ba"), 1)
  validate(s.solution("banana", "banana"), 1)
  validate(s.solution("banana", "bananan"), 0)
  validate(s.solution("banana", "c"), 0)
  validate(s.solution("banana", "baa"), 0)
}

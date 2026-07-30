package 프로그래머스.Lv0.카운트업

import util.validate

class Solution {

  fun solution(start_num: Int, end_num: Int): IntArray =
    IntArray(end_num - start_num + 1) { start_num + it }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 60.2MB)
 * 테스트 2 〉	통과 (0.01ms, 59.2MB)
 * 테스트 3 〉	통과 (0.01ms, 60.6MB)
 * 테스트 4 〉	통과 (0.01ms, 59.9MB)
 * 테스트 5 〉	통과 (0.01ms, 59.4MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(start: Int, end: Int): IntArray = (start..end).toList().toIntArray()
 * }
 * 테스트 1 〉	통과 (5.22ms, 60.8MB)
 * 테스트 2 〉	통과 (4.97ms, 60.8MB)
 * 테스트 3 〉	통과 (5.45ms, 59.4MB)
 * 테스트 4 〉	통과 (5.26ms, 60.9MB)
 * 테스트 5 〉	통과 (4.86ms, 61.1MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(3, 10),
    intArrayOf(3, 4, 5, 6, 7, 8, 9, 10)
  )
}

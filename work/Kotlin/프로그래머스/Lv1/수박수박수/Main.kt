package 프로그래머스.Lv1.수박수박수

import util.validate

class Solution {

  fun solution(n: Int): String = String(CharArray(n) { if (it % 2 == 0) '수' else '박' })
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.08ms, 58.6MB)
 * 테스트 2 〉	통과 (0.22ms, 57.8MB)
 * 테스트 3 〉	통과 (0.17ms, 59.4MB)
 * 테스트 4 〉	통과 (0.24ms, 59.8MB)
 * 테스트 5 〉	통과 (0.19ms, 59.7MB)
 * ```
 *
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(3), "수박수")
  validate(s.solution(4), "수박수박")
}

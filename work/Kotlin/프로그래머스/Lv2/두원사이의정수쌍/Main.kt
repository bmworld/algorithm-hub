package 프로그래머스.Lv2.두원사이의정수쌍

import util.validate

class Solution {

  fun solution(r1: Int, r2: Int): Long {
    val ract2 = r2 * 2L - 1
    val ract1 = r1 * 2L - 1
    return ract2 * ract2 + 4 - ract1 * ract1
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	실패 (0.02ms, 60.6MB)
 * 테스트 2 〉	실패 (0.02ms, 60.6MB)
 * 테스트 3 〉	실패 (0.02ms, 61MB)
 * 테스트 4 〉	실패 (0.03ms, 60.6MB)
 * 테스트 5 〉	실패 (0.02ms, 58.1MB)
 * 테스트 6 〉	실패 (0.02ms, 60.4MB)
 * 테스트 7 〉	실패 (0.02ms, 59.8MB)
 * 테스트 8 〉	실패 (0.02ms, 58.3MB)
 * 테스트 9 〉	실패 (0.01ms, 60.4MB)
 * 테스트 10 〉	실패 (0.02ms, 60.2MB)
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(1, 2), 12)
  validate(s.solution(2, 3), 20)
  validate(s.solution(1, 1_000_000), 3_999_996_000_004)
}

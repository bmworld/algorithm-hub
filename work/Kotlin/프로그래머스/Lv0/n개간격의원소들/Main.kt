package 프로그래머스.Lv0.n개간격의원소들

import util.validate

class Solution {

  fun solution(num_list: IntArray, n: Int): IntArray =
    IntArray((num_list.size + n - 1) / n) { num_list[it * n] }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 61.6MB)
 * 테스트 2 〉	통과 (0.01ms, 59.9MB)
 * 테스트 3 〉	통과 (0.01ms, 58.8MB)
 * 테스트 4 〉	통과 (0.01ms, 59MB)
 * 테스트 5 〉	통과 (0.01ms, 60.9MB)
 * 테스트 6 〉	통과 (0.01ms, 60MB)
 * 테스트 7 〉	통과 (0.01ms, 57.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(numList: IntArray, n: Int) = (numList.indices step n).map { numList[it] }
 * }
 * 테스트 1 〉	통과 (14.36ms, 63.7MB)
 * 테스트 2 〉	통과 (13.74ms, 62.4MB)
 * 테스트 3 〉	통과 (14.72ms, 64.2MB)
 * 테스트 4 〉	통과 (15.76ms, 64.5MB)
 * 테스트 5 〉	통과 (13.94ms, 64.2MB)
 * 테스트 6 〉	통과 (13.84ms, 63.6MB)
 * 테스트 7 〉	통과 (13.88ms, 63.7MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(4, 2, 6, 1, 7, 6), 2), intArrayOf(4, 6, 7))
  validate(s.solution(intArrayOf(4, 2, 6, 1, 7, 6), 4), intArrayOf(4, 7))

}

package 프로그래머스.Lv0.배열의원소만큼추가하기

import util.validate

class Solution {
  companion object {

    const val MAX = 10_000
  }

  fun solution(arr: IntArray): IntArray {
    val tmp = IntArray(MAX)
    var len = 0
    for (x in arr) {
      repeat(x) {
        tmp[len++] = x
      }
    }

    return IntArray(len) { tmp[it] }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.03ms, 59.7MB)
 * 테스트 2 〉	통과 (0.04ms, 60.6MB)
 * 테스트 3 〉	통과 (0.03ms, 60.6MB)
 * 테스트 4 〉	통과 (0.05ms, 61.1MB)
 * 테스트 5 〉	통과 (0.07ms, 61.5MB)
 * 테스트 6 〉	통과 (0.06ms, 60.1MB)
 * 테스트 7 〉	통과 (0.11ms, 62MB)
 * 테스트 8 〉	통과 (0.09ms, 62MB)
 * 테스트 9 〉	통과 (0.17ms, 59.8MB)
 * 테스트 10 〉	통과 (0.15ms, 60.9MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(arr: IntArray): List<Int> {
 *         return arr.flatMap { i -> List(i) { i } }
 *     }
 * }
 * 테스트 1 〉	통과 (3.41ms, 60.4MB)
 * 테스트 2 〉	통과 (3.10ms, 60.2MB)
 * 테스트 3 〉	통과 (3.15ms, 60.3MB)
 * 테스트 4 〉	통과 (3.30ms, 61.3MB)
 * 테스트 5 〉	통과 (3.29ms, 61.1MB)
 * 테스트 6 〉	통과 (3.18ms, 60.2MB)
 * 테스트 7 〉	통과 (3.43ms, 61.9MB)
 * 테스트 8 〉	통과 (3.40ms, 62.9MB)
 * 테스트 9 〉	통과 (3.66ms, 61.8MB)
 * 테스트 10 〉	통과 (3.35ms, 61.6MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(5, 1, 4)), intArrayOf(5, 5, 5, 5, 5, 1, 4, 4, 4, 4))
  validate(s.solution(intArrayOf(1, 1, 4)), intArrayOf(1, 1, 4, 4, 4, 4))
}

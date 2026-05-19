package 프로그래머스.알고리즘고득점Kit.해시.의상

import util.validate

class Solution {

  val NOT_SELECTED_CASE = 1
  fun solution(clothes: Array<Array<String>>): Int {
    val map = HashMap<String, Int>()

    for (c in clothes) {
      val ctgr = c[1]
      val cnt = map[ctgr] ?: 0
      map[ctgr] = cnt + 1
    }

    var ans = 1
    for (e in map) ans *= e.value + 1
    return ans - NOT_SELECTED_CASE
  }
}

/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (0.06ms, 63.6MB)
 * 테스트 2 〉	통과 (0.06ms, 61.8MB)
 * 테스트 3 〉	통과 (0.07ms, 62MB)
 * 테스트 4 〉	통과 (0.10ms, 62.4MB)
 * 테스트 5 〉	통과 (0.10ms, 62.2MB)
 * 테스트 6 〉	통과 (0.10ms, 62.2MB)
 * 테스트 7 〉	통과 (0.11ms, 62.2MB)
 * 테스트 8 〉	통과 (0.06ms, 63.1MB)
 * 테스트 9 〉	통과 (0.11ms, 64MB)
 * 테스트 10 〉	통과 (0.06ms, 63.1MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * class Solution {
 *     fun solution(clothes: Array<Array<String>>): Int {
 *         return clothes.groupBy { it[1] }.values.fold(1) { acc, v -> acc * (v.size + 1) }  - 1
 *     }
 * }
 * 테스트 1 〉	통과 (0.14ms, 64.3MB)
 * 테스트 2 〉	통과 (0.13ms, 63.2MB)
 * 테스트 3 〉	통과 (0.13ms, 63.8MB)
 * 테스트 4 〉	통과 (0.15ms, 63.7MB)
 * 테스트 5 〉	통과 (0.16ms, 63.4MB)
 * 테스트 6 〉	통과 (0.13ms, 65MB)
 * 테스트 7 〉	통과 (0.14ms, 63.1MB)
 * 테스트 8 〉	통과 (0.17ms, 62MB)
 * 테스트 9 〉	통과 (0.15ms, 64.3MB)
 * 테스트 10 〉	통과 (0.15ms, 63.1MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf(
    arrayOf("yellow_hat", "headgear"),
    arrayOf("blue_sunglasses", "eyewear"),
    arrayOf("green_turban", "headgear"),
  )), 5)

  validate(s.solution(arrayOf(
    arrayOf("crow_mask", "face"),
    arrayOf("blue_sunglasses", "face"),
    arrayOf("smoky_makeup", "face"),
  )), 3)


}

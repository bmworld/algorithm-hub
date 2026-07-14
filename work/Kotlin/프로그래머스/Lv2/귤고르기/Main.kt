package 프로그래머스.Lv2.귤고르기

import util.validate

class Solution {

  fun solution(k: Int, tangerine: IntArray): Int {
    val map = HashMap<Int, Int>()
    var maxCnt = 0
    for (x in tangerine) map[x] = ((map[x] ?: 0) + 1).also { if (it > maxCnt) maxCnt = it }

    var ans = 0
    var picked = 0

    val cnts = IntArray(maxCnt + 1)
    for (x in map) cnts[x.value]++

    for (cnt in maxCnt downTo 1) {
      var t = cnts[cnt]
      if (t == 0) continue
      val cap = cnt * t
      val rmn = k - picked
      if (cap > rmn) t = (rmn + cnt - 1) / cnt

      picked += cnt * t
      ans += t
      if (picked >= k) break
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * v1:
 * 테스트 1 〉	통과 (8.72ms, 68.2MB)
 * 테스트 2 〉	통과 (11.52ms, 66.8MB)
 * 테스트 3 〉	통과 (8.83ms, 67.6MB)
 * 테스트 4 〉	통과 (8.90ms, 69.4MB)
 * 테스트 5 〉	통과 (9.19ms, 65.7MB)
 * 테스트 6 〉	통과 (8.80ms, 64.5MB)
 * 테스트 7 〉	통과 (11.03ms, 67.3MB)
 * 테스트 8 〉	통과 (9.50ms, 66.4MB)
 * 테스트 9 〉	통과 (9.56ms, 66.7MB)
 * 테스트 10 〉	통과 (14.83ms, 67.1MB)
 * v2:
 * 테스트 1 〉	통과 (12.15ms, 66.7MB)
 * 테스트 2 〉	통과 (13.50ms, 68.8MB)
 * 테스트 3 〉	통과 (11.52ms, 66.6MB)
 * 테스트 4 〉	통과 (12.19ms, 67.6MB)
 * 테스트 5 〉	통과 (9.12ms, 66.4MB)
 * 테스트 6 〉	통과 (9.09ms, 65.6MB)
 * 테스트 7 〉	통과 (9.96ms, 66.9MB)
 * 테스트 8 〉	통과 (9.78ms, 68.3MB)
 * 테스트 9 〉	통과 (10.21ms, 67.1MB)
 * 테스트 10 〉	통과 (10.12ms, 69.2MB)
 * v3:
 * 테스트 1 〉	통과 (10.57ms, 67.8MB)
 * 테스트 2 〉	통과 (9.49ms, 67.4MB)
 * 테스트 3 〉	통과 (10.00ms, 68.5MB)
 * 테스트 4 〉	통과 (10.85ms, 69.3MB)
 * 테스트 5 〉	통과 (8.97ms, 66.5MB)
 * 테스트 6 〉	통과 (10.40ms, 65.7MB)
 * 테스트 7 〉	통과 (12.73ms, 67.2MB)
 * 테스트 8 〉	통과 (12.57ms, 67.5MB)
 * 테스트 9 〉	통과 (11.86ms, 66MB)
 * 테스트 10 〉	통과 (9.94ms, 68MB)
 *
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(k: Int, tangerine: IntArray): Int {
 *         var answer: Int = 0
 *         var limit = 0
 *         tangerine.groupBy { it }.toList().sortedByDescending { it.second.size }.forEach{
 *         if(limit >= k){
 *             return answer
 *         }
 *         limit += it.second.size
 *         answer++
 *     }
 *
 *     return answer
 *     }
 * }
 * 테스트 1 〉	통과 (23.70ms, 73.1MB)
 * 테스트 2 〉	통과 (27.42ms, 72.9MB)
 * 테스트 3 〉	통과 (23.82ms, 73.5MB)
 * 테스트 4 〉	통과 (21.69ms, 74.7MB)
 * 테스트 5 〉	통과 (22.74ms, 70.1MB)
 * 테스트 6 〉	통과 (22.31ms, 71.2MB)
 * 테스트 7 〉	통과 (23.85ms, 72.6MB)
 * 테스트 8 〉	통과 (23.87ms, 71.4MB)
 * 테스트 9 〉	통과 (23.24ms, 71.2MB)
 * 테스트 10 〉	통과 (23.09ms, 72.5MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(6, intArrayOf(1, 3, 2, 5, 4, 5, 2, 3)), 3)
  validate(s.solution(4, intArrayOf(1, 3, 2, 5, 4, 5, 2, 3)), 2)
  validate(s.solution(2, intArrayOf(1, 1, 1, 1, 2, 2, 2, 3)), 1)

}

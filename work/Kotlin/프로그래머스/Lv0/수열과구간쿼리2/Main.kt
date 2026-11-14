package 프로그래머스.Lv0.수열과구간쿼리2

import util.validate


class Solution {
  companion object {

    const val INF = Int.MAX_VALUE
  }

  fun solution(arr: IntArray, qs: Array<IntArray>): IntArray {
    val ans = IntArray(qs.size) { -1 }
    for (i in qs.indices) {
      val q = qs[i]
      val s = q[0]
      val e = q[1]
      val k = q[2]
      var found = INF
      for (j in s..e)
        arr[j].also { if (it in k + 1..found) found = it }

      if (found != INF) ans[i] = found
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (2.47ms, 60.5MB)
 * 테스트 2 〉	통과 (1.64ms, 59.5MB)
 * 테스트 3 〉	통과 (0.37ms, 59.5MB)
 * 테스트 4 〉	통과 (0.09ms, 58.8MB)
 * 테스트 5 〉	통과 (0.07ms, 61MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(arr: IntArray, queries: Array<IntArray>): List<Int> {
 *         return queries.map { q ->
 *             val min = arr.sliceArray(q[0]..q[1]).filter { it > q[2] }.minOrNull() ?: -1
 *             return@map if (min == Int.MAX_VALUE) -1 else min
 *         }
 *     }
 * }
 * 테스트 1 〉	통과 (20.20ms, 68.3MB)
 * 테스트 2 〉	통과 (17.70ms, 64.5MB)
 * 테스트 3 〉	통과 (15.51ms, 65.9MB)
 * 테스트 4 〉	통과 (14.01ms, 63.5MB)
 * 테스트 5 〉	통과 (13.61ms, 64.4MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(arr: IntArray, queries: Array<IntArray>): IntArray {
 *         var answer: IntArray = intArrayOf()
 *         queries.forEach{
 *             var temp = arr.sliceArray(it[0] .. it[1]).sortedArray()
 *             var num = -1
 *             for(i in 0 .. temp.size-1){
 *                 if(temp[i] > it[2]){
 *                     num = temp[i]
 *                     break
 *                 }
 *             }
 *             answer += num
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (23.87ms, 67.1MB)
 * 테스트 2 〉	통과 (19.30ms, 64.2MB)
 * 테스트 3 〉	통과 (19.28ms, 63.3MB)
 * 테스트 4 〉	통과 (14.59ms, 64.5MB)
 * 테스트 5 〉	통과 (19.84ms, 63.6MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(0, 1, 2, 4, 3),
    arrayOf(
      intArrayOf(0, 4, 2),
      intArrayOf(0, 3, 2),
      intArrayOf(0, 2, 2),
      intArrayOf(0, 4, 1),
    )
  ), intArrayOf(3, 4, -1, 2))
}

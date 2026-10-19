package 프로그래머스.Lv0.수열과구간쿼리1

import util.validate

class Solution {

  fun solution(arr: IntArray, qs: Array<IntArray>): IntArray {
    val N = arr.size
    val stt = IntArray(N)
    val end = IntArray(N)

    var min = N - 1
    var max = 0
    for (q in qs) {
      stt[q[0].also { if (it < min) min = it }]++
      end[q[1].also { if (it > max) max = it }]++
    }

    var cnt = 0
    for (i in min..max) {
      cnt += stt[i]
      arr[i] += cnt
      cnt -= end[i]
    }

    return arr
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.08ms, 60.2MB)
 * 테스트 2 〉	통과 (0.05ms, 60.6MB)
 * 테스트 3 〉	통과 (0.06ms, 59.8MB)
 * 테스트 4 〉	통과 (0.02ms, 60.6MB)
 * 테스트 5 〉	통과 (0.03ms, 59MB)
 * 테스트 6 〉	통과 (0.02ms, 60MB)
 * 테스트 7 〉	통과 (0.08ms, 61.1MB)
 * 테스트 8 〉	통과 (0.10ms, 61MB)
 * 테스트 9 〉	통과 (0.05ms, 58.4MB)
 * 테스트 10 〉	통과 (0.08ms, 61.1MB)
 * 테스트 11 〉	통과 (0.08ms, 59.6MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(arr: IntArray, queries: Array<IntArray>): IntArray {
 *         val mutableArray = arr;
 *
 *         for (query in queries) {
 *             for (i in query[0]..query[1]) {
 *                 mutableArray[i] += 1;
 *             }
 *         }
 *         return mutableArray
 *     }
 * }
 * 테스트 1 〉	통과 (1.47ms, 61.3MB)
 * 테스트 2 〉	통과 (0.78ms, 60.1MB)
 * 테스트 3 〉	통과 (0.22ms, 61.3MB)
 * 테스트 4 〉	통과 (0.05ms, 59.4MB)
 * 테스트 5 〉	통과 (0.03ms, 60.3MB)
 * 테스트 6 〉	통과 (0.02ms, 60.2MB)
 * 테스트 7 〉	통과 (1.26ms, 60.6MB)
 * 테스트 8 〉	통과 (1.32ms, 60.1MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(arr: IntArray, queries: Array<IntArray>): IntArray {
 *         var answer: IntArray = intArrayOf()
 *         queries.forEach { (s, e) ->
 *             (s..e).forEach { arr[it]+= 1 }
 *         }
 *         return arr
 *     }
 * }
 * 테스트 1 〉	통과 (4.61ms, 61.4MB)
 * 테스트 2 〉	통과 (3.70ms, 60.1MB)
 * 테스트 3 〉	통과 (2.36ms, 60MB)
 * 테스트 4 〉	통과 (1.36ms, 60.7MB)
 * 테스트 5 〉	통과 (1.41ms, 60MB)
 * 테스트 6 〉	통과 (1.36ms, 60.3MB)
 * 테스트 7 〉	통과 (3.90ms, 61.9MB)
 * 테스트 8 〉	통과 (4.07ms, 61MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(0, 1, 2, 3, 4),
    arrayOf(
      intArrayOf(0, 1),
      intArrayOf(1, 2),
      intArrayOf(2, 3),
    )
  ), intArrayOf(1, 3, 4, 4, 4))
}

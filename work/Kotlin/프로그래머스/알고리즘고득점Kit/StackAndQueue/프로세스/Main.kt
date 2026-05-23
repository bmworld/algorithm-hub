package 프로그래머스.알고리즘고득점Kit.StackAndQueue.프로세스

import util.validate

class Solution {

  val maxP = 9
  fun solution(a: IntArray, location: Int): Int {
    val cnts = IntArray(maxP + 1)
    val len = a.size

    var higher = 0
    var higherEnd = 0
    for (i in 0 until len) {
      val p = a[i].also {
        if (it > higher) {
          higher = it
          higherEnd = i
        } else if (it == higher) higherEnd = i
      }
      cnts[p]++
    }

    var ans = 0
    val t = a[location]

    for (i in 0 until len) {
      if (a[i] != higher) continue
      ans++
      if (i == location && higher == t) return ans
    }


    for (p in higher - 1 downTo 1) {
      if (cnts[p] == 0) continue

      val end = higherEnd
      var i = nxt(end, len)
      while (i != end) {
        if (a[i] == p) {
          ans++
          if (i == location && p == t) return ans
          higherEnd = i
        }
        i = nxt(i, len)
      }

      higher = p
    }

    return ans
  }

  fun nxt(idx: Int, size: Int): Int = if (idx + 1 < size) idx + 1 else 0
}

/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (0.01ms, 65.1MB)
 * 테스트 2 〉	통과 (0.09ms, 65.3MB)
 * 테스트 3 〉	통과 (0.01ms, 63.1MB)
 * 테스트 4 〉	통과 (0.01ms, 63.3MB)
 * 테스트 5 〉	통과 (0.01ms, 64MB)
 * 테스트 6 〉	통과 (0.02ms, 63.1MB)
 * 테스트 7 〉	통과 (0.02ms, 62.6MB)
 * 테스트 8 〉	통과 (0.05ms, 65.7MB)
 * 테스트 9 〉	통과 (0.02ms, 63MB)
 * 테스트 10 〉	통과 (0.03ms, 63.4MB)
 * 테스트 11 〉	통과 (0.08ms, 62.4MB)
 * 테스트 12 〉	통과 (0.01ms, 63.6MB)
 * 테스트 13 〉	통과 (0.06ms, 63.1MB)
 * 테스트 14 〉	통과 (0.01ms, 63MB)
 * 테스트 15 〉	통과 (0.01ms, 62.3MB)
 * 테스트 16 〉	통과 (0.02ms, 64.1MB)
 * 테스트 17 〉	통과 (0.04ms, 62.3MB)
 * 테스트 18 〉	통과 (0.02ms, 62.7MB)
 * 테스트 19 〉	통과 (0.06ms, 62.2MB)
 * 테스트 20 〉	통과 (0.01ms, 62.3MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * import java.util.*
 * class Solution {
 *     fun solution(priorities: IntArray, location: Int): Int {
 *             var printerQueue = ArrayDeque<Pair<Int,Int>>()
 *         priorities.forEachIndexed{index, i ->
 *             printerQueue.offer(Pair(index,i))
 *         }
 *
 *         var count = 0
 *         while (!printerQueue.isEmpty()){
 *             var first = printerQueue.poll()
 *
 *             if(printerQueue.filter { first.second < it.second }.size > 0){
 *                 printerQueue.offer(first)
 *             }else{
 *                 count++
 *                 if(first.first == location) return count
 *             }
 *         }
 *         return 0
 *     }
 * }
 *
 *
 * 테스트 1 〉	통과 (0.79ms, 63.6MB)
 * 테스트 2 〉	통과 (3.25ms, 62.2MB)
 * 테스트 3 〉	통과 (0.94ms, 62.9MB)
 * 테스트 4 〉	통과 (0.75ms, 61.3MB)
 * 테스트 5 〉	통과 (0.21ms, 63.3MB)
 * 테스트 6 〉	통과 (0.90ms, 63.9MB)
 * 테스트 7 〉	통과 (1.12ms, 63.2MB)
 * 테스트 8 〉	통과 (3.11ms, 63MB)
 * 테스트 9 〉	통과 (0.30ms, 63.6MB)
 * 테스트 10 〉	통과 (0.82ms, 62.9MB)
 * 테스트 11 〉	통과 (2.10ms, 65.7MB)
 * 테스트 12 〉	통과 (0.60ms, 63.4MB)
 * 테스트 13 〉	통과 (1.90ms, 64MB)
 * 테스트 14 〉	통과 (0.18ms, 63.4MB)
 * 테스트 15 〉	통과 (0.31ms, 64MB)
 * 테스트 16 〉	통과 (0.61ms, 63.5MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(intArrayOf(1, 1, 1, 1, 1), 2), 3
  )

  validate(
    s.solution(intArrayOf(2, 1, 3, 2), 2), 1
  )
  validate(
    s.solution(intArrayOf(1, 1, 9, 1, 1, 1), 0), 5
  )

}

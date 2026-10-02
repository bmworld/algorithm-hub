package 프로그래머스.Lv2._2023_KAKAO_BLIND_RECRUITMENT.택배배달과수거하기

import util.validate

class Solution {

  fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
    var ans = 0L
    var d = n - 1
    var p = n - 1

    while (d >= 0 || p >= 0) {

      while (d >= 0) if (deliveries[d] == 0) d-- else break
      while (p >= 0) if (pickups[p] == 0) p-- else break

      var to = maxOf(p, d)
      ans += (to + 1) * 2

      var nd = minOf(d, to)
      var dCap = cap
      while (nd >= 0 && dCap > 0) {
        val x = deliveries[nd]
        val c = minOf(x, dCap)
        dCap -= c
        deliveries[nd] = (x - c).also { if (it == 0) nd-- }
      }

      var np = minOf(p, to)
      var pCap = cap
      while (np >= 0 && pCap > 0) {
        val x = pickups[np]
        val c = minOf(x, pCap)
        pCap -= c
        pickups[np] = (x - c).also { if (it == 0) np-- }
      }
      d = nd
      p = np
    }

    return ans
  }
}

/**
 * ```
 * [ME]테스트 1 〉	통과 (0.18ms, 60.3MB)
 * 테스트 2 〉	통과 (0.05ms, 60.9MB)
 * 테스트 3 〉	통과 (0.19ms, 60.3MB)
 * 테스트 4 〉	통과 (0.18ms, 60.7MB)
 * 테스트 5 〉	통과 (0.19ms, 59.4MB)
 * 테스트 6 〉	통과 (0.20ms, 60.4MB)
 * 테스트 7 〉	통과 (0.42ms, 61.1MB)
 * 테스트 8 〉	통과 (0.66ms, 60.9MB)
 * 테스트 9 〉	통과 (1.57ms, 62.1MB)
 * 테스트 10 〉	통과 (1.28ms, 60.9MB)
 * 테스트 11 〉	통과 (0.78ms, 58.8MB)
 * 테스트 12 〉	통과 (0.72ms, 59.2MB)
 * 테스트 13 〉	통과 (0.59ms, 60.4MB)
 * 테스트 14 〉	통과 (0.59ms, 60.8MB)
 * 테스트 15 〉	통과 (4.24ms, 66.7MB)
 * 테스트 16 〉	통과 (16.74ms, 66.5MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
 *         var answer: Long = 0
 *         var give = 0
 *         var get = 0
 *         for (i in n downTo 1) {
 *             val delivery = deliveries[i-1]
 *             val pickup = pickups[i-1]
 *             if (delivery != 0 || pickup != 0) {
 *                 var cnt = 0
 *                 while (delivery > give || pickup > get) {
 *                     ++cnt
 *                     give+=cap
 *                     get+=cap
 *                 }
 *                 give-=delivery
 *                 get-=pickup
 *                 answer+=(i*2*cnt)
 *             }
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (0.04ms, 59.8MB)
 * 테스트 2 〉	통과 (0.03ms, 60.8MB)
 * 테스트 3 〉	통과 (0.02ms, 61MB)
 * 테스트 4 〉	통과 (0.02ms, 60.5MB)
 * 테스트 5 〉	통과 (0.02ms, 59.7MB)
 * 테스트 6 〉	통과 (0.02ms, 59.8MB)
 * 테스트 7 〉	통과 (0.07ms, 60.9MB)
 * 테스트 8 〉	통과 (0.09ms, 61MB)
 * 테스트 9 〉	통과 (0.32ms, 60.5MB)
 * 테스트 10 〉	통과 (0.26ms, 60.6MB)
 * 테스트 11 〉	통과 (0.19ms, 60.5MB)
 * 테스트 12 〉	통과 (0.20ms, 60.8MB)
 * 테스트 13 〉	통과 (0.18ms, 60.6MB)
 * 테스트 14 〉	통과 (0.16ms, 60.4MB)
 * 테스트 15 〉	통과 (3.63ms, 66.6MB)
 * 테스트 16 〉	통과 (7.17ms, 66.4MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
 *
 *         val minVisitCounts = IntArray( n )
 *
 *         var lastIndex = n - 1
 *
 *         while( lastIndex > -1 ) {
 *
 *             var bigger = maxOf( deliveries[lastIndex], pickups[lastIndex] )
 *             if ( bigger < 0 )
 *                 break
 *             val minVisitCount = bigger / cap + if ( bigger % cap == 0 ) 0 else 1
 *             minVisitCounts[lastIndex] = minVisitCount
 *             if ( lastIndex == 0 )
 *                 break
 *
 *             deliveries[lastIndex] -= minVisitCount * cap
 *             pickups[lastIndex] -= minVisitCount * cap
 *
 *             while ( lastIndex > 0 ) {
 *                 if ( deliveries[lastIndex] < 0 ) {
 *                     deliveries[lastIndex - 1] += deliveries[lastIndex]
 *                     deliveries[lastIndex] = 0
 *                 }
 *
 *                 if ( pickups[lastIndex] < 0 ) {
 *                     pickups[lastIndex - 1] += pickups[lastIndex]
 *                     pickups[lastIndex] = 0
 *                 }
 *                 if ( deliveries[lastIndex] > 0 || pickups[lastIndex] > 0 )
 *                     break
 *                 lastIndex--
 *             }
 *         }
 *
 *         var answer: Long = 0
 *         minVisitCounts.forEachIndexed {
 *             index,count -> answer += ( index + 1 ) * count * 2
 *         }
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (0.06ms, 60.5MB)
 * 테스트 2 〉	통과 (0.03ms, 61.2MB)
 * 테스트 3 〉	통과 (0.02ms, 60.3MB)
 * 테스트 4 〉	통과 (0.03ms, 57.9MB)
 * 테스트 5 〉	통과 (0.06ms, 59.5MB)
 * 테스트 6 〉	통과 (0.03ms, 60.2MB)
 * 테스트 7 〉	통과 (0.15ms, 61.1MB)
 * 테스트 8 〉	통과 (0.19ms, 60.7MB)
 * 테스트 9 〉	통과 (0.52ms, 60.6MB)
 * 테스트 10 〉	통과 (0.48ms, 61.4MB)
 * 테스트 11 〉	통과 (0.44ms, 60.8MB)
 * 테스트 12 〉	통과 (0.45ms, 59.6MB)
 * 테스트 13 〉	통과 (0.38ms, 60.4MB)
 * 테스트 14 〉	통과 (0.38ms, 61.7MB)
 * 테스트 15 〉	통과 (4.44ms, 66.6MB)
 * 테스트 16 〉	통과 (6.23ms, 67.5MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(4, 5, intArrayOf(1, 0, 3, 1, 2), intArrayOf(0, 3, 0, 4, 0)), 16)
  validate(s.solution(2, 7, intArrayOf(1, 0, 2, 0, 1, 0, 2), intArrayOf(0, 2, 0, 1, 0, 2, 0)), 30)
}

//      println("[$to] d ($d -> $nd), p ($p -> $np) = ans=$ans")

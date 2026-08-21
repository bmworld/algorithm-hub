package 프로그래머스.Lv2.디펜스게임

import util.validate

import java.util.PriorityQueue

class Solution {

  fun solution(me: Int, k: Int, enemy: IntArray): Int {
    val M = enemy.size
    if (k >= M) return M

    var ans = k

    val pq = PriorityQueue<Int>()
    repeat(k) {
      pq.add(enemy[it])
    }

    var enemies = 0
    for (i in k until M) {
      val x = enemy[i]
      val last = pq.peek()

      when {
        x <= last -> {
          enemies += x
          if (enemies > me) break
        }
        else -> {
          enemies += last
          if (enemies > me) break
          pq.poll()
          pq.add(x)
        }
      }
      ans++
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (1.05ms, 61.3MB)
 * 테스트 2 〉	통과 (1.68ms, 64MB)
 * 테스트 3 〉	통과 (38.21ms, 102MB)
 * 테스트 4 〉	통과 (3.06ms, 91.6MB)
 * 테스트 5 〉	통과 (0.01ms, 61.9MB)
 * 테스트 6 〉	통과 (32.25ms, 113MB)
 * 테스트 7 〉	통과 (14.21ms, 101MB)
 * 테스트 8 〉	통과 (6.02ms, 101MB)
 * 테스트 9 〉	통과 (11.96ms, 101MB)
 * 테스트 10 〉	통과 (18.46ms, 97.8MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * import java.util.*
 *
 * class Solution {
 *     fun solution(n: Int, k: Int, enemies: IntArray): Int {
 *         // enemy를 우선순위 큐에 넣고
 *         // k 개가 안되면 계속 넣음
 *         // k 개면 최소값과 비교해서 넣을지 확인
 *         // 가능하면 다시 넣음
 *         val pq=PriorityQueue<Int>()
 *         var ans = 0
 *         var addNum: Int
 *         var remain=n
 *         for(enemy in enemies) {
 *             when {
 *                 pq.size<k ->{
 *                     pq.add(enemy)
 *                     addNum=0
 *                 }
 *                 pq.peek()<enemy -> {
 *                     addNum=pq.poll();
 *                     pq.add(enemy)
 *                 }
 *                 else -> addNum=enemy
 *             }
 *             // n 보다 크면 게임 break
 *             remain-=addNum
 *             if(remain<0) break
 *             ans++
 *         }
 *         return ans
 *     }
 * }
 * 테스트 1 〉	통과 (1.04ms, 61.2MB)
 * 테스트 2 〉	통과 (2.12ms, 64.3MB)
 * 테스트 3 〉	통과 (43.99ms, 104MB)
 * 테스트 4 〉	통과 (3.25ms, 91.9MB)
 * 테스트 5 〉	통과 (1.46ms, 62.1MB)
 * 테스트 6 〉	통과 (33.67ms, 113MB)
 * 테스트 7 〉	통과 (14.49ms, 100MB)
 * 테스트 8 〉	통과 (6.68ms, 101MB)
 * 테스트 9 〉	통과 (13.88ms, 98.2MB)
 * 테스트 10 〉	통과 (30.89ms, 99.6MB)
 *
 * [RIVAL 2]
 * import java.util.PriorityQueue
 *
 * class Solution {
 *     fun solution(n: Int, k: Int, enemy: IntArray): Int {
 *         val pq = PriorityQueue<Int>(reverseOrder())
 *         var restN = n; var restK = k
 *
 *         enemy.forEachIndexed { i, v ->
 *             pq.add(v)
 *             restN -= v
 *             if (restN < 0) when(restK > 0) {
 *                 true -> { --restK; restN += pq.poll() }
 *                 false -> return i
 *             }
 *         }
 *
 *         return enemy.size
 *
 *     }
 * }
 * 테스트 1 〉	통과 (2.34ms, 61.6MB)
 * 테스트 2 〉	통과 (3.35ms, 64.4MB)
 * 테스트 3 〉	통과 (45.28ms, 107MB)
 * 테스트 4 〉	통과 (5.08ms, 90.9MB)
 * 테스트 5 〉	통과 (5.79ms, 61.8MB)
 * 테스트 6 〉	통과 (55.71ms, 108MB)
 * 테스트 7 〉	통과 (20.43ms, 107MB)
 * 테스트 8 〉	통과 (12.47ms, 106MB)
 * 테스트 9 〉	통과 (21.80ms, 104MB)
 * 테스트 10 〉	통과 (48.62ms, 160MB)
 *
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(7, 3, intArrayOf(4, 2, 4, 5, 3, 3, 1)), 5)
  validate(s.solution(2, 4, intArrayOf(3, 3, 3, 3)), 4)

}

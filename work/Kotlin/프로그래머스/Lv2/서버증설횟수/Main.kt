package 프로그래머스.Lv2.서버증설횟수

import util.validate

class Solution {
  companion object {

    const val HOURS_PER_DAY = 24
    const val DEF_SERVER_CNT = 1
  }

  fun solution(players: IntArray, m: Int, k: Int): Int {
    var times = 0
    val servers = IntArray(HOURS_PER_DAY) { DEF_SERVER_CNT }
    for (time in 0 until HOURS_PER_DAY) {
      val req = 1 + players[time] / m
      val cur = servers[time]

      val expanded = req - cur
      if (expanded > 0) {
        times += expanded
        for (j in time until minOf(time + k, HOURS_PER_DAY)) servers[j] += expanded
      }
    }
    return times
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.7MB)
 * 테스트 2 〉	통과 (0.02ms, 59.4MB)
 * 테스트 3 〉	통과 (0.02ms, 61MB)
 * 테스트 4 〉	통과 (0.02ms, 59.3MB)
 * 테스트 5 〉	통과 (0.02ms, 60.6MB)
 * 테스트 6 〉	통과 (0.02ms, 60.9MB)
 * 테스트 7 〉	통과 (0.02ms, 60.9MB)
 * 테스트 8 〉	통과 (0.02ms, 60.9MB)
 * 테스트 9 〉	통과 (0.02ms, 59.3MB)
 * 테스트 10 〉	통과 (0.02ms, 60.1MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *    fun solution(players: IntArray, m: Int, k: Int): Int {
 *         var answer: Int = 0
 *         var server = IntArray(24) { 0 };
 *
 *         players.forEachIndexed { index, player ->
 *         val capacity =  (m - 1) + server[index] * m
 *         if (capacity < player) {
 *             val newServer = player/m - server[index]
 *             (1..newServer).forEach {
 *                 (index..(minOf(index + k - 1, server.size - 1))).forEach {
 *                     server[it] ++
 *                 }
 *                 answer++
 *             }
 *         }
 *         }
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (1.28ms, 59.1MB)
 * 테스트 2 〉	통과 (1.47ms, 59.7MB)
 * 테스트 3 〉	통과 (2.03ms, 60.2MB)
 * 테스트 4 〉	통과 (1.55ms, 58.3MB)
 * 테스트 5 〉	통과 (1.28ms, 61.2MB)
 * 테스트 6 〉	통과 (1.25ms, 60.2MB)
 * 테스트 7 〉	통과 (1.27ms, 60.5MB)
 * 테스트 8 〉	통과 (1.30ms, 59.3MB)
 * 테스트 9 〉	통과 (1.87ms, 59.8MB)
 * 테스트 10 〉	통과 (2.10ms, 59.2MB)
 *
 * [RIVAL 2]
 * import java.util.PriorityQueue
 *
 * class Solution {
 *     fun solution(players: IntArray, m: Int, k: Int): Int {
 *         var answer: Int = 0
 *         var pq = PriorityQueue<Int>()
 *
 *         players.forEachIndexed { i, p ->
 *             while(pq.size > 0 && ( i - pq.peek()  >= k)) pq.poll()
 *
 *             var cur = pq.size
 *
 *             if( p/m == 0 && p/m <= cur ) return@forEachIndexed
 *
 *             (0..(p/m - cur -1)).forEach {
 *                pq.offer(i)
 *                answer++
 *             }
 *         }
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (1.40ms, 60.8MB)
 * 테스트 2 〉	통과 (1.78ms, 60.3MB)
 * 테스트 3 〉	통과 (1.41ms, 59.9MB)
 * 테스트 4 〉	통과 (1.44ms, 59.4MB)
 * 테스트 5 〉	통과 (1.58ms, 58.1MB)
 * 테스트 6 〉	통과 (1.60ms, 60.9MB)
 * 테스트 7 〉	통과 (1.71ms, 61MB)
 * 테스트 8 〉	통과 (1.49ms, 59.8MB)
 * 테스트 9 〉	통과 (3.04ms, 59.6MB)
 * 테스트 10 〉	통과 (2.04ms, 59.7MB)
 *
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(
      intArrayOf(0, 2, 3, 3, 1, 2, 0, 0, 0, 0, 4, 2, 0, 6, 0, 4, 2, 13, 3, 5, 10, 0, 1, 5), 3, 5
    ), 7
  )

  validate(
    s.solution(
      intArrayOf(0, 0, 0, 10, 0, 12, 0, 15, 0, 1, 0, 1, 0, 0, 0, 5, 0, 0, 11, 0, 8, 0, 0, 0), 5, 1
    ), 11
  )
  validate(
    s.solution(
      intArrayOf(0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 0, 5, 0, 2, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1), 1, 1
    ), 12
  )


}
//      println("---- [${time}] $p => $req vs $cur")

package 프로그래머스.Lv2.배달

import util.validate
import java.util.*

class Solution {
  companion object {

    const val START = 1
    const val SEP = 100
    const val INF = Int.MAX_VALUE
  }

  fun solution(N: Int, road: Array<IntArray>, k: Int): Int {

    val g = Array(N + 1) { mutableListOf<Int>() }
    for (arr in road) {
      val fr = arr[0]
      val to = arr[1]
      val t = arr[2]
      g[fr] += t * SEP + to
      g[to] += t * SEP + fr
    }

    val q = PriorityQueue<Int>()
    val dist = IntArray(N + 1) { INF }
    dist[START] = 0
    q.add(START)

    while (q.isNotEmpty()) {
      val e = q.poll()
      val t1 = e / SEP
      val fr = e % SEP

      for (e2 in g[fr]) {
        val t2 = e2 / SEP
        val to = e2 % SEP
        val acc = t1 + t2

        if (acc <= k && acc < dist[to]) {
          dist[to] = acc
          q.add(acc * SEP + to)
        }
      }
    }

    var ans = 0
    for (i in 1..N) if (dist[i] <= k) ans++
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.35ms, 59.8MB)
 * 테스트 2 〉	통과 (0.36ms, 60MB)
 * 테스트 3 〉	통과 (0.40ms, 60.8MB)
 * 테스트 4 〉	통과 (0.38ms, 60.6MB)
 * 테스트 5 〉	통과 (0.37ms, 60.4MB)
 * 테스트 6 〉	통과 (0.25ms, 60.9MB)
 * 테스트 7 〉	통과 (0.27ms, 60.1MB)
 * 테스트 8 〉	통과 (0.29ms, 60.1MB)
 * 테스트 9 〉	통과 (0.39ms, 61MB)
 * 테스트 10 〉	통과 (0.31ms, 60.2MB)
 * 테스트 11 〉	통과 (0.38ms, 60.1MB)
 * 테스트 12 〉	통과 (0.35ms, 59.8MB)
 * 테스트 13 〉	통과 (0.37ms, 58.3MB)
 * 테스트 14 〉	통과 (0.68ms, 61.3MB)
 * 테스트 15 〉	통과 (0.90ms, 61.2MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(N: Int, road: Array<IntArray>, k: Int): Int {
 *         var answer:Int = 0
 *         var len:Int = road.size - 1
 *     var dist:IntArray = IntArray(N+1, {i->100000000})
 *     dist[1] = 0
 *     var flag:Boolean = true
 *
 *     while(flag){
 *         flag = false
 *
 *         for(i in 0..len){
 *             if(dist[road[i][0]] + road[i][2] < dist[road[i][1]]){
 *                 flag = true
 *                 dist[road[i][1]] = dist[road[i][0]] + road[i][2]
 *             }
 *             if(dist[road[i][1]] + road[i][2] < dist[road[i][0]]){
 *                 flag = true
 *                 dist[road[i][0]] = dist[road[i][1]] + road[i][2]
 *             }
 *         }
 *     }
 *
 *     for(i in 1..N)
 *     {
 *         if(dist[i] <= k) answer++
 *     }
 *
 *     return answer;
 *     }
 * }
 * 테스트 1 〉	통과 (0.02ms, 61MB)
 * 테스트 2 〉	통과 (0.02ms, 60.2MB)
 * 테스트 3 〉	통과 (0.02ms, 58.1MB)
 * 테스트 4 〉	통과 (0.02ms, 59.6MB)
 * 테스트 5 〉	통과 (0.02ms, 59.2MB)
 * 테스트 6 〉	통과 (0.02ms, 59.1MB)
 * 테스트 7 〉	통과 (0.01ms, 59.9MB)
 * 테스트 8 〉	통과 (0.01ms, 60.7MB)
 * 테스트 9 〉	통과 (0.02ms, 59.1MB)
 * 테스트 10 〉	통과 (0.01ms, 59.8MB)
 * 테스트 11 〉	통과 (0.01ms, 60.3MB)
 * 테스트 12 〉	통과 (0.03ms, 59.4MB)
 * 테스트 13 〉	통과 (0.03ms, 61.2MB)
 * 테스트 14 〉	통과 (0.23ms, 61.3MB)
 * 테스트 15 〉	통과 (0.45ms, 60.6MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(5,
    arrayOf(
      intArrayOf(1, 2, 1),
      intArrayOf(2, 3, 3),
      intArrayOf(5, 2, 2),
      intArrayOf(1, 4, 2),
      intArrayOf(5, 3, 1),
      intArrayOf(5, 4, 2),
    ), 3), 4)

  validate(s.solution(6,
    arrayOf(
      intArrayOf(1, 2, 1),
      intArrayOf(1, 3, 2),
      intArrayOf(2, 3, 2),
      intArrayOf(3, 4, 3),
      intArrayOf(3, 5, 2),
      intArrayOf(3, 5, 3),
      intArrayOf(5, 6, 1),
    ), 4), 4)
}

//      println("[$fr] t= $t1")

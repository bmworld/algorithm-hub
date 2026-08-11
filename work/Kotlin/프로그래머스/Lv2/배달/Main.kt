package 프로그래머스.Lv2.배달

import util.validate

class Solution {
  companion object {

    const val SEP = 100
    const val INF = Int.MAX_VALUE
  }

  fun solution(N: Int, road: Array<IntArray>, K: Int): Int {

    val CAP = N + 1
    val dist = IntArray(CAP * CAP) { INF }

    val q = IntArray(CAP * CAP)
    dist[1 * CAP + 1] = 0

    for (x in road) {
      val fr = x[0]
      val to = x[1]
      val t = x[2]
      val p1 = dist[fr * CAP + to]
      if (t < p1) dist[fr * CAP + to] = t
      val p2 = to * CAP + fr
      if (t < dist[p2]) dist[p2] = t
    }

    var qh = 0
    var qt = 0
    for (to in 2..N) if (dist[1 * CAP + to] <= K) q[qt++] = to

    while (qh < qt) {
      val fr = q[qh++]
      val t = dist[1 * CAP + fr]

      for (to in 2..N) {
        if (fr == to) continue
        val acc = t + dist[fr * CAP + to]
        if (acc in 1..K && acc < dist[1 * CAP + to]) {
          dist[1 * CAP + to] = acc
          q[qt++] = to
        }
      }
    }

    var ans = 0
    for (to in 1..N) if (dist[1 * CAP + to] <= K) ans++
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
 * v2:
 * 테스트 1 〉	통과 (0.06ms, 59.9MB)
 * 테스트 2 〉	통과 (0.08ms, 60.3MB)
 * 테스트 3 〉	통과 (0.05ms, 60MB)
 * 테스트 4 〉	통과 (0.10ms, 60.4MB)
 * 테스트 5 〉	통과 (0.07ms, 60.5MB)
 * 테스트 6 〉	통과 (0.07ms, 59.8MB)
 * 테스트 7 〉	통과 (0.05ms, 61MB)
 * 테스트 8 〉	통과 (0.05ms, 59.7MB)
 * 테스트 9 〉	통과 (0.06ms, 60.4MB)
 * 테스트 10 〉	통과 (0.05ms, 59.3MB)
 * 테스트 11 〉	통과 (0.08ms, 59MB)
 * 테스트 12 〉	통과 (0.19ms, 60.1MB)
 * 테스트 13 〉	통과 (0.45ms, 59.2MB)
 * 테스트 14 〉	통과 (1.08ms, 59.8MB)
 * 테스트 15 〉	통과 (2.77ms, 60.6MB)
 * v3:
 * 테스트 1 〉	통과 (0.30ms, 59.2MB)
 * 테스트 2 〉	통과 (0.29ms, 59MB)
 * 테스트 3 〉	통과 (0.27ms, 60.8MB)
 * 테스트 4 〉	통과 (0.37ms, 59.2MB)
 * 테스트 5 〉	통과 (0.33ms, 58.5MB)
 * 테스트 6 〉	통과 (0.32ms, 58.3MB)
 * 테스트 7 〉	통과 (0.32ms, 59.8MB)
 * 테스트 8 〉	통과 (0.32ms, 58.8MB)
 * 테스트 9 〉	통과 (0.31ms, 59.6MB)
 * 테스트 10 〉	통과 (0.25ms, 58.7MB)
 * 테스트 11 〉	통과 (0.32ms, 60.9MB)
 * 테스트 12 〉	통과 (0.73ms, 58.3MB)
 * 테스트 13 〉	통과 (0.45ms, 58.1MB)
 * 테스트 14 〉	통과 (0.53ms, 60.4MB)
 * 테스트 15 〉	통과 (0.53ms, 62.2MB)
 * v4:
 * 테스트 1 〉	통과 (0.02ms, 60.2MB)
 * 테스트 2 〉	통과 (0.03ms, 61.2MB)
 * 테스트 3 〉	통과 (0.02ms, 59.4MB)
 * 테스트 4 〉	통과 (0.02ms, 59.9MB)
 * 테스트 5 〉	통과 (0.02ms, 60.5MB)
 * 테스트 6 〉	통과 (0.03ms, 60.2MB)
 * 테스트 7 〉	통과 (0.02ms, 61.5MB)
 * 테스트 8 〉	통과 (0.03ms, 60.2MB)
 * 테스트 9 〉	통과 (0.02ms, 60.6MB)
 * 테스트 10 〉	통과 (0.02ms, 60.4MB)
 * 테스트 11 〉	통과 (0.02ms, 60.6MB)
 * 테스트 12 〉	통과 (0.10ms, 60.9MB)
 * 테스트 13 〉	통과 (0.10ms, 60.5MB)
 * 테스트 14 〉	통과 (0.18ms, 59.8MB)
 * 테스트 15 〉	통과 (0.24ms, 61.3MB)
 * 테스트 16 〉	통과 (0.04ms, 60.5MB)
 * 테스트 17 〉	통과 (0.06ms, 60.6MB)
 * 테스트 18 〉	통과 (0.20ms, 59.7MB)
 * 테스트 19 〉	통과 (0.22ms, 61.6MB)
 * 테스트 20 〉	통과 (0.14ms, 58.7MB)
 * 테스트 21 〉	통과 (0.24ms, 60.7MB)
 * 테스트 22 〉	통과 (0.20ms, 59.5MB)
 * 테스트 23 〉	통과 (0.31ms, 61.4MB)
 * 테스트 24 〉	통과 (0.27ms, 62MB)
 * 테스트 25 〉	통과 (0.27ms, 61.8MB)
 * v5:
 * 테스트 1 〉	통과 (0.02ms, 59.6MB)
 * 테스트 2 〉	통과 (0.02ms, 59.5MB)
 * 테스트 3 〉	통과 (0.02ms, 60.5MB)
 * 테스트 4 〉	통과 (0.02ms, 58.5MB)
 * 테스트 5 〉	통과 (0.02ms, 61.2MB)
 * 테스트 6 〉	통과 (0.02ms, 60.7MB)
 * 테스트 7 〉	통과 (0.03ms, 60.9MB)
 * 테스트 8 〉	통과 (0.02ms, 61MB)
 * 테스트 9 〉	통과 (0.02ms, 61.1MB)
 * 테스트 10 〉	통과 (0.02ms, 60.7MB)
 * 테스트 11 〉	통과 (0.02ms, 59.4MB)
 * 테스트 12 〉	통과 (0.06ms, 60.2MB)
 * 테스트 13 〉	통과 (0.05ms, 61.1MB)
 * 테스트 14 〉	통과 (0.12ms, 59.9MB)
 * 테스트 15 〉	통과 (0.17ms, 60.7MB)
 * 테스트 16 〉	통과 (0.03ms, 60.5MB)
 * 테스트 17 〉	통과 (0.06ms, 60.4MB)
 * 테스트 18 〉	통과 (0.08ms, 60.8MB)
 * 테스트 19 〉	통과 (0.12ms, 60.7MB)
 * 테스트 20 〉	통과 (0.09ms, 60.3MB)
 * 테스트 21 〉	통과 (0.24ms, 61.1MB)
 * 테스트 22 〉	통과 (0.13ms, 60.7MB)
 * 테스트 23 〉	통과 (0.27ms, 59.5MB)
 * 테스트 24 〉	통과 (0.29ms, 61MB)
 * 테스트 25 〉	통과 (0.23ms, 61.5MB)
 *
 *
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
 * 테스트 1 〉	통과 (0.01ms, 59.8MB)
 * 테스트 2 〉	통과 (0.01ms, 60.9MB)
 * 테스트 3 〉	통과 (0.02ms, 60.4MB)
 * 테스트 4 〉	통과 (0.02ms, 61.7MB)
 * 테스트 5 〉	통과 (0.02ms, 59.7MB)
 * 테스트 6 〉	통과 (0.02ms, 60.5MB)
 * 테스트 7 〉	통과 (0.02ms, 61.4MB)
 * 테스트 8 〉	통과 (0.02ms, 60.3MB)
 * 테스트 9 〉	통과 (0.02ms, 60.9MB)
 * 테스트 10 〉	통과 (0.01ms, 60.9MB)
 * 테스트 11 〉	통과 (0.02ms, 60.2MB)
 * 테스트 12 〉	통과 (0.03ms, 58.6MB)
 * 테스트 13 〉	통과 (0.03ms, 58.8MB)
 * 테스트 14 〉	통과 (0.23ms, 60MB)
 * 테스트 15 〉	통과 (0.32ms, 60.7MB)
 * 테스트 16 〉	통과 (0.02ms, 60.7MB)
 * 테스트 17 〉	통과 (0.04ms, 60MB)
 * 테스트 18 〉	통과 (0.12ms, 60.7MB)
 * 테스트 19 〉	통과 (0.32ms, 60.7MB)
 * 테스트 20 〉	통과 (0.09ms, 60.3MB)
 * 테스트 21 〉	통과 (0.53ms, 61.2MB)
 * 테스트 22 〉	통과 (0.10ms, 61.2MB)
 * 테스트 23 〉	통과 (0.49ms, 60.8MB)
 * 테스트 24 〉	통과 (0.25ms, 61.5MB)
 * 테스트 25 〉	통과 (0.40ms, 60.9MB)
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

//        println("[$fr -> $to] k=$K VS acc=$acc VS ${dist[pos(1, to)]}}")

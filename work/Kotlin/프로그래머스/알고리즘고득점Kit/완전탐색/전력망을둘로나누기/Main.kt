package 프로그래머스.알고리즘고득점Kit.완전탐색.전력망을둘로나누기

import util.validate

class Solution {

  fun solution(n: Int, wires: Array<IntArray>): Int {
    val graph = Array(n + 1) { mutableListOf<Int>() }

    for (wire in wires) {
      val a = wire[0]
      val b = wire[1]
      graph[a] += b
      graph[b] += a
    }

    var ans: Int = n

    val q = IntArray(n)
    val ch = Array(n + 1) { BooleanArray(n + 1) }
    for (a in 1..n) {
      val cnds = graph[a]
      l@ for (b in cnds) {
        if (ch[a][b] || ch[b][a]) continue
        ch[a][b] = true
        ch[b][a] = true

        var cnt = 1
        val used = BooleanArray(n + 1)
        var qh = 0
        var qt = 0
        q[qt++] = b
        used[b] = true
        used[a] = true

        while (qh < qt) {
          val parent = q[qh++]
          for (child in graph[parent]) {
            if (child == a && parent != b) break@l
            if (used[child]) continue
            used[child] = true
            q[qt++] = child
            cnt++
          }
        }

        ans = minOf(ans, abs(n - 2 * cnt))
      }
    }

    return ans
  }

  fun abs(x: Int): Int = if (x < 0) -x else x
}

/**
 * ```
 * ME: v1
 * 테스트 1 〉	통과 (1.78ms, 59.7MB)
 * 테스트 2 〉	통과 (1.93ms, 60.3MB)
 * 테스트 3 〉	통과 (1.41ms, 59.2MB)
 * 테스트 4 〉	통과 (1.41ms, 59MB)
 * 테스트 5 〉	통과 (1.54ms, 60.1MB)
 * 테스트 6 〉	통과 (0.06ms, 59.2MB)
 * 테스트 7 〉	통과 (0.06ms, 59.3MB)
 * 테스트 8 〉	통과 (0.23ms, 58.7MB)
 * 테스트 9 〉	통과 (0.23ms, 59.1MB)
 * 테스트 10 〉	통과 (1.51ms, 59.9MB)
 * 테스트 11 〉	통과 (1.61ms, 60.1MB)
 * 테스트 12 〉	통과 (1.59ms, 59.7MB)
 * 테스트 13 〉	통과 (1.37ms, 59.2MB)
 * ME: v2
 * 테스트 1 〉	통과 (1.63ms, 59.7MB)
 * 테스트 2 〉	통과 (0.99ms, 60MB)
 * 테스트 3 〉	통과 (0.62ms, 59.5MB)
 * 테스트 4 〉	통과 (0.35ms, 59.7MB)
 * 테스트 5 〉	통과 (0.37ms, 59.2MB)
 * 테스트 6 〉	통과 (0.06ms, 59.4MB)
 * 테스트 7 〉	통과 (0.06ms, 59.4MB)
 * 테스트 8 〉	통과 (0.14ms, 59.4MB)
 * 테스트 9 〉	통과 (0.27ms, 58.6MB)
 * 테스트 10 〉	통과 (0.81ms, 59.9MB)
 * 테스트 11 〉	통과 (1.06ms, 59.5MB)
 * 테스트 12 〉	통과 (0.85ms, 59.3MB)
 * 테스트 13 〉	통과 (0.89ms, 59.5MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * import kotlin.math.abs
 *
 * class Solution {
 *     fun getDisconnectTower(wire: IntArray, wires: Array<IntArray>, compareNum: Int): Int {
 *         var answer = 0
 *         wires.forEach {
 *             if (!wire.contentEquals(it) && (compareNum == it[0] || compareNum == it[1]))
 *                 answer += 1 + getDisconnectTower(it, wires, if (compareNum == it[0]) it[1] else it[0])
 *         }
 *         return answer
 *     }
 *     fun solution(n: Int, wires: Array<IntArray>): Int {
 *         var bestNumOfTower: Int = -1
 *         wires.forEach {
 *             val value = 1 + getDisconnectTower(it, wires, it[0])
 *             if (abs(value - n/2) < abs(bestNumOfTower - n/2))
 *                 bestNumOfTower = value
 *             if (bestNumOfTower == n/2)
 *                 return if (n % 2 == 0) 0 else 1
 *         }
 *         return abs(n-bestNumOfTower - bestNumOfTower)
 *     }
 * }
 * 테스트 1 〉	통과 (0.50ms, 59.4MB)
 * 테스트 2 〉	통과 (3.50ms, 59.4MB)
 * 테스트 3 〉	통과 (7.28ms, 59.6MB)
 * 테스트 4 〉	통과 (4.52ms, 59.2MB)
 * 테스트 5 〉	통과 (5.20ms, 59.6MB)
 * 테스트 6 〉	통과 (0.04ms, 59.1MB)
 * 테스트 7 〉	통과 (0.04ms, 59.2MB)
 * 테스트 8 〉	통과 (0.38ms, 58.6MB)
 * 테스트 9 〉	통과 (0.35ms, 59.2MB)
 * 테스트 10 〉	통과 (2.82ms, 59.4MB)
 * 테스트 11 〉	통과 (3.22ms, 59.7MB)
 * 테스트 12 〉	통과 (3.13ms, 59.4MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(9,
    arrayOf(
      intArrayOf(1, 3),
      intArrayOf(2, 3),
      intArrayOf(3, 4),
      intArrayOf(4, 5),
      intArrayOf(4, 6),
      intArrayOf(4, 7),
      intArrayOf(7, 8),
      intArrayOf(7, 9),
    )
  ), 3)

  validate(s.solution(4,
    arrayOf(
      intArrayOf(1, 2),
      intArrayOf(2, 3),
      intArrayOf(3, 4),
    )
  ), 0)

  validate(s.solution(9,
    arrayOf(
      intArrayOf(1, 2),
      intArrayOf(2, 6),
      intArrayOf(3, 7),
      intArrayOf(3, 4),
      intArrayOf(4, 5),
      intArrayOf(6, 7),
    )
  ), 1)

  validate(

    s.solution(
      12,
      arrayOf(
        intArrayOf(1, 2),
        intArrayOf(1, 3),
        intArrayOf(1, 4),
        intArrayOf(1, 5),
        intArrayOf(5, 6),
        intArrayOf(6, 7),
        intArrayOf(7, 8),
        intArrayOf(8, 9),
        intArrayOf(9, 10),
        intArrayOf(10, 11),
        intArrayOf(11, 12),
      )
    ), 0
  )
}

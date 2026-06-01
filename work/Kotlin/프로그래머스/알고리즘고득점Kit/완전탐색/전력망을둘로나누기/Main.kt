package 프로그래머스.알고리즘고득점Kit.완전탐색.전력망을둘로나누기

import util.validate

class Solution {

  fun solution(n: Int, wires: Array<IntArray>): Int {
    val cap = n
    fun pos(r: Int, c: Int, cap: Int = n): Int = r * cap + c
    val wired = BooleanArray(cap * cap)
    val ch = BooleanArray(cap * cap)
    val graph = Array(n) { mutableListOf<Int>() }


    for (wire in wires) {
      val a = --wire[0]
      val b = --wire[1]
      graph[a] += b
      graph[b] += a
      wired[pos(a, b)] = true
      wired[pos(b, a)] = true
    }

    var ans = n

    fun dfs(a: Int): Int {
      var cnt = 1
      for (b in graph[a]) {
        if (!wired[pos(a, b)]) continue
        wired[pos(a, b)] = false
        wired[pos(b, a)] = false
        cnt += dfs(b)
        wired[pos(a, b)] = true
        wired[pos(b, a)] = true
      }
      return cnt
    }


    for (wire in wires) {
      val a = wire[0]
      val b = wire[1]

      if (ch[pos(a, b)]) continue
      ch[pos(a, b)] = true
      ch[pos(b, a)] = true
      wired[pos(a, b)] = false
      wired[pos(b, a)] = false
      val cnt = dfs(b)
      wired[pos(a, b)] = true
      wired[pos(b, a)] = true

      val diff = abs(n - 2 * cnt)
      if (diff == 0) return 0
      else ans = minOf(ans, diff)
    }

    return ans
  }

  fun abs(x: Int): Int = if (x < 0) -x else x
}

/**
 * ```
 * [ME]
 * v1:
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
 * v2:
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
 * v3:
 * 테스트 1 〉	통과 (2.34ms, 58.7MB)
 * 테스트 2 〉	통과 (0.97ms, 59.4MB)
 * 테스트 3 〉	통과 (0.47ms, 60.1MB)
 * 테스트 4 〉	통과 (0.28ms, 58.9MB)
 * 테스트 5 〉	통과 (0.42ms, 59.1MB)
 * 테스트 6 〉	통과 (0.06ms, 59.7MB)
 * 테스트 7 〉	통과 (0.07ms, 59.5MB)
 * 테스트 8 〉	통과 (0.14ms, 58.7MB)
 * 테스트 9 〉	통과 (0.16ms, 58.9MB)
 * 테스트 10 〉	통과 (0.86ms, 59.3MB)
 * 테스트 11 〉	통과 (1.91ms, 59.9MB)
 * 테스트 12 〉	통과 (0.87ms, 59.1MB)
 * 테스트 13 〉	통과 (0.88ms, 60MB)
 * v4:
 * 테스트 1 〉	통과 (1.12ms, 59.6MB)
 * 테스트 2 〉	통과 (0.99ms, 59.7MB)
 * 테스트 3 〉	통과 (0.64ms, 59.3MB)
 * 테스트 4 〉	통과 (0.36ms, 59.3MB)
 * 테스트 5 〉	통과 (0.53ms, 59.3MB)
 * 테스트 6 〉	통과 (0.06ms, 59.2MB)
 * 테스트 7 〉	통과 (0.05ms, 58.9MB)
 * 테스트 8 〉	통과 (1.85ms, 59.7MB)
 * 테스트 9 〉	통과 (0.23ms, 59.3MB)
 * 테스트 10 〉	통과 (0.92ms, 59.6MB)
 * 테스트 11 〉	통과 (0.89ms, 59.5MB)
 * 테스트 12 〉	통과 (0.77ms, 59.4MB)
 * 테스트 13 〉	통과 (0.90ms, 59.8MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * import kotlin.math.min
 * import kotlin.math.abs
 * class Solution {
 *     var answer: Int = Int.MAX_VALUE
 *     var vstd = Array(0) {IntArray(0)}
 *     var P = Array(0) {BooleanArray(0)}
 *     fun dfs(idx: Int, n: Int, wires: Array<IntArray>): Int {
 *         var ret = 1
 *         for (i in 0 until n) {
 *             if (P[idx][i]) {
 *                 P[idx][i] = false
 *                 P[i][idx] = false
 *                 ret += dfs(i, n, wires)
 *                 P[idx][i] = true
 *                 P[i][idx] = true
 *             }
 *         }
 *         answer = min(answer, abs(n - ret - ret))
 *         return ret
 *     }
 *
 *     fun solution(n: Int, wires: Array<IntArray>): Int {
 *         vstd = Array(n) {IntArray(n)}
 *         P = Array(n) {BooleanArray(n)}
 *
 *         repeat(wires.size) {
 *             val a = wires[it][0] - 1
 *             val b = wires[it][1] - 1
 *
 *             P[a][b] = true
 *             P[b][a] = true
 *         }
 *
 *         dfs(0, n, wires)
 *
 *
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (0.16ms, 59.3MB)
 * 테스트 2 〉	통과 (0.21ms, 59.3MB)
 * 테스트 3 〉	통과 (0.14ms, 58.7MB)
 * 테스트 4 〉	통과 (0.15ms, 59.3MB)
 * 테스트 5 〉	통과 (0.16ms, 59.4MB)
 * 테스트 6 〉	통과 (0.02ms, 59.1MB)
 * 테스트 7 〉	통과 (0.02ms, 59MB)
 * 테스트 8 〉	통과 (0.03ms, 59.5MB)
 * 테스트 9 〉	통과 (0.03ms, 59.2MB)
 * 테스트 10 〉	통과 (0.22ms, 59.8MB)
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

//      println("${a + 1} <-> ${b + 1} -> $cnt")
//        println("[${a + 1}] -> ${b + 1}")

package 프로그래머스.알고리즘고득점Kit.Greedy.섬연결하기

import util.validate

class Solution {

  val STT = 0
  val INF = Int.MAX_VALUE
  val SEP = 1_000
  fun solution(n: Int, infos: Array<IntArray>): Int {
    val cap = n
    val costs = IntArray(cap * n) { INF }
    fun pos(r: Int, c: Int): Int = r * cap + c

    val g = Array(n) { mutableListOf<Int>() }
    for (info in infos) {
      val a = info[0]
      val b = info[1]
      val c = info[2]
      g[a] += c * SEP + b
      g[b] += c * SEP + a

      costs[pos(a, b)] = c
      costs[pos(b, a)] = c
    }

    val q = IntArray((n - 1) * n / 2)
    var qh = 0
    var qt = 0
    q[qt++] = STT
    while (qh < qt) {
      val p = q[qh++]
      val sib = g[p]


    }

    var ans = 0
    repeat(n - 1) {
      ans += costs[pos(STT, it + 1)]
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(4, arrayOf(
      intArrayOf(0, 1, 1),
      intArrayOf(0, 2, 2),
      intArrayOf(1, 2, 5),
      intArrayOf(1, 3, 1),
      intArrayOf(2, 3, 8),
    )), 4
  )

}

package 프로그래머스.Lv1.실패율

import util.validate

class Solution {

  val scale = 1_000
  val SEP = 1_000
  fun solution(N: Int, stages: IntArray): IntArray {
    val cnts = IntArray(N + 2)
    for (n in stages) cnts[n]++
    var denominator = cnts[N + 1]

    val ans = IntArray(N) { N - it }
    for (n in N downTo 1) {
      val cnt = cnts[n]
      if (cnt == 0) continue
      denominator += cnt
      ans[n - 1] += (cnt * scale / denominator) * SEP
    }

    qs(ans, 0, N - 1)
    for (i in 0 until N) ans[i] = N - (ans[i] % SEP) + 1

    return ans
  }

  fun swap(
    a: IntArray,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(
    a: IntArray,
    l: Int,
    r: Int,
  ) {
    if (l >= r) return

    var pos = l
    var pl = l
    var pr = r
    val piv = a[(l + r) shr 1]

    while (pos <= pr) {
      val x = a[pos]
      when {
        x > piv -> swap(a, pos++, pl++)
        x < piv -> swap(a, pos, pr--)
        else -> pos++
      }
    }
    qs(a, l, pl - 1)
    qs(a, pr + 1, r)
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
  validate(s.solution(5, intArrayOf(2, 1, 2, 6, 2, 4, 3, 3)), intArrayOf(3, 4, 2, 1, 5))
  validate(s.solution(4, intArrayOf(4, 4, 4, 4, 4)), intArrayOf(4, 1, 2, 3))
}

////      println(
////        "[$n] = ${ans[n - 1]}, cnt = $cnt, denominator=$denominator")

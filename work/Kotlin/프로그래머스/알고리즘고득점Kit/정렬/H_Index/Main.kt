package 프로그래머스.알고리즘고득점Kit.정렬.H_Index

import util.validate

class Solution {

  fun solution(a: IntArray): Int {

    val len = a.size
    val last = len - 1
    qs(a, 0, last)

    val fr = 0
    var to = 0
    for (i in last downTo 0) {
      val x = a[i]
      if (x <= len) {
        to = i
        break
      }
    }

    var ans = 0
    for (i in fr..to) {
      val cnt = len - i
      val ctt = a[i]
      val h = if (cnt >= ctt) minOf(cnt, ctt) else 0
      if (h > ans) ans = h
    }

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
        x < piv -> swap(a, pos++, pl++)
        x > piv -> swap(a, pos, pr--)
        else -> pos++
      }
    }
    qs(a, l, pl - 1)
    qs(a, pr + 1, r)
  }
}

/**
 * ```
 * ME v1:
 * ```
 *
 *
 * ```
 * RIVAL:
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(intArrayOf(1, 1, 1)), 1
  )

  validate(
    s.solution(intArrayOf(3, 3)), 0
  )

  validate(
    s.solution(intArrayOf(3, 3, 3)), 3
  )

  validate(
    s.solution(intArrayOf(3, 0, 6, 1, 5)), 3
  )

  validate(
    s.solution(intArrayOf(0, 1, 4, 4, 5)), 1
  )
}

//    println("[$fr -> $to]")
//      println("cnt = $cnt, ctt = $ctt -> $h  vs $ans")

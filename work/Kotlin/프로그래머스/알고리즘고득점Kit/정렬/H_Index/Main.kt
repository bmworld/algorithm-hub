package 프로그래머스.알고리즘고득점Kit.정렬.H_Index

import util.validate

class Solution {

  fun solution(a: IntArray): Int {

    val len = a.size
    qs(a, 0, len - 1)

    var ans = 0
    for (i in 0 until len) {
      val cnt = len - i
      val h = a[i]
      if (cnt < h) break
      ans = h
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
    s.solution(intArrayOf(1, 1, 1, 1, 2)), 1
  )

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
    s.solution(intArrayOf(1, 5, 4, 4, 0)), 1
  )


  validate(
    s.solution(intArrayOf(5, 4, 4, 1, 9)), 4
  )

  validate(
    s.solution(intArrayOf(3, 3)), 0
  )
}

//    println("[$fr -> $to]")
//      println("cnt = $cnt, ctt = $ctt -> $h  vs $ans")
//      println("cnt = $cnt, ctt = $ctt -> $h  vs $ans")

package 프로그래머스.Lv0.뒤에서5등위로

import util.validate

class Solution {

  companion object {

    const val REMOVED = 5
  }

  fun solution(num_list: IntArray): IntArray {
    val N = num_list.size
    qs(num_list, 0, N - 1)
    return IntArray(N - REMOVED) { num_list[REMOVED + it] }
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
 * [ME]
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    intArrayOf(12, 4, 15, 46, 38, 1, 14, 56, 32, 10)),
    intArrayOf(15, 32, 38, 46, 56)
  )
}

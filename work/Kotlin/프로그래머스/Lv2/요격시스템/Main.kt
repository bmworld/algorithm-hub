package 프로그래머스.Lv2.요격시스템

import util.validate

class Solution {
  companion object {

    const val MAX_POS = 100_000_000
    const val SEP = 1_000_000_000L
  }

  fun solution(targets: Array<IntArray>): Int {
    val N = targets.size
    val msle = LongArray(N)
    val tracer = IntArray(MAX_POS)

    for (i in targets.indices) {
      val range = targets[i]
      val s = range[0]
      val e = range[1] - 1
      msle[i] = s * SEP + e
      for (p in s..e) tracer[p]++
    }

    qs(msle, 0, N - 1)

    var ans = 0
    var i = N - 1
    l@ while (i >= 0) {
      ans++
      val cur = msle[i--]
      if (i < 0) break
      val s = (cur / SEP).toInt()
      val e = (cur % SEP).toInt()

      var maxCnt = 0
      var shoot = 0
      for (p in s..e) {
        val cnt = tracer[p]
        if (cnt > maxCnt) {
          maxCnt = cnt
          shoot = p
        }
      }


      while (true) {
        if (i < 0) break@l
        val nxt = msle[i]
        if (shoot in nxt / SEP..nxt % SEP) i--
        else break
      }
    }
    return ans
  }

  fun swap(
    a: LongArray,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(
    a: LongArray,
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
 * 테스트 1 〉	통과 (415.39ms, 441MB)
 * 테스트 2 〉	통과 (154.63ms, 441MB)
 * 테스트 3 〉	실패 (163.87ms, 441MB)
 * 테스트 4 〉	실패 (150.03ms, 443MB)
 * 테스트 5 〉	실패 (194.72ms, 450MB)
 * 테스트 6 〉	실패 (5513.17ms, 476MB)
 * 테스트 7 〉	실패 (시간 초과)
 * 테스트 8 〉	실패 (시간 초과)
 * 테스트 9 〉	실패 (시간 초과)
 * 테스트 10 〉	실패 (시간 초과)
 * 테스트 11 〉	통과 (146.55ms, 441MB)
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      intArrayOf(4, 5),
      intArrayOf(4, 8),
      intArrayOf(10, 14),
      intArrayOf(11, 14),
      intArrayOf(5, 12),
      intArrayOf(3, 7),
      intArrayOf(1, 4),
    )
  ), 3)
}

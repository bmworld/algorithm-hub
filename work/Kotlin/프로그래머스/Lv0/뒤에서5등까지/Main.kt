package 프로그래머스.Lv0.뒤에서5등까지

import util.validate

class Solution {

  fun solution(num_list: IntArray): IntArray {
    qs(num_list, 0, num_list.size - 1)
    return num_list.copyOf(5)
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
 * 테스트 1 〉	통과 (0.02ms, 59.6MB)
 * 테스트 2 〉	통과 (0.02ms, 60.9MB)
 * 테스트 3 〉	통과 (0.03ms, 57.5MB)
 * 테스트 4 〉	통과 (0.02ms, 60.2MB)
 * 테스트 5 〉	통과 (0.02ms, 58MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(num_list: IntArray): IntArray {
 *         return num_list.sortedArray().take(5).toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (13.69ms, 63.5MB)
 * 테스트 2 〉	통과 (14.19ms, 64MB)
 * 테스트 3 〉	통과 (13.86ms, 63.3MB)
 * 테스트 4 〉	통과 (13.85ms, 64.6MB)
 * 테스트 5 〉	통과 (14.09ms, 63MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(12, 4, 15, 46, 38, 1, 14)), intArrayOf(1, 4, 12, 14, 15))
}

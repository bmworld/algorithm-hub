package 프로그래머스.Lv1.실패율

import util.validate

class Solution {

  val SEP = 10.0
  fun solution(N: Int, stages: IntArray): IntArray {
    val cnts = IntArray(N + 2)
    for (n in stages) cnts[n]++
    var denominator = cnts[N + 1]

    val a = DoubleArray(N) { (it + 1) * SEP }
    for (n in N downTo 1) {
      val cnt = cnts[n]
      if (cnt == 0) continue
      denominator += cnt
      a[n - 1] += cnt.toDouble() / denominator
    }

    qs(a, 0, N - 1)
    val ans = IntArray(N)
    var i = 0
    for (x in a) ans[i++] = (x / SEP).toInt()

    return ans
  }

  fun swap(
    a: DoubleArray,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(
    a: DoubleArray,
    l: Int,
    r: Int,
  ) {
    if (l >= r) return
    var pos = l
    var pl = l
    var pr = r
    val e = a[(l + r) shr 1]
    val pivN = e / SEP
    val piv: Double = e % SEP

    while (pos <= pr) {
      val e = a[pos]
      val n = e / SEP
      val x = e % SEP
      when {
        x > piv || x == piv && n < pivN -> swap(a, pos++, pl++)
        x < piv || x == piv && n > pivN -> swap(a, pos, pr--)
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
 * 테스트 1 〉	통과 (0.02ms, 59.8MB)
 * 테스트 2 〉	통과 (0.03ms, 59.5MB)
 * 테스트 3 〉	통과 (0.59ms, 60.2MB)
 * 테스트 4 〉	통과 (1.67ms, 62.2MB)
 * 테스트 5 〉	통과 (2.00ms, 68.8MB)
 * 테스트 6 〉	통과 (0.12ms, 58.3MB)
 * 테스트 7 〉	통과 (0.26ms, 59.4MB)
 * 테스트 8 〉	통과 (1.65ms, 63.4MB)
 * 테스트 9 〉	통과 (2.68ms, 69.2MB)
 * 테스트 10 〉	통과 (1.52ms, 62.7MB)
 * 테스트 11 〉	통과 (1.62ms, 63MB)
 * 테스트 12 〉	통과 (1.60ms, 63MB)
 * 테스트 13 〉	통과 (1.96ms, 66.9MB)
 * 테스트 14 〉	통과 (0.03ms, 58.9MB)
 * 테스트 15 〉	통과 (1.00ms, 60.6MB)
 * 테스트 16 〉	통과 (0.49ms, 61.3MB)
 * 테스트 17 〉	통과 (0.99ms, 61.7MB)
 * 테스트 18 〉	통과 (0.50ms, 59.3MB)
 * 테스트 19 〉	실패 (0.14ms, 59.6MB)
 * 테스트 20 〉	실패 (0.71ms, 60.8MB)
 * 테스트 21 〉	실패 (1.44ms, 61.4MB)
 * 테스트 22 〉	통과 (2.00ms, 70.3MB)
 * 테스트 23 〉	통과 (1.62ms, 66.2MB)
 * 테스트 24 〉	실패 (1.64ms, 64.7MB)
 * 테스트 25 〉	통과 (0.01ms, 59.1MB)
 * 테스트 26 〉	통과 (0.01ms, 58.1MB)
 * 테스트 27 〉	통과 (0.01ms, 59.5MB)
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
  validate(s.solution(2, intArrayOf(3, 3, 3, 3)), intArrayOf(1, 2))
  validate(s.solution(2, intArrayOf(2, 2, 3, 3)), intArrayOf(2, 1))
  validate(s.solution(2, intArrayOf(2, 2)), intArrayOf(2, 1))
  validate(s.solution(10, intArrayOf(2, 2, 10, 1)), intArrayOf(10, 2, 1, 3, 4, 5, 6, 7, 8, 9))
}

//      println("ans[${i - 1}] = ${a[i - 1]}")

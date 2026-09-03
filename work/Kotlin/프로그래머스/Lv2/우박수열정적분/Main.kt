package 프로그래머스.Lv2.우박수열정적분

import util.validate

class Solution {
  companion object {

    const val MAX = 300
    const val GOAL = 1.0
  }

  fun solution(k: Int, ranges: Array<IntArray>): DoubleArray {
    var pSum = DoubleArray(MAX)
    var n = 1
    var k = k.toDouble()

    while (k > GOAL) {
      var nk = 0.0
      pSum[n] = pSum[n++ - 1] + if (k % 2 == 0.0) {
        nk = k / 2
        (k - nk) / 2 + nk
      } else {
        nk = k * 3 + 1
        (nk - k) / 2 + k
      }
      k = nk
    }

    n--

    val ans = DoubleArray(ranges.size)
    for (i in ranges.indices) {
      val range = ranges[i]
      val a = range[0]
      val b = n + range[1]
      ans[i] = when {
        a == b -> 0.0
        a > b -> -1.0
        else -> pSum[b] - pSum[a]
      }
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 61MB)
 * 테스트 2 〉	통과 (0.05ms, 60.9MB)
 * 테스트 3 〉	통과 (0.47ms, 71.9MB)
 * 테스트 4 〉	통과 (0.09ms, 62MB)
 * 테스트 5 〉	통과 (0.05ms, 61.8MB)
 * 테스트 6 〉	통과 (0.14ms, 61.7MB)
 * 테스트 7 〉	통과 (0.40ms, 81.4MB)
 * 테스트 8 〉	통과 (0.55ms, 71MB)
 *
 * [RIVAL]
 * class Solution {
 *     fun solution(k: Int, ranges: Array<IntArray>): DoubleArray {
 *         val points = getPoints(k)
 *
 *         return ranges.map { range ->
 *             getWidth(range, points)
 *         }.toDoubleArray()
 *     }
 *
 *     private fun getWidth(range: IntArray, points: ArrayList<Int>): Double {
 *         val start = range.first()
 *         val end = points.size + range.last() - 1
 *
 *         if (start > end) return -1.0
 *         if (start == end) return 0.0
 *
 *         return (start..end).sumOf { points[it].toDouble() } - (points[start] + points[end]).toDouble() / 2
 *
 *     }
 *
 *     private fun getPoints(k: Int): ArrayList<Int> {
 *         val answer = arrayListOf<Int>()
 *
 *         var kk = k
 *         while (kk > 1) {
 *             answer.add(kk)
 *             if (kk % 2 == 0) kk /= 2
 *             else kk = kk * 3 + 1
 *         }
 *         answer.add(kk)
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (14.12ms, 63.9MB)
 * 테스트 2 〉	통과 (15.67ms, 64.8MB)
 * 테스트 3 〉	통과 (27.97ms, 74.9MB)
 * 테스트 4 〉	통과 (19.71ms, 66.2MB)
 * 테스트 5 〉	통과 (15.10ms, 66.1MB)
 * 테스트 6 〉	통과 (17.78ms, 67.5MB)
 * 테스트 7 〉	통과 (22.29ms, 73.5MB)
 * 테스트 8 〉	통과 (20.55ms, 76.4MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(k: Int, ranges: Array<IntArray>): DoubleArray {
 *         var n = k
 *         val y = mutableListOf<Int>().apply { add(n) }
 *         while (n != 1) {
 *             if (n % 2 == 0) n /= 2
 *             else n = n * 3 + 1
 *             y.add(n)
 *         }
 *         val sum = mutableListOf<Double>()
 *         for (i in 0 until y.size - 1) {
 *             val previous = sum.lastOrNull() ?: 0.toDouble()
 *             sum.add(previous + ((y[i] + y[i+1]) / 2.toDouble()))
 *         }
 *         return ranges.map{ (s, e) ->
 *             val endIndex = sum.size + e
 *             if (s > endIndex) (-1).toDouble()
 *             else if (s == endIndex) (0).toDouble()
 *             else {
 *                 sum[endIndex - 1] - (if (s == 0) 0.toDouble() else sum[s - 1])
 *             }
 *         }.toDoubleArray()
 *     }
 * }
 * 테스트 1 〉	통과 (3.85ms, 59.5MB)
 * 테스트 2 〉	통과 (5.89ms, 61.9MB)
 * 테스트 3 〉	통과 (5.55ms, 70.5MB)
 * 테스트 4 〉	통과 (7.26ms, 62.4MB)
 * 테스트 5 〉	통과 (4.74ms, 62.7MB)
 * 테스트 6 〉	통과 (4.99ms, 64MB)
 * 테스트 7 〉	통과 (5.70ms, 69.5MB)
 * 테스트 8 〉	통과 (5.63ms, 71.4MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(5,
    arrayOf(
      intArrayOf(0, 0),
      intArrayOf(0, -1),
      intArrayOf(2, -3),
      intArrayOf(3, -3),
    )
  ),
    doubleArrayOf(33.0, 31.5, 0.0, -1.0)
  )

  validate(s.solution(3,
    arrayOf(
      intArrayOf(0, 0),
      intArrayOf(1, -2),
      intArrayOf(3, -3),
    )
  ),
    doubleArrayOf(47.0, 36.0, 12.0)
  )
}

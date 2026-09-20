package 프로그래머스.Lv2.요격시스템

import util.validate

class Solution {
  companion object {

    const val SEP = 1_000_000_000L
  }

  fun solution(targets: Array<IntArray>): Int {
    val N = targets.size
    val msle = LongArray(N)

    for (i in targets.indices) {
      val range = targets[i]
      val s = range[0]
      val e = range[1] - 1
      msle[i] = e * SEP + s
    }

    qs(msle, 0, N - 1)

    var ans = 0
    var i = N - 1
    l@ while (i >= 0) {
      ans++
      val m1 = msle[i--]
      var s = m1 % SEP
      var e = m1 / SEP

      while (i >= 0) {
        val m2 = msle[i]
        val e2 = m2 / SEP
        if (e2 < s) break
        else i--
        s = maxOf(s, m2 % SEP)
        e = minOf(e, e2)
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
 * 테스트 1 〉	통과 (0.02ms, 60.7MB)
 * 테스트 2 〉	통과 (0.08ms, 60MB)
 * 테스트 3 〉	통과 (0.13ms, 60.3MB)
 * 테스트 4 〉	통과 (0.95ms, 61.1MB)
 * 테스트 5 〉	통과 (3.91ms, 67.7MB)
 * 테스트 6 〉	통과 (23.99ms, 88.3MB)
 * 테스트 7 〉	통과 (68.43ms, 165MB)
 * 테스트 8 〉	통과 (63.21ms, 160MB)
 * 테스트 9 〉	통과 (63.62ms, 157MB)
 * 테스트 10 〉	통과 (48.47ms, 148MB)
 * 테스트 11 〉	통과 (0.01ms, 60.5MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(targets: Array<IntArray>): Int {
 *         var answer: Int = 0
 *         targets.sortBy{it[1]}
 *         var destination = -1
 *         //exclusive
 *         for(target in targets){
 *             val (s,e) = target
 *             if(destination < s){
 *                 destination = e-1
 *                 answer++
 *             }
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (8.85ms, 62.4MB)
 * 테스트 2 〉	통과 (7.06ms, 63.6MB)
 * 테스트 3 〉	통과 (7.14ms, 63.5MB)
 * 테스트 4 〉	통과 (9.35ms, 64.2MB)
 * 테스트 5 〉	통과 (18.88ms, 71.6MB)
 * 테스트 6 〉	통과 (82.36ms, 97.9MB)
 * 테스트 7 〉	통과 (282.63ms, 181MB)
 * 테스트 8 〉	통과 (279.87ms, 305MB)
 *
 * [RIVAL 2]
 * data class Range(
 *     val start: Int,
 *     val end: Int,
 * ) {
 *     fun commonWith(other: Range): Range? = when {
 *         start >= other.end || end <= other.start -> null
 *
 *         else -> Range(
 *             maxOf(start, other.start),
 *             minOf(end, other.end)
 *         )
 *     }
 * }
 *
 * class Solution {
 *     fun solution(targets: Array<IntArray>): Int {
 *         var ans = 1
 *
 *         targets.map { Range(it[0], it[1]) }.sortedBy { it.start }.reduce { common, target ->
 *             target.commonWith(common) ?: run {
 *                 ans ++
 *                 target
 *             }
 *         }
 *
 *         return ans
 *     }
 * }
 * 테스트 1 〉	통과 (12.06ms, 63.2MB)
 * 테스트 2 〉	통과 (11.35ms, 64.2MB)
 * 테스트 3 〉	통과 (12.24ms, 65.5MB)
 * 테스트 4 〉	통과 (12.72ms, 65MB)
 * 테스트 5 〉	통과 (20.03ms, 74MB)
 * 테스트 6 〉	통과 (54.71ms, 102MB)
 * 테스트 7 〉	통과 (162.54ms, 196MB)
 * 테스트 8 〉	통과 (222.43ms, 323MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      intArrayOf(4, 5),
      intArrayOf(4, 8),
      intArrayOf(10, 14),
      intArrayOf(11, 13),
      intArrayOf(5, 12),
      intArrayOf(3, 7),
      intArrayOf(1, 4),
    )
  ), 3)
}

package 프로그래머스.입문.Day23.특이한정렬

import util.validate

class Solution {

  fun solution(a: IntArray, n: Int): IntArray {
    val len = a.size
    for (i in 0 until len) a[i] = a[i] - n

    qs(a, 0, len - 1)

    for (i in 0 until len) a[i] = a[i] + n
    return a
  }

  fun abs(v: Int): Int = if (v < 0) -v else v

  fun swap(a: IntArray, i: Int, j: Int) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(a: IntArray, l: Int, r: Int) {
    if (l >= r) return
    val m = sort(a, l, r)
    qs(a, l, m - 1)
    qs(a, m + 1, r)
  }

  fun sort(a: IntArray, l: Int, r: Int): Int {
    val m = (l + r) shr 1
    val piv = a[m]
    swap(a, m, r)

    var pos = l
    for (i in l until r) if (comp(a[i], piv)) swap(a, pos++, i)
    if (comp(piv, a[pos])) swap(a, pos, r)

    return pos
  }

  fun comp(v: Int, standard: Int): Boolean {
    val dist = abs(v)
    val sDist = abs(standard)
    return dist < sDist || dist == sDist && v > standard
  }
}

fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(1, 2, 3, 4, 5, 6), 4), intArrayOf(4, 5, 3, 6, 2, 1))
  validate(s.solution(intArrayOf(10000, 20, 36, 47, 40, 6, 10, 7000), 30),
    intArrayOf(36, 40, 20, 47, 10, 6, 7000, 10000))
}

//println("--AT: a[$it] = ${a[it]}")

/**
 * ME
 * 테스트 1 〉	통과 (0.17ms, 62.2MB)
 * 테스트 2 〉	통과 (0.17ms, 62.4MB)
 * 테스트 3 〉	통과 (0.11ms, 62.8MB)
 * 테스트 4 〉	통과 (0.16ms, 63.5MB)
 * 테스트 5 〉	통과 (0.02ms, 61.1MB)
 * 테스트 6 〉	통과 (0.02ms, 61.9MB)
 * 테스트 7 〉	통과 (0.01ms, 62.1MB)
 *
 *
 * RIVAL
 * import kotlin.math.abs
 *
 * class Solution {
 *     fun solution(numList: IntArray, n: Int): IntArray {
 *         return numList.sortedWith { a, b ->
 *             if (abs(a - n) == abs(b - n)) b.compareTo(a) else abs(a - n).compareTo(abs(b - n))
 *         }.toIntArray()
 *     }
 * }
 * 테스트 2 〉	통과 (15.27ms, 66.7MB)
 * 테스트 3 〉	통과 (14.95ms, 66MB)
 * 테스트 4 〉	통과 (14.98ms, 67.2MB)
 * 테스트 5 〉	통과 (13.42ms, 66.5MB)
 * 테스트 6 〉	통과 (16.38ms, 66.7MB)
 * 테스트 7 〉	통과 (13.58ms, 66.5MB)
 */

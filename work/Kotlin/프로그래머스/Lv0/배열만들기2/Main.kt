package 프로그래머스.Lv0.배열만들기2

import util.validate

class Solution {

  fun solution(l: Int, r: Int): IntArray {
    val cnds = createCnds(r)
    val fr = binSearch(l, cnds, true)
    val to = binSearch(r, cnds, false)

    return if (fr > to || cnds[to] > r)
      intArrayOf(-1) else
      cnds.copyOfRange(fr, to + 1)
  }

  private fun createCnds(r: Int): IntArray {
    val maxDigit = getLen(r)
    val size = (1 shl maxDigit) - 1
    val cnds = IntArray(size)
    var cnt = 0
    repeat(maxDigit) { prvDigit ->
      val prvCnt = cnt
      val x = 5 * pow(10, prvDigit)
      cnds[cnt++] = x
      repeat(prvCnt) { cnds[cnt++] = x + cnds[it] }
    }
    return cnds
  }

  fun binSearch(x: Int, arr: IntArray, goe: Boolean): Int {
    var l = 0
    var r = arr.size - 1
    while (l <= r) {
      val m = (l + r) shr 1
      val mv = arr[m]
      when {
        x == mv -> return m
        x < mv -> r = m - 1
        else -> l = m + 1
      }
    }
    return if (goe) l else r
  }

  fun getLen(n: Int): Int {
    var len = 0
    var t = n
    while (t > 0) {
      len++
      t /= 10
    }
    return len
  }

  fun pow(n: Int, p: Int): Int {
    if (p == 0) return 1
    var r = 1
    repeat(p) { r *= n }
    return r
  }
}

/**
 * ```
 * [ME]
 * v1:
 * 테스트 1 〉	실패 (0.01ms, 57.8MB)
 * 테스트 2 〉	실패 (0.02ms, 61.4MB)
 * 테스트 3 〉	실패 (0.01ms, 60.7MB)
 * 테스트 4 〉	실패 (0.02ms, 60MB)
 * 테스트 5 〉	실패 (0.02ms, 60.1MB)
 * 테스트 6 〉	실패 (0.01ms, 59.3MB)
 * 테스트 7 〉	실패 (0.01ms, 60.2MB)
 * 테스트 8 〉	실패 (0.01ms, 61.4MB)
 * 테스트 9 〉	실패 (0.01ms, 60MB)
 * 테스트 10 〉	실패 (0.01ms, 59.9MB)
 * 테스트 11 〉	실패 (0.01ms, 57.7MB)
 * 테스트 12 〉	통과 (0.01ms, 59.7MB)
 * 테스트 13 〉	실패 (0.01ms, 60.3MB)
 * 테스트 14 〉	실패 (0.02ms, 60.2MB)
 * 테스트 15 〉	통과 (0.01ms, 59.5MB)
 * 테스트 16 〉	통과 (0.01ms, 60.4MB)
 * 테스트 17 〉	통과 (0.01ms, 59.1MB)
 * 테스트 18 〉	실패 (0.02ms, 60.5MB)
 * 테스트 19 〉	통과 (0.01ms, 59.9MB)
 * 테스트 20 〉	통과 (0.01ms, 60.1MB)
 * 테스트 21 〉	실패 (0.01ms, 60.5MB)
 * 테스트 22 〉	통과 (0.01ms, 60.9MB)
 * v2:
 * 테스트 1 〉	통과 (6.41ms, 64MB)
 * 테스트 2 〉	통과 (6.22ms, 64.1MB)
 * 테스트 3 〉	통과 (6.29ms, 63.1MB)
 * 테스트 4 〉	통과 (6.44ms, 63.2MB)
 * 테스트 5 〉	통과 (6.46ms, 64MB)
 * 테스트 6 〉	통과 (6.36ms, 63.9MB)
 * 테스트 7 〉	통과 (6.50ms, 61MB)
 * 테스트 8 〉	통과 (9.22ms, 63.8MB)
 * 테스트 9 〉	통과 (6.34ms, 64.6MB)
 * 테스트 10 〉	통과 (6.47ms, 62.5MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(l: Int, r: Int): List<Int> {
 *         return (l..r).filter { it.toString().all { ch -> ch == '0' || ch == '5' } }.takeIf(List<Int>::isNotEmpty) ?: listOf(-1)
 *     }
 * }
 * 테스트 1 〉	통과 (2.56ms, 60.4MB)
 * 테스트 2 〉	통과 (2.53ms, 59.1MB)
 * 테스트 3 〉	통과 (4.07ms, 61.3MB)
 * 테스트 4 〉	통과 (3.82ms, 62.6MB)
 * 테스트 5 〉	통과 (3.97ms, 60.9MB)
 * 테스트 6 〉	통과 (3.10ms, 60.9MB)
 * 테스트 7 〉	통과 (12.17ms, 66.3MB)
 * 테스트 8 〉	통과 (15.96ms, 68.2MB)
 * 테스트 9 〉	통과 (14.87ms, 69.9MB)
 * 테스트 10 〉	통과 (35.56ms, 99MB)
 * 테스트 11 〉	통과 (9.80ms, 65.8MB)
 * 테스트 12 〉	통과 (4.38ms, 60.8MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(l: Int, r: Int): IntArray {
 *         return (l .. r)
 *             .filter { num -> num.toString().all { it == '0' || it == '5' } }
 *             .let { if (it.isEmpty()) intArrayOf(-1) else it.toIntArray() }
 *     }
 *
 * 테스트 1 〉	통과 (5.32ms, 61.4MB)
 * 테스트 2 〉	통과 (5.71ms, 61.1MB)
 * 테스트 3 〉	통과 (7.80ms, 62MB)
 * 테스트 4 〉	통과 (8.40ms, 61.6MB)
 * 테스트 5 〉	통과 (7.00ms, 62.4MB)
 * 테스트 6 〉	통과 (5.84ms, 61MB)
 * 테스트 7 〉	통과 (16.90ms, 67.6MB)
 * 테스트 8 〉	통과 (18.98ms, 68MB)
 * 테스트 9 〉	통과 (20.52ms, 72.3MB)
 * 테스트 10 〉	통과 (34.60ms, 102MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(5, 555), intArrayOf(5, 50, 55, 500, 505, 550, 555))
  validate(s.solution(10, 20), intArrayOf(-1))
  validate(s.solution(6, 50), intArrayOf(50))
  validate(s.solution(50, 50), intArrayOf(50))
  validate(s.solution(3, 4), intArrayOf(-1))
  validate(s.solution(3, 5), intArrayOf(5))
  validate(s.solution(5, 5), intArrayOf(5))
  validate(s.solution(5, 56), intArrayOf(5, 50, 55))
  validate(s.solution(5, 54), intArrayOf(5, 50))
  validate(s.solution(4, 10), intArrayOf(5))
  validate(s.solution(501, 556), intArrayOf(505, 550, 555))
}

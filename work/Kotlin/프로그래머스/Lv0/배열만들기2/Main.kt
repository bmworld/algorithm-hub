package 프로그래머스.Lv0.배열만들기2

import util.validate

class Solution {

  fun solution(l: Int, r: Int): IntArray {
    val s = getLen(l)
    val e = getLen(r)

    val ans = IntArray((1 shl e) - 1)
    var cnt = 0

    l@ for (i in s..e) {
      val x = 5 * pow(10, i - 1)
      val prv = cnt
      if (x in l..r) ans[cnt++] = x else break@l

      for (j in 0 until prv) {
        val x2 = x + ans[j]
        if (x2 in l..r) ans[cnt++] = x2 else break@l
      }
    }

    return if (cnt == 0) intArrayOf(-1) else ans.copyOf(cnt)
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
 * 채점
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(5, 555), intArrayOf(5, 50, 55, 500, 505, 550, 555))
  validate(s.solution(10, 20), intArrayOf(-1))
  validate(s.solution(3, 4), intArrayOf(-1))
  validate(s.solution(3, 5), intArrayOf(5))
  validate(s.solution(5, 56), intArrayOf(5, 50, 55))
  validate(s.solution(5, 54), intArrayOf(5, 50))
  validate(s.solution(5, 54), intArrayOf(5, 50))
}

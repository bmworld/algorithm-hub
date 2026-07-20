package 프로그래머스.Lv2.롤케이크자르기

import util.validate

class Solution {

  companion object {

    const val MAX = 10_000
    const val EMPTY = 0
  }

  fun solution(topping: IntArray): Int {
    var ans = 0

    val whole = IntArray(MAX + 1)
    var b = 0
    for (x in topping) if (whole[x]++ == EMPTY) b++
    if (b % 2 != 0) return 0

    var a = 0
    val aPart = IntArray(MAX + 1)
    for (i in 0 until topping.size - 1) {
      val x = topping[i]
      val aCnt = aPart[x]++ + 1
      if (aCnt == 1) a++
      if (aCnt == whole[x]) b--

      if (a == b) ans++
      else if (a > b) break
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 2 〉	실패 (1.68ms, 65.4MB)
 * 테스트 3 〉	통과 (1.92ms, 62.9MB)
 * 테스트 4 〉	통과 (2.04ms, 63.4MB)
 * 테스트 5 〉	통과 (9.30ms, 90.7MB)
 * 테스트 6 〉	실패 (3.70ms, 97.8MB)
 * 테스트 7 〉	실패 (3.48ms, 102MB)
 * 테스트 8 〉	실패 (4.96ms, 99.1MB)
 * 테스트 9 〉	통과 (4.90ms, 97.9MB)
 * 테스트 10 〉	통과 (3.62ms, 98.2MB)
 * 테스트 11 〉	통과 (1.61ms, 64.1MB)
 * 테스트 12 〉	통과 (0.39ms, 61.5MB)
 * 테스트 13 〉	통과 (6.40ms, 98.8MB)
 * 테스트 14 〉	실패 (3.76ms, 99.1MB)
 * 테스트 15 〉	실패 (3.34ms, 99.5MB)
 * 테스트 16 〉	실패 (3.26ms, 99.4MB)
 * 테스트 17 〉	통과 (6.72ms, 98.8MB)
 * 테스트 18 〉	실패 (4.57ms, 100MB)
 * 테스트 19 〉	통과 (4.28ms, 99.9MB)
 * 테스트 20 〉	실패 (3.38ms, 99.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(1, 2, 1, 3, 1, 4, 1, 2)), 2)
  validate(s.solution(intArrayOf(1, 2, 3, 1, 4)), 0)
}

//      println("[${i}] cur= $aCnt")

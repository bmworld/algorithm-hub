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
    if (b > 1 && b % 2 != 0) return 0

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
 * 테스트 1 〉	통과 (0.53ms, 61.2MB)
 * 테스트 2 〉	실패 (2.16ms, 63.2MB)
 * 테스트 3 〉	통과 (2.22ms, 62.5MB)
 * 테스트 4 〉	통과 (2.43ms, 62.8MB)
 * 테스트 5 〉	통과 (10.69ms, 91.5MB)
 * 테스트 6 〉	실패 (3.60ms, 97.4MB)
 * 테스트 7 〉	실패 (4.40ms, 97.6MB)
 * 테스트 8 〉	실패 (4.03ms, 98MB)
 * 테스트 9 〉	통과 (3.46ms, 98.5MB)
 * 테스트 10 〉	통과 (5.46ms, 101MB)
 * 테스트 11 〉	통과 (1.78ms, 61.6MB)
 * 테스트 12 〉	통과 (0.51ms, 60.4MB)
 * 테스트 13 〉	통과 (9.69ms, 99.8MB)
 * 테스트 14 〉	실패 (3.41ms, 101MB)
 * 테스트 15 〉	실패 (4.85ms, 97.9MB)
 * 테스트 16 〉	실패 (3.58ms, 97.8MB)
 * 테스트 17 〉	통과 (5.75ms, 100MB)
 * 테스트 18 〉	실패 (3.23ms, 99.1MB)
 * 테스트 19 〉	통과 (4.61ms, 99.2MB)
 * 테스트 20 〉	실패 (5.82ms, 98.1MB)
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
  validate(s.solution(intArrayOf(1, 1, 1)), 2)
}

//      println("[${i}] cur= $aCnt")

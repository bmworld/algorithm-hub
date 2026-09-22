package 프로그래머스.Lv2.두원사이의정수쌍

import util.validate

class Solution {

  fun solution(r1: Int, r2: Int): Long {
    var ans = 0L

    var x2 = r2.toLong()
    var y2 = 0L
    val r2r2 = r2 * r2.toLong()

    var x1 = r1.toLong()
    var y1 = 0L
    val r1r1 = r1 * r1.toLong()

    while (y2 < r2) {
      while (x2 * x2 + y2 * y2 > r2r2) x2--
      while (x1 > 0 && x1 * x1 + y1 * y1 > r1r1) x1--
      ans += (x2 - x1 + if (x1 * x1 == r1r1) 1 else 0) * 4
      y2++
      y1++
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 60.2MB)
 * 테스트 2 〉	실패 (0.03ms, 59.1MB)
 * 테스트 3 〉	통과 (0.02ms, 57.4MB)
 * 테스트 4 〉	실패 (0.13ms, 59.7MB)
 * 테스트 5 〉	실패 (0.08ms, 60MB)
 * 테스트 6 〉	실패 (0.19ms, 60.2MB)
 * 테스트 7 〉	통과 (7.37ms, 60.4MB)
 * 테스트 8 〉	실패 (11.12ms, 59.3MB)
 * 테스트 9 〉	실패 (8.36ms, 59.5MB)
 * 테스트 10 〉	통과 (5.67ms, 58.8MB)
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(1, 2), 12)
  validate(s.solution(2, 3), 20)
}

//      println("r2 ($x2, $y2) vs r1($x1, $y1) -> $cnt")

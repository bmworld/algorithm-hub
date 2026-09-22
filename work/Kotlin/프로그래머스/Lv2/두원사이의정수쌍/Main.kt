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

    while (y2 <= r2) {
      while (x2 * x2 + y2 * y2 > r2r2) x2--
      while (x1 > 0 && x1 * x1 + y1 * y1 > r1r1) x1--
      ans += when {
        y2 == 0L -> (x2 - x1 + 1) * 2
        y2 >= r1 -> (x2 * 2 + 1) * 2
        else -> (x2 - x1 + if (x1 * x1 == r1r1) 1 else 0) * 4
      }
      y2++
      y1++
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.9MB)
 * 테스트 2 〉	실패 (0.02ms, 61.3MB)
 * 테스트 3 〉	통과 (0.02ms, 59.3MB)
 * 테스트 4 〉	실패 (0.17ms, 58.9MB)
 * 테스트 5 〉	실패 (0.09ms, 58.9MB)
 * 테스트 6 〉	실패 (0.21ms, 59.8MB)
 * 테스트 7 〉	통과 (10.80ms, 60.1MB)
 * 테스트 8 〉	실패 (15.37ms, 60.3MB)
 * 테스트 9 〉	실패 (9.44ms, 60.4MB)
 * 테스트 10 〉	통과 (6.24ms, 58.4MB)
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

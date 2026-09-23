package 프로그래머스.Lv2.두원사이의정수쌍

import util.validate

class Solution {
  companion object {

    const val OUTER_EDGES_OF_R1 = 4
  }

  fun solution(r1: Int, r2: Int): Long {
    var ans = 0L

    val r2r2 = r2 * r2.toLong()
    var x2 = r2.toLong()
    var y2 = 0L

    loop1@ while (x2 > y2) {
      while (r2r2 >= x2 * x2 + (y2 + 1) * (y2 + 1)) {
        if (x2 == y2 + 1) break@loop1
        else y2++
      }
      ans += 8 * y2 + 4
      x2--
    }
    ans += (2 * x2 + 1) * (2 * x2 + 1)

    val r1r1 = r1 * r1.toLong()
    var x1 = r1.toLong()
    var y1 = 0L
    loop2@ while (x1 > y1) {
      while (r1r1 >= x1 * x1 + (y1 + 1) * (y1 + 1)) {
        if (x1 == y1 + 1) break@loop2
        else y1++
      }
      ans -= 8 * y1 + 4
      x1--
    }
    ans -= (2 * x1 + 1) * (2 * x1 + 1)

    return ans + OUTER_EDGES_OF_R1
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.03ms, 60.6MB)
 * 테스트 2 〉	실패 (0.02ms, 60MB)
 * 테스트 3 〉	통과 (0.02ms, 60.1MB)
 * 테스트 4 〉	실패 (0.07ms, 59.7MB)
 * 테스트 5 〉	실패 (0.06ms, 58.9MB)
 * 테스트 6 〉	실패 (0.13ms, 60.6MB)
 * 테스트 7 〉	통과 (4.15ms, 59.3MB)
 * 테스트 8 〉	실패 (5.63ms, 58.1MB)
 * 테스트 9 〉	실패 (4.63ms, 57.8MB)
 * 테스트 10 〉	통과 (3.95ms, 58.9MB)
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
  validate(s.solution(4, 5), 36)
  validate(s.solution(1, 1_000_000), 3_141_592_649_624)
}

// R5 = 49 + 32  = 81
// R4 = 25 + 24 = 49
//    println("R2 = (${x2}, $y2) -> $ans")
//    println("R1 = (${x1}, $y1) -> $ans")

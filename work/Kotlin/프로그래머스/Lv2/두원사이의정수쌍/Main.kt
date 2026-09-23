package 프로그래머스.Lv2.두원사이의정수쌍

import util.validate

class Solution {
  companion object {

    const val EDGES_ON_AXIS = 4
  }

  fun solution(r1: Int, r2: Int): Long {

    var r2c = 0L
    val r2r2 = r2 * r2.toLong()
    var x2 = r2.toLong()
    var y2 = 0L

    loop1@ while (x2 > y2) {
      while (r2r2 >= x2 * x2 + (y2 + 1) * (y2 + 1)) {
        if (x2 == y2 + 1) break@loop1
        else y2++
      }
      r2c += 8 * y2 + EDGES_ON_AXIS
      x2--
    }
    r2c += (2 * x2 + 1) * (2 * x2 + 1)

    var r1c = 0L
    val r1r1 = r1 * r1.toLong()
    var x1 = r1.toLong() - 1
    var y1 = 0L

    loop2@ while (x1 > y1) {
      while (r1r1 >= x1 * x1 + (y1 + 1) * (y1 + 1)) {
        if (x1 == y1 + 1) break@loop2
        else y1++
      }
      r1c += 8 * (maxOf(0, y1 - if (y1 > 0 && x1 * x1 + y1 * y1 == r1r1) 1 else 0)) + EDGES_ON_AXIS
      x1--
    }
    r1c += (2 * x1 + 1) * (2 * x1 + 1)

    return r2c - r1c
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.1MB)
 * 테스트 2 〉	통과 (0.03ms, 60.5MB)
 * 테스트 3 〉	통과 (0.02ms, 60.3MB)
 * 테스트 4 〉	통과 (0.09ms, 60.5MB)
 * 테스트 5 〉	통과 (0.05ms, 61.1MB)
 * 테스트 6 〉	통과 (0.15ms, 59.5MB)
 * 테스트 7 〉	통과 (4.43ms, 59.6MB)
 * 테스트 8 〉	통과 (6.10ms, 60.3MB)
 *
 * [RIVAL 1]
 * import kotlin.math.*
 * class Solution {
 *     fun solution(r1: Int, r2: Int): Long {
 *         var answer: Long = 0
 *
 *         for(r in 1 .. r2){
 *             val upper = floor(sqrt(r2*r2.toDouble() - r*r.toDouble())).toLong()
 *             val lower = ceil(sqrt(r1*r1.toDouble() - r*r.toDouble())).toLong()
 *             answer += upper - lower + 1
 *         }
 *         return 4 * answer
 *     }
 * }
 * 테스트 1 〉	통과 (0.03ms, 59.4MB)
 * 테스트 2 〉	통과 (0.03ms, 60.9MB)
 * 테스트 3 〉	통과 (0.07ms, 60MB)
 * 테스트 4 〉	통과 (0.38ms, 60.4MB)
 * 테스트 5 〉	통과 (0.38ms, 58.7MB)
 * 테스트 6 〉	통과 (0.59ms, 60MB)
 * 테스트 7 〉	통과 (9.32ms, 59.9MB)
 * 테스트 8 〉	통과 (9.88ms, 59.7MB)
 *
 *
 *
 * [RIVAL 2]
 * import kotlin.math.sqrt
 *
 * fun Int.square() = this * toLong()
 * fun Long.sqrt() = sqrt(toDouble())
 *
 * class Solution {
 *     fun solution(r1: Int, r2: Int): Long = (0..r2).sumOf { x ->
 *         val y1 = (r1.square() - x.square()).sqrt().toLong()
 *         val y2 = (r2.square() - x.square()).sqrt().toLong()
 *         (y2 - y1) * 4 + if (y1.toDouble() == (r1.square() - x.square()).sqrt()) 4 else 0
 *     } - 4
 * }
 * 테스트 1 〉	통과 (1.35ms, 60.1MB)
 * 테스트 2 〉	통과 (1.29ms, 60.4MB)
 * 테스트 3 〉	통과 (1.55ms, 60.3MB)
 * 테스트 4 〉	통과 (1.60ms, 60MB)
 * 테스트 5 〉	통과 (2.14ms, 59.9MB)
 * 테스트 6 〉	통과 (1.69ms, 59MB)
 * 테스트 7 〉	통과 (10.89ms, 59.8MB)
 * 테스트 8 〉	통과 (13.88ms, 60.3MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(5, 5), 12)
  validate(s.solution(1, 2), 12)
  validate(s.solution(2, 3), 20)
  validate(s.solution(4, 5), 36)
  validate(s.solution(5, 6), 44)
  validate(s.solution(5, 7), 80)
}

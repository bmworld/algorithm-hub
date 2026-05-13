package 프로그래머스.입문.Day25.연속된수의합

import util.validate

class Solution {

  fun solution(seq: Int, total: Int): IntArray {
    val a = IntArray(seq)

    val avg = (total + seq - 1) / seq
    val m = seq / 2

    val oddSeq = seq % 2 == 1
    if (oddSeq) a[m] = avg

    repeat(m) {
      val ld = it + 1
      val rd = it + if (oddSeq) 1 else 0
      val l = m - ld
      val r = m + rd

      a[l] = avg - ld
      a[r] = avg + rd
    }

    return a
  }
}

/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (0.01ms, 63.5MB)
 * 테스트 2 〉	통과 (0.01ms, 64.2MB)
 * 테스트 3 〉	통과 (0.01ms, 62MB)
 * 테스트 4 〉	통과 (0.01ms, 64.2MB)
 * 테스트 5 〉	통과 (0.01ms, 63.5MB)
 * 테스트 6 〉	통과 (0.01ms, 65.4MB)
 * 테스트 7 〉	통과 (0.01ms, 63.9MB)
 * 테스트 8 〉	통과 (0.01ms, 62.5MB)
 * 테스트 9 〉	통과 (0.01ms, 63MB)
 * 테스트 10 〉	통과 (0.01ms, 64.9MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * class Solution {
 *     fun solution(num: Int, total: Int): IntArray =
 *     (total / num - (num - 1) / 2).let { t -> IntArray(num) { index -> index + t } }
 *
 * }
 * 테스트 1 〉	통과 (0.01ms, 62.6MB)
 * 테스트 2 〉	통과 (0.01ms, 63MB)
 * 테스트 3 〉	통과 (0.01ms, 62.2MB)
 * 테스트 4 〉	통과 (0.01ms, 62.1MB)
 * 테스트 5 〉	통과 (0.02ms, 61.6MB)
 * 테스트 6 〉	통과 (0.01ms, 62.5MB)
 * 테스트 7 〉	통과 (0.01ms, 62.3MB)
 * 테스트 8 〉	통과 (0.01ms, 62.1MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(3, 12), intArrayOf(3, 4, 5))
  validate(s.solution(5, 15), intArrayOf(1, 2, 3, 4, 5))
  validate(s.solution(4, 14), intArrayOf(2, 3, 4, 5))
  validate(s.solution(5, 5), intArrayOf(-1, 0, 1, 2, 3))
  validate(s.solution(1, 1), intArrayOf(1))
  validate(s.solution(3, 0), intArrayOf(-1, 0, 1))
  validate(s.solution(4, 3), intArrayOf(-1, 0, 1, 2))
  validate(s.solution(2, 1), intArrayOf(0, 1))
}

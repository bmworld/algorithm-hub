package 프로그래머스.알고리즘고득점Kit.완전탐색.카펫

import util.validate

class Solution {

  fun solution(brown: Int, yellow: Int): IntArray {
    var h = 1
    var w = yellow
    while (h <= w) {
      if (yellow % h == 0 && brown == w * 2 + h * 2 + 4) break
      w = yellow / ++h
    }

    return intArrayOf(w + 2, h + 2)
  }
}

/**
 * ```
 * ME: v1
 * 테스트 1 〉	통과 (0.01ms, 65MB)
 * 테스트 2 〉	통과 (0.01ms, 65MB)
 * 테스트 3 〉	통과 (0.02ms, 64.7MB)
 * 테스트 4 〉	통과 (0.01ms, 64.9MB)
 * 테스트 5 〉	통과 (0.01ms, 61.9MB)
 * 테스트 6 〉	통과 (0.02ms, 63.4MB)
 * 테스트 7 〉	통과 (0.02ms, 63.7MB)
 * 테스트 8 〉	통과 (0.02ms, 63.6MB)
 * 테스트 9 〉	통과 (0.05ms, 65.9MB)
 * 테스트 10 〉	통과 (0.02ms, 65.3MB)
 * 테스트 11 〉	통과 (0.01ms, 61.1MB)
 * 테스트 12 〉	통과 (0.01ms, 65.5MB)
 * 테스트 13 〉	통과 (0.01ms, 65.2MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * class Solution {
 *     fun solution(brown: Int, red: Int): IntArray {
 *         return (1..red)
 *             .filter { red % it == 0 }
 *             .first { brown == (red / it * 2) + (it * 2) + 4 }
 *             .let { intArrayOf(red / it + 2, it + 2) }
 *     }
 * }
 *
 * 테스트 1 〉	통과 (1.10ms, 65.2MB)
 * 테스트 2 〉	통과 (1.15ms, 63.9MB)
 * 테스트 3 〉	통과 (14.04ms, 79.9MB)
 * 테스트 4 〉	통과 (1.52ms, 63.2MB)
 * 테스트 5 〉	통과 (1.72ms, 64.5MB)
 * 테스트 6 〉	통과 (7.97ms, 68.4MB)
 * 테스트 7 〉	통과 (18.90ms, 79MB)
 * 테스트 8 〉	통과 (17.89ms, 78MB)
 * 테스트 9 〉	통과 (20.29ms, 78.7MB)
 * 테스트 10 〉	통과 (19.19ms, 78.6MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(10, 2), intArrayOf(4, 3))
  validate(s.solution(8, 1), intArrayOf(3, 3))
  validate(s.solution(24, 24), intArrayOf(8, 6))
  validate(s.solution(32, 24), intArrayOf(14, 4))
}

//        println("[$dep] i = ${i}, ${used[i]}")

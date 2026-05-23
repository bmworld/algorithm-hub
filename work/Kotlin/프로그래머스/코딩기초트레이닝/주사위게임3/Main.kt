package 프로그래머스.코딩기초트레이닝.주사위게임3

import util.validate

class Solution {

  fun solution(a: Int, b: Int, c: Int, d: Int): Int {
    val sum = a + b + c + d

    return when {
      a == b && b == c && c == d -> 1111 * a
      sum - a == 3 * b -> case3x1(b, a)
      sum - b == 3 * c -> case3x1(c, b)
      sum - c == 3 * d -> case3x1(d, c)
      sum - d == 3 * a -> case3x1(a, d)
      else -> {
        val tmp = IntArray(4)
        var len = 0
        val arr = intArrayOf(a, b, c, d)
        for (i in 0..3)
          for (j in i + 1..3) {
            val a = arr[i]
            val b = arr[j]
            if (a == b) tmp[len++] = a
          }

        val p = tmp[0]

        when (len) {
          2 -> case2x2(p, tmp[1])
          1 -> {
            len = 0
            for (x in arr) if (x != p) tmp[len++] = x
            tmp[0] * tmp[1]
          }
          else -> minOf(a, b, c, d)
        }
      }
    }
  }

  fun case2x2(p: Int, q: Int): Int {
    var sub = p - q
    if (sub < 0) sub = -sub
    return (p + q) * sub
  }

  fun case3x1(p: Int, q: Int): Int {
    val t = p * 10 + q
    return t * t
  }
}

/**
 * ```
 * ME v1:
 * 테스트 1 〉	통과 (0.01ms, 63.6MB)
 * 테스트 2 〉	통과 (0.01ms, 62.5MB)
 * 테스트 3 〉	통과 (0.01ms, 62.6MB)
 * 테스트 4 〉	통과 (0.01ms, 64MB)
 * 테스트 5 〉	통과 (0.01ms, 63.3MB)
 * 테스트 6 〉	통과 (0.01ms, 63.1MB)
 * 테스트 7 〉	통과 (0.01ms, 64.1MB)
 * 테스트 8 〉	통과 (0.01ms, 62.9MB)
 * 테스트 9 〉	통과 (0.01ms, 61.9MB)
 * 테스트 10 〉	실패 (0.01ms, 62.6MB)
 * 테스트 11 〉	실패 (0.03ms, 63.9MB)
 * 테스트 12 〉	통과 (0.01ms, 62.7MB)
 * 테스트 13 〉	통과 (1.15ms, 63.5MB)
 * 테스트 14 〉	통과 (0.01ms, 63.8MB)
 * 테스트 15 〉	통과 (0.01ms, 62.4MB)
 * 테스트 16 〉	통과 (0.01ms, 62.4MB)
 * 테스트 17 〉	통과 (0.72ms, 63.8MB)
 * 테스트 18 〉	통과 (0.01ms, 64.4MB)
 * 테스트 19 〉	통과 (0.01ms, 62.2MB)
 * 테스트 20 〉	실패 (0.01ms, 62.5MB)
 * 테스트 21 〉	통과 (0.01ms, 63MB)
 * 테스트 22 〉	통과 (0.01ms, 64.3MB)
 * 테스트 23 〉	실패 (0.01ms, 61.8MB)
 * 테스트 24 〉	통과 (0.01ms, 64MB)
 * 테스트 25 〉	통과 (0.02ms, 62.2MB)
 * 테스트 26 〉	통과 (0.01ms, 62.5MB)
 * 테스트 27 〉	실패 (0.01ms, 62.3MB)
 * 테스트 28 〉	실패 (0.01ms, 63.5MB)
 * 테스트 29 〉	통과 (0.01ms, 64.3MB)
 * 테스트 30 〉	통과 (0.01ms, 63.3MB)
 * 테스트 31 〉	통과 (0.01ms, 62.6MB)
 * 테스트 32 〉	통과 (0.01ms, 63.5MB)
 * 테스트 33 〉	통과 (0.01ms, 64.2MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(2, 2, 2, 2), 2222
  )

  validate(
    s.solution(4, 4, 4, 1), 1681
  )

  validate(
    s.solution(6, 6, 3, 3), 27
  )

  validate(
    s.solution(2, 2, 5, 6), 30
  )

  validate(
    s.solution(2, 2, 7, 7), s.case2x2(7, 2)
  )

  validate(
    s.solution(2, 4, 5, 6), 2
  )


}

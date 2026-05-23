package 프로그래머스.코딩기초트레이닝.주사위게임3

import util.validate

class Solution {

  val SEP = 10
  fun solution(a: Int, b: Int, c: Int, d: Int): Int {
    val sum = a + b + c + d

    return when {
      a == b && b == c && c == d -> 1111 * a
      a != b && b != c && c != d -> minOf(a, b, c, d)
      sum - a == 3 * b -> case3x1(b, a)
      sum - b == 3 * c -> case3x1(c, b)
      sum - c == 3 * d -> case3x1(d, c)
      sum - d == 3 * a -> case3x1(a, d)
      else -> {
        val tmp = IntArray(2)
        var di = 0
        val arr = intArrayOf(a, b, c, d)
        for (i in 0..3)
          for (j in i + 1..3) {
            val a = arr[i]
            val b = arr[j]
            if (a == b) tmp[di++] = a
          }

        val p = tmp[0]

        if (di == 2) {
          case2x2(p, tmp[1])
        } else {
          di = 0
          for (x in arr) if (x != p) tmp[di++] = x
          tmp[0] * tmp[1]
        }
      }
    }
  }

  fun case2x2(p: Int, q: Int): Int {
    var sub = p - q
    if (sub < 0) sub *= -1
    return (p + q) * sub
  }

  fun dfs(dep: Int, sum: Int): Int {
    if (dep == 2) {

      return sum
    }

    return dfs(dep + 1, sum)
  }

  fun case3x1(p: Int, q: Int): Int {
    val t = p * 10 + q
    return t * t
  }
}

/**
 * ```
 * ME v1:
 * 테스트 1 〉	실패 (0.69ms, 63.6MB)
 * 테스트 2 〉	통과 (0.01ms, 62MB)
 * 테스트 3 〉	통과 (0.01ms, 65.2MB)
 * 테스트 4 〉	실패 (0.65ms, 65MB)
 * 테스트 5 〉	통과 (0.02ms, 63MB)
 * 테스트 6 〉	통과 (0.01ms, 63.7MB)
 * 테스트 7 〉	실패 (0.69ms, 61.7MB)
 * 테스트 8 〉	통과 (0.01ms, 64.3MB)
 * 테스트 9 〉	실패 (0.69ms, 62.5MB)
 * 테스트 10 〉	실패 (0.78ms, 62.8MB)
 * 테스트 11 〉	실패 (0.01ms, 62.9MB)
 * 테스트 12 〉	통과 (0.01ms, 63.5MB)
 * 테스트 13 〉	통과 (0.99ms, 62.5MB)
 * 테스트 14 〉	통과 (0.02ms, 61.8MB)
 * 테스트 15 〉	통과 (0.01ms, 63.6MB)
 * 테스트 16 〉	실패 (1.05ms, 63.1MB)
 * 테스트 17 〉	통과 (0.99ms, 62.3MB)
 * 테스트 18 〉	통과 (0.01ms, 62MB)
 * 테스트 19 〉	실패 (0.93ms, 62.9MB)
 * 테스트 20 〉	통과 (1.00ms, 64.4MB)
 * 테스트 21 〉	통과 (0.01ms, 62.2MB)
 * 테스트 22 〉	통과 (0.01ms, 63.6MB)
 * 테스트 23 〉	통과 (0.70ms, 64.2MB)
 * 테스트 24 〉	실패 (0.83ms, 63.2MB)
 * 테스트 25 〉	실패 (1.02ms, 62.3MB)
 * 테스트 26 〉	통과 (0.01ms, 62.4MB)
 * 테스트 27 〉	실패 (0.70ms, 63.3MB)
 * 테스트 28 〉	실패 (0.83ms, 63.3MB)
 * 테스트 29 〉	통과 (0.01ms, 62.4MB)
 * 테스트 30 〉	실패 (0.69ms, 63.8MB)
 * 테스트 31 〉	통과 (0.01ms, 63.7MB)
 * 테스트 32 〉	실패 (0.73ms, 61.8MB)
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
    s.solution(2, 4, 5, 6), 2
  )


}

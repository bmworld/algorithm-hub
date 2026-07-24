package 프로그래머스.Lv2.숫자변환하기

import util.validate

class Solution {

  fun solution(x: Int, y: Int, n: Int): Int {
    if (x == y) return 0

    val a = IntArray(y + 1)

    fun prvByN(num: Int): Int = if (num >= n) a[num - n] else 0

    for (num in x + 1..y) {
      a[num] = when {
        num / x / 6 > 0 && num % 6 == 0 -> 1 + minOf(a[num / 3], a[num / 2], prvByN(num))
        num / x / 3 > 0 && num % 3 == 0 -> 1 + minOf(a[num / 3], prvByN(num))
        num / x / 2 > 0 && num % 2 == 0 -> 1 + minOf(a[num / 2], prvByN(num))
        num >= n -> prvByN(num)
        else -> continue
      }
    }
    return if (a[y] == 0) -1 else a[y]
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	실패 (5.23ms, 62.3MB)
 * 테스트 2 〉	통과 (2.80ms, 63.8MB)
 * 테스트 3 〉	통과 (3.94ms, 63.5MB)
 * 테스트 4 〉	통과 (0.17ms, 60.2MB)
 * 테스트 5 〉	실패 (3.97ms, 59MB)
 * 테스트 6 〉	통과 (0.01ms, 61.4MB)
 * 테스트 7 〉	실패 (4.42ms, 61.5MB)
 * 테스트 8 〉	통과 (11.97ms, 65.1MB)
 * 테스트 9 〉	실패 (12.54ms, 64.6MB)
 * 테스트 10 〉	실패 (19.04ms, 63.4MB)
 * 테스트 11 〉	실패 (16.38ms, 62MB)
 * 테스트 12 〉	실패 (5.29ms, 62.8MB)
 * 테스트 13 〉	통과 (5.22ms, 62.9MB)
 * 테스트 14 〉	실패 (7.65ms, 60.8MB)
 * 테스트 15 〉	실패 (11.20ms, 63.2MB)
 * 테스트 16 〉	실패 (11.86ms, 62.7MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(10, 40, 5), 2)
  validate(s.solution(10, 40, 30), 1)
  validate(s.solution(2, 5, 4), -1)
  validate(s.solution(2, 2, 100), 0)
  validate(s.solution(1, 1_000_000, 1), 16)

}

//      println("a[$num] = ${a[num]}")

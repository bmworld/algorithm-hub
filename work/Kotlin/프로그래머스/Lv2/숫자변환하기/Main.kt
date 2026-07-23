package 프로그래머스.Lv2.숫자변환하기

import util.validate

class Solution {

  fun solution(x: Int, y: Int, n: Int): Int {
    var ans = Int.MAX_VALUE

    fun op(num: Int, cnt: Int) {
      if (num < y) {
        op(num + n, cnt + 1)
        op(num * 2, cnt + 1)
        op(num * 3, cnt + 1)
      } else if (num == y && cnt < ans) ans = cnt
    }

    op(x, 0)

    return if (ans == Int.MAX_VALUE) -1 else ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.21ms, 60.1MB)
 * 테스트 2 〉	통과 (0.24ms, 59.9MB)
 * 테스트 3 〉	통과 (0.18ms, 60.2MB)
 * 테스트 4 〉	통과 (0.21ms, 60.8MB)
 * 테스트 5 〉	실패 (시간 초과)
 * 테스트 6 〉	통과 (0.14ms, 61.3MB)
 * 테스트 7 〉	실패 (시간 초과)
 * 테스트 8 〉	통과 (1.13ms, 58.1MB)
 * 테스트 9 〉	실패 (런타임 에러)
 * 테스트 10 〉	실패 (런타임 에러)
 * 테스트 11 〉	실패 (런타임 에러)
 * 테스트 12 〉	통과 (0.15ms, 60.5MB)
 * 테스트 13 〉	통과 (0.23ms, 60.8MB)
 * 테스트 14 〉	실패 (시간 초과)
 * 테스트 15 〉	실패 (시간 초과)
 * 테스트 16 〉	실패 (시간 초과)
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

}

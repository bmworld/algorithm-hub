package 프로그래머스.Lv0._1로만들기

import util.validate

class Solution {

  fun solution(num_list: IntArray): Int {
    var ans = 0
    for (x in num_list) ans += run {
      var r = 0
      var t = x
      while (t != 1) {
        t = if (t % 2 == 0) t / 2 else (t - 1) / 2
        r++
      }
      r
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.22ms, 59.1MB)
 * 테스트 2 〉	통과 (0.16ms, 58.9MB)
 * 테스트 3 〉	통과 (0.21ms, 60MB)
 * 테스트 4 〉	통과 (0.17ms, 60.1MB)
 * 테스트 5 〉	통과 (0.16ms, 59.2MB)
 *
 * v2:
 * 테스트 1 〉	통과 (0.01ms, 58.8MB)
 * 테스트 2 〉	통과 (0.01ms, 59.2MB)
 * 테스트 3 〉	통과 (0.01ms, 60.4MB)
 * 테스트 4 〉	통과 (0.02ms, 59.6MB)
 * 테스트 5 〉	통과 (0.02ms, 59.6MB)
 *
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(num_list: IntArray): Int = num_list.map{check(it, 0)}.sum()
 *
 *     tailrec fun check(a: Int, cnt: Int): Int = if(a == 1) cnt else check(a / 2, cnt+1)
 * }
 * 테스트 1 〉	통과 (3.88ms, 60.5MB)
 * 테스트 2 〉	통과 (3.76ms, 60.4MB)
 * 테스트 3 〉	통과 (4.48ms, 60.2MB)
 * 테스트 4 〉	통과 (4.40ms, 61.1MB)
 * 테스트 5 〉	통과 (5.13ms, 58.9MB)
 *
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(12, 4, 15, 1, 1, 14)), 11)
}

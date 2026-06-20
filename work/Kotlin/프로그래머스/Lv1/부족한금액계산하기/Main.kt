package 프로그래머스.Lv1.부족한금액계산하기

import util.validate

class Solution {

  fun solution(price: Int, money: Int, count: Int): Long {
    val p = price.toLong()
    val c = count.toLong()
    val cost = p * c * (c + 1) / 2
    val rmn = cost - money
    return maxOf(0, rmn)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59MB)
 * 테스트 2 〉	통과 (0.02ms, 58.9MB)
 * 테스트 3 〉	통과 (0.02ms, 58.4MB)
 * 테스트 4 〉	통과 (0.02ms, 59.6MB)
 * 테스트 5 〉	통과 (0.02ms, 59.6MB)
 * 테스트 6 〉	통과 (0.02ms, 58.5MB)
 * 테스트 7 〉	통과 (0.02ms, 58.8MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(price: Int, money: Int, count: Int): Long
 * = (1..count).map { it * price.toLong() }.sum().let { if (money > it) 0 else it - money }
 * }
 * 테스트 1 〉	통과 (5.20ms, 59.3MB)
 * 테스트 2 〉	통과 (5.06ms, 59.5MB)
 * 테스트 3 〉	통과 (5.21ms, 59.2MB)
 * 테스트 4 〉	통과 (5.83ms, 59.1MB)
 * 테스트 5 〉	통과 (4.94ms, 60.1MB)
 * 테스트 6 〉	통과 (4.86ms, 60MB)
 * 테스트 7 〉	통과 (5.38ms, 59.4MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(3, 20, 4), 10)
  validate(s.solution(2_500, 1_000_000_000, 2_500), 6_815_625_000)
}

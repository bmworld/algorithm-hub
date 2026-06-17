package 프로그래머스.Lv1.없는숫자더하기

import util.validate

class Solution {

  val digits = 10
  fun solution(numbers: IntArray): Int {
    val ch = BooleanArray(digits)
    for (x in numbers) ch[x] = true

    var answer = 0
    repeat(digits) {
      if (!ch[it]) answer += it
    }

    return answer
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 58.5MB)
 * 테스트 2 〉	통과 (0.05ms, 58.9MB)
 * 테스트 3 〉	통과 (0.01ms, 59.4MB)
 * 테스트 4 〉	통과 (0.01ms, 58.3MB)
 * 테스트 5 〉	통과 (0.01ms, 57.9MB)
 * 테스트 6 〉	통과 (0.01ms, 57.7MB)
 * 테스트 7 〉	통과 (0.01ms, 58.9MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(numbers: IntArray): Int  = 45 - numbers.sum()
 * }
 * 테스트 1 〉	통과 (8.69ms, 63.6MB)
 * 테스트 2 〉	통과 (8.80ms, 63MB)
 * 테스트 3 〉	통과 (8.76ms, 61.4MB)
 * 테스트 4 〉	통과 (9.07ms, 62.3MB)
 * 테스트 5 〉	통과 (10.55ms, 64.5MB)
 * 테스트 6 〉	통과 (8.73ms, 61.5MB)
 * 테스트 7 〉	통과 (8.66ms, 62.9MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(1, 2, 3, 4, 6, 7, 8, 0)), 14)
  validate(s.solution(intArrayOf(5, 8, 4, 0, 6, 7, 9)), 6)
}

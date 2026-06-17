package 프로그래머스.Lv1.음양더하기

import util.validate

class Solution {

  fun solution(absolutes: IntArray, signs: BooleanArray): Int {
    val len = absolutes.size
    var answer = 0
    repeat(len) { i ->
      answer += absolutes[i] * if (signs[i]) 1 else -1
    }
    return answer
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.03ms, 59.4MB)
 * 테스트 2 〉	통과 (0.04ms, 58.1MB)
 * 테스트 3 〉	통과 (0.04ms, 58.1MB)
 * 테스트 4 〉	통과 (0.03ms, 58.7MB)
 * 테스트 5 〉	통과 (0.04ms, 58.3MB)
 * 테스트 6 〉	통과 (0.04ms, 58.7MB)
 * 테스트 7 〉	통과 (0.03ms, 59.5MB)
 * 테스트 8 〉	통과 (0.07ms, 57.8MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(absolutes: IntArray, signs: BooleanArray) =
 *         absolutes.foldIndexed(0) { idx, acc, num -> acc + if (signs[idx]) num else -num }
 * }
 * 테스트 1 〉	통과 (0.05ms, 59.8MB)
 * 테스트 2 〉	통과 (0.04ms, 57.8MB)
 * 테스트 3 〉	통과 (0.05ms, 57.7MB)
 * 테스트 4 〉	통과 (0.06ms, 58.3MB)
 * 테스트 5 〉	통과 (0.04ms, 58.9MB)
 * 테스트 6 〉	통과 (0.04ms, 59.6MB)
 * 테스트 7 〉	통과 (0.04ms, 59MB)
 * 테스트 8 〉	통과 (0.04ms, 58.3MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(4, 7, 12), booleanArrayOf(true, false, true)), 9)
  validate(s.solution(intArrayOf(1, 2, 3), booleanArrayOf(false, false, true)), 0)
}

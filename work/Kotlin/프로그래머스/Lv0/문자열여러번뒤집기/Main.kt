package 프로그래머스.Lv0.문자열여러번뒤집기

import util.validate

class Solution {

  fun solution(my_string: String, queries: Array<IntArray>): String {
    val ans = my_string.toCharArray()
    for (q in queries) {
      val s = q[0]
      val e = q[1]
      repeat((e - s + 1) / 2) {
        val tmp = ans[s + it]
        ans[s + it] = ans[e - it]
        ans[e - it] = tmp
      }
    }
    return String(ans)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 58.7MB)
 * 테스트 2 〉	통과 (0.03ms, 58.1MB)
 * 테스트 3 〉	통과 (0.04ms, 59.6MB)
 * 테스트 4 〉	통과 (0.07ms, 59.6MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(my_string: String, queries: Array<IntArray>): String {
 *         var answer = my_string
 *         queries.forEach { (s, e) ->
 *             answer = answer.slice(0 until s) + answer.slice(s..e)
 *                 .reversed() + answer.slice(e+1..answer.lastIndex)
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (12.18ms, 60.7MB)
 * 테스트 2 〉	통과 (10.29ms, 61.7MB)
 * 테스트 3 〉	통과 (10.71ms, 61.4MB)
 * 테스트 4 〉	통과 (10.36ms, 60.8MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(myString: String, queries: Array<IntArray>) = queries.fold(myString) { acc, arr ->
 *         acc.substring(0, arr[0]) + acc.substring(arr[0]..arr[1]).reversed() + acc.substring(arr[1] + 1, acc.length)
 *     }
 * }
 * 테스트 1 〉	통과 (9.74ms, 60.6MB)
 * 테스트 2 〉	통과 (9.07ms, 60.2MB)
 * 테스트 3 〉	통과 (8.98ms, 60.7MB)
 * 테스트 4 〉	통과 (9.31ms, 61.8MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("rermgorpsam", arrayOf(
    intArrayOf(2, 3),
    intArrayOf(0, 7),
    intArrayOf(5, 9),
    intArrayOf(6, 10),
  )), "programmers")


  validate(s.solution("abcdef", arrayOf(
    intArrayOf(0, 2),
  )), "cbadef")
}

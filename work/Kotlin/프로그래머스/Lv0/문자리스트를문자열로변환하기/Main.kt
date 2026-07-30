package 프로그래머스.Lv0.문자리스트를문자열로변환하기

import util.validate

class Solution {

  fun solution(arr: Array<String>): String =
    String(CharArray(arr.size) { arr[it][0] })
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.1MB)
 * 테스트 2 〉	통과 (0.01ms, 60.1MB)
 * 테스트 3 〉	통과 (0.02ms, 60.4MB)
 * 테스트 4 〉	통과 (0.02ms, 60.2MB)
 * 테스트 5 〉	통과 (0.03ms, 59.4MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(arr: Array<String>) = arr.joinToString("")
 * }
 * 테스트 1 〉	통과 (12.34ms, 64.3MB)
 * 테스트 2 〉	통과 (12.33ms, 63.9MB)
 * 테스트 3 〉	통과 (12.23ms, 63.8MB)
 * 테스트 4 〉	통과 (16.00ms, 65.1MB)
 * 테스트 5 〉	통과 (12.87ms, 64.1MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(arrayOf("a", "b", "c")),
    "abc",
  )
}

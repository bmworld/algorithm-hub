package 프로그래머스.Lv0.글자이어붙여문자열만들기

import util.validate

class Solution {

  fun solution(my_string: String, index_list: IntArray): String =
    String(CharArray(index_list.size) { my_string[index_list[it]] })
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.04ms, 59.8MB)
 * 테스트 2 〉	통과 (0.02ms, 60.1MB)
 * 테스트 3 〉	통과 (0.03ms, 60.1MB)
 * 테스트 4 〉	통과 (0.04ms, 60.9MB)
 * 테스트 5 〉	통과 (0.05ms, 58.4MB)
 * 테스트 6 〉	통과 (0.01ms, 60.1MB)
 * 테스트 7 〉	통과 (0.02ms, 58.4MB)
 * 테스트 8 〉	통과 (0.03ms, 58.1MB)
 * 테스트 9 〉	통과 (0.04ms, 60.2MB)
 * 테스트 10 〉	통과 (0.04ms, 60.8MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(my_string: String, index_list: IntArray): String {
 *         var answer: String = ""
 *         return index_list.map { my_string[it] }.joinToString("")
 *     }
 * }
 * 테스트 1 〉	통과 (8.26ms, 61.7MB)
 * 테스트 2 〉	통과 (8.96ms, 60.2MB)
 * 테스트 3 〉	통과 (9.11ms, 62.3MB)
 * 테스트 4 〉	통과 (9.93ms, 61.4MB)
 * 테스트 5 〉	통과 (9.02ms, 61MB)
 * 테스트 6 〉	통과 (7.84ms, 59.8MB)
 * 테스트 7 〉	통과 (9.06ms, 60.2MB)
 * 테스트 8 〉	통과 (14.63ms, 59.7MB)
 * 테스트 9 〉	통과 (8.00ms, 62.3MB)
 * 테스트 10 〉	통과 (8.40ms, 61.3MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("cvsgiorszzzmrpaqpe", intArrayOf(16, 6, 5, 3, 12, 14, 11, 11, 17, 12, 7)),
    "programmers")
  validate(s.solution("zpiaz", intArrayOf(1, 2, 0, 0, 3)),
    "pizza")
}

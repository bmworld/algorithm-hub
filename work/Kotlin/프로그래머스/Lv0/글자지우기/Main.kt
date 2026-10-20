package 프로그래머스.Lv0.글자지우기

import util.validate

class Solution {

  fun solution(str: String, indices: IntArray): String {
    val N = str.length
    val M = indices.size
    val ch = BooleanArray(N)
    for (x in indices) ch[x] = true
    val ans = CharArray(N - M)
    var i = 0
    for (j in str.indices) if (!ch[j]) ans[i++] = str[j]
    return String(ans)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.5MB)
 * 테스트 2 〉	통과 (0.02ms, 60.2MB)
 * 테스트 3 〉	통과 (0.02ms, 58.8MB)
 * 테스트 4 〉	통과 (0.02ms, 60.8MB)
 * 테스트 5 〉	통과 (0.02ms, 59.8MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(my_string: String, indices: IntArray): String {
 *         return my_string.filterIndexed { index, w -> index !in indices }
 *     }
 * }
 * 테스트 1 〉	통과 (8.78ms, 63.7MB)
 * 테스트 2 〉	통과 (9.01ms, 63.1MB)
 * 테스트 3 〉	통과 (9.80ms, 63.7MB)
 * 테스트 4 〉	통과 (8.98ms, 62.6MB)
 * 테스트 5 〉	통과 (9.29ms, 62.8MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(myString: String, indices: IntArray): String {
 *         return (myString.indices - indices.toSet()).map { myString[it] }.joinToString("")
 *     }
 * }
 * 테스트 1 〉	통과 (22.13ms, 64.8MB)
 * 테스트 2 〉	통과 (18.73ms, 65MB)
 * 테스트 3 〉	통과 (22.55ms, 64MB)
 * 테스트 4 〉	통과 (21.31ms, 63.9MB)
 * 테스트 5 〉	통과 (20.43ms, 65.8MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("apporoograpemmemprs", intArrayOf(1, 16, 6, 15, 0, 10, 11, 3)), "programmers")
}

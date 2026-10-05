package 프로그래머스.Lv0.부분문자열이어붙여문자열만들기

import util.validate

class Solution {

  fun solution(strs: Array<String>, parts: Array<IntArray>): String {
    val tmp = CharArray(100 * 100)
    var len = 0
    for (i in strs.indices) {
      val str = strs[i]
      val part = parts[i]
      for (j in part[0]..part[1]) tmp[len++] = str[j]
    }

    return String(tmp, 0, len)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.08ms, 59.7MB)
 * 테스트 2 〉	통과 (0.03ms, 59.6MB)
 * 테스트 3 〉	통과 (0.08ms, 59.9MB)
 * 테스트 4 〉	통과 (0.05ms, 60.4MB)
 * 테스트 5 〉	통과 (0.05ms, 59.7MB)
 * 테스트 6 〉	통과 (0.04ms, 59.5MB)
 * 테스트 7 〉	통과 (0.05ms, 59.3MB)
 * 테스트 8 〉	통과 (0.17ms, 59.7MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(myStrings: Array<String>, parts: Array<IntArray>): String {
 *         return myStrings.indices.joinToString("") { myStrings[it].substring(parts[it][0], parts[it][1] + 1) }
 *     }
 * }
 * 테스트 1 〉	통과 (20.41ms, 63.4MB)
 * 테스트 2 〉	통과 (21.86ms, 65.1MB)
 * 테스트 3 〉	통과 (19.65ms, 64.5MB)
 * 테스트 4 〉	통과 (18.48ms, 65.2MB)
 * 테스트 5 〉	통과 (18.11ms, 65.8MB)
 * 테스트 6 〉	통과 (20.15ms, 65.1MB)
 * 테스트 7 〉	통과 (19.54ms, 65.6MB)
 * 테스트 8 〉	통과 (18.38ms, 65.6MB)
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf("progressive", "hamburger", "hammer", "ahocorasick"),
    arrayOf(
      intArrayOf(0, 4),
      intArrayOf(1, 2),
      intArrayOf(3, 5),
      intArrayOf(7, 7),
    )
  ), "programmers")
}

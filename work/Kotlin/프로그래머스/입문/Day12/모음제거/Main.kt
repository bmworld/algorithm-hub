package 프로그래머스.입문.Day12.모음제거

class Solution {

  fun solution(my_string: String): String {
    val len = my_string.length
    val ans = CharArray(len)

    var cnt = 0
    for (i in 0 until len) {
      val ch = my_string[i]
      if (
        ch != 'a' &&
        ch != 'e' &&
        ch != 'i' &&
        ch != 'o' &&
        ch != 'u'
      ) ans[cnt++] = ch
    }
    return ans.concatToString(0, cnt).also { println(it) }
  }
}

fun main() {
  val s = Solution()
  check(s.solution("bus") == "bs")
  check(s.solution("nice to meet you") == "nc t mt y")
}

/**
 * CASE: val vowel = charArrayOf('a', 'e', 'i', 'o', 'u') 사용
 * 테스트 1 〉	통과 (21.92ms, 66.3MB)
 * 테스트 2 〉	통과 (25.21ms, 65.7MB)
 * 테스트 3 〉	통과 (23.15ms, 66.2MB)
 * 테스트 4 〉	통과 (21.62ms, 66.5MB)
 * 테스트 5 〉	통과 (21.05ms, 66.8MB)
 * 테스트 6 〉	통과 (22.18ms, 66.7MB)
 *
 * // 개별 비교
 * 테스트 1 〉	통과 (8.07ms, 64.8MB)
 * 테스트 2 〉	통과 (9.04ms, 64.4MB)
 * 테스트 3 〉	통과 (8.69ms, 63.8MB)
 * 테스트 4 〉	통과 (13.38ms, 62.7MB)
 * 테스트 5 〉	통과 (11.65ms, 62.3MB)
 * 테스트 6 〉	통과 (10.49ms, 63.8MB)
 */

package 프로그래머스.Lv0.세개의구분자

import util.validate

class Solution {

  fun solution(str: String): Array<String> {
    val N = str.length
    val ans = Array((N + 1) / 2) { "" }
    var i = 0

    val buf = CharArray(N)
    var j = 0

    for (s in str) {
      when (s) {
        'a', 'b', 'c' -> if (j > 0) {
          ans[i++] = String(buf, 0, j)
          j = 0
        }
        else -> buf[j++] = s
      }
    }

    if (j > 0) ans[i++] = String(buf, 0, j)

    return if (i == 0) arrayOf("EMPTY")
    else ans.copyOf(i) as Array<String>
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (14.37ms, 82.2MB)
 * 테스트 2 〉	통과 (13.39ms, 80.4MB)
 * 테스트 3 〉	통과 (5.13ms, 64.7MB)
 * 테스트 4 〉	통과 (4.13ms, 62.9MB)
 * 테스트 5 〉	통과 (10.82ms, 71.4MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(myStr: String): List<String> {
 *         return myStr.split("[abc]+".toRegex()).filter(String::isNotEmpty).let { it.ifEmpty { listOf("EMPTY") } }
 *     }
 * }
 * 테스트 1 〉	통과 (29.27ms, 85.3MB)
 * 테스트 2 〉	통과 (21.75ms, 83.4MB)
 * 테스트 3 〉	통과 (13.29ms, 67.6MB)
 * 테스트 4 〉	통과 (9.57ms, 66.7MB)
 * 테스트 5 〉	통과 (15.53ms, 73.8MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(myStr: String): Array<String> {
 *         var answer = myStr.split("[abc]".toRegex()).filter{ it.isNotEmpty() }.toTypedArray()
 *         return if (answer.isEmpty()) arrayOf("EMPTY") else answer
 *     }
 * }
 * 테스트 1 〉	통과 (23.14ms, 82.9MB)
 * 테스트 2 〉	통과 (21.41ms, 80.5MB)
 * 테스트 3 〉	통과 (13.75ms, 65.8MB)
 * 테스트 4 〉	통과 (9.76ms, 64.3MB)
 * 테스트 5 〉	통과 (17.65ms, 71MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("baconlettucetomato"), arrayOf("onlettu", "etom", "to"))
  validate(s.solution("abcd"), arrayOf("d"))
  validate(s.solution("cabab"), arrayOf("EMPTY"))
}

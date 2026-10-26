package 프로그래머스.Lv0.문자열묶기

import util.validate

class Solution {
  companion object {

    const val MAX_GROUP = 30
  }

  fun solution(strArr: Array<String>): Int {
    val ch = IntArray(MAX_GROUP + 1)
    for (str in strArr) ch[str.length]++

    var ans = 0
    for (len in 1..MAX_GROUP)
      ch[len].also { if (it > ans) ans = it }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (3.90ms, 88.3MB)
 * 테스트 2 〉	통과 (0.02ms, 59.9MB)
 * 테스트 3 〉	통과 (3.65ms, 91.3MB)
 * 테스트 4 〉	통과 (14.33ms, 94.6MB)
 * 테스트 5 〉	통과 (2.64ms, 88MB)
 * 테스트 6 〉	통과 (0.67ms, 69.7MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(strArr: Array<String>) = strArr.groupBy(String::length).values.maxOf(List<String>::size)
 * }
 * 테스트 1 〉	통과 (7.90ms, 90.6MB)
 * 테스트 2 〉	통과 (0.25ms, 59.9MB)
 * 테스트 3 〉	통과 (11.21ms, 91.9MB)
 * 테스트 4 〉	통과 (10.90ms, 92.5MB)
 * 테스트 5 〉	통과 (5.62ms, 89.1MB)
 * 테스트 6 〉	통과 (3.07ms, 70.7MB)
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf("a", "bc", "d", "efg", "hi")), 2)
  validate(s.solution(arrayOf("a", "bcadf")), 1)
  validate(s.solution(arrayOf("a", "bcadf", "c")), 2)
}

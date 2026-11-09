package 프로그래머스.Lv0.왼쪽오른쪽

import util.validate

class Solution {

  fun solution(strs: Array<String>): Array<String> {
    val N = strs.size
    for (i in 0 until N) when (strs[i]) {
      "l" -> return Array(i) { strs[it] }
      "r" -> return Array(N - (i + 1)) { strs[i + 1 + it] }
    }
    return arrayOf()
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 58.6MB)
 * 테스트 2 〉	통과 (0.01ms, 59.2MB)
 * 테스트 3 〉	통과 (0.01ms, 58.7MB)
 * 테스트 4 〉	통과 (0.01ms, 58MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(strList: Array<String>): Array<String> {
 *         if (!strList.contains("r") && !strList.contains("l")) return emptyArray()
 *         if (strList.contains("l") && (!strList.contains("r") || strList.indexOf("l") < strList.indexOf("r"))) return strList.sliceArray(0 until strList.indexOf("l"))
 *         if (strList.contains("r") && (!strList.contains("l") || strList.indexOf("r") < strList.indexOf("l"))) return strList.sliceArray((strList.indexOf("r") + 1) until strList.size)
 *         return emptyArray()
 *     }
 * }
 * 테스트 1 〉	통과 (10.63ms, 62.9MB)
 * 테스트 2 〉	통과 (10.69ms, 63MB)
 * 테스트 3 〉	통과 (11.31ms, 62.8MB)
 * 테스트 4 〉	통과 (11.21ms, 63.5MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(str_list: Array<String>): Array<String> {
 *         val i = str_list.indexOfFirst { it == "l" || it == "r" }
 *         return when {
 *             i < 0 -> emptyList<String>()
 *             str_list[i] == "l" -> str_list.slice(0 until i)
 *             else -> str_list.slice(i + 1..str_list.lastIndex)
 *         }.toTypedArray()
 *     }
 * }
 * 테스트 1 〉	통과 (10.53ms, 63.3MB)
 * 테스트 2 〉	통과 (13.71ms, 64.2MB)
 * 테스트 3 〉	통과 (13.49ms, 64.2MB)
 * 테스트 4 〉	통과 (14.00ms, 64.3MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf(
    "u",
    "u",
    "l",
    "r",
  )), arrayOf("u", "u"))

  validate(s.solution(arrayOf(
    "l",
  )), arrayOf())

  validate(s.solution(arrayOf(
    "d",
    "l",
    "u",
    "u",
  )), arrayOf("d"))

  validate(s.solution(arrayOf(
    "d",
    "d",
    "r",
    "u",
  )), arrayOf("u"))


  validate(s.solution(arrayOf(
    "d",
    "d",
    "d",
    "u",
  )), arrayOf())
}

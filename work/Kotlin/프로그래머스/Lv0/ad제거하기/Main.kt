package 프로그래머스.Lv0.ad제거하기

import util.validate

class Solution {

  fun solution(strs: Array<String>): Array<String> {
    val TRGT = "ad"
    val buf = Array(strs.size) { "" }
    var i = 0

    s1@ for (str in strs) {
      s2@ for (j in 0 until str.length - 1) {
        for (k in 0..1) if (str[j + k] != TRGT[k]) continue@s2
        continue@s1
      }

      buf[i++] = str
    }
    return buf.copyOf(i) as Array<String>
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.9MB)
 * 테스트 2 〉	통과 (0.02ms, 59.3MB)
 * 테스트 3 〉	통과 (0.30ms, 62MB)
 * 테스트 4 〉	통과 (0.27ms, 62MB)
 * 테스트 5 〉	통과 (0.06ms, 60.3MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(strArr: Array<String>) = strArr.filter { !it.contains("ad") }
 * }
 * 테스트 1 〉	통과 (4.35ms, 60.9MB)
 * 테스트 2 〉	통과 (4.41ms, 60.5MB)
 * 테스트 3 〉	통과 (4.76ms, 61.8MB)
 * 테스트 4 〉	통과 (4.84ms, 62MB)
 * 테스트 5 〉	통과 (4.53ms, 61.2MB)
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf("and", "notad", "abcd")),
    arrayOf("and", "abcd"))

  validate(s.solution(arrayOf("there", "are", "no", "a", "ds")),
    arrayOf("there", "are", "no", "a", "ds"))
}

package 프로그래머스.Lv0.문자열이몇번등장하는지세기

import util.validate

class Solution {

  fun solution(myString: String, pat: String): Int {
    val N = myString.length
    val M = pat.length
    if (N < M) return 0

    var ans = 0
    l@ for (i in 0..N - M) {
      for (j in 0 until M)
        if (myString[i + j] != pat[j]) continue@l
      ans++
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 59.5MB)
 * 테스트 2 〉	통과 (0.02ms, 59.5MB)
 * 테스트 3 〉	통과 (0.02ms, 59.1MB)
 * 테스트 4 〉	통과 (0.02ms, 59.3MB)
 * 테스트 5 〉	통과 (0.02ms, 59.7MB)
 * 테스트 6 〉	통과 (0.01ms, 58.8MB)
 * 테스트 7 〉	통과 (0.05ms, 59.3MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(myString: String, pat: String): Int {
 *         return (0..myString.length - pat.length).map { myString.substring(it, it + pat.length) }.count { it == pat }
 *     }
 * }
 * 테스트 1 〉	통과 (4.50ms, 60.4MB)
 * 테스트 2 〉	통과 (4.02ms, 60.9MB)
 * 테스트 3 〉	통과 (4.35ms, 60.7MB)
 * 테스트 4 〉	통과 (4.04ms, 61.1MB)
 * 테스트 5 〉	통과 (5.00ms, 60.3MB)
 * 테스트 6 〉	통과 (5.18ms, 60MB)
 * 테스트 7 〉	통과 (4.37ms, 61MB)
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("banana", "ana"), 2)
  validate(s.solution("aaaa", "aa"), 3)
  validate(s.solution("a", "aa"), 0)
  validate(s.solution("a", "a"), 1)
  validate(s.solution("a", "z"), 0)
}

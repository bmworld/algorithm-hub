package 프로그래머스.Lv0.부분문자열인지확인하기

import util.validate

class Solution {

  fun solution(a: String, b: String): Int {

    val aLen = a.length
    val bLen = b.length

    val trigger = b[0]

    l@ for (i in 0 until aLen) {
      if (a[i] != trigger) continue
      for (j in 1 until bLen) {
        if (i + j >= aLen) break@l
        if (a[i + j] != b[j]) continue@l
      }
      return 1
    }

    return 0
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 59.8MB)
 * 테스트 2 〉	통과 (0.01ms, 60.1MB)
 * 테스트 3 〉	통과 (0.02ms, 60.3MB)
 * 테스트 4 〉	통과 (0.01ms, 59.9MB)
 * 테스트 5 〉	통과 (0.01ms, 59.9MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(myString: String, target: String) = if (myString.contains(target)) 1 else 0
 * }
 * 테스트 1 〉	통과 (7.19ms, 60.1MB)
 * 테스트 2 〉	통과 (4.66ms, 61MB)
 * 테스트 3 〉	통과 (4.80ms, 60.4MB)
 * 테스트 4 〉	통과 (4.27ms, 60.8MB)
 * 테스트 5 〉	통과 (4.47ms, 60.4MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("banana", "ana"), 1)
  validate(s.solution("banana", "anz"), 0)
  validate(s.solution("banana", "banana"), 1)
  validate(s.solution("banana", "bananaa"), 0)
  validate(s.solution("banana", "a"), 1)
  validate(s.solution("a", "a"), 1)
  validate(s.solution("a", "ab"), 0)
}

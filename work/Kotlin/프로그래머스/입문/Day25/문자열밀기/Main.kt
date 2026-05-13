package 프로그래머스.입문.Day25.문자열밀기

import util.validate

class Solution {

  fun solution(A: String, B: String): Int {
    if (A == B) return 0

    val arr = B.toCharArray()
    repeat(B.length - 1) {
      if (A == rotate(arr).concatToString()) return 1 + it
    }

    return -1
  }

  fun rotate(arr: CharArray): CharArray {
    val t = arr[0]
    System.arraycopy(arr, 1, arr, 0, arr.size - 1)
    arr[arr.size - 1] = t
    return arr
  }
}

/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (4.26ms, 63.6MB)
 * 테스트 2 〉	통과 (5.01ms, 63.4MB)
 * 테스트 3 〉	통과 (0.01ms, 64.1MB)
 * 테스트 4 〉	통과 (4.20ms, 63.2MB)
 * 테스트 5 〉	통과 (4.04ms, 62.6MB)
 * 테스트 6 〉	통과 (3.99ms, 64.4MB)
 * 테스트 7 〉	통과 (3.85ms, 65.1MB)
 * 테스트 8 〉	통과 (4.38ms, 63.1MB)
 * ```
 * ```
 * RIVAL:
 * class Solution {
 *     fun solution(A: String, B: String): Int = (B + B).indexOf(A)
 * }
 * 테스트 1 〉	통과 (5.67ms, 63.2MB)
 * 테스트 2 〉	통과 (5.37ms, 63.1MB)
 * 테스트 3 〉	통과 (6.20ms, 62.8MB)
 * 테스트 4 〉	통과 (6.69ms, 63.3MB)
 * 테스트 5 〉	통과 (5.90ms, 63.6MB)
 * 테스트 6 〉	통과 (5.42ms, 63MB)
 * 테스트 7 〉	통과 (5.42ms, 63.1MB)
 * 테스트 8 〉	통과 (5.35ms, 64.1MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("hello", "ohell"), 1)
  validate(s.solution("hhhello", "lohhhel"), 2)
  validate(s.solution("apple", "elppa"), -1)
  validate(s.solution("abbb", "bbba"), 3)
  validate(s.solution("a", "a"), 0)
}

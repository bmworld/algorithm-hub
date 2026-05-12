package 프로그래머스.입문.Day24.A로B만들기

import util.validate

class Solution {

  val POSSIBLE = 1
  val IMPOSSIBLE = 0

  val a = 97
  val ALPHA_SIZE = 26
  fun solution(before: String, after: String): Int {
    val ch = IntArray(ALPHA_SIZE)
    for (x in before) ch[x.code - a]++

    for (x in after) {
      val cnt = ch[x.code - a]
      if (cnt > 0) ch[x.code - a]--
      else return IMPOSSIBLE
    }

    return POSSIBLE
  }
}

/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (0.05ms, 63MB)
 * 테스트 2 〉	통과 (0.02ms, 63.1MB)
 * 테스트 3 〉	통과 (0.02ms, 62.4MB)
 * 테스트 4 〉	통과 (0.03ms, 62.2MB)
 * 테스트 5 〉	통과 (0.10ms, 62MB)
 * 테스트 6 〉	통과 (0.02ms, 61.9MB)
 * 테스트 7 〉	통과 (0.03ms, 63.8MB)
 * 테스트 8 〉	통과 (0.05ms, 62.1MB)
 * 테스트 9 〉	통과 (0.02ms, 63.7MB)
 * 테스트 10 〉	통과 (0.04ms, 63.6MB)
 * 테스트 11 〉	통과 (0.09ms, 62.3MB)
 * 테스트 12 〉	통과 (0.08ms, 62.5MB)
 * 테스트 13 〉	통과 (0.02ms, 63MB)
 * 테스트 14 〉	통과 (0.02ms, 64.6MB)
 * 테스트 15 〉	통과 (0.03ms, 64.6MB)
 * 테스트 16 〉	통과 (0.04ms, 63MB)
 * 테스트 17 〉	통과 (0.03ms, 64MB)
 * 테스트 18 〉	통과 (0.07ms, 62.3MB)
 * 테스트 19 〉	통과 (0.10ms, 62.7MB)
 * 테스트 20 〉	통과 (0.07ms, 61.9MB)
 * 테스트 21 〉	통과 (0.06ms, 62.4MB)
 * 테스트 22 〉	통과 (0.05ms, 62.2MB)
 * 테스트 23 〉	통과 (0.02ms, 62MB)
 * ```
 * ```
 * RIVAL:
 * class Solution {
 *     fun solution(before: String, after: String): Int =
 *         if (before.toList().sorted() == after.toList().sorted()) 1 else 0
 * }
 * 테스트 1 〉	통과 (16.37ms, 68.2MB)
 * 테스트 2 〉	통과 (15.37ms, 67.1MB)
 * 테스트 3 〉	통과 (16.33ms, 67.1MB)
 * 테스트 4 〉	통과 (17.97ms, 67.5MB)
 * 테스트 5 〉	통과 (16.64ms, 66.8MB)
 * 테스트 6 〉	통과 (8.58ms, 65.1MB)
 * 테스트 7 〉	통과 (15.22ms, 67.5MB)
 * 테스트 8 〉	통과 (15.86ms, 67.5MB)
 * 테스트 9 〉	통과 (21.36ms, 66.7MB)
 * 테스트 10 〉	통과 (16.21ms, 66.9MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("aak", "aka"), 1)
  validate(s.solution("a", "b"), 0)
  validate(s.solution("abc", "cia"), 0)
  validate(s.solution("abc", "cba"), 1)
  validate(s.solution("olleh", "hello"), 1)
  validate(s.solution("hello", "helln"), 0)
}

//      println("[${ansLen - (it + 1)}] $d1 + $d2 + $caret -> ${a[ansLen - (it + 1)]}")

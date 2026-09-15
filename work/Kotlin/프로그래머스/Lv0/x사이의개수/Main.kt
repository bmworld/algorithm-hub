package 프로그래머스.Lv0.x사이의개수

import util.validate

class Solution {

  fun solution(myString: String): IntArray {
    var ans = IntArray(myString.length + 1)
    var splited = 0
    var len = 0
    for (x in myString) {
      when (x) {
        'x' -> {
          ans[splited++] = len
          len = 0
        }
        else -> len++
      }
    }
    ans[splited++] = len

    return ans.copyOf(splited)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 60MB)
 * 테스트 2 〉	통과 (0.01ms, 60.3MB)
 * 테스트 3 〉	통과 (2.06ms, 63.9MB)
 * 테스트 4 〉	통과 (1.85ms, 63.5MB)
 * 테스트 5 〉	통과 (0.40ms, 59.9MB)
 * 테스트 6 〉	통과 (0.26ms, 61.7MB)
 * 테스트 7 〉	통과 (0.85ms, 61.5MB)
 * 테스트 8 〉	통과 (1.07ms, 60.9MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(myString: String) = myString.split("x").map(String::length)
 * }
 * 테스트 1 〉	통과 (7.87ms, 60.7MB)
 * 테스트 2 〉	통과 (7.61ms, 60.9MB)
 * 테스트 3 〉	통과 (11.63ms, 66.4MB)
 * 테스트 4 〉	통과 (10.87ms, 65.1MB)
 * 테스트 5 〉	통과 (8.72ms, 62.6MB)
 * 테스트 6 〉	통과 (9.75ms, 61.3MB)
 * 테스트 7 〉	통과 (9.70ms, 61.7MB)
 * 테스트 8 〉	통과 (10.35ms, 63.2MB)
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("oxooxoxxox"), intArrayOf(1, 2, 1, 0, 1, 0))
  validate(s.solution("xabcxdefxghi"), intArrayOf(0, 3, 3, 3))
  validate(s.solution("xx"), intArrayOf(0, 0, 0))
  validate(s.solution("hxxh"), intArrayOf(1, 0, 1))
  validate(s.solution("hxxhx"), intArrayOf(1, 0, 1, 0))
}

package 프로그래머스.Lv1.문자열나누기

import util.validate

class Solution {

  val INIT = ' '
  fun solution(s: String): Int {
    var ans = 0
    var cur = INIT
    var rmn = 0
    for (x in s) {
      when (cur) {
        INIT -> {
          cur = x
          ans++
          rmn++
        }
        x -> rmn++
        else -> if (--rmn == 0) cur = INIT
      }
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 58.9MB)
 * 테스트 2 〉	통과 (0.12ms, 58MB)
 * 테스트 3 〉	통과 (0.19ms, 59.6MB)
 * 테스트 4 〉	통과 (0.01ms, 59.7MB)
 * 테스트 5 〉	통과 (0.01ms, 58MB)
 * 테스트 6 〉	통과 (0.01ms, 58MB)
 * 테스트 7 〉	통과 (0.01ms, 58.2MB)
 * 테스트 8 〉	통과 (0.01ms, 58.8MB)
 * 테스트 9 〉	통과 (0.15ms, 57.7MB)
 * 테스트 10 〉	통과 (0.19ms, 58.1MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(s: String): Int {
 *         var answer: Int = 0
 *
 *         val stack = mutableListOf<Char>()
 *
 *         s.forEach {
 *             if (stack.isEmpty()) {
 *                 answer++
 *                 stack.add(it)
 *             } else if (stack.first() == it) {
 *                 stack.add(it)
 *             } else {
 *                 stack.removeFirst()
 *             }
 *         }
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (5.22ms, 60.6MB)
 * 테스트 2 〉	통과 (5.89ms, 59.2MB)
 * 테스트 3 〉	통과 (5.40ms, 59.1MB)
 * 테스트 4 〉	통과 (3.87ms, 59.5MB)
 * 테스트 5 〉	통과 (4.26ms, 59.3MB)
 * 테스트 6 〉	통과 (3.93ms, 59.2MB)
 * 테스트 7 〉	통과 (3.94ms, 58.9MB)
 * 테스트 8 〉	통과 (3.58ms, 59.1MB)
 * 테스트 9 〉	통과 (4.62ms, 59.3MB)
 * 테스트 10 〉	통과 (4.42ms, 61.1MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("banana"), 3)
  validate(s.solution("abracadabra"), 6)
  validate(s.solution("aaabbaccccabba"), 3)
  validate(s.solution("abbaaaa"), 3)
}

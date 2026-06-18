package 프로그래머스.Lv1.서울에서김서방찾기

import util.validate

class Solution {

  val 김서방 = "Kim"
  fun solution(seoul: Array<String>): String {
    for (i in 0 until seoul.size) if (seoul[i] == 김서방) return "김서방은 ${i}에 있다"
    return "김서방 없다"
  }
}

/**
 * ```
 * [ME]
 * ```
 * 테스트 1 〉	통과 (1.91ms, 59.5MB)
 * 테스트 2 〉	통과 (1.79ms, 58.4MB)
 * 테스트 3 〉	통과 (1.74ms, 58.3MB)
 * 테스트 4 〉	통과 (1.79ms, 59.4MB)
 * 테스트 5 〉	통과 (1.78ms, 59MB)
 * 테스트 6 〉	통과 (2.37ms, 58.8MB)
 * 테스트 7 〉	통과 (2.04ms, 58.6MB)
 * 테스트 8 〉	통과 (2.04ms, 57.8MB)
 * 테스트 9 〉	통과 (1.75ms, 59.4MB)
 * 테스트 10 〉	통과 (2.02ms, 59.8MB)
 * ```
 * [RIVAL]
 * class Solution {
 *             fun solution(seoul: Array<String>): String = "김서방은 ${seoul.indexOf("Kim")}에 있다"
 *         }
 * 테스트 1 〉	통과 (10.75ms, 63.3MB)
 * 테스트 2 〉	통과 (10.64ms, 62.7MB)
 * 테스트 3 〉	통과 (11.07ms, 62.2MB)
 * 테스트 4 〉	통과 (10.70ms, 62.4MB)
 * 테스트 5 〉	통과 (10.92ms, 62MB)
 * 테스트 6 〉	통과 (10.83ms, 62MB)
 * 테스트 7 〉	통과 (10.52ms, 63.1MB)
 * 테스트 8 〉	통과 (10.55ms, 62MB)
 * 테스트 9 〉	통과 (12.50ms, 61.2MB)
 * 테스트 10 〉	통과 (11.42ms, 62.3MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf("Jane", "Kim")), "김서방은 1에 있다")
}

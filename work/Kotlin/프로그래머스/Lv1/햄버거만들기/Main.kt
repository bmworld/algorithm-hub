package 프로그래머스.Lv1.햄버거만들기

import util.validate

class Solution {

  val BREAD = 1
  val VEGETABLE = 2
  val MEAT = 3
  fun solution(ingredient: IntArray): Int {
    var ans = 0
    val stack = IntArray(ingredient.size)
    var i = 0
    for (x in ingredient) {
      if (x == BREAD && i >= 3 && stack[i - 1] == MEAT && stack[i - 2] == VEGETABLE && stack[i - 3] == BREAD) {
        ans++
        i -= 3
      } else stack[i++] = x
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 58.7MB)
 * 테스트 2 〉	통과 (0.01ms, 58MB)
 * 테스트 3 〉	통과 (4.69ms, 69.6MB)
 * 테스트 4 〉	통과 (6.49ms, 81.5MB)
 * 테스트 5 〉	통과 (8.16ms, 88.2MB)
 * 테스트 6 〉	통과 (5.41ms, 74.4MB)
 * 테스트 7 〉	통과 (6.02ms, 79.8MB)
 * 테스트 8 〉	통과 (5.15ms, 75.7MB)
 * 테스트 9 〉	통과 (4.33ms, 71MB)
 * 테스트 10 〉	통과 (0.23ms, 59.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(ingredient: IntArray): Int {
 *         var answer: Int = 0
 *         val sb = StringBuilder()
 *         for(item in ingredient) {
 *             sb.append('0'+item)
 *             if(sb.length >= 4 && sb.substring(sb.length-4) == "1231") {
 *                 sb.setLength(sb.length-4)
 *                 answer++
 *             }
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (0.03ms, 59.5MB)
 * 테스트 2 〉	통과 (0.03ms, 59.6MB)
 * 테스트 3 〉	통과 (13.50ms, 84.1MB)
 * 테스트 4 〉	통과 (21.61ms, 104MB)
 * 테스트 5 〉	통과 (30.67ms, 132MB)
 * 테스트 6 〉	통과 (19.58ms, 93.3MB)
 * 테스트 7 〉	통과 (25.01ms, 98.6MB)
 * 테스트 8 〉	통과 (17.83ms, 94.4MB)
 * 테스트 9 〉	통과 (14.99ms, 87.6MB)
 * 테스트 10 〉	통과 (1.32ms, 59.8MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(2, 1, 1, 2, 3, 1, 2, 3, 1)), 2)
  validate(s.solution(intArrayOf(1, 3, 2, 1, 2, 1, 3, 1, 2)), 0)
  validate(s.solution(intArrayOf(1, 1, 2, 3, 3, 1)), 0)
}

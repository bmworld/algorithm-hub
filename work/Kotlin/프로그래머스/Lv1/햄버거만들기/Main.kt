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
      when (x) {
        BREAD -> if (i >= 3 && stack[i - 1] == MEAT && stack[i - 2] == VEGETABLE && stack[i - 3] == BREAD) {
          ans++
          i -= 3
        } else stack[i++] = x
        VEGETABLE -> if (i >= 1 && stack[i - 1] == BREAD) stack[i++] = x
        MEAT -> if (i >= 2 && stack[i - 1] == VEGETABLE) stack[i++] = x
      }
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 59.9MB)
 * 테스트 2 〉	통과 (0.01ms, 58.3MB)
 * 테스트 3 〉	실패 (6.10ms, 70.9MB)
 * 테스트 4 〉	실패 (11.42ms, 83MB)
 * 테스트 5 〉	실패 (11.29ms, 88.5MB)
 * 테스트 6 〉	실패 (8.46ms, 75.4MB)
 * 테스트 7 〉	실패 (8.26ms, 79.8MB)
 * 테스트 8 〉	실패 (9.74ms, 76.6MB)
 * 테스트 9 〉	실패 (5.88ms, 71.2MB)
 * 테스트 10 〉	실패 (0.34ms, 59.6MB)
 * 테스트 11 〉	실패 (5.00ms, 68.7MB)
 * 테스트 12 〉	실패 (12.55ms, 93.8MB)
 * 테스트 13 〉	통과 (0.01ms, 58.2MB)
 * 테스트 14 〉	통과 (0.01ms, 60.5MB)
 * 테스트 15 〉	통과 (0.01ms, 58.5MB)
 * 테스트 16 〉	통과 (0.01ms, 57.9MB)
 * 테스트 17 〉	통과 (0.01ms, 58.6MB)
 * 테스트 18 〉	통과 (0.01ms, 59.4MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(2, 1, 1, 2, 3, 1, 2, 3, 1)), 2)
  validate(s.solution(intArrayOf(1, 3, 2, 1, 2, 1, 3, 1, 2)), 0)
  validate(s.solution(intArrayOf(1, 1, 2, 3, 3, 1)), 0)
}

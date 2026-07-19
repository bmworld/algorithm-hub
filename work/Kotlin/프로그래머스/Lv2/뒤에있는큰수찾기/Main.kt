package 프로그래머스.Lv2.뒤에있는큰수찾기

import util.validate

class Solution {

  companion object {

    const val EMPTY = -1
    const val INF = Int.MAX_VALUE

  }

  fun solution(numbers: IntArray): IntArray {
    val N = numbers.size

    val ans = IntArray(N) { EMPTY }
    val stack = IntArray(N + 1)
    var top = EMPTY

    repeat(N) {
      val i = N - (it + 1)
      val a = numbers[i]

      while (top >= 0 && stack[top] <= a) top--

      if (top >= 0) ans[i] = stack[top]

      stack[++top] = a
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 61.5MB)
 * 테스트 2 〉	통과 (0.01ms, 60.7MB)
 * 테스트 3 〉	통과 (0.01ms, 60.6MB)
 * 테스트 4 〉	통과 (0.02ms, 58.4MB)
 * 테스트 5 〉	통과 (0.09ms, 61.2MB)
 * 테스트 6 〉	통과 (0.76ms, 63.1MB)
 * 테스트 7 〉	통과 (0.71ms, 62.8MB)
 * 테스트 8 〉	통과 (2.03ms, 70.8MB)
 * 테스트 9 〉	통과 (1.81ms, 71.5MB)
 * 테스트 10 〉	통과 (2.70ms, 78.2MB)
 * 테스트 11 〉	통과 (2.37ms, 79.4MB)
 * 테스트 12 〉	통과 (4.38ms, 93MB)
 * 테스트 13 〉	통과 (4.59ms, 91.6MB)
 * 테스트 14 〉	통과 (8.37ms, 139MB)
 * 테스트 15 〉	통과 (13.92ms, 182MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * import java.util.*
 * class Solution {
 *
 *     fun solution(numbers: IntArray): IntArray {
 *         val answer = mutableListOf<Int>()
 *         val s = Stack<Int>()
 *
 *         for(i in numbers.lastIndex downTo 0) {
 *             var bigNum = -1
 *             while(s.isNotEmpty()){
 *                 if(s.peek() > numbers[i]) {
 *                     bigNum = s.peek()
 *                     break
 *                 }
 *                 else { s.pop() }
 *             }
 *             answer.add(bigNum)
 *             s.push(numbers[i])
 *         }
 *         return answer.reversed().toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (17.32ms, 62.1MB)
 * 테스트 2 〉	통과 (12.52ms, 63.6MB)
 * 테스트 3 〉	통과 (14.47ms, 64.1MB)
 * 테스트 4 〉	통과 (14.26ms, 63.1MB)
 * 테스트 5 〉	통과 (18.48ms, 64.9MB)
 * 테스트 6 〉	통과 (17.57ms, 66.2MB)
 * 테스트 7 〉	통과 (17.63ms, 67.2MB)
 * 테스트 8 〉	통과 (34.00ms, 77.2MB)
 * 테스트 9 〉	통과 (26.35ms, 77.3MB)
 * 테스트 10 〉	통과 (35.19ms, 87.9MB)
 * 테스트 11 〉	통과 (35.21ms, 87.8MB)
 * 테스트 12 〉	통과 (55.11ms, 104MB)
 * 테스트 13 〉	통과 (69.62ms, 104MB)
 * 테스트 14 〉	통과 (101.53ms, 160MB)
 * 테스트 15 〉	통과 (183.76ms, 227MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(2, 3, 3, 5)), intArrayOf(3, 5, 5, -1))
  validate(s.solution(intArrayOf(9, 1, 5, 3, 6, 2)), intArrayOf(-1, 5, 6, 6, -1, -1))
}

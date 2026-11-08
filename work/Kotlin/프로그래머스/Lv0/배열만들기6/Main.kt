package 프로그래머스.Lv0.배열만들기6

import util.validate

class Solution {

  fun solution(arr: IntArray): IntArray {
    val stk = IntArray(arr.size)
    var i = 0
    for (j in arr.indices) {
      val x = arr[j]
      if (i == 0 || stk[i - 1] != x) stk[i++] = x
      else i--
    }

    return if (i == 0) intArrayOf(-1) else stk.copyOf(i)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 58.7MB)
 * 테스트 2 〉	통과 (0.01ms, 59.2MB)
 * 테스트 3 〉	통과 (0.01ms, 59.5MB)
 * 테스트 4 〉	통과 (0.02ms, 58.6MB)
 *
 * [RIVAL 1]
 * import java.util.*;
 *
 * class Solution {
 *     fun solution(arr: IntArray): Stack<Int> {
 *         val stk = Stack<Int>()
 *
 *         for (i in arr.indices) {
 *             if (stk.isEmpty()) {
 *                 stk.push(arr[i])
 *             } else if (stk.peek() == arr[i]) {
 *                 stk.pop()
 *             } else {
 *                 stk.push(arr[i])
 *             }
 *         }
 *         if (stk.isEmpty()) stk.push(-1)
 *         return stk
 *     }
 * }
 * 테스트 1 〉	통과 (0.24ms, 59.3MB)
 * 테스트 2 〉	통과 (0.23ms, 60.1MB)
 * 테스트 3 〉	통과 (0.24ms, 60.5MB)
 * 테스트 4 〉	통과 (0.24ms, 58.8MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(arr: IntArray): IntArray {
 *         var i = 0
 *         val answer = mutableListOf<Int>()
 *         while ( i < arr.size ) {
 *             if (answer.isEmpty()) answer.add(arr[i++])
 *             else if (answer.last() == arr[i]) {
 *                 answer.removeLast()
 *                 i++
 *             }
 *             else if (answer.last() != arr[i]) answer.add(arr[i++])
 *         }
 *         return if (answer.isEmpty()) intArrayOf(-1) else answer.toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (3.61ms, 60.2MB)
 * 테스트 2 〉	통과 (3.68ms, 60.4MB)
 * 테스트 3 〉	통과 (3.65ms, 60.6MB)
 * 테스트 4 〉	통과 (4.25ms, 60.2MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(0, 1, 1, 1, 0)), intArrayOf(0, 1, 0))
  validate(s.solution(intArrayOf(0, 1, 0, 1, 0)), intArrayOf(0, 1, 0, 1, 0))
  validate(s.solution(intArrayOf(0, 1, 1, 0)), intArrayOf(-1))
  validate(s.solution(intArrayOf(0, 1, 1, 1, 1, 0, 1)), intArrayOf(1))
}

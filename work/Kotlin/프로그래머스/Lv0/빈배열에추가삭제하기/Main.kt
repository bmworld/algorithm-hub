package 프로그래머스.Lv0.빈배열에추가삭제하기

import util.validate

class Solution {
  companion object {

    const val MAX_NUM = 9
    const val MTPLR = 2
  }

  fun solution(arr: IntArray, flag: BooleanArray): IntArray {
    val tmp = IntArray(MAX_NUM * MTPLR * arr.size)
    var len = 0
    var cnt = 0
    var x = 0

    for (i in arr.indices) {
      val n = arr[i]
      if (flag[i]) {
        repeat(cnt) { tmp[len++] = x }
        x = n.also { cnt = it * MTPLR }
      } else {
        cnt -= n
        if (cnt < 0) {
          len = maxOf(0, len + cnt)
          cnt = 0
        }
      }
    }

    repeat(cnt) { tmp[len++] = x }

    return tmp.copyOf(len)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.3MB)
 * 테스트 2 〉	통과 (0.02ms, 59.2MB)
 * 테스트 3 〉	통과 (0.02ms, 60MB)
 * 테스트 4 〉	통과 (0.02ms, 60.6MB)
 * 테스트 5 〉	통과 (0.03ms, 59.2MB)
 * 테스트 6 〉	통과 (0.03ms, 59.5MB)
 * 테스트 7 〉	통과 (0.02ms, 59.8MB)
 * 테스트 8 〉	통과 (0.03ms, 59.7MB)
 * 테스트 9 〉	통과 (0.03ms, 59.8MB)
 * 테스트 10 〉	통과 (0.03ms, 60.1MB)
 *
 *
 *
 * [RIVAL 1]
 * import java.util.*
 *
 * class Solution {
 *     fun solution(arr: IntArray, flag: BooleanArray): Stack<Int> {
 *         val stack = Stack<Int>()
 *
 *         for (i in arr.indices) {
 *             for (j in 0 until arr[i] * if (flag[i]) 2 else 1) {
 *                 if (flag[i]) stack.push(arr[i]) else stack.pop()
 *             }
 *         }
 *         return stack
 *     }
 * }
 * 테스트 1 〉	통과 (0.33ms, 59.1MB)
 * 테스트 2 〉	통과 (0.36ms, 59.4MB)
 * 테스트 3 〉	통과 (0.29ms, 58.3MB)
 * 테스트 4 〉	통과 (0.27ms, 60.1MB)
 * 테스트 5 〉	통과 (0.24ms, 60.8MB)
 * 테스트 6 〉	통과 (0.25ms, 60.1MB)
 * 테스트 7 〉	통과 (0.26ms, 60.8MB)
 * 테스트 8 〉	통과 (0.28ms, 58.4MB)
 * 테스트 9 〉	통과 (0.35ms, 59.5MB)
 * 테스트 10 〉	통과 (0.40ms, 59.8MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(arr: IntArray, flag: BooleanArray): IntArray {
 *         var answer: IntArray = intArrayOf()
 *         for (i in flag.indices) {
 *             if (flag[i]) {
 *                 repeat(arr[i]*2) {
 *                     answer += arr[i]
 *                 }
 *             } else {
 *                 answer = answer.copyOfRange(0, answer.size-arr[i])
 *             }
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (6.18ms, 63.3MB)
 * 테스트 2 〉	통과 (6.13ms, 64.1MB)
 * 테스트 3 〉	통과 (6.25ms, 63.3MB)
 * 테스트 4 〉	통과 (6.22ms, 63.3MB)
 * 테스트 5 〉	통과 (6.39ms, 63.4MB)
 * 테스트 6 〉	통과 (7.14ms, 63.9MB)
 * 테스트 7 〉	통과 (6.36ms, 62.8MB)
 * 테스트 8 〉	통과 (6.34ms, 63.4MB)
 * 테스트 9 〉	통과 (6.57ms, 64.1MB)
 * 테스트 10 〉	통과 (6.67ms, 63.8MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    intArrayOf(3, 2, 4, 1, 3), booleanArrayOf(true, false, true, false, false)),
    intArrayOf(3, 3, 3, 3, 4, 4, 4, 4)
  )

  validate(s.solution(
    intArrayOf(1, 1, 1, 2, 2, 3), booleanArrayOf(true, false, false, true, false, true)),
    intArrayOf(2, 2, 3, 3, 3, 3, 3, 3)
  )

  validate(s.solution(
    intArrayOf(3), booleanArrayOf(true)),
    intArrayOf(3, 3, 3, 3, 3, 3)
  )

  validate(s.solution(
    intArrayOf(2, 3), booleanArrayOf(true, false)),
    intArrayOf(2)
  )

  validate(s.solution(
    intArrayOf(2, 1, 5), booleanArrayOf(true, true, false)),
    intArrayOf(2)
  )
}

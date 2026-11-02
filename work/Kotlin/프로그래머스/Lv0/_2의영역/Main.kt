package 프로그래머스.Lv0._2의영역

import util.validate

class Solution {

  fun solution(arr: IntArray): IntArray {
    var s = -1
    var e = -1
    for (i in arr.indices) {
      if (arr[i] == 2) {
        if (s == -1) s = i
        if (e < i) e = i
      }
    }

    return if (s == -1) intArrayOf(-1) else {
      val len = e - s + 1
      val ans = IntArray(len)
      System.arraycopy(arr, s, ans, 0, len)
      ans
    }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.48ms, 60.9MB)
 * 테스트 2 〉	통과 (0.35ms, 60.5MB)
 * 테스트 3 〉	통과 (0.90ms, 66.3MB)
 * 테스트 4 〉	통과 (1.40ms, 73.1MB)
 * 테스트 5 〉	통과 (0.91ms, 71.6MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(arr: IntArray): IntArray {
 *         return if (arr.contains(2)) arr.copyOfRange(arr.indexOf(2), arr.lastIndexOf(2) + 1) else intArrayOf(-1)
 *     }
 * }
 * 테스트 1 〉	통과 (9.62ms, 64.6MB)
 * 테스트 2 〉	통과 (9.28ms, 63.4MB)
 * 테스트 3 〉	통과 (9.50ms, 71.3MB)
 * 테스트 4 〉	통과 (9.20ms, 76.2MB)
 * 테스트 5 〉	통과 (8.93ms, 75.3MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(arr: IntArray): IntArray {
 *         val left = arr.indexOfFirst { it == 2 }
 *         val right = arr.indexOfLast { it == 2 }
 *         if (left == -1 && right == -1) return intArrayOf(-1)
 *         return arr.slice(left..right).toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (1.89ms, 59.1MB)
 * 테스트 2 〉	통과 (14.55ms, 64.3MB)
 * 테스트 3 〉	통과 (21.16ms, 70.9MB)
 * 테스트 4 〉	통과 (20.56ms, 78.1MB)
 * 테스트 5 〉	통과 (17.19ms, 76.1MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(1, 2, 1, 4, 5, 2, 9)), intArrayOf(2, 1, 4, 5, 2))
  validate(s.solution(intArrayOf(1, 3, 5)), intArrayOf(-1))
  validate(s.solution(intArrayOf(1, 2, 1)), intArrayOf(2))
  validate(s.solution(intArrayOf(1, 2, 1, 2, 1, 10, 2, 1)), intArrayOf(2, 1, 2, 1, 10, 2))
}

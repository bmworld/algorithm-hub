package 프로그래머스.Lv1.제일작은수제거하기

import util.validate

class Solution {

  fun solution(arr: IntArray): IntArray {
    val len = arr.size
    var cnt = 0
    var min = Int.MAX_VALUE
    for (x in arr) {
      if (x < min) {
        min = x
        cnt = 1
      } else if (x == min) cnt++
    }

    val nLen = len - cnt
    if (nLen == 0) return intArrayOf(-1)

    var ans = IntArray(nLen)
    var i = 0
    for (x in arr) if (x != min) ans[i++] = x
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (1.23ms, 68.5MB)
 * 테스트 2 〉	통과 (0.02ms, 59.2MB)
 * 테스트 3 〉	통과 (0.03ms, 59MB)
 * 테스트 4 〉	통과 (0.02ms, 58.6MB)
 * 테스트 5 〉	통과 (0.01ms, 58.5MB)
 * 테스트 6 〉	통과 (0.03ms, 59.1MB)
 * 테스트 7 〉	통과 (0.04ms, 58.9MB)
 * 테스트 8 〉	통과 (0.01ms, 58.9MB)
 * 테스트 9 〉	통과 (0.02ms, 58.5MB)
 * 테스트 10 〉	통과 (0.01ms, 58.3MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(arr: IntArray): IntArray = if(arr.size == 1) arrayOf(-1).toIntArray()
 *                                             else arr.filter { it != arr.min() }.toIntArray()
 * }
 * 테스트 1 〉	통과 (688.57ms, 75MB)
 * 테스트 2 〉	통과 (14.15ms, 63.4MB)
 * 테스트 3 〉	통과 (14.48ms, 62.9MB)
 * 테스트 4 〉	통과 (13.98ms, 62.7MB)
 * 테스트 5 〉	통과 (13.01ms, 62.4MB)
 * 테스트 6 〉	통과 (14.40ms, 64.1MB)
 * 테스트 7 〉	통과 (15.89ms, 63.1MB)
 * 테스트 8 〉	통과 (12.38ms, 62MB)
 * 테스트 9 〉	통과 (12.44ms, 64.4MB)
 * 테스트 10 〉	통과 (12.32ms, 63MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(1, 2, 3, 4)), intArrayOf(2, 3, 4))
  validate(s.solution(intArrayOf(1, 1, 3, 3, 1)), intArrayOf(3, 3))
  validate(s.solution(intArrayOf(10)), intArrayOf(-1))
}

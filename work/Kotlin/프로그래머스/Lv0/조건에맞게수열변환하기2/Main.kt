package 프로그래머스.Lv0.조건에맞게수열변환하기2

import util.validate

class Solution {

  fun solution(arr: IntArray): Int {
    var ans = 0
    val N = arr.size

    while (true) {
      val tmp = arr.copyOf()
      var same = true
      repeat(N) {
        val x = tmp[it]
        when {
          x >= 50 && x % 2 == 0 -> tmp[it] = x / 2
          x < 50 && x % 2 == 1 -> tmp[it] = x * 2 + 1
        }
        if (same && tmp[it] != arr[it]) same = false
      }

      if (same) break

      System.arraycopy(tmp, 0, arr, 0, N)
      ans++
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.7MB)
 * 테스트 2 〉	통과 (0.02ms, 58.3MB)
 * 테스트 3 〉	통과 (0.02ms, 59.7MB)
 * 테스트 4 〉	통과 (0.05ms, 60.2MB)
 * 테스트 5 〉	통과 (0.03ms, 58.7MB)
 * 테스트 6 〉	통과 (0.04ms, 58.5MB)
 *
 * [RIVAL]
 * import java.util.*
 *
 * class Solution {
 *     fun solution(arr: IntArray): Int {
 *         var i = 0
 *         val array = arr
 *
 *         while (true) {
 *             val newArray = array.map { operand ->
 *                 if (operand > 49 && operand % 2 == 0) operand / 2
 *                 else if (50 > operand && operand % 2 == 1) operand * 2 + 1
 *                 else operand
 *             }.toIntArray()
 *
 *             if (array.contentEquals(newArray)) break
 *             Arrays.setAll(array) { newArray[it] }
 *             i++
 *         }
 *         return i
 *     }
 * }
 * 테스트 1 〉	통과 (5.15ms, 59MB)
 * 테스트 2 〉	통과 (7.37ms, 60.6MB)
 * 테스트 3 〉	통과 (5.32ms, 61.7MB)
 * 테스트 4 〉	통과 (6.33ms, 58.3MB)
 * 테스트 5 〉	통과 (4.44ms, 58.8MB)
 * 테스트 6 〉	통과 (5.54ms, 59.9MB)
 *
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(arr: IntArray): Int {
 *         var change = 0;
 *         while (convert(arr)) change++
 *         return change
 *     }
 *
 *     private fun convert(arr: IntArray): Boolean {
 *         var flag = false
 *         arr.indices.forEach {
 *             if (arr[it] >= 50 && arr[it] % 2 == 0) {
 *                 arr[it] /= 2
 *                 flag = true
 *             } else if (arr[it] < 50 && arr[it] % 2 == 1) {
 *                 arr[it] = arr[it] * 2 + 1
 *                 flag = true
 *             }
 *         }
 *         return flag
 *     }
 * }
 * 테스트 1 〉	통과 (11.05ms, 62.5MB)
 * 테스트 2 〉	통과 (10.37ms, 62.5MB)
 * 테스트 3 〉	통과 (13.55ms, 62.8MB)
 * 테스트 4 〉	통과 (11.49ms, 63.3MB)
 * 테스트 5 〉	통과 (10.19ms, 63.2MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(
      intArrayOf(1, 2, 3, 100, 99, 98)), 5
  )

  validate(
    s.solution(
      intArrayOf(3, 123123)), 4
  )

}

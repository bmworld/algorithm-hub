package 프로그래머스.Lv2.행렬의곱셈

import util.validate

class Solution {

  fun solution(a1: Array<IntArray>, a2: Array<IntArray>): Array<IntArray> {
    val rows = a1.size
    val cols = a2[0].size
    val times = a1[0].size

    return Array(rows) { r ->
      val a1R = a1[r]
      IntArray(cols) { c ->
        var sum = 0
        repeat(times) { i ->
          sum += a1R[i] * a2[i][c]
        }
        sum
      }
    }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.51ms, 59.5MB)
 * 테스트 2 〉	통과 (2.92ms, 61.6MB)
 * 테스트 3 〉	통과 (2.81ms, 62.5MB)
 * 테스트 4 〉	통과 (0.23ms, 59.3MB)
 * 테스트 5 〉	통과 (2.46ms, 61.2MB)
 * 테스트 6 〉	통과 (2.19ms, 62.6MB)
 * 테스트 7 〉	통과 (0.20ms, 60.9MB)
 * 테스트 8 〉	통과 (0.09ms, 61.2MB)
 * 테스트 9 〉	통과 (0.07ms, 60.8MB)
 * 테스트 10 〉	통과 (2.96ms, 60.7MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
 *         var answer = arrayOf<IntArray>()
 *         var answer2 = Array(arr1.size, {IntArray(arr2[0].size, {0})})
 *
 *         var sum : Int = 0
 *
 *         for(i in 0..arr1.size-1 step 1){
 *             for(j in 0..arr2[0].size-1 step 1){
 *
 *                 for(k in 0..arr1[0].size-1)
 *                 {
 *                     sum += arr1[i][k] * arr2[k][j]
 *                 }
 *                 answer2[i][j] = sum
 *                 sum = 0
 *             }
 *         }
 *         answer = answer2
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (0.59ms, 60.1MB)
 * 테스트 2 〉	통과 (3.08ms, 61.3MB)
 * 테스트 3 〉	통과 (2.65ms, 62MB)
 * 테스트 4 〉	통과 (0.27ms, 58.9MB)
 * 테스트 5 〉	통과 (2.42ms, 61.1MB)
 * 테스트 6 〉	통과 (2.34ms, 61.2MB)
 * 테스트 7 〉	통과 (0.24ms, 60.5MB)
 * 테스트 8 〉	통과 (0.09ms, 61MB)
 * 테스트 9 〉	통과 (0.07ms, 60.1MB)
 * 테스트 10 〉	통과 (2.30ms, 60.7MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      intArrayOf(1, 4),
      intArrayOf(3, 2),
      intArrayOf(4, 1),
    ),
    arrayOf(
      intArrayOf(3, 3),
      intArrayOf(3, 3),
    ),
  ),
    arrayOf(
      intArrayOf(15, 15),
      intArrayOf(15, 15),
      intArrayOf(15, 15),
    )
  )

  validate(s.solution(
    arrayOf(
      intArrayOf(2, 3, 2),
      intArrayOf(4, 2, 4),
      intArrayOf(3, 1, 4),
    ),
    arrayOf(
      intArrayOf(5, 4, 3),
      intArrayOf(2, 4, 1),
      intArrayOf(3, 1, 1),
    ),
  ),
    arrayOf(
      intArrayOf(22, 22, 11),
      intArrayOf(36, 28, 18),
      intArrayOf(29, 20, 14),
    )
  )
}

package 프로그래머스.Lv2.행렬테두리회전하기

import util.validate

class Solution {

  fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
    val ans = IntArray(queries.size)
    val a = IntArray(rows * columns) { it + 1 }
    fun pos(r: Int, c: Int): Int = (r - 1) * columns + (c - 1)

    for (i in queries.indices) {
      val q = queries[i]
      val r1 = q[0]
      val c1 = q[1]
      val r2 = q[2]
      val c2 = q[3]

      val last = a[pos(r1, c1)]
      val cTimes = c2 - c1
      val rTimes = r2 - r1

      var min = last
      repeat(rTimes) {
        a[pos(r1 + it, c1)] = a[pos(r1 + it + 1, c1)].also { if (it < min) min = it }
      }
      repeat(cTimes) {
        a[pos(r2, c1 + it)] = a[pos(r2, c1 + it + 1)].also { if (it < min) min = it }
      }
      repeat(rTimes) {
        a[pos(r2 - it, c2)] = a[pos(r2 - it - 1, c2)].also { if (it < min) min = it }
      }
      repeat(cTimes - 1) {
        a[pos(r1, c2 - it)] = a[pos(r1, c2 - it - 1)].also { if (it < min) min = it }
      }

      a[pos(r1, c1 + 1)] = last

      ans[i] = min
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.20ms, 59.8MB)
 * 테스트 2 〉	통과 (0.17ms, 60.9MB)
 * 테스트 3 〉	통과 (7.12ms, 68.2MB)
 * 테스트 4 〉	통과 (5.36ms, 67.5MB)
 * 테스트 5 〉	통과 (5.84ms, 67.8MB)
 * 테스트 6 〉	통과 (6.76ms, 69.4MB)
 * 테스트 7 〉	통과 (7.49ms, 71.8MB)
 * 테스트 8 〉	통과 (5.64ms, 67.9MB)
 * 테스트 9 〉	통과 (6.74ms, 67.5MB)
 * 테스트 10 〉	통과 (6.21ms, 69MB)
 * 테스트 11 〉	통과 (5.69ms, 68.2MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
 *         var answer = intArrayOf()
 *         val arr = Array(rows) {i -> IntArray(columns) {j -> (i*columns) + j+1} }
 *         queries.forEach {
 *             val r1 = it[0] - 1
 *             val c1 = it[1] - 1
 *             val r2 = it[2] - 1
 *             val c2 = it[3] - 1
 *             val a1 = IntArray(c2 - c1) {c -> arr[r1][c1+c]}
 *             val a2 = IntArray(r2 - r1) {r -> arr[r1+r][c2]}
 *             val a3 = IntArray(c2 - c1) {c -> arr[r2][c1+c+1]}
 *             val a4 = IntArray(r2 - r1) {r -> arr[r1+r+1][c1]}
 *             var min = rows*columns
 *             a1.forEachIndexed { c, i ->
 *                 arr[r1][c+c1+1] = i
 *                 min = Math.min(min, i)
 *             }
 *             a2.forEachIndexed { r, i ->
 *                 arr[r1+r+1][c2] = i
 *                 min = Math.min(min, i)
 *             }
 *             a3.forEachIndexed { c, i ->
 *                 arr[r2][c+c1] = i
 *                 min = Math.min(min, i)
 *             }
 *             a4.forEachIndexed { r, i ->
 *                 arr[r1+r][c1] = i
 *                 min = Math.min(min, i)
 *             }
 *             answer += min
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (6.31ms, 62.5MB)
 * 테스트 2 〉	통과 (7.04ms, 62.8MB)
 * 테스트 3 〉	통과 (53.62ms, 141MB)
 * 테스트 4 〉	통과 (32.76ms, 114MB)
 * 테스트 5 〉	통과 (30.98ms, 114MB)
 * 테스트 6 〉	통과 (54.65ms, 184MB)
 * 테스트 7 〉	통과 (75.43ms, 228MB)
 * 테스트 8 〉	통과 (30.92ms, 114MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
 *         // 숫자 채우기
 *         var n = 1
 *         val box = Array(rows + 1) { Array(columns + 1) { 0 } }
 *         for (i in 1 until box.size) {
 *             for (j in 1 until box[i].size) {
 *                 box[i][j] = n++
 *             }
 *         }
 *         // 회전
 *         return IntArray(queries.size) { i -> rotate(box, queries[i]) }
 *     }
 *
 *     private fun rotate(box: Array<Array<Int>>, q: IntArray): Int {
 *         var min = Int.MAX_VALUE
 *         val tmp = box[q[0] + 1][q[1]]
 *
 *         for (i in q[0] + 1..q[2]) {
 *             box[i][q[1]] = if (i < q[2]) box[i + 1][q[1]] else box[i][q[1] + 1]
 *             min = minOf(min, box[i][q[1]])
 *         }
 *         for (j in q[1] + 1..q[3]) {
 *             box[q[2]][j] = if (j < q[3]) box[q[2]][j + 1] else box[q[2] - 1][j]
 *             min = minOf(min, box[q[2]][j])
 *         }
 *         for (i in q[2] - 1 downTo q[0]) {
 *             box[i][q[3]] = if (i > q[0]) box[i - 1][q[3]] else box[i][q[3] - 1]
 *             min = minOf(min, box[i][q[3]])
 *         }
 *         for (j in q[3] - 1 downTo q[1]) {
 *             box[q[0]][j] = if (j > q[1]) box[q[0]][j - 1] else tmp
 *             min = minOf(min, box[q[0]][j])
 *         }
 *         return min
 *     }
 * }
 * 테스트 1 〉	통과 (0.04ms, 60.2MB)
 * 테스트 2 〉	통과 (0.04ms, 60.8MB)
 * 테스트 3 〉	통과 (15.58ms, 81.1MB)
 * 테스트 4 〉	통과 (9.23ms, 74MB)
 * 테스트 5 〉	통과 (12.95ms, 77.2MB)
 * 테스트 6 〉	통과 (12.84ms, 79.4MB)
 * 테스트 7 〉	통과 (14.35ms, 81.4MB)
 * 테스트 8 〉	통과 (9.00ms, 73.9MB)
 *
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    6, 6,
    arrayOf(
      intArrayOf(2, 2, 5, 4),
      intArrayOf(3, 3, 6, 6),
      intArrayOf(5, 1, 6, 3),
    )),
    intArrayOf(8, 10, 25)
  )

  validate(s.solution(
    3, 3,
    arrayOf(
      intArrayOf(1, 1, 2, 2),
      intArrayOf(1, 2, 2, 3),
      intArrayOf(2, 1, 3, 2),
      intArrayOf(2, 2, 3, 3),
    )),
    intArrayOf(1, 1, 5, 3)
  )

  validate(s.solution(
    100, 97,
    arrayOf(
      intArrayOf(1, 1, 100, 97),
    )),
    intArrayOf(1)
  )

}

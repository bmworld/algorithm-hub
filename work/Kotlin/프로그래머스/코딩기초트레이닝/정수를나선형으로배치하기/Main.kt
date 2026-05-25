package 프로그래머스.코딩기초트레이닝.정수를나선형으로배치하기

import util.validate

class Solution {

  val CYCLE = 4
  val dc = intArrayOf(0, -1, 0, 1)
  val dr = intArrayOf(1, 0, -1, 0)

  fun solution(n: Int): Array<IntArray> {
    val a = Array(n) { IntArray(n) { it + 1 } }

    var x = n

    var d = 0
    var r = 0
    var c = n - 1
    repeat(n - 1) { i ->
      val len = n - (i + 1)
      repeat(2) {
        val cc = dc[d]
        val rr = dr[d]
        repeat(len) { j ->
          r += rr
          c += cc
          a[r][c] = ++x
        }
        d = if (d + 1 < CYCLE) d + 1 else 0
      }
    }
    return a
  }
}

/**
 * ```
 * ME:
 * 테스트 1 〉	통과 (0.02ms, 62.5MB)
 * 테스트 2 〉	통과 (0.02ms, 63.5MB)
 * 테스트 3 〉	통과 (0.04ms, 63.4MB)
 * 테스트 4 〉	통과 (0.02ms, 62.4MB)
 * 테스트 5 〉	통과 (0.03ms, 63.6MB)
 * 테스트 6 〉	통과 (0.01ms, 62.1MB)
 * 테스트 7 〉	통과 (0.03ms, 62.8MB)
 * 테스트 8 〉	통과 (0.08ms, 62.6MB)
 * 테스트 9 〉	통과 (0.04ms, 64.1MB)
 * 테스트 10 〉	통과 (0.05ms, 63.6MB)
 * 테스트 11 〉	통과 (0.06ms, 63.1MB)
 * 테스트 12 〉	통과 (0.05ms, 64.5MB)
 * 테스트 13 〉	통과 (0.01ms, 63.4MB)
 * 테스트 14 〉	통과 (0.02ms, 62.7MB)
 * 테스트 15 〉	통과 (0.05ms, 62.7MB)
 * 테스트 16 〉	통과 (0.02ms, 64.8MB)
 * 테스트 17 〉	통과 (0.05ms, 64.9MB)
 * 테스트 18 〉	통과 (0.02ms, 64MB)
 * 테스트 19 〉	통과 (0.04ms, 63MB)
 * 테스트 20 〉	통과 (0.06ms, 65.2MB)
 * 테스트 21 〉	통과 (0.02ms, 64.1MB)
 * 테스트 22 〉	통과 (0.02ms, 63.3MB)
 * 테스트 23 〉	통과 (0.01ms, 64.2MB)
 * 테스트 24 〉	통과 (0.05ms, 63.8MB)
 * 테스트 25 〉	통과 (0.04ms, 64MB)
 * 테스트 26 〉	통과 (0.02ms, 63.5MB)
 * 테스트 27 〉	통과 (0.02ms, 65.2MB)
 * 테스트 28 〉	통과 (0.08ms, 65.3MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * class Solution {
 *     fun solution(n: Int): Array<IntArray> {
 *         var answer = Array<IntArray>(n){ IntArray(n) }
 *         val dx = intArrayOf(-1,0,1,0)
 *         val dy = intArrayOf(0,1,0,-1)
 *         var (x, y) = 0 to 0
 *         var (sx, ex) = 0 to n
 *         var (sy, ey) = 0 to n
 *         var d = 1
 *         (1..(n*n)).forEach {
 *             answer[x][y] = it
 *             if (x+dx[d] !in sx until ex || y+dy[d] !in sy until ey) {
 *                 d = (d+1)%4
 *                 when (d) {
 *                     0 -> ex -= 1
 *                     1 -> sy += 1
 *                     2 -> sx += 1
 *                     else -> ey -= 1
 *                 }
 *             }
 *             x = x+dx[d]
 *             y = y+dy[d]
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (1.38ms, 62.8MB)
 * 테스트 2 〉	통과 (1.54ms, 62.3MB)
 * 테스트 3 〉	통과 (1.69ms, 62.5MB)
 * 테스트 4 〉	통과 (2.15ms, 62.6MB)
 * 테스트 5 〉	통과 (1.39ms, 64.9MB)
 * 테스트 6 〉	통과 (1.31ms, 62.8MB)
 * 테스트 7 〉	통과 (1.34ms, 62.5MB)
 * 테스트 8 〉	통과 (2.01ms, 62.6MB)
 * 테스트 9 〉	통과 (1.45ms, 63.7MB)
 * 테스트 10 〉	통과 (1.40ms, 63.4MB)
 * 테스트 11 〉	통과 (1.70ms, 62.1MB)
 * 테스트 12 〉	통과 (2.15ms, 63.4MB)
 * 테스트 13 〉	통과 (1.33ms, 63.2MB)
 * 테스트 14 〉	통과 (1.35ms, 64.4MB)
 * 테스트 15 〉	통과 (2.12ms, 62.6MB)
 * 테스트 16 〉	통과 (1.46ms, 62.3MB)
 * 테스트 17 〉	통과 (1.49ms, 63.7MB)
 * 테스트 18 〉	통과 (1.61ms, 63.7MB)
 * 테스트 19 〉	통과 (1.60ms, 62.4MB)
 * 테스트 20 〉	통과 (1.47ms, 63.9MB)
 * 테스트 21 〉	통과 (1.49ms, 61.9MB)
 *
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(1),
    arrayOf(
      intArrayOf(1)
    )
  )

  validate(
    s.solution(2),
    arrayOf(
      intArrayOf(1, 2),
      intArrayOf(4, 3),
    )
  )

  validate(
    s.solution(4),
    arrayOf(
      intArrayOf(1, 2, 3, 4),
      intArrayOf(12, 13, 14, 5),
      intArrayOf(11, 16, 15, 6),
      intArrayOf(10, 9, 8, 7),
    )
  )

  validate(
    s.solution(5),
    arrayOf(
      intArrayOf(1, 2, 3, 4, 5),
      intArrayOf(16, 17, 18, 19, 6),
      intArrayOf(15, 24, 25, 20, 7),
      intArrayOf(14, 23, 22, 21, 8),
      intArrayOf(13, 12, 11, 10, 9),
    )
  )

}

//
//repeat(n) { r ->
//  repeat(n) { c ->
//    println("ans[$r][$c] = ${ans[r][c]}")
//  }
//}

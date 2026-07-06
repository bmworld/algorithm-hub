package 프로그래머스.Lv1.바탕화면정리

import util.validate

class Solution {

  val FILE = 35
  fun solution(wallpaper: Array<String>): IntArray {
    val H = wallpaper.size
    val W = wallpaper[0].length

    var minR = H - 1
    var maxR = 0
    var minC = W - 1
    var maxC = 0

    repeat(H) { r ->
      val str = wallpaper[r]
      repeat(W) { c ->
        if (str[c].code == FILE) {
          if (r < minR) minR = r
          if (r > maxR) maxR = r
          if (c < minC) minC = c
          if (c > maxC) maxC = c
        }
      }
    }

    return intArrayOf(minR, minC, maxR + 1, maxC + 1)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 58.6MB)
 * 테스트 2 〉	통과 (0.01ms, 59.6MB)
 * 테스트 3 〉	통과 (0.01ms, 61.6MB)
 * 테스트 4 〉	통과 (0.01ms, 58MB)
 * 테스트 5 〉	통과 (0.01ms, 58.3MB)
 * 테스트 6 〉	통과 (0.01ms, 59MB)
 * 테스트 7 〉	통과 (0.03ms, 60.7MB)
 * 테스트 8 〉	통과 (0.04ms, 59.3MB)
 * 테스트 9 〉	통과 (0.06ms, 59.9MB)
 * 테스트 10 〉	통과 (0.03ms, 60MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * import kotlin.math.*
 *
 * class Solution {
 *     fun solution(wallpaper: Array<String>): IntArray {
 *         var minX = wallpaper.size
 *         var minY = wallpaper[0].length
 *         var maxX = 0
 *         var maxY = 0
 *
 *         for (i in wallpaper.indices) {
 *             for (j in wallpaper[i].indices) {
 *                 if (wallpaper[i][j] == '#') {
 *                     minX = min(i, minX)
 *                     minY = min(j, minY)
 *                     maxX = max(i, maxX)
 *                     maxY = max(j, maxY)
 *                 }
 *             }
 *         }
 *         return intArrayOf(minX, minY, maxX + 1, maxY + 1)
 *     }
 * }
 * 테스트 1 〉	통과 (0.02ms, 59.7MB)
 * 테스트 2 〉	통과 (0.02ms, 59MB)
 * 테스트 3 〉	통과 (0.02ms, 57.8MB)
 * 테스트 4 〉	통과 (0.02ms, 57.4MB)
 * 테스트 5 〉	통과 (0.03ms, 59MB)
 * 테스트 6 〉	통과 (0.02ms, 59MB)
 * 테스트 7 〉	통과 (0.04ms, 58.9MB)
 * 테스트 8 〉	통과 (0.05ms, 58.4MB)
 * 테스트 9 〉	통과 (0.09ms, 58.9MB)
 * 테스트 10 〉	통과 (0.07ms, 58.1MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(".#...", "..#..", "...#.")),
    intArrayOf(0, 1, 3, 4)
  )
  validate(
    s.solution(
      arrayOf("..........", ".....#....", "......##..", "...##.....", "....#.....")),
    intArrayOf(1, 3, 5, 8)
  )
  validate(
    s.solution(
      arrayOf(".##...##.", "#..#.#..#", "#...#...#", ".#.....#.", "..#...#..", "...#.#...",
        "....#....")),
    intArrayOf(0, 0, 7, 9)
  )

  validate(
    s.solution(
      arrayOf("..", "#.")),
    intArrayOf(1, 0, 2, 1)
  )


}

//          println("[$r, $c] $minR, $minC, $maxR, $maxC")

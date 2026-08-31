package 프로그래머스.Lv2.거리두기확인하기

import util.validate

class Solution {

  companion object {

    const val P = 'P'
    const val O = 'O'

    val s1dr = intArrayOf(-1, 0, 1)
    val s1dc = intArrayOf(0, 1, 0)
    val s2drdc = arrayOf(
      arrayOf(
        intArrayOf(-1, -2, -1),
        intArrayOf(-1, 0, 1)
      ),
      arrayOf(
        intArrayOf(-1, 0, 1),
        intArrayOf(1, 2, 1)
      ),
      arrayOf(
        intArrayOf(1, 2, 1),
        intArrayOf(-1, 0, 1)
      )
    )
  }

  fun solution(places: Array<Array<String>>): IntArray {
    val N = places.size
    val R = places[0].size
    val C = places[0][0].length
    var ans = IntArray(N)

    repeat(N) {
      val place = places[it]

      for (r in 0 until R)
        for (c in 0 until C) {
          if (place[r][c] != P) continue

          for (dir in 0..2) {
            val nr = r + s1dr[dir]
            val nc = c + s1dc[dir]
            if (nr !in 0 until R || nc !in 0 until C) continue
            when (place[nr][nc]) {
              P -> {
                ans[it] = 0
                return@repeat
              }
              O -> {
                val s2 = s2drdc[dir]
                val s2dr = s2[0]
                val s2dc = s2[1]
                for (dir2 in 0..2) {
                  val nr2 = r + s2dr[dir2]
                  val nc2 = c + s2dc[dir2]
                  if (nr2 !in 0 until R || nc2 !in 0 until C) continue
                  if (place[nr2][nc2] == P) {
                    ans[it] = 0
                    return@repeat
                  }
                }
              }
            }
          }
        }
      ans[it] = 1
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.03ms, 60.6MB)
 * 테스트 2 〉	통과 (0.02ms, 59.8MB)
 * 테스트 3 〉	통과 (0.02ms, 60MB)
 * 테스트 4 〉	통과 (0.02ms, 59.3MB)
 * 테스트 5 〉	통과 (0.03ms, 60MB)
 * 테스트 6 〉	통과 (0.02ms, 59.7MB)
 * 테스트 7 〉	통과 (0.02ms, 59.5MB)
 * 테스트 8 〉	통과 (0.02ms, 61.3MB)
 * 테스트 9 〉	통과 (0.02ms, 60.1MB)
 * 테스트 10 〉	통과 (0.02ms, 59.4MB)
 *
 *
 *
 *
 * [RIVAL]
 * class Solution {
 *     fun solution(places: Array<Array<String>>): IntArray {
 *         var answer = IntArray(places.size)
 *         for (placeNum in places.indices) {
 *             var place = places[placeNum]
 *             var flag = true
 *             for (i in place.indices) {
 *                 for (j in place[0].indices) {
 *                     if (!flag)
 *                     break
 *                     if (place[i][j] == 'P')
 *                     flag = check(place, i, j, 0, 0)
 *                 }
 *                 if (!flag)
 *                     break
 *             }
 *             answer[placeNum] = if (flag) 1 else 0
 *         }
 *         return answer
 *     }
 *
 *     fun check(place: Array<String>, i: Int, j: Int, skip: Int, depth: Int): Boolean {
 *         //아래 확인 1
 *         var result = true
 *         if (result && skip != 1 && i < place.lastIndex)
 *             when (place[i + 1][j]) {
 *                 'P' -> return false
 *                 'O' -> if (depth == 0) result = check(place, i + 1, j, 0, 1)
 *             }
 *         //왼쪽 확인 2
 *         if (result && skip != 2 && j > 0)
 *             when (place[i][j - 1]) {
 *                 'P' -> return false
 *                 'O' -> if (depth == 0) result = check(place, i, j - 1, 3, 1)
 *             }
 *         //오른쪽 확인 3
 *         if (result && skip != 3 && j < place[0].lastIndex)
 *             when (place[i][j + 1]) {
 *                 'P' -> return false
 *                 'O' -> if (depth == 0) result = check(place, i, j + 1, 2, 1)
 *             }
 *         //전부 아닐시
 *         return result
 *     }
 * }
 * 테스트 1 〉	통과 (13.67ms, 64.3MB)
 * 테스트 2 〉	통과 (12.72ms, 64.2MB)
 * 테스트 3 〉	통과 (13.64ms, 65.1MB)
 * 테스트 4 〉	통과 (14.47ms, 62.7MB)
 * 테스트 5 〉	통과 (13.23ms, 63.7MB)
 * 테스트 6 〉	통과 (13.07ms, 64MB)
 * 테스트 7 〉	통과 (14.83ms, 62MB)
 * 테스트 8 〉	통과 (12.82ms, 64.2MB)
 * 테스트 9 〉	통과 (13.43ms, 63.5MB)
 * 테스트 10 〉	통과 (12.68ms, 65.3MB)
 *
 * [RIVAL 2]
 * import java.util.LinkedList
 * import kotlin.math.abs
 *
 * class Solution {
 *     fun solution(places: Array<Array<String>>): IntArray {
 *         return IntArray(places.size) { if (check(places[it])) 1 else 0 }
 *     }
 *
 *     private fun check(board: Array<String>): Boolean {
 *         val moves = arrayOf(-1 to 0, 0 to 1, 1 to 0, 0 to -1)
 *         for (i in board.indices) {
 *             for ((j, type) in board[i].withIndex()) {
 *                 if (type != 'P') continue
 *
 *                 val q = LinkedList<Pair<Int, Int>>().apply { offer(i to j) }
 *                 while (q.isNotEmpty()) {
 *                     val cur = q.poll()
 *
 *                     for ((mr, mc) in moves) {
 *                         val nr = cur.first + mr
 *                         val nc = cur.second + mc
 *                         val diff = abs(i - nr) + abs(j - nc)
 *
 *                         if (nr !in board.indices || nc !in board[0].indices || diff == 0) continue
 *                         if (diff <= 2 && board[nr][nc] == 'P') return false
 *
 *                         if (diff < 2 && board[nr][nc] == 'O') q.offer(nr to nc)
 *                     }
 *                 }
 *             }
 *         }
 *         return true
 *     }
 * }
 * 테스트 1 〉	통과 (1.07ms, 59.2MB)
 * 테스트 2 〉	통과 (0.85ms, 60MB)
 * 테스트 3 〉	통과 (0.72ms, 60.2MB)
 * 테스트 4 〉	통과 (0.60ms, 60MB)
 * 테스트 5 〉	통과 (0.69ms, 58.6MB)
 * 테스트 6 〉	통과 (0.69ms, 59.9MB)
 * 테스트 7 〉	통과 (0.60ms, 59.2MB)
 * 테스트 8 〉	통과 (0.66ms, 58.3MB)
 * 테스트 9 〉	통과 (0.60ms, 60.4MB)
 * 테스트 10 〉	통과 (0.59ms, 58.7MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf(
    arrayOf("POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"),
    arrayOf("POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"),
    arrayOf("PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"),
    arrayOf("OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"),
    arrayOf("PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"),
  )), intArrayOf(1, 0, 1, 1, 1))
}

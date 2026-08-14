package 프로그래머스.Lv2.리코쳇로봇

import util.validate

class Solution {
  companion object {

    const val STT = 'R'
    const val END = 'G'
    const val WALL = 'D'
    const val RIGHT = 0
    const val DOWN = 1
    const val LEFT = 2
    const val UP = 3
    val dirR = intArrayOf(0, 1, 0, -1)
    val dirC = intArrayOf(1, 0, -1, 0)
    const val EMPTY = -1
    const val DIR_SEP = 10
    const val C_SEP = DIR_SEP * 100
    const val R_SEP = C_SEP * 100
  }

  fun solution(board: Array<String>): Int {
    val R = board.size
    val C = board[0].length

    var sttR = EMPTY
    var sttC = EMPTY

    l@ for (r in 0 until R)
      for (c in 0 until C)
        if (board[r][c] == STT) {
          sttR = r
          sttC = c
          break@l
        }

    val ch = BooleanArray(R * C)
    fun pos(r: Int, c: Int): Int = r * C + c

    fun encode(cnt: Int, r: Int, c: Int, dir: Int): Int =
      cnt * R_SEP + r * C_SEP + c * DIR_SEP + dir

    val q = IntArray(R * C * 2 + 2)
    var qh = 0
    var qt = 0

    ch[pos(sttR, sttC)] = true
    repeat(4) { dir ->
      q[qt++] = encode(1, sttR, sttC, dir)
    }

    while (qh < qt) {
      val cntrcd = q[qh++]
      val cnt = cntrcd / R_SEP
      val rcd = cntrcd % R_SEP
      var r = rcd / C_SEP
      val cd = rcd % C_SEP
      var c = cd / DIR_SEP
      val d = cd % DIR_SEP

      val dr = dirR[d]
      val dc = dirC[d]

      while (r + dr in 0 until R && c + dc in 0 until C && board[r + dr][c + dc] != WALL) {
        r += dr
        c += dc
      }

      val pos = pos(r, c)
      if (ch[pos]) continue
      else ch[pos] = true

      when {
        board[r][c] == END -> return cnt
        d == UP || d == DOWN -> {
          q[qt++] = encode(cnt + 1, r, c, LEFT)
          q[qt++] = encode(cnt + 1, r, c, RIGHT)
        }
        d == LEFT || d == RIGHT -> {
          q[qt++] = encode(cnt + 1, r, c, UP)
          q[qt++] = encode(cnt + 1, r, c, DOWN)
        }
      }
    }
    return EMPTY
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.71ms, 59.1MB)
 * 테스트 2 〉	통과 (0.63ms, 59.5MB)
 * 테스트 3 〉	통과 (0.08ms, 61.3MB)
 * 테스트 4 〉	통과 (0.26ms, 60.8MB)
 * 테스트 5 〉	통과 (0.33ms, 59.2MB)
 * 테스트 6 〉	통과 (0.06ms, 60.2MB)
 * 테스트 7 〉	통과 (0.89ms, 60.5MB)
 * 테스트 8 〉	통과 (0.13ms, 59.1MB)
 * 테스트 9 〉	통과 (0.41ms, 60.5MB)
 * 테스트 10 〉	통과 (0.70ms, 60.3MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * import java.util.*
 *
 * class Solution {
 *     private val dx = intArrayOf(0, 1, 0, -1)
 *     private val dy = intArrayOf(1, 0, -1, 0)
 *
 *     fun solution(board: Array<String>): Int {
 *         val que = LinkedList<Triple<Int, Int, Int>>()
 *         que.add(getStartIndex(board))
 *         val isVisited = Array(board.size) { BooleanArray(board[0].length) }
 *         while (que.isNotEmpty()) {
 *             val index = que.poll()
 *             val y = index.first
 *             val x = index.second
 *             val count = index.third
 *
 *             if (board[y][x] == 'G') return count
 *             for (i in 0..3) {
 *                 val nextIndex = getNextIndex(x, y, dx[i], dy[i], board)
 *                 val ny = nextIndex.first
 *                 val nx = nextIndex.second
 *
 *                 if (ny == y && nextIndex.second == x) continue
 *                 if (isVisited[ny][nx]) continue
 *                 que.add(Triple(ny, nx, index.third + 1))
 *                 isVisited[ny][nx] = true
 *             }
 *         }
 *         return -1
 *     }
 *
 *     private fun getNextIndex(
 *         x: Int,
 *         y: Int,
 *         dx: Int,
 *         dy: Int,
 *         board: Array<String>,
 *     ): Pair<Int, Int> {
 *         var currentX = x
 *         var currentY = y
 *         while (true) {
 *             if (currentX + dx >= board[0].length || currentX + dx < 0) return Pair(currentY, currentX)
 *             if (currentY + dy >= board.size || currentY + dy < 0) return Pair(currentY, currentX)
 *             if (board[currentY + dy][currentX + dx] == 'D') return Pair(currentY, currentX)
 *             currentX += dx
 *             currentY += dy
 *         }
 *     }
 *
 *     private fun getStartIndex(board: Array<String>): Triple<Int, Int, Int> {
 *         for (i in board.indices) {
 *             board[i].forEachIndexed { index, c ->
 *                 if (c == 'R') return Triple(i, index, 0)
 *             }
 *         }
 *
 *         return Triple(-1, -1, -1)
 *     }
 * }
 * 테스트 1 〉	통과 (2.98ms, 60.5MB)
 * 테스트 2 〉	통과 (2.61ms, 59.3MB)
 * 테스트 3 〉	통과 (1.22ms, 60.2MB)
 * 테스트 4 〉	통과 (1.70ms, 59.1MB)
 * 테스트 5 〉	통과 (1.74ms, 59.5MB)
 * 테스트 6 〉	통과 (0.88ms, 59MB)
 * 테스트 7 〉	통과 (2.56ms, 61.6MB)
 * 테스트 8 〉	통과 (1.24ms, 60.8MB)
 * 테스트 9 〉	통과 (2.15ms, 60.3MB)
 * 테스트 10 〉	통과 (1.84ms, 60.9MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf(
    "...D..R",
    ".D.G...",
    "....D.D",
    "D....D.",
    "..D...."
  )), 7)

  validate(s.solution(arrayOf(
    "...D.GR",
    ".D.....",
    "....D.D",
    "D....D.",
    "..D...."
  )), -1)

}

//       println("- cnt=$cnt, dir=${d}, board[$r][$c] = ${board[r][c]}")

package 프로그래머스.Lv2.무인도여행

import util.validate

class Solution {
  companion object {

    const val SEA = 'X'
    const val SEP = 100
    const val ZERO = 48
    val dr = intArrayOf(0, 1, 0, -1)
    val dc = intArrayOf(1, 0, -1, 0)
  }

  fun solution(maps: Array<String>): IntArray {
    val R = maps.size
    val C = maps[0].length
    val CAP = C
    val N = R * C

    val ans = IntArray((N + 1) / 2)
    var island = 0
    var rmn = N
    val ch = BooleanArray(N)
    val q = IntArray(N)

    l@ for (r in 0 until R)
      for (c in 0 until C) {
        if (rmn == 0) break@l
        val pos = r * CAP + c
        if (ch[pos]) continue

        ch[pos] = true
        rmn--

        val x = maps[r][c]
        if (x == SEA) continue

        var days = x.code - ZERO
        var qh = 0
        var qt = 0
        q[qt++] = r * SEP + c

        while (qh < qt) {
          val e = q[qh++]
          val r = e / SEP
          val c = e % SEP
          for (i in 0 until 4) {
            val nr = r + dr[i]
            val nc = c + dc[i]
            val nPos = nr * CAP + nc
            if (nr !in 0 until R || nc !in 0 until C || ch[nPos]) continue

            ch[nPos] = true
            rmn--

            val nx = maps[nr][nc]
            if (nx == SEA) continue

            days += nx.code - ZERO
            q[qt++] = nr * SEP + nc
          }
        }

        ans[island++] = days
      }

    if (island == 0) return intArrayOf(-1)

    qs(ans, 0, island - 1)

    return ans.copyOf(island)
  }

  fun swap(
    a: IntArray,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(
    a: IntArray,
    l: Int,
    r: Int,
  ) {
    if (l >= r) return

    var pos = l
    var pl = l
    var pr = r
    val piv = a[(l + r) shr 1]

    while (pos <= pr) {
      val x = a[pos]
      when {
        x < piv -> swap(a, pos++, pl++)
        x > piv -> swap(a, pos, pr--)
        else -> pos++
      }
    }

    qs(a, l, pl - 1)
    qs(a, pr + 1, r)
  }
}

/**
 * ```
 * [ME
 * 테스트 1 〉	통과 (0.01ms, 59.7MB)
 * 테스트 2 〉	통과 (0.02ms, 60MB)
 * 테스트 3 〉	통과 (0.03ms, 60.2MB)
 * 테스트 4 〉	통과 (0.04ms, 60.6MB)
 * 테스트 5 〉	통과 (0.23ms, 59.5MB)
 * 테스트 6 〉	통과 (0.36ms, 61.6MB)
 * 테스트 7 〉	통과 (0.19ms, 59.4MB)
 * 테스트 8 〉	통과 (0.56ms, 60.1MB)
 * 테스트 9 〉	통과 (1.03ms, 60.6MB)
 * 테스트 10 〉	통과 (0.87ms, 59.4MB)
 * ```
 *
 *
 * ```
 * [RIVAL] - DFS ver.
 * class Solution {
 *     var tempF = 0
 *     val dx = listOf(0,0,1,-1)
 *     val dy = listOf(1,-1,0,0)
 *     lateinit var visit : Array<Array<Boolean>>
 *     fun solution(maps: Array<String>): IntArray {
 *         var answer: IntArray = intArrayOf()
 *         val answerList = mutableListOf<Int>()
 *         visit = Array(maps.size) { Array(maps[0].length) { false }}
 *
 *         for (i in 0 until maps.size) {
 *             for (j in 0 until maps[0].length) {
 *                 if (!visit[i][j] && maps[i][j] != 'X') {
 *                     tempF = 0
 *                     dfs(i, j, maps)
 *                     answerList.add(tempF)
 *                 }
 *             }
 *         }
 *         answerList.sort()
 *         if (answerList.isEmpty()) {
 *             return intArrayOf(-1)
 *         } else {
 *             return answerList.toIntArray()
 *         }
 *     }
 *
 *     fun dfs(x: Int, y: Int, maps: Array<String>) {
 *         visit[x][y] = true
 *         tempF += maps[x][y].digitToInt()
 *         for (i in 0 until 4) {
 *             try {
 *                 if (!visit[x + dx[i]][y + dy[i]] && maps[x + dx[i]][y+ dy[i]] != 'X') {
 *                     dfs(x+ dx[i], y+ dy[i], maps)
 *                 }
 *             } catch(e: Exception) { }
 *         }
 *     }
 * }
 * 테스트 1 〉	통과 (0.30ms, 63.5MB)
 * 테스트 2 〉	통과 (2.33ms, 64MB)
 * 테스트 3 〉	통과 (1.77ms, 64.3MB)
 * 테스트 4 〉	통과 (1.72ms, 63.6MB)
 * 테스트 5 〉	통과 (4.24ms, 64.8MB)
 * 테스트 6 〉	통과 (3.78ms, 64.2MB)
 * 테스트 7 〉	통과 (3.27ms, 64.3MB)
 * 테스트 8 〉	통과 (4.55ms, 65.9MB)
 * 테스트 9 〉	통과 (6.08ms, 66MB)
 * 테스트 10 〉	통과 (6.50ms, 64.3MB)
 *
 * [RIVAL 2] BFS ver.
 * import java.util.LinkedList
 *
 * class Solution {
 *
 *     data class IsLand(var x: Int, var y: Int)
 *
 *     fun solution(maps: Array<String>): IntArray {
 *         val xDir = intArrayOf(-1, 0, 1, 0)
 *         val yDir = intArrayOf(0, -1, 0, 1)
 *         val m = maps.size
 *         val n = maps[0].length
 *
 *         val visitDates = mutableListOf<Int>()
 *         val visited = Array(m) { BooleanArray(n) }
 *
 *         for (x in 0 until m) {
 *             for (y in 0 until n) {
 *
 *                 var date = 0
 *
 *                 val queue = LinkedList<IsLand>().apply {
 *                     if(!visited[x][y] && maps[x][y] != 'X') offer(IsLand(x, y))
 *                 }
 *
 *                 while(queue.isNotEmpty()) {
 *                     val poll = queue.poll()
 *
 *                     date += maps[poll.x][poll.y].digitToInt()
 *                     visited[poll.x][poll.y] = true
 *
 *                     for(i in xDir.indices) {
 *                         val nx = poll.x + xDir[i]
 *                         val ny = poll.y + yDir[i]
 *
 *                         if(nx < 0 || nx >= m || ny < 0 || ny >= n || visited[nx][ny] || maps[nx][ny] == 'X') continue
 *
 *                         visited[nx][ny] = true
 *                         queue.offer(IsLand(nx, ny))
 *                     }
 *                 }
 *
 *                 if(date != 0) visitDates.add(date)
 *             }
 *         }
 *
 *         return if(visitDates.isEmpty()) intArrayOf(-1) else visitDates.sorted().toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (0.28ms, 60.4MB)
 * 테스트 2 〉	통과 (4.82ms, 61.1MB)
 * 테스트 3 〉	통과 (4.62ms, 60.1MB)
 * 테스트 4 〉	통과 (11.04ms, 64.3MB)
 * 테스트 5 〉	통과 (11.70ms, 64.2MB)
 * 테스트 6 〉	통과 (16.41ms, 64.2MB)
 * 테스트 7 〉	통과 (11.56ms, 64.7MB)
 * 테스트 8 〉	통과 (12.04ms, 64.3MB)
 * 테스트 9 〉	통과 (18.90ms, 64.4MB)
 * 테스트 10 〉	통과 (13.86ms, 63.9MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf("X591X", "X1X5X", "X231X", "1XXX1")), intArrayOf(1, 1, 27))
  validate(s.solution(arrayOf("XXX", "XXX", "XXX")), intArrayOf(-1))
  validate(s.solution(arrayOf(
    "9XXX6",
    "XX1XX",
    "XXX23")
  ), intArrayOf(1, 5, 6, 9))
}

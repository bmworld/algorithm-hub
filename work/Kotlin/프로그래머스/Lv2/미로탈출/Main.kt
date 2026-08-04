package 프로그래머스.Lv2.미로탈출

import util.validate

class Solution {
  companion object {

    const val S = 'S'
    const val E = 'E'
    const val L = 'L'
    const val O = 'O'
    const val X = 'X'

    const val UNSEEN = 0
    const val EMPTY = -1
    const val IMPOSSIBLE = -1
    val dr = intArrayOf(0, 1, 0, -1)
    val dc = intArrayOf(1, 0, -1, 0)
  }

  fun solution(maps: Array<String>): Int {
    var time = 0
    var stt = EMPTY
    var end = EMPTY

    val R = maps.size
    val C = maps[0].length
    val CAP = C
    val SIZE = R * C

    fun pos(r: Int, c: Int) = r * CAP + c

    l@ for (r in 0 until R) {
      for (c in 0 until C) {
        when (maps[r][c]) {
          S -> {
            stt = pos(r, c)
            if (end != EMPTY) break@l
          }
          E -> {
            end = pos(r, c)
            if (stt != EMPTY) break@l
          }
        }
      }
    }

    // S -> L
    val q = IntArray(SIZE)
    var qh = 0
    var qt = 0
    q[qt++] = stt

    val c1 = IntArray(SIZE)

    bfs@ while (qh < qt) {
      val cur = q[qh++]
      val r = cur / CAP
      val c = cur % CAP

      for (i in 0..3) {
        val nr = r + dr[i]
        val nc = c + dc[i]
        val nxt = pos(nr, nc)
        if (nr in 0 until R && nc in 0 until C && c1[nxt] == UNSEEN) {
          when (maps[nr][nc]) {
            O, E -> {
              c1[nxt] = c1[cur] + 1
              q[qt++] = nxt
            }
            L -> {
              time = c1[cur] + 1
              stt = nxt
              break@bfs
            }
          }
        }
      }
    }


    if (time == UNSEEN) return IMPOSSIBLE

    // L -> E
    qh = 0
    qt = 0
    q[qt++] = stt

    val c2 = IntArray(SIZE)
    bfs@ while (qh < qt) {
      val cur = q[qh++]
      val r = cur / CAP
      val c = cur % CAP

      for (i in 0..3) {
        val nr = r + dr[i]
        val nc = c + dc[i]
        val nxt = pos(nr, nc)
        if (nr in 0 until R && nc in 0 until C && c2[nxt] == UNSEEN) {
          when (maps[nr][nc]) {
            S, O -> {
              c2[nxt] = c2[cur] + 1
              q[qt++] = nxt
            }
            E -> return time + c2[cur] + 1
          }
        }
      }
    }

    return IMPOSSIBLE
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.03ms, 59.5MB)
 * 테스트 2 〉	통과 (0.05ms, 59.5MB)
 * 테스트 3 〉	통과 (0.05ms, 60.5MB)
 * 테스트 4 〉	통과 (0.05ms, 58.6MB)
 * 테스트 5 〉	통과 (0.09ms, 60.3MB)
 * 테스트 6 〉	통과 (0.03ms, 60.7MB)
 * 테스트 7 〉	통과 (0.42ms, 60.1MB)
 * 테스트 8 〉	통과 (0.68ms, 59.4MB)
 * 테스트 9 〉	통과 (0.02ms, 60.2MB)
 * 테스트 10 〉	통과 (0.03ms, 60.1MB)
 * 테스트 11 〉	통과 (0.17ms, 60.5MB)
 * 테스트 12 〉	통과 (1.08ms, 61.3MB)
 * 테스트 13 〉	통과 (1.20ms, 60.7MB)
 * 테스트 14 〉	통과 (0.88ms, 60.8MB)
 * 테스트 15 〉	통과 (0.13ms, 59.6MB)
 * 테스트 16 〉	통과 (1.98ms, 60.4MB)
 * 테스트 17 〉	통과 (2.75ms, 61.2MB)
 * 테스트 18 〉	통과 (0.06ms, 60.5MB)
 * 테스트 19 〉	통과 (0.07ms, 59.8MB)
 * 테스트 20 〉	통과 (1.94ms, 60.9MB)
 * 테스트 21 〉	통과 (0.43ms, 60.1MB)
 * 테스트 22 〉	통과 (0.06ms, 60.6MB)
 * 테스트 23 〉	통과 (0.02ms, 60.5MB)
 * ```
 *
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     private lateinit var map: List<CharArray>
 *     private lateinit var times: Array<IntArray>
 *
 *     fun solution(maps: Array<String>): Int {
 *         map = maps.map { it.toCharArray() } // 'S', 'E', 'L'. 'O', 'X'
 *         val si = map.indexOfFirst { 'S' in it }
 *         val sj = map[si].indexOfFirst { it == 'S' }
 *         val li = map.indexOfFirst { 'L' in it }
 *         val lj = map[li].indexOfFirst { it == 'L' }
 *         val ei = map.indexOfFirst { 'E' in it }
 *         val ej = map[ei].indexOfFirst { it == 'E' }
 *         var time = 0
 *         times = Array(map.size) { IntArray(map[0].size) { 0 } }
 *         routing(false, si, sj, li, lj)
 *         time += times[li][lj]
 *         if (times[li][lj] == 0) return -1
 *         times = Array(map.size) { IntArray(map[0].size) { 0 } }
 *         routing(true, li, lj, ei, ej)
 *         time += times[ei][ej]
 *         if (times[ei][ej] == 0) return -1
 *         return time
 *     }
 *
 *     private fun routing(levered: Boolean, i: Int, j: Int, ei: Int, ej: Int, time: Int = 0) {
 *         if (!(i >= 0 && i < map.size && j >= 0 && j < map[0].size)) return
 *         if (times[i][j] != 0 && time >= times[i][j]) return
 *         val cell = map[i][j]
 *         if (cell == 'X') return
 *         times[i][j] = time
 *         if (levered && cell == 'E' || !levered && cell == 'L') return
 *         routing(levered, i - 1, j, ei, ej, time + 1) // up
 *         routing(levered, i + 1, j, ei, ej, time + 1) // down
 *         routing(levered, i, j - 1, ei, ej, time + 1) // left
 *         routing(levered, i, j + 1, ei, ej, time + 1) // right
 *     }
 * }
 * 테스트 1 〉	통과 (8.82ms, 64.3MB)
 * 테스트 2 〉	통과 (8.87ms, 63.8MB)
 * 테스트 3 〉	통과 (11.34ms, 63.9MB)
 * 테스트 4 〉	통과 (9.12ms, 63.9MB)
 * 테스트 5 〉	통과 (8.79ms, 63.5MB)
 * 테스트 6 〉	통과 (9.10ms, 63.9MB)
 * 테스트 7 〉	통과 (18.16ms, 62.8MB)
 * 테스트 8 〉	통과 (31.45ms, 63.4MB)
 * 테스트 9 〉	통과 (8.93ms, 62.7MB)
 * 테스트 10 〉	통과 (8.77ms, 64.1MB)
 * 테스트 11 〉	통과 (14.41ms, 63.5MB)
 * 테스트 12 〉	통과 (63.85ms, 65.4MB)
 * 테스트 13 〉	통과 (71.00ms, 63MB)
 * 테스트 14 〉	통과 (75.22ms, 65.7MB)
 * 테스트 15 〉	통과 (13.69ms, 63.7MB)
 * 테스트 16 〉	통과 (295.26ms, 65.1MB)
 * 테스트 17 〉	통과 (258.85ms, 66.6MB)
 *
 * [RIVAL 2]
 * import java.util.*
 *
 * class Solution {
 *     lateinit var map: Array<CharArray>
 * lateinit var visited: Array<BooleanArray>
 * var row = 0
 * var col = 0
 *
 * fun solution(maps: Array<String>): Int {
 *     row = maps.size
 *     col = maps[0].length
 *     map = Array(row) { CharArray(col) }
 *     visited = Array(row) { BooleanArray(col) }
 *
 *     var startIdx = Pair(0, 0)
 *     var endIdx = Pair(0, 0)
 *     var leverIdx = Pair(0, 0)
 *     repeat(row) { i ->
 *         map[i] = maps[i].toCharArray()
 *         repeat(col) { j ->
 *             when(map[i][j]) {
 *                 'S' -> startIdx = Pair(i, j)
 *                 'E' -> endIdx = Pair(i, j)
 *                 'L' -> leverIdx = Pair(i, j)
 *             }
 *         }
 *     }
 *
 *     val goLever = bfs(startIdx, leverIdx)
 *     if(goLever == -1) return -1
 *
 *     repeat(row) { visited[it].fill(false) }
 *     val goExit = bfs(leverIdx, endIdx)
 *     if(goExit == -1) return -1
 *
 *     return goLever + goExit
 * }
 *
 * fun bfs(startIdx: Pair<Int, Int>, targetIdx: Pair<Int, Int>): Int {
 *     val dir = arrayOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
 *     val que: Queue<Triple<Int, Int, Int>> = LinkedList()
 *     que.add(Triple(startIdx.first, startIdx.second, 0))
 *     visited[startIdx.first][startIdx.second] = true
 *
 *     while(que.isNotEmpty()) {
 *         val cur = que.poll()
 *         val curY = cur.first
 *         val curX = cur.second
 *         val curMove = cur.third
 *
 *         if(curY == targetIdx.first && curX == targetIdx.second) return curMove
 *
 *         for(i in 0 until 4) {
 *             val nextY = curY + dir[i].first
 *             val nextX = curX + dir[i].second
 *
 *             if(!isCheck(nextY, nextX)) continue
 *
 *             visited[nextY][nextX] = true
 *             que.add(Triple(nextY, nextX, curMove + 1))
 *         }
 *     }
 *
 *     return -1
 * }
 *
 * fun isCheck(y: Int, x: Int): Boolean
 * = y in 0 until row && x in 0 until col && !visited[y][x] && map[y][x] != 'X'
 * }
 * 테스트 1 〉	통과 (6.96ms, 63.8MB)
 * 테스트 2 〉	통과 (9.60ms, 63.1MB)
 * 테스트 3 〉	통과 (8.02ms, 63.8MB)
 * 테스트 4 〉	통과 (7.27ms, 63.9MB)
 * 테스트 5 〉	통과 (9.69ms, 62.9MB)
 * 테스트 6 〉	통과 (7.33ms, 62.4MB)
 * 테스트 7 〉	통과 (8.61ms, 63.5MB)
 * 테스트 8 〉	통과 (9.74ms, 63.2MB)
 * 테스트 9 〉	통과 (6.98ms, 63.3MB)
 * 테스트 10 〉	통과 (0.94ms, 59.9MB)
 * 테스트 11 〉	통과 (8.09ms, 63.4MB)
 * 테스트 12 〉	통과 (9.98ms, 63.6MB)
 * 테스트 13 〉	통과 (9.85ms, 64.1MB)
 * 테스트 14 〉	통과 (8.95ms, 63.1MB)
 * 테스트 15 〉	통과 (8.95ms, 63MB)
 * 테스트 16 〉	통과 (11.65ms, 63.4MB)
 * 테스트 17 〉	통과 (16.20ms, 62.5MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf("SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE")), 16)
  validate(s.solution(arrayOf("LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO")), -1)
  validate(s.solution(arrayOf(
    "LXXXXS",
    "OOOOOO",
    "OOOOOO",
    "OOOOOO",
    "EOOOOO"
  )), 11)

  validate(s.solution(arrayOf(
    "XXXXXX",
    "OOESLO",
    "OOOOOO",
    "OOOOOO",
    "OOOOOO"
  )), 3)

  validate(s.solution(arrayOf(
    "XXXXXX",
    "OOXSLO",
    "OOOOOO",
    "XOOOOO",
    "EXOOOO"
  )), -1)

  validate(s.solution(arrayOf(
    "XXXXXL",
    "OOXSXX",
    "OOOOOO",
    "XOOOOO",
    "OEOOOO"
  )), -1)

  validate(s.solution(arrayOf(
    "XXX",
    "OOX",
    "OSO",
    "XLO",
    "OEO"
  )), 2)

}

//    println("[path1] [$R, $C] $stt~$end -> t=$time")
//         println("[$i] ${r}, $c -> $nr, $nc")
//     println("[path2] [$R, $C] $stt~$end -> t=$time")

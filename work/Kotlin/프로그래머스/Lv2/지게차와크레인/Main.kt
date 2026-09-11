package 프로그래머스.Lv2.지게차와크레인

import util.validate

class Solution {
  companion object {

    const val EMPTY = -1
    const val A = 65
    const val ALPHABETS = 26
    val dr = intArrayOf(0, 1, 0, -1)
    val dc = intArrayOf(1, 0, -1, 0)
  }

  fun solution(storage: Array<String>, req: Array<String>): Int {
    val R = storage.size
    val C = storage[0].length
    fun pos(r: Int, c: Int): Int = r * C + c

    val N = R * C
    val strg = IntArray(N)
    val rchbl = BooleanArray(N)
    val alpbt = Array(ALPHABETS) { mutableSetOf<Int>() }

    repeat(R) { r ->
      repeat(C) { c ->
        val char = storage[r][c].code - A
        val pos = pos(r, c)
        strg[pos] = char
        alpbt[char] += pos
        if (r == 0 || r == R - 1 || c == 0 || c == C - 1) rchbl[pos] = true
      }
    }

    var ans = N
    var q = IntArray(N)

    for (x in req) {
      val char = x[0].code - A

      val cnds =
        if (x.length > 1) alpbt[char]
        else alpbt[char].filter { pos -> rchbl[pos] }

      var qh = 0
      var qt = 0
      for (pos in cnds) {
        strg[pos] = EMPTY
        if (rchbl[pos]) q[qt++] = pos
      }

      ans -= cnds.size
      alpbt[char].removeAll(cnds)

      bfs@ while (qh < qt) {
        val e = q[qh++]
        val r = e / C
        val c = e % C
        repeat(4) {
          val nr = r + dr[it]
          val nc = c + dc[it]
          if (nr !in 0 until R || nc !in 0 until C) return@repeat

          val pos = pos(nr, nc)
          if (rchbl[pos]) return@repeat
          rchbl[pos] = true

          val char = strg[pos]
          if (char == EMPTY) q[qt++] = pos
          else alpbt[char] += pos
        }
      }
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.62ms, 58.5MB)
 * 테스트 2 〉	통과 (1.55ms, 59.8MB)
 * 테스트 3 〉	통과 (0.48ms, 60.5MB)
 * 테스트 4 〉	통과 (2.28ms, 60.6MB)
 * 테스트 5 〉	통과 (0.28ms, 60.7MB)
 * 테스트 6 〉	통과 (0.76ms, 61.2MB)
 * 테스트 7 〉	통과 (0.22ms, 60.6MB)
 * 테스트 8 〉	통과 (1.74ms, 60.4MB)
 * 테스트 9 〉	통과 (0.49ms, 60.2MB)
 * 테스트 10 〉	통과 (1.88ms, 60.8MB)
 * 테스트 11 〉	통과 (1.70ms, 60.8MB)
 * 테스트 12 〉	통과 (2.20ms, 61.6MB)
 * 테스트 13 〉	통과 (0.38ms, 60.3MB)
 * 테스트 14 〉	통과 (0.42ms, 60.5MB)
 * 테스트 15 〉	통과 (0.74ms, 60.5MB)
 * 테스트 16 〉	통과 (0.43ms, 59.9MB)
 * 테스트 17 〉	통과 (0.73ms, 60.2MB)
 * 테스트 18 〉	통과 (3.82ms, 59.2MB)
 * 테스트 19 〉	통과 (1.51ms, 61.1MB)
 * 테스트 20 〉	통과 (1.50ms, 60.2MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(storage: Array<String>, requests: Array<String>): Int {
 *         val n = storage.size
 *         val m = storage[0].length
 *         val board = Array(n+2){ CharArray(m+2) {'.'} }
 *
 *         var answer = n * m
 *
 *         for (i in 0 until n) {
 *             for (j in 0 until m) {
 *                 board[i+1][j+1] = storage[i][j]
 *             }
 *         }
 *
 *         for (request in requests) {
 *             val target = request[0]
 *             if (request.length == 2) {
 *                 for (i in 1 until n+1) {
 *                     for (j in 1 until m+1) {
 *                         if (board[i][j] == target) {
 *                             board[i][j] = '.'
 *                             answer--
 *                         }
 *                     }
 *                 }
 *             } else {
 *                 val removeList = find(board, target, n, m)
 *                 for ((x, y) in removeList) {
 *                     if (board[x][y] == target){
 *                         board[x][y] = '.'
 *                         answer--
 *                     }
 *                 }
 *
 *             }
 *         }
 *         return answer
 *     }
 *
 *     private fun find(board: Array<CharArray>, target: Char, n: Int, m: Int) : MutableList<Pair<Int, Int>> {
 *         val removeList = mutableListOf<Pair<Int, Int>>()
 *
 *         val visited = Array(n+2) { BooleanArray(m+2) }
 *
 *         val queue = mutableListOf<Pair<Int, Int>>()
 *         var head = 0
 *
 *         val dx = intArrayOf(-1, 1, 0, 0)
 *         val dy = intArrayOf(0, 0, -1, 1)
 *
 *         queue.add(0 to 0)
 *         visited[0][0] = true
 *
 *         while (head < queue.size) {
 *             val current = queue[head]
 *             head++
 *
 *             val x = current.first
 *             val y = current.second
 *
 *             for (dir in 0 until 4) {
 *                 val nx = x + dx[dir]
 *                 val ny = y + dy[dir]
 *
 *                 if (nx < 0 || ny < 0 || nx >= n+2 || ny >= m+2) {
 *                     continue
 *                 }
 *
 *                 if (visited[nx][ny]) {
 *                     continue
 *                 }
 *
 *                 if (board[nx][ny] == '.') {
 *                     visited[nx][ny] = true
 *                     queue.add(nx to ny)
 *                 } else if (board[nx][ny] == target) {
 *                     visited[nx][ny] = true
 *                     removeList.add(nx to ny)
 *                 }
 *             }
 *         }
 *
 *         return removeList
 *     }
 * }
 * 테스트 1 〉	통과 (2.70ms, 60.2MB)
 * 테스트 2 〉	통과 (9.95ms, 62MB)
 * 테스트 3 〉	통과 (3.68ms, 59.6MB)
 * 테스트 4 〉	통과 (12.85ms, 63.4MB)
 * 테스트 5 〉	통과 (0.04ms, 60.5MB)
 * 테스트 6 〉	통과 (0.11ms, 60.4MB)
 * 테스트 7 〉	통과 (0.04ms, 59.2MB)
 * 테스트 8 〉	통과 (0.41ms, 60.3MB)
 * 테스트 9 〉	통과 (1.01ms, 60.6MB)
 * 테스트 10 〉	통과 (3.90ms, 61.1MB)
 * 테스트 11 〉	통과 (3.49ms, 58.9MB)
 * 테스트 12 〉	통과 (4.33ms, 60.5MB)
 * 테스트 13 〉	통과 (0.86ms, 61.1MB)
 * 테스트 14 〉	통과 (1.67ms, 60.9MB)
 * 테스트 15 〉	통과 (3.80ms, 61.2MB)
 * 테스트 16 〉	통과 (1.92ms, 60.7MB)
 * 테스트 17 〉	통과 (3.06ms, 60.9MB)
 * 테스트 18 〉	통과 (8.61ms, 63.4MB)
 * 테스트 19 〉	통과 (8.90ms, 61MB)
 * 테스트 20 〉	통과 (6.88ms, 62.5MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(storage: Array<String>, requests: Array<String>): Int {
 *         val n = storage.size
 *         val m = storage[0].length
 *
 *         val graph = Array(n + 2) { CharArray(m + 2) { '0' } }
 *         for (i in 0 until n) {
 *             for (j in 0 until m) {
 *                 graph[i + 1][j + 1] = storage[i][j]
 *             }
 *         }
 *
 *         fun bfs(target: Char): Int {
 *             var ret = 0
 *             val dx = intArrayOf(0, 0, 1, -1)
 *             val dy = intArrayOf(1, -1, 0, 0)
 *             val q = ArrayDeque<Pair<Int, Int>>()
 *             val visited = Array(n + 2) { BooleanArray(m + 2) }
 *
 *             q.addLast(Pair(0, 0))
 *             visited[0][0] = true
 *
 *             while(q.isNotEmpty()) {
 *                 val (cx, cy) = q.removeFirst()
 *                 for(i in 0..3) {
 *                     val nx = cx + dx[i]
 *                     val ny = cy + dy[i]
 *
 *                     if(nx !in 0..n + 1 || ny !in 0..m + 1) continue
 *                     if(visited[nx][ny]) continue
 *
 *                     visited[nx][ny] = true
 *
 *                     if(graph[nx][ny] == '0') {
 *                         q.addLast(Pair(nx, ny))
 *                     } else if(graph[nx][ny] == target) {
 *                         graph[nx][ny] = '0'
 *                         ret++
 *                     }
 *                 }
 *             }
 *             return ret
 *         }
 *
 *         fun checkAll(target: Char): Int {
 *             var ret = 0
 *             for(i in 1..n) {
 *                 for(j in 1..m) {
 *                     if(graph[i][j] == target) {
 *                         ret++
 *                         graph[i][j] = '0'
 *                     }
 *                 }
 *             }
 *             return ret
 *         }
 *
 *         var answer = 0
 *         for(req in requests) {
 *             if(req.length == 2) answer += checkAll(req[0])
 *             else answer += bfs(req[0])
 *         }
 *
 *         return n * m - answer
 *     }
 * }
 * 테스트 1 〉	통과 (14.35ms, 63.6MB)
 * 테스트 2 〉	통과 (21.30ms, 66.4MB)
 * 테스트 3 〉	통과 (13.81ms, 63.4MB)
 * 테스트 4 〉	통과 (21.46ms, 65.5MB)
 * 테스트 5 〉	통과 (0.03ms, 59.9MB)
 * 테스트 6 〉	통과 (0.18ms, 60.8MB)
 * 테스트 7 〉	통과 (0.03ms, 59.4MB)
 * 테스트 8 〉	통과 (0.45ms, 59.5MB)
 * 테스트 9 〉	통과 (11.91ms, 63.7MB)
 * 테스트 10 〉	통과 (15.33ms, 63MB)
 * 테스트 11 〉	통과 (14.73ms, 64.1MB)
 * 테스트 12 〉	통과 (14.80ms, 64.4MB)
 * 테스트 13 〉	통과 (11.43ms, 63.7MB)
 * 테스트 14 〉	통과 (15.75ms, 63.5MB)
 * 테스트 15 〉	통과 (17.48ms, 64.1MB)
 * 테스트 16 〉	통과 (13.18ms, 63MB)
 * 테스트 17 〉	통과 (15.64ms, 63.2MB)
 * 테스트 18 〉	통과 (19.91ms, 64.9MB)
 * 테스트 19 〉	통과 (20.49ms, 65MB)
 * 테스트 20 〉	통과 (18.84ms, 64.9MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf("AZWQY", "CAABX", "BBDDA", "ACACA"),
    arrayOf("A", "BB", "A")
  ),
    11
  )

  validate(s.solution(
    arrayOf("HAH", "HBH", "HHH", "HAH", "HBH"),
    arrayOf("C", "B", "B", "B", "B", "H")
  ),
    4
  )
}

//      println("[$x] cnds = $cnds -> $ans")
//        println("[Q] $r, $c")

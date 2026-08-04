package 프로그래머스.Lv2.미로탈출

import util.validate

class Solution {
  companion object {

    const val S = 'S'
    const val E = 'E'
    const val L = 'L'
    const val O = 'O'
    const val X = 'X'

    const val EMPTY = -1
    const val UNSEEN = 0
    const val IMPOSSIBLE = -1
    val dr = intArrayOf(0, 1, 0, -1)
    val dc = intArrayOf(1, 0, -1, 0)
  }

  fun solution(maps: Array<String>): Int {
    var time = 0
    var stt = EMPTY
    var end = EMPTY

    val N = maps.size
    val cap = N

    fun pos(r: Int, c: Int) = r * cap + c
    l@ for (r in 0 until N) {
      for (c in 0 until N) {
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
    val p1 = IntArray(N * N)
    val q = IntArray(N * N)
    var qh = 0
    var qt = 0
    q[qt++] = stt


    while (qh < qt) {
      val x = q[qh++]
      val r = x / cap
      val c = x % cap
      for (i in 0..3) {
        val nr = r + dr[i]
        val nc = c + dc[i]
        val nxt = pos(nr, nc)
        if (nr in 0 until N &&
          nc in 0 until N &&
          p1[nxt] == UNSEEN
        ) {

          val t = p1[pos(r, c)]
          when (maps[nr][nc]) {
            O, E -> {
              p1[nxt] = t + 1
              q[qt++] = nxt
            }
            L -> {
              time = t + 1
              stt = nxt
              break
            }
          }
        }
      }
    }


    if (time == UNSEEN) return IMPOSSIBLE

    // L -> E
    val p2 = IntArray(N * N)
    qh = 0
    qt = 0
    q[qt++] = stt

    while (qh < qt) {
      val x = q[qh++]
      val r = x / cap
      val c = x % cap
      for (i in 0..3) {
        val nr = r + dr[i]
        val nc = c + dc[i]
        val nxt = pos(nr, nc)
        if (nr in 0 until N &&
          nc in 0 until N &&
          p2[nxt] == UNSEEN
        ) {

          val t = p2[pos(r, c)]
          when (maps[nr][nc]) {
            S, O -> {
              p2[nxt] = t + 1
              q[qt++] = nxt
            }
            E -> return time + t + 1
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
 * 테스트 1 〉	통과 (0.03ms, 59.4MB)
 * 테스트 2 〉	실패 (0.03ms, 59.8MB)
 * 테스트 3 〉	실패 (0.05ms, 60.4MB)
 * 테스트 4 〉	통과 (0.07ms, 59.9MB)
 * 테스트 5 〉	통과 (0.07ms, 60.1MB)
 * 테스트 6 〉	통과 (0.04ms, 59.4MB)
 * 테스트 7 〉	통과 (0.55ms, 60.6MB)
 * 테스트 8 〉	실패 (0.47ms, 59.8MB)
 * 테스트 9 〉	실패 (0.02ms, 60.1MB)
 * 테스트 10 〉	실패 (런타임 에러)
 * 테스트 11 〉	실패 (0.39ms, 60MB)
 * 테스트 12 〉	통과 (1.53ms, 60.7MB)
 * 테스트 13 〉	실패 (런타임 에러)
 * 테스트 14 〉	실패 (1.33ms, 58.8MB)
 * 테스트 15 〉	실패 (런타임 에러)
 * 테스트 16 〉	실패 (2.64ms, 61.4MB)
 * 테스트 17 〉	실패 (4.00ms, 58MB)
 * 테스트 18 〉	실패 (런타임 에러)
 * 테스트 19 〉	통과 (0.08ms, 59.7MB)
 * 테스트 20 〉	실패 (런타임 에러)
 * 테스트 21 〉	통과 (0.55ms, 60.5MB)
 * 테스트 22 〉	실패 (0.04ms, 60MB)
 * 테스트 23 〉	통과 (0.02ms, 60.5MB)
 * ```
 *
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf("SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE")), 16)
  validate(s.solution(arrayOf("LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO")), -1)

}

//    println("[path1] ${stt}~$end -> ans=$time")
//     println("[path2] ${stt}~$end -> ans=$time")

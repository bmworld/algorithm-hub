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

    val R = maps.size
    val C = maps[0].length
    val CAP = R

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
    val SIZE = R * C
    val p1 = IntArray(SIZE)
    val q = IntArray(SIZE)
    var qh = 0
    var qt = 0
    q[qt++] = stt


    while (qh < qt) {
      val cur = q[qh++]
      val r = cur / CAP
      val c = cur % CAP
      for (i in 0..3) {
        val nr = r + dr[i]
        val nc = c + dc[i]
        val nxt = pos(nr, nc)
        if (nr in 0 until R &&
          nc in 0 until C &&
          p1[nxt] == UNSEEN
        ) when (maps[nr][nc]) {
          O, E -> {
            p1[nxt] = p1[pos(r, c)] + 1
            q[qt++] = nxt
          }
          L -> {
            time = p1[pos(r, c)] + 1
            stt = nxt
            break
          }
        }
      }
    }


    if (time == UNSEEN) return IMPOSSIBLE

    // L -> E
    val p2 = IntArray(SIZE)
    qh = 0
    qt = 0
    q[qt++] = stt

    while (qh < qt) {
      val cur = q[qh++]
      val r = cur / CAP
      val c = cur % CAP
      for (i in 0..3) {
        val nr = r + dr[i]
        val nc = c + dc[i]
        val nxt = pos(nr, nc)
        if (nr in 0 until R &&
          nc in 0 until C &&
          p2[nxt] == UNSEEN
        ) when (maps[nr][nc]) {
          S, O -> {
            p2[nxt] = p2[pos(r, c)] + 1
            q[qt++] = nxt
          }
          E -> return time + p2[pos(r, c)] + 1
        }
      }
    }

    return IMPOSSIBLE
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.4MB)
 * 테스트 2 〉	실패 (0.05ms, 60.3MB)
 * 테스트 3 〉	실패 (0.05ms, 59.8MB)
 * 테스트 4 〉	통과 (0.05ms, 58.7MB)
 * 테스트 5 〉	통과 (0.07ms, 60MB)
 * 테스트 6 〉	통과 (0.04ms, 60.8MB)
 * 테스트 7 〉	통과 (0.46ms, 60.3MB)
 * 테스트 8 〉	실패 (0.58ms, 60.2MB)
 * 테스트 9 〉	실패 (0.02ms, 59.9MB)
 * 테스트 10 〉	실패 (런타임 에러)
 * 테스트 11 〉	실패 (0.34ms, 59.8MB)
 * 테스트 12 〉	통과 (1.55ms, 60MB)
 * 테스트 13 〉	실패 (런타임 에러)
 * 테스트 14 〉	실패 (1.19ms, 61MB)
 * 테스트 15 〉	실패 (런타임 에러)
 * 테스트 16 〉	실패 (2.55ms, 60MB)
 * 테스트 17 〉	실패 (3.64ms, 60.8MB)
 * 테스트 18 〉	통과 (0.02ms, 58.8MB)
 * 테스트 19 〉	통과 (0.08ms, 60.8MB)
 * 테스트 20 〉	통과 (0.11ms, 60.2MB)
 * 테스트 21 〉	통과 (0.48ms, 60.9MB)
 * 테스트 22 〉	실패 (0.03ms, 59.5MB)
 * 테스트 23 〉	통과 (0.03ms, 59.7MB)
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

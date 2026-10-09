package 프로그래머스.Lv2._2025_카카오_하반기_1차.바이러스파이프

import util.validate
import java.util.HashMap

class Solution {
  companion object {

    const val SEP = 10
    const val A = 0
    const val B = 1
    const val C = 2
    const val TYPE_SIZE = 3
    const val BIN_LEN = 32
    const val DEF_INFECTED = 1
  }

  fun solution(n: Int, virus: Int, edges: Array<IntArray>, k: Int): Int {
    val used = BooleanArray(n + 1)
    val g = Array(n + 1) { mutableListOf<Int>() }
    for (e in edges) {
      val n1 = e[0]
      val n2 = e[1]
      val type = e[2] - 1
      g[n1] += n2 * SEP + type
      g[n2] += n1 * SEP + type
    }

    val map = HashMap<Int, Int>()
    val q = Array(n + 1) { Pair(0, 0) }
    var qh = 0
    var qt = 0
    q[qt++] = Pair(0, virus)
    used[virus] = true

    while (qh < qt) {
      val p = q[qh++]
      val path = p.first
      val n1 = p.second

      val pos = BIN_LEN - path.countLeadingZeroBits()
      val dep = (pos + TYPE_SIZE - 1) / TYPE_SIZE
      val t1 = pos - 1 - (dep - 1) * TYPE_SIZE

      for (e in g[n1]) {
        val n2 = e / SEP
        if (used[n2]) continue
        used[n2] = true
        val t2 = e % SEP
        val nxtPath = if (t1 == t2 && dep > 0) path else path or (1 shl (dep * TYPE_SIZE + t2))
        q[qt++] = Pair(nxtPath, n2)
        map[nxtPath] = map.getOrDefault(nxtPath, 0) + 1
      }
    }

    var ans = DEF_INFECTED
    var size = 3
    repeat(k - 1) { size *= 2 }
    val usedPath = HashSet<Int>()

    fun dfs(type: Int, times: Int, path: Int) {
      if (times == k) {
        if (path in usedPath) return
        usedPath.add(path)

        var infected = DEF_INFECTED
        ch@ for (e in map) {
          val p1 = e.key
          var p2 = path
          while (p2 > 0) {
            if (p1 and p2 == p1) {
              infected += e.value
              continue@ch
            }
            p2 = p2 shr TYPE_SIZE
          }
        }

        if (infected > ans) ans = infected
        return
      }

      val nxt = path or (1 shl (times * TYPE_SIZE + type))
      when (type) {
        A -> {
          dfs(B, times + 1, nxt)
          dfs(C, times + 1, nxt)
        }
        B -> {
          dfs(A, times + 1, nxt)
          dfs(C, times + 1, nxt)
        }
        C -> {
          dfs(A, times + 1, nxt)
          dfs(B, times + 1, nxt)
        }
      }
    }

    for (type in 0..2) dfs(type, 0, 0)

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.40ms, 60.3MB)
 * 테스트 2 〉	통과 (0.64ms, 59.6MB)
 * 테스트 3 〉	통과 (1.04ms, 60.2MB)
 * 테스트 4 〉	통과 (0.56ms, 59MB)
 * 테스트 5 〉	통과 (1.34ms, 60.4MB)
 * 테스트 6 〉	실패 (1.06ms, 60MB)
 * 테스트 7 〉	통과 (0.96ms, 60.8MB)
 * 테스트 8 〉	실패 (1.27ms, 59.6MB)
 * 테스트 9 〉	통과 (0.68ms, 59.7MB)
 * 테스트 10 〉	통과 (0.94ms, 59.6MB)
 * 테스트 11 〉	통과 (0.91ms, 60.4MB)
 * 테스트 12 〉	통과 (1.27ms, 60.1MB)
 * 테스트 13 〉	통과 (0.86ms, 60.5MB)
 * 테스트 14 〉	실패 (1.01ms, 60.1MB)
 * 테스트 15 〉	실패 (0.97ms, 60.4MB)
 * 테스트 16 〉	실패 (1.35ms, 60.3MB)
 * 테스트 17 〉	통과 (1.07ms, 61MB)
 * 테스트 18 〉	실패 (1.10ms, 61MB)
 * 테스트 19 〉	통과 (0.75ms, 61MB)
 * 테스트 20 〉	통과 (0.81ms, 59.8MB)
 * 테스트 21 〉	실패 (0.93ms, 59.3MB)
 * 테스트 22 〉	통과 (0.52ms, 60MB)
 * 테스트 23 〉	실패 (1.23ms, 59.9MB)
 * 테스트 24 〉	실패 (1.24ms, 60.5MB)
 * 테스트 25 〉	실패 (1.08ms, 60.6MB)
 * 테스트 26 〉	통과 (1.28ms, 59.6MB)
 * 테스트 27 〉	실패 (1.25ms, 59.7MB)
 * 테스트 28 〉	통과 (0.75ms, 59.4MB)
 * 테스트 29 〉	실패 (1.21ms, 59.9MB)
 * 테스트 30 〉	통과 (0.56ms, 60.4MB)
 * 테스트 31 〉	실패 (1.12ms, 59.9MB)
 * 테스트 32 〉	실패 (1.06ms, 59.5MB)
 * 테스트 33 〉	실패 (1.12ms, 61.2MB)
 * 테스트 34 〉	실패 (1.22ms, 59.9MB)
 * 테스트 35 〉	실패 (1.15ms, 59.5MB)
 * 테스트 36 〉	통과 (0.93ms, 59MB)
 * 테스트 37 〉	통과 (1.01ms, 61.1MB)
 * 테스트 38 〉	실패 (0.93ms, 60.7MB)
 * 테스트 39 〉	통과 (0.73ms, 60.6MB)
 * 테스트 40 〉	통과 (0.88ms, 60.2MB)
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    10,
    1,
    arrayOf(
      intArrayOf(1, 2, 1),
      intArrayOf(1, 3, 1),
      intArrayOf(1, 4, 3),
      intArrayOf(1, 5, 2),
      intArrayOf(5, 6, 1),
      intArrayOf(5, 7, 1),
      intArrayOf(2, 8, 3),
      intArrayOf(2, 9, 2),
      intArrayOf(9, 10, 1),
    ),
    2
  ), 6)

//  validate(s.solution(
//    7,
//    6,
//    arrayOf(
//      intArrayOf(1, 2, 3),
//      intArrayOf(1, 4, 3),
//      intArrayOf(4, 5, 1),
//      intArrayOf(5, 6, 1),
//      intArrayOf(3, 6, 2),
//      intArrayOf(3, 7, 2),
//    ),
//    3
//  ), 7)
}

//    for (x in map) println("---- x[${x.key.toString(2)}]= ${x.value}")
//              println("[INFECTED] p1= ${p1.toString(2)} and p2=${p2.toString(2)}")

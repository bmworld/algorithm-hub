package 프로그래머스.알고리즘고득점Kit.완전탐색.전력망을둘로나누기

import util.validate

class Solution {

  fun solution(n: Int, wires: Array<IntArray>): Int {

    var maxCnt = -1
    var target = -1

    val graph = Array(n + 1) { mutableListOf<Int>() }

    for (wire in wires) {
      val a = wire[0]
      val b = wire[1]

      val aa = graph[a]
      aa += b
      val bb = graph[b]
      bb += a

      val aLen = aa.size
      if (aLen > maxCnt) {
        maxCnt = aLen
        target = a
      }

      val bLen = bb.size
      if (bLen > maxCnt) {
        maxCnt = bLen
        target = b
      }
    }

    var ans: Int = n
    val cnds = graph[target]

    val q = IntArray(n)

    l@ for (cnd in cnds) {
      var cnt = 1
      val used = BooleanArray(n + 1)
      var qh = 0
      var qt = 0
      q[qt++] = cnd
      used[cnd] = true
      used[target] = true

      while (qh < qt) {
        val parent = q[qh++]
        for (child in graph[parent]) {
          if (child == target && parent != cnd) break@l
          if (used[child]) continue
          used[child] = true
          q[qt++] = child
          cnt++
        }
      }

      ans = minOf(ans, abs(n - 2 * cnt))
    }


    return ans
  }

  fun abs(x: Int): Int = if (x < 0) -x else x
}

/**
 * ```
 * ME: v1
 * 테스트 1 〉	통과 (0.14ms, 59.4MB)
 * 테스트 2 〉	실패 (0.14ms, 60.4MB)
 * 테스트 3 〉	통과 (0.13ms, 60.3MB)
 * 테스트 4 〉	통과 (0.15ms, 60MB)
 * 테스트 5 〉	통과 (0.20ms, 59.9MB)
 * 테스트 6 〉	통과 (0.05ms, 58.6MB)
 * 테스트 7 〉	통과 (0.05ms, 59MB)
 * 테스트 8 〉	실패 (0.07ms, 59.2MB)
 * 테스트 9 〉	실패 (0.06ms, 59.1MB)
 * 테스트 10 〉	실패 (0.19ms, 59.1MB)
 * 테스트 11 〉	실패 (0.23ms, 59.7MB)
 * 테스트 12 〉	실패 (0.13ms, 59.3MB)
 * 테스트 13 〉	실패 (0.19ms, 60MB)
 * ```
 *
 *
 * ```
 * RIVAL:
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(9,
    arrayOf(
      intArrayOf(1, 3),
      intArrayOf(2, 3),
      intArrayOf(3, 4),
      intArrayOf(4, 5),
      intArrayOf(4, 6),
      intArrayOf(4, 7),
      intArrayOf(7, 8),
      intArrayOf(7, 9),
    )
  ), 3)

  validate(s.solution(4,
    arrayOf(
      intArrayOf(1, 2),
      intArrayOf(2, 3),
      intArrayOf(3, 4),
    )
  ), 0)

  validate(s.solution(9,
    arrayOf(
      intArrayOf(1, 2),
      intArrayOf(2, 6),
      intArrayOf(3, 7),
      intArrayOf(3, 4),
      intArrayOf(4, 5),
      intArrayOf(6, 7),
    )
  ), 1)
}

//println("[$cnd] $cnt -> diff = $diff")
//    println("--- target = $target ($maxCnt)")

package 프로그래머스.Lv2.배달

import util.validate
import java.util.*

class Solution {
  companion object {

    const val DEF = 1
    const val START = 1
    const val SEP = 100
  }

  fun solution(N: Int, road: Array<IntArray>, k: Int): Int {

    val g = Array(N + 1) { mutableListOf<Int>() }
    for (arr in road) {
      val fr = arr[0]
      val to = arr[1]
      val t = arr[2]
      g[fr] += t * SEP + to
      g[to] += t * SEP + fr
    }

    var ans = DEF
    val q = PriorityQueue<Int>()
    val ch = BooleanArray(N + 1)

    q.add(START)
    ch[START] = true

    while (q.isNotEmpty()) {
      val e = q.poll()
      val t1 = e / SEP
      val fr = e % SEP

      if (fr != START) ans++

      for (e2 in g[fr]) {
        val t2 = e2 / SEP
        val to = e2 % SEP
        val acc = t1 + t2

        if (!ch[to] && acc <= k) {
          ch[to] = true
          q.add(acc * SEP + to)
        }
      }
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.28ms, 61.2MB)
 * 테스트 2 〉	통과 (0.37ms, 59.1MB)
 * 테스트 3 〉	통과 (0.32ms, 61.2MB)
 * 테스트 4 〉	통과 (0.33ms, 61.1MB)
 * 테스트 5 〉	통과 (0.26ms, 61MB)
 * 테스트 6 〉	통과 (0.39ms, 59.3MB)
 * 테스트 7 〉	통과 (0.30ms, 58.9MB)
 * 테스트 8 〉	통과 (0.26ms, 57.6MB)
 * 테스트 9 〉	통과 (0.30ms, 58.2MB)
 * 테스트 10 〉	통과 (0.27ms, 61.6MB)
 * 테스트 11 〉	통과 (0.30ms, 60.9MB)
 * 테스트 12 〉	통과 (0.38ms, 59.3MB)
 * 테스트 13 〉	통과 (0.43ms, 60.1MB)
 * 테스트 14 〉	통과 (0.67ms, 61.1MB)
 * 테스트 15 〉	실패 (1.34ms, 60.4MB)
 * 테스트 16 〉	통과 (0.27ms, 60.5MB)
 * 테스트 17 〉	통과 (0.29ms, 60.6MB)
 * 테스트 18 〉	통과 (0.62ms, 60.3MB)
 * 테스트 19 〉	통과 (0.79ms, 60.9MB)
 * 테스트 20 〉	실패 (0.40ms, 61.2MB)
 * 테스트 21 〉	실패 (1.24ms, 61.9MB)
 * 테스트 22 〉	실패 (0.63ms, 60.5MB)
 * 테스트 23 〉	실패 (0.88ms, 61.5MB)
 * 테스트 24 〉	실패 (0.85ms, 61.7MB)
 * 테스트 25 〉	통과 (1.26ms, 61.3MB)
 * 테스트 26 〉	실패 (1.28ms, 61MB)
 * 테스트 27 〉	실패 (1.15ms, 60.9MB)
 * 테스트 28 〉	실패 (1.27ms, 59.6MB)
 * 테스트 29 〉	실패 (1.11ms, 62MB)
 * 테스트 30 〉	통과 (1.01ms, 62.1MB)
 * 테스트 31 〉	통과 (0.43ms, 60.6MB)
 * 테스트 32 〉	통과 (0.37ms, 61.2MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(5,
    arrayOf(
      intArrayOf(1, 2, 1),
      intArrayOf(2, 3, 3),
      intArrayOf(5, 2, 2),
      intArrayOf(1, 4, 2),
      intArrayOf(5, 3, 1),
      intArrayOf(5, 4, 2),
    ), 3), 4)

  validate(s.solution(6,
    arrayOf(
      intArrayOf(1, 2, 1),
      intArrayOf(1, 3, 2),
      intArrayOf(2, 3, 2),
      intArrayOf(3, 4, 3),
      intArrayOf(3, 5, 2),
      intArrayOf(3, 5, 3),
      intArrayOf(5, 6, 1),
    ), 4), 4)
}

//      println("[$fr] t= $t1")

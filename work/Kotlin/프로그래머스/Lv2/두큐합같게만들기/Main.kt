package 프로그래머스.Lv2.두큐합같게만들기

import util.validate

class Solution {
  companion object {

    const val INF = Int.MAX_VALUE
  }

  fun solution(q1: IntArray, q2: IntArray): Int {

    val s1 = sumOfArr(q1)
    var s2 = sumOfArr(q2)
    val total = s1 + s2
    if (total % 2 != 0L) return -1

    var ans = INF
    val half = total / 2

    val len = q1.size
    val q1End = len - 1

    var sum = 0
    var l = 0
    var r = -1

    fun add() {
      r++
      sum += if (r < len) q1[r] else if (r < 2 * len) q2[r - len] else 0
    }

    fun subt() {
      sum -= if (l < len) q1[l] else q2[l - len]
      l++
    }

    while (r < 2 * len) {
      when {
        sum < half -> add()
        sum > half -> subt()
        else -> {
          val cnt = if (q1End in l..r) {
            l + if (r > q1End) r - q1End else 0
          } else {
            val fr = l - if (l >= len) len else 0
            val to = r - if (r >= len) len else 0
            (to + 1) + fr + len
          }
          if (cnt < ans) ans = cnt

          add()
          subt()
        }
      }
    }

    return if (ans == INF) -1 else ans
  }

  fun sumOfArr(arr: IntArray): Long {
    var r = 0L
    for (x in arr) r += x
    return r
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.16ms, 60.7MB)
 * 테스트 2 〉	통과 (0.18ms, 60.4MB)
 * 테스트 3 〉	통과 (0.21ms, 58.4MB)
 * 테스트 4 〉	통과 (0.17ms, 59.7MB)
 * 테스트 5 〉	통과 (0.18ms, 60.3MB)
 * 테스트 6 〉	통과 (0.16ms, 60.1MB)
 * 테스트 7 〉	통과 (0.26ms, 59.9MB)
 * 테스트 8 〉	통과 (0.26ms, 59.8MB)
 * 테스트 9 〉	통과 (0.35ms, 59.6MB)
 * 테스트 10 〉	통과 (0.51ms, 59.8MB)
 * 테스트 11 〉	통과 (3.98ms, 65MB)
 * 테스트 12 〉	통과 (4.01ms, 65.8MB)
 * 테스트 13 〉	통과 (4.49ms, 64.3MB)
 * 테스트 14 〉	통과 (4.88ms, 65.7MB)
 * 테스트 15 〉	통과 (5.41ms, 68.9MB)
 * 테스트 16 〉	통과 (5.50ms, 69.1MB)
 * 테스트 17 〉	통과 (5.43ms, 69.8MB)
 * 테스트 18 〉	통과 (11.12ms, 86.3MB)
 * 테스트 19 〉	통과 (10.79ms, 88MB)
 * 테스트 20 〉	실패 (7.11ms, 88.2MB)
 * 테스트 21 〉	실패 (7.37ms, 88.3MB)
 * 테스트 22 〉	실패 (7.03ms, 88.4MB)
 * 테스트 23 〉	실패 (11.84ms, 87.6MB)
 * 테스트 24 〉	실패 (10.11ms, 84.6MB)
 * 테스트 25 〉	실패 (0.27ms, 60.3MB)
 * 테스트 26 〉	실패 (0.23ms, 59.3MB)
 * 테스트 27 〉	실패 (0.17ms, 59.9MB)
 * 테스트 28 〉	통과 (5.32ms, 68.9MB)
 * 테스트 29 〉	통과 (1.37ms, 61.4MB)
 * 테스트 30 〉	실패 (4.67ms, 70.9MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(intArrayOf(3, 2, 7, 2), intArrayOf(4, 6, 5, 1)),
    2
  )

  validate(
    s.solution(intArrayOf(1, 2, 1, 2), intArrayOf(1, 10, 1, 2)),
    7
  )

  validate(
    s.solution(intArrayOf(1, 1), intArrayOf(1, 5)),
    -1
  )
}

//          println("[$l ~ $r] sum=$sum cnt = $cnt")

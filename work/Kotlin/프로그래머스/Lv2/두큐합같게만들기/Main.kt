package 프로그래머스.Lv2.두큐합같게만들기

import util.validate

class Solution {
  companion object {

    const val INF = Int.MAX_VALUE
    const val X = -1
  }

  fun solution(q1: IntArray, q2: IntArray): Int {

    var s1 = 0L
    for (x in q1) s1 += x
    var s2 = 0L
    for (x in q2) s2 += x
    val total = s1 + s2
    if (total % 2 != 0L) return X

    var ans = INF
    val half = total / 2
    val len = q1.size
    val q1End = len - 1
    val rangeEnd = 2 * (len - 1)

    for (stt in 0..rangeEnd) {
      var sum = 0L

      for (end in stt..rangeEnd) {
        sum += if (end >= len) q2[end - len] else q1[end]
        if (sum > half) break
        if (sum < half) continue

        val sttInQ1 = stt <= q1End && end >= q1End
        val cnt = if (sttInQ1) {
          stt + if (end > q1End) end - q1End else 0
        } else {
          val fr = stt - if (stt >= len) len else 0
          val to = end - if (end >= len) len else 0
          (to + 1) + fr + len
        }

        if (cnt < ans) ans = cnt
      }
    }

    return if (ans == INF) -1 else ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 61MB)
 * 테스트 2 〉	통과 (0.01ms, 61.6MB)
 * 테스트 3 〉	통과 (0.02ms, 59.4MB)
 * 테스트 4 〉	통과 (0.03ms, 60.6MB)
 * 테스트 5 〉	통과 (0.10ms, 60.7MB)
 * 테스트 6 〉	통과 (0.39ms, 60.3MB)
 * 테스트 7 〉	통과 (1.41ms, 60.5MB)
 * 테스트 8 〉	통과 (2.95ms, 60.7MB)
 * 테스트 9 〉	통과 (10.41ms, 60.4MB)
 * 테스트 10 〉	통과 (12.95ms, 61.7MB)
 * 테스트 11 〉	통과 (2934.37ms, 63.8MB)
 * 테스트 12 〉	통과 (3289.24ms, 64.3MB)
 * 테스트 13 〉	통과 (6115.77ms, 63.4MB)
 * 테스트 14 〉	통과 (8411.38ms, 64.8MB)
 * 테스트 15 〉	실패 (시간 초과)
 * 테스트 16 〉	실패 (시간 초과)
 * 테스트 17 〉	실패 (시간 초과)
 * 테스트 18 〉	실패 (시간 초과)
 * 테스트 19 〉	실패 (시간 초과)
 * 테스트 20 〉	실패 (시간 초과)
 * 테스트 21 〉	실패 (시간 초과)
 * 테스트 22 〉	실패 (시간 초과)
 * 테스트 23 〉	실패 (시간 초과)
 * 테스트 24 〉	실패 (시간 초과)
 * 테스트 25 〉	통과 (0.41ms, 60.7MB)
 * 테스트 26 〉	통과 (0.38ms, 59.3MB)
 * 테스트 27 〉	통과 (0.36ms, 60.3MB)
 * 테스트 28 〉	실패 (시간 초과)
 * 테스트 29 〉	통과 (127.24ms, 59.3MB)
 * 테스트 30 〉	실패 (시간 초과)
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

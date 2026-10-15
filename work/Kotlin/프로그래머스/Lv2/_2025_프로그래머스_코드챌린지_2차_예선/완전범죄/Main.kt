package 프로그래머스.Lv2._2025_프로그래머스_코드챌린지_2차_예선.완전범죄

import util.validate

class Solution {
  private companion object {

    const val MIN_E = 1
    const val MAX_E = 3
    const val ALL_ITEMS = MAX_E * MAX_E
  }

  fun solution(evdnc: Array<IntArray>, n: Int, m: Int): Int {
    var aSum = 0
    val cnts = IntArray(ALL_ITEMS)
    val cap = MAX_E
    fun pos(a: Int, b: Int) = (MAX_E - a) * cap + (b - 1)

    for (i in evdnc.indices) {
      val e = evdnc[i]
      val a = e[0]
      val b = e[1]
      cnts[pos(a, b)]++
      aSum += a
    }

    var bSum = 0
    l@ for (a in MAX_E downTo MIN_E) {
      for (b in MIN_E..MAX_E) {
        var cnt = cnts[pos(a, b)]
        while (cnt-- > 0 && bSum + b < m) {
          aSum -= a
          if (aSum <= 0) break@l
          bSum += b
        }
      }
    }

    aSum = maxOf(aSum, 0)

    return if (aSum < n) aSum else -1
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.3MB)
 * 테스트 2 〉	통과 (0.02ms, 60.4MB)
 * 테스트 3 〉	통과 (0.02ms, 60MB)
 * 테스트 4 〉	통과 (0.02ms, 59.2MB)
 * 테스트 5 〉	통과 (0.02ms, 59.4MB)
 * 테스트 6 〉	통과 (0.02ms, 60.2MB)
 * 테스트 7 〉	통과 (0.02ms, 60.1MB)
 * 테스트 8 〉	통과 (0.03ms, 60.7MB)
 * 테스트 9 〉	통과 (0.02ms, 59.5MB)
 * 테스트 10 〉	통과 (0.02ms, 60.8MB)
 *
 * v2: WA1
 * 테스트 1 〉	통과 (0.02ms, 58.2MB)
 * 테스트 2 〉	통과 (0.03ms, 60.7MB)
 * 테스트 3 〉	통과 (0.03ms, 59.5MB)
 * 테스트 4 〉	통과 (0.03ms, 58.6MB)
 * 테스트 5 〉	통과 (0.03ms, 60.5MB)
 * 테스트 6 〉	통과 (0.04ms, 60.4MB)
 * 테스트 7 〉	통과 (0.03ms, 60.2MB)
 * 테스트 8 〉	통과 (0.03ms, 60.3MB)
 * 테스트 9 〉	통과 (0.02ms, 61MB)
 * 테스트 10 〉	통과 (0.02ms, 60.1MB)
 * 테스트 11 〉	통과 (0.02ms, 58MB)
 * 테스트 12 〉	통과 (0.02ms, 57.5MB)
 * 테스트 13 〉	통과 (0.03ms, 60.3MB)
 * 테스트 14 〉	통과 (0.02ms, 59.6MB)
 * 테스트 15 〉	통과 (0.04ms, 59.7MB)
 * 테스트 16 〉	통과 (0.02ms, 60MB)
 * 테스트 17 〉	통과 (0.03ms, 59.3MB)
 * 테스트 18 〉	통과 (0.02ms, 57.3MB)
 * 테스트 19 〉	통과 (0.03ms, 60.6MB)
 * 테스트 20 〉	통과 (0.03ms, 60.1MB)
 * 테스트 21 〉	통과 (0.02ms, 60.4MB)
 * 테스트 22 〉	통과 (0.03ms, 60.8MB)
 * 테스트 23 〉	통과 (0.04ms, 58.8MB)
 * 테스트 24 〉	통과 (0.03ms, 59.6MB)
 * 테스트 25 〉	통과 (0.03ms, 60.4MB)
 * 테스트 26 〉	실패 (0.02ms, 61MB)
 * 테스트 27 〉	통과 (0.03ms, 59.4MB)
 * 테스트 28 〉	통과 (0.03ms, 58.5MB)
 * 테스트 29 〉	통과 (0.03ms, 58MB)
 * 테스트 30 〉	통과 (0.02ms, 60.9MB)
 * 테스트 31 〉	통과 (0.03ms, 61MB)
 * 테스트 32 〉	통과 (0.03ms, 57.8MB)
 * 테스트 33 〉	통과 (0.03ms, 61.9MB)
 * 테스트 34 〉	실패 (0.03ms, 59.7MB)
 * 테스트 35 〉	통과 (0.03ms, 60.2MB)
 * 테스트 36 〉	통과 (0.03ms, 59.7MB)
 * 테스트 37 〉	통과 (0.02ms, 60.8MB)
 * 테스트 38 〉	통과 (0.02ms, 60MB)
 * 테스트 39 〉	통과 (0.02ms, 60.1MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(info: Array<IntArray>, n: Int, m: Int): Int {
 *         val itemCount = info.size
 *
 *         // dp[현재아이템][A흔적][B흔적] 별 가능여부
 *         val dp = Array(itemCount + 1) { Array(n) { BooleanArray(m) } }
 *         dp[0][0][0] = true
 *
 *         // 각 아이템에 대해
 *         for (i in 0 until itemCount) {
 *             // 현재까지의 가능한 모든 상태에 대해
 *             for (aTrace in 0 until n) {
 *                 for (bTrace in 0 until m) {
 *                     if (!dp[i][aTrace][bTrace]) continue
 *
 *                     // A가 훔치는 경우
 *                     val nextATrace = aTrace + info[i][0]
 *                     if (nextATrace < n) {
 *                         dp[i + 1][nextATrace][bTrace] = true
 *                     }
 *
 *                     // B가 훔치는 경우
 *                     val nextBTrace = bTrace + info[i][1]
 *                     if (nextBTrace < m) {
 *                         dp[i + 1][aTrace][nextBTrace] = true
 *                     }
 *                 }
 *             }
 *         }
 *
 *         // 마지막 상태에서 가능한 A의 흔적 최솟값 찾기
 *         var minATrace = n
 *         for (aTrace in 0 until n) {
 *             for (bTrace in 0 until m) {
 *                 if (dp[itemCount][aTrace][bTrace]) {
 *                     minATrace = minOf(minATrace, aTrace)
 *                 }
 *             }
 *         }
 *
 *         return if (minATrace == n) -1 else minATrace
 *     }
 * }
 * 테스트 1 〉	통과 (0.14ms, 59.8MB)
 * 테스트 2 〉	통과 (0.18ms, 60.1MB)
 * 테스트 3 〉	통과 (0.19ms, 59.7MB)
 * 테스트 4 〉	통과 (0.23ms, 59.9MB)
 * 테스트 5 〉	통과 (1.25ms, 60.2MB)
 * 테스트 6 〉	통과 (0.62ms, 59.8MB)
 * 테스트 7 〉	통과 (0.80ms, 60.3MB)
 * 테스트 8 〉	통과 (0.05ms, 59.6MB)
 * 테스트 9 〉	통과 (0.80ms, 61.2MB)
 * 테스트 10 〉	통과 (0.51ms, 59.1MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(info: Array<IntArray>, n: Int, m: Int): Int {
 *         val dp = Array(info.size) { Array(n) { IntArray(m) { -1 } } }
 *
 *         fun dfs(i: Int, a: Int, b: Int): Int {
 *             if (a >= n || b >= m) return Int.MAX_VALUE
 *             if (i == info.size) return a
 *             if (dp[i][a][b] != -1) return dp[i][a][b]
 *
 *             val (evidenceA, evidenceB) = info[i]
 *
 *             val pickA = dfs(i + 1, a + evidenceA, b)
 *             val pickB = dfs(i + 1, a, b + evidenceB)
 *
 *             dp[i][a][b] = Math.min(pickA, pickB)
 *
 *             return dp[i][a][b]
 *         }
 *
 *         val result = dfs(0, 0, 0)
 *
 *         return if (result == Int.MAX_VALUE) {
 *             -1
 *         } else {
 *             result
 *         }
 *     }
 * }
 * 테스트 1 〉	통과 (0.20ms, 60.5MB)
 * 테스트 2 〉	통과 (0.16ms, 59.3MB)
 * 테스트 3 〉	통과 (0.31ms, 58.2MB)
 * 테스트 4 〉	통과 (0.26ms, 59.9MB)
 * 테스트 5 〉	통과 (1.18ms, 61.4MB)
 * 테스트 6 〉	통과 (0.65ms, 60.6MB)
 * 테스트 7 〉	통과 (0.96ms, 59.6MB)
 * 테스트 8 〉	통과 (0.06ms, 60.7MB)
 * 테스트 9 〉	통과 (3.25ms, 58.1MB)
 * 테스트 10 〉	통과 (1.59ms, 58.6MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(
      arrayOf(
        intArrayOf(1, 2),
        intArrayOf(2, 3),
        intArrayOf(2, 1),
      ),
      4,
      4
    ), 2
  )

  validate(
    s.solution(
      arrayOf(
        intArrayOf(1, 2),
        intArrayOf(2, 3),
        intArrayOf(2, 1),
      ),
      1,
      7
    ), 0
  )

  validate(
    s.solution(
      arrayOf(
        intArrayOf(3, 3),
        intArrayOf(3, 3),
      ),
      7,
      1
    ), 6
  )

  validate(
    s.solution(
      arrayOf(
        intArrayOf(3, 3),
        intArrayOf(3, 3),
      ),
      6,
      1
    ), -1
  )

//
  validate(
    s.solution(
      arrayOf(
        intArrayOf(3, 1),
        intArrayOf(2, 2)
      ),
      5,
      3
    ),
    2
  )

  // 반례: A 한도 때문에 실패
  // ME: -1 / AC: 1
  validate(s.solution(
    arrayOf(
      intArrayOf(1, 1),
      intArrayOf(2, 3)
    ),
    2,
    4
  ), 1)
}

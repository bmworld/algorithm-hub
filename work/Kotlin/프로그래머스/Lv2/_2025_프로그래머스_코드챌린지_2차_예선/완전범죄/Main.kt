package 프로그래머스.Lv2._2025_프로그래머스_코드챌린지_2차_예선.완전범죄

import util.validate

class Solution {
  private companion object {

    const val ARRESTED = -1

    const val SEP = 10
    const val MIN_E = 1
    const val MAX_E = 3
    const val MAX_DIFF = MAX_E - MIN_E
  }

  fun solution(evdnc: Array<IntArray>, n: Int, m: Int): Int {
    val N = evdnc.size
    val arr = IntArray(N)

    for (i in evdnc.indices) {
      val e = evdnc[i]
      val a = e[0]
      val b = e[1]
      val diff = a - b + MAX_DIFF
      arr[i] = diff * SEP + a
    }

    qs(arr, 0, N - 1)

    var aSum = 0
    var bSum = 0
    for (i in 0 until N) {
      val e = arr[i]
      val diff = e / SEP - MAX_DIFF
      val a = e % SEP
      val b = a - diff


      when {
        bSum + b < m -> bSum += b
        aSum + a < n -> aSum += a
        else -> return ARRESTED
      }
    }

    return aSum
  }

  fun swap(
    a: IntArray,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(
    a: IntArray,
    l: Int,
    r: Int,
  ) {
    if (l >= r) return

    var pos = l
    var pl = l
    var pr = r
    val piv = a[(l + r) shr 1]

    while (pos <= pr) {
      val x = a[pos]
      when {
        x > piv -> swap(a, pos++, pl++)
        x < piv -> swap(a, pos, pr--)
        else -> pos++
      }
    }
    qs(a, l, pl - 1)
    qs(a, pr + 1, r)
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
}

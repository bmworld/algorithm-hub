package 프로그래머스.Lv2._2025_프로그래머스_코드챌린지_2차_예선.완전범죄

import util.validate

class Solution {
  private companion object {

    const val EMPTY = -1
    const val ITEM_CNT = 9
    const val MAX_EVDNC = 3
    const val CAP = MAX_EVDNC
  }

  fun solution(evdnc: Array<IntArray>, n: Int, m: Int): Int {
    var aSum = 0

    val cnts = IntArray(ITEM_CNT)
    fun pos(a: Int, b: Int) = (MAX_EVDNC - a) * CAP + (b - 1)
    for (i in evdnc.indices) {
      val e = evdnc[i]
      val a = e[0]
      val b = e[1]
      cnts[pos(a, b)]++
      aSum += a
    }

    val dp = IntArray(m) { EMPTY }
    dp[0] = 0

    for (i in cnts.indices) {
      val cnt = cnts[i]
      if (cnt == 0) continue
      val a = MAX_EVDNC - i / CAP
      val b = i % CAP + 1

      repeat(cnt) { j ->
        for (used in m - 1 downTo b * (j + 1)) {
          val rmn = used - b
          val rmnA = dp[rmn]
          if (rmnA == EMPTY) continue
          val cur = rmnA + a
          if (cur > dp[used]) dp[used] = cur
        }
      }
    }

    var bSum = 0
    for (i in m - 1 downTo 1) {
      val x = dp[i]
      if (x > bSum) bSum = x
    }
    val best = aSum - bSum
    return if (best < n) maxOf(0, best) else -1
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
 * 테스트 26 〉	실패 (0.02ms, 61MB)
 * 테스트 34 〉	실패 (0.03ms, 59.7MB)
 *
 * v3: 두 아이템을 비교할 필요가 있는 것만 별도 처리
 * 테스트 1 〉	통과 (0.32ms, 59.9MB)
 * 테스트 2 〉	통과 (0.32ms, 59.8MB)
 * 테스트 3 〉	통과 (0.30ms, 59.1MB)
 * 테스트 4 〉	통과 (0.18ms, 60MB)
 * 테스트 5 〉	통과 (0.29ms, 60.5MB)
 * 테스트 6 〉	통과 (0.26ms, 60.2MB)
 * 테스트 7 〉	통과 (0.28ms, 58.7MB)
 * 테스트 8 〉	통과 (0.26ms, 59.9MB)
 * 테스트 9 〉	통과 (0.18ms, 59.9MB)
 * 테스트 10 〉	통과 (0.21ms, 60.6MB)
 *
 * v4: Greedy -> DP
 * 테스트 1 〉	통과 (0.02ms, 60.3MB)
 * 테스트 2 〉	통과 (0.03ms, 58.2MB)
 * 테스트 3 〉	통과 (0.02ms, 61MB)
 * 테스트 4 〉	통과 (0.05ms, 61.6MB)
 * 테스트 5 〉	통과 (0.04ms, 60.9MB)
 * 테스트 6 〉	통과 (0.04ms, 59.4MB)
 * 테스트 7 〉	통과 (0.03ms, 60.7MB)
 * 테스트 8 〉	통과 (0.04ms, 60.1MB)
 * 테스트 9 〉	통과 (0.04ms, 60.4MB)
 * 테스트 10 〉	통과 (0.05ms, 60.6MB)
 *
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
  // CASE: 기본 최적 배정
  validate(s.solution(
    arrayOf(
      intArrayOf(1, 2),
      intArrayOf(2, 3),
      intArrayOf(2, 1),
    ),
    4,
    4
  ), 2)

// CASE: B가 모든 물건을 훔칠 수 있는 경우
  validate(s.solution(
    arrayOf(
      intArrayOf(1, 2),
      intArrayOf(2, 3),
      intArrayOf(2, 1)
    ),
    1,
    7
  ), 0)

// CASE: B가 물건을 훔칠 수 없는 경우
  validate(s.solution(
    arrayOf(
      intArrayOf(3, 3),
      intArrayOf(3, 3)
    ),
    7,
    1
  ), 6)

// CASE: 두 도둑 모두 체포를 피할 수 없는 경우
  validate(s.solution(
    arrayOf(
      intArrayOf(3, 3),
      intArrayOf(3, 3)
    ),
    6,
    1
  ), -1)

// CASE: 동일한 B 흔적 한도에서 최적 선택
  validate(s.solution(
    arrayOf(
      intArrayOf(3, 1),
      intArrayOf(2, 2)
    ),
    5,
    3
  ), 2)

// CASE: A 한도에 따른 선택
  validate(s.solution(
    arrayOf(
      intArrayOf(1, 1),
      intArrayOf(2, 3)
    ),
    2,
    4
  ), 1)

// CASE: 동일한 절약 효율을 가진 유형 간 묶음 비교
  validate(s.solution(
    arrayOf(
      intArrayOf(3, 2),
      intArrayOf(2, 1),
      intArrayOf(2, 1)
    ),
    4,
    3
  ), 3)

  // CASE: 동일한 B 흔적 용량에서 묶음 선택 실패
  // ME: 3 / 정답: 2
  validate(s.solution(
    arrayOf(
      intArrayOf(3, 2),
      intArrayOf(2, 1),
      intArrayOf(2, 1)
    ),
    8,
    4
  ), 2)

// CASE: 비인접 유형 간 최적 배정 실패
// ME: 3 / 정답: 2
  validate(s.solution(
    arrayOf(
      intArrayOf(2, 1),
      intArrayOf(2, 1),
      intArrayOf(3, 3)
    ),
    8,
    5
  ), 2)

// CASE: B 흔적 소비량이 다른 선택지의 비교 실패
// ME: 4 / 정답: 3
  validate(s.solution(
    arrayOf(
      intArrayOf(2, 1),
      intArrayOf(3, 3),
      intArrayOf(2, 2)
    ),
    8,
    4
  ), 3)

// CASE: 동일 유형의 묶음이 단일 물건보다 유리한 경우
// ME: 4 / 정답: 3
  validate(s.solution(
    arrayOf(
      intArrayOf(3, 3),
      intArrayOf(2, 2),
      intArrayOf(2, 2)
    ),
    8,
    5
  ), 3)

// CASE: 작은 A 흔적의 물건들을 우선 배정해야 하는 경우
// ME: 2 / 정답: 1
  validate(s.solution(
    arrayOf(
      intArrayOf(1, 1),
      intArrayOf(1, 1),
      intArrayOf(2, 3)
    ),
    5,
    5
  ), 1)
}

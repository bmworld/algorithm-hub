package 프로그래머스.Lv2._2025_프로그래머스_코드챌린지_2차_예선.완전범죄

import util.validate

class Solution {
  private companion object {

    const val A = 0
    const val B = 1
    const val IMPOSSIBLE = -1

    val items = arrayOf(
      intArrayOf(3, 1),
      intArrayOf(3, 2),
      intArrayOf(2, 1),
      intArrayOf(3, 3),
      intArrayOf(2, 2),
      intArrayOf(1, 1),
      intArrayOf(2, 3),
      intArrayOf(1, 2),
      intArrayOf(1, 3),
    )
  }

  fun solution(evdnc: Array<IntArray>, n: Int, m: Int): Int {
    var aSum = 0

    val itemSize = items.size
    val cnts = IntArray(itemSize)
    for (i in evdnc.indices) {
      val e = evdnc[i]
      val a = e[A]
      val b = e[B]
      cnts[pos(a, b)]++
      aSum += a
    }

    var bSum = 0
    l@ for (cur in 0 until itemSize) {
      var c1 = cnts[cur]
      if (c1 == 0) continue

      val (a1, b1) = items[cur]
      val nxt = cur + 1
      if (nxt < itemSize) {
        val (a2, b2) = items[nxt]
        var c2 = cnts[nxt]
        val cap = maxOf(b1, b2)
        var t1 = 0
        var t2 = 0
        while (c1 > 0 && bSum + b1 * minOf(c1, cap / b1).also { t1 = it } < m
          && c2 > 0 && bSum + b2 * minOf(c2, cap / b2).also { t2 = it } < m) {
          val p1 = a1 * t1
          val p2 = a2 * t2
          if (p1 >= p2) {
            c1 -= t1
            cnts[cur] -= t1
            aSum -= p1
            bSum += b1 * t1
          } else {
            c2 -= t2
            cnts[nxt] -= t2
            aSum -= p2
            bSum += b2 * t2
          }
        }
      }

      val times = minOf(c1, (m - 1 - bSum) / b1)
      aSum -= a1 * times
      if (aSum <= 0) break@l
      bSum += b1 * times
      cnts[cur] -= times
    }

    return if (aSum < n) maxOf(aSum, 0) else IMPOSSIBLE
  }

  private fun pos(a: Int, b: Int) = when {
    a == 3 && b == 1 -> 0
    a == 3 && b == 2 -> 1
    a == 2 && b == 1 -> 2
    a == 3 && b == 3 -> 3
    a == 2 && b == 2 -> 4
    a == 1 && b == 1 -> 5
    a == 2 && b == 3 -> 6
    a == 1 && b == 2 -> 7
    a == 1 && b == 3 -> 8
    else -> throw Exception()
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
 * 테스트 11 〉	통과 (0.02ms, 59.6MB)
 * 테스트 12 〉	통과 (0.02ms, 60MB)
 * 테스트 13 〉	통과 (0.02ms, 61.5MB)
 * 테스트 14 〉	통과 (0.02ms, 60.2MB)
 * 테스트 15 〉	통과 (0.02ms, 60MB)
 * 테스트 16 〉	통과 (0.19ms, 60.6MB)
 * 테스트 17 〉	통과 (0.18ms, 60.3MB)
 * 테스트 18 〉	통과 (0.36ms, 60.6MB)
 * 테스트 19 〉	통과 (0.22ms, 60.8MB)
 * 테스트 20 〉	통과 (0.26ms, 58.6MB)
 * 테스트 21 〉	통과 (0.18ms, 59.3MB)
 * 테스트 22 〉	통과 (0.26ms, 60.6MB)
 * 테스트 23 〉	통과 (0.21ms, 60.2MB)
 * 테스트 24 〉	통과 (0.22ms, 58.9MB)
 * 테스트 25 〉	통과 (0.22ms, 59.2MB)
 * 테스트 26 〉	통과 (0.25ms, 59.9MB)
 * 테스트 27 〉	통과 (0.27ms, 60.3MB)
 * 테스트 28 〉	통과 (0.22ms, 60.5MB)
 * 테스트 29 〉	통과 (0.20ms, 60.3MB)
 * 테스트 30 〉	통과 (0.18ms, 60MB)
 * 테스트 31 〉	통과 (0.18ms, 60MB)
 * 테스트 32 〉	통과 (0.27ms, 59.7MB)
 * 테스트 33 〉	통과 (0.27ms, 59.9MB)
 * 테스트 34 〉	통과 (0.18ms, 60.6MB)
 * 테스트 35 〉	통과 (0.29ms, 60.7MB)
 * 테스트 36 〉	통과 (0.28ms, 61.4MB)
 * 테스트 37 〉	통과 (0.23ms, 61MB)
 * 테스트 38 〉	통과 (0.20ms, 60.7MB)
 * 테스트 39 〉	통과 (0.19ms, 59.3MB)
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
      intArrayOf(2, 1)
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

// println(
//            "[aSum=$aSum, bSum=$bSum] $a1 * $t1 = $p1 (b1=$b1) vs $a2($b2) * $t2  = $p2 (b2=$b2)")

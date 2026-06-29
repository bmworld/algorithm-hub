package 프로그래머스.Lv1.실패율

import util.validate

class Solution {

  val SEP2 = 1_000
  val SEP1 = SEP2 * 1_000_000
  fun solution(N: Int, stages: IntArray): IntArray {
    val cnts = LongArray(N + 2)
    for (n in stages) cnts[n]++
    var denominator = cnts[N + 1]

    val a = LongArray(N) { it.toLong() }
    for (n in N downTo 1) {
      val cnt = cnts[n]
      if (cnt == 0L) continue
      denominator += cnt
      a[n - 1] += cnt * SEP1 + denominator * SEP2
    }

    qs(a, 0, N - 1)
    val ans = IntArray(N)
    var i = 0
    for (x in a) ans[i++] = (x % SEP2 + 1).toInt()

    return ans
  }

  fun swap(
    a: LongArray,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(
    a: LongArray,
    l: Int,
    r: Int,
  ) {
    if (l >= r) return
    var pos = l
    var pl = l
    var pr = r
    val pivCdn = a[(l + r) shr 1]
    val pivC = pivCdn / SEP1
    val pivDn = pivCdn % SEP1
    val pivD = pivDn / SEP2
    val pivN = pivDn % SEP2

    while (pos <= pr) {
      val cdn = a[pos]
      val c = cdn / SEP1
      val dn = cdn % SEP1
      val d = dn / SEP2
      val n = dn % SEP2
      val pivX = if (d == 0L) pivC else pivC * d
      val x = if (pivD == 0L) c else c * pivD
      when {
        x > pivX || x == pivX && n < pivN -> swap(a, pos++, pl++)
        x < pivX || x == pivX && n > pivN -> swap(a, pos, pr--)
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
 * 테스트 1 〉	통과 (0.02ms, 58.4MB)
 * 테스트 2 〉	통과 (0.03ms, 59.9MB)
 * 테스트 3 〉	통과 (0.66ms, 60.4MB)
 * 테스트 4 〉	통과 (1.78ms, 64MB)
 * 테스트 5 〉	통과 (2.22ms, 69.1MB)
 * 테스트 6 〉	통과 (0.17ms, 60.4MB)
 * 테스트 7 〉	통과 (0.26ms, 60.8MB)
 * 테스트 8 〉	통과 (1.75ms, 63.6MB)
 * 테스트 9 〉	통과 (2.19ms, 69MB)
 * 테스트 10 〉	통과 (1.78ms, 63.8MB)
 * 테스트 11 〉	통과 (1.76ms, 62.5MB)
 * 테스트 12 〉	통과 (1.86ms, 63.4MB)
 * 테스트 13 〉	통과 (2.03ms, 66.3MB)
 * 테스트 14 〉	통과 (0.03ms, 58.3MB)
 * 테스트 15 〉	통과 (1.14ms, 61.3MB)
 * 테스트 16 〉	통과 (0.56ms, 59.3MB)
 * 테스트 17 〉	통과 (1.11ms, 61.4MB)
 * 테스트 18 〉	통과 (0.57ms, 59.6MB)
 * 테스트 19 〉	통과 (0.13ms, 58.6MB)
 * 테스트 20 〉	통과 (0.80ms, 61.4MB)
 * 테스트 21 〉	통과 (1.54ms, 61.4MB)
 * 테스트 22 〉	통과 (2.35ms, 69MB)
 * 테스트 23 〉	통과 (1.75ms, 64.9MB)
 * 테스트 24 〉	통과 (2.03ms, 66MB)
 * 테스트 25 〉	통과 (0.02ms, 60.6MB)
 * 테스트 26 〉	통과 (0.01ms, 59.2MB)
 * 테스트 27 〉	통과 (0.01ms, 58.5MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * data class Stage(var level: Int, var pass: Int, var fail: Int) {
 *     val failRate: Float
 *     get() = if (fail+pass == 0)  0.0f else (fail.toFloat()) / (pass + fail)
 * }
 *
 * class Solution {
 *     fun solution(N: Int, stages: IntArray): IntArray {
 *         var stageInfo = Array(N,  { Stage(it+1, 0, 0)})
 *
 *         for (level in stages) {
 *             for (i in 0.until(level-1)) {
 *                 stageInfo[i].pass++
 *             }
 *             if (level != N+1) stageInfo[level-1].fail++
 *         }
 *         stageInfo.sortByDescending { it.failRate }
 *         return stageInfo.map { it.level }.toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (11.63ms, 63.9MB)
 * 테스트 2 〉	통과 (10.95ms, 63.6MB)
 * 테스트 3 〉	통과 (19.99ms, 64.8MB)
 * 테스트 4 〉	통과 (29.93ms, 67.1MB)
 * 테스트 5 〉	통과 (35.35ms, 72.3MB)
 * 테스트 6 〉	통과 (12.62ms, 63.1MB)
 * 테스트 7 〉	통과 (14.14ms, 64.5MB)
 * 테스트 8 〉	통과 (23.55ms, 66.3MB)
 * 테스트 9 〉	통과 (36.64ms, 72.8MB)
 * 테스트 10 〉	통과 (23.80ms, 66.4MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(5, intArrayOf(2, 1, 2, 6, 2, 4, 3, 3)), intArrayOf(3, 4, 2, 1, 5))
  validate(s.solution(4, intArrayOf(4, 4, 4, 4, 4)), intArrayOf(4, 1, 2, 3))
  validate(s.solution(2, intArrayOf(3, 3, 3, 3)), intArrayOf(1, 2))
  validate(s.solution(2, intArrayOf(2, 2, 3, 3)), intArrayOf(2, 1))
  validate(s.solution(2, intArrayOf(2, 2)), intArrayOf(2, 1))
  validate(s.solution(10, intArrayOf(2, 2, 10, 1)), intArrayOf(10, 2, 1, 3, 4, 5, 6, 7, 8, 9))
  // 1. 모두 클리어
  validate(
    s.solution(5, intArrayOf(6, 6, 6, 6, 6)),
    intArrayOf(1, 2, 3, 4, 5)
  )
  // 2. 전부 1스테이지
  validate(
    s.solution(5, intArrayOf(1, 1, 1, 1, 1)),
    intArrayOf(1, 2, 3, 4, 5)
  )
  // 3. 마지막 스테이지만 실패
  validate(
    s.solution(5, intArrayOf(5, 5, 5, 5, 5)),
    intArrayOf(5, 1, 2, 3, 4)
  )
  validate(
    s.solution(3, intArrayOf(1, 1, 1, 2, 3, 3, 3, 3, 4, 4)),
    intArrayOf(3, 1, 2)
  )
  // 7. denominator 급감
  validate(
    s.solution(5, intArrayOf(
      5, 5, 5,
      6
    )),
    intArrayOf(5, 1, 2, 3, 4)
  )

  // 8. 모든 stage에 한 명씩
  validate(
    s.solution(5, intArrayOf(
      1, 2, 3, 4, 5, 6
    )),
    intArrayOf(5, 4, 3, 2, 1)
  )

  // 9. Double 오차 유도 (1/3, 2/6)
  validate(
    s.solution(3, intArrayOf(
      2,
      3,
      4, 4, 4
    )),
    intArrayOf(3, 2, 1)
  )
  // 10. 1/3 vs 2/6
  validate(
    s.solution(4, intArrayOf(
      2,
      3, 3,
      5, 5, 5
    )),
    intArrayOf(3, 2, 1, 4)
  )
}

//    println("[$l ~ $r] = $pivCdn")
//      println("pivX = $pivX vs x=$x")

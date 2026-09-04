package 프로그래머스.Lv2.광물캐기

import util.validate

class Solution {
  companion object {

    const val D = 25
    const val I = 5
    const val S = 1
    const val DIA = 'd'
    const val IRN = 'i'
    const val BUNDLE = 5
  }

  fun solution(picks: IntArray, minerals: Array<String>): Int {
    var ans = 0

    val N = minerals.size
    val fatigues = IntArray((N + BUNDLE - 1) / BUNDLE)
    var len = 0

    var i = 0
    var fatigue = 0
    while (i < N) {
      fatigue += when (minerals[i++][0]) {
        DIA -> D
        IRN -> I
        else -> S
      }

      if (i % 5 == 0 || i == N) {
        fatigues[len++] = fatigue
        fatigue = 0
      }
    }

    qs(fatigues, 0, len - 1)

    var dCnt = picks[0]
    while (dCnt-- > 0 && len > 0) {
      var f = fatigues[--len]
      val dCnt = f / D
      if (dCnt > 0) f %= D
      val iCnt = f / I
      if (iCnt > 0) f %= I

      ans += dCnt + iCnt + f
    }

    var iCnt = picks[1]
    while (iCnt-- > 0 && len > 0) {
      var f = fatigues[--len]
      val dCnt = f / D
      if (dCnt > 0) f %= D
      val iCnt = f / I
      if (iCnt > 0) f %= I

      ans += dCnt * 5 + iCnt + f
    }

    var sCnt = picks[2]
    while (sCnt-- > 0 && len > 0) {
      var f = fatigues[--len]
      val dCnt = f / D
      if (dCnt > 0) f %= D
      val iCnt = f / I
      if (iCnt > 0) f %= I

      ans += dCnt * 25 + iCnt * 5 + f
    }

    return ans
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
        x < piv -> swap(a, pos++, pl++)
        x > piv -> swap(a, pos, pr--)
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
 * 테스트 1 〉	통과 (0.02ms, 58MB)
 * 테스트 2 〉	통과 (0.03ms, 60.1MB)
 * 테스트 3 〉	통과 (0.02ms, 61MB)
 * 테스트 4 〉	통과 (0.04ms, 60.8MB)
 * 테스트 5 〉	통과 (0.02ms, 58.4MB)
 * 테스트 6 〉	통과 (0.02ms, 59.6MB)
 * 테스트 7 〉	통과 (0.04ms, 59.9MB)
 * 테스트 8 〉	실패 (0.02ms, 60.7MB)
 * 테스트 9 〉	실패 (0.03ms, 60.8MB)
 * 테스트 10 〉	통과 (0.02ms, 59.3MB)
 * 테스트 11 〉	통과 (0.02ms, 60.2MB)
 * 테스트 12 〉	통과 (0.02ms, 61.8MB)
 * 테스트 13 〉	통과 (0.03ms, 57.2MB)
 * 테스트 14 〉	통과 (0.02ms, 60.3MB)
 * 테스트 15 〉	통과 (0.02ms, 61MB)
 * 테스트 16 〉	통과 (0.02ms, 60.7MB)
 * 테스트 17 〉	통과 (0.02ms, 60.5MB)
 * 테스트 18 〉	통과 (0.02ms, 60.6MB)
 * 테스트 19 〉	통과 (0.02ms, 60.3MB)
 * 테스트 20 〉	실패 (0.03ms, 60.1MB)
 * 테스트 21 〉	통과 (0.02ms, 60.5MB)
 * 테스트 22 〉	통과 (0.03ms, 59.5MB)
 * 테스트 23 〉	통과 (0.02ms, 59.4MB)
 * 테스트 24 〉	통과 (0.02ms, 61.5MB)
 * 테스트 25 〉	통과 (0.02ms, 60.7MB)
 * 테스트 26 〉	통과 (0.02ms, 60.7MB)
 * 테스트 27 〉	통과 (0.02ms, 59.3MB)
 * 테스트 28 〉	통과 (0.02ms, 60.5MB)
 * 테스트 29 〉	통과 (0.02ms, 60MB)
 * 테스트 30 〉	통과 (0.02ms, 60.2MB)
 * 테스트 31 〉	통과 (0.02ms, 60.9MB)
 * 테스트 32 〉	통과 (0.02ms, 60.1MB)
 * 테스트 33 〉	통과 (0.02ms, 60.4MB)
 * 테스트 34 〉	통과 (0.02ms, 60.3MB)
 * 테스트 35 〉	통과 (0.02ms, 60.7MB)
 *
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()

  validate(
    s.solution(
      intArrayOf(1, 2, 3),
      arrayOf("diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone")
    ), 12
  )

  validate(
    s.solution(
      intArrayOf(0, 1, 1),
      arrayOf(
        "diamond", "diamond", "diamond", "diamond", "diamond",
        "iron", "iron", "iron", "iron", "iron", "diamond"
      )
    ), 50
  )

}

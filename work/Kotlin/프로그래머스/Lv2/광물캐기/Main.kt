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

    var dCnt = picks[0]
    var iCnt = picks[1]
    var sCnt = picks[2]

    val maxGroup = dCnt + iCnt + sCnt
    val N = minerals.size
    val fatigues = IntArray((N + BUNDLE - 1) / BUNDLE)
    var group = 0

    var i = 0
    var fatigue = 0
    while (i < N) {
      fatigue += when (minerals[i++][0]) {
        DIA -> D
        IRN -> I
        else -> S
      }

      if (i % 5 == 0 || i == N) {
        fatigues[group++] = fatigue
        if (group < maxGroup) fatigue = 0
        else break
      }
    }

    qs(fatigues, 0, group - 1)

    while (dCnt-- > 0 && group > 0) {
      var f = fatigues[--group]
      if (f > D) {
        ans += f / D
        f %= D
      }

      if (f > I) {
        ans += f / I
        f %= I
      }

      ans += f
    }


    while (iCnt-- > 0 && group > 0) {
      var f = fatigues[--group]
      if (f > D) {
        ans += (f / D) * 5
        f %= D
      }

      if (f > I) {
        ans += f / I
        f %= I
      }

      ans += f
    }


    while (sCnt-- > 0 && group > 0) {
      var f = fatigues[--group]
      if (f > D) {
        ans += (f / D) * 25
        f %= D
      }

      if (f > I) {
        ans += (f / I) * 5
        f %= I
      }

      ans += f
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
 * 테스트 1 〉	통과 (0.02ms, 58.5MB)
 * 테스트 2 〉	통과 (0.02ms, 61MB)
 * 테스트 3 〉	통과 (0.02ms, 59.5MB)
 * 테스트 4 〉	통과 (0.02ms, 60.9MB)
 * 테스트 5 〉	통과 (0.03ms, 60.4MB)
 * 테스트 6 〉	통과 (0.02ms, 57.9MB)
 * 테스트 7 〉	통과 (0.02ms, 59MB)
 * 테스트 8 〉	통과 (0.02ms, 60.3MB)
 * 테스트 9 〉	통과 (0.02ms, 59.7MB)
 * 테스트 10 〉	실패 (0.02ms, 60.8MB)
 * 테스트 11 〉	통과 (0.03ms, 58.8MB)
 * 테스트 12 〉	통과 (0.02ms, 60.3MB)
 * 테스트 13 〉	통과 (0.03ms, 58MB)
 * 테스트 14 〉	실패 (0.02ms, 60.4MB)
 * 테스트 15 〉	통과 (0.02ms, 61.9MB)
 * 테스트 16 〉	통과 (0.02ms, 59.3MB)
 * 테스트 17 〉	통과 (0.02ms, 57.7MB)
 * 테스트 18 〉	통과 (0.02ms, 60.9MB)
 * 테스트 19 〉	통과 (0.02ms, 60.3MB)
 * 테스트 20 〉	통과 (0.02ms, 59.5MB)
 * 테스트 21 〉	통과 (0.02ms, 60.4MB)
 * 테스트 22 〉	통과 (0.02ms, 60.8MB)
 * 테스트 23 〉	통과 (0.02ms, 59.7MB)
 * 테스트 24 〉	통과 (0.02ms, 60.1MB)
 * 테스트 25 〉	실패 (0.02ms, 59.9MB)
 * 테스트 26 〉	통과 (0.02ms, 60.7MB)
 * 테스트 27 〉	통과 (0.02ms, 60.3MB)
 * 테스트 28 〉	통과 (0.02ms, 61.5MB)
 * 테스트 29 〉	실패 (0.02ms, 60.6MB)
 * 테스트 30 〉	실패 (0.02ms, 59.9MB)
 * 테스트 31 〉	실패 (0.02ms, 60.3MB)
 * 테스트 32 〉	실패 (0.02ms, 60.4MB)
 * 테스트 33 〉	실패 (0.02ms, 60.8MB)
 * 테스트 34 〉	통과 (0.02ms, 59MB)
 * 테스트 35 〉	통과 (0.02ms, 60.4MB)
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

  validate(
    s.solution(
      intArrayOf(0, 0, 0),
      arrayOf(
        "diamond", "diamond", "diamond", "diamond", "diamond",
        "iron", "iron", "iron", "iron", "iron", "diamond"
      )
    ), 0
  )


  validate(
    s.solution(
      intArrayOf(1, 0, 0),
      arrayOf(
        "diamond", "diamond", "diamond", "diamond", "diamond",
        "iron", "iron", "iron", "iron", "iron", "diamond"
      )
    ), 5
  )

  validate(
    s.solution(
      intArrayOf(0, 1, 0),
      arrayOf(
        "diamond", "diamond", "diamond", "diamond", "diamond",
        "iron", "iron", "iron", "iron", "iron", "diamond"
      )
    ), 25
  )


  validate(
    s.solution(
      intArrayOf(0, 0, 1),
      arrayOf(
        "iron", "iron", "iron", "iron", "iron",
        "diamond", "diamond", "diamond", "diamond", "diamond",
      )
    ), 25
  )

  validate(
    s.solution(
      intArrayOf(10, 0, 0),
      arrayOf(
        "iron", "iron", "iron", "iron", "iron",
        "diamond"
      )
    ), 6
  )

}

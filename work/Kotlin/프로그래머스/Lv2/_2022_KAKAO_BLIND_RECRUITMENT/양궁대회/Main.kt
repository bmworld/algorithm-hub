package 프로그래머스.Lv2._2022_KAKAO_BLIND_RECRUITMENT.양궁대회

import util.validate

class Solution {
  companion object {

    const val MAX_SCORE = 10
    const val CNT_SEP = 10
    const val ESTEEMATED_SEP = CNT_SEP * 1_000
    const val ALL = MAX_SCORE + 1
    const val IMPOSSIBLE = 0
  }

  fun solution(n: Int, info: IntArray): IntArray {
    var RYAN = 0
    var APEACH = 0

    val scores = IntArray(ALL) { i ->
      val score = MAX_SCORE - i
      val aCnt = info[i]
      if (aCnt > 0) APEACH += score

      when {
        aCnt >= n -> IMPOSSIBLE
        else -> {
          val rCnt = aCnt + 1
          val gained = score * if (aCnt > 0) 2 else 1
          val esteemed = gained * 100 / rCnt
          esteemed * ESTEEMATED_SEP + score * CNT_SEP + rCnt
        }
      }
    }

    qs(scores, 0, ALL - 1)

    val ans = IntArray(ALL)
    var rmn = n
    for (i in scores.indices) {
      val sc = scores[i] % ESTEEMATED_SEP
      val cnt = sc % CNT_SEP
      val score = sc / CNT_SEP
      val gainedScore = score * if (cnt > 1) 2 else 1
      scores[i] = gainedScore * CNT_SEP + cnt
      if (rmn >= cnt) {
        RYAN += gainedScore
        rmn -= cnt
        ans[MAX_SCORE - score] = cnt
      }
    }

    if (rmn > 0) ans[MAX_SCORE] = rmn
    if (RYAN <= APEACH) return intArrayOf(-1)

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
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(1,
    intArrayOf(1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    intArrayOf(-1)
  )

  validate(s.solution(5,
    intArrayOf(2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0)),
    intArrayOf(0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0)
  )

  validate(s.solution(9,
    intArrayOf(0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1)),
    intArrayOf(1, 1, 2, 0, 1, 2, 2, 0, 0, 0, 0)
  )

  validate(s.solution(10,
    intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 3)),
    intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 2)
  )

  validate(s.solution(10,
    intArrayOf(10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    intArrayOf(0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
  )

  // TODO 최상위 3개가 아닌, 다른 케이스로 승리할 수 있는 것...

}

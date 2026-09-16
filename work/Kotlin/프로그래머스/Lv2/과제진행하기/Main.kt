package 프로그래머스.Lv2.과제진행하기

import util.validate

class Solution {
  companion object {

    const val MAX_T = 24 * 60
    const val SEP = 1_000
    const val ZERO = 48
    const val EMPTY = -1
  }

  fun solution(plans: Array<Array<String>>): Array<String> {
    val N = plans.size
    var ans = Array(N) { "" }
    var ai = 0

    val nameMapper = Array(N) { "" }
    val stack = IntArray(N)
    var stacked = 0
    val schdl = IntArray(MAX_T)

    var minT = MAX_T
    var maxT = 0
    for (i in 0 until N) {
      val p = plans[i]
      nameMapper[i] = p[0]
      val t = parseT(p[1])
      val pt = p[2].toInt()
      schdl[t] = i * SEP + pt

      if (t < minT) minT = t
      if (t > maxT) maxT = t
    }

    var cur = EMPTY
    var rmnPt = 0
    for (t in minT..maxT) {
      val x1 = schdl[t]
      when {
        x1 > 0 -> {
          if (cur != EMPTY) stack[stacked++] = cur * SEP + rmnPt
          cur = x1 / SEP
          rmnPt = x1 % SEP - 1
        }
        cur == EMPTY -> if (stacked > 0) {
          val x2 = stack[--stacked]
          cur = x2 / SEP
          rmnPt = x2 % SEP - 1
        }
        else -> if (--rmnPt <= 0) {
          ans[ai++] = nameMapper[cur]
          cur = EMPTY
        }
      }
    }

    ans[ai++] = nameMapper[cur]

    repeat(stacked) {
      ans[ai++] = nameMapper[stack[--stacked] / SEP]
    }

    return ans
  }

  fun parseT(hhMM: String): Int =
    (hhMM[0].code - ZERO) * 600 + (hhMM[1].code - ZERO) * 60 + (hhMM[3].code - ZERO) * 10 + (hhMM[4].code - ZERO)
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.03ms, 59.2MB)
 * 테스트 2 〉	통과 (0.03ms, 60.1MB)
 * 테스트 3 〉	통과 (0.07ms, 60MB)
 * 테스트 4 〉	통과 (0.05ms, 60.2MB)
 * 테스트 5 〉	통과 (0.05ms, 59.5MB)
 * 테스트 6 〉	통과 (0.06ms, 61.1MB)
 * 테스트 7 〉	통과 (0.07ms, 59.5MB)
 * 테스트 8 〉	통과 (0.07ms, 59.7MB)
 * 테스트 9 〉	실패 (0.10ms, 58.8MB)
 * 테스트 10 〉	통과 (0.09ms, 60.6MB)
 * 테스트 11 〉	실패 (0.21ms, 61.2MB)
 * 테스트 12 〉	실패 (0.41ms, 60.4MB)
 * 테스트 13 〉	통과 (0.40ms, 61.2MB)
 * 테스트 14 〉	실패 (0.72ms, 62.9MB)
 * 테스트 15 〉	실패 (0.70ms, 61MB)
 * 테스트 16 〉	통과 (0.02ms, 60.6MB)
 * 테스트 17 〉	통과 (0.02ms, 59.9MB)
 * 테스트 18 〉	통과 (0.03ms, 57.5MB)
 * 테스트 19 〉	통과 (0.04ms, 60.4MB)
 * 테스트 20 〉	실패 (0.12ms, 59.5MB)
 * 테스트 21 〉	통과 (0.09ms, 59.5MB)
 * 테스트 22 〉	통과 (0.61ms, 62.4MB)
 * 테스트 23 〉	실패 (0.62ms, 62.3MB)
 * 테스트 24 〉	통과 (0.64ms, 62.3MB)
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      arrayOf("korean", "11:40", "30"),
      arrayOf("english", "12:10", "20"),
      arrayOf("math", "12:30", "40"),
    )
  ), arrayOf("korean", "english", "math"))

  validate(s.solution(
    arrayOf(
      arrayOf("science", "12:40", "50"),
      arrayOf("music", "12:20", "40"),
      arrayOf("history", "14:00", "30"),
      arrayOf("computer", "12:30", "100"),
    )
  ), arrayOf("science", "history", "computer", "music"))

  validate(s.solution(
    arrayOf(
      arrayOf("aaa", "12:00", "20"),
      arrayOf("bbb", "12:10", "30"),
      arrayOf("ccc", "12:40", "10"),
    )
  ), arrayOf("bbb", "ccc", "aaa"))
}

//println("[$i] ${p.contentToString()} -> $stt, $time")
//      println("[$t] $x1 | $cur, $rmnPt")

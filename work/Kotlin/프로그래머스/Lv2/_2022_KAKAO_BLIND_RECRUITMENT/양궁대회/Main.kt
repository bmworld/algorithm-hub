package 프로그래머스.Lv2._2022_KAKAO_BLIND_RECRUITMENT.양궁대회

import util.validate

class Solution {
  companion object {

    const val MAX_SCORE = 10
    const val ALL = MAX_SCORE + 1
  }

  fun solution(n: Int, info: IntArray): IntArray {
    var APEACH = 0
    val scores = IntArray(ALL)
    for (i in info.indices) {
      val score = MAX_SCORE - i
      scores[i] = if (info[i] > 0) {
        APEACH += score
        score * 2
      } else score
    }

    var RYAN = 0
    var best = 0
    val ch = IntArray(ALL)

    fun dfs(stt: Int, rmn: Int, acc: Int, flag: Int) {
      if (rmn == 0) {
        if (acc > RYAN || acc == RYAN && flag > best) {
          RYAN = acc
          best = flag
        }
        return
      }


      for (score in stt downTo 0) {
        val i = MAX_SCORE - score
        val reqCnt = if (score == 0) 0 else info[i] + 1
        if (reqCnt > rmn) continue
        val used = if (score == 0) rmn else reqCnt
        ch[i] = used

        dfs(score - 1,
          rmn - used,
          acc + scores[i],
          flag or (1 shl i)
        )
        ch[i] = 0
      }
    }

    dfs(MAX_SCORE, n, 0, 0)

    if (RYAN <= APEACH) return intArrayOf(-1)

    val ans = IntArray(ALL)
    var rmn = n
    while (best > 0) {
      val i = best.countTrailingZeroBits()
      ans[i] =
        if (MAX_SCORE - i == 0) rmn
        else (info[i] + 1).also { rmn -= it }
      best = best xor (1 shl i)
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.25ms, 59.7MB)
 * 테스트 2 〉	통과 (0.32ms, 59.7MB)
 * 테스트 3 〉	통과 (0.39ms, 60MB)
 * 테스트 4 〉	통과 (0.29ms, 59.4MB)
 * 테스트 5 〉	통과 (0.33ms, 58.3MB)
 * 테스트 6 〉	통과 (0.35ms, 60.2MB)
 * 테스트 7 〉	통과 (0.29ms, 57.2MB)
 * 테스트 8 〉	통과 (0.24ms, 58.8MB)
 * 테스트 9 〉	통과 (0.34ms, 59.5MB)
 * 테스트 10 〉	통과 (0.29ms, 59.5MB)
 *
 *
 *
 * [RIVAL 1]
 * class Solution {
 *     var max = -55
 *     var result = IntArray(11) { 0 }
 *
 *     fun solution(n: Int, info: IntArray): IntArray {
 *         max = -55
 *         result = IntArray(11) { 0 }
 *         recursiveFunction(n, n, 0, 0, 0, result, info)
 *         return if (max <= 0) intArrayOf(-1)
 *         else result
 *     }
 *
 *     fun recursiveFunction(
 *         n: Int,
 *         left: Int,
 *         myScore: Int,
 *         rivalScore: Int,
 *         index: Int,
 *         current: IntArray,
 *         info: IntArray
 *     ) {
 *         if (index == 10) {
 *             val temp = current.clone()
 *             temp[index] += left
 *             if (max < myScore - rivalScore) {
 *                 max = myScore - rivalScore
 *                 result = temp
 *             } else if (max == myScore - rivalScore) {
 *                 for (i in 10 downTo 0) {
 *                     if (result[i] < temp[i]) result = temp
 *                     else if (result[i] > temp[i]) break
 *                 }
 *             }
 *             return
 *         }
 *         val need = info[index] + 1
 *         val score = 10 - index
 *
 *         if (left >= need) {
 *             val myNextScore = myScore + score
 *             current[index] += need
 *             recursiveFunction(n, left - need, myNextScore, rivalScore, index + 1, current, info)
 *             current[index] -= need
 *         }
 *
 *         val rivalNextScore = if (info[index] == 0) rivalScore else rivalScore + score
 *         recursiveFunction(n, left, myScore, rivalNextScore, index + 1, current, info)
 *     }
 * }
 * 테스트 1 〉	통과 (0.04ms, 60.2MB)
 * 테스트 2 〉	통과 (0.37ms, 60.7MB)
 * 테스트 3 〉	통과 (0.19ms, 59.5MB)
 * 테스트 4 〉	통과 (0.16ms, 59.4MB)
 * 테스트 5 〉	통과 (0.31ms, 61MB)
 * 테스트 6 〉	통과 (0.19ms, 61.3MB)
 * 테스트 7 〉	통과 (0.11ms, 60.8MB)
 * 테스트 8 〉	통과 (0.07ms, 59.2MB)
 * 테스트 9 〉	통과 (0.12ms, 59.8MB)
 * 테스트 10 〉	통과 (0.06ms, 59.7MB)
 *
 *
 *
 * [RIVAL 2]
 * class Solution {
 *
 *     private fun ryanScore(ryan: IntArray, apeach: IntArray): Int {
 *         var apeachScore = 0
 *         var ryanScore = 0
 *         ryan.forEachIndexed { index, i ->
 *             if (apeach[index] >= i) {
 *                 if (apeach[index] != 0) apeachScore += 10 - index
 *             } else {
 *                 ryanScore += 10 - index
 *             }
 *         }
 *         return ryanScore - apeachScore
 *     }
 *
 *     private val maxArray: (MutableList<Int>, IntArray) -> MutableList<Int> = { old, new ->
 *         val oldScore = old.reduceIndexed { index, acc, i -> acc + (index) * i }
 *         val newScore = new.reduceIndexed { index, acc, i -> acc + (index) * i }
 *         if (oldScore < newScore) new.toMutableList() else old.toMutableList()
 *     }
 *
 *
 *     fun solution(n: Int, info: IntArray): IntArray {
 *         val answer = IntArray(11) { 0 }
 *         var gap = Pair(0, MutableList(11) { 0 })
 *
 *         fun fillArrow(arrow: Int, ryan: IntArray): IntArray {
 *             var remain = arrow
 *             for (i in 10 downTo 0) {
 *                 if (remain == 0) break
 *                 if (info[i] == 0) continue
 *                 while (remain != 0 && ryan[i] < info[i]) {
 *                     ryan[i] += 1
 *                     remain -= 1
 *                 }
 *             }
 *             return ryan
 *         }
 *
 *         fun dfs(x: Int, sum: Int) {
 *             if (x == 11) {
 *                 if (sum > n) return
 *                 val score = if (sum < n) ryanScore(fillArrow(n - sum, answer), info) else ryanScore(answer, info)
 *                 if (gap.first <= score) {
 *                     gap = if (score == gap.first) score to maxArray(gap.second,
 *                         answer) else score to answer.toMutableList()
 *                 }
 *                 return
 *             }
 *             answer[x] = info[x] + 1
 *             dfs(x + 1, sum + info[x] + 1)
 *             answer[x] = 0
 *             dfs(x + 1, sum)
 *         }
 *         dfs(0, 0)
 *         return if (gap.first == 0) intArrayOf(-1) else gap.second.toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (13.39ms, 64.2MB)
 * 테스트 2 〉	통과 (14.11ms, 63MB)
 * 테스트 3 〉	통과 (17.74ms, 62.8MB)
 * 테스트 4 〉	통과 (13.52ms, 63.4MB)
 * 테스트 5 〉	통과 (15.27ms, 61.3MB)
 * 테스트 6 〉	통과 (14.24ms, 63.8MB)
 * 테스트 7 〉	통과 (13.99ms, 62.3MB)
 * 테스트 8 〉	통과 (13.87ms, 61.8MB)
 * 테스트 9 〉	통과 (13.86ms, 63.8MB)
 * 테스트 10 〉	통과 (12.96ms, 63.8MB)
 *
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

  //
  validate(s.solution(10,
    intArrayOf(0, 0, 0, 0, 0, 0, 0, 4, 4, 2, 0)),
    intArrayOf(1, 1, 1, 1, 1, 1, 1, 0, 0, 3, 0)
  )

  validate(s.solution(10,
    intArrayOf(10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)),
    intArrayOf(0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
  )
}

//    println("RYAN = ${RYAN} vs APEACH= $APEACH --- best=${best.toString(2)}")
//           println(
//            "ch=${ch.contentToString()} -> cur (${flag.toString(2)}) vs best(${
//              best.toString(2)
//            }) | acc=$acc")

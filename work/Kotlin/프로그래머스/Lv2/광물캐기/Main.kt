package 프로그래머스.Lv2.광물캐기

import util.validate

class Solution {
  companion object {

    const val D = 100
    const val I = 10
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
      if (f >= D) {
        ans += f / D
        f %= D
      }

      if (f >= I) {
        ans += f / I
        f %= I
      }

      ans += f
    }


    while (iCnt-- > 0 && group > 0) {
      var f = fatigues[--group]
      if (f >= D) {
        ans += (f / D) * 5
        f %= D
      }

      if (f >= I) {
        ans += f / I
        f %= I
      }

      ans += f
    }


    while (sCnt-- > 0 && group > 0) {
      var f = fatigues[--group]
      if (f >= D) {
        ans += (f / D) * 25
        f %= D
      }

      if (f >= I) {
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
 * 테스트 1 〉	통과 (0.02ms, 59MB)
 * 테스트 2 〉	통과 (0.02ms, 60.6MB)
 * 테스트 3 〉	통과 (0.02ms, 59.7MB)
 * 테스트 4 〉	통과 (0.02ms, 60MB)
 * 테스트 5 〉	통과 (0.02ms, 59.9MB)
 * 테스트 6 〉	통과 (0.03ms, 60.6MB)
 * 테스트 7 〉	통과 (0.02ms, 60.5MB)
 * 테스트 8 〉	통과 (0.02ms, 58.6MB)
 * 테스트 9 〉	통과 (0.02ms, 60MB)
 * 테스트 10 〉	통과 (0.02ms, 61MB)
 * 테스트 11 〉	통과 (0.02ms, 60.9MB)
 * 테스트 12 〉	통과 (0.02ms, 60.1MB)
 * 테스트 13 〉	통과 (0.02ms, 60.5MB)
 * 테스트 14 〉	통과 (0.02ms, 60.7MB)
 * 테스트 15 〉	통과 (0.03ms, 59.9MB)
 *
 * [RIVAL]
 * class Solution {
 *     private fun solve(picks: IntArray, minerals: List<String>, fatigue: Int): Int {
 *         if (picks.sum() == 0 || minerals.isEmpty()) return fatigue
 *         return listOf(
 *                 mapOf("diamond" to 1, "iron" to 1, "stone" to 1),
 *                 mapOf("diamond" to 5, "iron" to 1, "stone" to 1),
 *                 mapOf("diamond" to 25, "iron" to 5, "stone" to 1),
 *         ).mapIndexed { index, fatigues ->
 *             if (picks[index] > 0) {
 *                 val nextPicks = picks.clone()
 *                 nextPicks[index] -= 1
 *                 solve(nextPicks, minerals.drop(5), fatigue + minerals.take(5).sumOf { fatigues.getOrDefault(it, 0) })
 *             } else 2147483647
 *         }.minOrNull()!!
 *     }
 *
 *     fun solution(picks: IntArray, minerals: Array<String>): Int {
 *         return solve(picks, minerals.asList(), 0)
 *     }
 * }
 * 테스트 1 〉	통과 (14.08ms, 65.2MB)
 * 테스트 2 〉	통과 (20.49ms, 65.6MB)
 * 테스트 3 〉	통과 (14.37ms, 64.5MB)
 * 테스트 4 〉	통과 (16.49ms, 64.1MB)
 * 테스트 5 〉	통과 (16.15ms, 64MB)
 * 테스트 6 〉	통과 (17.16ms, 65.2MB)
 * 테스트 7 〉	통과 (14.72ms, 64.3MB)
 * 테스트 8 〉	통과 (15.40ms, 64.3MB)
 * 테스트 9 〉	통과 (14.94ms, 63.4MB)
 * 테스트 10 〉	통과 (15.15ms, 64.3MB)
 * 테스트 11 〉	통과 (24.14ms, 123MB)
 * 테스트 12 〉	통과 (23.04ms, 66.2MB)
 * 테스트 13 〉	통과 (20.13ms, 66.2MB)
 * 테스트 14 〉	통과 (26.66ms, 66.9MB)
 * 테스트 15 〉	통과 (26.03ms, 64.6MB)
 *
 * [RIVAL 2]
 * import java.util.*
 * import kotlin.math.*
 * class Solution {
 *     fun solution(picks: IntArray, minerals: Array<String>): Int {
 *         var answer: Int = 0
 *         var n = 0
 *         var pickQ = PriorityQueue<Int>(Comparator.reverseOrder())
 *         var stoneQ = PriorityQueue<IntArray>{a, b ->
 *             b.sum() - a.sum()
 *         }
 *
 *         picks.forEachIndexed{ ind, a ->
 *             repeat(a){
 *                 pickQ.add((5).toDouble().pow(2-ind).toInt())
 *             }
 *         }
 *
 *         var tempArray = ArrayList<Int>()
 *         for(i in 0 until minerals.size){
 *             when(minerals[i][0]){
 *                 'd' ->{
 *                     tempArray.add(25)
 *                 }
 *                 'i' ->{
 *                     tempArray.add(5)
 *                 }
 *                 's' ->{
 *                     tempArray.add(1)
 *                 }
 *             }
 *             if(i%5 == 4){
 *                 stoneQ.add(tempArray.toIntArray())
 *                 tempArray.clear()
 *                 if(stoneQ.size == pickQ.size) break
 *             }
 *         }
 *         if(tempArray.size != 0){
 *             stoneQ.add(tempArray.toIntArray())
 *         }
 *
 *         while(stoneQ.isNotEmpty() && pickQ.isNotEmpty()){
 *             var stone  = stoneQ.poll()
 *             var pick = pickQ.poll()
 *             stone.forEach{
 *                 if(pick >= it){
 *                     answer += 1
 *                 }else{
 *                     answer += it / pick
 *                 }
 *             }
 *         }
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (6.46ms, 61.2MB)
 * 테스트 2 〉	통과 (15.15ms, 64.9MB)
 * 테스트 3 〉	통과 (15.64ms, 63.7MB)
 * 테스트 4 〉	통과 (16.80ms, 64.3MB)
 * 테스트 5 〉	통과 (14.62ms, 63.7MB)
 * 테스트 6 〉	통과 (15.04ms, 63.6MB)
 * 테스트 7 〉	통과 (14.70ms, 64.3MB)
 * 테스트 8 〉	통과 (14.68ms, 63.7MB)
 * 테스트 9 〉	통과 (16.14ms, 64.4MB)
 * 테스트 10 〉	통과 (14.84ms, 63.6MB)
 * 테스트 11 〉	통과 (14.94ms, 63.6MB)
 * 테스트 12 〉	통과 (14.16ms, 63.3MB)
 * 테스트 13 〉	통과 (20.47ms, 64.9MB)
 * 테스트 14 〉	통과 (15.21ms, 65.2MB)
 * 테스트 15 〉	통과 (15.22ms, 64.9MB)
 *
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

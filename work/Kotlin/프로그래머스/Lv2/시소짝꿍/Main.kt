package 프로그래머스.Lv2.시소짝꿍

import util.validate

class Solution {
  companion object {

    const val MAX = 1_000
    const val MIN = 100
  }

  fun solution(weights: IntArray): Long {

    var min = MAX
    var max = MIN
    val cnts = LongArray(MAX + 1)

    for (w in weights) cnts[w.also {
      if (it < min) min = it
      if (it > max) max = it
    }]++

    var ans = 0L

    for (w1 in min..max) {
      val c1 = cnts[w1]
      if (c1 == 0L) continue
      for (w2 in w1..max) {
        val c2 = cnts[w2]
        if (c2 == 0L) continue

        ans += when {
          w1 == w2 -> c1 * (c1 - 1) / 2
          else -> if (w1 * 3 == w2 * 2 || w1 * 4 == w2 * 2 || w1 * 4 == w2 * 3) c1 * c2 else 0
        }
      }
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.03ms, 58.9MB)
 * 테스트 2 〉	통과 (0.03ms, 60.3MB)
 * 테스트 3 〉	통과 (0.06ms, 60.8MB)
 * 테스트 4 〉	통과 (6.43ms, 59.9MB)
 * 테스트 5 〉	통과 (5.01ms, 61.2MB)
 * 테스트 6 〉	통과 (4.91ms, 62.6MB)
 * 테스트 7 〉	통과 (6.27ms, 61.1MB)
 * 테스트 8 〉	통과 (4.73ms, 62.6MB)
 * 테스트 9 〉	통과 (6.57ms, 63.6MB)
 * 테스트 10 〉	통과 (5.46ms, 64.2MB)
 * v2:
 * 테스트 1 〉	통과 (0.04ms, 57.3MB)
 * 테스트 2 〉	통과 (0.03ms, 60.4MB)
 * 테스트 3 〉	통과 (0.07ms, 60.1MB)
 * 테스트 4 〉	통과 (5.35ms, 61.8MB)
 * 테스트 5 〉	통과 (4.85ms, 60.8MB)
 * 테스트 6 〉	통과 (5.41ms, 60.9MB)
 * 테스트 7 〉	통과 (4.25ms, 62.5MB)
 * 테스트 8 〉	통과 (4.15ms, 63.5MB)
 * 테스트 9 〉	통과 (4.10ms, 63.3MB)
 * 테스트 10 〉	통과 (5.15ms, 64.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(weights: IntArray) =
 *         with(mutableMapOf<Int, MutableSet<Int>>()) {
 *             weights.mapIndexed { index, weight ->
 *                 if(containsKey(weight)) this[weight]?.add(index)
 *                 else this[weight] = mutableSetOf(index)
 *                 weight
 *             }.toSet().fold(0L) { result, num ->
 *                 val duplicate = this[num]!!.size.toLong()
 *                 val (two, three, four) = num.run { intArrayOf(times(2), times(3), times(4)) }
 *
 *                 var sum = this[four/2]?.size?.times(duplicate) ?: 0L
 *                 if(two % 3 == 0) sum += this[two/3]?.size?.times(duplicate) ?: 0
 *                 if(two % 4 == 0) sum += this[two/4]?.size?.times(duplicate) ?: 0
 *                 if(three % 2 == 0) sum += this[three/2]?.size?.times(duplicate) ?: 0
 *                 if(three % 4 == 0) sum += this[three/4]?.size?.times(duplicate) ?: 0
 *                 if(four % 3 == 0) sum += this[four/3]?.size?.times(duplicate) ?: 0
 *                 if(duplicate > 1) sum += (duplicate-1) * duplicate / 2
 *
 *                 remove(num)
 *                 result+sum
 *             }
 *         }
 * }
 * 테스트 1 〉	통과 (18.05ms, 64.5MB)
 * 테스트 2 〉	통과 (20.13ms, 64MB)
 * 테스트 3 〉	통과 (15.01ms, 64.8MB)
 * 테스트 4 〉	통과 (23.29ms, 67.3MB)
 * 테스트 5 〉	통과 (24.33ms, 69.3MB)
 * 테스트 6 〉	통과 (26.59ms, 71.2MB)
 * 테스트 7 〉	통과 (34.17ms, 73MB)
 * 테스트 8 〉	통과 (35.24ms, 75.9MB)
 * 테스트 9 〉	통과 (37.44ms, 79.1MB)
 * 테스트 10 〉	통과 (47.02ms, 82.6MB)
 * [RIVAL 2]
 * class Solution {
 *     fun solution(weights: IntArray): Long {
 *         var answer: Long = 0
 *         val multiplier: List<Int> = listOf(2, 3, 4)
 *         val divider: List<Int> = listOf(1, 2, 3)
 *         val map: HashMap<Int, Long> = hashMapOf()
 *
 *         weights.forEach {
 *             map[it] = (map[it] ?: 0) + 1L
 *         }
 *
 *         map.keys.forEach {
 *             if (map[it]!! > 1) {
 *                 var sum: Long = 0L
 *                 for (i in 1 until map[it]!!) {
 *                     sum += i
 *                 }
 *                 answer += sum
 *             }
 *             for (i in 0 until 3) {
 *                 if (it % divider[i] != 0) {
 *                     continue
 *                 }
 *                 answer += map[it]!! * (map[(it / divider[i]) * multiplier[i]] ?: 0L) * 1L
 *             }
 *         }
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (10.67ms, 62.6MB)
 * 테스트 2 〉	통과 (9.13ms, 63.1MB)
 * 테스트 3 〉	통과 (9.41ms, 63.2MB)
 * 테스트 4 〉	통과 (11.51ms, 65.9MB)
 * 테스트 5 〉	통과 (13.28ms, 65.9MB)
 * 테스트 6 〉	통과 (14.44ms, 67.4MB)
 * 테스트 7 〉	통과 (15.06ms, 67.5MB)
 * 테스트 8 〉	통과 (18.32ms, 67.8MB)
 * 테스트 9 〉	통과 (18.98ms, 71.2MB)
 * 테스트 10 〉	통과 (18.73ms, 72.2MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(intArrayOf(100, 180, 360, 100, 270)), 4
  )

}

//        println("-- $w1($c1) & $w2($c2) --> $pairs")

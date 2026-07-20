package 프로그래머스.Lv2.롤케이크자르기

import util.validate

class Solution {

  companion object {

    const val MAX = 10_000
    const val EMPTY = 0
  }

  fun solution(topping: IntArray): Int {
    var ans = 0

    val whole = IntArray(MAX + 1)
    var b = 0
    for (x in topping) if (whole[x]++ == EMPTY) b++

    var a = 0
    val aPart = IntArray(MAX + 1)
    for (i in 0 until topping.size - 1) {
      val x = topping[i]
      val aCnt = aPart[x]++ + 1
      if (aCnt == 1) a++
      if (aCnt == whole[x]) b--

      if (a == b) ans++
      else if (a > b) break
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.41ms, 61MB)
 * 테스트 2 〉	통과 (1.85ms, 64.8MB)
 * 테스트 3 〉	통과 (1.99ms, 63.1MB)
 * 테스트 4 〉	통과 (1.90ms, 64.9MB)
 * 테스트 5 〉	통과 (6.67ms, 90.9MB)
 * 테스트 6 〉	통과 (5.56ms, 99MB)
 * 테스트 7 〉	통과 (5.19ms, 99.2MB)
 * 테스트 8 〉	통과 (6.17ms, 99.2MB)
 * 테스트 9 〉	통과 (3.31ms, 99MB)
 * 테스트 10 〉	통과 (4.51ms, 98.2MB)
 * 테스트 11 〉	통과 (1.60ms, 63.5MB)
 * 테스트 12 〉	통과 (0.38ms, 62.3MB)
 * 테스트 13 〉	통과 (9.64ms, 99.1MB)
 * 테스트 14 〉	통과 (6.58ms, 97.4MB)
 * 테스트 15 〉	통과 (6.37ms, 101MB)
 * 테스트 16 〉	통과 (5.99ms, 100MB)
 * 테스트 17 〉	통과 (5.58ms, 99.8MB)
 * 테스트 18 〉	통과 (5.66ms, 97.7MB)
 * 테스트 19 〉	통과 (6.46ms, 97.3MB)
 * 테스트 20 〉	통과 (4.09ms, 98.8MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *
 *     private operator fun MutableMap<Int, Int>.plusAssign(topping: Int) {
 *         this[topping] = this.getOrDefault(topping, 0) + 1
 *     }
 *
 *     private operator fun MutableMap<Int, Int>.minusAssign(topping: Int) {
 *         val currentToppingCount = this[topping]!!
 *         when {
 *             currentToppingCount == 1 -> this.remove(topping)
 *             currentToppingCount > 1 -> this[topping] = currentToppingCount - 1
 *             else -> throw IllegalStateException()
 *         }
 *     }
 *
 *     fun solution(toppings: IntArray): Int {
 *         val toppingCounts = object {
 *             val left: MutableMap<Int, Int> = HashMap()
 *             val right: MutableMap<Int, Int> = HashMap()
 *         }
 *         for (topping in toppings) toppingCounts.right += topping
 *
 *         var fairCases = 0
 *         for (cutAfter in 0 until toppings.lastIndex) {
 *             val newToppingForLeft = toppings[cutAfter]
 *             toppingCounts.left += newToppingForLeft
 *             toppingCounts.right -= newToppingForLeft
 *
 *             if (toppingCounts.left.count() == toppingCounts.right.count()) fairCases++
 *         }
 *
 *         return fairCases
 *     }
 *
 * }
 * 테스트 1 〉	통과 (12.92ms, 65.1MB)
 * 테스트 2 〉	통과 (31.69ms, 78MB)
 * 테스트 3 〉	통과 (19.18ms, 71.8MB)
 * 테스트 4 〉	통과 (22.42ms, 72.6MB)
 * 테스트 5 〉	통과 (59.71ms, 140MB)
 * 테스트 6 〉	통과 (101.48ms, 204MB)
 * 테스트 7 〉	통과 (96.83ms, 193MB)
 * 테스트 8 〉	통과 (87.71ms, 179MB)
 * 테스트 9 〉	통과 (132.12ms, 173MB)
 * 테스트 10 〉	통과 (95.29ms, 176MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(topping: IntArray): Int {
 *         var cnt = 0
 *         val hm1 = HashMap<Int, Int>()
 *         val hm2 = HashMap<Int, Int>()
 *         for (top in topping) hm2[top] = hm2.getOrDefault(top, 0) + 1
 *
 *         for (i in topping.indices) {
 *             hm1[topping[i]] = hm1.getOrDefault(topping[i], 0) + 1
 *             hm2[topping[i]] = hm2.getOrDefault(topping[i], 0) - 1
 *             if (hm2.getOrDefault(topping[i], 0) == 0) hm2.remove(topping[i])
 *             if (hm1.size == hm2.size) cnt++
 *         }
 *         return cnt
 *     }
 * }
 * 테스트 1 〉	통과 (4.42ms, 61.5MB)
 * 테스트 2 〉	통과 (23.91ms, 76.2MB)
 * 테스트 3 〉	통과 (17.27ms, 66.8MB)
 * 테스트 4 〉	통과 (14.03ms, 68.7MB)
 * 테스트 5 〉	통과 (58.27ms, 137MB)
 * 테스트 6 〉	통과 (130.03ms, 218MB)
 * 테스트 7 〉	통과 (111.77ms, 207MB)
 * 테스트 8 〉	통과 (112.69ms, 189MB)
 * 테스트 9 〉	통과 (93.94ms, 189MB)
 * 테스트 10 〉	통과 (92.71ms, 187MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(1, 2, 1, 3, 1, 4, 1, 2)), 2)
  validate(s.solution(intArrayOf(1, 2, 3, 1, 4)), 0)
  validate(s.solution(intArrayOf(1, 1, 1)), 2)
}

//      println("[${i}] cur= $aCnt")

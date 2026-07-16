package 프로그래머스.Lv2.할인행사

import util.validate

class Solution {

  val PERIOD = 10
  val THRESHOLD_OF_SATISFIED = 0
  val THRESHOLD_OF_UNSATISFIED = 1
  fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
    val N = want.size

    val cnts = HashMap<String, Int>(N)
    for (i in 0 until N) cnts[want[i]] = number[i]

    var desires = N
    fun add(item: String) {
      cnts[item].also {
        if (it != null) cnts[item] = (it - 1).also { if (it == THRESHOLD_OF_SATISFIED) desires-- }
      }
    }

    fun remove(item: String) {
      cnts[item].also {
        if (it != null) cnts[item] = (it + 1).also { if (it == THRESHOLD_OF_UNSATISFIED) desires++ }
      }
    }

    var ans = 0
    fun ch() {
      if (desires == THRESHOLD_OF_SATISFIED) ans++
    }

    repeat(PERIOD) { i ->
      add(discount[i])
    }


    ch()

    for (i in PERIOD until discount.size) {
      remove(discount[i - PERIOD])
      add(discount[i])
      ch()
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (1.99ms, 72.8MB)
 * 테스트 2 〉	통과 (8.99ms, 87.1MB)
 * 테스트 3 〉	통과 (6.04ms, 72.7MB)
 * 테스트 4 〉	통과 (9.76ms, 89MB)
 * 테스트 5 〉	통과 (6.89ms, 80MB)
 * 테스트 6 〉	통과 (1.71ms, 61.8MB)
 * 테스트 7 〉	통과 (3.40ms, 76.8MB)
 * 테스트 8 〉	통과 (11.08ms, 97MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
 *         var answer: Int = 0
 *         val wantMap = mutableMapOf<String, Int>()
 *
 *         repeat(discount.size) {
 *             wantMap.clear()
 *             repeat(want.size) { wantMap[want[it]] = number[it] }
 *             var num = 0
 *             for(i in it until discount.size) {
 *                 val food = discount[i]
 *                 if(!wantMap.containsKey(food)) break
 *                 if(wantMap[food] == 0) break
 *                 wantMap[food] = wantMap[food]!! - 1
 *                 num ++
 *             }
 *             if (num == 10) answer ++
 *         }
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (7.70ms, 75.3MB)
 * 테스트 2 〉	통과 (34.69ms, 113MB)
 * 테스트 3 〉	통과 (15.00ms, 73MB)
 * 테스트 4 〉	통과 (24.05ms, 93.3MB)
 * 테스트 5 〉	통과 (13.39ms, 87.2MB)
 * 테스트 6 〉	통과 (6.48ms, 65MB)
 * 테스트 7 〉	통과 (11.16ms, 80.2MB)
 * 테스트 8 〉	통과 (20.86ms, 105MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf("banana", "apple", "rice", "pork", "pot"),
    intArrayOf(3, 2, 2, 2, 1),
    arrayOf("chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork",
      "rice", "pot", "banana", "apple", "banana")),
    3
  )
  validate(s.solution(
    arrayOf("apple"),
    intArrayOf(10),
    arrayOf("banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana",
      "banana", "banana")), 0
  )

  validate(s.solution(
    arrayOf("banana"),
    intArrayOf(10),
    arrayOf("banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana",
      "banana", "banana")), 1
  )

  validate(s.solution(
    arrayOf("banana"),
    intArrayOf(10),
    arrayOf("banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana",
      "banana", "banana", "banana")), 2
  )

  validate(s.solution(
    arrayOf("a", "b"),
    intArrayOf(2, 1),
    arrayOf(
      "a", "a", "a", "a", "a",
      "a", "a", "a", "a", "a",
      "b", "b", "a")), 3
  )

  validate(s.solution(
    arrayOf("k", "b"),
    intArrayOf(2, 1),
    arrayOf(
      "a", "a", "a", "a", "a",
      "a", "a", "a", "a", "a",
      "b", "b", "a")), 0
  )
}

//      println("desires = ${desires}, $ans")

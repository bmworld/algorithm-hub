package 프로그래머스.Lv0.커피심부름

import util.validate

class Solution {
  companion object {

    const val AMERICANO = 4500
    const val LATTE = 5000
    val menu = hashMapOf(
      "anything" to 1,
      "americano" to 1,
      "iceamericano" to 1,
      "americanoice" to 1,
      "hotamericano" to 1,
      "americanohot" to 1,
    )

  }

  fun solution(order: Array<String>): Int {
    var ans = 0
    for (o in order) ans += if (menu[o] != null) AMERICANO else LATTE
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.3MB)
 * 테스트 2 〉	통과 (0.02ms, 59.7MB)
 * 테스트 3 〉	통과 (0.02ms, 60.1MB)
 * 테스트 4 〉	통과 (0.02ms, 60.1MB)
 * 테스트 5 〉	통과 (0.02ms, 59MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(order: Array<String>) = order.map { if (it.contains("cafelatte")) 5000 else 4500 }.sum();
 * }
 * 테스트 1 〉	통과 (7.78ms, 60.6MB)
 * 테스트 2 〉	통과 (7.70ms, 61.2MB)
 * 테스트 3 〉	통과 (7.79ms, 62MB)
 * 테스트 4 〉	통과 (8.02ms, 60.6MB)
 * 테스트 5 〉	통과 (7.97ms, 60MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(order: Array<String>): Int {
 *         var answer: Int = 0
 *         val hm = HashMap<String, Int>()
 *         hm["iceamericano"] = 4500
 *         hm["americanoice"] = 4500
 *         hm["americanohot"] = 4500
 *         hm["hotamericano"] = 4500
 *         hm["icecafelatte"] = 5000
 *         hm["cafelatteice"] = 5000
 *         hm["hotcafelatte"] = 5000
 *         hm["cafelattehot"] = 5000
 *         hm["americano"] = 4500
 *         hm["cafelatte"] = 5000
 *         hm["anything"] = 4500
 *
 *         for (o in order) {
 *             answer += hm[o]!!
 *         }
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (0.03ms, 60.3MB)
 * 테스트 2 〉	통과 (0.03ms, 59.5MB)
 * 테스트 3 〉	통과 (0.03ms, 59MB)
 * 테스트 4 〉	통과 (0.04ms, 58.7MB)
 * 테스트 5 〉	통과 (0.04ms, 59.8MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf(
    "cafelatte", "americanoice", "hotcafelatte", "anything"
  )), 19_000)

  validate(s.solution(arrayOf(
    "americanoice", "americano", "iceamericano"
  )), 13_500)

  validate(s.solution(arrayOf(
    "cafelatte"
  )), 5_000)
}

package 프로그래머스.Lv1.가장많이받은선물

import util.validate

class Solution {

  fun solution(friends: Array<String>, gifts: Array<String>): Int {
    val N = friends.size
    val GAP = N

    val nameMap = HashMap<String, Int>(N)
    for (i in 0 until N) nameMap[friends[i]] = i

    val giftIndex = IntArray(N)
    val giftHistory = IntArray(N * GAP)
    fun pos(a: Int, b: Int): Int = a * GAP + b


    for (str in gifts) {
      val split = str.split(' ')
      val sndr = nameMap[split[0]]!!
      val rcvr = nameMap[split[1]]!!

      giftIndex[sndr]++
      giftIndex[rcvr]--

      giftHistory[pos(sndr, rcvr)]++
    }

    val giftCnt = IntArray(N)
    for (a in 0 until N)
      for (b in a + 1 until N) {
        val aSentCnt = giftHistory[pos(a, b)]
        val bSentCnt = giftHistory[pos(b, a)]

        when {
          aSentCnt > bSentCnt -> giftCnt[a]++
          aSentCnt < bSentCnt -> giftCnt[b]++
          else -> {
            val agi = giftIndex[a]
            val bgi = giftIndex[b]
            when {
              agi > bgi -> giftCnt[a]++
              agi < bgi -> giftCnt[b]++
            }
          }
        }
      }

    var ans = 0
    for (x in giftCnt) if (x > ans) ans = x
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (4.62ms, 60MB)
 * 테스트 2 〉	통과 (4.82ms, 60MB)
 * 테스트 3 〉	통과 (4.81ms, 60.5MB)
 * 테스트 4 〉	통과 (4.52ms, 60MB)
 * 테스트 5 〉	통과 (6.64ms, 60.4MB)
 * 테스트 6 〉	통과 (4.94ms, 60.4MB)
 * 테스트 7 〉	통과 (5.68ms, 59.9MB)
 * 테스트 8 〉	통과 (5.82ms, 61.2MB)
 * 테스트 9 〉	통과 (8.84ms, 64.1MB)
 * 테스트 10 〉	통과 (9.25ms, 65.3MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * import kotlin.math.*
 * class Solution {
 *     fun solution(friends: Array<String>, gifts: Array<String>): Int {
 *         val giftMap = gifts.groupingBy { it }.eachCount()
 *         val pIdxMap = friends.map { it to pIdx(it, friends, giftMap) }.toMap()
 *         return friends.map {
 *             numOfPresents(it, friends, giftMap, pIdxMap)
 *         }.max()
 *     }
 *
 *     fun pIdx(friend: String, friends: Array<String>, giftMap: Map<String, Int>): Int {
 *         val give = friends.sumBy { giftMap["$friend $it"] ?: 0 }
 *         val take = friends.sumBy { giftMap["$it $friend"] ?: 0 }
 *         return give - take
 *     }
 *
 *     fun numOfPresents(friend: String, friends: Array<String>, giftMap: Map<String, Int>, pIdxMap: Map<String, Int>): Int {
 *         return friends.count {
 *             val give = giftMap["$friend $it"] ?: 0
 *             val take = giftMap["$it $friend"] ?: 0
 *             give > take || (give == take && pIdxMap[friend]!! > pIdxMap[it]!!)
 *         }
 *     }
 *
 *     fun List<Int>.max(): Int {
 *         return reduce { max, d -> max(max, d) }
 *     }
 * }
 * 테스트 1 〉	통과 (5.96ms, 60MB)
 * 테스트 2 〉	통과 (8.81ms, 59.6MB)
 * 테스트 3 〉	통과 (9.35ms, 59.8MB)
 * 테스트 4 〉	통과 (6.65ms, 59.5MB)
 * 테스트 5 〉	통과 (24.68ms, 60.9MB)
 * 테스트 6 〉	통과 (6.52ms, 59.5MB)
 * 테스트 7 〉	통과 (8.10ms, 60.7MB)
 * 테스트 8 〉	통과 (10.06ms, 60.3MB)
 * 테스트 9 〉	통과 (8.32ms, 62.2MB)
 * 테스트 10 〉	통과 (11.60ms, 63.3MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf("muzi", "ryan", "frodo", "neo"),
    arrayOf("muzi frodo", "muzi frodo", "ryan muzi", "ryan muzi", "ryan muzi", "frodo muzi",
      "frodo ryan", "neo muzi")
  ), 2)

  validate(s.solution(
    arrayOf("joy", "brad", "alessandro", "conan", "david"),
    arrayOf("alessandro brad", "alessandro joy", "alessandro conan", "david alessandro",
      "alessandro david")
  ), 4)

  validate(s.solution(
    arrayOf("a", "b", "c"),
    arrayOf("a b", "b a", "c a", "a c", "a c", "c a")
  ), 0)

}

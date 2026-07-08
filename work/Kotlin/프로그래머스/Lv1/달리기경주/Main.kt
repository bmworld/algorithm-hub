package 프로그래머스.Lv1.달리기경주

import util.validate

class Solution {

  val TOP = 0
  fun solution(players: Array<String>, callings: Array<String>): Array<String> {
    val map = HashMap<String, Int>(players.size)
    for (i in players.indices) map[players[i]] = i

    for (cur in callings) {
      val rank = map[cur]!!
      if (rank > TOP) {
        val comp = players[rank - 1]

        players[rank - 1] = cur
        players[rank] = comp

        map[cur] = rank - 1
        map[comp] = rank
      }
    }


    return players
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.05ms, 57.5MB)
 * 테스트 2 〉	통과 (0.04ms, 58.2MB)
 * 테스트 3 〉	통과 (0.12ms, 60.5MB)
 * 테스트 4 〉	통과 (0.42ms, 60.3MB)
 * 테스트 5 〉	통과 (2.14ms, 61.4MB)
 * 테스트 6 〉	통과 (4.22ms, 68.2MB)
 * 테스트 7 〉	통과 (14.03ms, 85.8MB)
 * 테스트 8 〉	통과 (27.73ms, 104MB)
 * 테스트 9 〉	통과 (67.00ms, 130MB)
 * 테스트 10 〉	통과 (140.54ms, 233MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(players: Array<String>, callings: Array<String>): Array<String> {
 *         val playerIdxMap = hashMapOf<String, Int>()
 *
 *         players.forEachIndexed { i, p ->
 *             playerIdxMap.put(p, i)
 *         }
 *
 *         for (c in callings) {
 *             val idx = playerIdxMap[c]!!
 *
 *             players[idx] = players[idx-1]
 *             players[idx-1] = c
 *
 *             playerIdxMap.put(c, idx-1)
 *             playerIdxMap.put(players[idx], idx)
 *         }
 *
 *         return players
 *     }
 * }
 * 테스트 1 〉	통과 (0.03ms, 58.2MB)
 * 테스트 2 〉	통과 (0.04ms, 59.4MB)
 * 테스트 3 〉	통과 (0.09ms, 59.3MB)
 * 테스트 4 〉	통과 (0.44ms, 60.3MB)
 * 테스트 5 〉	통과 (1.44ms, 63.7MB)
 * 테스트 6 〉	통과 (2.69ms, 73.4MB)
 * 테스트 7 〉	통과 (13.56ms, 85.5MB)
 * 테스트 8 〉	통과 (28.48ms, 104MB)
 * 테스트 9 〉	통과 (47.98ms, 130MB)
 * 테스트 10 〉	통과 (131.68ms, 234MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(
      arrayOf("mumu", "soe", "poe", "kai", "mine"),
      arrayOf("kai", "kai", "mine", "mine"),
    ),
    arrayOf("mumu", "kai", "mine", "soe", "poe")
  )

}

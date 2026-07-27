package 프로그래머스.Lv2.오픈채팅방

import util.validate

class Solution {

  companion object {

    const val MAX_LEN = 10
    const val SEP = ' '
    const val OP_POS = 0
    const val inSuffix = "님이 들어왔습니다."
    const val outSuffix = "님이 나갔습니다."
  }

  fun solution(record: Array<String>): Array<String> {
    val N = record.size
    var len = 0
    val map = HashMap<String, String>()

    val tmpRecord = Array(N) { "" }
    for (i in 0 until N) {
      val str = record[i]
      val split = str.split(SEP)
      val id = split[1]

      when (val op = str[OP_POS]) {
        'E' -> {
          tmpRecord[len++] = op + id
          map[id] = split[2]
        }
        'L' -> tmpRecord[len++] = op + id
        'C' -> map[id] = split[2]
      }
    }

    return Array(len) {
      val str = tmpRecord[it]
      val op = str[OP_POS]
      val id = String(CharArray(str.length - 1) { str[it + 1] })
      map[id] + if (op == 'E') inSuffix else outSuffix
    }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (4.76ms, 61.1MB)
 * 테스트 2 〉	통과 (4.88ms, 61.1MB)
 * 테스트 3 〉	통과 (5.35ms, 61.2MB)
 * 테스트 4 〉	통과 (5.40ms, 61MB)
 * 테스트 5 〉	통과 (7.30ms, 62.3MB)
 * 테스트 6 〉	통과 (7.85ms, 62.6MB)
 * 테스트 7 〉	통과 (7.28ms, 62.9MB)
 * 테스트 8 〉	통과 (7.76ms, 63.1MB)
 * 테스트 9 〉	통과 (7.79ms, 63.5MB)
 * 테스트 10 〉	통과 (7.23ms, 62.7MB)
 * 테스트 11 〉	통과 (6.73ms, 62.3MB)
 * 테스트 12 〉	통과 (7.39ms, 62.3MB)
 * 테스트 13 〉	통과 (9.64ms, 62MB)
 * 테스트 14 〉	통과 (8.62ms, 62.4MB)
 * 테스트 15 〉	통과 (7.16ms, 62.6MB)
 * 테스트 16 〉	통과 (4.92ms, 61MB)
 * 테스트 17 〉	통과 (6.26ms, 61MB)
 * 테스트 18 〉	통과 (5.50ms, 61.5MB)
 * 테스트 19 〉	통과 (8.24ms, 62.2MB)
 * 테스트 20 〉	통과 (7.98ms, 62.1MB)
 * 테스트 21 〉	통과 (7.65ms, 62.5MB)
 * 테스트 22 〉	통과 (6.99ms, 63.3MB)
 * 테스트 23 〉	통과 (10.32ms, 62.1MB)
 * 테스트 24 〉	통과 (8.54ms, 61.9MB)
 * 테스트 25 〉	통과 (49.06ms, 141MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(record: Array<String>): Array<String> {
 *         val user = mutableMapOf<String, String>()
 *
 *     return record
 *             .map {
 *                 val r = it.split(" ")
 *                 val action = r.first()
 *                 when (action) {
 *                     "Enter", "Change" -> user += r[1] to r[2]
 *                 }
 *                 r
 *             }
 *             .asSequence()
 *             .filter { it[0] != "Change" }
 *             .map {
 *                 val nickName = user[it[1]]
 *                 val explanation = when (it[0]) {
 *                     "Enter" -> "님이 들어왔습니다."
 *                     "Leave" -> "님이 나갔습니다."
 *                     else -> throw IllegalArgumentException()
 *                 }
 *                 "$nickName$explanation"
 *             }
 *             .toList().toTypedArray()
 *     }
 * }
 * 테스트 1 〉	통과 (14.93ms, 62.6MB)
 * 테스트 2 〉	통과 (14.36ms, 61.2MB)
 * 테스트 3 〉	통과 (13.11ms, 63.2MB)
 * 테스트 4 〉	통과 (13.41ms, 62.8MB)
 * 테스트 5 〉	통과 (18.18ms, 63.6MB)
 * 테스트 6 〉	통과 (21.08ms, 64.1MB)
 * 테스트 7 〉	통과 (15.99ms, 64.2MB)
 * 테스트 8 〉	통과 (18.88ms, 65.2MB)
 * 테스트 9 〉	통과 (19.32ms, 65.1MB)
 * 테스트 10 〉	통과 (17.04ms, 63.3MB)
 * 테스트 11 〉	통과 (18.09ms, 63.1MB)
 * 테스트 12 〉	통과 (18.05ms, 64.1MB)
 * 테스트 13 〉	통과 (17.24ms, 64.4MB)
 * 테스트 14 〉	통과 (26.93ms, 63.6MB)
 * 테스트 15 〉	통과 (13.75ms, 63MB)
 * 테스트 16 〉	통과 (14.77ms, 62.7MB)
 * 테스트 17 〉	통과 (17.09ms, 62.9MB)
 * 테스트 18 〉	통과 (16.57ms, 62.9MB)
 * 테스트 19 〉	통과 (16.38ms, 64.9MB)
 * 테스트 20 〉	통과 (17.78ms, 64.8MB)
 * 테스트 21 〉	통과 (17.25ms, 64MB)
 * 테스트 22 〉	통과 (17.84ms, 62MB)
 * 테스트 23 〉	통과 (17.79ms, 64.5MB)
 * 테스트 24 〉	통과 (23.30ms, 63.4MB)
 * 테스트 25 〉	통과 (121.14ms, 148MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf("Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
      "Change uid4567 Ryan")
  ),
    arrayOf("Prodo님이 들어왔습니다.", "Ryan님이 들어왔습니다.", "Prodo님이 나갔습니다.", "Prodo님이 들어왔습니다.")
  )

}

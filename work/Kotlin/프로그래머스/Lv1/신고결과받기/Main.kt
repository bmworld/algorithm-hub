package 프로그래머스.Lv1.신고결과받기

import util.validate

class Solution {

  val SEP = ' '
  fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray {
    val N = id_list.size
    val idMapper = HashMap<String, Int>(N)
    for (i in 0 until N) idMapper[id_list[i]] = i

    val history = Array(N) { mutableSetOf<Int>() }

    for (str in report) {
      val s = str.split(SEP)
      val plaintiff = idMapper[s[0]]!!
      val defendant = idMapper[s[1]]!!

      history[defendant] += plaintiff
    }

    val mailingCnt = IntArray(N)
    for (defendant in 0 until N)
      if (history[defendant].size >= k)
        for (plaintiff in history[defendant]) mailingCnt[plaintiff]++

    return mailingCnt
  }
}

/**
 * ```
 * [ME]
 * v1:
 * 테스트 1 〉	통과 (4.85ms, 60.3MB)
 * 테스트 2 〉	통과 (4.65ms, 61.2MB)
 * 테스트 3 〉	통과 (106.87ms, 166MB)
 * 테스트 4 〉	통과 (4.88ms, 60.2MB)
 * 테스트 5 〉	통과 (4.85ms, 60.4MB)
 * 테스트 6 〉	통과 (7.00ms, 61.5MB)
 * 테스트 7 〉	통과 (9.03ms, 65.2MB)
 * 테스트 8 〉	통과 (12.49ms, 78.8MB)
 * 테스트 9 〉	통과 (54.13ms, 123MB)
 * 테스트 10 〉	통과 (53.33ms, 123MB)
 * v2:
 * 테스트 1 〉	통과 (4.93ms, 60.6MB)
 * 테스트 2 〉	통과 (5.12ms, 61MB)
 * 테스트 3 〉	통과 (85.93ms, 167MB)
 * 테스트 4 〉	통과 (4.91ms, 59.9MB)
 * 테스트 5 〉	통과 (5.12ms, 59MB)
 * 테스트 6 〉	통과 (7.96ms, 62.4MB)
 * 테스트 7 〉	통과 (8.98ms, 65.7MB)
 * 테스트 8 〉	통과 (14.33ms, 76.1MB)
 * 테스트 9 〉	통과 (58.06ms, 124MB)
 * 테스트 10 〉	통과 (45.45ms, 123MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(id_list: Array<String>, report: Array<String>, k: Int): IntArray =
 *     report.map { it.split(" ") }
 *         .groupBy { it[1] }
 *         .asSequence()
 *         .map { it.value.distinct() }
 *         .filter { it.size >= k }
 *         .flatten()
 *         .map { it[0] }
 *         .groupingBy { it }
 *         .eachCount()
 *         .run { id_list.map { getOrDefault(it, 0) }.toIntArray() }
 * }
 * 테스트 1 〉	통과 (16.13ms, 62.9MB)
 * 테스트 2 〉	통과 (17.98ms, 63.4MB)
 * 테스트 3 〉	통과 (120.90ms, 165MB)
 * 테스트 4 〉	통과 (18.98ms, 63.7MB)
 * 테스트 5 〉	통과 (20.37ms, 61.8MB)
 * 테스트 6 〉	통과 (21.79ms, 64.3MB)
 * 테스트 7 〉	통과 (25.45ms, 71.3MB)
 * 테스트 8 〉	통과 (43.22ms, 80.6MB)
 * 테스트 9 〉	통과 (87.07ms, 128MB)
 * 테스트 10 〉	통과 (127.36ms, 129MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(
      arrayOf("muzi", "frodo", "apeach", "neo"),
      arrayOf("muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"),
      2
    ),
    intArrayOf(2, 1, 1, 0)
  )

  validate(
    s.solution(
      arrayOf("con", "ryan"),
      arrayOf("ryan con", "ryan con", "ryan con", "ryan con"),
      3
    ),
    intArrayOf(0, 0)
  )

}

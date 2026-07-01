package 프로그래머스.Lv1.대충만든자판

import util.validate

class Solution {

  val A = 65
  val ALPHABETS = 26
  val IMPOSSIBLE = -1
  val INF = Int.MAX_VALUE
  fun solution(keymap: Array<String>, targets: Array<String>): IntArray {
    val cnts = IntArray(ALPHABETS) { INF }
    for (str in keymap) {
      for (i in 0 until str.length) {
        val x = str[i].code - A
        val cnt = i + 1
        if (cnt < cnts[x]) cnts[x] = cnt
      }
    }

    val ans = IntArray(targets.size)
    for (i in 0 until targets.size) {
      val str = targets[i]
      var sum = 0
      for (j in 0 until str.length) {
        val x = str[j].code - A
        val cnt = cnts[x]
        if (cnt == INF) {
          sum = IMPOSSIBLE
          break
        } else sum += cnt
      }
      ans[i] = sum
    }
    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.07ms, 59.3MB)
 * 테스트 2 〉	통과 (0.03ms, 59.3MB)
 * 테스트 3 〉	통과 (0.03ms, 59.8MB)
 * 테스트 4 〉	통과 (0.04ms, 58.1MB)
 * 테스트 5 〉	통과 (0.03ms, 58.1MB)
 * 테스트 6 〉	통과 (0.04ms, 58.7MB)
 * 테스트 7 〉	통과 (0.03ms, 60.3MB)
 * 테스트 8 〉	통과 (0.07ms, 58.6MB)
 * 테스트 9 〉	통과 (0.03ms, 58.9MB)
 * 테스트 10 〉	통과 (0.03ms, 59.3MB)
 * 테스트 11 〉	통과 (0.02ms, 57.8MB)
 * 테스트 12 〉	통과 (0.01ms, 57.8MB)
 * 테스트 13 〉	통과 (0.02ms, 57.8MB)
 * 테스트 14 〉	통과 (0.14ms, 59.4MB)
 * 테스트 15 〉	통과 (0.16ms, 59.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(keymap: Array<String>, targets: Array<String>): IntArray =
 *         targets.map { str ->
 *             str.map { c -> keymap.map { it.indexOf(c) + 1 }
 *                 .filterNot { it < 1 }
 *                 .let { list ->
 *                     if (list.isEmpty()) -1
 *                     else list.minOf { it }
 *                 }
 *             }.let { if ( it.contains(-1)) -1 else it.sum() }
 *         }.toIntArray()
 * }
 * 테스트 1 〉	통과 (11.23ms, 60.7MB)
 * 테스트 2 〉	통과 (10.19ms, 60.2MB)
 * 테스트 3 〉	통과 (9.95ms, 60.9MB)
 * 테스트 4 〉	통과 (10.40ms, 61.2MB)
 * 테스트 5 〉	통과 (8.92ms, 61.2MB)
 * 테스트 6 〉	통과 (9.27ms, 60.8MB)
 * 테스트 7 〉	통과 (9.25ms, 62MB)
 * 테스트 8 〉	통과 (8.93ms, 61.3MB)
 * 테스트 9 〉	통과 (10.83ms, 59.3MB)
 * 테스트 10 〉	통과 (11.23ms, 59.8MB)
 * 테스트 11 〉	통과 (8.58ms, 59.9MB)
 * 테스트 12 〉	통과 (9.90ms, 59.4MB)
 * 테스트 13 〉	통과 (8.14ms, 60.9MB)
 * 테스트 14 〉	통과 (29.04ms, 69.2MB)
 * 테스트 15 〉	통과 (40.95ms, 72.7MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf("ABACD", "BCEFD"), arrayOf("ABCD", "AABB")), intArrayOf(9, 4))
  validate(s.solution(arrayOf("AA"), arrayOf("B")), intArrayOf(-1))
  validate(s.solution(arrayOf("AGZ", "BSSS"), arrayOf("ASA", "BGZ")), intArrayOf(4, 6))
}

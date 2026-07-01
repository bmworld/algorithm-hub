package 프로그래머스.Lv1.로또의최고순위와최저순위

import util.validate

class Solution {

  val TOTAL = 45
  fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
    val ch = IntArray(TOTAL + 1)
    for (x in lottos) ch[x]++

    var matched = 0
    for (x in win_nums) if (ch[x] > 0) matched++

    val min = minOf(7 - matched, 6)
    val max = maxOf(min - ch[0], 1)
    return intArrayOf(max, min)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60MB)
 * 테스트 2 〉	통과 (0.01ms, 59.5MB)
 * 테스트 3 〉	통과 (0.02ms, 58.3MB)
 * 테스트 4 〉	통과 (0.02ms, 59MB)
 * 테스트 5 〉	통과 (0.01ms, 58MB)
 * 테스트 6 〉	통과 (0.02ms, 58.3MB)
 * 테스트 7 〉	통과 (0.02ms, 59.6MB)
 * 테스트 8 〉	통과 (0.02ms, 60.2MB)
 * 테스트 9 〉	통과 (0.02ms, 58.3MB)
 * 테스트 10 〉	통과 (0.03ms, 58.2MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(lottos: IntArray, winNums: IntArray): IntArray {
 *         return intArrayOf(
 *                 (lottos.size.plus(1)) - lottos.filter { winNums.contains(it) || it == 0 }.size,
 *                 (lottos.size.plus(1)) - lottos.filter(winNums::contains).size
 *         ).map { if (it > 6) it - 1 else it }.toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (13.26ms, 61.6MB)
 * 테스트 2 〉	통과 (16.40ms, 62.6MB)
 * 테스트 3 〉	통과 (13.49ms, 63.2MB)
 * 테스트 4 〉	통과 (12.76ms, 63MB)
 * 테스트 5 〉	통과 (14.13ms, 63.2MB)
 * 테스트 6 〉	통과 (12.28ms, 64.1MB)
 * 테스트 7 〉	통과 (12.57ms, 63.3MB)
 * 테스트 8 〉	통과 (12.40ms, 63.7MB)
 * 테스트 9 〉	통과 (15.14ms, 63.4MB)
 * 테스트 10 〉	통과 (18.27ms, 62.2MB)
 * 테스트 11 〉	통과 (12.73ms, 63.8MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(44, 1, 0, 0, 31, 25), intArrayOf(31, 10, 45, 1, 6, 19)),
    intArrayOf(3, 5))
  validate(s.solution(intArrayOf(0, 0, 0, 0, 0, 0), intArrayOf(38, 19, 20, 40, 15, 25)),
    intArrayOf(1, 6))
  validate(s.solution(intArrayOf(45, 4, 35, 20, 3, 9), intArrayOf(20, 9, 3, 45, 4, 35)),
    intArrayOf(1, 1))
}

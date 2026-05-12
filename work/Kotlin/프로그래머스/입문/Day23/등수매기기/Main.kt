package 프로그래머스.입문.Day23.등수매기기

import util.validate

class Solution {

  fun solution(score: Array<IntArray>): IntArray {
    val len = score.size

    var ans = IntArray(len)
    val a = IntArray(len) { score[it][0] + score[it][1] }

    for (i in 0 until len) {
      val n = a[i]
      var rank = 1
      for (j in 0 until len) {
        if (i == j) continue
        val m = a[j]
        if (n < m) rank++
      }
      ans[i] = rank
    }

    return ans
  }
}

fun main() {
  val s = Solution()
  validate(s.solution(arrayOf(
    intArrayOf(80, 70),
    intArrayOf(90, 50),
    intArrayOf(40, 70),
    intArrayOf(50, 80),
  )),
    intArrayOf(1, 2, 4, 3))

  validate(s.solution(arrayOf(
    intArrayOf(80, 70),
    intArrayOf(70, 80),
    intArrayOf(30, 50),
    intArrayOf(90, 100),
    intArrayOf(100, 90),
    intArrayOf(100, 100),
    intArrayOf(10, 30),
  )),
    intArrayOf(4, 4, 6, 2, 2, 1, 7))

}

/**
 * ME
 * 테스트 1 〉	통과 (0.01ms, 63.8MB)
 * 테스트 2 〉	통과 (0.02ms, 61.8MB)
 * 테스트 3 〉	통과 (0.01ms, 62.6MB)
 * 테스트 4 〉	통과 (0.01ms, 62.4MB)
 * 테스트 5 〉	통과 (0.01ms, 62.5MB)
 * 테스트 6 〉	통과 (0.02ms, 62.7MB)
 * 테스트 7 〉	통과 (0.01ms, 62.8MB)
 * 테스트 8 〉	통과 (0.02ms, 62.5MB)
 * 테스트 9 〉	통과 (0.02ms, 61.7MB)
 * 테스트 10 〉	통과 (0.02ms, 62.4MB)
 * 테스트 11 〉	통과 (0.02ms, 61.7MB)
 * 테스트 12 〉	통과 (0.04ms, 63.5MB)
 *
 * RIVAL
 * class Solution {
 *     fun solution(score: Array<IntArray>): IntArray {
 *         return score.map(IntArray::average).map { score.map(IntArray::average).sortedDescending().indexOf(it) + 1 }.toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (14.26ms, 66.7MB)
 * 테스트 2 〉	통과 (14.45ms, 66.3MB)
 * 테스트 3 〉	통과 (14.96ms, 65.6MB)
 * 테스트 4 〉	통과 (13.94ms, 65.8MB)
 * 테스트 5 〉	통과 (16.53ms, 66.1MB)
 * 테스트 6 〉	통과 (19.18ms, 66.5MB)
 * 테스트 7 〉	통과 (17.94ms, 66.6MB)
 * 테스트 8 〉	통과 (15.01ms, 66.4MB)
 * 테스트 9 〉	통과 (13.72ms, 66.7MB)
 * 테스트 10 〉	통과 (15.49ms, 66.9MB)
 * 테스트 11 〉	통과 (13.86ms, 66.8MB)
 * 테스트 12 〉	통과 (15.13ms, 66.4MB)
 *
 */

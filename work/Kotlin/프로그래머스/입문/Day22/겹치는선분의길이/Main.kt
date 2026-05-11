package 프로그래머스.입문.Day22.겹치는선분의길이

class Solution {

  fun solution(lines: Array<IntArray>): Int {
    var ans = 0

    val l1 = lines[0]
    val l2 = lines[1]
    val l3 = lines[2]

    val points = sortedSetOf(l1[0], l1[1], l2[0], l2[1], l3[0], l3[1])
    var prv = -1
    for (cur in points) {
      val m = cur.toDouble() - 0.5
      if (
        inRange(m, l1) && inRange(m, l2)
        || inRange(m, l1) && inRange(m, l3)
        || inRange(m, l2) && inRange(m, l3)
      ) ans += cur - prv

      prv = cur
    }

    return ans
  }

  fun inRange(m: Double, range: IntArray): Boolean = m >= range[0] && m <= range[1]
}

fun main() {
  val s = Solution()

  check(s.solution(
    arrayOf(
      intArrayOf(2, 3),
      intArrayOf(4, 6),
      intArrayOf(1, 2)
    )
  )
    == 0)


  check(s.solution(
    arrayOf(
      intArrayOf(0, 1),
      intArrayOf(2, 5),
      intArrayOf(3, 9)
    )
  )
    == 2)

  check(s.solution(
    arrayOf(
      intArrayOf(-1, 1),
      intArrayOf(1, 3),
      intArrayOf(3, 9)
    )
  )
    == 0)

  check(s.solution(
    arrayOf(
      intArrayOf(0, 5),
      intArrayOf(3, 9),
      intArrayOf(1, 10)
    )
  )
    == 8)

}

/**
 * AS IS
 * 테스트 1 〉	통과 (10.48ms, 65.7MB)
 * 테스트 2 〉	통과 (10.73ms, 65.3MB)
 * 테스트 3 〉	통과 (10.43ms, 65.5MB)
 * 테스트 4 〉	통과 (10.26ms, 66.2MB)
 * 테스트 5 〉	통과 (10.22ms, 65.3MB)
 * 테스트 6 〉	통과 (10.44ms, 65.7MB)
 * 테스트 7 〉	통과 (10.43ms, 66.7MB)
 * 테스트 8 〉	통과 (10.09ms, 65.4MB)
 * 테스트 9 〉	통과 (10.10ms, 65.6MB)
 * 테스트 10 〉	통과 (9.89ms, 66MB)
 */

package 프로그래머스.입문.Day6.짝수홀수개수

class Solution {

  fun solution(a: IntArray): IntArray {
    var even = 0
    var odd = 0

    fun ch(v: Int) {
      if (v % 2 == 0) even++ else odd++
    }

    val len = a.size
    repeat((len + 1) / 2) {
      ch(a[it])
      val opp = len - 1 - it
      if (it != opp) ch(a[opp])
    }

    return intArrayOf(even, odd)
  }
}

fun main() {
  val s = Solution()

  val r1 = s.solution(intArrayOf(1, 2, 3, 4, 5))
  check(r1[0] == 2)
  check(r1[1] == 3)

  val r2 = s.solution(intArrayOf(1, 2, 2, 1000))
  check(r2[0] == 3)
  check(r2[1] == 1)

}

/**
 * 테스트 1 〉	통과 (0.17ms, 64.2MB)
 * 테스트 2 〉	통과 (0.27ms, 62.6MB)
 * 테스트 3 〉	통과 (0.38ms, 63.5MB)
 * 테스트 4 〉	통과 (0.18ms, 63.1MB)
 * 테스트 5 〉	통과 (0.17ms, 63.3MB)
 */

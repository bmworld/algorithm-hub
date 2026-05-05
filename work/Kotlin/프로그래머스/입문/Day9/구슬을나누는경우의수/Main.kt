package 프로그래머스.입문.Day9.구슬을나누는경우의수

class Solution {

  fun solution(balls: Int, share: Int): Int {
    val cnt = minOf(share, balls - share)

    var ans = 1L
    repeat(cnt) {
      val a = balls - it
      val b = it + 1
      ans = ans * a / b
    }

    return ans.toInt()
  }
}

fun main() {
  val s = Solution()
  check(s.solution(1, 1).also { println(it) } == 1)
  check(s.solution(3, 1).also { println(it) } == 3)
  check(s.solution(3, 2).also { println(it) } == 3)
  check(s.solution(3, 3).also { println(it) } == 1)
  check(s.solution(5, 3).also { println(it) } == 10)
  check(s.solution(11, 3).also { println(it) } == 165)
  check(s.solution(11, 8).also { println(it) } == 165)
  check(s.solution(30, 30).also { println(it) } == 1)
  check(s.solution(30, 15).also { println(it) } == 155117520)
}

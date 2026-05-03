package 프로그래머스.입문.Day4.피자나눠먹기2

class Solution {

  val slices = 6
  fun solution(n: Int): Int = ((slices * n) / getGCD(slices, n)) / slices
  fun getGCD(a: Int, b: Int): Int = if (b == 0) a else getGCD(b, a % b)
}

fun main() {
  val s = Solution()
  check(s.solution(6) == 1)
  check(s.solution(10) == 5)
  check(s.solution(4) == 2)
  check(s.solution(7) == 7)
}

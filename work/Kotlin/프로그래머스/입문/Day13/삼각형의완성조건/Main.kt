package 프로그래머스.입문.Day13.삼각형의완성조건

class Solution {

  fun solution(sides: IntArray): Int {
    val a = sides[0]
    val b = sides[1]
    val c = sides[2]
    val sum = a + b + c
    val max = maxOf(a, b, c)
    return if (sum > 2 * max) 1 else 2
  }
}

fun main() {
  val s = Solution()
  check(s.solution(intArrayOf(1, 2, 3)) == 2)
  check(s.solution(intArrayOf(3, 6, 2)) == 2)
  check(s.solution(intArrayOf(199, 72, 222)) == 1)
}

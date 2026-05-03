package 프로그래머스.입문.Day2.분수의덧셈

class Solution {

  fun solution(n1: Int, d1: Int, n2: Int, d2: Int): IntArray {
    val lcm = (d1 * d2) / getGCD(d1, d2)
    val d = n1 * (lcm / d1) + n2 * (lcm / d2)
    val gcd = getGCD(d, lcm)
    return intArrayOf(d / gcd, lcm / gcd)
  }
}

fun getGCD(a: Int, b: Int): Int = if (b == 0) a else getGCD(b, a % b)


fun main() {
  val s = Solution()

  val a1 = s.solution(1, 2, 3, 4)
  check(a1[0] == 5 && a1[1] == 4) {
    "expected 5/4 but got ${a1[0]}/${a1[1]}"
  }

  val a2 = s.solution(9, 2, 1, 3)
  check(a2[0] == 29 && a2[1] == 6) {
    "expected 29/6 but got ${a2[0]}/${a2[1]}"
  }

  val a3 = s.solution(1, 2, 1, 1)
  check(a3[0] == 3 && a3[1] == 2) {
    "expected 3/2 but got ${a3[0]}/${a3[1]}"
  }

  val a4 = s.solution(1, 4, 1, 4)
  check(a4[0] == 1 && a4[1] == 2) {
    "expected 1/2 but got ${a4[0]}/${a4[1]}"
  }
}

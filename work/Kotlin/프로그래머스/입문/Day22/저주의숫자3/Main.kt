package 프로그래머스.입문.Day22.저주의숫자3

class Solution {

  val CURSED_NUM = 3
  fun solution(n: Int): Int {

    var ans = 1
    var num = 1
    while (num++ < n) {
      while (isCursed(++ans)) {
      }
    }
    return ans
  }

  fun isCursed(ans: Int): Boolean = ans % CURSED_NUM == 0 || hasDigit(ans)

  fun hasDigit(n: Int, digit: Int = CURSED_NUM): Boolean {
    var x = n
    while (x > 0) {
      if (x % 10 == digit) return true
      x /= 10
    }
    return false
  }
}

fun main() {
  val s = Solution()
  check(s.solution(1) == 1)
  check(s.solution(8) == 11)
  check(s.solution(9) == 14)
  check(s.solution(10) == 16)
  check(s.solution(15) == 25)
  check(s.solution(40) == 76)
}

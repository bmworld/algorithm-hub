package 프로그래머스.입문.Day12.숨어있는숫자의덧셈

class Solution {

  val ZERO = 48
  fun solution(str: String): Int {
    val len = str.length
    var ans = 0
    for (i in 0 until len) {
      val ch = str[i]
      val x = ch.code - ZERO
      if (x in 0..9) ans += x
    }
    return ans
  }
}

fun main() {
  val s = Solution()
  check(s.solution("aAb1B2cC34oOp") == 10)
  check(s.solution("1a2b3c4d123") == 16)
}

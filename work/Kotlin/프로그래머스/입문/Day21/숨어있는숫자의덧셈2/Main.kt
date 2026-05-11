package 프로그래머스.입문.Day21.숨어있는숫자의덧셈2

class Solution {

  val ZERO = 48
  val NUM = ZERO..ZERO + 9
  fun solution(str: String): Int {
    var ans = 0

    var x = 0
    for (i in 0 until str.length) {
      val code = str[i].code
      when (code) {
        in NUM -> x = x * 10 + code - ZERO
        else -> {
          ans += x
          x = 0
        }

      }
    }

    if (str[str.length - 1].code in NUM) ans += x
    return ans
  }
}

fun main() {
  val s = Solution()
  check(s.solution("aAb1B2cC34oOp") == 37)
  check(s.solution("1a2b3c4d123Z") == 133)
  check(s.solution("abb10dd90") == 100)
}

package 프로그래머스.입문.Day20.다항식더하기

class Solution {

  val X = 'x'.code
  val PLUS = '+'.code
  val SPACE = 32
  val ZERO = 48
  val NUM = ZERO..ZERO + 9
  fun solution(str: String): String {
    var a = 0
    var b = 0

    val len = str.length
    var num = 0
    for (i in 0..len) {
      if (i == len) {
        if (str[i - 1].code == X) a += maxOf(1, num) else b += num
        break
      }

      val code = str[i].code
      when (code) {
        PLUS, X -> continue
        SPACE -> {
          if (str[i - 1].code == X) a += maxOf(1, num) else b += num
          num = 0
        }
        in NUM -> num = num * 10 + code - ZERO
      }
    }

    val p1 = if (a == 0) "" else if (a > 1) "${a}x" else "x"
    val op = if (a > 0 && b > 0) " + " else ""
    val p2 = if (b > 0) b else ""
    return p1 + op + p2
  }
}

fun main() {
  val s = Solution()
  check(s.solution("3x + 7 + x") == "4x + 7")
  check(s.solution("x") == "x")
  check(s.solution("x + 1 + 2") == "x + 3")
  check(s.solution("x + x + x") == "3x")
  check(s.solution("123 + 3") == "126")
  check(s.solution("123 + 3x") == "3x + 123")
}

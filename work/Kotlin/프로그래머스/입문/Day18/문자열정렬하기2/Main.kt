package 프로그래머스.입문.Day18.문자열정렬하기2

class Solution {

  private val a = 97
  private val A = 65
  private val ALPHABETS = 26
  fun solution(str: String): String {
    val ch = IntArray(ALPHABETS)
    for (s in str) {
      val c = s.code
      ch[when {
        c >= a -> c - a
        c >= A -> c - A
        else -> break
      }]++
    }
    val ans = CharArray(str.length)
    var i = 0
    repeat(ALPHABETS) {
      var cnt = ch[it]
      while (cnt-- > 0) ans[i++] = (it + a).toChar()
    }

    return ans.concatToString()
  }
}

fun main() {
  val s = Solution()
  check(s.solution("Bcad") == "abcd")
  check(s.solution("Python") == "hnopty")
}

package 프로그래머스.입문.Day15.한번만등장한문자

class Solution {

  val ALPHABET_CNT = 26
  val a = 97
  fun solution(s: String): String {
    val ch = IntArray(ALPHABET_CNT)
    for (x in s) ch[x.code - a]++

    val answer = CharArray(ALPHABET_CNT)
    var i = 0
    repeat(ALPHABET_CNT) {
      if (ch[it] == 1) answer[i++] = (it + a).toChar()
    }
    return answer.concatToString(0, i)
  }
}

fun main() {
  val s = Solution()
  check(s.solution("abcabcadc") == "d")
  check(s.solution("abcd") == "abcd")
}

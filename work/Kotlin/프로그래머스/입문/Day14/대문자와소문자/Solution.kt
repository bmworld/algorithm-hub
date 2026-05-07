package 프로그래머스.입문.Day14.대문자와소문자

class Solution {

  val a = 97
  val z = 122
  val lowerCase = a..z
  val toLowerCase = 32
  val toUpperCase = -32
  fun solution(str: String): String {
    val a = CharArray(str.length) {
      val ch = str[it]
      val code = ch.code
      (code + if (code in lowerCase) toUpperCase else toLowerCase).toChar()
    }
    return a.concatToString()
  }
}

fun main() {
  val s = Solution()
  check(s.solution("cccCCC") == "CCCccc")
  check(s.solution("abCdEfghIJ") == "ABcDeFGHij")
}

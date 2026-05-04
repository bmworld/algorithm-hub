package 프로그래머스.입문.Day7.특정문자제거하기

class Solution {

  fun solution(str: String, letter: String): String {
    val arr = CharArray(str.length)
    var i = 0
    val t = letter.toCharArray()[0]
    for (s in str) if (s != t) arr[i++] = s
    return arr.concatToString(0, i)
  }
}

fun main() {
  val s = Solution()
  check(s.solution("abcdef", "f") == "abcde")
  check(s.solution("BCBdbe", "B") == "Cdbe")
}

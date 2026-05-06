package 프로그래머스.입문.Day12.모음제거

class Solution {

  val vowel = charArrayOf('a', 'e', 'i', 'o', 'u')
  fun solution(my_string: String): String {
    val len = my_string.length
    val ans = CharArray(len)

    var cnt = 0
    for (i in 0 until len) {
      val ch = my_string[i]
      if (ch !in vowel) ans[cnt++] = ch
    }
    return ans.concatToString(0, cnt).also { println(it) }
  }
}

fun main() {
  val s = Solution()
  check(s.solution("bus") == "bs")
  check(s.solution("nice to meet you") == "nc t mt y")
}

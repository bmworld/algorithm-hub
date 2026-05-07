package 프로그래머스.입문.Day13.중복된문자제거

class Solution {

  fun solution(str: String): String {
    val ch = LinkedHashSet<Char>()
    val ans = CharArray(str.length)
    var i = 0
    for (s in str) if (ch.add(s)) ans[i++] = s
    return ans.concatToString(0, i)
  }
}

fun main() {
  val s = Solution()
  check(s.solution("We are the world") == "We arthwold")
}

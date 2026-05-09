package 프로그래머스.입문.Day18.문자열안에문자열

class Solution {

  fun solution(s1: String, s2: String): Int = if (s1.contains(s2)) 1 else 2
}

fun main() {
  val s = Solution()
  check(s.solution("ab6CDE443fgh22iJKlmn1o", "6CD") == 1)
  check(s.solution("ppprrrogrammers", "pppp") == 2)
  check(s.solution("ppprrrogrammers", "ppp") == 1)
  check(s.solution("AbcAbcA", "AAA") == 2)
}

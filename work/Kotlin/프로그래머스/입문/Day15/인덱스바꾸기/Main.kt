package 프로그래머스.입문.Day15.인덱스바꾸기

class Solution {

  fun solution(s: String, n1: Int, n2: Int): String {
    val ans = s.toCharArray()
    val tmp = ans[n1]
    ans[n1] = ans[n2]
    ans[n2] = tmp
    return ans.concatToString()
  }
}

fun main() {
  val s = Solution()
  check(s.solution("hello", 1, 2) == "hlelo")
  check(s.solution("I love you", 3, 6) == "I l veoyou")
}

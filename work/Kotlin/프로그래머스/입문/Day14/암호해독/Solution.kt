package 프로그래머스.입문.Day14.암호해독

class Solution {

  fun solution(cipher: String, code: Int): String =
    CharArray(cipher.length / code) { cipher[(code - 1) + it * code] }.concatToString()
}

fun main() {
  val s = Solution()
  check(s.solution("dfjardstddetckdaccccdegk", 4) == "attack")
  check(s.solution("pfqallllabwaoclk", 2) == "fallback")
}

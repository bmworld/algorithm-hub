package 프로그래머스.입문.Day9.가위바위보

class Solution {

  fun solution(letter: String): String {
    val len = letter.length
    val ans = CharArray(len)
    repeat(len) {
      val x = letter[it]
      ans[it] = when (x) {
        '2' -> '0'
        '0' -> '5'
        else -> '2'
      }
    }
    return ans.concatToString()
  }
}

fun main() {
  val s = Solution()
  check(s.solution("2").also { println(it) } == "0")
  check(s.solution("205").also { println(it) } == "052")
}

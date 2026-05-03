package 프로그래머스.입문.Day6.뒤집힌문자열

class Solution {

  fun solution(str: String): String {
    val len = str.length
    val arr = str.toCharArray()
    repeat(len / 2) { i ->
      val opp = len - 1 - i
      val tmp = arr[i]
      arr[i] = arr[opp]
      arr[opp] = tmp
    }
    return arr.concatToString()
  }
}

fun main() {
  val s = Solution()

  check(s.solution("jaron") == "noraj")
  check(s.solution("bread") == "daerb")
}

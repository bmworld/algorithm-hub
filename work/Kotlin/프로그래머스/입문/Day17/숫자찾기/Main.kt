package 프로그래머스.입문.Day17.숫자찾기

class Solution {

  fun solution(num: Int, k: Int): Int {
    val str = num.toString()
    val len = str.length
    for (i in 0 until len) if (str[i].code - 48 == k) return i + 1
    return -1
  }
}

fun main() {
  val s = Solution()
  check(s.solution(29183, 1) == 3)
  check(s.solution(232443, 4) == 4)
  check(s.solution(123456, 7) == -1)
}

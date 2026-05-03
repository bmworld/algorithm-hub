package 프로그래머스.입문.Day5.옷가게할인받기

class Solution {

  fun solution(price: Int): Int {
    return price * when {
      price >= 500_000 -> 80
      price >= 300_000 -> 90
      price >= 100_000 -> 95
      else -> 100
    } / 100
  }
}

fun main() {
  val s = Solution()
  check(s.solution(100) == 100)
  check(s.solution(150_000) == 142_500)
  check(s.solution(300_100) == 270_090)
  check(s.solution(580_000) == 464_000)
}

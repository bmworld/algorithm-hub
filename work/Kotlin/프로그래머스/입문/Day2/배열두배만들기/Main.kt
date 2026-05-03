package 프로그래머스.입문.Day2.배열두배만들기

class Solution {

  fun solution(arr: IntArray): IntArray {
    repeat(arr.size) {
      arr[it] *= 2
    }
    return arr
  }
}

fun main() {
  val s = Solution()

  val arr = intArrayOf(1, 2, 3, 4, -198, 1000)
  val ans = s.solution(arr.clone())
  for (i in 0 until arr.size) check(ans[i] == arr[i] * 2)
}

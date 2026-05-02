package 프로그래머스.입문.짝수는싫어요

class Solution {

  fun solution(n: Int): IntArray = IntArray((n + 1) / 2) { 1 + it * 2 }
}

fun main() {
  val s = Solution()
  val arr = s.solution(15)
  for (i in 0 until arr.size) {
    check(arr[i] == 1 + i * 2)
  }

}

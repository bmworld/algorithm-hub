package 프로그래머스.입문.Day5.배열뒤집기

class Solution {

  fun solution(arr: IntArray): IntArray {
    val size = arr.size
    val end = size - 1
    repeat(size / 2) { i ->
      val tmp = arr[i]
      arr[i] = arr[end - i]
      arr[end - i] = tmp
    }
    return arr
  }
}

fun main() {
  val s = Solution()
  val a = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
  val b = s.solution(a.clone())
  repeat(a.size / 2 + 1) {
    check(a[it] == b[a.size - 1 - it])
  }

}

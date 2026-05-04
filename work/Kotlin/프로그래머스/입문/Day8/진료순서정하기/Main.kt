package 프로그래머스.입문.Day8.진료순서정하기

class Solution {

  fun solution(arr: IntArray): IntArray {
    val len = arr.size

    var ans = IntArray(len)
    for (i in 0 until len) {
      var rank = 1
      val a = arr[i]
      for (j in 0 until len) {
        if (i == j) continue
        if (a < arr[j]) rank++
      }
      ans[i] = rank
    }
    return ans
  }
}

fun main() {
  val s = Solution()
  val act = s.solution(intArrayOf(30, 10, 23, 6, 100))
  val exp = intArrayOf(2, 4, 3, 5, 1)
  for (i in 0 until act.size) {
    check(act[i] == exp[i])
  }

}

package 프로그래머스.입문.Day10.배열회전시키기

class Solution {

  fun solution(a: IntArray, dir: String): IntArray {
    val len = a.size
    val ans = IntArray(len)
    val movedCnt = len - 1
    val end = len - 1
    when (dir) {
      "left" -> {
        val t = a[0]
        System.arraycopy(a, 1, ans, 0, movedCnt)
        ans[end] = t
      }
      else -> {
        val t = a[end]
        System.arraycopy(a, 0, ans, 1, movedCnt)
        ans[0] = t
      }
    }
    return ans
  }
}

fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(1, 2, 3), "right"), intArrayOf(3, 1, 2))
  validate(s.solution(intArrayOf(1, 2, 3), "left"), intArrayOf(2, 3, 1))

}

fun validate(actual: IntArray, expect: IntArray) {
  repeat(actual.size) {
    check(actual[it] == expect[it])
  }
}

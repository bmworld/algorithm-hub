package 프로그래머스.입문.Day15.약수구하기

class Solution {

  val min = 1
  fun solution(n: Int): IntArray {

    val ans = mutableListOf<Int>()
    ans += min

    if (n > min) {
      var d = 2
      while (d <= n / d) {
        if (n % d == 0) ans += d
        d++
      }

      val len = ans.size
      repeat(len) {
        val i = len - (it + 1)
        val x = ans[i]
        if (x * x != n) ans += n / x
      }
    }
    return ans.toIntArray()
  }
}

fun main() {
  val s = Solution()
  val act = s.solution(24)
  val exp = intArrayOf(1, 2, 3, 4, 6, 8, 12, 24)
  repeat(exp.size) {
    check(act[it] == exp[it])
  }

  val act2 = s.solution(8)
  val exp2 = intArrayOf(1, 2, 4, 8)
  repeat(exp2.size) {
    check(act2[it] == exp2[it])
  }
}

package 프로그래머스.입문.Day15.n의배수고르기

class Solution {

  fun solution(n: Int, arr: IntArray): IntArray {
    var i = 0
    for (k in arr) if (k % n == 0) arr[i++] = k
    val ans = IntArray(i)
    System.arraycopy(arr, 0, ans, 0, i)
    return ans
  }
}

fun main() {
  val s = Solution()
  val act = s.solution(3, intArrayOf(4, 5, 6, 7, 8, 9, 10, 11, 12).clone())
  val exp = intArrayOf(6, 9, 12)
  check(act.size == (exp.size).also {
    repeat(it) { i ->
      check(act[i] == exp[i])
    }
  })

  val act2 = s.solution(5, intArrayOf(1, 9, 3, 10, 13, 5).clone())
  val exp2 = intArrayOf(10, 5)
  check(act2.size == (exp2.size).also {
    repeat(it) { i ->
      check(act2[i] == exp2[i])
    }
  })

  val a3 = s.solution(12, intArrayOf(2, 100, 120, 600, 12, 12).clone())
  val e3 = intArrayOf(120, 600, 12, 12)
  check(a3.size == (e3.size).also {
    repeat(it) { i ->
      check(a3[i] == e3[i])
    }
  })

}

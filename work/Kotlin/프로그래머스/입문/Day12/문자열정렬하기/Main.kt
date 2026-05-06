package 프로그래머스.입문.Day12.문자열정렬하기

import java.util.*

class Solution {

  val ZERO = 48
  fun solution(my_string: String): IntArray {
    val len = my_string.length
    val nums = PriorityQueue<Int>()

    var cnt = 0
    for (i in 0 until len) {
      val ch = my_string[i]
      val x = ch.code - ZERO
      if (x in 0..9) {
        nums.add(x)
        cnt++
      }
    }
    val ans = IntArray(cnt)
    var i = 0
    while (nums.isNotEmpty()) ans[i++] = nums.poll()
    return ans
  }
}

fun main() {
  val s = Solution()
  val act = s.solution("hi12392")
  val exp = intArrayOf(1, 2, 2, 3, 9)
  repeat(act.size) {
    check(exp[it] == act[it])
  }

  val act2 = s.solution("p2o4i8gj2")
  val exp2 = intArrayOf(2, 2, 4, 8)
  repeat(act2.size) {
    check(exp2[it] == act2[it])
  }
}

/**
 * 테스트 1 〉	통과 (0.33ms, 62.6MB)
 * 테스트 2 〉	통과 (0.34ms, 63.7MB)
 * 테스트 3 〉	통과 (0.40ms, 62.9MB)
 * 테스트 4 〉	통과 (0.50ms, 64MB)
 */

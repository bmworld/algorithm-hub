package 프로그래머스.입문.Day8.배열자르기

class Solution {

  fun solution(arr: IntArray, num1: Int, num2: Int): IntArray {
    val len = num2 - num1 + 1
    var ans = IntArray(len)
    System.arraycopy(arr, num1, ans, 0, len)
    return ans
  }
}

fun main() {
  val s = Solution()
  val arr = intArrayOf(1, 2, 3, 4, 5, 8, 1023, 33)
  val fr = 3
  val to = arr.size - 1
  val ans = s.solution(arr, fr, to)
  var j = 0
  for (i in fr..to) check(arr[i] == ans[j++].also { println("it = ${it}") })
}

/**
 * 테스트 1 〉	통과 (0.02ms, 64MB)
 * 테스트 2 〉	통과 (0.02ms, 63.1MB)
 * 테스트 3 〉	통과 (0.01ms, 64MB)
 * 테스트 4 〉	통과 (0.02ms, 64.3MB)
 * 테스트 5 〉	통과 (0.01ms, 63.6MB)
 *
 */

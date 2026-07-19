package 프로그래머스.Lv2.뒤에있는큰수찾기

import util.validate

class Solution {

  companion object {

    val EMPTY = -1

  }

  fun solution(numbers: IntArray): IntArray {
    val N = numbers.size

    val ans = IntArray(N)
    val tracer = HashMap<Int, Int>()

    repeat(N) {
      val i = N - (it + 1)
      val a = numbers[i]

      var pos = N
      var x = EMPTY
      for ((b, j) in tracer) {
        if (b > a && j < pos) {
          x = b
          pos = j
        }
      }

      ans[i] = x
      tracer[a] = i
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.06ms, 59.5MB)
 * 테스트 2 〉	통과 (0.04ms, 60.7MB)
 * 테스트 3 〉	통과 (0.05ms, 58.9MB)
 * 테스트 4 〉	통과 (0.69ms, 59.9MB)
 * 테스트 5 〉	통과 (23.12ms, 60.3MB)
 * 테스트 6 〉	통과 (425.73ms, 62.2MB)
 * 테스트 7 〉	통과 (486.22ms, 64.3MB)
 * 테스트 8 〉	통과 (3980.20ms, 74MB)
 * 테스트 9 〉	통과 (3874.04ms, 75.1MB)
 * 테스트 10 〉	통과 (8947.30ms, 88.4MB)
 * 테스트 11 〉	통과 (9168.38ms, 86.5MB)
 * 테스트 12 〉	실패 (시간 초과)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(2, 3, 3, 5)), intArrayOf(3, 5, 5, -1))
  validate(s.solution(intArrayOf(9, 1, 5, 3, 6, 2)), intArrayOf(-1, 5, 6, 6, -1, -1))
}

//          println("x,j = $b, $j")

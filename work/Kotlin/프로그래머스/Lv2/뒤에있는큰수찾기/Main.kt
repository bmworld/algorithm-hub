package 프로그래머스.Lv2.뒤에있는큰수찾기

import util.validate

class Solution {

  companion object {

    val EMPTY = -1

  }

  fun solution(numbers: IntArray): IntArray {
    val N = numbers.size

    val ans = IntArray(N) { EMPTY }

    repeat(N) {
      val i = N - (it + 1)
      val a = numbers[i]
      for (j in i + 1 until N) {
        val b = numbers[j]
        if (a < b) {
          ans[i] = b
          break
        }
      }
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.01ms, 60.5MB)
 * 테스트 2 〉	통과 (0.01ms, 59.1MB)
 * 테스트 3 〉	통과 (0.01ms, 60.9MB)
 * 테스트 4 〉	통과 (0.02ms, 61.5MB)
 * 테스트 5 〉	통과 (0.13ms, 60.1MB)
 * 테스트 6 〉	통과 (1.53ms, 62.7MB)
 * 테스트 7 〉	통과 (1.32ms, 63.4MB)
 * 테스트 8 〉	통과 (2.57ms, 70.8MB)
 * 테스트 9 〉	통과 (2.61ms, 71.2MB)
 * 테스트 10 〉	통과 (4.62ms, 78.4MB)
 * 테스트 11 〉	통과 (5.75ms, 78.1MB)
 * 테스트 12 〉	통과 (6.04ms, 92.1MB)
 * 테스트 13 〉	통과 (8.08ms, 91.3MB)
 * 테스트 14 〉	통과 (14.81ms, 139MB)
 * 테스트 15 〉	통과 (26.26ms, 178MB)
 * 테스트 16 〉	통과 (28.61ms, 178MB)
 * 테스트 17 〉	통과 (32.92ms, 180MB)
 * 테스트 18 〉	통과 (25.51ms, 178MB)
 * 테스트 19 〉	통과 (24.07ms, 177MB)
 * 테스트 20 〉	실패 (시간 초과)
 * 테스트 21 〉	실패 (시간 초과)
 * 테스트 22 〉	실패 (시간 초과)
 * 테스트 23 〉	실패 (시간 초과)
 * 테스트 24 〉	통과 (0.02ms, 60.6MB)
 * 테스트 25 〉	통과 (0.02ms, 59.6MB)
 * 테스트 26 〉	통과 (0.02ms, 59.8MB)
 * 테스트 27 〉	통과 (0.01ms, 60.6MB)
 * 테스트 28 〉	통과 (0.01ms, 60.6MB)
 * 테스트 29 〉	통과 (0.02ms, 60.5MB)
 * 테스트 30 〉	통과 (0.01ms, 60MB)
 * 테스트 31 〉	통과 (0.01ms, 59.2MB)
 * 테스트 32 〉	통과 (0.01ms, 59.5MB)
 * 테스트 33 〉	통과 (0.01ms, 57.9MB)
 * 테스트 34 〉	통과 (0.01ms, 57.9MB)
 * 테스트 35 〉	통과 (0.01ms, 59.6MB)
 * 테스트 36 〉	통과 (0.01ms, 60.7MB)
 * 테스트 37 〉	통과 (0.01ms, 61.1MB)
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

//      println("i = ${i}, $r, $c")

// 해당 항목의 마지막 위치
// 해당 항목의 큰수
// 뒤에서 앞으로 오면서 갱신하는거지

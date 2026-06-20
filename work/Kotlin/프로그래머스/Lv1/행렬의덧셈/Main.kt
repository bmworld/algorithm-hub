package 프로그래머스.Lv1.행렬의덧셈

import util.validate

class Solution {

  fun solution(a: Array<IntArray>, b: Array<IntArray>): Array<IntArray> {
    val rLen = a.size
    val cLen = a[0].size
    var answer = Array<IntArray>(rLen) { r ->
      IntArray(cLen) { c ->
        a[r][c] + b[r][c]
      }
    }

    return answer
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 57.5MB)
 * 테스트 2 〉	통과 (0.03ms, 59.8MB)
 * 테스트 3 〉	통과 (0.05ms, 58.7MB)
 * 테스트 4 〉	통과 (0.03ms, 58.6MB)
 * 테스트 5 〉	통과 (0.03ms, 58.6MB)
 * 테스트 6 〉	통과 (0.03ms, 60.7MB)
 * 테스트 7 〉	통과 (0.01ms, 58.5MB)
 * 테스트 8 〉	통과 (0.02ms, 58.7MB)
 * 테스트 9 〉	통과 (0.19ms, 61.3MB)
 * 테스트 10 〉	통과 (0.14ms, 61.5MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      intArrayOf(1, 2),
      intArrayOf(2, 3),
    ),
    arrayOf(
      intArrayOf(3, 4),
      intArrayOf(5, 6),
    )
  ), arrayOf(
    intArrayOf(4, 6),
    intArrayOf(7, 9),
  ))
}

//        println("[$r][$c] = ${a[r][c] + b[r][c]}")

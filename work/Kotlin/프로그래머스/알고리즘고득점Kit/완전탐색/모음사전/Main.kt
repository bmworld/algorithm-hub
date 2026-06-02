package 프로그래머스.알고리즘고득점Kit.완전탐색.모음사전

import util.validate

class Solution {

  val VOWELS = 5
  val MAX_LEN = 5
  val unit = IntArray(MAX_LEN).also {
    repeat(MAX_LEN) { i ->
      val j = MAX_LEN - (i + 1)
      it[j] = 1 + VOWELS * (if (j + 1 < MAX_LEN) it[j + 1] else 0)
    }
  }

  fun solution(word: String): Int {
    var answer = 0
    for (i in 0 until word.length) {
      val prvX = getX(word[i]) - 1
      answer += 1 + prvX * unit[i]
    }

    return answer
  }

  fun getX(char: Char): Int = when (char) {
    'A' -> 1
    'E' -> 2
    'I' -> 3
    'O' -> 4
    else -> 5
  }
}

/**
 * ```
 * [ME]
 * v1:
 * 테스트 1 〉	통과 (0.01ms, 59.6MB)
 * 테스트 2 〉	통과 (0.02ms, 58.4MB)
 * 테스트 3 〉	통과 (0.01ms, 59.2MB)
 * 테스트 4 〉	통과 (0.01ms, 58.7MB)
 * 테스트 5 〉	통과 (0.01ms, 58.3MB)
 * 테스트 6 〉	통과 (0.02ms, 57.7MB)
 * 테스트 7 〉	통과 (0.01ms, 59.5MB)
 * 테스트 8 〉	통과 (0.01ms, 58.5MB)
 * 테스트 9 〉	통과 (0.02ms, 58.6MB)
 * 테스트 10 〉	통과 (0.04ms, 56.9MB)
 * 테스트 11 〉	통과 (0.02ms, 58.1MB)
 * 테스트 12 〉	통과 (0.01ms, 58MB)
 * 테스트 13 〉	통과 (0.02ms, 58.9MB)
 * 테스트 14 〉	통과 (0.02ms, 59.5MB)
 * 테스트 15 〉	통과 (0.01ms, 59.4MB)
 * 테스트 16 〉	통과 (0.01ms, 59.3MB)
 * 테스트 17 〉	통과 (0.01ms, 59.2MB)
 * 테스트 18 〉	통과 (0.01ms, 58.9MB)
 * 테스트 19 〉	통과 (0.01ms, 58.6MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(word: String): Int {
 *         val list = listOf('A', 'E', 'I', 'O', 'U')
 *         return word.mapIndexed { i, w ->
 *             getSum(5 - i) * list.indexOf(w)
 *         }.sum() + word.length
 *     }
 *
 *     // 등비 급수 (S_n)
 *     private fun getSum(n: Int) = (((5.0).pow(n) - 1) / (5 - 1)).toInt()
 * }
 * 테스트 1 〉	통과 (9.84ms, 63.3MB)
 * 테스트 2 〉	통과 (10.12ms, 63.3MB)
 * 테스트 3 〉	통과 (10.39ms, 63.3MB)
 * 테스트 4 〉	통과 (9.89ms, 63.5MB)
 * 테스트 5 〉	통과 (9.73ms, 63MB)
 * 테스트 6 〉	통과 (9.92ms, 63.1MB)
 * 테스트 7 〉	통과 (9.66ms, 63.5MB)
 * 테스트 8 〉	통과 (9.85ms, 63.2MB)
 * 테스트 9 〉	통과 (10.09ms, 62.5MB)
 * 테스트 10 〉	통과 (9.74ms, 62.8MB)
 * 테스트 11 〉	통과 (10.63ms, 63MB)
 * 테스트 12 〉	통과 (9.93ms, 62.5MB)
 * 테스트 13 〉	통과 (10.29ms, 63.2MB)
 * 테스트 14 〉	통과 (9.82ms, 63.8MB)
 * 테스트 15 〉	통과 (9.83ms, 63.3MB)
 * 테스트 16 〉	통과 (9.89ms, 63.2MB)
 * 테스트 17 〉	통과 (13.96ms, 63.3MB)
 * 테스트 18 〉	통과 (9.88ms, 63.6MB)
 * 테스트 19 〉	통과 (10.67ms, 62.7MB)
 * 테스트 20 〉	통과 (10.38ms, 63.3MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("AAAAE"), 6)
  validate(s.solution("AAAE"), 10)
  validate(s.solution("I"), 1563)
  validate(s.solution("EIO"), 1189)
}

//      println("[$i] 1 + $prvChar * ${unit[i]} --> $answer")

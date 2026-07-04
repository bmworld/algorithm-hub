package 프로그래머스.Lv1.성격유형검사하기

import util.validate

class Solution {

  val NEUTRALITY = 4
  val CHARACTERS = 4
  fun solution(survey: Array<String>, choices: IntArray): String {
    val scores = IntArray(CHARACTERS)
    for (i in survey.indices) {
      val char = survey[i]
      var score = choices[i] - NEUTRALITY

      val c1 = char[0]
      val c2 = char[1]
      scores[when {
        c1 == 'R' || c2 == 'R' -> {
          if (c1 == 'R') score *= -1
          0
        }
        c1 == 'C' || c2 == 'C' -> {
          if (c1 == 'C') score *= -1
          1
        }
        c1 == 'J' || c2 == 'J' -> {
          if (c1 == 'J') score *= -1
          2
        }
        c1 == 'A' || c2 == 'A' -> {
          if (c1 == 'A') score *= -1
          3
        }
        else -> throw IllegalArgumentException("INDEX: $i ($c1, $c2)")
      }] += score
    }

    val ans = CharArray(CHARACTERS)
    ans[0] = if (scores[0] >= 0) 'R' else 'T'
    ans[1] = if (scores[1] >= 0) 'C' else 'F'
    ans[2] = if (scores[2] >= 0) 'J' else 'M'
    ans[3] = if (scores[3] >= 0) 'A' else 'N'

    return String(ans)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.6MB)
 * 테스트 2 〉	통과 (0.01ms, 59.6MB)
 * 테스트 3 〉	통과 (0.02ms, 58.8MB)
 * 테스트 4 〉	통과 (0.02ms, 59.7MB)
 * 테스트 5 〉	통과 (0.05ms, 59.6MB)
 * 테스트 6 〉	통과 (0.02ms, 58.1MB)
 * 테스트 7 〉	통과 (0.02ms, 58.2MB)
 * 테스트 8 〉	통과 (0.02ms, 58.1MB)
 * 테스트 9 〉	통과 (0.03ms, 58.3MB)
 * 테스트 10 〉	통과 (0.02ms, 58.3MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 * fun solution(survey: Array<String>, choices: IntArray): String {
 *         val scoreMap = mutableMapOf("RT" to 0, "CF" to 0, "JM" to 0, "AN" to 0)
 *
 *         survey.forEachIndexed { index, key ->
 *             if (scoreMap.keys.contains(key)) {
 *                 scoreMap[key] = scoreMap[key]!! + choices[index] - 4
 *             } else {
 *                 scoreMap[key.reversed()] = scoreMap[key.reversed()]!! - (choices[index] - 4)
 *             }
 *         }
 *
 *         var answer = ""
 *         scoreMap.forEach { (key, value) -> answer += if(value > 0) key[1] else key[0] }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (11.86ms, 61MB)
 * 테스트 2 〉	통과 (10.22ms, 61.3MB)
 * 테스트 3 〉	통과 (9.37ms, 59.1MB)
 * 테스트 4 〉	통과 (9.99ms, 60.9MB)
 * 테스트 5 〉	통과 (9.50ms, 60.4MB)
 * 테스트 6 〉	통과 (9.11ms, 60.5MB)
 * 테스트 7 〉	통과 (10.15ms, 61MB)
 * 테스트 8 〉	통과 (12.28ms, 60.3MB)
 * 테스트 9 〉	통과 (10.62ms, 59.7MB)
 * 테스트 10 〉	통과 (9.42ms, 59.5MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf("AN", "CF", "MJ", "RT", "NA"),
    intArrayOf(5, 3, 2, 7, 5)
  ),
    "TCMA")

  validate(s.solution(
    arrayOf("TR", "RT", "TR"),
    intArrayOf(7, 1, 3)
  ),
    "RCJA")
}

//      println("$i -> socres[$pos] = ${scores[pos]}")

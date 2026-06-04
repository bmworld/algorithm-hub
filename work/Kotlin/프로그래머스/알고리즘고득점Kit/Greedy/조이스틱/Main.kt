package 프로그래머스.알고리즘고득점Kit.Greedy.조이스틱

import util.validate

class Solution {

  val A = 65
  val Z = 90
  val EMPTY = -1
  fun solution(name: String): Int {
    val len = name.length

    var end = EMPTY
    var x = EMPTY
    var y = 0
    for (tp in len - 1 downTo 0) {
      val yDist = getYDist(name[tp].code)
      if (yDist == 0) continue
      y += yDist

      val xDist = when {
        end == EMPTY -> {
          var stt = 0
          while (stt < len && name[stt].code == A) stt++

          if (stt == tp) 0
          else minOf(tp, len - stt)
        }
        else -> {
          val rmn = len - end
          val case1 = 2 * tp + rmn
          val case2 = 2 * rmn + tp
          minOf(case1, case2)
        }
      }

      if (x == EMPTY || xDist < x) x = xDist
      end = tp
    }

    if (x == EMPTY) x = 0
    return x + y
  }

  fun getYDist(code: Int): Int = minOf(code - A, Z + 1 - code)
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.1MB)
 * 테스트 2 〉	통과 (0.02ms, 59MB)
 * 테스트 3 〉	통과 (0.02ms, 58.3MB)
 * 테스트 4 〉	통과 (0.02ms, 58.5MB)
 * 테스트 5 〉	통과 (0.02ms, 58.6MB)
 * 테스트 6 〉	통과 (0.02ms, 59.3MB)
 * 테스트 7 〉	통과 (0.02ms, 59.5MB)
 * 테스트 8 〉	통과 (0.02ms, 59MB)
 * 테스트 9 〉	통과 (0.02ms, 59.2MB)
 * 테스트 10 〉	통과 (0.02ms, 58.6MB)
 * 테스트 11 〉	통과 (0.02ms, 58.7MB)
 * 테스트 12 〉	통과 (0.03ms, 59.4MB)
 * 테스트 13 〉	통과 (0.02ms, 58.4MB)
 * 테스트 14 〉	통과 (0.03ms, 59.5MB)
 * 테스트 15 〉	통과 (0.03ms, 58.9MB)
 * 테스트 16 〉	통과 (0.02ms, 59.2MB)
 * 테스트 17 〉	통과 (0.02ms, 57.7MB)
 * 테스트 18 〉	통과 (0.02ms, 58.2MB)
 * 테스트 19 〉	실패 (0.02ms, 59.6MB)
 * 테스트 20 〉	통과 (0.02ms, 59.3MB)
 * 테스트 21 〉	실패 (0.02ms, 58.9MB)
 * 테스트 22 〉	통과 (0.02ms, 58.5MB)
 * 테스트 23 〉	통과 (0.02ms, 57.8MB)
 * 테스트 24 〉	통과 (0.02ms, 58.5MB)
 * 테스트 25 〉	통과 (0.02ms, 59.3MB)
 * 테스트 26 〉	통과 (0.03ms, 58.7MB)
 * 테스트 27 〉	통과 (0.02ms, 58.9MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("JAZ"), 11)
  validate(s.solution("JEROEN"), 56)
  validate(s.solution("JAN"), 23)
  validate(s.solution("A"), 0)
  validate(s.solution("AAAA"), 0)
  validate(s.solution("BBAAAAAAB"), 6)
  validate(s.solution("BBBAAAAAAAB"), 8)
  validate(s.solution("ABAAAAAAAAABB"), 7)
}

//      println("---[$rmn] pos = $pos, l=$l ($ld) r=$r($rd) -> cnt = $cnt")
//          println("[$name] stt = ${stt} vs tp= $tp")

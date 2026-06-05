package 프로그래머스.알고리즘고득점Kit.Greedy.조이스틱

import util.validate

class Solution {

  val A = 65
  val Z = 90
  val INF = Int.MAX_VALUE
  fun solution(name: String): Int {
    val len = name.length

    var end = INF
    var minX = INF
    var accY = 0
    for (tp in len - 1 downTo 0) {
      val y = getY(name[tp].code)
      if (y == 0) continue
      accY += y

      val x = when {
        end == INF -> {
          var stt = 1
          while (stt < len && name[stt].code == A) stt++
          minOf(tp, len - stt)
        }
        else -> {
          val rmn = len - end
          val case1 = 2 * tp + rmn
          val case2 = 2 * rmn + tp
          minOf(case1, case2)
        }
      }

      if (x < minX) minX = x
      end = tp
    }

    if (minX == INF) minX = 0

    return minX + accY
  }

  fun getY(code: Int): Int = minOf(code - A, Z + 1 - code)
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 58.9MB)
 * 테스트 2 〉	통과 (0.03ms, 59.2MB)
 * 테스트 3 〉	통과 (0.02ms, 59.2MB)
 * 테스트 4 〉	통과 (0.02ms, 58.6MB)
 * 테스트 5 〉	통과 (0.02ms, 59MB)
 * 테스트 6 〉	통과 (0.03ms, 58.8MB)
 * 테스트 7 〉	통과 (0.02ms, 58.9MB)
 * 테스트 8 〉	통과 (0.02ms, 59.1MB)
 * 테스트 9 〉	통과 (0.02ms, 57.8MB)
 * 테스트 10 〉	통과 (0.03ms, 59.3MB)
 * 테스트 11 〉	통과 (0.07ms, 57.9MB)
 * 테스트 12 〉	통과 (0.05ms, 58.6MB)
 * 테스트 13 〉	통과 (0.02ms, 58.3MB)
 * 테스트 14 〉	통과 (0.02ms, 58.4MB)
 * 테스트 15 〉	통과 (0.02ms, 58.4MB)
 * 테스트 16 〉	통과 (0.02ms, 59.3MB)
 * 테스트 17 〉	통과 (0.02ms, 59.6MB)
 * 테스트 18 〉	통과 (0.02ms, 58.5MB)
 * 테스트 19 〉	통과 (0.02ms, 59.4MB)
 * 테스트 20 〉	통과 (0.03ms, 58.6MB)
 * 테스트 21 〉	통과 (0.03ms, 58.5MB)
 * 테스트 22 〉	통과 (0.02ms, 58.4MB)
 * 테스트 23 〉	통과 (0.02ms, 58.3MB)
 * 테스트 24 〉	통과 (0.02ms, 58.5MB)
 * 테스트 25 〉	통과 (0.02ms, 59.4MB)
 * 테스트 26 〉	통과 (0.02ms, 58.7MB)
 * 테스트 27 〉	통과 (0.02ms, 59.3MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * import kotlin.math.min
 *
 * class Solution {
 *     fun solution(name: String): Int {
 *         var answer = 0
 *         val str = CharArray(name.length) { 'A' }
 *         var cur = 0
 *
 *         while(String(str) != name) {
 *             if(name[cur] == str[cur]) {
 *                 //왼쪽 오른쪽중에 name과 str 다르고 가장 적은 횟수로 이동할 수 있는 곳 찾기
 *                 var left = 1
 *                 var right = 1
 *
 *                 //right 횟수 체크
 *                 while(str[(cur + right) % str.size] == name[(cur + right) % str.size])
 *                     right++
 *                 //left 횟수 체크
 *                 while(str[if(cur - left < 0) cur - left + str.size else cur - left] == name[if(cur - left < 0) cur - left + str.size else cur - left])
 *                     left++
 *
 *                 //right left중 작은거 선택
 *                 cur = when {
 *                     left >= right -> (cur + right) % str.size
 *                     cur - left < 0 -> cur - left + str.size
 *                     else -> cur - left
 *                 }
 *
 *                 answer += min(left, right)
 *             }
 *             else {
 *                 answer += name[cur].findMinMove()
 *                 str[cur] = name[cur]
 *             }
 *         }
 *         return answer
 *     }
 * }
 *
 * fun Char.findMinMove() = min(this.toInt() - 65, 91 - this.toInt())
 * 테스트 1 〉	통과 (0.16ms, 58.4MB)
 * 테스트 2 〉	통과 (0.17ms, 58.5MB)
 * 테스트 3 〉	통과 (0.16ms, 58.3MB)
 * 테스트 4 〉	통과 (0.17ms, 59.4MB)
 * 테스트 5 〉	통과 (0.17ms, 59.2MB)
 * 테스트 6 〉	통과 (0.23ms, 58.7MB)
 * 테스트 7 〉	통과 (0.19ms, 59.2MB)
 * 테스트 8 〉	통과 (0.16ms, 59.5MB)
 * 테스트 9 〉	통과 (0.21ms, 58.5MB)
 * 테스트 10 〉	통과 (0.17ms, 57.8MB)
 * 테스트 11 〉	통과 (0.16ms, 58.5MB)
 * 테스트 12 〉	통과 (0.16ms, 59.4MB)
 * 테스트 13 〉	실패 (0.20ms, 59.1MB)
 * 테스트 14 〉	통과 (0.17ms, 58.3MB)
 * 테스트 15 〉	통과 (0.16ms, 59.7MB)
 * 테스트 16 〉	통과 (0.02ms, 58.6MB)
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
  validate(s.solution("ABAAAAAAAAABB"), 7)
  validate(s.solution("BBBBAAAAAAAB"), 10)
  validate(s.solution("BAAAAAAB"), 3)
}

//      println("[$tp] xDist = $xDist")
//    println("-> x=$x, y=$y")

package 프로그래머스.Lv1.카드뭉치

import util.validate

class Solution {

  fun solution(c1: Array<String>, c2: Array<String>, goal: Array<String>): String {
    var i1 = 0
    var i2 = 0

    var switched = false
    var cur = c1

    fun move() {
      if (cur == c1) i1++ else i2++
    }

    fun swap() {
      cur = if (cur == c1) c2 else c1
    }

    for (x in goal) {
      if (x == getCard(cur, c1, i1, c2, i2)) {
        switched = false
        move()
        continue
      }

      swap()

      if (x == getCard(cur, c1, i1, c2, i2)) {
        switched = true
        move()
        continue
      }

      return "No"
    }

    return "Yes"
  }

  fun getCard(cur: Array<String>, c1: Array<String>,
    i1: Int, c2: Array<String>, i2: Int): String =
    if (cur == c1 && i1 < c1.size) c1[i1] else if (cur == c2 && i2 < c2.size) c2[i2] else ""
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.18ms, 59.3MB)
 * 테스트 2 〉	통과 (0.21ms, 57.6MB)
 * 테스트 3 〉	통과 (0.22ms, 58.2MB)
 * 테스트 4 〉	통과 (0.19ms, 59MB)
 * 테스트 5 〉	통과 (0.21ms, 59.8MB)
 * 테스트 6 〉	통과 (0.18ms, 57.9MB)
 * 테스트 7 〉	통과 (0.17ms, 59.7MB)
 * 테스트 8 〉	통과 (0.17ms, 59.5MB)
 * 테스트 9 〉	통과 (0.17ms, 58.6MB)
 * 테스트 10 〉	통과 (0.18ms, 58.1MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
 *         var idx1 = 0
 *         var idx2 = 0
 *         goal.forEach {
 *             if (idx1 < cards1.size && it == cards1[idx1]) idx1++
 *             else if (idx2 < cards2.size && it == cards2[idx2]) idx2++
 *             else return "No"
 *         }
 *         return "Yes"
 *     }
 * }
 * 테스트 1 〉	통과 (0.01ms, 59.4MB)
 * 테스트 2 〉	통과 (0.01ms, 58.6MB)
 * 테스트 3 〉	통과 (0.02ms, 57.9MB)
 * 테스트 4 〉	통과 (0.01ms, 58.9MB)
 * 테스트 5 〉	통과 (0.03ms, 57.3MB)
 * 테스트 6 〉	통과 (0.01ms, 59.3MB)
 * 테스트 7 〉	통과 (0.01ms, 58.6MB)
 * 테스트 8 〉	통과 (0.01ms, 60.2MB)
 * 테스트 9 〉	통과 (0.01ms, 58MB)
 * 테스트 10 〉	통과 (0.02ms, 59.3MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(arrayOf("i", "drink", "water"), arrayOf("want", "to"),
    arrayOf("i", "want", "to", "drink", "water")), "Yes")
  validate(s.solution(arrayOf("i", "water", "drink"), arrayOf("want", "to"),
    arrayOf("i", "want", "to", "drink", "water")), "No")
  validate(s.solution(arrayOf("i", "want", "to"), arrayOf("drink", "water"),
    arrayOf("i", "want", "to", "drink", "water")), "Yes")

  validate(s.solution(arrayOf("i", "love"), arrayOf("you"),
    arrayOf("i", "love", "you")), "Yes")
  validate(s.solution(arrayOf("i", "love"), arrayOf("you"),
    arrayOf("you", "love", "i")), "No")
  validate(s.solution(arrayOf("i", "love"), arrayOf("you"),
    arrayOf("you", "i", "love")), "Yes")
  validate(s.solution(arrayOf("i", "love"), arrayOf("you"),
    arrayOf("love", "i", "you")), "No")
}

//      println("[NOW][$x] cur=${if (cur == c1) "c1($i1)" else "c2($i2)"} ->")
//      println("[SWAP][$x] cur=${if (cur == c1) "c1($i1)" else "c2($i2)"} ->")

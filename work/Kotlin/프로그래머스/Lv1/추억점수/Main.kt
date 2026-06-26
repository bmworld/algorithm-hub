package 프로그래머스.Lv1.추억점수

import util.validate

class Solution {

  fun solution(name: Array<String>, yearning: IntArray, photo: Array<Array<String>>): IntArray {
    val m = HashMap<String, Int>(name.size)
    repeat(name.size) {
      m += name[it] to yearning[it]
    }

    val len = photo.size
    var ans = IntArray(len)
    repeat(len) {
      val people = photo[it]
      var score = 0
      for (person in people) {
        val v = m[person]
        if (v != null) score += v
      }
      ans[it] = score
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.42ms, 58.8MB)
 * 테스트 2 〉	통과 (0.44ms, 57.7MB)
 * 테스트 3 〉	통과 (0.55ms, 59.5MB)
 * 테스트 4 〉	통과 (0.57ms, 59.8MB)
 * 테스트 5 〉	통과 (0.75ms, 59.3MB)
 * 테스트 6 〉	통과 (1.19ms, 66.2MB)
 * 테스트 7 〉	통과 (2.00ms, 63MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(names: Array<String>, yearnings: IntArray, photoes: Array<Array<String>>): IntArray {
 *         val map = names.zip(yearnings.toTypedArray()).toMap()
 *         return photoes.map { photo -> photo.sumOf { map[it] ?: 0 } }.toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (15.24ms, 62.8MB)
 * 테스트 2 〉	통과 (13.69ms, 63.2MB)
 * 테스트 3 〉	통과 (15.25ms, 62.8MB)
 * 테스트 4 〉	통과 (16.18ms, 64.7MB)
 * 테스트 5 〉	통과 (13.82ms, 64.5MB)
 * 테스트 6 〉	통과 (15.30ms, 68.1MB)
 * 테스트 7 〉	통과 (14.65ms, 66.9MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf("may", "kein", "kain", "radi"),
    intArrayOf(5, 10, 1, 3),
    arrayOf(
      arrayOf("may", "kein", "kain", "radi"),
      arrayOf("may", "kein", "brin", "deny"),
      arrayOf("kon", "kain", "may", "coni"))
  ),
    intArrayOf(19, 15, 6))

  validate(s.solution(
    arrayOf("kali", "mari", "don"),
    intArrayOf(11, 1, 55),
    arrayOf(
      arrayOf("kali", "mari", "don"),
      arrayOf("pony", "tom", "teddy"),
      arrayOf("con", "mona", "don"))
  ),
    intArrayOf(67, 0, 55))


}

package 프로그래머스.Lv0.그림확대

import util.validate

class Solution {

  fun solution(picture: Array<String>, k: Int): Array<String> {
    val R = picture.size
    val C = picture[0].length
    return Array(R * k) { r ->
      val r2 = r / k
      String(CharArray(C * k) { c -> picture[r2][c / k] })
    }
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.9MB)
 * 테스트 2 〉	통과 (0.06ms, 60.9MB)
 * 테스트 3 〉	통과 (0.03ms, 59.6MB)
 * 테스트 4 〉	통과 (0.05ms, 59.3MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(picture: Array<String>, k: Int): Array<String> {
 *         var answer = mutableListOf<String>()
 *         picture.forEach { line ->
 *             var result = ""
 *             line.forEach { pixcel -> repeat(k){ result += pixcel }}
 *             repeat(k) { answer.add(result) }
 *         }
 *         return answer.toTypedArray()
 *     }
 * }
 *
 * 테스트 1 〉	통과 (3.05ms, 59.5MB)
 * 테스트 2 〉	통과 (3.51ms, 60.1MB)
 * 테스트 3 〉	통과 (3.05ms, 61.2MB)
 * 테스트 4 〉	통과 (2.89ms, 59.3MB)
 *
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(picture: Array<String>, k: Int): Array<String> {
 *         var answer = arrayOf<String>()
 *         picture.forEach {
 *             var addString = it.map{it.toString().repeat(k)}.joinToString("")
 *             (1..k).forEach{
 *                 answer = answer.plus(addString)
 *             }
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (18.21ms, 65MB)
 * 테스트 2 〉	통과 (17.02ms, 64.9MB)
 * 테스트 3 〉	통과 (15.98ms, 63.4MB)
 * 테스트 4 〉	통과 (17.26ms, 64.9MB)
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      ".xx...xx.",
      "x..x.x..x",
      "x...x...x",
      ".x.....x.",
      "..x...x..",
      "...x.x...",
      "....x...."),
    2),
    arrayOf(
      "..xxxx......xxxx..",
      "..xxxx......xxxx..",
      "xx....xx..xx....xx",
      "xx....xx..xx....xx",
      "xx......xx......xx",
      "xx......xx......xx",
      "..xx..........xx..",
      "..xx..........xx..",
      "....xx......xx....",
      "....xx......xx....",
      "......xx..xx......",
      "......xx..xx......",
      "........xx........",
      "........xx........")
  )


  validate(s.solution(
    arrayOf("x.x", ".x.", "x.x"), 3),
    arrayOf(
      "xxx...xxx", "xxx...xxx", "xxx...xxx",
      "...xxx...", "...xxx...", "...xxx...",
      "xxx...xxx", "xxx...xxx", "xxx...xxx"
    )
  )

}

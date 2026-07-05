package 프로그래머스.Lv1.키패드누르기

import util.validate

class Solution {

  val L = 'L'
  val R = 'R'
  fun solution(numbers: IntArray, hand: String): String {
    val CAP = 3
    fun pos(r: Int, c: Int): Int = r * CAP + c

    var right = pos(3, 0)
    var left = pos(3, 2)
    val ans = CharArray(numbers.size) { L }

    fun moveR(i: Int, nxt: Int) {
      ans[i] = R
      right = nxt
    }

    for (i in 0 until numbers.size) {
      var key = numbers[i]
      if (key == 0) key = 11

      val r = (key - 1) / 3
      val c = (key + 2) % 3

      val nxt = pos(r, c)


      when (key) {
        1, 4, 7 -> left = nxt
        3, 6, 9 -> moveR(i, nxt)
        else -> {
          val ld = abs(left / CAP - r) + abs(left % CAP - c)
          val rd = abs(right / CAP - r) + abs(right % CAP - c)

          when {
            ld < rd -> left = nxt
            ld > rd -> moveR(i, nxt)
            hand == "right" -> moveR(i, nxt)
            else -> left = nxt
          }
        }
      }
    }

    return String(ans)
  }

  fun abs(x: Int): Int = if (x < 0) -x else x
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.17ms, 58.5MB)
 * 테스트 2 〉	통과 (0.19ms, 58.2MB)
 * 테스트 3 〉	통과 (0.17ms, 60.1MB)
 * 테스트 4 〉	통과 (0.23ms, 58.9MB)
 * 테스트 5 〉	통과 (0.17ms, 57.5MB)
 * 테스트 6 〉	통과 (0.15ms, 59.7MB)
 * 테스트 7 〉	통과 (0.24ms, 58.9MB)
 * 테스트 8 〉	통과 (0.18ms, 59.4MB)
 * 테스트 9 〉	통과 (0.24ms, 59.8MB)
 * 테스트 10 〉	통과 (0.14ms, 57.9MB)
 * 테스트 11 〉	통과 (0.20ms, 59.5MB)
 * 테스트 12 〉	통과 (0.16ms, 59MB)
 * 테스트 13 〉	통과 (0.24ms, 58.5MB)
 * 테스트 14 〉	통과 (0.23ms, 59.8MB)
 * 테스트 15 〉	통과 (0.49ms, 59.1MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *
 *     lateinit var keypadHash : HashMap<Char, ArrayList<Int>>
 *     val ROW = 0
 *     val COL = 1
 *
 *     fun solution(numbers: IntArray, hand: String): String {
 *         var answer = ""
 *         var leftThumb = ArrayList<Int>(2)
 *         var rightThumb = ArrayList<Int>(2)
 *
 *         initHash()
 *         leftThumb = keypadHash['*']!!
 *         rightThumb = keypadHash['#']!!
 *
 *         numbers.forEach{
 *             val key = (it+48).toChar()
 *             when(key){
 *
 *                 '1', '4', '7' -> {
 *                     answer += "L"
 *                     leftThumb = keypadHash[key]!!
 *                 }
 *                 '3', '6', '9' -> {
 *                     answer += "R"
 *                     rightThumb = keypadHash[key]!!
 *                 }
 *                 '2', '5', '8', '0' -> {
 *                     var stdKey = keypadHash[key]!!
 *                     var leftDistance = Math.abs(stdKey[ROW] - leftThumb[ROW]) +
 *                                             Math.abs(stdKey[COL] - leftThumb[COL])
 *                     var rightDistance = Math.abs(stdKey[ROW] - rightThumb[ROW]) +
 *                                             Math.abs(stdKey[COL] - rightThumb[COL])
 *
 *                     if(leftDistance < rightDistance){
 *                        answer += "L"
 *                        leftThumb = stdKey
 *                     } else if(leftDistance > rightDistance){
 *                        answer += "R"
 *                        rightThumb = stdKey
 *                     } else {
 *                         if( hand == "right"){
 *                           answer += "R"
 *                           rightThumb = stdKey
 *                         } else {
 *                           answer += "L"
 *                           leftThumb = stdKey
 *                         }
 *                     }
 *                 }
 *             }
 *         }
 *
 *         return answer
 *     }
 *
 *     fun initHash(){
 *
 *         keypadHash = hashMapOf<Char, ArrayList<Int>>(
 *             '1' to arrayListOf<Int>(0,0),
 *             '2' to arrayListOf<Int>(0,1),
 *             '3' to arrayListOf<Int>(0,2),
 *             '4' to arrayListOf<Int>(1,0),
 *             '5' to arrayListOf<Int>(1,1),
 *             '6' to arrayListOf<Int>(1,2),
 *             '7' to arrayListOf<Int>(2,0),
 *             '8' to arrayListOf<Int>(2,1),
 *             '9' to arrayListOf<Int>(2,2),
 *             '*' to arrayListOf<Int>(3,0),
 *             '0' to arrayListOf<Int>(3,1),
 *             '#' to arrayListOf<Int>(3,2)
 *         )
 *     }
 * }
 * 테스트 1 〉	통과 (6.63ms, 61MB)
 * 테스트 2 〉	통과 (6.33ms, 59.3MB)
 * 테스트 3 〉	통과 (6.48ms, 60.5MB)
 * 테스트 4 〉	통과 (6.28ms, 60.5MB)
 * 테스트 5 〉	통과 (6.16ms, 58.8MB)
 * 테스트 6 〉	통과 (6.26ms, 60.9MB)
 * 테스트 7 〉	통과 (6.34ms, 60.4MB)
 * 테스트 8 〉	통과 (6.54ms, 60.6MB)
 * 테스트 9 〉	통과 (6.30ms, 59.7MB)
 * 테스트 10 〉	통과 (6.46ms, 59.7MB)
 * 테스트 11 〉	통과 (6.73ms, 60.1MB)
 * 테스트 12 〉	통과 (6.36ms, 60.3MB)
 * 테스트 13 〉	통과 (6.44ms, 59.5MB)
 * 테스트 14 〉	통과 (9.70ms, 61MB)
 * 테스트 15 〉	통과 (7.04ms, 61.9MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5), "right"), "LRLLLRLLRRL")
  validate(s.solution(intArrayOf(7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2), "left"), "LRLLRRLLLRR")
  validate(s.solution(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0), "right"), "LLRLLRLLRL")
}

package 프로그래머스.Lv2.택배상자

import util.validate

class Solution {

  fun solution(order: IntArray): Int {
    var ans = 0

    val N = order.size
    val num = IntArray(N + 1) { it }
    val stack = IntArray(N) { it + 1 }
    var i = order[0]
    var n = i + 1

    for (x in order) {
      when {
        i > 0 && stack[i - 1] == x -> {
          i--
          ans++
        }
        x == n -> {
          n++
          ans++
        }
        x < n -> break
        else -> {
          val cnt = x - n
          System.arraycopy(num, n, stack, i, cnt)
          i += cnt
          n = x + 1
          ans++
        }
      }
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (2.13ms, 66.4MB)
 * 테스트 2 〉	통과 (4.81ms, 86.1MB)
 * 테스트 3 〉	통과 (6.39ms, 92.7MB)
 * 테스트 4 〉	통과 (4.25ms, 85.6MB)
 * 테스트 5 〉	통과 (7.43ms, 107MB)
 * 테스트 6 〉	통과 (5.23ms, 71.7MB)
 * 테스트 7 〉	통과 (13.08ms, 100MB)
 * 테스트 8 〉	통과 (2.33ms, 63.1MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * import java.util.*
 * class Solution {
 *     fun solution(order: IntArray): Int {
 *         val bozo = Stack<Int>()
 *         var box = 1
 *         var index = 0
 *         while(index < order.size){
 *             if(order[index] == box){
 *                 index++
 *             }else if(order[index] > box){
 *                 bozo.push(box)
 *             }else {
 *                 if(bozo.size == 0) break
 *
 *                 val top = bozo.pop()
 *                 if(top == order[index]){
 *                     index++
 *                     continue
 *                 }else{
 *                     break
 *                 }
 *             }
 *             box++
 *         }
 *
 *         return index
 *     }
 * }
 * 테스트 1 〉	통과 (6.91ms, 68.1MB)
 * 테스트 2 〉	통과 (10.57ms, 89.3MB)
 * 테스트 3 〉	통과 (14.74ms, 99.1MB)
 * 테스트 4 〉	통과 (12.83ms, 92.9MB)
 * 테스트 5 〉	통과 (43.80ms, 154MB)
 * 테스트 6 〉	통과 (9.32ms, 73.5MB)
 * 테스트 7 〉	통과 (19.72ms, 103MB)
 * 테스트 8 〉	통과 (5.53ms, 62.5MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(intArrayOf(4, 3, 1, 2, 5)), 2)
  validate(s.solution(intArrayOf(5, 4, 3, 2, 1)), 5)
  validate(s.solution(intArrayOf(2, 5, 7, 1, 3, 4, 6)), 3)
}

package 프로그래머스.알고리즘고득점Kit.Greedy.큰수만들기

import util.validate

class Solution {

  val ZERO = 48
  val EMPTY = -1
  fun solution(number: String, k: Int): String {
    val len = number.length - k
    val ans = CharArray(len)
    fun getD(i: Int): Int = number[i].code - ZERO


    var l = 0
    var ai = 0
    l@ for (r in k until number.length) {
      var max = EMPTY
      for (i in l..r) {
        val d = getD(i)
        if (d > max) {
          max = d
          l = i + 1
        }
      }

      ans[ai++] = (max + ZERO).toChar()
    }

    return ans.concatToString()
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (4.41ms, 58.8MB)
 * 테스트 2 〉	통과 (5.17ms, 59.1MB)
 * 테스트 3 〉	통과 (4.53ms, 58.9MB)
 * 테스트 4 〉	통과 (5.78ms, 60.7MB)
 * 테스트 5 〉	통과 (4.58ms, 60MB)
 * 테스트 6 〉	통과 (17.27ms, 61.2MB)
 * 테스트 7 〉	통과 (18.96ms, 60.4MB)
 * 테스트 8 〉	통과 (70.46ms, 60MB)
 * 테스트 9 〉	통과 (13.04ms, 63.2MB)
 * 테스트 10 〉	통과 (2654.08ms, 64.1MB)
 * 테스트 11 〉	통과 (4.71ms, 60MB)
 * 테스트 12 〉	통과 (4.00ms, 59.9MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * import java.util.*
 * class Solution {
 *     fun solution(number: String, k: Int): String {
 *         var answer = ""
 *         var kCnt = k
 *         val numberStack : Stack<Char> = Stack()
 *         var numArray = CharArray(number.length-k)
 *
 *
 *         number.forEach {
 *             while (!numberStack.isEmpty()&&numberStack.peek()<it&&kCnt!=0){
 *                 numberStack.pop()
 *                 kCnt--
 *             }
 *             numberStack.push(it)
 *         }
 *
 *         for (i in 0 until kCnt){
 *             numberStack.pop()
 *         }
 *
 *         numberStack.forEachIndexed { index, c ->
 *             numArray[index] = c
 *         }
 *
 *         return String(numArray)
 *     }
 *
 *
 * }
 * 테스트 1 〉	통과 (0.33ms, 57.9MB)
 * 테스트 2 〉	통과 (0.39ms, 57.6MB)
 * 테스트 3 〉	통과 (0.41ms, 59.1MB)
 * 테스트 4 〉	통과 (0.91ms, 58.2MB)
 * 테스트 5 〉	통과 (1.10ms, 59.6MB)
 * 테스트 6 〉	통과 (4.84ms, 58.1MB)
 * 테스트 7 〉	통과 (9.90ms, 59MB)
 * 테스트 8 〉	통과 (14.51ms, 61MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("1924", 2), "94")
  validate(s.solution("1231234", 3), "3234")
  validate(s.solution("4177252841", 4), "775841")
}

//          println("$number[$i] j=$j -> cnts[$d] = ${cnts[d]}")

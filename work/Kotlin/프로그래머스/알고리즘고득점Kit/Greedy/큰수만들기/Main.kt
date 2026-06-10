package 프로그래머스.알고리즘고득점Kit.Greedy.큰수만들기

import util.validate

class Solution {

  fun solution(num: String, k: Int): String {
    val len = num.length - k
    val ans = CharArray(len)

    var removable = k
    var i = 0
    for (d in num) {
      while (removable > 0 && i > 0 && d > ans[i - 1]) {
        removable--
        i--
      }

      if (i < len) ans[i++] = d
    }

    return ans.concatToString()
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (5.17ms, 59.3MB)
 * 테스트 2 〉	통과 (6.46ms, 60.3MB)
 * 테스트 3 〉	통과 (4.29ms, 59.8MB)
 * 테스트 4 〉	통과 (4.59ms, 59.1MB)
 * 테스트 5 〉	통과 (4.10ms, 58.7MB)
 * 테스트 6 〉	통과 (6.15ms, 61.8MB)
 * 테스트 7 〉	통과 (6.22ms, 59.8MB)
 * 테스트 8 〉	통과 (7.50ms, 61MB)
 * 테스트 9 〉	통과 (8.57ms, 64.5MB)
 * 테스트 10 〉	통과 (15.98ms, 64.7MB)
 * 테스트 11 〉	통과 (4.05ms, 60.9MB)
 * 테스트 12 〉	통과 (4.23ms, 59.9MB)
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
 * 테스트 1 〉	통과 (0.36ms, 58.2MB)
 * 테스트 2 〉	통과 (0.40ms, 58.2MB)
 * 테스트 3 〉	통과 (0.40ms, 58.3MB)
 * 테스트 4 〉	통과 (2.37ms, 58.1MB)
 * 테스트 5 〉	통과 (0.99ms, 58.8MB)
 * 테스트 6 〉	통과 (3.75ms, 59.7MB)
 * 테스트 7 〉	통과 (10.43ms, 58.8MB)
 * 테스트 8 〉	통과 (12.74ms, 60MB)
 * 테스트 9 〉	통과 (25.51ms, 67.5MB)
 * 테스트 10 〉	통과 (51.20ms, 66.6MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("1924", 2), "94")
  validate(s.solution("1231234", 3), "3234")
  validate(s.solution("4177252841", 4), "775841")
  validate(s.solution("71", 0), "71")
  validate(s.solution("17", 1), "7")
  validate(s.solution("71", 1), "7")
}

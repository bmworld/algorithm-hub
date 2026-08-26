package 프로그래머스.Lv2.괄호변환

import util.validate

class Solution {
  companion object {

    const val O = '('
    const val C = ')'
  }

  fun solution(str: String): String {
    val a = str.toCharArray()

    fun op(l: Int, r: Int) {
      if (l >= r) return

      var o = 0
      var c = 0
      val uStt = a[l]
      if (uStt == O) o++ else c++
      for (i in l + 1..r) {
        if (a[i] == O) o++ else c++
        if (o == c) break
      }

      val vStt = l + o + c

      if (uStt == O) return op(vStt, r)

      val v = a.copyOfRange(vStt, r + 1)
      val vCnt = r - vStt + 1

      val w2Size = vStt - 2 - l
      val w2 = CharArray(w2Size) { if (a[l + 1 + it] == O) C else O }
      System.arraycopy(w2, 0, a, l + vCnt + 2, w2Size)

      a[l] = O
      System.arraycopy(v, 0, a, l + 1, vCnt)
      a[l + vCnt + 1] = C

      op(l + 1, l + vCnt)
    }

    op(0, a.size - 1)
    return String(a)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.8MB)
 * 테스트 2 〉	통과 (6.24ms, 63.3MB)
 * 테스트 3 〉	통과 (0.02ms, 59.3MB)
 * 테스트 4 〉	통과 (6.26ms, 63.8MB)
 * 테스트 5 〉	통과 (0.01ms, 60.4MB)
 * 테스트 6 〉	통과 (6.34ms, 62.6MB)
 * 테스트 7 〉	통과 (6.17ms, 63.3MB)
 * 테스트 8 〉	통과 (0.02ms, 59.5MB)
 * 테스트 9 〉	통과 (8.44ms, 62.7MB)
 * 테스트 10 〉	통과 (7.34ms, 63.9MB)
 * 테스트 11 〉	통과 (8.33ms, 63.6MB)
 * 테스트 12 〉	통과 (6.34ms, 63.5MB)
 * 테스트 13 〉	통과 (9.14ms, 63.3MB)
 * 테스트 14 〉	통과 (6.29ms, 62.1MB)
 * 테스트 15 〉	통과 (6.64ms, 63.6MB)
 * 테스트 16 〉	통과 (7.25ms, 62.6MB)
 * 테스트 17 〉	통과 (9.21ms, 61.5MB)
 * 테스트 18 〉	통과 (6.92ms, 64.1MB)
 * 테스트 19 〉	통과 (6.43ms, 62.7MB)
 * 테스트 20 〉	통과 (6.25ms, 64.2MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(p: String): String {
 *         var answer = ""
 *         answer = split(p)
 *         return answer
 *     }
 *     fun split(s: String): String {
 *         var u = ""
 *         var v = ""
 *         var check = 0
 *         var count = 0
 *
 *         if(s.isEmpty()) return ""
 *
 *         for(i in s) {
 *             if(i == '(') check++
 *             if(i == ')') check--
 *             count++
 *             if(check == 0) {
 *                 u = s.substring(0,count)
 *                 v = s.substring(count,s.length)
 *                 break
 *             }
 *         }
 *         if(checkCorrect(u)) return u + split(v)
 *         else {
 *             var a = "(" + split(v) + ")"
 *             var b = ""
 *             u.substring(1,u.length-1).forEach {
 *                 if(it=='(') b = b + ")"
 *                 else b = b + "("
 *             }
 *             return a + b
 *         }
 *     }
 *
 *     fun checkCorrect(s: String): Boolean {
 *         var check = 0
 *         for( i in s) {
 *             if(i == '(') check++
 *             if(i == ')') check--
 *             if(check < 0) return false
 *         }
 *         return true
 *     }
 * }
 * 테스트 1 〉	통과 (0.43ms, 61MB)
 * 테스트 2 〉	통과 (2.26ms, 60MB)
 * 테스트 3 〉	통과 (0.40ms, 59.4MB)
 * 테스트 4 〉	통과 (1.71ms, 60.2MB)
 * 테스트 5 〉	통과 (0.44ms, 59.8MB)
 * 테스트 6 〉	통과 (1.82ms, 59.6MB)
 * 테스트 7 〉	통과 (2.01ms, 60.8MB)
 * 테스트 8 〉	통과 (0.62ms, 60MB)
 * 테스트 9 〉	통과 (2.02ms, 60MB)
 * 테스트 10 〉	통과 (2.11ms, 59.7MB)
 * 테스트 11 〉	통과 (2.01ms, 60.1MB)
 * 테스트 12 〉	통과 (2.14ms, 60.1MB)
 * 테스트 13 〉	통과 (2.29ms, 60.6MB)
 * 테스트 14 〉	통과 (2.28ms, 59.9MB)
 * 테스트 15 〉	통과 (2.48ms, 60.7MB)
 * 테스트 16 〉	통과 (2.54ms, 59.8MB)
 * 테스트 17 〉	통과 (2.30ms, 60MB)
 * 테스트 18 〉	통과 (2.59ms, 61MB)
 * 테스트 19 〉	통과 (3.15ms, 60.8MB)
 * 테스트 20 〉	통과 (2.84ms, 59.4MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun isPerfect(p: String): Boolean {
 *         var k = 0
 *         for(i in p) {
 *             if(i == '(') k++ else k--
 *             if(k < 0) {
 *                 return false
 *             }
 *         }
 *         return true
 *     }
 *     fun solve(p: String): String {
 *         if(p == "") {
 *             return ""
 *         }
 *
 *         var l = 0
 *         var r = 0
 *         for(i in p) {
 *             if(l > 0 && l == r) {
 *                 break;
 *             }
 *             if(i == '(') l++ else r++
 *         }
 *
 *         var result = ""
 *         if(isPerfect(p.substring(0, l + r))) {
 *             result += (p.substring(0, l + r) + solve(p.substring(l + r, p.length)))
 *         }else {
 *             result += "("
 *             result += solve(p.substring(l + r, p.length))
 *             result += ")"
 *             p.substring(1, l + r - 1).forEach { if(it == '(') result += ")" else result += "(" }
 *         }
 *         return result
 *     }
 *     fun solution(p: String): String {
 *         var answer = ""
 *         answer = solve(p)
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (3.87ms, 60.5MB)
 * 테스트 2 〉	통과 (1.57ms, 60.8MB)
 * 테스트 3 〉	통과 (4.34ms, 59.3MB)
 * 테스트 4 〉	통과 (5.89ms, 59.8MB)
 * 테스트 5 〉	통과 (2.91ms, 60.6MB)
 * 테스트 6 〉	통과 (3.30ms, 59.7MB)
 * 테스트 7 〉	통과 (3.99ms, 59.8MB)
 * 테스트 8 〉	통과 (2.77ms, 60.5MB)
 * 테스트 9 〉	통과 (3.54ms, 60.4MB)
 * 테스트 10 〉	통과 (3.43ms, 60.4MB)
 * 테스트 11 〉	통과 (3.71ms, 59.9MB)
 * 테스트 12 〉	통과 (3.53ms, 61.4MB)
 * 테스트 13 〉	통과 (4.95ms, 59.8MB)
 * 테스트 14 〉	통과 (4.06ms, 61MB)
 * 테스트 15 〉	통과 (3.75ms, 60.3MB)
 * 테스트 16 〉	통과 (3.89ms, 60.4MB)
 * 테스트 17 〉	통과 (3.75ms, 60.9MB)
 * 테스트 18 〉	통과 (3.83ms, 60.4MB)
 * 테스트 19 〉	통과 (4.41ms, 60.7MB)
 * 테스트 20 〉	통과 (4.61ms, 61.2MB)
 *
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(")"), ")")
  validate(s.solution("()"), "()")
  validate(s.solution(")("), "()")
  validate(s.solution("(()())()"), "(()())()")
  validate(s.solution("()))((()"), "()(())()")
  validate(s.solution("))(("), "()()")

  validate(s.solution("(((()())))()"), "(((()())))()")
  validate(s.solution("()(((()())))"), "()(((()())))")
  validate(s.solution(")))(((()"), "(())(())")
  validate(s.solution("))()()((()"), "(())()()()")

  // ---
  validate(s.solution(")))((("), "()(())")
}

//      println("[$l, $r -> ${l + 1}, ${l + vCnt}] vStt=$vStt, vCnt = $vCnt")

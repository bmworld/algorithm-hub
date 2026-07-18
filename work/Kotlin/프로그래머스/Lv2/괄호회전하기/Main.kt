package 프로그래머스.Lv2.괄호회전하기

import util.validate

class Solution {

  companion object {

    const val op1 = '('
    const val cp1 = ')'

    const val op2 = '['
    const val cp2 = ']'

    const val op3 = '{'
    const val cp3 = '}'

  }


  fun solution(s: String): Int {
    var ans = 0
    val N = s.length
    val stack = Stack(N)

    l@ for (i in 0 until N) {
      val c = s[i]
      if (!isOp(c)) continue
      else stack.push(c)

      for (j in i + 1 until i + N) {
        val c = s[j % N]
        when {
          isOp(c) -> stack.push(c)
          else -> if (stack.isEmpty() || !isPair(stack.pop(), c)) continue@l
        }
      }

      if (stack.isEmpty()) ans++
      else stack.clear()
    }

    return ans
  }


  fun isOp(c: Char): Boolean = c == op1 || c == op2 || c == op3
  fun isPair(op: Char, cp: Char): Boolean =
    op == op1 && cp == cp1 ||
      op == op2 && cp == cp2 ||
      op == op3 && cp == cp3

  class Stack(N: Int) {

    val stack = CharArray(N)
    var len = 0

    fun isEmpty(): Boolean = len == 0
    fun isNotEmpty(): Boolean = len > 0
    fun clear() {
      len = 0
    }

    fun push(c: Char) {
      stack[len++] = c
    }

    fun pop(): Char = if (isNotEmpty()) stack[--len]
    else throw Exception("Empty Stack")
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (2.85ms, 60.3MB)
 * 테스트 2 〉	통과 (1.79ms, 59.9MB)
 * 테스트 3 〉	통과 (1.69ms, 60.2MB)
 * 테스트 4 〉	통과 (2.62ms, 59.7MB)
 * 테스트 5 〉	통과 (5.23ms, 58.9MB)
 * 테스트 6 〉	통과 (3.33ms, 60.9MB)
 * 테스트 7 〉	통과 (4.23ms, 59.9MB)
 * 테스트 8 〉	통과 (4.50ms, 59.3MB)
 * 테스트 9 〉	통과 (7.34ms, 59.4MB)
 * 테스트 10 〉	통과 (7.68ms, 59.3MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(s: String): Int {
 *         var answer: Int = 0
 *
 *         for(i in 0..s.length-1) {
 *             var tmp = s.substring(i)+s.substring(0,i)
 *             if(isRight(tmp)) {
 *                 answer+=1
 *             }
 *         }
 *         return answer
 *     }
 *
 *     fun isRight(str: String): Boolean {
 *         var list = mutableListOf<Char>()
 *         var endCh = charArrayOf(']',')','}')
 *
 *         for(ch in str) {
 *             if(endCh.contains(ch)) {
 *                 if(list.isEmpty()) {
 *                     return false
 *                 }
 *                 var tmp=list.removeAt(list.size-1)
 *                 when(ch) {
 *                     ']' -> if(tmp!='[') return false
 *                     ')' -> if(tmp!='(') return false
 *                     '}' -> if(tmp!='{') return false
 *                 }
 *             } else {
 *                 list.add(ch)
 *             }
 *         }
 *         return list.size==0
 *     }
 * }
 * 테스트 1 〉	통과 (13.57ms, 66.7MB)
 * 테스트 2 〉	통과 (12.69ms, 66MB)
 * 테스트 3 〉	통과 (11.96ms, 65.5MB)
 * 테스트 4 〉	통과 (12.67ms, 65.5MB)
 * 테스트 5 〉	통과 (15.37ms, 67.1MB)
 * 테스트 6 〉	통과 (12.98ms, 66.3MB)
 * 테스트 7 〉	통과 (16.41ms, 66.2MB)
 * 테스트 8 〉	통과 (14.89ms, 65.2MB)
 * 테스트 9 〉	통과 (18.30ms, 65.9MB)
 * 테스트 10 〉	통과 (23.62ms, 66.6MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("(()())"), 1)
  validate(s.solution(")"), 0)
  validate(s.solution("()()"), 2)
  validate(s.solution("()"), 1)
  validate(s.solution("())))((("), 1)
  validate(s.solution("())))(((("), 0)
  validate(s.solution("()))))((("), 0)
  validate(s.solution(")("), 1)
  validate(s.solution("))("), 0)
  validate(s.solution(")(("), 0)
  validate(s.solution("[](){}"), 3)
  validate(s.solution("[)(]"), 0)
  validate(s.solution("}}}"), 0)
  validate(s.solution("}]()[{"), 2)
  validate(s.solution("}()["), 0)
  validate(s.solution("{{}]"), 0)
  validate(s.solution("{{}}()"), 2)
  validate(s.solution("}{{}}[()]{"), 3)
  validate(s.solution("][])[({{}{}})({{}{}})](["), 2)
  validate(s.solution("][({{}{}})({{}{}})()()()"), 1)
  validate(s.solution(")(()"), 1)
}

//      println("[${l - 1}, $r] $c1")

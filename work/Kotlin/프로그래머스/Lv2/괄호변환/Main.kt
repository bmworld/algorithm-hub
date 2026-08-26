package 프로그래머스.Lv2.괄호변환

import util.validate

class Solution {
  companion object {

    const val O = '('
    const val C = ')'
    const val HALF = 500
    val OArr = CharArray(HALF) { O }
    val CArr = CharArray(HALF) { C }
  }

  fun solution(str: String): String {
    val a = str.toCharArray()


    fun op(l: Int, r: Int) {
      if (l >= r) return

      var o = 0
      var c = 0
      val first = a[l]
      if (first == O) o++ else c++
      for (i in l + 1..r) {
        if (a[i] == O) o++ else c++
        if (o == c) break
      }

      val nl = l + o + c

      if (first == O) return op(nl, r)

      a[l] = O
      val vCnt = r - nl + 1
      System.arraycopy(a, nl, a, l + 1, vCnt)
      val nr = l + vCnt
      a[nr + 1] = C
      System.arraycopy(OArr, 0, a, nr + 2, o - 1)
      System.arraycopy(CArr, 0, a, nr + 2 + o - 1, c - 1)
      op(l + 1, nr)
    }

    op(0, a.size - 1)
    return String(a)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 60.6MB)
 * 테스트 2 〉	통과 (0.02ms, 60.3MB)
 * 테스트 3 〉	통과 (0.02ms, 59.9MB)
 * 테스트 4 〉	통과 (0.02ms, 59.2MB)
 * 테스트 5 〉	통과 (0.02ms, 60.3MB)
 * 테스트 6 〉	통과 (0.02ms, 60.5MB)
 * 테스트 7 〉	통과 (0.02ms, 59.4MB)
 * 테스트 8 〉	통과 (0.02ms, 59.6MB)
 * 테스트 9 〉	통과 (0.02ms, 57.7MB)
 * 테스트 10 〉	통과 (0.02ms, 59.4MB)
 * 테스트 11 〉	통과 (0.02ms, 59.7MB)
 * 테스트 12 〉	실패 (0.03ms, 60.5MB)
 * 테스트 13 〉	실패 (0.03ms, 59.4MB)
 * 테스트 14 〉	실패 (0.05ms, 60MB)
 * 테스트 15 〉	실패 (0.03ms, 60.4MB)
 * 테스트 16 〉	실패 (0.06ms, 59.4MB)
 * 테스트 17 〉	실패 (0.07ms, 59.4MB)
 * 테스트 18 〉	실패 (0.07ms, 58.1MB)
 * 테스트 19 〉	실패 (0.10ms, 59.3MB)
 * 테스트 20 〉	실패 (0.09ms, 60.1MB)
 * 테스트 21 〉	실패 (0.07ms, 61.4MB)
 * 테스트 22 〉	실패 (0.05ms, 60.7MB)
 * 테스트 23 〉	실패 (0.06ms, 59.3MB)
 * 테스트 24 〉	통과 (0.05ms, 60.4MB)
 * 테스트 25 〉	통과 (0.06ms, 59.3MB)
 * ```
 *
 *
 * ```
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("(()())()"), "(()())()")
  validate(s.solution(")("), "()")
  validate(s.solution("()))((()"), "()(())()")
  validate(s.solution("))(("), "()()")
}

//      println("[$l ~ $r] -> [${l + 1} ~ $nr] = o = $o, c=$c , cnt = $vCnt")

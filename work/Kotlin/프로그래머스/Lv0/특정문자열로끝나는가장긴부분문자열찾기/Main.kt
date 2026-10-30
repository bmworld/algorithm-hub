package 프로그래머스.Lv0.특정문자열로끝나는가장긴부분문자열찾기

import util.validate

class Solution {

  fun solution(str: String, pat: String): String {
    val N = str.length
    val M = pat.length

    l@ for (i in N - 1 downTo M - 1) {
      for (j in M - 1 downTo 0)
        if (str[i - (M - (j + 1))] != pat[j]) continue@l
      return String(CharArray(i + 1) { str[it] })
    }

    return ""
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 58.4MB)
 * 테스트 2 〉	통과 (0.02ms, 58.6MB)
 * 테스트 3 〉	통과 (0.02ms, 57.7MB)
 * 테스트 4 〉	통과 (0.02ms, 58.8MB)
 * 테스트 5 〉	통과 (0.02ms, 58.7MB)
 * 테스트 6 〉	통과 (0.02ms, 59.1MB)
 * 테스트 7 〉	통과 (0.02ms, 59MB)
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(myString: String, pat: String) = myString.substring(0, myString.lastIndexOf(pat) + pat.length)
 * }
 * 테스트 1 〉	통과 (4.48ms, 61.1MB)
 * 테스트 2 〉	통과 (4.60ms, 60.2MB)
 * 테스트 3 〉	통과 (4.45ms, 59.9MB)
 * 테스트 4 〉	통과 (4.34ms, 60.9MB)
 * 테스트 5 〉	통과 (7.52ms, 60.2MB)
 * 테스트 6 〉	통과 (4.78ms, 60.1MB)
 * 테스트 7 〉	통과 (4.33ms, 60.5MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(myString: String, pat: String): String {
 *         var answer: String = ""
 *         return myString.substringBeforeLast(pat) + pat
 *     }
 * }
 * 테스트 1 〉	통과 (6.86ms, 60.4MB)
 * 테스트 2 〉	통과 (4.88ms, 60.3MB)
 * 테스트 3 〉	통과 (6.38ms, 60.1MB)
 * 테스트 4 〉	통과 (4.93ms, 60.7MB)
 * 테스트 5 〉	통과 (5.24ms, 59.8MB)
 * 테스트 6 〉	통과 (7.39ms, 60.1MB)
 * 테스트 7 〉	통과 (5.24ms, 60MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("AbCdEFG", "dE"), "AbCdE")
  validate(s.solution("AAAAaaaa", "a"), "AAAAaaaa")
  validate(s.solution("AAAAaaaa", "z"), "")
  validate(s.solution("ZzwozzwwZzowwoZzwwowzz", "Zz"), "ZzwozzwwZzowwoZz")
}

//        println("[$i] str[${i - l}] = ${str[i - l]}  vs  pat[$r] = ${pat[r]}")

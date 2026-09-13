package 프로그래머스.Lv2._2020_KAKAO_BLIND_RECRUITMENT.문자열압축

import util.validate

class Solution {

  fun solution(s: String): Int {
    val N = s.length
    var ans = N

    compLen@ for (len in 1..N / 2) {

      var acc = N % len
      val groups = N / len

      var cur = 0
      var nxt = cur + len
      var pairs = 1

      compGroup@ while (nxt < groups * len) {

        compChar@ for (d in 0 until len) {
          if (s[cur + d] == s[nxt + d]) continue@compChar
          acc += calcPairs(len, pairs)
          cur = nxt
          nxt = cur + len
          pairs = 1

          if (acc > ans) continue@compLen
          else continue@compGroup
        }

        nxt += len
        pairs++
      }

      acc += calcPairs(len, pairs)

      if (ans > acc) ans = acc
    }

    return ans
  }

  private fun calcPairs(strLen: Int, pairs: Int) = strLen + when {
    pairs == 1 -> 0
    pairs < 10 -> 1
    pairs < 100 -> 2
    else -> 3
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.02ms, 59.5MB)
 * 테스트 2 〉	통과 (0.08ms, 58.7MB)
 * 테스트 3 〉	통과 (0.06ms, 60.5MB)
 * 테스트 4 〉	통과 (0.02ms, 59.6MB)
 * 테스트 5 〉	통과 (0.01ms, 59.2MB)
 * 테스트 6 〉	통과 (0.02ms, 59.7MB)
 * 테스트 7 〉	통과 (0.13ms, 59.1MB)
 * 테스트 8 〉	통과 (0.12ms, 57.8MB)
 * 테스트 9 〉	통과 (0.15ms, 59.8MB)
 * 테스트 10 〉	통과 (0.36ms, 60.1MB)
 * 테스트 11 〉	통과 (0.03ms, 59.6MB)
 * 테스트 12 〉	통과 (0.03ms, 59.4MB)
 * 테스트 13 〉	통과 (0.03ms, 59.7MB)
 * 테스트 14 〉	통과 (0.12ms, 58.7MB)
 * 테스트 15 〉	통과 (0.04ms, 59.6MB)
 * 테스트 16 〉	통과 (0.02ms, 60.9MB)
 * 테스트 17 〉	통과 (0.35ms, 58.1MB)
 * 테스트 18 〉	통과 (0.26ms, 58.9MB)
 * 테스트 19 〉	통과 (0.21ms, 59.1MB)
 * 테스트 20 〉	통과 (0.34ms, 60.1MB)
 * 테스트 21 〉	통과 (0.42ms, 58.9MB)
 * 테스트 22 〉	통과 (0.38ms, 60.3MB)
 * 테스트 23 〉	통과 (0.41ms, 59.1MB)
 * 테스트 24 〉	통과 (0.38ms, 59.6MB)
 * 테스트 25 〉	통과 (0.39ms, 60.2MB)
 * 테스트 26 〉	통과 (0.37ms, 59.4MB)
 * 테스트 27 〉	통과 (0.52ms, 59.6MB)
 * 테스트 28 〉	통과 (0.02ms, 59.9MB)
 * 테스트 29 〉	통과 (0.02ms, 59.1MB)
 * 테스트 30 〉	통과 (0.10ms, 59.9MB)
 * 테스트 31 〉	실패 (4.49ms, 60.5MB)
 *
 * [RIVAL 1]
 *
 * [RIVAL 2]
 * ```
 */
fun main() {
  val s = Solution()

  validate(s.solution("a"), 1)
  validate(s.solution("aa"), 2)
  validate(s.solution("ab"), 2)
  validate(s.solution("aaa"), 2)
  validate(s.solution("aaab"), 3)

  validate(s.solution("aabbaccc"), 7)
  validate(s.solution("ababcdcdababcdcd"), 9)
  validate(s.solution("abcabcdede"), 8)
  validate(s.solution("abcabcabcabcdededededede"), 14)
  validate(s.solution("xababcdcdababcdcd"), 17)
}

//println("--------- [$s]")
//println("[len=${len}] while($comp < ${groups * len})")
//println("cur($cur) vs comp($comp), acc=$acc, pairs=$pairs")

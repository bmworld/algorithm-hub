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
    pairs < 1000 -> 3
    else -> 4
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.04ms, 59.3MB)
 * 테스트 2 〉	통과 (0.08ms, 58.5MB)
 * 테스트 3 〉	통과 (0.04ms, 60.6MB)
 * 테스트 4 〉	통과 (0.02ms, 57.8MB)
 * 테스트 5 〉	통과 (0.01ms, 60.1MB)
 * 테스트 6 〉	통과 (0.02ms, 60.3MB)
 * 테스트 7 〉	통과 (0.10ms, 59.8MB)
 * 테스트 8 〉	통과 (0.10ms, 59.9MB)
 * 테스트 9 〉	통과 (0.22ms, 60.1MB)
 * 테스트 10 〉	통과 (0.44ms, 57.6MB)
 * 테스트 11 〉	통과 (0.03ms, 59.4MB)
 * 테스트 12 〉	통과 (0.03ms, 59.4MB)
 * 테스트 13 〉	통과 (0.03ms, 60.6MB)
 * 테스트 14 〉	통과 (0.15ms, 60.2MB)
 * 테스트 15 〉	통과 (0.04ms, 60.3MB)
 * 테스트 16 〉	통과 (0.01ms, 60.3MB)
 * 테스트 17 〉	통과 (0.22ms, 60.1MB)
 * 테스트 18 〉	통과 (0.19ms, 60.1MB)
 * 테스트 19 〉	통과 (0.23ms, 59.4MB)
 * 테스트 20 〉	통과 (0.35ms, 59.4MB)
 * 테스트 21 〉	통과 (0.56ms, 59.6MB)
 * 테스트 22 〉	통과 (0.37ms, 59.5MB)
 * 테스트 23 〉	통과 (0.41ms, 60.2MB)
 * 테스트 24 〉	통과 (1.34ms, 58.8MB)
 * 테스트 25 〉	통과 (0.35ms, 59.7MB)
 * 테스트 26 〉	통과 (0.37ms, 60MB)
 * 테스트 27 〉	통과 (0.52ms, 59.2MB)
 * 테스트 28 〉	통과 (0.01ms, 59.8MB)
 * 테스트 29 〉	통과 (0.02ms, 59.7MB)
 * 테스트 30 〉	통과 (0.09ms, 59.6MB)
 * 테스트 31 〉	통과 (5.17ms, 59.3MB)
 *
 * [RIVAL 1]
 * import java.util.*
 * class Solution {
 *     data class Word(
 *         val word : String,
 *         var count : Int = 1
 *     )
 *     fun solution(s: String): Int {
 *         var answer = Int.MAX_VALUE
 *         for(space in 1..s.length) {
 *             val compressedWordList = LinkedList<Word>()
 *             var startIndex = 0
 *             var endIndex = 0
 *             while(endIndex != s.length) {
 *                 endIndex = (startIndex + space).let {
 *                     if(it > s.length) s.length
 *                     else it
 *                 }
 *                 val currentWord = s.substring(startIndex, endIndex)
 *                 if(compressedWordList.isEmpty() || compressedWordList.peekLast().word != currentWord) compressedWordList.add(Word(currentWord))
 *                 else compressedWordList.peekLast().count++
 *                 startIndex = endIndex
 *             }
 *             val length = compressedWordList.fold(0) {
 *                 acc, word ->
 *                 acc + word.word.length + if(word.count == 1) 0 else {
 *                     word.count.toString().length
 *                 }
 *             }
 *             if(length < answer) answer = length
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (0.55ms, 59.9MB)
 * 테스트 2 〉	통과 (1.43ms, 60.3MB)
 * 테스트 3 〉	통과 (1.04ms, 59.9MB)
 * 테스트 4 〉	통과 (0.72ms, 60MB)
 * 테스트 5 〉	통과 (0.67ms, 59.5MB)
 * 테스트 6 〉	통과 (0.76ms, 60.5MB)
 * 테스트 7 〉	통과 (1.49ms, 59.7MB)
 * 테스트 8 〉	통과 (1.62ms, 60.2MB)
 * 테스트 9 〉	통과 (1.71ms, 60.3MB)
 * 테스트 10 〉	통과 (3.29ms, 61.1MB)
 *
 * [RIVAL 2]
 * import kotlin.math.min
 * class Solution {
 *     fun count(t:String, s:String, cur:Int, sum:Int) : Int {
 *         var len = cur.toString().length + t.length
 *         if(cur == 1)
 *             --len
 *         if(s.length < t.length)
 *             return sum + len + s.length
 *         return if(s.startsWith(t)) {
 *             count(t, s.substring(t.length),cur+1, sum)
 *         } else {
 *             count(s.substring(0, t.length), s, 0, sum + len)
 *         }
 *     }
 *
 *     fun solution(s: String): Int {
 *         var answer = s.length
 *         val len = s.length / 2
 *         for(i in 1..len) {
 *             val t = s.substring(0 until i)
 *             answer = min(answer, count(t, s, 0, 0))
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (6.26ms, 60.4MB)
 * 테스트 2 〉	통과 (8.22ms, 60.8MB)
 * 테스트 3 〉	통과 (9.62ms, 60.7MB)
 * 테스트 4 〉	통과 (7.33ms, 60.6MB)
 * 테스트 5 〉	통과 (0.01ms, 60.1MB)
 * 테스트 6 〉	통과 (6.04ms, 60.6MB)
 * 테스트 7 〉	통과 (7.52ms, 61.2MB)
 * 테스트 8 〉	통과 (7.65ms, 61.6MB)
 * 테스트 9 〉	통과 (8.41ms, 61.8MB)
 * 테스트 10 〉	통과 (12.88ms, 72.6MB)
 *
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

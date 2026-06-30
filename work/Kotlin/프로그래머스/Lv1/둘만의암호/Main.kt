package 프로그래머스.Lv1.둘만의암호

import util.validate

class Solution {

  val a = 97
  val LOCKED = -1
  val ALPHABETS = 26
  fun solution(s: String, skip: String, index: Int): String {
    val mapper = IntArray(ALPHABETS)
    for (x in skip) mapper[x.code - a] = LOCKED

    val pwLen = ALPHABETS - skip.length
    val pw = CharArray(pwLen)

    var i = 0
    for (j in 0 until ALPHABETS) if (mapper[j] != LOCKED) mapper[j] = (i++).also { pw[it] = (j + a).toChar() }

    return String(CharArray(s.length) { pw[(mapper[s[it].code - a] + index) % pwLen] })
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.27ms, 58.5MB)
 * 테스트 2 〉	통과 (0.23ms, 59.3MB)
 * 테스트 3 〉	통과 (0.19ms, 58.3MB)
 * 테스트 4 〉	통과 (0.25ms, 59.7MB)
 * 테스트 5 〉	통과 (0.17ms, 58.4MB)
 * 테스트 6 〉	통과 (0.19ms, 60.1MB)
 * 테스트 7 〉	통과 (0.18ms, 59.9MB)
 * 테스트 8 〉	통과 (0.18ms, 58.7MB)
 * 테스트 9 〉	통과 (0.22ms, 58.2MB)
 * 테스트 10 〉	통과 (0.23ms, 58.4MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(s: String, skip: String, index: Int): String {
 *         var answer: String = ""
 *         val skipped = skip.map { it.toChar() }
 *         val alphabet = ('a'..'z').filter { it !in skipped }
 *
 *         s.forEach {
 *             val i = (alphabet.indexOf(it) + index) % alphabet.size
 *             answer += alphabet[i]
 *         }
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (2.03ms, 58.8MB)
 * 테스트 2 〉	통과 (2.09ms, 60MB)
 * 테스트 3 〉	통과 (2.28ms, 59.2MB)
 * 테스트 4 〉	통과 (1.88ms, 59.8MB)
 * 테스트 5 〉	통과 (1.99ms, 59.4MB)
 * 테스트 6 〉	통과 (2.00ms, 59.9MB)
 * 테스트 7 〉	통과 (1.89ms, 59.7MB)
 * 테스트 8 〉	통과 (2.35ms, 58.6MB)
 * 테스트 9 〉	통과 (2.84ms, 58.7MB)
 * 테스트 10 〉	통과 (2.90ms, 59MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("aukks", "wbqd", 5), "happy")
}

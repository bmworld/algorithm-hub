package 프로그래머스.Lv1.중요한단어를스포방지

import util.validate

class Solution {

  val SPACE = ' '
  fun solution(message: String, spoiler_ranges: Array<IntArray>): Int {
    val N = message.length
    val spoilerRange = BooleanArray(N)
    for (r in spoiler_ranges) for (i in r[0]..r[1]) spoilerRange[i] = true

    val tmp = CharArray(N)

    val spoilerWords = HashSet<String>()
    val publicWords = HashSet<String>()
    var isSpoiler = false
    var j = 0
    repeat(N) { i ->
      val c = message[i]
      if (c != SPACE) {
        if (spoilerRange[i]) isSpoiler = true
        tmp[j++] = c
      }

      if (c == SPACE || i == N - 1) {
        if (isSpoiler) {
          spoilerWords += String(tmp, 0, j)
          isSpoiler = false
        } else publicWords += String(tmp, 0, j)
        j = 0
      }
    }

    var ans = 0
    for (w in spoilerWords) {
      if (!publicWords.contains(w)) {
        publicWords += w
        ans++
      }
    }

    return ans
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.05ms, 58.5MB)
 * 테스트 2 〉	통과 (0.05ms, 59.4MB)
 * 테스트 3 〉	통과 (0.05ms, 58.3MB)
 * 테스트 4 〉	통과 (0.06ms, 59.3MB)
 * 테스트 5 〉	통과 (0.24ms, 57.8MB)
 * 테스트 6 〉	통과 (0.26ms, 57.8MB)
 * 테스트 7 〉	통과 (0.14ms, 58.6MB)
 * 테스트 8 〉	통과 (0.07ms, 60.1MB)
 * 테스트 9 〉	통과 (0.16ms, 58.7MB)
 * 테스트 10 〉	통과 (0.05ms, 59MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(message: String, spoiler_ranges: Array<IntArray>): Int {
 *         var progress = 0
 *         val words = mutableSetOf<String>()
 *         val secrets = mutableListOf<String>()
 *         var i = 0
 *         message.split(" ").forEach { word ->
 *             while (i < spoiler_ranges.size - 1 && progress > spoiler_ranges[i][1]) i++
 *             if (progress <= spoiler_ranges[i][1] && progress + word.length - 1 >= spoiler_ranges[i][0]) {
 *                 secrets.add(word)
 *             } else {
 *                 words.add(word)
 *             }
 *             progress += word.length + 1
 *         }
 *
 *         var answer = 0
 *         secrets.forEach { secret ->
 *             if (secret !in words) {
 *                 answer++
 *                 words.add(secret)
 *             }
 *         }
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (4.89ms, 60.4MB)
 * 테스트 2 〉	통과 (4.89ms, 60MB)
 * 테스트 3 〉	통과 (4.35ms, 60.3MB)
 * 테스트 4 〉	통과 (4.62ms, 59.5MB)
 * 테스트 5 〉	통과 (4.50ms, 60.7MB)
 * 테스트 6 〉	통과 (4.72ms, 60.8MB)
 * 테스트 7 〉	통과 (4.69ms, 59.8MB)
 * 테스트 8 〉	통과 (4.35ms, 60.7MB)
 * 테스트 9 〉	통과 (4.48ms, 59MB)
 * 테스트 10 〉	통과 (4.76ms, 59.3MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(
      "here is muzi here is a secret message",
      arrayOf(intArrayOf(0, 3), intArrayOf(23, 28))
    ), 1
  )

  validate(
    s.solution(
      "my phone number is 01012345678 and may i have your phone number",
      arrayOf(intArrayOf(5, 5), intArrayOf(25, 28), intArrayOf(34, 40), intArrayOf(53, 59))
    ), 4
  )

}

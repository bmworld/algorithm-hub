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
      when (c) {
        SPACE -> {
          val word = String(tmp, 0, j)
          if (isSpoiler) {
            spoilerWords += word
            isSpoiler = false
          } else publicWords += word

          j = 0
        }
        else -> {
          if (spoilerRange[i]) isSpoiler = true
          tmp[j++] = c
        }
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
 * 테스트 1 〉	통과 (0.05ms, 59MB)
 * 테스트 2 〉	통과 (0.05ms, 58.7MB)
 * 테스트 3 〉	통과 (0.04ms, 58.7MB)
 * 테스트 4 〉	통과 (0.08ms, 58.2MB)
 * 테스트 5 〉	통과 (0.21ms, 59.5MB)
 * 테스트 6 〉	통과 (0.25ms, 57.8MB)
 * 테스트 7 〉	통과 (0.20ms, 58.3MB)
 * 테스트 8 〉	실패 (0.06ms, 58.6MB)
 * 테스트 9 〉	통과 (0.17ms, 59.7MB)
 * 테스트 10 〉	통과 (0.05ms, 59.3MB)
 * 테스트 11 〉	통과 (0.06ms, 59MB)
 * 테스트 12 〉	실패 (0.06ms, 58.8MB)
 * 테스트 13 〉	통과 (0.06ms, 59.6MB)
 * 테스트 14 〉	통과 (0.37ms, 59.4MB)
 * 테스트 15 〉	실패 (0.22ms, 57.8MB)
 * 테스트 16 〉	통과 (0.08ms, 58.5MB)
 * 테스트 17 〉	통과 (0.15ms, 60.1MB)
 * 테스트 18 〉	통과 (0.18ms, 57.8MB)
 * 테스트 19 〉	통과 (0.11ms, 58.6MB)
 * 테스트 20 〉	통과 (0.09ms, 59.5MB)
 * 테스트 21 〉	통과 (0.13ms, 58.1MB)
 * 테스트 22 〉	실패 (0.05ms, 58.8MB)
 * 테스트 23 〉	실패 (0.06ms, 58.1MB)
 * 테스트 24 〉	통과 (0.63ms, 60MB)
 * 테스트 25 〉	통과 (0.65ms, 59.6MB)
 * 테스트 26 〉	실패 (1.17ms, 61.7MB)
 * 테스트 27 〉	실패 (1.28ms, 58.4MB)
 * 테스트 28 〉	통과 (0.91ms, 59MB)
 * 테스트 29 〉	실패 (1.37ms, 58.6MB)
 * 테스트 30 〉	실패 (0.41ms, 59.1MB)
 * 테스트 31 〉	통과 (0.47ms, 59.5MB)
 * 테스트 32 〉	통과 (0.98ms, 58.9MB)
 * 테스트 33 〉	통과 (1.21ms, 60.6MB)
 * 테스트 34 〉	통과 (0.43ms, 59.2MB)
 * 테스트 35 〉	실패 (0.51ms, 59.6MB)
 * 테스트 36 〉	실패 (0.67ms, 59.4MB)
 * 테스트 37 〉	통과 (0.21ms, 60MB)
 * 테스트 38 〉	통과 (0.22ms, 58.4MB)
 * 테스트 39 〉	통과 (0.08ms, 58MB)
 * 테스트 40 〉	통과 (0.13ms, 59.9MB)
 * 테스트 41 〉	통과 (0.19ms, 58.5MB)
 * 테스트 42 〉	통과 (0.10ms, 58.8MB)
 * 테스트 43 〉	실패 (0.27ms, 59.4MB)
 * 테스트 44 〉	통과 (0.14ms, 57.9MB)
 * 테스트 45 〉	통과 (0.20ms, 59.8MB)
 * 테스트 46 〉	통과 (0.52ms, 60MB)
 * 테스트 47 〉	통과 (0.21ms, 59.3MB)
 * 테스트 48 〉	통과 (0.50ms, 59.4MB)
 * 테스트 49 〉	통과 (0.59ms, 57.9MB)
 * 테스트 50 〉	통과 (0.43ms, 57.9MB)
 * 테스트 51 〉	통과 (0.52ms, 59.7MB)
 * 테스트 52 〉	통과 (0.62ms, 58.8MB)
 * 테스트 53 〉	실패 (0.40ms, 58.3MB)
 * 테스트 54 〉	통과 (0.32ms, 59.2MB)
 * 테스트 55 〉	통과 (0.25ms, 59.4MB)
 * 테스트 56 〉	통과 (0.35ms, 58.7MB)
 * 테스트 57 〉	통과 (0.15ms, 59.3MB)
 * 테스트 58 〉	통과 (0.06ms, 59.6MB)
 * 테스트 59 〉	통과 (0.14ms, 58.3MB)
 * 테스트 60 〉	통과 (0.05ms, 58.2MB)
 * 테스트 61 〉	통과 (0.05ms, 59.3MB)
 * 테스트 62 〉	통과 (0.06ms, 59.4MB)
 * 테스트 63 〉	통과 (0.06ms, 59.2MB)
 * 테스트 64 〉	통과 (0.08ms, 59.8MB)
 * 테스트 65 〉	통과 (0.07ms, 58.2MB)
 * 테스트 66 〉	통과 (0.06ms, 60.9MB)
 * 테스트 67 〉	통과 (0.06ms, 58.3MB)
 * 테스트 68 〉	실패 (0.09ms, 57.8MB)
 * 테스트 69 〉	통과 (0.16ms, 57.7MB)
 * 테스트 70 〉	통과 (0.08ms, 59.4MB)
 * 테스트 71 〉	통과 (0.07ms, 57.5MB)
 * 테스트 72 〉	통과 (0.05ms, 58.3MB)
 * 테스트 73 〉	통과 (0.10ms, 58.2MB)
 * 테스트 74 〉	통과 (0.12ms, 58.1MB)
 * 테스트 75 〉	실패 (0.06ms, 59.4MB)
 * 테스트 76 〉	통과 (0.07ms, 58.8MB)
 * 테스트 77 〉	통과 (0.05ms, 57.8MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
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

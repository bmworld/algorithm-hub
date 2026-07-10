package 프로그래머스.Lv1.동영상재생기

import util.validate

class Solution {

  val ZERO = 48
  val NINE = 57
  val COLON = 58
  val MOVE = 10
  val NUM = ZERO..NINE
  fun solution(video_len: String, pos: String, op_start: String, op_end: String,
    commands: Array<String>): String {
    val stt = 0
    val end = toSec(video_len)
    val opS = toSec(op_start)
    val opE = toSec(op_end)
    val op = opS..opE

    var now = toSec(pos)

    fun skipOp() {
      if (now in op) now = opE
    }

    skipOp()
    for (command in commands) {
      val nxt = now + MOVE * if (command[0] == 'p') -1 else 1
      now = when {
        nxt < stt -> stt
        nxt > end -> end
        else -> nxt
      }

      skipOp()
    }

    return toStrTime(now)
  }

  fun toSec(s: String): Int {
    var mm = 0
    var ss = 0
    var i = 0
    for (x in s) {
      val code = x.code
      when {
        code in NUM -> when (i) {
          0 -> mm = mm * 10 + code - ZERO
          1 -> ss = ss * 10 + code - ZERO
        }
        else -> i++
      }
    }
    return mm * 60 + ss
  }

  fun toStrTime(t: Int): String {
    val mm = t / 60
    val ss = t % 60
    val r = CharArray(5) {
      when (it) {
        0 -> mm / 10 + ZERO
        1 -> mm % 10 + ZERO
        2 -> COLON
        3 -> ss / 10 + ZERO
        4 -> ss % 10 + ZERO
        else -> throw Exception()
      }.toChar()
    }
    return String(r)

  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.16ms, 59.4MB)
 * 테스트 2 〉	통과 (0.17ms, 58.9MB)
 * 테스트 3 〉	통과 (0.16ms, 59.7MB)
 * 테스트 4 〉	통과 (0.16ms, 59.1MB)
 * 테스트 5 〉	통과 (0.17ms, 58.5MB)
 * 테스트 6 〉	통과 (0.18ms, 58.5MB)
 * 테스트 7 〉	통과 (0.21ms, 61MB)
 * 테스트 8 〉	통과 (0.17ms, 58.4MB)
 * 테스트 9 〉	통과 (0.21ms, 58.8MB)
 * 테스트 10 〉	통과 (0.17ms, 58.1MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(video_len: String, pos: String, op_start: String, op_end: String, commands: Array<String>): String {
 *         val videoLen = video_len.toSec()
 *         var currentPos = pos.toSec()
 *         val opStart = op_start.toSec()
 *         val opEnd = op_end.toSec()
 *
 *         currentPos = openingSkip(currentPos, opStart..opEnd)
 *
 *         for(command in commands) {
 *             when(command) {
 *                 "prev" -> {
 *                     currentPos = if(currentPos - 10< 0) 0 else currentPos - 10
 *                 }
 *                 "next" -> {
 *                     currentPos = if(currentPos + 10 > videoLen) videoLen else currentPos + 10
 *                 }
 *             }
 *
 *             currentPos = openingSkip(currentPos, opStart..opEnd)
 *         }
 *
 *         return currentPos.toTimeString()
 *     }
 *
 *     fun openingSkip(currentPos: Int, opRange : IntRange) : Int {
 *         return if(currentPos in opRange) opRange.last else currentPos
 *     }
 *
 *     fun String.toSec() : Int = this.split(":").let {
 *         it[0].toInt() * 60 + it[1].toInt()
 *     }
 *
 *     fun Int.toTimeString() : String = String.format("%02d:%02d", this/60, this%60)
 * }
 * 테스트 1 〉	통과 (13.06ms, 61.4MB)
 * 테스트 2 〉	통과 (14.25ms, 60.9MB)
 * 테스트 3 〉	통과 (12.55ms, 61.2MB)
 * 테스트 4 〉	통과 (14.34ms, 60.2MB)
 * 테스트 5 〉	통과 (13.34ms, 60.5MB)
 * 테스트 6 〉	통과 (13.07ms, 60.9MB)
 * 테스트 7 〉	통과 (12.19ms, 60.2MB)
 * 테스트 8 〉	통과 (12.20ms, 60.3MB)
 * 테스트 9 〉	통과 (13.41ms, 60.7MB)
 * 테스트 10 〉	통과 (12.64ms, 60.7MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(
      "34:33", "13:00", "00:55", "02:55",
      arrayOf("next", "prev")
    ),
    "13:00"
  )

  validate(
    s.solution(
      "10:55", "00:05", "00:15", "06:55",
      arrayOf("prev", "next", "next")
    ),
    "06:55"
  )

  validate(
    s.solution(
      "07:22", "04:05", "00:15", "04:07",
      arrayOf("next")
    ),
    "04:17"
  )

  validate(
    s.solution(
      "01:00", "00:58", "00:01", "00:10",
      arrayOf("next")
    ),
    "01:00"
  )

  validate(
    s.solution(
      "01:00", "00:05", "00:01", "00:05",
      arrayOf("prv")
    ),
    "00:00"
  )


}

//    println("$video_len - >toStrTime($end) = ${toStrTime(end)}")

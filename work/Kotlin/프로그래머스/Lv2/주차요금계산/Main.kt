package 프로그래머스.Lv2.주차요금계산

import util.validate

class Solution {

  companion object {

    val ZERO = 48
    val CAR_NO_LEN = 4
    val MAX_CAR_NO = 9999

    val OUT = -1
    val DEADLINE = 23 * 60 + 59
  }

  fun solution(fees: IntArray, records: Array<String>): IntArray {
    val N = MAX_CAR_NO + 1
    val inTime = IntArray(N) { OUT }
    val timer = IntArray(N)
    val used = BooleanArray(N)
    var minNo = MAX_CAR_NO
    var maxNo = 0
    var carCnt = 0

    for (str in records) {
      val no = parseCarNo(str)
      val time = parseTime(str)

      if (!used[no]) {
        used[no] = true
        carCnt++
      }

      if (str[11] == 'I') inTime[no] = time
      else {
        timer[no] += time - inTime[no]
        inTime[no] = OUT
      }

      if (no > maxNo) maxNo = no
      if (no < minNo) minNo = no
    }

    for (no in minNo..maxNo) {
      val t = inTime[no]
      if (t != OUT) timer[no] += DEADLINE - t
    }

    val ans = IntArray(carCnt)
    var i = 0

    val baseTime = fees[0]
    val baseFree = fees[1]
    val unit = fees[2]
    val fee = fees[3]

    for (no in minNo..maxNo) {
      if (!used[no]) continue
      ans[i++] = baseFree + maxOf(0, (timer[no] - baseTime + unit - 1) / unit * fee)
    }

    return ans
  }

  private fun parseCarNo(str: String): Int {
    var no = 0
    repeat(CAR_NO_LEN) {
      no = no * 10 + str[6 + it].code - ZERO
    }
    return no
  }

  private fun parseTime(str: String): Int =
    (str[0].code - ZERO) * 600 +
      (str[1].code - ZERO) * 60 +
      (str[3].code - ZERO) * 10 +
      (str[4].code - ZERO)
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.18ms, 60.3MB)
 * 테스트 2 〉	통과 (0.08ms, 58.1MB)
 * 테스트 3 〉	통과 (0.14ms, 60.6MB)
 * 테스트 4 〉	통과 (0.20ms, 60.5MB)
 * 테스트 5 〉	통과 (0.38ms, 59.6MB)
 * 테스트 6 〉	통과 (0.29ms, 60.2MB)
 * 테스트 7 〉	통과 (1.04ms, 61.1MB)
 * 테스트 8 〉	통과 (0.63ms, 60.8MB)
 * 테스트 9 〉	통과 (0.31ms, 59.7MB)
 * 테스트 10 〉	통과 (1.11ms, 60.6MB)
 * 테스트 10 〉	통과 (1.11ms, 60.6MB)
 * 테스트 11 〉	통과 (1.21ms, 61.3MB)
 * 테스트 12 〉	통과 (1.63ms, 62.5MB)
 * 테스트 13 〉	통과 (0.14ms, 61.1MB)
 * 테스트 14 〉	통과 (0.11ms, 60.5MB)
 * 테스트 15 〉	통과 (0.07ms, 59.8MB)
 * 테스트 16 〉	통과 (0.06ms, 60.7MB)
 * v2:
 * 테스트 1 〉	통과 (0.48ms, 60MB)
 * 테스트 2 〉	통과 (0.23ms, 59.8MB)
 * 테스트 3 〉	통과 (0.30ms, 60.3MB)
 * 테스트 4 〉	통과 (0.39ms, 60.5MB)
 * 테스트 5 〉	통과 (0.44ms, 59MB)
 * 테스트 6 〉	통과 (0.41ms, 60.1MB)
 * 테스트 7 〉	통과 (0.67ms, 61.2MB)
 * 테스트 8 〉	통과 (0.55ms, 60.4MB)
 * 테스트 9 〉	통과 (0.43ms, 60.6MB)
 * 테스트 10 〉	통과 (0.68ms, 60.9MB)
 * 테스트 11 〉	통과 (0.66ms, 60.9MB)
 * 테스트 12 〉	통과 (0.80ms, 61.8MB)
 * 테스트 13 〉	통과 (0.31ms, 60.3MB)
 * 테스트 14 〉	통과 (0.29ms, 60.1MB)
 * 테스트 15 〉	통과 (0.17ms, 59.5MB)
 * 테스트 16 〉	통과 (0.17ms, 60.3MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(fees: IntArray, records: Array<String>): IntArray {
 *         var times = mutableMapOf<String, Int>()
 *
 *         records.groupBy { it.split(" ")[1] }.forEach {
 *             it.value.let { order ->
 *                 val s = order.toMutableList()
 *                 if (s.size % 2 == 1) s.add("23:59 ${it.key} OUT")
 *                 for (i in 0..s.lastIndex step 2) {
 *                     val a = s[i + 1].split(" ")[0].split(":")
 *                     val b = s[i].split(" ")[0].split(":")
 *                     val time = (a[0].toInt() - b[0].toInt()) * 60 + a[1].toInt() - b[1].toInt()
 *                     if (times.containsKey(it.key)) times[it.key] = times[it.key]!! + time else times[it.key] = time
 *                 }
 *             }
 *         }
 *
 *         times.forEach {
 *             val extraTime = if(it.value - fees[0] > 0) {
 *                 if ((it.value - fees[0]) % fees[2] != 0) (it.value - fees[0]) / fees[2] + 1
 *                 else (it.value - fees[0]) / fees[2]
 *             } else 0
 *             times[it.key] = fees[1] + extraTime * fees[3]
 *         }
 *
 *         return times.toList().sortedBy { it.first }.toMap().values.toIntArray()
 *     }
 * }
 * 테스트 1 〉	통과 (25.60ms, 66MB)
 * 테스트 2 〉	통과 (13.94ms, 60.3MB)
 * 테스트 3 〉	통과 (23.07ms, 66.1MB)
 * 테스트 4 〉	통과 (18.85ms, 65.1MB)
 * 테스트 5 〉	통과 (20.42ms, 65.8MB)
 * 테스트 6 〉	통과 (21.60ms, 66.1MB)
 * 테스트 7 〉	통과 (25.13ms, 68.7MB)
 * 테스트 8 〉	통과 (21.75ms, 68.2MB)
 * 테스트 9 〉	통과 (19.88ms, 65.7MB)
 * 테스트 10 〉	통과 (26.70ms, 67.6MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    intArrayOf(180, 5000, 10, 600),
    arrayOf("05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN",
      "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT")
  ),
    intArrayOf(14600, 34400, 5000)
  )

  validate(s.solution(
    intArrayOf(120, 0, 60, 591),
    arrayOf("16:00 3961 IN", "16:00 0202 IN", "18:00 3961 OUT", "18:00 0202 OUT", "23:58 3961 IN")
  ),
    intArrayOf(0, 591)
  )

  validate(s.solution(
    intArrayOf(1, 461, 1, 10),
    arrayOf("00:00 1234 IN")
  ),
    intArrayOf(14841)
  )
}

//      println("[${i - 1} = $no] ${ans[i - 1]} -> t=$t")

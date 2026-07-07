package 프로그래머스.Lv1.개인정보수집유효기간

import util.validate
import java.time.LocalDate

class Solution {

  val LAST_DAY_OF_MONTH = 28
  val DATE_LEN = 10
  private val A = 65
  private val ZERO = 48
  val ALPHABETS = 26
  fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {

    val today = toDate(today)
    val exp = IntArray(ALPHABETS)
    for (str in terms) {
      var period = 0
      for (i in 2 until str.length) period = period * 10 + str[i].code - ZERO
      exp[getTerm(str[0].code)] = period
    }

    var len = 0
    var tmp = IntArray(privacies.size)
    for (i in privacies.indices) {
      val str = privacies[i]
      var expDate = toDate(str)
      val dd = expDate.dayOfMonth
      val ndd = if (dd == 1) LAST_DAY_OF_MONTH else dd - 1
      val nMM = exp[getTerm(str[11].code)] - if (dd == 1) 1L else 0L
      expDate = expDate.plusMonths(nMM)
      expDate = expDate.withDayOfMonth(ndd)

      if (today.isAfter(expDate)) tmp[len++] = i + 1
    }

    return IntArray(len) { tmp[it] }
  }

  fun getTerm(code: Int): Int = code - A
  fun toDate(strDate: String): LocalDate {
    var yyyy = 0
    var MM = 0
    var dd = 0
    repeat(DATE_LEN) {
      val x = strDate[it].code - ZERO
      when (it) {
        in 0..3 -> yyyy = yyyy * 10 + x
        in 5..6 -> MM = MM * 10 + x
        in 8..9 -> dd = dd * 10 + x
      }
    }

    return LocalDate.of(yyyy, MM, dd)
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.34ms, 59.1MB)
 * 테스트 2 〉	통과 (2.43ms, 59.6MB)
 * 테스트 3 〉	통과 (2.75ms, 59.1MB)
 * 테스트 4 〉	통과 (0.37ms, 59MB)
 * 테스트 5 〉	통과 (0.39ms, 58.8MB)
 * 테스트 6 〉	통과 (0.43ms, 58MB)
 * 테스트 7 〉	통과 (2.77ms, 60.3MB)
 * 테스트 8 〉	통과 (2.58ms, 59.4MB)
 * 테스트 9 〉	통과 (2.73ms, 58.6MB)
 * 테스트 10 〉	통과 (2.52ms, 58.7MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(today: String, terms: Array<String>, privacies: Array<String>)
 *     = privacies.indices
 *     .filter { privacies[it].split(" ").first().split("\\.".toRegex())
 *        .map(String::toInt).let { (y, m, d) -> (y * 12 * 28) + (m * 28) + d } +
 *        (terms.map { it.split(" ") }.associate { (a, b) -> a to b.toInt() }
 *        .getOrDefault(privacies[it].split(" ").last(), 0) * 28) <= today.split("\\.".toRegex())
 *        .map(String::toInt).let { (y, m, d) -> (y * 12 * 28) + (m * 28) + d } }
 *        .map { it + 1 }
 * }
 * 테스트 1 〉	통과 (22.76ms, 65.6MB)
 * 테스트 2 〉	통과 (21.07ms, 65.5MB)
 * 테스트 3 〉	통과 (21.13ms, 65.8MB)
 * 테스트 4 〉	통과 (21.12ms, 65.6MB)
 * 테스트 5 〉	통과 (21.57ms, 65.2MB)
 * 테스트 6 〉	통과 (35.56ms, 64.5MB)
 * 테스트 7 〉	통과 (24.04ms, 64.9MB)
 * 테스트 8 〉	통과 (22.11ms, 64.6MB)
 * 테스트 9 〉	통과 (27.72ms, 64.3MB)
 * 테스트 10 〉	통과 (25.93ms, 66MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(
    s.solution(
      "2022.05.19",
      arrayOf("A 6", "B 12", "C 3"),
      arrayOf("2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C")
    ),
    intArrayOf(1, 3)
  )

  validate(
    s.solution(
      "2020.01.01",
      arrayOf("Z 3", "D 5"),
      arrayOf("2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z")
    ),
    intArrayOf(1, 4, 5)
  )

}

//println("[$i][type=${exp[getType(str[11].code)]}] $expDate")

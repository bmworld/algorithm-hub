package 프로그래머스.Lv1.신규아이디추천

import util.validate

class Solution {

  val MIN_LEN = 3
  val MAX_LEN = 15
  val ALPHABETS = 26
  val distFromLowerCase = 32

  val DOT = 46
  val isNum = 48..57
  private val A = 65
  private val a = 97
  val uppercase = A until A + ALPHABETS
  val lowercase = a until a + ALPHABETS
  fun solution(new_id: String): String {
    var len = 0
    val tmp = CharArray(maxOf(new_id.length, MIN_LEN))
    var prv = 0

    fun append(code: Int) {
      tmp[len++] = code.toChar()
    }

    for (x in new_id) {
      val code = x.code

      when (code) {
        in uppercase -> append(toLowerCase(code))
        DOT -> if (len == 0 || prv == DOT) continue else append(code)
        in lowercase, in isNum, '-'.code, '_'.code -> append(code)
        else -> continue
      }

      if (len >= MAX_LEN) break
      else prv = code
    }

    if (len == 0) append(a)
    else if (tmp[len - 1].code == DOT) len--

    while (len < MIN_LEN) append(tmp[len - 1].code)

    return String(CharArray(len) { tmp[it] })
  }

  fun toLowerCase(upperCaseCode: Int): Int = upperCaseCode + distFromLowerCase
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.14ms, 58.3MB)
 * 테스트 2 〉	통과 (0.17ms, 60MB)
 * 테스트 3 〉	통과 (0.19ms, 58.5MB)
 * 테스트 4 〉	통과 (0.14ms, 58.5MB)
 * 테스트 5 〉	통과 (0.23ms, 60MB)
 * 테스트 6 〉	통과 (0.15ms, 60.8MB)
 * 테스트 7 〉	통과 (0.15ms, 60.1MB)
 * 테스트 8 〉	통과 (0.14ms, 59.3MB)
 * 테스트 9 〉	통과 (0.15ms, 58.6MB)
 * 테스트 10 〉	통과 (0.15ms, 59.9MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(new_id: String): String = new_id
 *             .stageOne()
 *             .stageTwo()
 *             .stageThree()
 *             .stageFour()
 *             .stageFive()
 *             .stageSix()
 *             .stageSeven()
 *
 *     fun String.stageOne() = lowercase()
 *     fun String.stageTwo() = filter { it.isLetterOrDigit() || it == '.' || it == '_' || it == '-' }
 *     fun String.stageThree() = replace(Regex("[.]{2,}"), ".")
 *     fun String.stageFour() = removePrefix(".").removeSuffix(".")
 *     fun String.stageFive() = if (isEmpty()) "a" else this
 *     fun String.stageSix() = if (length >= 16) substring(0, 15).removeSuffix(".") else this
 *     fun String.stageSeven() = if (length >= 3) this else this + last().toString().repeat(3 - length)
 * }
 * 테스트 1 〉	통과 (5.09ms, 59.5MB)
 * 테스트 2 〉	통과 (5.77ms, 59.7MB)
 * 테스트 3 〉	통과 (5.95ms, 59.6MB)
 * 테스트 4 〉	통과 (5.16ms, 58.9MB)
 * 테스트 5 〉	통과 (5.29ms, 60.7MB)
 * 테스트 6 〉	통과 (7.28ms, 59MB)
 * 테스트 7 〉	통과 (5.49ms, 60.7MB)
 * 테스트 8 〉	통과 (5.05ms, 60.2MB)
 * 테스트 9 〉	통과 (6.54ms, 60.7MB)
 * 테스트 10 〉	통과 (5.88ms, 59.5MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    "...!@BaT#*..y.abcdefghijklm"
  ),
    "bat.y.abcdefghi"
  )
  validate(s.solution(
    "z-+.^."
  ),
    "z--"
  )
  validate(s.solution(
    "=.="
  ),
    "aaa"
  )
  validate(s.solution(
    "123_.def"
  ),
    "123_.def"
  )
  validate(s.solution(
    "abcdefghijklmn.p"
  ),
    "abcdefghijklmn"
  )

  // ---------------------------------------------------------------------
  validate(s.solution(
    "Z"
  ),
    "zzz"
  )

  validate(s.solution(
    "-_"
  ),
    "-__"
  )

  validate(s.solution(
    ""
  ),
    "aaa"
  )

  validate(s.solution(
    "z.......k......z..."
  ),
    "z.k.z"
  )


  validate(s.solution(
    "...z...kj"
  ),
    "z.kj"
  )

  validate(s.solution(
    "kkkkkkkkkkkkendddddddddddddd"
  ),
    "kkkkkkkkkkkkend"
  )


}

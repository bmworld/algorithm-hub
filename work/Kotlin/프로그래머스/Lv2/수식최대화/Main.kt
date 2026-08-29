package 프로그래머스.Lv2.수식최대화

import util.validate

class Solution {
  companion object {

    const val a = '+'
    const val s = '-'
    const val m = '*'

    const val ZERO = 48
    const val MAXLEN = 50
    const val UNIQ_OPERATOR_MAX_SIZE = 3
  }

  fun solution(exp: String): Long {

    val uop = CharArray(UNIQ_OPERATOR_MAX_SIZE)
    var uopCnt = 0
    var sc = 0
    var ac = 0
    var mc = 0

    val nums = LongArray(MAXLEN)
    var ni = 0
    val ops = CharArray(MAXLEN)
    var oi = 0

    var x = 0L
    for (char in exp) {
      when (char) {
        a, s, m -> {
          ops[oi++] = char.also {
            when (it) {
              a -> if (ac++ == 0) uop[uopCnt++] = it
              s -> if (sc++ == 0) uop[uopCnt++] = it
              m -> if (mc++ == 0) uop[uopCnt++] = it
            }
          }
          nums[ni++] = x
          x = 0L
        }
        else -> x = x * 10 + char.code - ZERO
      }
    }
    nums[ni++] = x

    var ans = 0L

    val op = CharArray(uopCnt)
    val usedOp = BooleanArray(uopCnt)
    fun dfs(dep: Int) {
      if (dep == uopCnt) {
        if (uopCnt == UNIQ_OPERATOR_MAX_SIZE && op[1] == s) return

        val tmpNums = nums.copyOf(ni)
        val tmpOps = ops.copyOf(oi)
        var tmpOpLen = oi

        var opi = uopCnt
        while (--opi >= 0) {
          val o1 = op[opi]
          var tni = 1
          var toi = 0

          for (i in 0 until tmpOpLen) {
            var x = tmpNums[i + 1]
            val o2 = tmpOps[i]
            if (o1 == o2) {
              when (o1) {
                a -> tmpNums[tni - 1] = tmpNums[tni - 1] + x
                s -> tmpNums[tni - 1] = tmpNums[tni - 1] - x
                m -> tmpNums[tni - 1] = tmpNums[tni - 1] * x
              }
              tmpOpLen--
            } else {
              tmpNums[tni++] = x
              tmpOps[toi++] = o2
            }
          }
        }

        abs(tmpNums[0]).also { if (it > ans) ans = it }
        return
      }

      for (i in 0 until uopCnt) {
        if (usedOp[i]) continue
        usedOp[i] = true
        op[dep] = uop[i]
        dfs(dep + 1)
        usedOp[i] = false
      }
    }

    dfs(0)

    return ans
  }

  fun abs(v: Long): Long = if (v < 0L) -v else v
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.35ms, 58.3MB)
 * 테스트 2 〉	통과 (0.33ms, 59.8MB)
 * 테스트 3 〉	통과 (0.34ms, 60.1MB)
 * 테스트 4 〉	통과 (0.31ms, 60.5MB)
 * 테스트 5 〉	통과 (0.33ms, 60MB)
 * 테스트 6 〉	통과 (0.33ms, 59.8MB)
 * 테스트 7 〉	통과 (0.31ms, 60.3MB)
 * 테스트 8 〉	실패 (0.36ms, 59.5MB)
 * 테스트 9 〉	통과 (0.32ms, 59.6MB)
 * 테스트 10 〉	통과 (0.32ms, 60.6MB)
 * 테스트 11 〉	실패 (0.32ms, 59.8MB)
 * 테스트 12 〉	통과 (0.37ms, 60.3MB)
 * 테스트 13 〉	실패 (0.43ms, 59.4MB)
 * 테스트 14 〉	실패 (2.15ms, 59.2MB)
 * 테스트 15 〉	통과 (0.32ms, 59.5MB)
 * 테스트 16 〉	통과 (0.30ms, 60.6MB)
 * 테스트 17 〉	통과 (0.30ms, 59.7MB)
 * 테스트 18 〉	통과 (0.51ms, 60MB)
 * 테스트 19 〉	통과 (0.30ms, 60.1MB)
 * 테스트 20 〉	통과 (0.35ms, 59.9MB)
 * 테스트 21 〉	통과 (0.33ms, 60.4MB)
 * 테스트 22 〉	통과 (0.32ms, 60.9MB)
 * 테스트 23 〉	통과 (0.33ms, 60MB)
 * 테스트 24 〉	통과 (0.31ms, 60.6MB)
 * 테스트 25 〉	통과 (0.38ms, 60.9MB)
 * 테스트 26 〉	통과 (0.52ms, 60.8MB)
 * 테스트 27 〉	실패 (0.34ms, 59.6MB)
 * 테스트 28 〉	통과 (0.32ms, 60.4MB)
 * 테스트 29 〉	통과 (0.31ms, 59.3MB)
 * 테스트 30 〉	통과 (0.33ms, 60.7MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("100+20+30+50"), 200)
  validate(s.solution("100-200*300-500+20"), 60420)
  validate(s.solution("50*6-3*2"), 300)
}

//println(
//"[$o1] ${tmpNums.contentToString()}, ${tmpOps.contentToString()} until $tmpOpLen")

//        println("---- op = ${op.joinToString()} -> tmpNums[0] = ${tmpNums[0]}")

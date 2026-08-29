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
 * 테스트 1 〉	통과 (0.37ms, 58.6MB)
 * 테스트 2 〉	통과 (0.31ms, 59.4MB)
 * 테스트 3 〉	통과 (0.32ms, 59.7MB)
 * 테스트 4 〉	통과 (0.36ms, 59.4MB)
 * 테스트 5 〉	통과 (0.33ms, 60.6MB)
 * 테스트 6 〉	통과 (0.31ms, 60.1MB)
 * 테스트 7 〉	통과 (0.33ms, 59.9MB)
 * 테스트 8 〉	통과 (0.49ms, 59.9MB)
 * 테스트 9 〉	통과 (0.36ms, 59.4MB)
 * 테스트 10 〉	통과 (0.43ms, 60.5MB)
 * ```
 *
 *
 * ```
 * [RIVAL]
 * class Solution {
 *     fun solution(expression: String): Long {
 *         var answer: Long = 0
 *         val cases = listOf("*+-","*-+", "+*-", "+-*", "-*+", "-+*")
 *         val numsOrder = expression.split('*', '-', '+').map { it.toLong() }
 *         val opsOrder = expression.filter { it in "*+-" }
 *         cases.forEach { ops ->
 *             val tempNums = numsOrder.toMutableList()
 *             val tempOps = opsOrder.toMutableList()
 *             ops.forEach { op ->
 *                 var index = tempOps.indexOfFirst { it == op }
 *                 while (index >= 0) {
 *                     val result = when (op) {
 *                         '*' -> tempNums[index] * tempNums[index + 1]
 *                         '+' -> tempNums[index] + tempNums[index + 1]
 *                         '-' -> tempNums[index] - tempNums[index + 1]
 *                         else -> 0
 *                     }
 *                     tempNums[index+1] = result
 *                     tempNums.removeAt(index)
 *                     tempOps.removeAt(index)
 *                     index = tempOps.indexOfFirst { it == op }
 *                 }
 *             }
 *             answer = answer.coerceAtLeast(Math.abs(tempNums[0]))
 *         }
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (21.69ms, 66.4MB)
 * 테스트 2 〉	통과 (24.07ms, 66.5MB)
 * 테스트 3 〉	통과 (23.02ms, 66.1MB)
 * 테스트 4 〉	통과 (22.72ms, 66.6MB)
 * 테스트 5 〉	통과 (29.27ms, 66MB)
 * 테스트 6 〉	통과 (24.17ms, 66MB)
 * 테스트 7 〉	통과 (22.29ms, 66.4MB)
 * 테스트 8 〉	통과 (23.60ms, 66.1MB)
 * 테스트 9 〉	통과 (22.91ms, 65.6MB)
 * 테스트 10 〉	통과 (23.70ms, 66.1MB)
 *
 * [RIVAL 2]
 * class Solution {
 *     private val numbers = mutableListOf<Long>()
 *     private val operators = mutableListOf<Char>()
 *
 *     fun solution(expression: String): Long {
 *         var answer: Long = 0
 *         val numTemp = StringBuilder()
 *         for (c in expression) {
 *             when (c) {
 *                 '*', '+', '-' -> {
 *                     operators.add(c)
 *                     numbers.add(numTemp.toString().toLong())
 *                     numTemp.clear()
 *                 }
 *                 else -> numTemp.append(c)
 *             }
 *         }
 *         numbers.add(numTemp.toString().toLong())
 *
 *         val ops = operators.distinct()
 *         val visited = BooleanArray(ops.size)
 *         val priority = mutableListOf<Char>()
 *         fun dfs(depth: Int) {
 *             if (depth == ops.size) {
 *                 answer = maxOf(answer, calc(priority))
 *                 return
 *             }
 *
 *             for (i in ops.indices) {
 *                 if (!visited[i]) {
 *                     visited[i] = true
 *                     priority.add(ops[i])
 *
 *                     dfs(depth + 1)
 *
 *                     priority.removeLast()
 *                     visited[i] = false
 *                 }
 *             }
 *         }
 *         dfs(0)
 *
 *         return answer
 *     }
 *
 *     private fun calc(priority: List<Char>): Long {
 *         val nums = numbers.toMutableList()
 *         val ops = operators.toMutableList()
 *
 *         for (p in priority) {
 *             var i = 0
 *             while (i < ops.size) {
 *                 if (ops[i] == p) {
 *                     nums[i] = when(p) {
 *                         '*' -> nums[i] * nums[i + 1]
 *                         '+' -> nums[i] + nums[i + 1]
 *                         '-' -> nums[i] - nums[i + 1]
 *                         else -> 0
 *                     }
 *                     nums.removeAt(i + 1)
 *                     ops.removeAt(i)
 *                 } else {
 *                     i++
 *                 }
 *             }
 *         }
 *
 *         return Math.abs(nums[0])
 *     }
 * }
 * 테스트 1 〉	통과 (7.52ms, 61.3MB)
 * 테스트 2 〉	통과 (7.56ms, 59.7MB)
 * 테스트 3 〉	통과 (8.72ms, 61.1MB)
 * 테스트 4 〉	통과 (7.76ms, 61.6MB)
 * 테스트 5 〉	통과 (7.66ms, 62.1MB)
 * 테스트 6 〉	통과 (7.42ms, 61.9MB)
 * 테스트 7 〉	통과 (8.51ms, 62.3MB)
 * 테스트 8 〉	통과 (7.99ms, 61.6MB)
 * 테스트 9 〉	통과 (7.98ms, 61.4MB)
 * 테스트 10 〉	통과 (7.82ms, 62.3MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution("100+20+30+50"), 200)
  validate(s.solution("100-200*300-500+20"), 60420)
  validate(s.solution("50*6-3*2"), 300)
}

package 백준.Silver.no14888

import java.io.*

fun main() =
    with(StreamTokenizer(System.`in`.bufferedReader())) {
      fun nextInt(): Int {
        nextToken()
        return nval.toInt()
      }

      val n = nextInt()
      val nums = IntArray(n)
      repeat(n) { idx -> nums[idx] = nextInt() }

      val ops = IntArray(n)
      val pl = nextInt()
      for (i in 0 until pl) ops[i] = 1
      val mi = nextInt()
      for (i in pl until pl + mi) ops[i] = 2
      val mu = nextInt()
      for (i in pl + mi until pl + mi + mu) ops[i] = 3
      val di = nextInt()
      for (i in pl + mi + mu until pl + mi + mu + di) ops[i] = 4

      BufferedWriter(OutputStreamWriter(System.out)).use { bw -> solveTo(nums, ops, bw) }
    }

/**
 * 계산순서: 연산자 우선순위 무시 -> 순차적 연산 음수 나눗셈: 진행 음수를 양수로 나눌 때, 양수로 바꾼 뒤 그 몫을 음수 바꾸어 취함
 *
 * @param nums 숫자 목록 (2<=n<=11)
 * @param ops 연산자수 목록 (1: 덧셈, 2: 뺄셈, 3: 곱셈, 4: 나눗셈)
 * @return 최대값, 최소값
 */
fun solveTo(nums: IntArray, ops: IntArray, out: Appendable) {
  var min = Int.MAX_VALUE
  var max = Int.MIN_VALUE

  val n = nums.size
  val opSize = n - 1
  val opCh = BooleanArray(opSize) { false }

  fun dfs(numIdx: Int, acc: Int) {
    if (numIdx == n) {
      if (min > acc) {
        min = acc
      }

      if (max < acc) {
        max = acc
      }
    } else {

      for (i in 0 until opSize) {
        if (opCh[i]) continue
        opCh[i] = true

        var nextAcc = 0
        var num = nums[numIdx]
        val op = ops[i]
        when (op) {
          1 -> nextAcc = acc + num
          2 -> nextAcc = acc - num
          3 -> nextAcc = acc * num
          4 -> {
            if (num < 0) {
              num = -num
              nextAcc = -(acc / num)
            } else nextAcc = acc / num
          }
        }

        dfs(numIdx + 1, nextAcc)

        opCh[i] = false
      }
    }
  }

  dfs(1, nums[0])

  out.append(max.toString()).append("\n").append(min.toString())
}

/** 테스트 */
fun solution(
    nums: IntArray,
    op: IntArray,
): String {
  val sb = StringBuilder()
  solveTo(nums, op, sb)
  return sb.trimEnd().toString()
}

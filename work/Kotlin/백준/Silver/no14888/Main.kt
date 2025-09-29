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

      val op = IntArray(4)
      repeat(4) { idx -> op[idx] = nextInt() }

      BufferedWriter(OutputStreamWriter(System.out)).use { bw -> solveTo(nums, op, bw) }
    }

/**
 * 계산순서: 연산자 우선순위 무시 -> 순차적 연산 음수 나눗셈: 진행 음수를 양수로 나눌 때, 양수로 바꾼 뒤 그 몫을 음수 바꾸어 취함
 *
 * @param nums 숫자 목록 (2<=n<=11)
 * @param op 연산자수 목록 (순서: 덧셈, 뺄셈, 곱셈, 나눗셈)
 * @return 최대값, 최소값
 */
fun solveTo(nums: IntArray, op: IntArray, out: Appendable) {
  var min = 0
  var max = 0

  for (n in nums) {
    println("n = ${n}")
  }

  for (o in op) {
    println("o = ${o}")
  }

  //
  out.append(if (max < 0) "-" else "" + max.toString())
      .append("\n")
      .append(if (min < 0) "-" else "" + min.toString())
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

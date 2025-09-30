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

      val p = nextInt()
      val m = nextInt()
      val t = nextInt()
      val d = nextInt()

      BufferedWriter(OutputStreamWriter(System.out)).use { bw -> solveTo(nums, p, m, t, d, bw) }
    }

/**
 * 계산순서: 연산자 우선순위 무시 -> 순차적 연산 음수 나눗셈: 진행 음수를 양수로 나눌 때, 양수로 바꾼 뒤 그 몫을 음수 바꾸어 취함
 *
 * @param nums 숫자 목록 (2<=n<=11)
 * @param p 연산자수: 덧셈
 * @param m 연산자수: 뺄셈
 * @param t 연산자수: 곱셈
 * @param d 연산자수: 나눗셈
 * @return 최대값, 최소값
 */
fun solveTo(
    nums: IntArray,
    p: Int,
    m: Int,
    t: Int,
    d: Int,
    out: Appendable,
) {
  var min = Int.MAX_VALUE
  var max = Int.MIN_VALUE

  val n = nums.size

  fun dfs(idx: Int, acc: Int, p: Int, m: Int, t: Int, d: Int) {
    if (idx == n) {
      if (min > acc) min = acc
      if (max < acc) max = acc
      return
    }

    val num = nums[idx]
    if (p > 0) dfs(idx + 1, acc + num, p - 1, m, t, d)
    if (m > 0) dfs(idx + 1, acc - num, p, m - 1, t, d)
    if (t > 0) dfs(idx + 1, acc * num, p, m, t - 1, d)
    if (d > 0) dfs(idx + 1, if (acc >= 0) acc / num else -((-acc) / num), p, m, t, d - 1)
  }

  dfs(1, nums[0], p, m, t, d)

  out.append(max.toString()).append("\n").append(min.toString())
}
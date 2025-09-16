import java.io.*

fun main() =
    with(StreamTokenizer(System.`in`.bufferedReader())) {
      fun nextInt(): Int {
        nextToken()
        return nval.toInt()
      }

      val size = nextInt()
      val k = nextInt()

      val arr = IntArray(size) { nextInt() }

      BufferedWriter(OutputStreamWriter(System.out)).use { bw ->
        solveTo(k, arr, bw) // BufferedWriter를 그대로 넘김
        bw.flush()
      }
    }

/** 제출용 */
fun solveTo(k: Int, A: IntArray, out: Appendable) {
  var answer = -1
  var found = false
  var writes = 0
  val tmp = IntArray(A.size)

  fun merge(A: IntArray, p: Int, q: Int, r: Int) {
    if (found) return
    var i = p
    var j = q + 1
    var t = 0 // tmp index

    // 정렬
    while (i <= q && j <= r) {
      val v = if (A[i] <= A[j]) A[i++] else A[j++]
      tmp[t++] = v
      writes++
      if (writes == k) {
        answer = v
        found = true
      }
    }

    // 전 나머지
    while (!found && i <= q) {
      val v = A[i++]
      tmp[t++] = v
      writes++
      if (writes == k) {
        answer = v
        found = true
      }
    }

    // 후 나머지
    while (!found && j <= r) {
      val v = A[j++]
      tmp[t++] = v
      writes++
      if (writes == k) {
        answer = v
        found = true
      }
    }

    // copy: tmp[0 until t] -> A[p..r]
    System.arraycopy(tmp, 0, A, p, t)
  }

  fun mergeSort(A: IntArray, p: Int, r: Int) {
    if (found) return
    if (p >= r) return
    val q = (p + r) / 2 // 중
    mergeSort(A, p, q) // 전
    if (found) return
    mergeSort(A, q + 1, r) // 후
    if (found) return
    merge(A, p, q, r) // 합
  }

  mergeSort(A, 0, A.size - 1)

  out.append(answer.toString())
}
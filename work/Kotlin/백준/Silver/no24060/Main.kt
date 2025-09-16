package 백준.Silver.no24060

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
  val answer = IntArray(2) //  [업데이트 회수, 결과값]
  answer[1] = -1

  fun writeLine() {
    when (out) {
      is BufferedWriter -> {
        val bw = out as BufferedWriter
        bw.write(answer[1].toString())
      }
      is Writer -> {
        val w = out as Writer
        w.write(answer[1].toString())
      }
      else -> {
        out.append(answer[1].toString())
      }
    }
  }

  val tmp = IntArray(A.size)
  mergeSort(A, 0, A.size - 1, tmp, k, answer)

  writeLine()
}

fun mergeSort(A: IntArray, p: Int, r: Int, tmp: IntArray, k: Int, aw: IntArray) {
  if (p >= r) return
  val q = (p + r) / 2 // 중
  mergeSort(A, p, q, tmp, k, aw) // 전
  mergeSort(A, q + 1, r, tmp, k, aw) // 후
  merge(A, p, q, r, tmp, k, aw) // 합
}

fun merge(A: IntArray, p: Int, q: Int, r: Int, tmp: IntArray, k: Int, aw: IntArray) {
  var i = p
  var j = q + 1
  var t = 0 // tmp index

  while (i <= q && j <= r) {

    if (A[i] <= A[j]) {

      tmp[t++] = A[i++]
      updateAnswer(k, aw, tmp[t - 1])
    } else {
      tmp[t++] = A[j++]
      updateAnswer(k, aw, tmp[t - 1])
    }
  }
  while (i <= q) {
    tmp[t++] = A[i++] // 전 나머지
    updateAnswer(k, aw, tmp[t - 1])
  }
  while (j <= r) {
    tmp[t++] = A[j++] // 후 나머지
    updateAnswer(k, aw, tmp[t - 1])
  }

  // copy: tmp[0 until t] -> A[p..r]
  System.arraycopy(tmp, 0, A, p, t)
}

fun updateAnswer(k: Int, aw: IntArray, v: Int) {
  aw[0]++
  if (aw[0] == k) {
    aw[1] = v
  }
}

/** 테스트용 */
fun solution(n: Int, arr: IntArray): Int {
  val sb = StringBuilder()
  solveTo(n, arr, sb)
  return sb.toString().toInt()
}

import java.io.*
import java.nio.CharBuffer
import java.util.*

fun main() {
  val st = StreamTokenizer(System.`in`.bufferedReader())
  val bw = BufferedWriter(OutputStreamWriter(System.out))

  while (true) {
    val t = st.nextToken()
    if (t == StreamTokenizer.TT_EOF) break
    if (t != StreamTokenizer.TT_NUMBER) continue

    val n = st.nval.toInt()
    solveTo(n, bw) // BufferedWriter를 그대로 넘김
  }

  bw.flush()
}

// 3^N 캐시 (N ≤ 12)
private val POW3 =
    IntArray(13).apply {
      this[0] = 1
      for (i in 1..12) this[i] = this[i - 1] * 3
    }

/** 제출용 */
fun solveTo(n: Int, out: Appendable) {
  val len = POW3[n]
  val arr = CharArray(len) { '-' }

  fun writeLine() {
    when (out) {
      is BufferedWriter -> {
        val bw = out as BufferedWriter
        bw.write(arr, 0, arr.size)
        bw.newLine()
      }
      is Writer -> {
        val w = out as Writer
        w.write(arr, 0, arr.size)
        out.append('\n')
      }
      else -> {
        // Appendable only: String 생성 대신 CharBuffer로 복사 없는 뷰 전달
        out.append(CharBuffer.wrap(arr))
        out.append('\n')
      }
    }
  }

  fun cantor(l: Int, r: Int) {

    val size = r - l + 1
    if (size < 3) return
    val third = size / 3

    Arrays.fill(arr, l + third, l + 2 * third, ' ')

    if (third >= 3) {
      cantor(l, l + third - 1)
      cantor(l + 2 * third, r)
    }
  }

  cantor(0, len - 1)

  writeLine()
}
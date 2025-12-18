import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val CODE_0 = 48
const val CODE_9 = 57

fun main() {
  val n = readInt()
  val m = readInt()
  val c = ByteArray(m * 2 - 1) { if (it % 2 == 1) ' '.code.toByte() else 0 }

  fun dfs(order: Int, pos: Int) {
    if (order == m) {
      OUT.write(c)
      OUT.write('\n'.code)
    } else {
      for (i in 1..n) {
        c[pos] = (i + CODE_0).toByte()
        dfs(order + 1, pos + 2)
      }
    }
  }
  dfs(0, 0)
  OUT.flush()
}

val IN = BufferedInputStream(System.`in`)
val OUT = BufferedOutputStream(System.`out`)

private fun readInt(): Int {
  var c = IN.read()
  while (c <= 32) c = IN.read()
  var n = 0
  while (c in CODE_0..CODE_9) {
    n = n * 10 + (c - CODE_0)
    c = IN.read()
  }
  return n
}
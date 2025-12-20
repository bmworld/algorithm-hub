import java.io.DataInputStream

private const val IBS = 160
private val I = DataInputStream(System.`in`)
private val IB = ByteArray(IBS)
private var Ii = 0
private var Il = 0
private const val EOF = -1

private fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

private val NUM = 48..57
private fun i(): Int {
  var v = 0
  var s = 1
  var c: Byte
  while (r().also { c = it } in NUM || c == 45.toByte()) {
    when (c) {
      in NUM -> v = v * 10 + c - 48
      else -> s = -1
    }
  }
  return s * v
}

private fun s(): Byte {
  var c = r()
  while (c !in ALPHABET && c != EMPTY) c = r()
  return c
}

private const val NODE_CNT = 26
private const val A = 65
private val ALPHABET = A until A + NODE_CNT
private const val EMPTY = 46.toByte()

fun main() {
  val n = i()
  val l = IntArray(NODE_CNT)
  val r = IntArray(NODE_CNT)
  repeat(n) {
    val root = s() - A
    val ln = s()
    if (ln != EMPTY) l[root] = ln - A
    val rn = s()
    if (rn != EMPTY) r[root] = rn - A
  }

  fun preOrder(
    v: Int,
  ) {
    print((v + A).toChar())

    val lv = l[v]
    if (lv > 0) preOrder(lv)

    val rv = r[v]
    if (rv > 0) preOrder(rv)

  }

  fun inOrder(v: Int) {
    val lv = l[v]
    if (lv > 0) inOrder(lv)

    print((v + A).toChar())

    val rv = r[v]
    if (rv > 0) inOrder(rv)
  }

  fun postOrder(v: Int) {
    val lv = l[v]
    if (lv > 0) postOrder(lv)

    val rv = r[v]
    if (rv > 0) postOrder(rv)

    print((v + A).toChar())
  }

  preOrder(0)
  println()

  inOrder(0)
  println()

  postOrder(0)
}
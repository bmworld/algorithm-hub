package 백준.Silver.no1935

import java.io.BufferedInputStream
import java.util.*

const val IBS = 1 shl 7
val I = BufferedInputStream(System.`in`)
val IB = ByteArray(IBS)
var Ii = 0
var Il = 0
const val EOF = -1

fun r(): Byte {
  if (Ii == Il) {
    Il = I.read(IB, 0, IBS)
    if (Il == EOF) IB[0] = EOF.toByte()
    Ii = 0
  }
  return IB[Ii++]
}

val NUM = 48..57
fun i(): Int {
  var v = 0
  var s = 1
  var b: Byte
  while (r().also { b = it } in NUM || b == 45.toByte()) {
    when (b) {
      in NUM -> v = v * 10 + b - 48
      else -> s = -1
    }
  }
  return s * v
}

fun main() {
  val MUL: Byte = 42
  val ADD: Byte = 43
  val SUB: Byte = 45
  val DIV: Byte = 47
  val A: Byte = 65
  val Z: Byte = 90
  val OPERAND = A..Z

  val OP_CNT = i()
  val postfix = ByteArray(10_000)
  var pi = 0
  var b: Byte
  while (r().also { b = it } >= MUL) postfix[pi++] = b

  val mapper = DoubleArray(OP_CNT) { i().toDouble() }
  val stack = Stack<Double>()
  repeat(pi) {
    val b = postfix[it]
    when (b) {
      in OPERAND -> stack.add(mapper[b - A])
      else -> {
        val nxt = stack.pop()
        val prv = stack.pop()
        val r = when (b) {
          ADD -> prv + nxt
          SUB -> prv - nxt
          MUL -> prv * nxt
          else -> prv / nxt
        }
        stack.add(r)
      }
    }
  }

  print("%.2f".format(stack.pop()))
}

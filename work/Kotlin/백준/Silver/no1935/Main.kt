package 백준.Silver.no1935

import java.io.BufferedInputStream

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
  var b: Byte
  while (r().also { b = it } in NUM) v = v * 10 + b - 48
  return v
}

const val Z: Byte = 90
const val A: Byte = 65
val OPERAND = A..Z
const val MAX_OP_SIZE = 10_000
const val MUL: Byte = 42
const val ADD: Byte = 43
const val SUB: Byte = 45
const val DIV: Byte = 47

fun main() {
  val OP_KINDS = i()
  val postfix = ByteArray(MAX_OP_SIZE)
  var pi = 0
  var b: Byte
  while (r().also { b = it } >= MUL) postfix[pi++] = b

  val mapper = DoubleArray(OP_KINDS) { i().toDouble() }
  val stack = DoubleArray(pi)
  var si = 0
  repeat(pi) {
    val b = postfix[it]
    when (b) {
      in OPERAND -> stack[si++] = mapper[b - A]
      else -> {
        val nxt = stack[--si]
        val prv = stack[--si]

        stack[si++] = when (b) {
          ADD -> prv + nxt
          SUB -> prv - nxt
          MUL -> prv * nxt
          else -> prv / nxt
        }
      }
    }
  }

  print("%.2f".format(stack[0]))
}

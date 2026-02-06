package 백준.Silver.no1406

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 600_000
const val OBS = 1 shl 12
val O = BufferedOutputStream(System.`out`, OBS)
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

const val L: Byte = 76
const val D: Byte = 68
const val B: Byte = 66
const val P: Byte = 80
val UPPER_CASE = 65..90
val LOWER_CASE = 97..122
fun getChar(): Byte {
  var b: Byte
  var char: Byte = 0
  while (r().also { b = it } in UPPER_CASE || b in LOWER_CASE) char = b
  return char
}

const val EMPTY: Byte = 0
const val MAX_LEN = 600_000
fun main() {
  val str = Array<Node>(MAX_LEN + 1) { Node() }
  var EOL = str[0].also { it.char = 47 }
  var initI = 1

  var i = initI
  var c: Byte
  while (r().also { c = it } in LOWER_CASE) {
    val prv = if (i > initI) str[i - 1] else null
    add(str, i++, c, prv, EOL)
  }

  val stt = str[initI]
  var head: Node = if (stt.char == null) EOL else stt
  var cur: Node = EOL

  repeat(i()) {
    when (getChar()) {
      L -> cur.prv.also { if (it != null) cur = it }
      D -> cur.nxt.also { if (it != null) cur = it }
      B -> {
        val p = cur.prv
        if (p == null) return@repeat
        p.prv.also {
          cur.prv = it
          if (it == null) head = cur else it.nxt = cur
        }
      }
      P -> add(str, i++, getChar(), cur.prv, cur).also {
        if (cur == head) head = it
      }
    }
  }

  while (head != EOL) {
    O.write(head.char!!.toInt())
    if (head.nxt == null) break
    else head = head.nxt!!
  }

  O.flush()
}

fun add(str: Array<Node>, pos: Int, char: Byte, prv: Node? = null, nxt: Node? = null): Node {
  val node = Node(char, prv, nxt).also {
    if (prv != null) prv.nxt = it
    if (nxt != null) nxt.prv = it
  }
  str[pos] = node
  return node
}

class Node(var char: Byte? = null,
  var prv: Node? = null,
  var nxt: Node? = null
)

//repeat(i) {
//  println("toChar(str[it]) = ${toChar(str[it].char ?: 32)}")
//}
//println(
//"prv=${toChar(cur.prv?.char ?: 32)}, " +
//"cur = ${toChar(cur.char ?: 32)}," +
//" nxt=${toChar(cur.nxt?.char ?: 32)}")

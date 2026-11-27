package 백준.Gold.no1379

import java.io.BufferedInputStream
import java.io.BufferedOutputStream

const val IBS = 1 shl 20
const val OBS = 1 shl 4
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

const val WS = 10
val WB = ByteArray(WS)
fun w(
  num: Int
) {
  var v = if (num >= 0) num
  else {
    O.write(45)
    -num
  }
  var pos = WS - 1
  do {
    WB[pos--] = (v % 10 + 48).toByte()
    v /= 10
  } while (v > 0)
  O.write(WB, ++pos, WS - pos)
  O.write(10)
}

val SEP = 10_000_000_000UL

fun main() {

  val N = i()
  val cnds = Array(N) {
    val i = i()
    val s = i().toULong()
    val e = i().toULong()
    ROOM(i, s * SEP + e)
  }

  qs(cnds, 0, N - 1)

  val rooms = HEAP(N)

  val roomInfo = IntArray(N + 1)
  var num = 1
  for (etr in cnds) {
    val perid = etr.e
    val n = etr.n
    val s = perid / SEP
    val e = perid % SEP
    etr.e = e

    roomInfo[n] =
      if (rooms.isNotEmpty() && s >= rooms.top().e)
        rooms.pop()!!.n.also { etr.n = it }
      else (num++).also { etr.n = it }

    rooms.push(etr)
  }

  w(rooms.len)
  for (i in 1..N) w(roomInfo[i])
  O.flush()
}

data class ROOM(var n: Int, var e: ULong)

class HEAP(size: Int) {

  private val heap = Array(size + 1) { ROOM(0, 0UL) }
  private val root = 1
  var len = 0

  fun isNotEmpty() = len > 0

  fun push(v: ROOM) {
    var ci = ++len
    heap[len] = v
    while (ci > root) {
      val pi = ci shr 1
      val p = heap[pi]
      val c = heap[ci]
      if (p.e > c.e) {
        heap[pi] = c
        heap[ci] = p
        ci = pi
      } else break
    }
  }

  fun pop(): ROOM? {
    if (len == 0) return null
    val v = heap[root]
    val x = heap[len--]

    var pi = root
    var ci = root shl 1
    while (ci <= len) {
      val ri = ci + 1
      if (ri <= len && heap[ri].e < heap[ci].e) ci++
      if (heap[ci].e >= x.e) break
      heap[pi] = heap[ci]
      pi = ci
      ci = pi shl 1
    }
    heap[pi] = x
    return v
  }

  fun top(): ROOM = heap[root]
}

fun swap(
  a: Array<ROOM>,
  i: Int,
  j: Int,
) {
  val tmp = a[i]
  a[i] = a[j]
  a[j] = tmp
}

fun qs(
  a: Array<ROOM>,
  l: Int,
  r: Int,
) {
  if (l >= r) return

  var pos = l
  var pl = l
  var pr = r
  val piv = a[(l + r) shr 1].e

  while (pos <= pr) {
    val x = a[pos].e
    when {
      x < piv -> swap(a, pos++, pl++)
      x > piv -> swap(a, pos, pr--)
      else -> pos++
    }
  }
  qs(a, l, pl - 1)
  qs(a, pr + 1, r)
}

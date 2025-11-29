import java.io.BufferedOutputStream

private const val OBS = 1 shl 1
private val O = BufferedOutputStream(System.`out`, OBS)
private val R = ByteArray(11).also {
  it[0] = 49
  it[1] = 53
  it[2] = 50

  it[3] = 10

  it[4] = 98
  it[5] = 109
  it[6] = 119
  it[7] = 111
  it[8] = 114
  it[9] = 108
  it[10] = 100
}

fun main() {
  O.write(R)
  O.flush()
}
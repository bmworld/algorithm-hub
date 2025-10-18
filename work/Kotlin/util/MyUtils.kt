package util

fun getStringArr(s: String): Array<String> {
  return s.trim().lines().toTypedArray()
}

fun getStringSet(s: String): LinkedHashSet<String> {
  val set = LinkedHashSet<String>()
  s.trim().lines().forEach { set.add(it) }
  return set
}

fun getIntArr(s: String): IntArray {
  return s.trim().split(" ").map { it.toInt() }.toIntArray()
}

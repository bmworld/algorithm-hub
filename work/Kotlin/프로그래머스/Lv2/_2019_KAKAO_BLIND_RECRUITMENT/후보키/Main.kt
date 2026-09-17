package 프로그래머스.Lv2._2019_KAKAO_BLIND_RECRUITMENT.후보키

import util.validate
import java.util.HashMap

class Solution {

  fun solution(relation: Array<Array<String>>): Int {

    val R = relation.size
    val C = relation[0].size
    val rel = LongArray(R * C)
    fun pos(r: Int, c: Int): Int = r * C + c

    var standard = 1L
    val strMapper = HashMap<String, Long>()
    for (c in 0 until C) {
      var uniqN = standard
      for (r in 0 until R) {
        val str = relation[r][c]
        val n = strMapper[str]
        if (n == null) {
          strMapper[str] = uniqN.also { rel[pos(r, c)] = it }
          uniqN += standard
        } else rel[pos(r, c)] = n
      }

      strMapper.clear()
      standard *= 100
    }

    val uniqCombs = HashSet<Int>()
    val combined = BooleanArray(C)
    val combCh = HashSet<Long>()
    fun dfs(dep: Int, stt: Int, len: Int) {
      if (dep == len) {

        var cand = 0
        for (c in 0 until C) if (combined[c]) cand = cand or (1 shl c)

        for (comn in uniqCombs) if (cand and comn == comn) return


        combCh.clear()
        for (r in 0 until R) {
          var comb = 0L
          for (c in 0 until C) if (combined[c]) comb += rel[pos(r, c)]

          if (combCh.contains(comb)) return
          else combCh.add(comb)
        }

        uniqCombs.add(cand)
        return
      }

      for (i in stt until C - (len - 1) + dep) {
        combined[i] = true
        dfs(dep + 1, i + 1, len)
        combined[i] = false
      }
    }

    for (comb in 1..C) dfs(0, 0, comb)

    return uniqCombs.size
  }
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.11ms, 60.2MB)
 * 테스트 2 〉	통과 (0.15ms, 59.3MB)
 * 테스트 3 〉	통과 (0.16ms, 59.8MB)
 * 테스트 4 〉	통과 (0.19ms, 59.3MB)
 * 테스트 5 〉	통과 (0.14ms, 60.2MB)
 * 테스트 6 〉	통과 (0.09ms, 59.7MB)
 * 테스트 7 〉	통과 (0.09ms, 60.3MB)
 * 테스트 8 〉	통과 (0.08ms, 58MB)
 * 테스트 9 〉	통과 (0.20ms, 59.5MB)
 * 테스트 10 〉	통과 (0.19ms, 60.5MB)
 * 테스트 11 〉	통과 (0.22ms, 60.5MB)
 * 테스트 12 〉	통과 (0.64ms, 59.7MB)
 * 테스트 13 〉	통과 (0.42ms, 60MB)
 * 테스트 14 〉	통과 (0.11ms, 60.3MB)
 * 테스트 15 〉	통과 (0.17ms, 59.3MB)
 * 테스트 16 〉	통과 (0.14ms, 59MB)
 * 테스트 17 〉	통과 (0.13ms, 59.3MB)
 * 테스트 18 〉	통과 (0.89ms, 59.7MB)
 * 테스트 19 〉	통과 (0.90ms, 59.9MB)
 * 테스트 20 〉	통과 (1.06ms, 58.9MB)
 *
 *
 *
 * [RIVAL 1]
 * class Solution {
 *
 *     data class Database(val records: List<Record>) {
 *
 *         fun getUniqueKeys(): Set<Key> {
 *             val masterKey = Key(List(records.first().columns.size) { it }.toSet())
 *             return findAllUniqueKeys(masterKey)
 *         }
 *
 *         private fun findAllUniqueKeys(key: Key): Set<Key> {
 *             return if (!definedUniquelyBy(key)) {
 *                 emptySet()
 *             } else {
 *                 key.subKeys()
 *                     .flatMap(::findAllUniqueKeys).toSet()
 *                     .takeIf { it.isNotEmpty() } ?: setOf(key)
 *             }
 *         }
 *
 *         private fun definedUniquelyBy(key: Key): Boolean {
 *             return records.groupBy { record ->
 *                 // pick only record's columns defined by key
 *                 record.columns.filterIndexed { index, _ -> index in key.columns }
 *             }.size == records.size
 *         }
 *     }
 *
 *     data class Record(val columns: List<String>)
 *
 *     data class Key(val columns: Set<Int>) {
 *
 *         fun subKeys(): List<Key> = columns.map { Key(columns - it) }
 *     }
 *
 *     fun solution(relation: Array<Array<String>>): Int {
 *         return Database(relation.map {
 *             Record(it.toList())
 *         }).getUniqueKeys().size
 *     }
 * }
 * 테스트 1 〉	통과 (23.36ms, 64MB)
 * 테스트 2 〉	통과 (16.35ms, 64.3MB)
 * 테스트 3 〉	통과 (16.99ms, 63.8MB)
 * 테스트 4 〉	통과 (17.24ms, 64MB)
 * 테스트 5 〉	통과 (17.10ms, 64.4MB)
 * 테스트 6 〉	통과 (15.19ms, 64.1MB)
 * 테스트 7 〉	통과 (15.51ms, 64.9MB)
 * 테스트 8 〉	통과 (16.20ms, 63.1MB)
 * 테스트 9 〉	통과 (17.19ms, 64.7MB)
 * 테스트 10 〉	통과 (23.73ms, 63.6MB)
 * 테스트 11 〉	통과 (22.00ms, 64.9MB)
 * 테스트 12 〉	통과 (74.15ms, 122MB)
 * 테스트 13 〉	통과 (26.39ms, 65.6MB)
 * 테스트 14 〉	통과 (16.38ms, 65.2MB)
 * 테스트 15 〉	통과 (16.95ms, 62.7MB)
 * 테스트 16 〉	통과 (19.50ms, 64.7MB)
 * 테스트 17 〉	통과 (17.10ms, 64.5MB)
 * 테스트 18 〉	통과 (218.24ms, 289MB)
 * 테스트 19 〉	통과 (100.96ms, 130MB)
 * 테스트 20 〉	통과 (152.95ms, 178MB)
 *
 *
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(relation: Array<Array<String>>): Int {
 *         if (relation.isEmpty()) {
 *             return 0
 *         }
 *
 *         val binaryFlag = getBinaryFlag(relation[0])
 *         val candidateKeyMap = BooleanArray(binaryFlag) { true }
 *         val allCombinationSet: MutableSet<String> = mutableSetOf()
 *
 *         for (row in relation) {
 *             addRow(binaryFlag, row, candidateKeyMap, allCombinationSet)
 *         }
 *
 *         val result = getMinimumCount(candidateKeyMap)
 *         return result
 *     }
 *
 *     private fun getMinimumCount(candidateKeyMap: BooleanArray): Int {
 *         for (idx in 0 until candidateKeyMap.size) {
 *             if (candidateKeyMap[idx]) {
 *                 for (idx2 in idx + 1 until candidateKeyMap.size) {
 *                     if (candidateKeyMap[idx2] &&
 *                         (idx + 1) and (idx2 + 1) == (idx + 1)
 *                     ) {
 *                         candidateKeyMap[idx2] = false
 *                     }
 *                 }
 *             }
 *         }
 *         return candidateKeyMap.filter { it }.size
 *     }
 *
 *     private fun addRow(
 *         binaryFlag: Int,
 *         row: Array<String>,
 *         candidateKeyMap: BooleanArray,
 *         allCombinationSet: MutableSet<String>
 *     ) {
 *
 *         for (idx in 0 until binaryFlag) {
 *             if (!candidateKeyMap[idx]) {
 *                 continue
 *             }
 *
 *             val indexNumber: Array<Int> = getIndexNumber(idx)
 *             val sb = StringBuffer()
 *
 *             for (recordFlag in indexNumber) {
 *                 sb.append(recordFlag)
 *                 sb.append("+")
 *                 sb.append(row[getIndexFromFlag(recordFlag)])
 *                 sb.append("+")
 *             }
 *
 *             val combination = sb.toString()
 *
 *             if (allCombinationSet.contains(combination)) {
 *                 candidateKeyMap[idx] = false
 *                 continue
 *             }
 *             allCombinationSet += combination
 *         }
 *     }
 *
 *     private fun getIndexFromFlag(recordFlag: Int): Int {
 *         var binaryFlag = 1
 *         var result = 0
 *
 *         while (binaryFlag and recordFlag == 0) {
 *             binaryFlag = binaryFlag shl (1)
 *             result++
 *         }
 *         return result
 *     }
 *
 *     private fun getIndexNumber(idx: Int): Array<Int> {
 *         val newIdx = idx + 1
 *         var result: Array<Int> = arrayOf()
 *         var checker: Int = 1
 *         while (checker <= newIdx) {
 *             val cursor = (checker and newIdx)
 *             if (cursor != 0) {
 *                 result += checker
 *             }
 *             checker = checker shl 1
 *         }
 *         return result
 *     }
 *
 *     private fun getBinaryFlag(row: Array<String>): Int {
 *         val size: Int = row.size
 *         var binaryFlag = 1
 *         for (idx in 1 until size) {
 *             binaryFlag = binaryFlag shl (1)
 *             binaryFlag = binaryFlag or 1
 *         }
 *         return binaryFlag
 *     }
 * }
 * 테스트 1 〉	통과 (7.03ms, 63.2MB)
 * 테스트 2 〉	통과 (6.82ms, 62.6MB)
 * 테스트 3 〉	통과 (6.71ms, 63.3MB)
 * 테스트 4 〉	통과 (7.94ms, 62.7MB)
 * 테스트 5 〉	통과 (6.73ms, 63.7MB)
 * 테스트 6 〉	통과 (6.56ms, 63.8MB)
 * 테스트 7 〉	통과 (6.56ms, 63.2MB)
 * 테스트 8 〉	통과 (6.54ms, 63.4MB)
 * 테스트 9 〉	통과 (7.26ms, 63MB)
 * 테스트 10 〉	통과 (13.58ms, 62.1MB)
 * 테스트 11 〉	통과 (10.09ms, 62MB)
 * 테스트 12 〉	통과 (14.27ms, 62.3MB)
 * 테스트 13 〉	통과 (8.24ms, 64.1MB)
 * 테스트 14 〉	통과 (6.77ms, 62.8MB)
 * 테스트 15 〉	통과 (6.86ms, 63.7MB)
 * 테스트 16 〉	통과 (9.79ms, 62.4MB)
 * 테스트 17 〉	통과 (6.75ms, 63.9MB)
 * 테스트 18 〉	통과 (16.83ms, 66.1MB)
 * 테스트 19 〉	통과 (12.08ms, 63.6MB)
 * 테스트 20 〉	통과 (13.49ms, 65.4MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      arrayOf("100", "ryan", "music", "2"),
      arrayOf("200", "apeach", "math", "2"),
      arrayOf("300", "tube", "computer", "3"),
      arrayOf("400", "con", "computer", "4"),
      arrayOf("500", "muzi", "music", "3"),
      arrayOf("600", "apeach", "music", "2"),
    )
  ), 2)
}

//println("relation[$r][$c] = ${relation[r][c]} -> x=$x")
//repeat(R) { r ->
//  repeat(C) { c ->
//    println("rel[pos($r,$c)] = ${rel[pos(r, c)]}")
//  }
//}
//        println("[$len] ${cand.toString(2)}")

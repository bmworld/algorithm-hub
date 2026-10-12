package 프로그래머스.Lv2._2021_KAKAO_BLIND_RECRUITMENT.순위검색

import util.validate
import java.util.HashMap

class Solution {
  companion object {

    const val ZERO = 48
    const val NA = -1
    val NUM = ZERO..ZERO + 9

    const val LANG = 0
    const val PSTN = 1
    const val CRRE = 2
    const val FOOD = 3

    const val CCP = 0
    const val JAVA = 1
    const val PTHN = 2

    const val BE = 3
    const val FE = 4

    const val JNR = 5
    const val SNR = 6

    const val CHKN = 7
    const val PZZA = 8
  }

  fun solution(info: Array<String>, query: Array<String>): IntArray {
    val M = query.size
    val ans = IntArray(M)
    val infoByCond = HashMap<Int, MutableList<Int>>()

    // Preprocessing
    for (l in intArrayOf(NA, CCP, JAVA, PTHN)) {
      for (p in intArrayOf(NA, BE, FE)) {
        for (c in intArrayOf(NA, JNR, SNR)) {
          for (f in intArrayOf(NA, CHKN, PZZA)) {
            infoByCond[
              (if (l == NA) 0 else 1 shl l) or
                (if (p == NA) 0 else 1 shl p) or
                (if (c == NA) 0 else 1 shl c) or
                (if (f == NA) 0 else 1 shl f)
            ] = mutableListOf()
          }
        }
      }
    }

    // Parse
    for (i in info.indices) {
      val str = info[i]
      var score = 0
      var type = LANG

      var j = 0
      var t1 = 0
      var t2 = 0
      var t3 = 0
      var t4 = 0
      while (j < str.length) {
        val c = str[j]
        when (c.code) {
          in NUM -> {
            score = c.code - ZERO
            for (k in j + 1 until str.length)
              score = score * 10 + str[k].code - ZERO
            break
          }
          else -> {
            val (pos, jump) = getBinPosAndJump(type, c, false)
            when (type++) {
              LANG -> t1 = 1 shl pos
              PSTN -> t2 = 1 shl pos
              CRRE -> t3 = 1 shl pos
              FOOD -> t4 = 1 shl pos
            }
            j += jump
          }
        }
      }

      infoByCond[0]!!.add(score)

      infoByCond[t1]!!.add(score)
      infoByCond[t2]!!.add(score)
      infoByCond[t3]!!.add(score)
      infoByCond[t4]!!.add(score)

      infoByCond[t1 or t2]!!.add(score)
      infoByCond[t1 or t3]!!.add(score)
      infoByCond[t1 or t4]!!.add(score)
      infoByCond[t2 or t3]!!.add(score)
      infoByCond[t2 or t4]!!.add(score)
      infoByCond[t3 or t4]!!.add(score)

      infoByCond[t1 or t2 or t3]!!.add(score)
      infoByCond[t1 or t2 or t4]!!.add(score)
      infoByCond[t1 or t3 or t4]!!.add(score)
      infoByCond[t2 or t3 or t4]!!.add(score)

      infoByCond[t1 or t2 or t3 or t4]!!.add(score)

    }

    // Sort
    for (e in infoByCond) {
      val arr = e.value
      qs(arr, 0, arr.size - 1)
    }

    // Query
    for (i in query.indices) {
      val str = query[i]

      var cond = 0
      var minScore = 0
      var j = 0
      var type = LANG
      while (j < str.length) {
        val c = str[j]
        when (c.code) {
          in NUM -> {
            minScore = c.code - ZERO
            for (k in j + 1 until str.length)
              minScore = minScore * 10 + str[k].code - ZERO
            break
          }
          '-'.code -> j += if (type++ < FOOD) 6 else 2
          else -> getBinPosAndJump(type++, c, true).also {
            val (pos, jump) = it
            cond = cond or (1 shl pos)
            j += jump
          }
        }
      }

      ans[i] = lowerBound(infoByCond[cond]!!, minScore)
    }

    return ans
  }

  private fun lowerBound(arr: MutableList<Int>, score: Int): Int {
    var l = 0
    val all = arr.size
    var r = all
    while (l < r) {
      val m = (l + r) shr 1
      when {
        arr[m] >= score -> r = m
        else -> l = m + 1
      }
    }

    return all - r
  }

  private fun getBinPosAndJump(type: Int, c: Char, isQuery: Boolean): Pair<Int, Int> {
    var jump = 0
    val pos = when (type) {
      LANG -> when (c) {
        'c' -> {
          jump += 4
          CCP
        }
        'j' -> {
          jump += 5
          JAVA
        }
        'p' -> {
          jump += 7
          PTHN
        }
        else -> throw Exception()
      }
      PSTN -> when (c) {
        'b' -> {
          jump += 8
          BE
        }
        'f' -> {
          jump += 9
          FE
        }
        else -> throw Exception()
      }
      CRRE -> when (c) {
        'j' -> {
          jump += 7
          JNR
        }
        's' -> {
          jump += 7
          SNR
        }
        else -> throw Exception()
      }
      FOOD -> when (c) {
        'c' -> {
          jump += 8
          CHKN
        }
        'p' -> {
          jump += 6
          PZZA
        }
        else -> throw Exception()
      }
      else -> throw Exception()
    }
    return Pair(
      pos,
      jump + if (isQuery && type < FOOD) 4 else 0
    )
  }


  fun swap(
    a: MutableList<Int>,
    i: Int,
    j: Int,
  ) {
    val tmp = a[i]
    a[i] = a[j]
    a[j] = tmp
  }

  fun qs(
    a: MutableList<Int>,
    l: Int,
    r: Int,
  ) {
    if (l >= r) return

    var pos = l
    var pl = l
    var pr = r
    val piv = a[(l + r) shr 1]

    while (pos <= pr) {
      val x = a[pos]
      when {
        x < piv -> swap(a, pos++, pl++)
        x > piv -> swap(a, pos, pr--)
        else -> pos++
      }
    }
    qs(a, l, pl - 1)
    qs(a, pr + 1, r)
  }
}

/**
 * ```
 * [ME]
 * v1: WA
 * 정확성 테스트
 * 테스트 1 〉	통과 (0.06ms, 59.9MB)
 * 테스트 2 〉	통과 (0.06ms, 58.7MB)
 * 테스트 3 〉	통과 (0.21ms, 60.5MB)
 * 테스트 4 〉	통과 (1.23ms, 61MB)
 * 테스트 5 〉	통과 (2.51ms, 60.1MB)
 * 테스트 6 〉	통과 (3.98ms, 59.9MB)
 * 테스트 7 〉	통과 (3.11ms, 61.8MB)
 * 테스트 8 〉	통과 (7.11ms, 61.8MB)
 * 테스트 9 〉	통과 (8.34ms, 62.6MB)
 * 테스트 10 〉	통과 (8.00ms, 63.8MB)
 * 테스트 11 〉	통과 (2.30ms, 59.6MB)
 * 테스트 12 〉	통과 (3.88ms, 60.8MB)
 * 테스트 13 〉	통과 (3.80ms, 61.7MB)
 * 테스트 14 〉	통과 (7.70ms, 62.1MB)
 * 테스트 15 〉	통과 (7.68ms, 61.3MB)
 * 테스트 16 〉	통과 (2.50ms, 61.5MB)
 * 테스트 17 〉	통과 (4.51ms, 59.9MB)
 * 테스트 18 〉	통과 (7.25ms, 62.2MB)
 * 효용성 테스트
 * 테스트 1 〉	실패 (시간 초과)
 * 테스트 2 〉	실패 (시간 초과)
 * 테스트 3 〉	실패 (시간 초과)
 * 테스트 4 〉	실패 (시간 초과)
 *
 * v2: WA
 * 정확성  테스트
 * 테스트 1 〉	통과 (0.85ms, 60.1MB)
 * 테스트 2 〉	통과 (0.96ms, 60.2MB)
 * 테스트 3 〉	통과 (1.28ms, 59.2MB)
 * 테스트 4 〉	통과 (1.85ms, 61.6MB)
 * 테스트 5 〉	실패 (3.38ms, 62.2MB)
 * 테스트 6 〉	실패 (7.58ms, 63.2MB)
 * 테스트 7 〉	실패 (3.30ms, 62.9MB)
 * 테스트 8 〉	실패 (26.26ms, 69.3MB)
 * 테스트 9 〉	실패 (37.56ms, 78.9MB)
 * 테스트 10 〉	실패 (37.54ms, 81.9MB)
 * 테스트 11 〉	통과 (3.13ms, 62.3MB)
 * 테스트 12 〉	통과 (7.50ms, 66.5MB)
 * 테스트 13 〉	통과 (3.60ms, 61.3MB)
 * 테스트 14 〉	통과 (30.57ms, 73MB)
 * 테스트 15 〉	통과 (21.90ms, 74.3MB)
 * 테스트 16 〉	실패 (3.19ms, 61MB)
 * 테스트 17 〉	통과 (7.85ms, 66.3MB)
 * 테스트 18 〉	실패 (21.37ms, 75.2MB)
 * 효율성  테스트
 * 테스트 1 〉	실패 (380.20ms, 252MB)
 * 테스트 2 〉	실패 (392.49ms, 254MB)
 * 테스트 3 〉	실패 (362.90ms, 242MB)
 * 테스트 4 〉	실패 (393.08ms, 252MB)
 *
 * v3: AC
 * 테스트 1 〉	통과 (1.35ms, 59.9MB)
 * 테스트 2 〉	통과 (1.22ms, 59.9MB)
 * 테스트 3 〉	통과 (1.13ms, 60.3MB)
 * 테스트 4 〉	통과 (2.63ms, 61.1MB)
 * 테스트 5 〉	통과 (3.57ms, 60.7MB)
 * 테스트 6 〉	통과 (7.15ms, 64.1MB)
 * 테스트 7 〉	통과 (3.75ms, 63.1MB)
 * 테스트 8 〉	통과 (29.63ms, 68.6MB)
 * 테스트 9 〉	통과 (43.56ms, 78.9MB)
 * 테스트 10 〉	통과 (37.92ms, 81.8MB)
 * 테스트 11 〉	통과 (3.34ms, 61.4MB)
 * 테스트 12 〉	통과 (9.46ms, 66.4MB)
 * 테스트 13 〉	통과 (4.76ms, 61.7MB)
 * 테스트 14 〉	통과 (24.99ms, 73.5MB)
 * 테스트 15 〉	통과 (21.51ms, 74.4MB)
 * 테스트 16 〉	통과 (3.48ms, 60.9MB)
 * 테스트 17 〉	통과 (7.52ms, 65.1MB)
 * 테스트 18 〉	통과 (21.35ms, 73.1MB)
 * 효율성  테스트
 * 테스트 1 〉	통과 (395.05ms, 259MB)
 * 테스트 2 〉	통과 (417.09ms, 260MB)
 * 테스트 3 〉	통과 (378.18ms, 255MB)
 * 테스트 4 〉	통과 (400.84ms, 252MB)
 *
 * v4: 개선 (Binary Search -> Lower Bound)
 * 테스트 1 〉	통과 (0.93ms, 59.8MB)
 * 테스트 2 〉	통과 (1.03ms, 60MB)
 * 테스트 3 〉	통과 (1.18ms, 59.3MB)
 * 테스트 4 〉	통과 (1.90ms, 58.8MB)
 * 테스트 5 〉	통과 (4.01ms, 61.1MB)
 * 테스트 6 〉	통과 (8.03ms, 63MB)
 * 테스트 7 〉	통과 (3.07ms, 62.7MB)
 * 테스트 8 〉	통과 (26.79ms, 69.6MB)
 * 테스트 9 〉	통과 (39.48ms, 80.1MB)
 * 테스트 10 〉	통과 (37.41ms, 82.1MB)
 * 테스트 11 〉	통과 (3.15ms, 60.4MB)
 * 테스트 12 〉	통과 (7.60ms, 65.7MB)
 * 테스트 13 〉	통과 (3.43ms, 62.3MB)
 * 테스트 14 〉	통과 (21.99ms, 73.3MB)
 * 테스트 15 〉	통과 (21.85ms, 74.3MB)
 * 테스트 16 〉	통과 (3.03ms, 61.5MB)
 * 테스트 17 〉	통과 (7.73ms, 66.2MB)
 * 테스트 18 〉	통과 (21.47ms, 74.6MB)
 * 효율성  테스트
 * 테스트 1 〉	통과 (369.41ms, 236MB)
 * 테스트 2 〉	통과 (384.70ms, 252MB)
 * 테스트 3 〉	통과 (361.65ms, 251MB)
 * 테스트 4 〉	통과 (378.95ms, 251MB)
 *
 * v5: 개선 (Proprocessing 에서 user 에 해당되는 항목만)
 * 테스트 1 〉	통과 (0.74ms, 60.6MB)
 * 테스트 2 〉	통과 (0.76ms, 60MB)
 * 테스트 3 〉	통과 (1.27ms, 59.5MB)
 * 테스트 4 〉	통과 (1.63ms, 61.9MB)
 * 테스트 5 〉	통과 (3.99ms, 61.4MB)
 * 테스트 6 〉	통과 (4.73ms, 64.6MB)
 * 테스트 7 〉	통과 (2.72ms, 61.3MB)
 * 테스트 8 〉	통과 (18.73ms, 69.9MB)
 * 테스트 9 〉	통과 (39.69ms, 77.5MB)
 * 테스트 10 〉	통과 (30.10ms, 80.2MB)
 * 테스트 11 〉	통과 (2.95ms, 61.7MB)
 * 테스트 12 〉	통과 (5.44ms, 65.7MB)
 * 테스트 13 〉	통과 (3.24ms, 63.5MB)
 * 테스트 14 〉	통과 (15.62ms, 72.5MB)
 * 테스트 15 〉	통과 (16.59ms, 75.1MB)
 * 테스트 16 〉	통과 (3.00ms, 60.8MB)
 * 테스트 17 〉	통과 (4.88ms, 66.3MB)
 * 테스트 18 〉	통과 (15.86ms, 74MB)
 * 효율성  테스트
 * 테스트 1 〉	통과 (506.08ms, 281MB)
 * 테스트 2 〉	통과 (466.34ms, 272MB)
 * 테스트 3 〉	통과 (436.23ms, 269MB)
 * 테스트 4 〉	통과 (436.02ms, 265MB)
 *
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(info: Array<String>, query: Array<String>): IntArray {
 *         val root = Node()
 *
 *         for (record in info) {
 *             root.addRecord(record.split(" "))
 *         }
 *
 *         root.sortScores()
 *
 *         val answer = IntArray(query.size)
 *         for ((index, q) in query.withIndex()) {
 *             answer[index] = root.count(q.split(" ").filterNot { it == "and" })
 *             // println("#$index: ${answer[index]}")
 *         }
 *         return answer
 *     }
 *
 *     class Node {
 *         var scores: MutableList<Int>? = null
 *         val children: MutableMap<String, Node> = mutableMapOf()
 *
 *         fun addRecord(record: List<String>, depth: Int = 0) {
 *             val key = record[depth]
 *             if (depth == 4) {
 *                 val score = key.toInt()
 *
 *                 if (scores == null)
 *                     scores = mutableListOf(score)
 *                 else
 *                     scores!!.add(score)
 *             } else {
 *                 if (children[key] == null)
 *                     children[key] = Node()
 *
 *                 children[key]!!.addRecord(record, depth + 1)
 *             }
 *         }
 *
 *         fun sortScores(depth: Int = 0) {
 *             if (depth == 4) {
 *                 scores!!.sort()
 *             } else {
 *                 for (child in children.values)
 *                     child.sortScores(depth + 1)
 *             }
 *         }
 *
 *         fun count(query: List<String>, depth: Int = 0): Int {
 *             val key = query[depth]
 *             if (depth == 4) {
 *                 return scores!!.size - lowerBound(key.toInt())
 *             } else {
 *                 var sum = 0
 *                 if (key == "-") {
 *                     for (child in children.values)
 *                         sum += child.count(query, depth + 1)
 *                 } else {
 *                     sum += children[key]?.count(query, depth + 1) ?: 0
 *                 }
 *                 return sum
 *             }
 *         }
 *
 *         private fun lowerBound(least: Int): Int {
 *             if (least > scores!!.last()) return scores!!.size
 *
 *             var top = scores!!.lastIndex
 *             var bottom = 0
 *             var mid = top
 *
 *             while (top > bottom) {
 *                 val mid = (bottom + top) / 2
 *                 if (mid == top)
 *                     return top
 *
 *                 if (scores!![mid] < least) {
 *                     bottom = mid + 1
 *                 } else {
 *                     top = mid
 *                 }
 *             }
 *             return bottom
 *         }
 *     }
 * }
 * 테스트 1 〉	통과 (10.81ms, 59.4MB)
 * 테스트 2 〉	통과 (9.68ms, 61.8MB)
 * 테스트 3 〉	통과 (10.84ms, 61.6MB)
 * 테스트 4 〉	통과 (14.74ms, 63MB)
 * 테스트 5 〉	통과 (19.78ms, 71.8MB)
 * 테스트 6 〉	통과 (18.45ms, 63.8MB)
 * 테스트 7 〉	통과 (26.05ms, 73MB)
 * 테스트 8 〉	통과 (17.83ms, 71.7MB)
 * 테스트 9 〉	통과 (20.70ms, 72MB)
 * 테스트 10 〉	통과 (27.02ms, 72.2MB)
 * 테스트 11 〉	통과 (15.99ms, 70.7MB)
 * 테스트 12 〉	통과 (13.63ms, 62.7MB)
 * 테스트 13 〉	통과 (17.52ms, 72.5MB)
 * 테스트 14 〉	통과 (15.90ms, 70.6MB)
 * 테스트 15 〉	통과 (18.83ms, 70.1MB)
 * 테스트 16 〉	통과 (13.67ms, 70.1MB)
 * 테스트 17 〉	통과 (22.67ms, 63.1MB)
 * 테스트 18 〉	통과 (15.41ms, 71MB)
 * 효율성  테스트
 * 테스트 1 〉	통과 (293.05ms, 208MB)
 * 테스트 2 〉	통과 (340.90ms, 216MB)
 * 테스트 3 〉	실행 중단
 * 테스트 4 〉	통과 (559.47ms, 206MB)
 *
 *
 * [RIVAL 2]
 * class Solution {
 *     fun solution(info: Array<String>, query: Array<String>): IntArray {
 *         var answer = Array(query.size){it}
 *         val hashInfo = HashMap<List<String>, MutableList<Int>>()
 *
 *         val language = listOf("java", "cpp", "python", "-")
 *         val position = listOf("backend", "frontend", "-")
 *         val career = listOf("junior", "senior", "-")
 *         val soulFood = listOf("pizza", "chicken", "-")
 *
 *         for (l in language){
 *             for (p in position){
 *                 for (c in career){
 *                     for (s in soulFood){
 *                         hashInfo[listOf(l, p, c, s)] = mutableListOf()
 *                     }
 *                 }
 *             }
 *         }
 *
 *         for (data in info){
 *             val temp = data.split(" ")
 *             hashInfo[listOf(temp[0], temp[1], temp[2], temp[3])]?.add(temp[4].toInt())
 *             hashInfo[listOf("-", temp[1], temp[2], temp[3])]?.add(temp[4].toInt())
 *             hashInfo[listOf(temp[0], "-", temp[2], temp[3])]?.add(temp[4].toInt())
 *             hashInfo[listOf(temp[0], temp[1], "-", temp[3])]?.add(temp[4].toInt())
 *             hashInfo[listOf(temp[0], temp[1], temp[2], "-")]?.add(temp[4].toInt())
 *             hashInfo[listOf("-", "-", temp[2], temp[3])]?.add(temp[4].toInt())
 *             hashInfo[listOf("-", temp[1], "-", temp[3])]?.add(temp[4].toInt())
 *             hashInfo[listOf("-", temp[1], temp[2], "-")]?.add(temp[4].toInt())
 *             hashInfo[listOf(temp[0], "-", "-", temp[3])]?.add(temp[4].toInt())
 *             hashInfo[listOf(temp[0], "-", temp[2], "-")]?.add(temp[4].toInt())
 *             hashInfo[listOf(temp[0], temp[1], "-", "-")]?.add(temp[4].toInt())
 *             hashInfo[listOf("-", "-", "-", temp[3])]?.add(temp[4].toInt())
 *             hashInfo[listOf("-", "-", temp[2], "-")]?.add(temp[4].toInt())
 *             hashInfo[listOf("-", temp[1], "-", "-")]?.add(temp[4].toInt())
 *             hashInfo[listOf(temp[0], "-", "-", "-")]?.add(temp[4].toInt())
 *             hashInfo[listOf("-", "-", "-", "-")]?.add(temp[4].toInt())
 *         }
 *
 *         for (i in hashInfo){
 *             hashInfo[i.key]?.sort()
 *         }
 *
 *         for ((idx, q) in query.withIndex()){
 *             val temp = q.split(" and ", " ")
 *
 *             if (hashInfo[listOf(temp[0], temp[1], temp[2], temp[3])]!!.isNotEmpty()){
 *                 answer[idx] = hashInfo[listOf(temp[0], temp[1], temp[2], temp[3])]!!.size - lowerBound(hashInfo[listOf(temp[0], temp[1], temp[2], temp[3])]!!, if (temp[4] == "-") 0 else temp[4].toInt())
 *             }
 *             else{
 *                 answer[idx] = 0
 *             }
 *         }
 *
 *         return answer.toIntArray()
 *     }
 *
 *     fun lowerBound(arr: MutableList<Int>, target: Int): Int{
 *         var start = 0
 *         var end = arr.size
 *
 *         while (start < end){
 *             val mid = (start + end) / 2
 *             if (arr[mid] < target)
 *                 start = mid + 1
 *             else
 *                 end = mid
 *         }
 *
 *         return end
 *     }
 * }
 * 테스트 1 〉	통과 (26.56ms, 65.6MB)
 * 테스트 2 〉	통과 (27.71ms, 66.3MB)
 * 테스트 3 〉	통과 (28.31ms, 65.9MB)
 * 테스트 4 〉	통과 (34.70ms, 69.4MB)
 * 테스트 5 〉	통과 (34.29ms, 72.1MB)
 * 테스트 6 〉	통과 (56.19ms, 70.8MB)
 * 테스트 7 〉	통과 (38.57ms, 73.1MB)
 * 테스트 8 〉	통과 (92.28ms, 80.2MB)
 * 테스트 9 〉	통과 (83.88ms, 83.2MB)
 * 테스트 10 〉	통과 (70.90ms, 84.1MB)
 * 테스트 11 〉	통과 (35.01ms, 72.5MB)
 * 테스트 12 〉	통과 (50.25ms, 71MB)
 * 테스트 13 〉	통과 (37.87ms, 73.4MB)
 * 테스트 14 〉	통과 (64.23ms, 75.9MB)
 * 테스트 15 〉	통과 (88.97ms, 76.5MB)
 * 테스트 16 〉	통과 (34.59ms, 71.6MB)
 * 테스트 17 〉	통과 (43.61ms, 71.6MB)
 * 테스트 18 〉	통과 (53.02ms, 77.5MB)
 * 효율성  테스트
 * 테스트 1 〉	통과 (725.08ms, 274MB)
 * 테스트 2 〉	통과 (720.54ms, 279MB)
 * 테스트 3 〉	통과 (707.06ms, 279MB)
 * 테스트 4 〉	통과 (1013.29ms, 273MB)
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      "java backend junior pizza 150",
      "python frontend senior chicken 210",
      "python frontend senior chicken 150",
      "cpp backend senior pizza 260",
      "java backend junior chicken 80",
      "python backend senior chicken 50",
//      "python frontend senior pizza 100000" // <-- 테스트용
    ),
    arrayOf(
      "java and backend and junior and pizza 100",
      "python and frontend and senior and chicken 200",
      "cpp and - and senior and pizza 250",
      "- and backend and senior and - 150",
      "- and - and - and chicken 100",
      "- and - and - and - 150",
      "- and - and - and - 160"
    )
  ),
    intArrayOf(1, 1, 1, 1, 2, 4, 2))

  validate(s.solution(
    arrayOf(
      "java backend junior pizza 100"
    ),
    arrayOf(
      "java and backend and junior and pizza 101"
    )
  ), intArrayOf(0))

  validate(s.solution(
    arrayOf(
      "java backend junior pizza 100",
      "java backend junior pizza 100",
      "java backend junior pizza 100"
    ),
    arrayOf(
      "java and backend and junior and pizza 100"
    )
  ), intArrayOf(3))
}

//println("" +
//"t1 = ${t1.toString(2)}, " +
//"t2 = ${t2.toString(2)}, " +
//"t3 = ${t3.toString(2)}, " +
//"t4 = ${t4.toString(2)}")

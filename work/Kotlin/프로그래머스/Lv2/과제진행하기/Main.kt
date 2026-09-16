package 프로그래머스.Lv2.과제진행하기

import util.validate

class Solution {
  companion object {

    const val MAX_T = 24 * 60
    const val SEP = 1_000
    const val ZERO = 48
    const val EMPTY = -1
  }

  fun solution(plans: Array<Array<String>>): Array<String> {
    val N = plans.size
    var ans = Array(N) { "" }
    var ai = 0

    val nameMapper = Array(N) { "" }
    val stack = IntArray(N)
    var stacked = 0
    val schdl = IntArray(MAX_T)

    // PARSE
    var minT = MAX_T
    var maxT = 0
    for (i in 0 until N) {
      val p = plans[i]
      nameMapper[i] = p[0]
      val t = parseT(p[1])
      val pt = p[2].toInt()
      schdl[t] = i * SEP + pt

      if (t < minT) minT = t
      if (t > maxT) maxT = t
    }

    // START
    var cur = EMPTY
    var rmnPt = 0

    fun done(idx: Int) {
      ans[ai++] = nameMapper[idx]
      cur = EMPTY
    }

    fun switch(info: Int) {
      val pt = info % SEP - 1
      val nxt = info / SEP
      if (pt == 0) done(nxt)
      else {
        cur = nxt
        rmnPt = pt
      }
    }

    for (t in minT..maxT) {
      val x1 = schdl[t]
      when {
        x1 > 0 -> {
          if (cur != EMPTY) stack[stacked++] = cur * SEP + rmnPt
          switch(x1)
        }
        cur == EMPTY -> if (stacked > 0) switch(stack[--stacked])
        else -> if (--rmnPt <= 0) done(cur)
      }
    }

    if (cur != EMPTY) done(cur)

    repeat(stacked) {
      ans[ai++] = nameMapper[stack[--stacked] / SEP]
    }

    return ans
  }

  fun parseT(hhMM: String): Int =
    (hhMM[0].code - ZERO) * 600 + (hhMM[1].code - ZERO) * 60 + (hhMM[3].code - ZERO) * 10 + (hhMM[4].code - ZERO)
}

/**
 * ```
 * [ME]
 * 테스트 1 〉	통과 (0.25ms, 57.8MB)
 * 테스트 2 〉	통과 (0.22ms, 59.1MB)
 * 테스트 3 〉	통과 (0.34ms, 59.2MB)
 * 테스트 4 〉	통과 (0.98ms, 58.4MB)
 * 테스트 5 〉	통과 (0.28ms, 59.7MB)
 * 테스트 6 〉	통과 (0.32ms, 60.3MB)
 * 테스트 7 〉	통과 (0.22ms, 59.9MB)
 * 테스트 8 〉	통과 (0.36ms, 58.3MB)
 * 테스트 9 〉	통과 (0.36ms, 61.1MB)
 * 테스트 10 〉	통과 (0.25ms, 59.6MB)
 * 테스트 11 〉	통과 (0.33ms, 60.8MB)
 * 테스트 12 〉	통과 (0.57ms, 62MB)
 * 테스트 13 〉	통과 (0.53ms, 61.9MB)
 * 테스트 14 〉	통과 (0.76ms, 61.7MB)
 * 테스트 15 〉	통과 (0.75ms, 62.6MB)
 *
 *
 * [RIVAL 1]
 * class Solution {
 *     fun solution(plans: Array<Array<String>>): Array<String> {
 *         var answer = mutableListOf<String>()
 *         var modiPlans = plans.map{
 *             val (startHour, startMinute) = it[1].split(":")
 *             val startIntTime = startHour.toInt()*60+startMinute.toInt()
 *             Plan(it[0],startIntTime,it[2].toInt())
 *         }.sortedBy{it.startTime}
 *
 *         for(i in modiPlans.indices){
 *             for(j in i+1 until modiPlans.size){
 *                 if(modiPlans[i].getEndTime() > modiPlans[j].startTime){
 *                     modiPlans[i].duration += modiPlans[j].duration
 *                 }else{
 *                     break
 *                 }
 *             }
 *         }
 *
 *         return modiPlans.sortedBy{ it.getEndTime() }.map{it.name}.toTypedArray()
 *     }
 *     class Plan(
 *         val name:String,
 *         var startTime:Int,
 *         var duration:Int
 *     ){
 *         fun getEndTime():Int{
 *             return startTime+duration
 *         }
 *     }
 * }
 * 테스트 1 〉	통과 (15.72ms, 63.7MB)
 * 테스트 2 〉	통과 (17.37ms, 64.4MB)
 * 테스트 3 〉	통과 (15.23ms, 65MB)
 * 테스트 4 〉	통과 (16.36ms, 65.3MB)
 * 테스트 5 〉	통과 (16.39ms, 65.1MB)
 * 테스트 6 〉	통과 (15.30ms, 65MB)
 * 테스트 7 〉	통과 (16.15ms, 66MB)
 * 테스트 8 〉	통과 (15.88ms, 65.8MB)
 * 테스트 9 〉	통과 (21.04ms, 65.8MB)
 * 테스트 10 〉	통과 (17.67ms, 65.1MB)
 * 테스트 11 〉	통과 (18.53ms, 66.5MB)
 * 테스트 12 〉	통과 (26.98ms, 67.4MB)
 * 테스트 13 〉	통과 (30.24ms, 67MB)
 * 테스트 14 〉	통과 (30.94ms, 68.2MB)
 * 테스트 15 〉	통과 (33.20ms, 68MB)
 *
 * [RIVAL 2]
 * import java.util.*
 * class Solution {
 *     fun solution(plans: Array<Array<String>>): Array<String> {
 *         var answer: Array<String> = arrayOf<String>()
 *
 *         var planQ = PriorityQueue<Triple<String, Int, Int>>{a, b ->
 *             a.second - b.second
 *         }
 *         var stoppedQ = LinkedList<Pair<String, Int>>()
 *
 *         plans.forEach{
 *             val start = it[1].split(":")[0].toInt()*60 + it[1].split(":")[1].toInt()
 *             planQ.add(Triple(it[0], start, it[2].toInt()))
 *         }
 *
 *         var subject = planQ.peek().first
 *         var now = planQ.peek().second
 *         var remain = planQ.peek().third
 *         planQ.poll()
 *         while(true){
 *
 *             while(planQ.isNotEmpty()){
 *                 now++
 *                 remain--
 *                 if(remain == 0){
 *                     answer += subject
 *                     if(stoppedQ.isEmpty()) break
 *                     subject = stoppedQ.peek().first
 *                     remain = stoppedQ.peek().second
 *                     stoppedQ.poll()
 *                 }
 *                 if(now == planQ.peek().second){
 *                     stoppedQ.addFirst(subject to remain)
 *                     break
 *                 }
 *             }
 *             if(planQ.isEmpty()) break
 *             now = planQ.peek().second
 *             subject = planQ.peek().first
 *             remain = planQ.peek().third
 *             planQ.poll()
 *         }
 *
 *         answer += subject
 *         while(stoppedQ.isNotEmpty()){
 *             answer += stoppedQ.poll().first
 *         }
 *
 *         return answer
 *     }
 * }
 * 테스트 1 〉	통과 (13.84ms, 64.1MB)
 * 테스트 2 〉	통과 (14.71ms, 64.1MB)
 * 테스트 3 〉	통과 (13.60ms, 65MB)
 * 테스트 4 〉	통과 (15.94ms, 64.3MB)
 * 테스트 5 〉	통과 (13.84ms, 63.6MB)
 * 테스트 6 〉	통과 (14.07ms, 64.2MB)
 * 테스트 7 〉	통과 (15.13ms, 64.2MB)
 * 테스트 8 〉	통과 (14.08ms, 65.9MB)
 * 테스트 9 〉	통과 (17.64ms, 65.6MB)
 * 테스트 10 〉	통과 (14.19ms, 65.3MB)
 * 테스트 11 〉	통과 (16.42ms, 66MB)
 * 테스트 12 〉	통과 (16.72ms, 67.3MB)
 * 테스트 13 〉	통과 (17.96ms, 67.3MB)
 * 테스트 14 〉	통과 (19.48ms, 70.2MB)
 * 테스트 15 〉	통과 (21.98ms, 68.9MB)
 * 테스트 16 〉	통과 (16.30ms, 62.7MB)
 *
 * [RIVAL 3]
 * import java.util.*
 * class Solution {
 *     val EMPTY_PLAN = Plan("", 0, 0)
 *     fun solution(plans: Array<Array<String>>): Array<String> {
 *         val newPlans = plans.sortedWith { p1, p2 ->
 *             toInt(p1[1]) - toInt(p2[1])
 *         }.map {
 *             Plan(it[0], toInt(it[1]), it[2].toInt())
 *         }
 *
 *         var now = 0
 *         val st = Stack<Plan>()
 *         var idx = 0
 *         var curPlan = EMPTY_PLAN
 *         var answer = mutableListOf<String>()
 *         while (now++ <= 60 * 24 + 100 || !(st.isEmpty() && curPlan == EMPTY_PLAN)) {
 *             if (curPlan != EMPTY_PLAN) {
 *                 curPlan.playtime -= 1
 *                 if (curPlan.playtime == 0) {
 *                     answer.add(curPlan.name)
 *                     curPlan = if (st.isEmpty()) EMPTY_PLAN else st.pop()
 *                 }
 *             }
 *             if (idx in newPlans.indices && now >= newPlans[idx].start) {
 *                 if (curPlan != EMPTY_PLAN) st.push(curPlan)
 *                 curPlan = newPlans[idx++]
 *             }
 *         }
 *         return answer.toTypedArray()
 *     }
 *
 *     fun toInt(time: String): Int = time.split(":").let { it[0].toInt() * 60 + it[1].toInt() }
 * }
 *
 * class Plan(var name: String, var start: Int, var playtime: Int)
 * 테스트 1 〉	통과 (18.59ms, 65.3MB)
 * 테스트 2 〉	통과 (18.76ms, 65.5MB)
 * 테스트 3 〉	통과 (18.62ms, 65MB)
 * 테스트 4 〉	통과 (19.33ms, 64.1MB)
 * 테스트 5 〉	통과 (28.22ms, 65.5MB)
 * 테스트 6 〉	통과 (19.85ms, 63.7MB)
 * 테스트 7 〉	통과 (29.70ms, 66.1MB)
 * 테스트 8 〉	통과 (24.50ms, 63.4MB)
 * 테스트 9 〉	통과 (31.08ms, 63.5MB)
 * 테스트 10 〉	통과 (20.98ms, 65.6MB)
 * 테스트 11 〉	통과 (24.00ms, 65.5MB)
 * 테스트 12 〉	통과 (26.85ms, 73.6MB)
 * 테스트 13 〉	통과 (27.34ms, 73.6MB)
 * 테스트 14 〉	통과 (39.36ms, 81.4MB)
 * 테스트 15 〉	통과 (31.31ms, 82.5MB)
 *
 *
 *
 * ```
 */
fun main() {
  val s = Solution()
  validate(s.solution(
    arrayOf(
      arrayOf("korean", "11:40", "30"),
      arrayOf("english", "12:10", "20"),
      arrayOf("math", "12:30", "40"),
    )
  ), arrayOf("korean", "english", "math"))

  validate(s.solution(
    arrayOf(
      arrayOf("science", "12:40", "50"),
      arrayOf("music", "12:20", "40"),
      arrayOf("history", "14:00", "30"),
      arrayOf("computer", "12:30", "100"),
    )
  ), arrayOf("science", "history", "computer", "music"))

  validate(s.solution(
    arrayOf(
      arrayOf("aaa", "12:00", "20"),
      arrayOf("bbb", "12:10", "30"),
      arrayOf("ccc", "12:40", "10"),
    )
  ), arrayOf("bbb", "ccc", "aaa"))

  validate(s.solution(
    arrayOf(
      arrayOf("a1", "23:50", "20"),
      arrayOf("z", "23:52", "1"),
      arrayOf("a2", "23:53", "20"),
      arrayOf("a3", "23:55", "20"),
      arrayOf("bbb", "23:59", "30"),
    )
  ), arrayOf("z", "bbb", "a3", "a2", "a1"))

  validate(s.solution(
    arrayOf(
      arrayOf("a1", "23:50", "1"),
      arrayOf("a2", "23:51", "1"),
      arrayOf("a3", "23:52", "1"),
      arrayOf("bbb", "23:59", "30"),
    )
  ), arrayOf("a1", "a2", "a3", "bbb"))

  validate(s.solution(
    arrayOf(
      arrayOf("a1", "23:50", "5"),
      arrayOf("z", "23:51", "1"),
      arrayOf("a2", "23:52", "2"),
      arrayOf("a3", "23:53", "2"),
      arrayOf("bbb", "23:59", "30"),
    )
  ), arrayOf("z", "a3", "a2", "bbb", "a1"))

  validate(s.solution(
    arrayOf(
      arrayOf("a1", "23:50", "3"),
      arrayOf("z", "23:51", "1"),
      arrayOf("a2", "23:52", "2"),
      arrayOf("a3", "23:53", "2"),
      arrayOf("bbb", "23:59", "30"),
    )
  ), arrayOf("z", "a3", "a2", "a1", "bbb"))

  // 마지막 과제가 pt = 1 -> t loop 이후 done() 에서 오류발생여부
  validate(s.solution(
    arrayOf(
      arrayOf("a1", "23:50", "10"),
      arrayOf("z", "23:51", "1"),
      arrayOf("a2", "23:52", "2"),
      arrayOf("a3", "23:53", "2"),
      arrayOf("b", "23:59", "1"),
    )
  ), arrayOf("z", "a3", "a2", "b", "a1"))

}

package u05lab.ex3

import java.util.concurrent.TimeUnit
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.concurrent.duration.FiniteDuration

object PerformanceUtils:
  case class MeasurementResults[T](result: T, duration: FiniteDuration) extends Ordered[MeasurementResults[_]]:
    override def compare(that: MeasurementResults[_]): Int = duration.toNanos.compareTo(that.duration.toNanos)

  def measure[T](msg: String)(expr: => T): MeasurementResults[T] =
    val startTime = System.nanoTime()
    val res = expr
    val duration = FiniteDuration(System.nanoTime() - startTime, TimeUnit.NANOSECONDS)
    if (msg.nonEmpty) println(msg + " -- " + duration.toNanos + " nanos; " + duration.toMillis + "ms")
    MeasurementResults(res, duration)

  def measure[T](expr: => T): MeasurementResults[T] = measure("")(expr)

@main def checkPerformance: Unit =

  /* immutable Linear sequences: List, mutable ListBuffer */
  val lb: ListBuffer[Int] = ListBuffer[Int]()
  val ls: List[Int] = List[Int]()
  lb.addAll(1 to 10000000)


  /* Indexed sequences: immutable Vector, Array, mutable ArrayBuffer */
  val v: Vector[Int] = Vector[Int]()
  val a: Array[Int] = Array[Int]()
  val ab: ArrayBuffer[Int] = ArrayBuffer[Int]()
  ab.addAll(1 to 10000000)
  /* Sets */
  val s: Set[Int] = Set()

  /* Maps */
  val m: Map[Int, Iterable[Int]] = Map[Int, Iterable[Int]](0 -> (1 to 10000000))

  /* Comparison */
  import PerformanceUtils.*
  val lst = (1 to 10000000).toList
  val vec = (1 to 10000000).toVector
  assert(measure("lst last")(lst.last) > measure("vec last")(vec.last))
  assert(measure("lb last")(lb.last) > measure("vec last")(vec.last))
  assert(measure("vec last")(vec.last) < measure("ls last")(ls.appendedAll(1 to 10000000).last))
  assert(measure("v last")(v.appendedAll(1 to 10000000).last) > measure("vec last")(vec.last))
  assert(measure("a last")(a.appendedAll(1 to 10000000).last) > measure("ab last")(ab.last))
  assert(measure("s last")(s.++(1 to 10000000).last) > measure("lst last")(lst.last))
  assert(measure("s last")(s.++(1 to 10000000).last) > measure("m last")(m.last))
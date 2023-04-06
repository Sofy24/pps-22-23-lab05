package u05lab.ex3

object Collection extends App:
  println("Immutable map")
  val m: Map[String, Int] = Map("a" -> 1, "aa" -> 2, "aaa" -> 3, "aaaa" -> 4)
  println("Third value = " + m("aaa"))
  println("This is the size of the map = " + m.size)
  println("This is an iterable with the values of the map = " + m.values)
  println("This is a set with the keys of the map = " + m.keySet)
  println(m.map{ case (key, value) => (value, key)})
  println(m.map{ case (key, value) => (key, value + key)})
  val newMap = m - "a"
  println("This is a new map without the first element = " + newMap)

  println("\nImmutable vector")
  val vector: Vector[String] = Vector("a", "b", "c", "d")
  println("Fourth value = " + vector(3))
  println("This is the size of the vector = " + vector.size)
  println("This is the reversed vector = " + vector.reverse)
  println("This is a new vector with a different element in the second position = " + vector.updated(2, "y"))
  println("This is a vector with the elements from 0 to 3 = " + vector.slice(0, 3))
  println("In this vector there is no letter c = " + vector.patch(2, Nil, 1))

  println("\nImmutable set")
  val s: Set[Int] = Set(1, 2, 3, 4, 5, 6)
  println("The set contais 6 => " + s(6))
  println("The set contais 5 => " + s.contains(5))
  println("A new set with new elements = " + (s + 0 + 7))
  println("This is the intersection between 2 sets = " + (s & Set(1, 8, 9, 0, 3)))
  println("In this set there is no 2" + (s - 2))
  println("The size of the set is = " + s.size)

  println("\nMutable map")
  import collection._
  val mm: mutable.Map[String, Int] = mutable.Map[String, Int]("a" -> 1)
  mm += ("aa" -> 2)
  mm += ("aaa" -> 3)
  println("This is the map now = " + mm)
  println("This is the size of the map = " + mm.size)
  println(mm.map{ case (key, value) => (value, key)})
  println(mm.map{ case (key, value) => (key, value + key)})
  println("This is an iterable with the values of the map = " + mm.values)
  println("This is a set with the keys of the map = " + mm.keySet)
  mm.remove("a")
  println("This is the map now = " + mm)

  println("\nMutable ArrayBuffer")
  val b: mutable.ArrayBuffer[Int] = mutable.ArrayBuffer[Int](1, 2)
  println("This is the element in the first position = " + b(0))
  b += 3
  b += 6
  println("This is the size of the ArrayBuffer = " + b.size)
  b -= 2
  b.remove(0)
  println("This is the ArrayBuffer now = " + b)
  println("This is a copy of the ArrayBuffer in which the 3 is replaced with 4 = " + b.updated(0, 4))

  println("\nMutable set")
  val ms: mutable.Set[Int] = mutable.Set(1, 7, 8)
  ms += 2
  ms += 3
  println("The size of the set is = " + ms.size)
  println("The set contais 7 => " + ms(7))
  println("The set contais 1 => " + ms.contains(1))
  ms -= 8
  println("This is the set now = " + ms)
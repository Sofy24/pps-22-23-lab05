package u05lab.ex2

object Pair:
  case class Pair[X, Y](val x: X, val y: Y):
      def getX: X = x
  
      def getY: Y = y

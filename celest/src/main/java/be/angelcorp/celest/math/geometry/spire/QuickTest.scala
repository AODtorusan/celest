package be.angelcorp.celest.math.geometry.spire

import spire.implicits._
import _Vec3Spire._
import _Vec3._
import spire.math.Rational

object QuickTest extends App {

  val v1 = _Vec3Spire(0d, 1d, 0d)
  println("v1 = " + v1)
  println("||v1|| = " + v1.norm)

  val v2 = _Vec3Spire(1d, 0d, 1d)
  println("v2 = " + v2)
  println("v1 + v2 = " + (v1 + v2))
  println("v1 dot v2 = " + (v1 dot v2))
  println("v1 cross v2 = " + (v1 cross v2))
  println("v1 angle v2 = " + (v1 angle v2))

  val v3 = _Vec3Spire(1f, 1f, 1f)
  println("v3 = " + v3)
  println("v3 (widend) + v1 = " +  ((v3: _Vec3[Double]) + v1))

  val r1 = _Vec3Spire( Rational(1), Rational(2), Rational(3) )
  val r2: _Vec3[Rational] = v1
  println("r1 + r2 = " + (r1 + r2))
  println("r1 dot r2 = " + (r1 dot r2))
  println("r1 cross r2 = " + (r1 cross r2))

}

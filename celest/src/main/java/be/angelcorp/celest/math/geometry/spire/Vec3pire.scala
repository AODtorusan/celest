package be.angelcorp.celest.math.geometry.spire

import spire.algebra._
import spire.implicits._
import spire.math._

abstract class _Vec3Spire[@specialized(Int, Float, Double) N: Field] extends _Vec3[N] {

  type Vec = _Vec3Spire[N]

  implicit def trig:  Trig[N]
  implicit def nroot: NRoot[N]

  lazy val normSquared: N =
    x * x + y * y + z * z

  lazy val norm: N =
    sqrt( normSquared )

  def distance( v: V ): N =
    space.minus(this, v).norm

  def angle(v2: V): N = {
    val dotproduct = space.dot( this, v2 )
    val normproduct = norm * v2.norm
    acos( dotproduct / normproduct )
  }

  /** Vector-vector cross product, stored in a new vector. */
  def cross(v: V): V = space.buildVector(
    y * v.z - v.y * z,
    z * v.x - v.z * x,
    x * v.y - v.x * y
  )


}

object _Vec3Spire {

  // Space for math between Vec3SDouble instances
  implicit object Vec3SDoubleSpace extends _Vec3SpaceImpl[Vec3SDouble, Double] {
    override implicit def scalar: Field[Double] = DoubleAlgebra
    override def buildVector(x: Double, y: Double, z: Double): Vec3SDouble = new Vec3SDouble(x, y, z)
    override def convert(v: _Vec3[Double]): Vec3SDouble = v match {
      case vec: Vec3SDouble => vec
      case _ => buildVector( v.x, v.y, v.z )
    }
  }
  // Space for math between _Vec3[Double] instances
  implicit object Vec3DoubleSpace extends _Vec3SpaceImpl[_Vec3[Double], Double] {
    override implicit def scalar: Field[Double] = DoubleAlgebra
    override def buildVector(x: Double, y: Double, z: Double): Vec3SDouble = new Vec3SDouble(x, y, z)
    override def convert(v: _Vec3[Double]): _Vec3[Double] = v
  }
  // Space for math between Vec3SFloat instances
  implicit object Vec3SFloatSpace extends _Vec3SpaceImpl[Vec3SFloat, Float] {
    override def buildVector(x: Float, y: Float, z: Float): Vec3SFloat = new Vec3SFloat(x, y, z)
    override implicit def scalar: Field[Float] = FloatAlgebra
    override def convert(v: _Vec3[Float]): Vec3SFloat = v match {
      case vec: Vec3SFloat => vec
      case _ => buildVector( v.x, v.y, v.z )
    }
  }
  // Space for math between _Vec3[Float] instances
  implicit object Vec3FloatSpace extends _Vec3SpaceImpl[_Vec3[Float], Float] {
    override implicit def scalar: Field[Float] = FloatAlgebra
    override def buildVector(x: Float, y: Float, z: Float): Vec3SFloat = new Vec3SFloat(x, y, z)
    override def convert(v: _Vec3[Float]): _Vec3[Float] = v
  }
  // Space for math between Vec3SRational instances
  implicit object Vec3SRationalSpace extends _Vec3SpaceImpl[Vec3SRational, Rational] {
    override def buildVector(x: Rational, y: Rational, z: Rational): Vec3SRational = new Vec3SRational(x, y, z)
    override implicit val scalar: Field[Rational] = new RationalAlgebra
    override def convert(v: _Vec3[Rational]): Vec3SRational = v match {
      case vec: Vec3SRational => vec
      case _ => buildVector( v.x, v.y, v.z )
    }
  }
  // Space for math between _Vec3[Rational] instances
  implicit object Vec3RationalSpace extends _Vec3SpaceImpl[_Vec3[Rational], Rational] {
    override implicit val scalar: Field[Rational] = new RationalAlgebra
    override def buildVector(x: Rational, y: Rational, z: Rational): Vec3SRational = new Vec3SRational(x, y, z)
    override def convert(v: _Vec3[Rational]): _Vec3[Rational] = v
  }

  class Vec3SDouble(val x: Double, val y: Double, val z: Double) extends _Vec3Spire[Double] {
    override implicit def space: Vec3DoubleSpace.type = Vec3DoubleSpace
    override implicit def nroot: NRoot[Double] = DoubleAlgebra
    override implicit def trig:  Trig[Double] = DoubleAlgebra
  }
  class Vec3SFloat(val x: Float, val y: Float, val z: Float) extends _Vec3Spire[Float] {
    override implicit def space: Vec3FloatSpace.type = Vec3FloatSpace
    override implicit def nroot: NRoot[Float] = FloatAlgebra
    override implicit def trig: Trig[Float] = FloatAlgebra
  }
  class Vec3SRational(val x: Rational, val y: Rational, val z: Rational) extends _Vec3Spire[Rational] {
    override implicit def space: Vec3RationalSpace.type = Vec3RationalSpace
    override implicit def nroot = ??? // TODO find correct implementation
    override implicit def trig = ??? // TODO find correct implementation
  }

  def apply( x: Double,   y: Double  , z: Double   ): _Vec3[Double]   = new Vec3SDouble(x, y, z)
  def apply( x: Float,    y: Float   , z: Float    ): _Vec3[Float]    = new Vec3SFloat(x, y, z)
  def apply( x: Rational, y: Rational, z: Rational ): _Vec3[Rational] = new Vec3SRational(x, y, z)

}



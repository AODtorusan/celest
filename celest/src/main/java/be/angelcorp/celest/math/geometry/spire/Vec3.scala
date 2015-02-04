package be.angelcorp.celest.math.geometry.spire

import be.angelcorp.celest.math.geometry.spire._Vec3Spire.{Vec3SDouble, Vec3SRational}
import spire.math.Rational

trait _Vec3[@specialized(Int, Float, Double) N] {

  type V = _Vec3[N]

  implicit def space: _Vec3Space[V, N]

  def x: N
  def y: N
  def z: N

  def size = 3

  def apply( index: Int ): N = index match {
    case 0 => x
    case 1 => y
    case 2 => z
    case _ => throw new IndexOutOfBoundsException
  }

  def normSquared: N
  def norm: N

  def distance( v: V ): N

  def angle(v2: V): N

  /** Vector-vector cross product, stored in a new vector. */
  def cross(v: V): V
  def ×( v: V ): V = cross(v)

  override def toString: String = s"Vec3($x, $y, $z)"

}

object _Vec3 {

  class Vec3Widening[@specialized(Int, Float, Double) N, @specialized(Int, Float, Double) N0 <% N]( vec: _Vec3[N0] ) extends _Vec3[N] {
    val wider: _Vec3[N] = _Vec3[N](vec.x, vec.y, vec.z)
    override implicit def space: _Vec3Space[V, N] = wider.space
    override def cross(v: V): V = wider.cross(v)
    override def x: N = wider.x
    override def y: N = wider.y
    override def z: N = wider.z
    override def distance(v: V): N = wider.distance(v)
    override def normSquared: N = wider.normSquared
    override def norm: N = wider.norm
    override def angle(v2: V): N = wider.angle(v2)
  }

  // After implementing 'def apply[@specialized(Int, Float, Double) N](s: N): _Vec3[N] = ???' you can replace these with:
  //implicit def widen[@specialized(Int, Float, Double) N, @specialized(Int, Float, Double) N0 <% N](v: _Vec3[N0]): _Vec3[N] = new Vec3Widening(v)
  implicit def widen[@specialized(Int, Float, Double) N0 <% Double](v: _Vec3[N0]): _Vec3[Double] = new Vec3SDouble(v.x, v.y, v.z)
  implicit def widen2[@specialized(Int, Float, Double) N0 <% Rational](v: _Vec3[N0]): _Vec3[Rational] = new Vec3SRational(v.x, v.y, v.z)

  def x[@specialized(Int, Float, Double) N]: _Vec3[N] = ???
  def y[@specialized(Int, Float, Double) N]: _Vec3[N] = ???
  def z[@specialized(Int, Float, Double) N]: _Vec3[N] = ???

  def zero[@specialized(Int, Float, Double) N]: _Vec3[N] = ???

  def apply[@specialized(Int, Float, Double) N](s: N): _Vec3[N] = ???
  def apply[@specialized(Int, Float, Double) N](x: N, y: N, z: N): _Vec3[N] = ???

}

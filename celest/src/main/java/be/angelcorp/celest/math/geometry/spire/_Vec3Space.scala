package be.angelcorp.celest.math.geometry.spire

import spire.algebra.{Field, CoordinateSpace}
import spire.implicits._


trait _Vec3Space[V <: _Vec3[N], @specialized(Int, Float, Double) N] extends CoordinateSpace[V, N] with Field[V] {

  def buildVector( x: N, y: N, z: N ): V
  def convert( v: _Vec3[N] ): V

}

trait _Vec3SpaceImpl[V <: _Vec3[N], @specialized(Int, Float, Double) N] extends _Vec3Space[V, N] {
  override def dimensions: Int = 2

  override def _x(v: V): N = v.x
  override def _y(v: V): N = v.y
  override def _z(v: V): N = v.z

  override def axis(i: Int): V = i match {
    case 0 => buildVector( scalar.one, scalar.zero, scalar.zero )
    case 1 => buildVector( scalar.zero, scalar.one, scalar.zero )
    case 2 => buildVector( scalar.zero, scalar.zero, scalar.one )
    case _ => throw new IndexOutOfBoundsException
  }

  override def coord(v: V, i: Int): N = i match {
    case 0 => v.x
    case 1 => v.y
    case _ => throw new IndexOutOfBoundsException
  }

  override def timesl(r: N, v: V): V =
    buildVector( v.x * r, v.y * r, v.z * r )

  override def divr(v: V, f: N): V =
    buildVector( v.x / f, v.y /  f, v.z / f )

  override def negate(v: V): V =
    buildVector( -v.x, -v.y, -v.z )

  override def zero: V =
    buildVector( scalar.zero, scalar.zero, scalar.zero )

  override def plus(v1: V, v2: V): V =
    buildVector( v1.x + v2.x, v1.y + v2.y, v1.z + v2.z )

  override def dot(v: V, w: V): N =
    v.x * w.x + v.y * w.y + v.z * w.z

  override def minus(v1: V, v2: V): V =
    buildVector( v1.x - v2.x, v1.y - v2.y, v1.z - v2.z )

  override def div(v1: V, v2: V): V =
    buildVector( v1.x / v2.x, v1.y / v2.y, v1.z / v2.z ) // TODO: Difference with quot

  override def quot(a: V, b: V): V =
    buildVector( a.x / b.x, a.y / b.y, a.z / b.z ) // TODO: Difference with div

  override def gcd(a: V, b: V): V =
    buildVector( a.x gcd b.x, a.y gcd b.y, a.z gcd b.z )

  override def mod(a: V, b: V): V =
    buildVector( a.x % b.x, a.y % b.y, a.z % b.z )

  override def one: V =
    buildVector( scalar.one, scalar.one, scalar.one )

  override def times(a: V, b: V): V =
    buildVector( a.x * b.x, a.y * b.y, a.z * b.z )

}

/**
 * Copyright (C) 2009-2012 simon <simon@angelcorp.be>
 *
 * Licensed under the Non-Profit Open Software License version 3.0
 * (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *        http://www.opensource.org/licenses/NOSL3.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.angelcorp.libs.celest.state

import be.angelcorp.libs.math.linear.Vector3D
import be.angelcorp.libs.celest.frames

/**
 * [[be.angelcorp.libs.celest.state.Orbit]] in the form of the Cartesian position (x,y,z) and velocity (&#7819;,
 * &#7823;, &#380;).
 *
 * <pre>
 * Elements: { x,   y,   z,    &#7819;,     &#7823;,     &#380;  }
 * Units:    {[m], [m], [m], [m/s], [m/s], [m/s]}
 * </pre>
 *
 * @param position Instantaneous position [m]
 * @param velocity Instantaneous velocity [m/s]
 * @param frame    Reference frame in which the position and velocity are defined
 *
 * @author Simon Billemont
 */
class PosVel( val position: Vector3D, val velocity: Vector3D, val frame: Option[frames.BodyCentered] = None) extends Orbit {

  /**
   * {@inheritDoc}
   */
	def toPosVel = this

}

object PosVel {

  /**
   * Create a pair of position/velocity, both equal to zero:
   * <pre>
   * R = [0, 0, 0]
   * V = [0, 0, 0]
   * </pre>
   */
  def apply(): PosVel = new PosVel( Vector3D.ZERO, Vector3D.ZERO )

  /**
   * Create a pair of position/velocity, both equal to zero:
   * <pre>
   * R = [ x,  y,  y]
   * V = [vx, vy, vz]
   * </pre>
   * @param x Instantaneous x coordinate [m].
   * @param y Instantaneous y coordinate [m].
   * @param z Instantaneous z coordinate [m].
   * @param vx Instantaneous x velocity [m/s].
   * @param vy Instantaneous y velocity [m/s].
   * @param vz Instantaneous z velocity [m/s].
   */
  def apply(x: Double, y: Double, z: Double, vx: Double, vy: Double, vz : Double, frame: Option[frames.BodyCentered] = None): PosVel =
    new PosVel( Vector3D(x,y,z), Vector3D(vx,vy,vz), frame )

  /**
   * Create from a 6 dimensional array.
   * <pre>
   * d = [Rx, Ry, Rz, Vx, Vy, Vz]
   * </pre>
   * <p>
   * Note: The array must have a length of 6
   * </p>
   *
   * @param array Array to extract the position and velocity from
   */
  def apply(array: Array[Double], frame: Option[frames.BodyCentered]): PosVel = {
    require(array.size == 6)
    PosVel( array(0), array(1), array(2), array(3), array(4), array(5), frame)
  }


}

/**
 * Copyright (C) 2013 Simon Billemont <simon@angelcorp.be>
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

package be.angelcorp.libs.celest.frames.implementations.transforms

import math._
import be.angelcorp.libs.celest.frames.{IReferenceFrame, ConstantRotationTransformFactory}
import be.angelcorp.libs.celest.time.IJulianDate
import be.angelcorp.libs.util.physics.Angle._
import be.angelcorp.libs.math.rotation.RotationMatrix._
import be.angelcorp.libs.math.linear.Matrix3D

/**
 * The reference frame bias from the J2000.0 (EME2000) dynamical equinox to the GCRF reference frame.
 * =References=
 * 1) D. Vallado et al. ,<b>"Implementation Issues Surrounding the New IAU Reference Systems for Astrodynamics"</b>, 16th AAS/AIAA Space Flight Mechanics Conference, Florida, January 2006<br/>
 * 2) G. Petit, B. Luzum (eds.).,<b>"IERS Conventions (2010)"</b>, IERS Technical Note 36, Frankfurt am Main: Verlag des Bundesamts für Kartographie und Geodäsie, 2010. 179 pp., ISBN 3-89888-989-6<br/>
 * 3) G. H. Kaplan, <b>"The IAU Resolutions on Astronomical Reference Systems, Time Scales, and Earth Rotation Models"</b>, 2005, U.S. Naval Observatory Circular No. 179, [online] http://arxiv.org/abs/astro-ph/0602086
 */
class J2000FrameBias( val preciseForm: Boolean = true ) extends ConstantRotationTransformFactory[IReferenceFrame, IReferenceFrame] {

  /**
   * The offset in the ICRS right ascension origin with respect to the dynamical equinox of J2000.0,
   * as measured in an inertial (non-rotating) system.
   *
   * See reference [3], section 3.5.
   */
  val δα0 = ArcSecond convert -0.0146
  /**
   * Part of the ICRS pole offsets (ξ0 and η0).
   *
   * See reference [3], section 3.5.
   */
  val ξ0  = ArcSecond convert -0.0166170
  /**
   * Part of the ICRS pole offsets (ξ0 and η0).
   *
   * See reference [3], section 3.5.
   */
  val η0  = ArcSecond convert -0.0068192

  /**
   * The frame bias matrix (constant as a function of time).
   *
   * See reference [3], section 3.5.
   */
  val B =
    if (preciseForm) {
      val δα0Sq = pow(δα0, 2); val ξ0Sq = pow(ξ0, 2); val η0Sq = pow(η0, 2)
      Matrix3D( // Reference [3] equation 3.4
        1.0 - 0.5 * (δα0Sq + ξ0Sq),   δα0,                          -ξ0,
        -δα0 - η0 * ξ0,               1.0 - 0.5 * (δα0Sq + η0Sq),   -η0,
         ξ0  - η0 * δα0,              η0 + ξ0 * δα0,                 1.0 - 0.5 * (η0Sq + ξ0Sq)
      )
    } else
      Matrix3D( // Reference [3] equation 3.3
         1,    δα0,  -ξ0,
        -δα0,   1,   -η0,
         ξ0,   η0,    1
      )

  def rotationMatrix(epoch: IJulianDate) = B

  def getCost(epoch: IJulianDate) = 100.0

}

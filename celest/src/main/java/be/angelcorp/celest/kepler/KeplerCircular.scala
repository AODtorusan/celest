/**
 * Copyright (C) 2009-2012 simon <simon@angelcorp.be>
 *
 * Licensed under the Non-Profit Open Software License version 3.0
 * (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/NOSL3.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.angelcorp.celest.kepler

import math._
import be.angelcorp.celest.state.Keplerian

class KeplerCircular(k: Keplerian) extends KeplerEllipse(k) {

  override def arealVel = sqrt(μ * k.semiMajorAxis) / 2

  override def semiLatusRectum = k.semiMajorAxis

  override def apocenter = k.semiMajorAxis

  override def pericenter = k.semiMajorAxis

  override def totEnergyPerMass = -μ / (2 * k.semiMajorAxis)

  override def visViva(r: Double) = μ / r

  lazy val circularVelocity = sqrt(μ / k.semiMajorAxis)

}

object KeplerCircular {

  def circularVelocity(r: Double, µ: Double) = sqrt(µ / r)

}
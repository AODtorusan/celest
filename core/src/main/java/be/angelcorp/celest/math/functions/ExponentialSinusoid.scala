/**
 * Copyright (C) 2009-2015 simon <simon@angelcorp.be>
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
package be.angelcorp.celest.math.functions

import org.apache.commons.math3.analysis.differentiation.{DerivativeStructure, UnivariateDifferentiableFunction}
import org.apache.commons.math3.util.FastMath._

/**
 * Exponential sinusoid function defined by;
 * 
 * <pre>
 * r(&theta;) = k0 exp( q0 &theta; + k1 sin(k2 &theta; + &phi; ) )
 * </pre>
 *
 * @param k0 Scaling factor constant. Unit: Determines output unit.
 * @param k1 Dynamic range parameter [-]
 * @param k2 Winding parameter [-]
 * @param q0 Constant [-]
 * @param phi Phase angle [rad]
 *
 * @author Simon Billemont
 */
class ExponentialSinusoid(val k0: Double, val k1: Double, val k2: Double, val q0: Double, val phi: Double)
	extends UnivariateDifferentiableFunction {

	override def value(theta: Double) =
		k0 * exp(q0 * theta + k1 * sin(k2 * theta + phi))

	override def value(theta: DerivativeStructure) {
		//  f(x) = k0 * exp(q0 * theta + k1 * sin(k2 * theta + phi))
		//       = k0 * exp(q0 * theta +  a )
		//       = k0 * b
		val a = theta.multiply( k2 ).add( phi ).sin().multiply( k1 )
		val b = theta.multiply( q0 ).add( a ).exp()
		b.multiply( k0 )
	}

	override def toString =
		s"ExponentialSinusoid(k0=$k0, k1=$k1, k2=$k2, q0=$q0, phi=$phi)"

}

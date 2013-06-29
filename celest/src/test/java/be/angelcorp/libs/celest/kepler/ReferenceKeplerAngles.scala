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
package be.angelcorp.libs.celest.kepler

import scala.math._
import org.apache.commons.math3.linear.ArrayRealVector
import org.apache.commons.math3.util.Precision
import be.angelcorp.libs.celest.state.positionState.KeplerElements
import be.angelcorp.libs.math.functions.domain.AngularDomain
import be.angelcorp.libs.util.physics.Angle._

object ReferenceKeplerAngles {

	val degree = Degree getScaleFactor

	val circularAngles = List[ArrayRealVector](
		// e, nu, M, E
		// Calculation using Fundamentals of Astrodynamics & applications matlab script: 'newtonnu'
    new ArrayRealVector(Array[Double]( 0.0, -3.1415926536, 3.1415926536, -3.1415926536 )),
    new ArrayRealVector(Array[Double]( 0.0, -2.3561944902, 3.9269908170, -2.3561944902 )),
    new ArrayRealVector(Array[Double]( 0.0, -1.5707963268, 4.7123889804, -1.5707963268 )),
    new ArrayRealVector(Array[Double]( 0.0, -0.7853981634, 5.4977871438, -0.7853981634 )),
    new ArrayRealVector(Array[Double]( 0.0,  0.0000000000, 0.0000000000,  0.0000000000 )),
    new ArrayRealVector(Array[Double]( 0.0,  0.7853981634, 0.7853981634,  0.7853981634 )),
    new ArrayRealVector(Array[Double]( 0.0,  1.5707963268, 1.5707963268,  1.5707963268 )),
    new ArrayRealVector(Array[Double]( 0.0,  2.3561944902, 2.3561944902,  2.3561944902 )),
    new ArrayRealVector(Array[Double]( 0.0,  3.1415926536, 3.1415926536,  3.1415926536 ))
  )

  val ellipticalAngles = List[ArrayRealVector](
		// e, nu, M, E
    // Calculation using Fundamentals of Astrodynamics & applications matlab script: 'newtonnu'
    new ArrayRealVector(Array[Double]( 0.1, -3.1415926536, 3.1415926536, -3.1415926536 )),
    new ArrayRealVector(Array[Double]( 0.1, -2.3561944902, 4.0761607380, -2.2827342914 )),
    new ArrayRealVector(Array[Double]( 0.1, -1.5707963268, 4.9120551453, -1.4706289056 )),
    new ArrayRealVector(Array[Double]( 0.1, -0.7853981634, 5.6319320443, -0.7169631113 )),
    new ArrayRealVector(Array[Double]( 0.1,  0.0000000000, 0.0000000000,  0.0000000000 )),
    new ArrayRealVector(Array[Double]( 0.1,  0.7853981634, 0.6512532629,  0.7169631113 )),
    new ArrayRealVector(Array[Double]( 0.1,  1.5707963268, 1.3711301619,  1.4706289056 )),
    new ArrayRealVector(Array[Double]( 0.1,  2.3561944902, 2.2070245692,  2.2827342914 )),
    new ArrayRealVector(Array[Double]( 0.1,  3.1415926536, 3.1415926536,  3.1415926536 )),
    new ArrayRealVector(Array[Double]( 0.2, -3.1415926536, 3.1415926536, -3.1415926536 )),
    new ArrayRealVector(Array[Double]( 0.2, -2.3561944902, 4.2419309133, -2.2026421312 )),
    new ArrayRealVector(Array[Double]( 0.2, -1.5707963268, 5.1097060806, -1.3694384060 )),
    new ArrayRealVector(Array[Double]( 0.2, -0.7853981634, 5.7523259906, -0.6522553846 )),
    new ArrayRealVector(Array[Double]( 0.2,  0.0000000000, 0.0000000000,  0.0000000000 )),
    new ArrayRealVector(Array[Double]( 0.2,  0.7853981634, 0.5308593166,  0.6522553846 )),
    new ArrayRealVector(Array[Double]( 0.2,  1.5707963268, 1.1734792266,  1.3694384060 )),
    new ArrayRealVector(Array[Double]( 0.2,  2.3561944902, 2.0412543939,  2.2026421312 )),
    new ArrayRealVector(Array[Double]( 0.2,  3.1415926536, 3.1415926536,  3.1415926536 )),
    new ArrayRealVector(Array[Double]( 0.3, -3.1415926536, 3.1415926536, -3.1415926536 )),
    new ArrayRealVector(Array[Double]( 0.3, -2.3561944902, 4.4262205040, -2.1138112194 )),
    new ArrayRealVector(Array[Double]( 0.3, -1.5707963268, 5.3032633948, -1.2661036728 )),
    new ArrayRealVector(Array[Double]( 0.3, -0.7853981634, 5.8599789279, -0.5901527661 )),
    new ArrayRealVector(Array[Double]( 0.3,  0.0000000000, 0.0000000000,  0.0000000000 )),
    new ArrayRealVector(Array[Double]( 0.3,  0.7853981634, 0.4232063793,  0.5901527661 )),
    new ArrayRealVector(Array[Double]( 0.3,  1.5707963268, 0.9799219124,  1.2661036728 )),
    new ArrayRealVector(Array[Double]( 0.3,  2.3561944902, 1.8569648032,  2.1138112194 )),
    new ArrayRealVector(Array[Double]( 0.3,  3.1415926536, 3.1415926536,  3.1415926536 )),
    new ArrayRealVector(Array[Double]( 0.4, -3.1415926536, 3.1415926536, -3.1415926536 )),
    new ArrayRealVector(Array[Double]( 0.4, -2.3561944902, 4.6313263879, -2.0133272226 )),
    new ArrayRealVector(Array[Double]( 0.4, -1.5707963268, 5.4905118820, -1.1592794807 )),
    new ArrayRealVector(Array[Double]( 0.4, -0.7853981634, 5.9556622951, -0.5295973782 )),
    new ArrayRealVector(Array[Double]( 0.4,  0.0000000000, 0.0000000000,  0.0000000000 )),
    new ArrayRealVector(Array[Double]( 0.4,  0.7853981634, 0.3275230120,  0.5295973782 )),
    new ArrayRealVector(Array[Double]( 0.4,  1.5707963268, 0.7926734251,  1.1592794807 )),
    new ArrayRealVector(Array[Double]( 0.4,  2.3561944902, 1.6518589193,  2.0133272226 )),
    new ArrayRealVector(Array[Double]( 0.4,  3.1415926536, 3.1415926536,  3.1415926536 )),
    new ArrayRealVector(Array[Double]( 0.5, -3.1415926536, 3.1415926536, -3.1415926536 )),
    new ArrayRealVector(Array[Double]( 0.5, -2.3561944902, 4.8599063090, -1.8969240191 )),
    new ArrayRealVector(Array[Double]( 0.5, -1.5707963268, 5.6690004579, -1.0471975512 )),
    new ArrayRealVector(Array[Double]( 0.5, -0.7853981634, 6.0399192373, -0.4694752612 )),
    new ArrayRealVector(Array[Double]( 0.5,  0.0000000000, 0.0000000000,  0.0000000000 )),
    new ArrayRealVector(Array[Double]( 0.5,  0.7853981634, 0.2432660699,  0.4694752612 )),
    new ArrayRealVector(Array[Double]( 0.5,  1.5707963268, 0.6141848493,  1.0471975512 )),
    new ArrayRealVector(Array[Double]( 0.5,  2.3561944902, 1.4232789981,  1.8969240191 )),
    new ArrayRealVector(Array[Double]( 0.5,  3.1415926536, 3.1415926536,  3.1415926536 )),
    new ArrayRealVector(Array[Double]( 0.6, -3.1415926536, 3.1415926536, -3.1415926536 )),
    new ArrayRealVector(Array[Double]( 0.6, -2.3561944902, 5.1147902016, -1.7579210263 )),
    new ArrayRealVector(Array[Double]( 0.6, -1.5707963268, 5.8358900892, -0.9272952180 )),
    new ArrayRealVector(Array[Double]( 0.6, -0.7853981634, 6.1130525738, -0.4084391419 )),
    new ArrayRealVector(Array[Double]( 0.6,  0.0000000000, 0.0000000000,  0.0000000000 )),
    new ArrayRealVector(Array[Double]( 0.6,  0.7853981634, 0.1701327334,  0.4084391419 )),
    new ArrayRealVector(Array[Double]( 0.6,  1.5707963268, 0.4472952180,  0.9272952180 )),
    new ArrayRealVector(Array[Double]( 0.6,  2.3561944902, 1.1683951056,  1.7579210263 )),
    new ArrayRealVector(Array[Double]( 0.6,  3.1415926536, 3.1415926536,  3.1415926536 )),
    new ArrayRealVector(Array[Double]( 0.7, -3.1415926536, 3.1415926536, -3.1415926536 )),
    new ArrayRealVector(Array[Double]( 0.7, -2.3561944902, 5.3982470734, -1.5848689216 )),
    new ArrayRealVector(Array[Double]( 0.7, -1.5707963268, 5.9876864670, -0.7953988302 )),
    new ArrayRealVector(Array[Double]( 0.7, -0.7853981634, 6.1750733901, -0.3445591705 )),
    new ArrayRealVector(Array[Double]( 0.7,  0.0000000000, 0.0000000000,  0.0000000000 )),
    new ArrayRealVector(Array[Double]( 0.7,  0.7853981634, 0.1081119171,  0.3445591705 )),
    new ArrayRealVector(Array[Double]( 0.7,  1.5707963268, 0.2954988402,  0.7953988302 )),
    new ArrayRealVector(Array[Double]( 0.7,  2.3561944902, 0.8849382337,  1.5848689216 )),
    new ArrayRealVector(Array[Double]( 0.7,  3.1415926536, 3.1415926536,  3.1415926536 )),
    new ArrayRealVector(Array[Double]( 0.8, -3.1415926536, 3.1415926536, -3.1415926536 )),
    new ArrayRealVector(Array[Double]( 0.8, -2.3561944902, 5.7094260302, -1.3552464166 )),
    new ArrayRealVector(Array[Double]( 0.8, -1.5707963268, 6.1196841984, -0.6435011088 )),
    new ArrayRealVector(Array[Double]( 0.8, -0.7853981634, 6.2255591484, -0.2744074161 )),
    new ArrayRealVector(Array[Double]( 0.8,  0.0000000000, 0.0000000000,  0.0000000000 )),
    new ArrayRealVector(Array[Double]( 0.8,  0.7853981634, 0.0576261588,  0.2744074161 )),
    new ArrayRealVector(Array[Double]( 0.8,  1.5707963268, 0.1635011088,  0.6435011088 )),
    new ArrayRealVector(Array[Double]( 0.8,  2.3561944902, 0.5737592770,  1.3552464166 )),
    new ArrayRealVector(Array[Double]( 0.8,  3.1415926536, 3.1415926536,  3.1415926536 )),
    new ArrayRealVector(Array[Double]( 0.9, -3.1415926536, 3.1415926536, -3.1415926536 )),
    new ArrayRealVector(Array[Double]( 0.9, -2.3561944902, 6.0344980120, -1.0116016436 )),
    new ArrayRealVector(Array[Double]( 0.9, -1.5707963268, 6.2244594003, -0.4510268118 )),
    new ArrayRealVector(Array[Double]( 0.9, -0.7853981634, 6.2632181052, -0.1894852237 )),
    new ArrayRealVector(Array[Double]( 0.9,  0.0000000000, 0.0000000000,  0.0000000000 )),
    new ArrayRealVector(Array[Double]( 0.9,  0.7853981634, 0.0199672020,  0.1894852237 )),
    new ArrayRealVector(Array[Double]( 0.9,  1.5707963268, 0.0587259069,  0.4510268118 )),
    new ArrayRealVector(Array[Double]( 0.9,  2.3561944902, 0.2486872952,  1.0116016436 )),
    new ArrayRealVector(Array[Double]( 0.9,  3.1415926536, 3.1415926536,  3.1415926536 ))
  )

	val parabolicAngles = List[ArrayRealVector](
		// e, nu, M, B
		// Calculation using Fundamentals of Astrodynamics & applications matlab script: 'newtonnu'
			new ArrayRealVector(Array[Double]( 1.0, -3.1415926536, Double.PositiveInfinity, Double.PositiveInfinity )),
			new ArrayRealVector(Array[Double]( 1.0, -2.3561944902, -7.1045694997, -2.4142135624 )),
			new ArrayRealVector(Array[Double]( 1.0, -1.5707963268, -1.3333333333, -1.0000000000 )),
			new ArrayRealVector(Array[Double]( 1.0, -0.7853981634, -0.4379028330, -0.4142135624 )),
			new ArrayRealVector(Array[Double]( 1.0,  0.0000000000,  0.0000000000,  0.0000000000 )),
			new ArrayRealVector(Array[Double]( 1.0,  0.7853981634,  0.4379028330,  0.4142135624 )),
			new ArrayRealVector(Array[Double]( 1.0,  1.5707963268,  1.3333333333,  1.0000000000 )),
			new ArrayRealVector(Array[Double]( 1.0,  2.3561944902,  7.1045694997,  2.4142135624 )),
			new ArrayRealVector(Array[Double]( 1.0,  3.1415926536, Double.PositiveInfinity, Double.PositiveInfinity))
	)

  val hyperbolicAngles = List[ArrayRealVector](
		// e, nu, M, H
    // Calculation using Fundamentals of Astrodynamics & applications matlab script: 'newtonnu'
    new ArrayRealVector(Array[Double]( 1.1, -3.1415926536, Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.1, -2.3561944902, -0.4327905309, -1.1714792614 )),
    new ArrayRealVector(Array[Double]( 1.1, -1.5707963268, -0.0605150721, -0.4435682544 )),
    new ArrayRealVector(Array[Double]( 1.1, -0.7853981634, -0.0192210672, -0.1812723729 )),
    new ArrayRealVector(Array[Double]( 1.1,  0.0000000000,  0.0000000000, 0.0000000000 )),
    new ArrayRealVector(Array[Double]( 1.1,  0.7853981634,  0.0192210672, 0.1812723729 )),
    new ArrayRealVector(Array[Double]( 1.1,  1.5707963268,  0.0605150721, 0.4435682544 )),
    new ArrayRealVector(Array[Double]( 1.1,  2.3561944902,  0.4327905309, 1.1714792614 )),
    new ArrayRealVector(Array[Double]( 1.1,  3.1415926536,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.2, -3.1415926536,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.2, -2.3561944902, -1.8673241467, -1.8485467854 )),
    new ArrayRealVector(Array[Double]( 1.2, -1.5707963268, -0.1736274460, -0.6223625037 )),
    new ArrayRealVector(Array[Double]( 1.2, -0.7853981634, -0.0533943151, -0.2510911184 )),
    new ArrayRealVector(Array[Double]( 1.2,  0.0000000000,  0.0000000000, 0.0000000000 )),
    new ArrayRealVector(Array[Double]( 1.2,  0.7853981634,  0.0533943151, 0.2510911184 )),
    new ArrayRealVector(Array[Double]( 1.2,  1.5707963268,  0.1736274460, 0.6223625037 )),
    new ArrayRealVector(Array[Double]( 1.2,  2.3561944902,  1.8673241467, 1.8485467854 )),
    new ArrayRealVector(Array[Double]( 1.2,  3.1415926536,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.3, -3.1415926536,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.3, -2.3561944902, -6.7727599434, -2.6819937631 )),
    new ArrayRealVector(Array[Double]( 1.3, -1.5707963268, -0.3234281913, -0.7564329109 )),
    new ArrayRealVector(Array[Double]( 1.3, -0.7853981634, -0.0963990804, -0.3014550594 )),
    new ArrayRealVector(Array[Double]( 1.3,  0.0000000000,  0.0000000000, 0.0000000000 )),
    new ArrayRealVector(Array[Double]( 1.3,  0.7853981634,  0.0963990804, 0.3014550594 )),
    new ArrayRealVector(Array[Double]( 1.3,  1.5707963268,  0.3234281913, 0.7564329109 )),
    new ArrayRealVector(Array[Double]( 1.3,  2.3561944902,  6.7727599434, 2.6819937631 )),
    new ArrayRealVector(Array[Double]( 1.3,  3.1415926536,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.4, -3.1415926536,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.4, -2.3561944902, -91.5810741071, -4.9263474634 )),
    new ArrayRealVector(Array[Double]( 1.4, -1.5707963268, -0.5046995295, -0.8670147265 )),
    new ArrayRealVector(Array[Double]( 1.4, -0.7853981634, -0.1459395291, -0.3414841243 )),
    new ArrayRealVector(Array[Double]( 1.4,  0.0000000000,  0.0000000000, 0.0000000000 )),
    new ArrayRealVector(Array[Double]( 1.4,  0.7853981634,  0.1459395291, 0.3414841243 )),
    new ArrayRealVector(Array[Double]( 1.4,  1.5707963268,  0.5046995295, 0.8670147265 )),
    new ArrayRealVector(Array[Double]( 1.4,  2.3561944902,  91.5810741071, 4.9263474634 )),
    new ArrayRealVector(Array[Double]( 1.4,  3.1415926536,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.5, -3.1415926536,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.5, -2.3561944902,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.5, -1.5707963268, -0.7146273330, -0.9624236501 )),
    new ArrayRealVector(Array[Double]( 1.5, -0.7853981634, -0.2006619342, -0.3748109841 )),
    new ArrayRealVector(Array[Double]( 1.5,  0.0000000000,  0.0000000000, 0.0000000000 )),
    new ArrayRealVector(Array[Double]( 1.5,  0.7853981634,  0.2006619342, 0.3748109841 )),
    new ArrayRealVector(Array[Double]( 1.5,  1.5707963268,  0.7146273330, 0.9624236501 )),
    new ArrayRealVector(Array[Double]( 1.5,  2.3561944902,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.5,  3.1415926536,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.6, -3.1415926536,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.6, -2.3561944902,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.6, -1.5707963268, -0.9514314445, -1.0469679150 )),
    new ArrayRealVector(Array[Double]( 1.6, -0.7853981634, -0.2596477387, -0.4033442220 )),
    new ArrayRealVector(Array[Double]( 1.6,  0.0000000000,  0.0000000000, 0.0000000000 )),
    new ArrayRealVector(Array[Double]( 1.6,  0.7853981634,  0.2596477387, 0.4033442220 )),
    new ArrayRealVector(Array[Double]( 1.6,  1.5707963268,  0.9514314445, 1.0469679150 )),
    new ArrayRealVector(Array[Double]( 1.6,  2.3561944902,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.6,  3.1415926536,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.7, -3.1415926536,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.7, -2.3561944902,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.7, -1.5707963268, -1.2138826218, -1.1232309826 )),
    new ArrayRealVector(Array[Double]( 1.7, -0.7853981634, -0.3222254835, -0.4282412259 )),
    new ArrayRealVector(Array[Double]( 1.7,  0.0000000000,  0.0000000000, 0.0000000000 )),
    new ArrayRealVector(Array[Double]( 1.7,  0.7853981634,  0.3222254835, 0.4282412259 )),
    new ArrayRealVector(Array[Double]( 1.7,  1.5707963268,  1.2138826218, 1.1232309826 )),
    new ArrayRealVector(Array[Double]( 1.7,  2.3561944902,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.7,  3.1415926536,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.8, -3.1415926536,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.8, -2.3561944902,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.8, -1.5707963268, -1.5010825875, -1.1929107310 )),
    new ArrayRealVector(Array[Double]( 1.8, -0.7853981634, -0.3878808926, -0.4502691762 )),
    new ArrayRealVector(Array[Double]( 1.8,  0.0000000000,  0.0000000000, 0.0000000000 )),
    new ArrayRealVector(Array[Double]( 1.8,  0.7853981634,  0.3878808926, 0.4502691762 )),
    new ArrayRealVector(Array[Double]( 1.8,  1.5707963268,  1.5010825875, 1.1929107310 )),
    new ArrayRealVector(Array[Double]( 1.8,  2.3561944902,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.8,  3.1415926536,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.9, -3.1415926536,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.9, -2.3561944902,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.9, -1.5707963268, -1.8123481135, -1.2571958266 )),
    new ArrayRealVector(Array[Double]( 1.9, -0.7853981634, -0.4562072018, -0.4699684602 )),
    new ArrayRealVector(Array[Double]( 1.9,  0.0000000000,  0.0000000000, 0.0000000000 )),
    new ArrayRealVector(Array[Double]( 1.9,  0.7853981634,  0.4562072018, 0.4699684602 )),
    new ArrayRealVector(Array[Double]( 1.9,  1.5707963268,  1.8123481135, 1.2571958266 )),
    new ArrayRealVector(Array[Double]( 1.9,  2.3561944902,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 1.9,  3.1415926536,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 2.0, -3.1415926536,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 2.0, -2.3561944902,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 2.0, -1.5707963268, -2.1471437182, -1.3169578969 )),
    new ArrayRealVector(Array[Double]( 2.0, -0.7853981634, -0.5268749860, -0.4877368863 )),
    new ArrayRealVector(Array[Double]( 2.0,  0.0000000000,  0.0000000000, 0.0000000000 )),
    new ArrayRealVector(Array[Double]( 2.0,  0.7853981634,  0.5268749860, 0.4877368863 )),
    new ArrayRealVector(Array[Double]( 2.0,  1.5707963268,  2.1471437182, 1.3169578969 )),
    new ArrayRealVector(Array[Double]( 2.0,  2.3561944902,  Double.NaN, Double.NaN )),
    new ArrayRealVector(Array[Double]( 2.0,  3.1415926536,  Double.NaN, Double.NaN ))
  )

	val circularEllipticalAngels = circularAngles ::: ellipticalAngles
  val all                      = circularAngles ::: ellipticalAngles ::: parabolicAngles ::: hyperbolicAngles

	def checkEqual(v: ArrayRealVector, true_value: Double, computed_value: Double): Boolean = {
		if ( computed_value.isNaN && true_value.isNaN ) {
			true; // Difference in definition for angles that are not reachable
		} else if ( true_value.isInfinite ) {
			// Accuracy breaks down as slight angles generate huge differences in anomaly
			abs(computed_value) > 1E10
		} else if ( abs(v.getEntry(0)) < 1) {
			val dom = new AngularDomain(true_value, 1E-10, 1E-10)
			dom.inBounds(computed_value)
		} else {
			Precision.equals(true_value, computed_value, abs(true_value * 1E-8) )
		}
	}

	def checkEqualAnomaly(    v: ArrayRealVector, computedValue: Double) =
    checkEqual(v, v.getEntry(3), computedValue)

  def checkEqualMeanAnomaly(v: ArrayRealVector, computedValue: Double) =
    checkEqual(v, v.getEntry(2), computedValue)

  def createKeplerElements(v: ArrayRealVector) =
    new KeplerElements(Double.NaN, v.getEntry(0), Double.NaN, Double.NaN, Double.NaN, v.getEntry(1))

}

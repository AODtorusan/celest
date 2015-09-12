/**
 * Copyright (C) 2009-2015 simon <simon@angelcorp.be>
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
package be.angelcorp.celest.math.functions

import breeze.numerics.{abs, sqrt}
import org.apache.commons.math3.analysis.UnivariateFunction
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction
import org.apache.commons.math3.analysis.polynomials.PolynomialsUtils

/**
 * <p>
 * Complete algorithm for associated function based on NUMERICAL RECIPES IN FORTRAN 77: THE ART OF
 * SCIENTIFIC COMPUTING (ISBN 0-521-43064-X) chapter Spherical Harmonics 6.8 p 247
 * </p>
 *
 * <p>
 * Recursion equations for associated function based on wakker eq 20.2-2
 * </p>
 *
 * @author simon
 *
 */
object LegendreUtils {
  /**
   * Computes the associated Legendre function of the first kind P<sub>l,m</sub>(x)
   *
   * <p>
   * Based on NUMERICAL RECIPES IN FORTRAN 77: THE ART OF SCIENTIFIC COMPUTING (ISBN 0-521-43064-X)
   * chapter Spherical Harmonics 6.8 p 247
   * </p>
   *
   * @param l
	 * Order of the Legendre function
   * @param m
	 * Degree of the Legendre function, where 0 <= m <= l
   * @param x
	 * Point of evaluations, -1 <= x <= 1
   */
  def associatedLegendreFunctionOfTheFirstKind(l: Int, m: Int, x: Double): Double = {
    if (m < 0) throw new IllegalArgumentException(s"Bad arguments given (m < 0) where m=$m")
    if (m > 1) throw new IllegalArgumentException(s"Bad arguments given (m > 1) where m=$m")
    if (abs(x) > 1) throw new IllegalArgumentException(s"Bad arguments given (abs(x) > 1) where x=$x")

    var fact: Double = 0.0
    var pmm: Double = 1.0
    var somx2: Double = 0.0
    if (m > 0) {
      somx2 = sqrt((1.0 - x) * (1.0 + x))
      fact = 1.0
      for (i <- 1 to m) {
        pmm = -pmm * fact * somx2
        fact = fact + 2.0
      }
    }

    /* Compute Pl,m */
    var plgndr: Double = 0.0
    var pll: Double = 0
    var pmmp1: Double = 0.0
    if (l == m) {
      plgndr = pmm
    } else {
      // Compute Pm+1,m
      pmmp1 = x * (2 * m + 1) * pmm
      if (l == m + 1)
        plgndr = pmmp1
      else { // Compute Pl,m, l > m+ 1
        for (ll <- m + 2 to l) {
          pll = (x * (2 * ll - 1) * pmmp1 - (ll + m - 1) * pmm) / (ll - m)
          pmm = pmmp1
          pmmp1 = pll
        }
        plgndr = pll
      }
    }
    plgndr
  }

  /**
   * Computes the associated Legendre function of the first kind P<sub>l,0</sub>(x) with a recursion
   * formula
   *
   * <p>
   * Uses recursion formula: (n+1) P<sub>n+1,0</sub>(x) = (2n+1) x P<sub>n,0</sub>(x) - n
   * P<sub>n-1,0</sub>(x)
   * </p>
   *
   * @param l
	 * Order of the Legendre function
   * @param x
	 * Point of evaluations, -1 <= x <= 1
   * @param pn_10
	 * Value of P<sub>l-1,0</sub>(x)
   * @param pn_20
	 * Value of P<sub>l-2,0</sub>(x)
   */
  def associatedLegendreFunctionOfTheFirstKindRec1(l: Int, m: Int, x: Double, pn_10: Double, pn_20: Double): Double =
    ((2 * l - 1) / l) * x * pn_10 - ((l - 1) / l) * pn_20

  /**
   * Computes the associated Legendre function of the first kind P<sub>l,0</sub>(x) with a recursion
   * furmula
   *
   * <p>
   * Uses recursion formula: (n+1) P<sub>n+1,0</sub>(x) = (2n+1) x P<sub>n,0</sub>(x) - n
   * P<sub>n-1,0</sub>(x)
   * </p>
   *
   * @param l
	 * Order of the Legendre function
   * @param pn_10
	 * Function P<sub>l-1,0</sub>(x)
   * @param pn_20
	 * Function P<sub>l-2,0</sub>(x)
   */
  def associatedLegendreFunctionOfTheFirstKindRec1(l: Int, pn_10: UnivariateFunction, pn_20: UnivariateFunction): Double => Double =
    (x: Double) => ((2 * l - 1) / l) * x * pn_10.value(x) - ((l - 1) / l) * pn_20.value(x)

  /**
   * Computes the associated Legendre function of the first kind P<sub>l,m</sub>(x) with a recursion
   * formula, where l = m
   *
   * <p>
   * Uses recursion formula: P<sub>n,n</sub>(x) = (2n-1) (1-x<sup>2</sup>)<sup>0.5</sup>
   * P<sub>n-1,n-1</sub>(x)
   * </p>
   *
   * @param l
	 * Order of the Legendre function
   * @param x
	 * Point of evaluations, -1 <= x <= 1
   * @param pl_1l_1
	 * Value of the function P<sub>l-1,l-1</sub>(x)
   * @return
   */
  def associatedLegendreFunctionOfTheFirstKindRec2(l: Int, x: Double, pl_1l_1: Double): Double =
    (2 * l - 1) * Math.sqrt(1 - x * x) * pl_1l_1

  /**
   * Computes the associated Legendre function of the first kind P<sub>l,m</sub>(x) with a recursion
   * formula, where l = m
   *
   * <p>
   * Uses recursion formula: P<sub>n,n</sub>(x) = (2n-1) (1-x<sup>2</sup>)<sup>0.5</sup>
   * P<sub>n-1,n-1</sub>(x)
   * </p>
   *
   * @param l
	 * Order of the Legendre function
   * @param pl_1l_1
	 * Function P<sub>l-1,l-1</sub>(x)
   */
  def associatedLegendreFunctionOfTheFirstKindRec2(l: Int, pl_1l_1: Double => Double): Double => Double =
    (x: Double) => (2 * l - 1) * Math.sqrt(1 - x * x) * pl_1l_1(x)

  /**
   * Computes the associated Legendre function of the first kind P<sub>l,m</sub>(x) with a recursion
   * formula, where l-1 = m
   *
   * <p>
   * Uses recursion formula: P<sub>n,n-1</sub>(x) = (2n-1) x P<sub>n-1,n-1</sub>(x)
   * </p>
   *
   * @param l
	 * Order of the Legendre function
   * @param x
	 * Point of evaluations, -1 <= x <= 1
   * @param pl_1l_1
	 * Value of the function P<sub>l-1,l-1</sub>(x)
   */
  def associatedLegendreFunctionOfTheFirstKindRec3(l: Int, x: Double, pl_1l_1: Double): Double =
    (2 * l - 1) * x * pl_1l_1

  /**
   * Computes the associated Legendre function of the first kind P<sub>l,m</sub>(x) with a recursion
   * formula, where l-1 = m
   *
   * <p>
   * Uses recursion formula: P<sub>n,n-1</sub>(x) = (2n-1) x P<sub>n-1,n-1</sub>(x)
   * </p>
   *
   * @param l
	 * Order of the Legendre function
   * @param pl_1l_1
	 * Function P<sub>l-1,l-1</sub>(x)
   */
  def associatedLegendreFunctionOfTheFirstKindRec3(l: Int, pl_1l_1: Double => Double): Double => Double =
    (x: Double) => (2 * l - 1) * x * pl_1l_1(x)

  /**
   * Computes the associated Legendre function of the first kind P<sub>l,m</sub>(x) with a recursion
   * formula, where l-1 = m
   *
   * <p>
   * Uses recursion formula: P<sub>n,m</sub>(x) = ((2n-1)/(n-m)) x P<sub>n-1,m</sub>(x) -
   * ((n+m-1)/(n-m)) P<sub>n-2,m</sub>(x)
   * </p>
   *
   * @param l
	 * Order of the Legendre function
   * @param m
	 * Degree of the Legendre function, where 0 <= m <= l
   * @param x
	 * Point of evaluations, -1 <= x <= 1
   * @param pl_1m
	 * Value of the function P<sub>l-1,m</sub>(x)
   * @param pl_2m
	 * Value of the function P<sub>l-2,m</sub>(x)
   */
  def associatedLegendreFunctionOfTheFirstKindRec4(l: Int, m: Int, x: Double, pl_1m: Double, pl_2m: Double): Double =
    ((2 * l - 1) / (l - m)) * x * pl_1m - ((l + m - 1) / (l - m)) * pl_2m

  /**
   * Computes the associated Legendre function of the first kind P<sub>l,m</sub>(x) with a recursion
   * formula, where l-1 = m
   *
   * <p>
   * Uses recursion formula: P<sub>n,m</sub>(x) = ((2n-1)/(n-m)) x P<sub>n-1,m</sub>(x) -
   * ((n+m-1)/(n-m)) P<sub>n-2,m</sub>(x)
   * </p>
   *
   * @param l
	 * Order of the Legendre function
   * @param m
	 * Degree of the Legendre function, where 0 <= m <= l
   * @param pl_1m
	 * Function P<sub>l-1,m</sub>(x)
   * @param pl_2m
	 * Function P<sub>l-2,m</sub>(x)
   */
  def associatedLegendreFunctionOfTheFirstKindRec4(l: Int, m: Int, pl_1m: Double => Double, pl_2m: Double => Double): Double => Double =
    (x: Double) => ((2 * l - 1) / (l - m)) * x * pl_1m(x) - ((l + m - 1) / (l - m)) * pl_2m(x)

  /**
   * Shorthand function
   * <p>
   * Computes the value of a Legendre polynomial P<sub>l</sub>(x)
   * </p>
   *
   * @see be.angelcorp.celest.math.functions.LegendreUtils#legendrePolynomial(int, double)
   */
  def legendreP(l: Int, x: Double): Double =
    legendrePolynomial(l, x)

  /**
   * Shorthand function
   * <p>
   * Computes the associated Legendre function of the first kind P<sub>l,m</sub>(x)
   * </p>
   *
   * @see be.angelcorp.celest.math.functions.LegendreUtils#associatedLegendreFunctionOfTheFirstKind(int, int, double)
   */
  def legendreP(l: Int, m: Int, x: Double): Double =
    associatedLegendreFunctionOfTheFirstKind(l, m, x)

  /**
   * Computes the value of a Legendre polynomial P<sub>l</sub>(x)
   *
   * @param l
	 * Order of the Legendre function
   * @param x
	 * Point of evaluations, -1 <= x <= 1
   */
  def legendrePolynomial(l: Int, x: Double): Double =
    PolynomialsUtils.createLegendrePolynomial(l).value(x)

  /**
   * Computes the value of a Legendre polynomial P<sub>l</sub>(x)
   *
   *
   * @param l
	 * Order of the Legendre function
   * @param x
	 * Point of evaluations, -1 <= x <= 1
   */
  def legendrePolynomialF(l: Int, x: Double): PolynomialFunction =
    PolynomialsUtils.createLegendrePolynomial(l)

}
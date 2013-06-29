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

package be.angelcorp.libs.celest.time.timeStandard

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import be.angelcorp.libs.celest.time.TimeUtils._
import be.angelcorp.libs.celest.time._

@RunWith(classOf[JUnitRunner])
class TestTDB extends FlatSpec with ShouldMatchers {

  "TDB" should "select transform symmetrically" in {
    implicit val universe = new MockTimeUniverse()
    val tdb       = new TDB( universe.J2000_EPOCH )
    val base_date = new JulianDate( 2012, 04, 15, 12, 03, 06.24, tdb )

    expect( base_date.getJD  ) { base_date.getJulianDate( universe.TT ).getJulianDate( tdb ).getJD }
  }

  /**
   * Conversion between various time standards, test date and results from:
   * <p>
   * D. Vallado et al. ,
   * <b>"Implementation Issues Surrounding the New IAU Reference Systems for Astrodynamics"</b>, 16th
   * AAS/AIAA Space Flight Mechanics Conference, Florida, January 2006
   * </p>
   */
  it should "conform to the 'Implementation Issues Surrounding the New IAU Reference Systems for Astrodynamics' validation data" in {
    // April 6, 2004, 7:51:28.386009 UTC = JD 2453101.827411875
    // dut1 -0.439962 s dat 32 s
    implicit val universe = new MockTimeUniverse()
    val date = new JulianDate(2004, 04, 06, 00, 00, 28352.5700090000, universe.TT) // Test epoch in TT, see paper.

    // Reference seconds in the current day TDB
    val tdb_seconds = 28352.5716651154

    // 1E-4 due to the precision restriction of JulianDate
    val jd_tdb = date.getJulianDate( new TDB( universe.J2000_EPOCH ) )
    secondsInDay( jd_tdb.getJD ) should be (tdb_seconds plusOrMinus 1E-4)
  }

  /**
   * Conversion between various time standards, test date and results from:
   * <p>
   * D.A. Vallado, <b>"Fundamentals of Astrodynamics and Applications"</b>, 2007, ISBN:
   * 978-0-387-71831-6, p201 example 3-7. Important: The book contains errata for this example!
   * </p>
   */
  it should "conform to the 'Fundamentals of Astrodynamics and Applications' validation data" in {
    // Reference epoch in the book are for April 6, 2004, 16:43:00.0000 UTC or 16:44:04.1840 TT (not May 14)
    implicit val universe = new MockTimeUniverse()
    val jd_tt = new JulianDate( 2004, 4, 06, 16, 44, 04.1840, universe.TT )

    val tdb = new TDB( universe.J2000_EPOCH )
    tdb.offsetFromTT(jd_tt) should be ( 0.00164 plusOrMinus 1E-5)
  }
}
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
package be.angelcorp.libs.celest.time

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import be.angelcorp.libs.celest.universe.DefaultUniverse
import be.angelcorp.libs.util.physics.Time
import java.util.{Calendar, GregorianCalendar}
import be.angelcorp.libs.celest.time.dateStandard.DateStandards

@RunWith(classOf[JUnitRunner])
class TestJulianDate extends FlatSpec with ShouldMatchers  {

  implicit val universe = new DefaultUniverse

  "JulianDate" should "correctly add time intervals" in {
		// Check if the numerical value of JD gets correctly added
		val jd0 = new JulianDate(5)
    val dt = 325;// [s]

    jd0.add(dt, Time.second).jd should equal (5 + Time.convert(dt, Time.second, Time.day))

		// Check of the value of ITimeStandard is correctly kept after adding
		val jd1 = new JulianDate(1.0, universe.TT)
		val jd1_actual = jd1.add(1, Time.day_julian)
    jd1_actual.jd           should be    (2.0 plusOrMinus 1E-16)
		jd1_actual.timeStandard should equal (universe.TT)
	}

  it should "reference retain the correct TimeStandard" in {
		// J2000 epoch
		// Terrestrial Time: January 1 2000, 12:00:00 TT = 2451545.0 JD TT
		// International Atomic Time: January 1, 2000, 11:59:27.816 TAI = 2451544.99962750 JD TAI
		// Coordinated Universal Time: January 1, 2000, 11:58:55.816 UTC = 2451544.99925713 JD UTC
		val jd_tt_j2000 = new JulianDate(2451545.0, universe.TT)
    val jd_tai_j2000_true = new JulianDate(2451544.99962750, universe.TAI)
    val jd_utc_j2000_true = new JulianDate(2451544.99925713, universe.UTC)
    jd_tt_j2000.timeStandard       should equal (universe.TT )
    jd_tai_j2000_true.timeStandard should equal (universe.TAI)
    jd_utc_j2000_true.timeStandard should equal (universe.UTC)

		val jd_tai_j2000_actual = jd_tt_j2000.inTimeStandard(universe.TAI)
    val jd_utc_j2000_actual = jd_tt_j2000.inTimeStandard(universe.UTC)
    jd_tai_j2000_actual.timeStandard should equal (universe.TAI)
    jd_utc_j2000_actual.timeStandard should equal (universe.UTC)

    jd_tai_j2000_actual.jd should be ( jd_tai_j2000_true.jd plusOrMinus 1E-8 )
    jd_utc_j2000_actual.jd should be ( jd_utc_j2000_true.jd plusOrMinus 1E-8 )
	}

  it should "return the correct Julian date" in {
		// MATLAB:
		// % October 10, 2004, at 12:21:18 p.m.:
		// fprintf('%.10f\n', juliandate(2004,10,10,12,21,18))
		// 2453289.0147916665
		val cal = new GregorianCalendar(2004, 10, 10, 12, 21, 18)
    val jd  = new JulianDate(2453289.0147916665)
    val jd2 = new JulianDate(cal.getTime, universe.UTC)
    val jd3 = new JulianDate(2453289.0147916665, DateStandards.JULIAN_DATE)
    val jd4 = new JulianDate(53288.5147916665, DateStandards.MODIFIED_JULIAN_DAY)

		/* Check jd get */
		/* Less then half a second off ? */
    jd.jd should be (2453289.0147916665 plusOrMinus 1.0 / (24 * 3600 * 1000))
    jd2.jd should be (2453289.0147916665 plusOrMinus 1.0 / (24 * 3600 * 1000))
    jd3.jd should be (2453289.0147916665 plusOrMinus 1.0 / (24 * 3600 * 1000))
    jd4.jd should be (2453289.0147916665 plusOrMinus 1.0 / (24 * 3600 * 1000))

    DateStandards.JULIAN_DATE.fromJD(jd.jd)       should be (2453289.0147916665 plusOrMinus 1.0 / (24 * 3600 * 1000))
    DateStandards.JULIAN_DAY_NUMBER.fromJD(jd.jd) should equal (2453289)
  }

  it should "return the correct java.util date" in {
    // MATLAB:
    // % October 10, 2004, at 12:21:18 p.m.:
    // fprintf('%.10f\n', juliandate(2004,10,10,12,21,18))
    // 2453289.0147916665
    val jd  = new JulianDate(2453289.0147916665)

		/* Check date get */
		/* Check conversion to date */
    val cal = new GregorianCalendar()
		cal.setTime(jd.date)

    cal.get(Calendar.YEAR)        should equal (2004)
		cal.get(Calendar.MONTH)       should equal (10)
		cal.get(Calendar.DATE)        should equal (10)
		cal.get(Calendar.HOUR_OF_DAY) should equal (12)
		cal.get(Calendar.MINUTE)      should equal (21)
		cal.get(Calendar.SECOND)      should equal (18)
	}

  it should "compute the correct relative offset to another Epoch" in {
		val epoch1 = new JulianDate(0)
    val epoch2 = new JulianDate(5.22)

    epoch2.relativeTo(epoch1) should equal (5.22)
		epoch2.relativeTo(epoch1, Time.second) should equal (Time.convert(5.22, Time.day))
	}

  it should "compute the correct faction-in-day quantity" in {
    val epoch1 = new JulianDate(5.81)
    val epoch2 = new JulianDate(5.22)

    epoch1.fractionInDay should be (0.31 plusOrMinus 1E-15)
    epoch2.fractionInDay should be (0.72 plusOrMinus 1E-15)
  }

}

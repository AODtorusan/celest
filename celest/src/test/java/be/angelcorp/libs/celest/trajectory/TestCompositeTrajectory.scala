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
package be.angelcorp.libs.celest.trajectory

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FlatSpec
import org.scalatest.matchers.ShouldMatchers
import be.angelcorp.libs.celest.universe.DefaultUniverse
import be.angelcorp.libs.celest.state.{PosVel, Orbit}
import be.angelcorp.libs.celest.frames.IReferenceFrame
import be.angelcorp.libs.celest.time.{JulianDate, Epoch}
import be.angelcorp.libs.util.physics.Time


@RunWith(classOf[JUnitRunner])
class TestCompositeTrajectory extends FlatSpec with ShouldMatchers  {

    implicit val universe = new DefaultUniverse

	/** Test state that contains a given constant */
	class TestState( val value: Double ) extends Orbit {
    def frame: Option[IReferenceFrame] = throw new UnsupportedOperationException
    def toPosVel: PosVel = throw new UnsupportedOperationException
  }

  /** Test trajectory that returns the time at which it is evaluated */
	class TestTrajectory(id: Double) extends Trajectory {
    def apply(t: Epoch) = new TestState(t.add(id, Time.day).jd)
  }

  "CompositeTrajectory" should "correctly evaluate the added sub-trajectories" in {
    val trajectory = new CompositeTrajectory()

    // Add various trajectories at various times
    trajectory.trajectories.put( new JulianDate(0),  new TestTrajectory(100) )
    trajectory.trajectories.put( new JulianDate(10), new TestTrajectory(200) )
    trajectory.trajectories.put( new JulianDate(20), new TestTrajectory(300) )

    // Equal begin time as t1
    trajectory(new JulianDate(0 )).asInstanceOf[TestState].value should be (100.0 plusOrMinus 1E-16)
    // In between t1 and t2, t1 should be used
    trajectory(new JulianDate(5 )).asInstanceOf[TestState].value should be (105.0 plusOrMinus 1E-16)
    // Same as above but s2
    trajectory(new JulianDate(15)).asInstanceOf[TestState].value should be (215.0 plusOrMinus 1E-16)
    // Same insertion time as s3
    trajectory(new JulianDate(20)).asInstanceOf[TestState].value should be (320.0 plusOrMinus 1E-16)
    // Time after the last insertion
    trajectory(new JulianDate(25)).asInstanceOf[TestState].value should be (325.0 plusOrMinus 1E-16)
  }

  it should "fail when queried for an epoch before the first trajectory" in {
		val trajectory = new CompositeTrajectory()

		// Add a state a t=0
		trajectory.trajectories.put(new JulianDate(0), new TestTrajectory(0))

		intercept[ArithmeticException] {
      trajectory(new JulianDate(-1)); // There is no state on or before -1 so exception
    }
	}

}

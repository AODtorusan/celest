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
package be.angelcorp.libs.celest.trajectory;

import be.angelcorp.libs.celest.universe.DefaultUniverse;
import be.angelcorp.libs.celest.universe.Universe;
import org.junit.Test;

import be.angelcorp.libs.celest.state.positionState.CartesianElements;
import be.angelcorp.libs.celest.state.positionState.PositionState;
import be.angelcorp.libs.celest.time.JulianDate;
import be.angelcorp.libs.celest.unit.CelestTest;

public class TestDiscreteTrajectory extends CelestTest {

    public static Universe universe = new DefaultUniverse();

	@Test(expected = ArithmeticException.class)
	public void testInalidDiscreteTrajectory() {
		DiscreteTrajectory trajectory = new DiscreteTrajectory();

		// Add a state a t=0
		PositionState s1 = new CartesianElements();
		trajectory.addState(new JulianDate(0, universe), s1);

		try {
			trajectory.evaluate(new JulianDate(-1, universe)); // There is no state on or before -1 so exception
			fail("The should be not state at t=-1, because the first state is at t=0");
		} catch (ArithmeticException success) {
		}
	}

	@Test
	public void testValidDiscreteTrajectory() {
		DiscreteTrajectory trajectory = new DiscreteTrajectory();

		// Add various states at various times
		PositionState s1 = new CartesianElements();
		PositionState s2 = new CartesianElements();
		PositionState s3 = new CartesianElements();
		trajectory.addState(new JulianDate(0,  universe), s1);
		trajectory.addState(new JulianDate(10, universe), s2);
		trajectory.addState(new JulianDate(20, universe), s3);

		assertEquals(s1, trajectory.evaluate(new JulianDate(0,  universe))); // Equal begin time as s1
		assertEquals(s1, trajectory.evaluate(new JulianDate(5,  universe))); // In between s1 and s2
		assertEquals(s2, trajectory.evaluate(new JulianDate(15, universe))); // Same as above but s2
		assertEquals(s3, trajectory.evaluate(new JulianDate(20, universe))); // Same insertion time as s3
		assertEquals(s3, trajectory.evaluate(new JulianDate(25, universe))); // Time after the last insertion
	}

}
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

import be.angelcorp.libs.celest.state.positionState.IPositionState;
import be.angelcorp.libs.celest.time.IJulianDate;

/**
 * A trajectory is a function of time which results in a state for that given time. Evaluating a
 * trajectory over time yields a the path that the satellite follows over the time interval.
 * 
 * @author Simon Billemont
 */
public interface ITrajectory {

	/**
	 * Find the state of a body at a given time
	 * 
	 * @param t
	 *            Time of the epoch [jd, UT]
	 * @return The state at the given epoch
	 * @throws FunctionEvaluationException
	 *             When the state cannot be computed
	 */
	public IPositionState evaluate(IJulianDate t);

}

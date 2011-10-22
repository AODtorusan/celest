/**
 * Copyright (C) 2011 simon <aodtorusan@gmail.com>
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
package be.angelcorp.libs.celest.time;

/**
 * Represents an angle/epoch with a given integer number of degrees, minutes and seconds (double), from a
 * known event or direction.
 * 
 * <p>
 * An angle in the form of aa�mm'ss.sss"
 * </p>
 * 
 * @author simon
 * 
 */
public interface IDegreeMinSec {

	/**
	 * Get the integer amount of degrees preceding the epoch/direction.
	 * 
	 * @return Degrees to the direction
	 */
	public abstract int getDegree();

	/**
	 * Get the integer amount of minutes preceding the epoch/direction from the last full degree.
	 * 
	 * @return Minutes to the direction
	 */
	public abstract int getMinute();

	/**
	 * Get the angle representation as an equivalent amount of radians (complete DMS => rad)
	 * 
	 * @return Radians in this DMS
	 */
	public abstract double getRadian();

	/**
	 * Get the amount of seconds preceding the epoch/direction from the last complete minute.
	 * 
	 * @return Seconds to the direction
	 */
	public abstract double getSecond();

	/**
	 * Set the integer amount of degrees preceding the epoch.
	 * 
	 * @param hour
	 *            Degrees to the direction
	 */
	public abstract void setDegree(int deg);

	/**
	 * Set the integer amount of minutes preceding the epoch/direction from the last complete degree.
	 * 
	 * @param minute
	 *            Minutes to the direction
	 */
	public abstract void setMinute(int minute);

	/**
	 * Set the angle representation as an equivalent amount of radians (complete rad => DMS)
	 * 
	 * @param rad
	 *            Set radians for in this DMS
	 */
	public abstract void setRadian(double rad);

	/**
	 * Set the amount of seconds preceding the epoch/direction from the last complete minute.
	 * 
	 * @param second
	 *            Seconds to the direction
	 */
	public abstract void setSecond(double second);

}
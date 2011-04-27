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
package be.angelcorp.libs.celest.eom.forcesmodel;

import be.angelcorp.libs.celest.body.CelestialBody;
import be.angelcorp.libs.celest.physics.quantities.ObjectForce;
import be.angelcorp.libs.math.linear.Vector3D;

/**
 * 
 * A Gravitational force of a body on a satellite. This is the main driver of most satellites there
 * orbit.
 * 
 * @author simon
 * 
 */
public class GravitationalForce extends ObjectForce {

	/**
	 * Body generating the gravitationalForce
	 */
	private CelestialBody	gravitationBody;

	/**
	 * Create a gravitational force acting on the satellite from the gravitationBody. This uses the
	 * gravitational representation in the gravitationBody
	 * {@link CelestialBody#getGravitationalPotential()}
	 * 
	 * @param satellite
	 *            Satellite where the force is acting on
	 * @param gravitationBody
	 *            Body that is generating the gravitational force
	 */
	public GravitationalForce(CelestialBody satellite, CelestialBody gravitationBody) {
		super(satellite);
		this.gravitationBody = gravitationBody;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector3D getForce() {
		Vector3D f = toAcceleration().multiply(getObject().getMass());
		return f;
	}

	/**
	 * @see GravitationalForce#gravitationBody
	 */
	public CelestialBody getGravitationBody() {
		return gravitationBody;
	}

	/**
	 * @see GravitationalForce#gravitationBody
	 */
	public void setGravitationBody(CelestialBody gravitationBody) {
		this.gravitationBody = gravitationBody;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Vector3D toAcceleration() {
		Vector3D rCenter = getGravitationBody().getState().toCartesianElements().getR();
		Vector3D rSatellite = getObject().getState().toCartesianElements().getR();
		Vector3D g = gravitationBody.getGravitationalPotential().evaluate(rSatellite.subtract(rCenter));
		return g;
	}

}
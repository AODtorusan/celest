package be.angelcorp.libs.celest.body;

import be.angelcorp.libs.celest.constants.Constants;
import be.angelcorp.libs.celest.constants.SolarConstants;
import be.angelcorp.libs.celest.stateVector.CartesianElements;
import be.angelcorp.libs.celest.stateVector.StateVector;

/**
 * Representation of a celestial body (eg planet/sun/satellite). It is a wrapper for a state vector and
 * the mass of the body
 * 
 * @author simon
 * 
 */
public class CelestialBody {

	/**
	 * Body state vector
	 */
	private StateVector	state;
	/**
	 * Body mass
	 */
	private double		mass;

	/**
	 * Generic body with coordinates with R: <0, 0, 0> and V: <0, 0, 0> and with mass of our sun (one
	 * solar mass)
	 */
	public CelestialBody() {
		this(new CartesianElements(), SolarConstants.mass);
	}

	/**
	 * Create a generic body with given statevector (location/speed) and mass
	 * 
	 * @param state
	 *            State of the body
	 * @param mass
	 *            Mass of the body [kg]
	 */
	public CelestialBody(StateVector state, double mass) {
		setState(state);
		setMass(mass);
	}

	/**
	 * Get the mass of the celestial body
	 * <p>
	 * <b>Unit: [kg]</b>
	 * </p>
	 * 
	 * @return Mass of the body
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * Get the gravitational parameter of the celestial body (&mu; = G * m)
	 * <p>
	 * <b>Unit: [m<sup>3</sup> / s<sup>2</sup>]</b>
	 * </p>
	 * 
	 * @return Mu of the body
	 */
	public double getMu() {
		return Constants.mass2mu(mass);
	}

	/**
	 * Get the celestial body state vector (speed/velocity)
	 * 
	 * @return Body state vector
	 */
	public StateVector getState() {
		return state;
	}

	/**
	 * Set the mass of the celestial body
	 * 
	 * @param mass
	 *            new mass of the body [kg]
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}

	/**
	 * Set the gravitational parameter of the celestial body (&mu; = G * m)
	 * 
	 * @param mu
	 *            Mu of the body [m<sup>3</sup> / s<sup>2</sup>]
	 */
	public void setMu(double mu) {
		this.mass = Constants.mu2mass(mu);
	}

	/**
	 * Set the state of the celestial body (speed/location)
	 * 
	 * @param state
	 *            new body state
	 */
	public void setState(StateVector state) {
		this.state = state;
	}

}

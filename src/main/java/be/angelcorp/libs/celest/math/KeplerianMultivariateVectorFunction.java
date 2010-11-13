/**
 * Copyright (C) 2010 Simon Billemont <aodtorusan@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package be.angelcorp.libs.celest.math;

import be.angelcorp.libs.math.functions.MultivariateVectorFunction;

/**
 * A MultivariateVectorFunction that returns a set of keplerian coordinates
 * 
 * @author simon
 * @see MultivariateVectorFunction
 * 
 */
public interface KeplerianMultivariateVectorFunction extends MultivariateVectorFunction, Keplerian {

}

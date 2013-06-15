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
package be.angelcorp.libs.celest.frames;

/**
 * Simple baseclass that takes care some basic tasks, such as combining this
 * {@link IReferenceFrameTransformFactory} with another factory.
 * 
 * 
 * @author Simon Billemont
 * 
 * @param <F0>
 *            Creates transforms from this type of frame.
 * @param <F1>
 *            Creates transforms to this type of frame.
 */
public abstract class BasicReferenceFrameTransformFactory<F0 extends IReferenceFrame, F1 extends IReferenceFrame>
		implements IReferenceFrameTransformFactory<F0, F1> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <F2 extends IReferenceFrame> CompositeFrameTransformFactory<F0, F1, F2> add(
			IReferenceFrameTransformFactory<F1, F2> other) {
		CompositeFrameTransformFactory<F0, F1, F2> newFactory = new CompositeFrameTransformFactory<>(this, other);
		return newFactory;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();
	}

}

package be.angelcorp.libs.celest.frameGraph.transformations

import be.angelcorp.libs.celest.frameGraph.{BasicReferenceFrameTransformFactory, ReferenceFrame}
import be.angelcorp.libs.celest.time.Epoch
import be.angelcorp.libs.math.linear.Matrix3D

trait ConstantRotationTransformFactory[ F0 <: ReferenceFrame, F1 <: ReferenceFrame ]
  extends BasicReferenceFrameTransformFactory[F0, F1] {

  /**
   * Find the transformation between frame F0 and F1 at the specified epoch in the form of a pure rotation matrix.
   * @param epoch Epoch for the frame transformation.
   * @return Rotation matrix to transform from F0 to F1.
   */
  def rotationMatrix(epoch: Epoch): Matrix3D

  /**
   * Factory that generates the inverse transformation from F1 => F0 by inverting the rotation matrix (= transpose)
   */
  class InverseConstantRotationTransformFactory extends BasicReferenceFrameTransformFactory[F1, F0] {
    def cost(epoch: Epoch) = ConstantRotationTransformFactory.this.cost(epoch)

    def transform(epoch: Epoch) =
      new ConstantRotationTransform[F1, F0, InverseConstantRotationTransformFactory]( rotationMatrix(epoch).transpose(), epoch, this )

    def inverse = ConstantRotationTransformFactory.this
  }

  def inverse = new InverseConstantRotationTransformFactory

  def transform(epoch: Epoch) =
    new ConstantRotationTransform[F0, F1, ConstantRotationTransformFactory[F0, F1]]( rotationMatrix(epoch), epoch, this )

}
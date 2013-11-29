package be.angelcorp.celest.time

import com.google.inject.Key
import be.angelcorp.celest.universe.Universe
import be.angelcorp.celest.time.EpochAnnotations._

/**
 * A set of short-hand functions to retrieve predefined epochs's (see [[be.angelcorp.celest.time.EpochAnnotations]]) from the universe
 */
object Epochs {

  /** See [[be.angelcorp.celest.time.EpochAnnotations.J2000]] */
  def J2000(implicit universe: Universe) = universe.injector.getInstance(Key.get(classOf[Epoch], classOf[J2000]))

  /** See [[be.angelcorp.celest.time.EpochAnnotations.J1950]] */
  def J1950(implicit universe: Universe) = universe.injector.getInstance(Key.get(classOf[Epoch], classOf[J1950]))

  /** See [[be.angelcorp.celest.time.EpochAnnotations.J1900]] */
  def J1900(implicit universe: Universe) = universe.injector.getInstance(Key.get(classOf[Epoch], classOf[J1900]))

  /** See [[be.angelcorp.celest.time.EpochAnnotations.B1950]] */
  def B1950(implicit universe: Universe) = universe.injector.getInstance(Key.get(classOf[Epoch], classOf[B1950]))

  /** See [[be.angelcorp.celest.time.EpochAnnotations.TAI_EPOCH]] */
  def TAI_EPOCH(implicit universe: Universe) = universe.injector.getInstance(Key.get(classOf[Epoch], classOf[TAI_EPOCH]))

  /** See [[be.angelcorp.celest.time.EpochAnnotations.TT_EPOCH]] */
  def TT_EPOCH(implicit universe: Universe) = universe.injector.getInstance(Key.get(classOf[Epoch], classOf[TT_EPOCH]))

  /** See [[be.angelcorp.celest.time.EpochAnnotations.TCG_EPOCH]] */
  def TCG_EPOCH(implicit universe: Universe) = universe.injector.getInstance(Key.get(classOf[Epoch], classOf[TCG_EPOCH]))

  /** See [[be.angelcorp.celest.time.EpochAnnotations.TCB_EPOCH]] */
  def TCB_EPOCH(implicit universe: Universe) = universe.injector.getInstance(Key.get(classOf[Epoch], classOf[TCB_EPOCH]))

  /** See [[be.angelcorp.celest.time.EpochAnnotations.TDB_EPOCH]] */
  def TDB_EPOCH(implicit universe: Universe) = universe.injector.getInstance(Key.get(classOf[Epoch], classOf[TDB_EPOCH]))

}

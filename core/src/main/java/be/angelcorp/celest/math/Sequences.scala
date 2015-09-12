package be.angelcorp.celest.math

object Sequences {

  /**
   * Creates an infinite sequence that repeatedly applies a given function to the previous result.
   *
   *  @param start the start value of the iterator
   *  @param f     the function that's repeatedly applied
   *  @return      the iterator producing the infinite sequence of values `start, f(start), f(f(start)), ...`
   */
  def sequence[T](start: T)(f: T => T) = Iterator.iterate(start)(f)

}

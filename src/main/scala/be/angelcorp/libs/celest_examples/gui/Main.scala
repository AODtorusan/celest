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
package be.angelcorp.libs.celest_examples.gui

import scala.collection.JavaConverters._
import org.slf4j.LoggerFactory
import org.reflections.Reflections
import be.angelcorp.libs.util.gui.config.Config
import be.angelcorp.libs.util.gui.objectGuiSerializer.ObjectGuiSerializer
import be.angelcorp.libs.util.gui.objectGuiSerializer.selectableList.SelectableListSerializer

object Main extends App {
  val logger	= LoggerFactory.getLogger( "Main" )

	val examplesPackage	= "be.angelcorp.libs.celest_examples"

	val config = new Config()
  config.addSettings(new Settings())
  Config.setInstance(config)

  ObjectGuiSerializer.getInstance().addGuiSerializer(new SelectableListSerializer())

  val examples = discoverExamples()
  new SimpleGui(examples)

  def discoverExamples() = {
		val reflections = new Reflections(examplesPackage)
		val raw_examples = reflections.getTypesAnnotatedWith(classOf[CelestExample], true).asScala

		val examples = raw_examples.map( e => new Example(e) )
    examples.foreach( e => logger.debug("Found CelestExample class: {}", e) )

		examples.toSeq.sortBy( _.toString )
	}

}

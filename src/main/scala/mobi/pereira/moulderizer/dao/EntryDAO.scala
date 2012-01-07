package mobi.pereira.moulderizer.dao

import mobi.pereira.moulderizer.data.Entry

/**
 * @author Lucien Pereira
 */
object EntryDAO {
  val entries: Traversable[Entry] = Entry(0, "Implement your own Scala collection") :: Entry(1, "What's next : \"Akka: Simpler Scalability, Fault-Tolerance, Concurrency & Remoting through Actors\" par Jonas Bonér") :: Nil

}
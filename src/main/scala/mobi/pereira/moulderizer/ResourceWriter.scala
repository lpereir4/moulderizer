package mobi.pereira.moulderizer

import unfiltered.response.ResponseWriter
import java.io.{InputStreamReader, BufferedReader, Writer}

/**
 * @author Lucien Pereira
 */
class ResourceWriter(resourcePath: String, bufferSize: Int = 1024, encoding: String = "utf-8") extends ResponseWriter {

  def write(writer: Writer) {
    val buffer = new Array[Char](bufferSize)
    val inputStream = getClass.getClassLoader.getResourceAsStream(resourcePath)
    val inputStreamReader = new InputStreamReader(inputStream, encoding)
    val bufferedReader = new BufferedReader(inputStreamReader)

    var nbRead = bufferedReader.read(buffer, 0, bufferSize)
    while (-1 != nbRead) {
      writer.write(buffer, 0, nbRead)
      nbRead = bufferedReader.read(buffer, 0, bufferSize)
    }

    bufferedReader.close()
    inputStreamReader.close()
    inputStream.close()
  }
}
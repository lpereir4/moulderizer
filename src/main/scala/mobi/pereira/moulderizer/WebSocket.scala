package mobi.pereira.moulderizer

import dao.EntryDAO
import data.Entry
import unfiltered.request._
import unfiltered.response._

import org.clapper.avsl.Logger
import moulder.Moulds._
import moulder.Values._
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import moulder.{Value, MoulderShop}
import java.io._

/**
 * @author Lucien Pereira
 *
 * val parameters: Seq[(String, Any)] = params.map(a => (a._1, a._2.fold("")(_ + _))).toSeq
 */
class WebSocket extends unfiltered.filter.Plan {
  val logger = Logger(classOf[WebSocket])

  def intent = {
    case req@GET(Path("/blog") & Params(params)) => renderTemplate(getClass.getClassLoader.getResourceAsStream("templates/index.xhtml"))
    case GET(Path(Seg("oauth2callback" :: Nil)) & Params(params)) => Ok ~> Html(<p>Hello
      {params}
    </p>)
  }

  def renderTemplate(template: InputStream): ResponseWriter = {
    try {
      val document: Document = Jsoup.parse(template, "utf-8", "")
      val s = MoulderShop()

      s.register("title", text("Lucien's web page"))
      s.register("h5#entriesTitle", text("Entrées"))

      val entriesList = sub()
      entriesList.register("a",
        attr(Value("href"), transform[Entry, String](elemData(), "/blog/" + _.id.toString))
          :: text(transform[Entry, String](elemData(), _.title))
          :: Nil)
      s.register("#entries li",
        repeat(Value(EntryDAO.entries.toList))
          :: entriesList
          :: Nil)

      val response = ResponseString(s.process(document).head._1.toString)
      template.close()
      response
    } catch {
      case a => {
        logger.error("error : ", a)
        ResponseString("InternalServerError")
      }
    }
  }
}

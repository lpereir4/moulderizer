package mobi.pereira.moulderizer

import dao.EntryDAO
import data.Entry
import unfiltered.request._
import unfiltered.response._

import org.clapper.avsl.Logger
import moulder.Moulds._
import moulder.Values._
import moulder.MoulderShop
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
      val s = MoulderShop()

      s.register("title", text("Lucien's web page"))
      s.register("h5#entriesTitle", text("Entrées"))
      s.register("#entries li",
        repeat(EntryDAO.entries.toList, (item: Entry, index: Int) => {
          sub().register("a",
            attr("href", "/blog/" + item.id.toString) :: text(item.title) :: Nil) :: Nil
        }) :: Nil)

      val doc = s.process(template)
      ResponseString(doc.html())
    } catch {
      case a => {
        logger.error("error : ", a)
        ResponseString("InternalServerError")
      }
    }
  }

}

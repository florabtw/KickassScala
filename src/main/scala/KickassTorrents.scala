import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._

class KickassTorrents {

  def search(query: String) :List[Torrent] = {
    val browser = JsoupBrowser()
    val search = browser.get(s"https://kat.cr/usearch/$query")

    val torrents = search >> elementList("tr[id^=torrent_]")

    torrents.map { element =>
      new Torrent()
    }
  }
}

object KickassTorrents {
  def apply(): KickassTorrents = {
    new KickassTorrents
  }
}

object KAT {
  def apply(): KickassTorrents = {
    new KickassTorrents
  }
}
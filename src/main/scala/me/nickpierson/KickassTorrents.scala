package me.nickpierson

import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}
import com.netaporter.uri.dsl._

private[nickpierson] object Field extends Enumeration {
  type Field = Field.Value

  val SIZE = Value("size")
  val SEEDERS = Value("seeders")
  val FILES = Value("files_count")
  val AGE = Value("time_add")
  val LEECHERS = Value("leechers")
}

class KickassTorrents private[nickpierson] (browser: Browser = JsoupBrowser()) {
  import Field._

  def search(query: String, page: Int = 1, field: Field = SEEDERS): List[Torrent] = {
    val url = "https://kat.cr/usearch" / query / page.toString ? ("field" -> field.toString)

    val search = browser.get(url)

    Parser.parseTorrents(search)
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
package me.nickpierson

import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}
import com.netaporter.uri.dsl._

object Field extends Enumeration {
  type Field = Field.Value

  val SIZE = Value("size")
  val SEEDERS = Value("seeders")
  val FILES = Value("files_count")
  val AGE = Value("time_add")
  val LEECHERS = Value("leechers")
}

object Order extends Enumeration {
  type Order = Order.Value

  val DESC = Value("desc")
  val ASC = Value("asc")
}

class KickassTorrents private[nickpierson] (browser: Browser = JsoupBrowser()) {
  import Field._
  import Order._

  def search(query: String, page: Int = 1, field: Field = SEEDERS, order: Order = DESC): List[Torrent] = {
    val url = "https://kat.cr/usearch" / query / page.toString ? ("field" -> field) & ("sorder" -> order)

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
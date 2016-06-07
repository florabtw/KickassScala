package me.nickpierson

import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._

class KickassTorrents private[nickpierson] (browser: Browser = JsoupBrowser()) {

  def search(query: String) :List[Torrent] = {
    val search = browser.get(s"https://kat.cr/usearch/$query")

    val torrents = search >> elementList("tr[id^=torrent_]")

    torrents.map { element =>
      new Torrent(element >> text("a.cellMainLink"), element >> text("td:nth-child(2)"))
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
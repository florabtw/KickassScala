package me.nickpierson

import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}

class KickassTorrents private[nickpierson] (browser: Browser = JsoupBrowser()) {

  def search(query: String) :List[Torrent] = {
    val search = browser.get(s"https://kat.cr/usearch/$query")

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
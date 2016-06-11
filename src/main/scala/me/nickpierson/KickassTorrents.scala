package me.nickpierson

import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}

class KickassTorrents private[nickpierson] (browser: Browser = JsoupBrowser()) {

  def search(query: String, page: Int = 1): List[Torrent] = {
    val pageParam = if (page == 1) "" else s"$page/"
    val search = browser.get(s"https://kat.cr/usearch/$query/$pageParam")

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
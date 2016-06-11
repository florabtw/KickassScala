package me.nickpierson

import java.io.File
import java.time.{Instant, LocalDate, Month}

import net.ruippeixotog.scalascraper.browser.{Browser, JsoupBrowser}
import org.scalamock.scalatest.MockFactory
import org.scalatest._

class KickassTorrentsSpec extends FlatSpec with Matchers with MockFactory {

  val mockBrowser = mock[Browser]

  val realBrowser = JsoupBrowser()
  val searchFile = new File(getClass.getResource("/search.html").toURI)
  val searchResult = realBrowser.parseFile(searchFile)

  def elementaryOSTorrents = {
    val kat = new KickassTorrents(mockBrowser)
    (mockBrowser.get _).expects("https://kat.cr/usearch/elementary os").returning(searchResult)

    kat.search("elementary os")
  }

  "A KAT" should "be initializable" in {
    val kat = KickassTorrents()

    kat should be (an[KickassTorrents])
  }

  it should "have a short name" in {
    val kat = KAT()

    kat should be (an[KickassTorrents])
  }

  "A Search" should "return a list of torrents" in {
    val kat = new KickassTorrents(mockBrowser)
    (mockBrowser.get _).expects("https://kat.cr/usearch/elementary os").returning(searchResult)

    val torrents = kat.search("elementary os")

    torrents.length should be (3)
  }

  "A Torrent" should "have a name" in {
    val torrents = elementaryOSTorrents

    torrents.head.name should be ("Elementary OS Freya 64 Bit")
    torrents.last.name should be ("Elementary OS 0.3.1 64-bit")
  }

  it should "have a size" in {
    val torrents = elementaryOSTorrents

    torrents.head.size should be ("852 MB")
    torrents.last.size should be ("867 MB")
  }

  it should "have a number of files" in {
    val torrents = elementaryOSTorrents

    torrents.head.files should be (1)
    torrents.last.files should be (2)
  }

  it should "have an uploaded time" in {
    val torrents = elementaryOSTorrents

    torrents.head.uploaded should be (LocalDate.of(2015, Month.APRIL, 11))
    torrents.last.uploaded should be (LocalDate.of(2015, Month.NOVEMBER, 22))
  }

  it should "have a number of seeders" in {
    val torrents = elementaryOSTorrents

    torrents.head.seeders should be (5)
    torrents.last.seeders should be (0)
  }

  it should "have a number of leechers" in {
    val torrents = elementaryOSTorrents

    torrents.head.leechers should be (0)
    torrents.last.leechers should be (0)
  }

  it should "have an optional uploader" in {
    val torrents = elementaryOSTorrents

    torrents.head.uploader should be (Some("realmania"))
    torrents(1).uploader should be (None)
    torrents.last.uploader should be (Some("Router1011"))
  }

  it should "have a number of comments" in {
    val torrents = elementaryOSTorrents

    torrents.head.numberOfComments should be (117)
    torrents(1).numberOfComments should be (0)
    torrents.last.numberOfComments should be (1)
  }

  it should "have a verified flag" in {
    val torrents = elementaryOSTorrents

    torrents.head.isVerified should be (true)
    torrents.last.isVerified should be (false)
  }

  it should "have a magnet link" in {
    val torrents = elementaryOSTorrents

    torrents.head.magnetLink should be ("magnet:?xt=urn:btih:12345")
    torrents.last.magnetLink should be ("magnet:?xt=urn:btih:56789")
  }

  it should "have a download link" in {
    val torrents = elementaryOSTorrents

    torrents.head.downloadLink should be ("https://torcache.net/torrent/12345.torrent")
    torrents.last.downloadLink should be ("https://torcache.net/torrent/56789.torrent")
  }
}

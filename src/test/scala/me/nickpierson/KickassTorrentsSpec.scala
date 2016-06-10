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

  def gameOfThronesTorrents = {
    val kat = new KickassTorrents(mockBrowser)
    (mockBrowser.get _).expects("https://kat.cr/usearch/game of thrones").returning(searchResult)

    kat.search("game of thrones")
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
    (mockBrowser.get _).expects("https://kat.cr/usearch/game of thrones").returning(searchResult)

    val torrents = kat.search("game of thrones")

    torrents.length should be (25)
  }

  "A Torrent" should "have a name" in {
    val torrents = gameOfThronesTorrents

    torrents.head.name should be ("Game of Thrones S06E06 SUBFRENCH HDTV XviD-ZT avi")
    torrents.last.name should be ("Game of Thrones Season 2 [720p] x265")
  }

  it should "have a size" in {
    val torrents = gameOfThronesTorrents

    torrents.head.size should be ("541.41 MB")
    torrents.last.size should be ("1.78 GB")
  }

  it should "have a number of files" in {
    val torrents = gameOfThronesTorrents

    torrents.head.files should be (1)
    torrents.last.files should be (12)
  }

  it should "have an uploaded time" in {
    val torrents = gameOfThronesTorrents

    torrents.head.uploaded should be (LocalDate.of(2016, Month.MAY, 30))
    torrents.last.uploaded should be (LocalDate.of(2015, Month.AUGUST, 12))
  }

  it should "have a number of seeders" in {
    val torrents = gameOfThronesTorrents

    torrents.head.seeders should be (13578)
    torrents.last.seeders should be (584)
  }

  it should "have a number of leechers" in {
    val torrents = gameOfThronesTorrents

    torrents.head.leechers should be (885)
    torrents.last.leechers should be (137)
  }

  it should "have an uploader" in {
    val torrents = gameOfThronesTorrents

    torrents.head.uploader should be ("wassup_bro")
    torrents.last.uploader should be ("lucifer22")
  }

  it should "have a verified flag" in {
    val torrents = gameOfThronesTorrents

    torrents.head.isVerified should be (true)
    torrents.last.isVerified should be (false)
  }
}

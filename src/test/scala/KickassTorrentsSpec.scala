import org.scalatest._

class KickassTorrentsSpec extends FlatSpec with Matchers {

  "A KAT" should "be initializable" in {
    val kat = KickassTorrents()

    kat should be (an[KickassTorrents])
  }

  "A KAT" should "have a short name" in {
    val kat = KAT()

    kat should be (an[KickassTorrents])
  }
}

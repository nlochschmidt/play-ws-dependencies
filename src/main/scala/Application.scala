import com.ning.http.client.AsyncHttpClientConfig
import play.api.libs.ws.WSResponse
import play.api.libs.ws.ning.NingWSClient

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object PlayWSWithoutPlayExample extends App {

  val timeout = 60000 * 10

  val builder = new AsyncHttpClientConfig.Builder()
  val client = new NingWSClient(builder.setConnectionTimeoutInMs(timeout).setRequestTimeoutInMs(timeout)
    .setIdleConnectionTimeoutInMs(timeout).build())

  val responseFuture: Future[WSResponse] =
    client.url("https://twitter.com/bjburton/status/586574945435701248").get()

  val result = Await.result(responseFuture, Duration.Inf)

  client.close()

  println(result.body)
}

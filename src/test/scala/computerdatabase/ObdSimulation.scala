package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class ObdSimulation extends Simulation {

  val httpProtocol = http
    .baseUrl("http://localhost:8001")
    .inferHtmlResources(BlackList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*detectportal\.firefox\.com.*"""), WhiteList())
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate")
    .contentTypeHeader("application/json")
    .userAgentHeader("PostmanRuntime/7.26.10")

  val headers_0 = Map(
    "Cache-Control" -> "no-cache",
    "Postman-Token" -> "2d8a65c8-4962-4de2-b369-359be977283e")

  val headers_1 = Map(
    "Cache-Control" -> "no-cache",
    "Postman-Token" -> "f330b634-f263-4ccd-b2b2-925cb4e4a287")


  val scn = scenario("ObdSimulation")
    // Start Trip
    .exec(http("request_0")
      .post("/api/v1/vehicle/0f607264fc6318a92b9e13c65db7cd3c/session/1595523124347")
      .headers(headers_0)
      .body(RawFileBody("computerdatabase/obdsimulation/0000_request.json")))
    .repeat(5, "n") {
      exec(http("request_1")
        .post("/api/v1/vehicle/0f607264fc6318a92b9e13c65db7cd3c/session/1595523124347/route")
        .headers(headers_1)
        .body(RawFileBody("computerdatabase/obdsimulation/0001_request.json")))
    }


  // Route Added

  setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
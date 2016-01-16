package pl.immutables.http.stream

import org.http4s._
import org.http4s.dsl._
import org.http4s.server._
import org.http4s.client._

import org.http4s.server.blaze.BlazeBuilder
import org.http4s.client.blaze.{defaultClient => client}
import scodec.bits.ByteVector

import scalaz.stream.io
import scalaz.stream.Process
import scalaz.concurrent.Task

trait Streaming {
  val bufferSize = 128

  def read(path: String): Process[Task, ByteVector] = Process
    .constant(bufferSize).toSource
    .through(io.fileChunkR(path, bufferSize))

  def write(path: String, data: EntityBody) =
    (data to io.fileChunkW(path)).run
}

object StreamingServer extends App with Streaming {
  val service = HttpService {
    case GET -> Root / "download" =>
      Ok(read("avatar.png"))
    case req @ POST -> Root / "upload" =>
      write("uploaded", req.body).flatMap(_ => Created())
  }

  BlazeBuilder.bindHttp(8080)
    .mountService(service, "/")
    .run
    .awaitShutdown()
}

object StreamingClient extends App {
  client
    .prepare(GET(uri("http://localhost:8080/download")))
    .flatMap(response => client.prepare(POST(uri("http://localhost:8080/upload"), response.body)))
    .run
}

# data-streaming-example

Example of data streaming over http using http4s and scalaz-streams.

Run
```
sbt "run-main pl.immutables.http.stream.StreamingServer"
```
to start a server.

Then, run 
```
sbt "run-main pl.immutables.http.stream.StreamingClient"
```
to execute a client.

A client downloads data from a server and uploads it back.
A server reads and writes data using streams.

More on http://immutables.pl/2016/01/16/Streaming-data-using-http4s-and-scalaz-stream/

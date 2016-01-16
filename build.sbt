lazy val root = (project in file(".")).
  settings(
    name := "data-streaming",
    version := "0.1",
    scalaVersion := "2.11.7"
  )

val http4sVersion = "0.11.3"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
)

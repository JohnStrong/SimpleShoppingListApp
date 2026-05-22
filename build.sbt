ThisBuild / scalaVersion := "3.3.7"   // use a recent stable Scala 3 version
ThisBuild / version := "0.1.0"
ThisBuild / organization := "org.myapps.shoppinglistservice"

lazy val root = (project in file("."))
  .settings(
    name := "SimpleShoppingListApp",
    libraryDependencies ++= Seq(
      "org.springframework.boot" % "spring-boot-starter-data-jpa" % "3.3.7",
      "org.springframework.boot" % "spring-boot-starter-web" % "3.3.7",

      "org.scalatest" %% "scalatest" % "3.2.20" % Test,
      "org.mockito" % "mockito-core" % "5.22.0" % Test // Why? `mockito-scala` only works up to Scala 2.X currently sadly :(
    )
  )
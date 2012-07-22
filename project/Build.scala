import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "pinoyhubber"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "com.github.twitter" % "bootstrap" % "2.0.2",
      "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
      "commons-validator" % "commons-validator" % "1.3.1"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      resolvers += "webjars" at "http://webjars.github.com/m2"      
    )

}

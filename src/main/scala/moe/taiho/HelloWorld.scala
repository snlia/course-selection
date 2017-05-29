package moe.taiho

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

import akka.actor.{ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import moe.taiho.actors.HelloWorldActor

/**
  * Created by swordfeng on 17-5-29.
  */
object HelloWorld extends App {
    val system = ActorSystem("hello-world")
    val helloWorld = system.actorOf(Props[HelloWorldActor], "actor")

    implicit val timeout = Timeout(5.second)

    (helloWorld ? "hello") onSuccess {
        case "world!" => println("hello world!")
    }

    system.stop(helloWorld)
    system.terminate()
}
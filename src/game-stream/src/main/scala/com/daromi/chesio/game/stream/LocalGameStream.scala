package com.daromi.chesio.game.stream

import java.io.PrintStream
import java.net.ServerSocket
import scala.util.{Failure, Success, Using}

final class LocalGameStream(
    private val port: Int = 9999,
    private val rate: Int = 100 // milliseconds
):
  private var running: Boolean = false

  def start(): Unit =
    println(s"running on ${Thread.currentThread()}")
    println(s"starting server at port $port...")

    Using.Manager { use =>
      val server = use(ServerSocket(port))
      println(s"server started")

      val client = use(server.accept())
      println("client connected")

      val out = use(PrintStream(client.getOutputStream, true))

      running = true

      while running do
        sendData(out, "e4")
        Thread.sleep(rate)
    } match
      case Success(_) => println("server stopped")
      case Failure(e) => println(s"${e.getMessage}")

  private def sendData(out: PrintStream, data: String): Unit =
    println(s"sending data: $data")
    out.println(data)

  def stop(): Unit =
    running = false
    println("stopping server...")

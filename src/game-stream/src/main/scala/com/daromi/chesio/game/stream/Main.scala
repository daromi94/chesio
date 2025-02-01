package com.daromi.chesio.game.stream

@main def main(args: String*): Unit =
  val stream = LocalGameStream()
  stream.start()

import org.scalajs.dom
import dom.document
import org.querki.jquery._

import scala.scalajs.js

object TimerApp {
  def main(args: Array[String]): Unit = {
    $(() => setupUI())
  }

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }

  def addClickedMessage(): Unit = {
    appendPar(document.body, "You clicked the button!")
    playBeep()
  }

  def setupUI(): Unit = {
    $("body").append("<p>Hello Scala JS yay!!!/p>")
    $("#click-me-button").click(() => addClickedMessage())
  }

  def playBeep(): Unit = {
    val doc = js.Dynamic.global.document
    val sound = doc.getElementById("sound1")
    sound.play()

//    val sound = document.getElementById("sound1")
//    sound.Play()
  }

  def beep(duration: Int, frequency: Int, volume: Int, t: String): Unit = {
    val audioCtx = AudioContext()
    var oscillator = audioCtx.createOscillator();
    var gainNode = audioCtx.createGain();

    oscillator.connect(gainNode);
    gainNode.connect(audioCtx.destination);

    if (volume){gainNode.gain.value = volume;};
    if (frequency){oscillator.frequency.value = frequency;}
    if (type){oscillator.type = type;}
    if (callback){oscillator.onended = callback;}

    oscillator.start();
    setTimeout(function(){oscillator.stop()}, (duration ? duration : 500));

  }
}
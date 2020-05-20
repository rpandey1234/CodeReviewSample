package edu.stanford.rkpandey.codereviewsample

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

  private lateinit var buttons: List<Button>

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    buttons = listOf(btnOption1, btnOption2, btnOption3)
    reset()
  }

  private fun reset() {
    textview.text = "Choose your favorite place"
    btnOption1.text = "Park"
    btnOption1.setOnClickListener { view ->
      Log.i(TAG, "Click listener for park")
      onParkClick()
    }

    btnOption2.text = "Home"
    btnOption2.setOnClickListener {
      Log.i(TAG, "Click listener for home")
      onHomeClick()
    }

    btnOption3.text = "Restaurant"
    btnOption3.setTextColor(Color.BLACK)
    btnOption3.setOnClickListener {
      Log.i(TAG, "Click listener for restaurant")
      onRestaurantClick()
    }
  }

  // Present options for various parks close to the user
  private fun onParkClick() {
    textview.text = "Favorite park?"
    val parks = mutableListOf<String>("Roosevelt Park", "Sand Hill", "Crocker Field")
    buttons.forEachIndexed { index, button ->
      button.text = parks[index]
      button.setOnClickListener { reset() }
    }
  }

  private fun onHomeClick() {
    textview.text = "Favorite room at home?"
    val rooms = listOf("Living Room", "Kitchen", "Bedroom")
    buttons.forEachIndexed { index, button ->
      button.text = rooms[index]
      button.setOnClickListener { reset() }
    }
  }

  private fun onRestaurantClick() {
    textview.text = "Favorite restaurant?"
    val restaurants = listOf("Chipotle", "Olive Garden", "Shizen")
    buttons.forEachIndexed { index, button ->
      button.text = restaurants[index]
      button.setOnClickListener {
        // Check if the user picked the correct restaurant
        if (check(button.text.toString(), "Shizen")) {
          Toast.makeText(this, "You won!!", Toast.LENGTH_SHORT).show()
        } else {
          Toast.makeText(this, "You lost!", Toast.LENGTH_SHORT).show()
        }
        reset()
      }
    }
    // Get the last button
    val lastButton = buttons.get(buttons.size - 1)
    lastButton.setTextColor(Color.BLUE)
  }
}

private fun check(chosenAnswer: String, expectedAnswer: String): Boolean {
  if (chosenAnswer == expectedAnswer) {
    return true
  } else {
    return false
  }
}

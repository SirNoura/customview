package nunofernandes.example.customviewteste

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    //val touchScreenView: TouchScreenView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewValue = findViewById<TextView>(R.id.textViewContador)
        val touchScreen = findViewById<TouchScreenView>(R.id.touchScreenView)
        touchScreen.setOnValueChange = {
            textViewValue.setText(it.toInt().toString())
        }

        val textViewValue2 = findViewById<TextView>(R.id.textViewContador2)
        val touchScreen2 = findViewById<TouchScreenView>(R.id.touchScreenView2)
        touchScreen2.setOnValueChange = {
            textViewValue2.setText(it.toInt().toString())
        }

        //touchScreenView?.roundMapMarkerUserPosition()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.share,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_share){
            val share = Intent(Intent.ACTION_SEND)
            share.type = "image/png"
            startActivity(Intent.createChooser(share, "BITMAP"))
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}
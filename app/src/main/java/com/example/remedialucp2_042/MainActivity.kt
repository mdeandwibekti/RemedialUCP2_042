package com.example.remedialucp2_042

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.remedialucp2_042.tampilan.view.KategoriFragment
import com.example.remedialucp2_042.ui.theme.RemedialUCP2_042Theme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootContainer = FrameLayout(this).apply {
            id = View.generateViewId()
        }
        setContentView(rootContainer)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(rootContainer.id, KategoriFragment())
                .commit()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RemedialUCP2_042Theme {
        Greeting("Android")
    }
}
package tw.edu.pu.s1114859.intent

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import tw.edu.pu.s1114859.intent.ui.theme.IntentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntentTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                   // Greeting("Android")
                    FirstScreen()
                }
            }
        }
    }
}

@Composable
fun FirstScreen(){
    val context = LocalContext.current
    var url by remember { mutableStateOf("https://www.pu.edu.tw") }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Cyan),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        TextField(
            value = url,
            onValueChange = { newText ->
                url = newText
            },
            label = { Text(text = "網址") },
            placeholder = { Text(text = "請輸入您要瀏覽的網址") }
        )

        Button(onClick = {
            var it = Intent(context, SecondActivity::class.java)
            it.putExtra("website", url)

            context.startActivity(it)
        })
        {
            Text(text = "跳轉到SecondActivity")
        }
        Button(
            onClick = {
                var it = Intent(Intent.ACTION_WEB_SEARCH)
                it.putExtra(SearchManager.QUERY, "靜宜資管")
                context.startActivity(it)
            }
        )
        {
            Text(text = "搜尋關鍵字")
        }

    }
}

package tw.edu.pu.s1114859.intent

import android.app.Activity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import tw.edu.pu.s1114859.intent.ui.theme.IntentTheme

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current  //取得App的運行環境
    val activity = (context as Activity)  //取得App運行的活動
    var url:String? = context.intent.getStringExtra("website")


    Column {
        Text(
            text = "SecondAcyivity\n網址為：" + url!!

        )
        Button(
            onClick = {
                activity.finish()
            })
        {
            Text(text = "回到MainActivity")
        }
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()

                    settings.useWideViewPort = true
                    settings.setSupportZoom(true)
                }
            },
            update = { webView ->
                webView.loadUrl(url!!)
            }
        )


    }

}

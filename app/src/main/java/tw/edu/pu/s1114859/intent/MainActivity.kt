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
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import tw.edu.pu.s1114859.intent.ui.theme.IntentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntentTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                   // Greeting("Android")
                    //FirstScreen()
                    //SecondScreen()
                    Main()

                }
            }
        }
    }
}

@Composable
fun FirstScreen(navController: NavController){
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
                var it = Intent(Intent.ACTION_VIEW)
                it.data = Uri.parse(url)
                context.startActivity(it)
            }
        )
        {
            Text(text = "開啟瀏覽器")
        }
        Button(
            onClick = {
                var it = Intent(Intent.ACTION_SENDTO)
                it.data = Uri.parse("mailto:tcyang@gm.pu.edu.tw")
                context.startActivity(it)
            }
        )
        {
            Text(text = "寄發電子郵件")
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
        Button(
            onClick = {
                var it = Intent(Intent.ACTION_VIEW)
                //it.data = Uri.parse("geo:24.2267756,120.5771591")
                //it.data = Uri.parse("geo:24.2267756,120.5771591?q=restaurants\n")
                //it.data = Uri.parse("geo:0,0?q=台中市沙鹿區台灣大道七段200號\n")
                //it.data = Uri.parse("geo:0,0?q=高美濕地\n")
                it.data = Uri.parse("google.navigation:q=靜宜大學\n")
                context.startActivity(it)
            }
        )
        {
            Text(text = "Google Map查詢")
        }
        Button(onClick = {
            navController.navigate("JumpSecond")
        }) {
            Text(text = "我是畫面1，按一下跳至畫面2")
        }

    }
}
@Composable
fun SecondScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            navController.navigate("JumpFirst")
        }) {
            Text(text = "我是畫面2，按一下跳至畫面1")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "JumpFirst"){
        composable("JumpFirst"){
            FirstScreen(navController = navController)
        }
        composable("JumpSecond"){
            SecondScreen(navController = navController)
        }
    }
}

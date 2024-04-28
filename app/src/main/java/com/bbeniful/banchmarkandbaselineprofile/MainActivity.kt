package com.bbeniful.banchmarkandbaselineprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.bbeniful.banchmarkandbaselineprofile.ui.theme.BanchmarkAndBaselineProfileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BanchmarkAndBaselineProfileTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            List(data = getData()) { data, index ->
                navController.navigate("details/$data/$index")
            }
        }
        composable("details/{data}/{index}", arguments = listOf(navArgument("data") {
            type = NavType.StringType
        }, navArgument("index") {
            type = NavType.IntType
        })) {
            val data = it.arguments?.getString("data")
            val index = it.arguments?.getInt("index")
            Box(Modifier.fillMaxWidth()) {
                Text(text = "$data at $index")
            }
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun List(data: List<String>, onClick: (String, Int) -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Red)
            .semantics {
                testTagsAsResourceId = true
            }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(22.dp),
            modifier = Modifier.testTag("item_list")
        ) {
            itemsIndexed(data) { index, item ->
                ItemView(data = item, index, onClick)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ItemView(data: String, index: Int, onClick: (String, Int) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .height(35.dp)
            .padding(horizontal = 12.dp)
            .background(Color.Blue)
            .semantics {
                testTagsAsResourceId = true
            }
            .testTag("text_box_$index")
            .clickable {
                onClick(data, index)
            }
    ) {
        Text(
            text = "$data $index",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

fun getData() = listOf(
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
    "appletree",
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BanchmarkAndBaselineProfileTheme {
        Greeting("Android")
    }
}
package com.tupleinfotech.jetpackdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tupleinfotech.jetpackdemo.ui.theme.JetpackdemoTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PreviewConversation()
        }
    }
}


@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

@Composable
fun MessageCard(msg : Message){
    Row (
        modifier = Modifier.padding(start = 10.dp, top = 10.dp, end = 10.dp, bottom = 5.dp)){
        Image(
            painter = painterResource(R.drawable.profile_image),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.onPrimary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }

        val surfaceColor by animateColorAsState(
            if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
        )

        Column(
            modifier = Modifier
                .padding(start = 5.dp, top = 20.dp)
                .clickable { isExpanded = !isExpanded }) {

            Surface(shape = MaterialTheme.shapes.medium, color = MaterialTheme.colorScheme.onBackground, shadowElevation = 10.dp) {
                Text(
                    text = msg.author,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.secondaryContainer
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Surface(shape = MaterialTheme.shapes.extraSmall,
                color = surfaceColor,
                shadowElevation = 10.dp) {
                Text(text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    JetpackdemoTheme {
        Conversation(SampleData.conversationSample)
    }
}

/*@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard(){
    JetpackdemoTheme {
        Surface {
            MessageCard(Message("Android", "Jetpack Compose"))
        }
    }
}*/
/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )

}
data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: com.tupleinfotech.jetpackdemo.Message){
    Column {
        Text(text = msg.author)
        Text(text = msg.body)
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackdemoTheme {
        Greeting("Android")
    }
    MessageCard(
        msg = Message("","")
    )
}*/

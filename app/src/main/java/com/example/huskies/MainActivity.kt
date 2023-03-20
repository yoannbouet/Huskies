package com.example.huskies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.huskies.model.Husky
import com.example.huskies.model.HuskyDataSource
import com.example.huskies.ui.theme.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HuskiesTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    HuskiesApp()
                }
            }
        }
    }
}

@Composable
fun HuskiesApp() {
    HuskiesTheme {
        Scaffold(
            modifier = Modifier,
            topBar = {
                TopAppBar()
            }
        ) {
            it.calculateTopPadding()
            LazyColumn {
                items(HuskyDataSource.huskies) { husky ->
                    HuskyItem(husky)
                }
            }
        }
    }
}

@Composable
fun ClickableCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        content = {
            Box(content = { content() })
        }
    )
}

@Composable
fun HuskyItem(husky: Husky) {
    var expanded by remember { mutableStateOf(false) }
        ClickableCard(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .animateContentSize(),
            onClick = { expanded = !expanded }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.animateContentSize()
            ) {
                Image(
                    painter = painterResource(husky.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.TopCenter
                )
                Spacer(Modifier.height(6.dp))
                Row(
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 6.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Spacer(modifier = Modifier
                        .width(25.dp)
                        .height(25.dp)
                    )
                    Text(
                        text = stringResource(husky.nameRes),
                        style = MaterialTheme.typography.h3,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier
                        .width(25.dp)
                        .height(25.dp)
                        .padding(end = 6.dp)
                        .drawBehind {
                            drawCircle(husky.eyeColor)
                        }
                    )
                }
                if (expanded) {
                    Text(
                        text = stringResource(husky.descriptionRes),
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 6.dp),
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                }
                if (husky.nameRes02 != 0) {
                    Row(
                        modifier = Modifier
                            .padding(top = 4.dp, bottom = 6.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Spacer(modifier = Modifier
                            .width(25.dp)
                            .height(25.dp)
                        )
                        Text(
                            text = stringResource(husky.nameRes02),
                            style = MaterialTheme.typography.h3,
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier
                            .width(25.dp)
                            .height(25.dp)
                            .padding(end = 6.dp)
                            .drawBehind {
                                drawCircle(husky.eyeColor02)
                            }
                        )
                    }
                    if (expanded) {
                        Text(
                            text = stringResource(husky.descriptionRes02),
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 6.dp),
                            textAlign = TextAlign.Center,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }

@Composable
fun TopAppBar(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, top = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.huskies),
                style = MaterialTheme.typography.h1,
            )
            Text(
                text = stringResource(R.string.eye_color),
                style = MaterialTheme.typography.h3,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HuskiesTheme(darkTheme = false) {
        HuskiesApp()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewDark() {
    HuskiesTheme(darkTheme = true) {
        HuskiesApp()
    }
}
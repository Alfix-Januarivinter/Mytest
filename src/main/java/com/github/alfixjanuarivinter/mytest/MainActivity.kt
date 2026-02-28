/*
 * Mytest - Simple Android app using Jetpack Compose
 * Copyright (c) 2026 Alfix Januarivinter
 *
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 4.0 International License.
 * To view a copy of this license, visit:
 * https://creativecommons.org/licenses/by-sa/4.0/
 */

package com.github.alfixjanuarivinter.mytest

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.github.alfixjanuarivinter.mytest.ui.theme.MytestTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        enableEdgeToEdge()

        setContent {
            MytestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GreetingWithMenu()
                }
            }
        }
    }
}

@Composable
fun GreetingWithMenu() {
    var menuExpanded by remember { mutableStateOf(false) }
    val isDarkTheme = isSystemInDarkTheme()
    val menuBackground = if (isDarkTheme) Color(0xFF222222) else Color(0xFFEEEEEE)

    Box(modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                contentDescription = "App Icon",
                modifier = Modifier.size(96.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Hello User!",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Text(
            text = "v${BuildConfig.VERSION_NAME}",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(24.dp)
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(24.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(Color(0xFF555555), RoundedCornerShape(12.dp))
                    .clickable { menuExpanded = !menuExpanded },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            DropdownMenu(
                expanded = menuExpanded,
                onDismissRequest = { menuExpanded = false },
                offset = DpOffset(x = 0.dp, y = 8.dp),
                containerColor = menuBackground,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.width(280.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Mytest",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2196F3)
                    )
                    Text(
                        text = "V${BuildConfig.VERSION_NAME}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4CAF50)
                    )
                }
            }
        }
    }
}

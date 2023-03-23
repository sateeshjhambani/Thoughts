package com.sateeshjh.thoughts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sateeshjh.thoughts.ui.theme.ThoughtsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThoughtsAppTheme {
                
            }
        }
    }
}

package com.example.ucp2.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ucp2.R

@Composable
fun Home(
    onButtonClickDsn: () -> Unit,
    onButtonClickMK: () -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(26.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            // Card pertama
            Card(
                modifier = Modifier
                    .background(Color.Gray, RoundedCornerShape(16.dp))
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Dosen",
                        color = Color.Black,
                        fontSize = 60.sp
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
//                    Image(
//                        painter = painterResource(R.drawable.)
//                    )
                    Button(
                        onClick = onButtonClickDsn,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                    ) {
                        Text(text = "Klik Saya")
                    }
                }
            }
        Spacer(modifier = Modifier.padding(20.dp))

              Card(
                  modifier = Modifier
                      .background(Color.Gray, RoundedCornerShape(16.dp)),
              ) {
                  Column (
                      verticalArrangement = Arrangement.Center,
                      horizontalAlignment = Alignment.CenterHorizontally
                  ){
                      Text(
                          color = Color.Black,
                          text = "Mata Kuliah",
                          fontSize = 60.sp
                      )
                      Spacer(modifier = Modifier.padding(10.dp))
                      Button(
                          onClick = onButtonClickMK,
                          modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                      ) {
                          Text(text = "Klik Saya")
                      }
                  }
              }
         }
    }

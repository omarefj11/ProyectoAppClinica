package com.example.proyectoclinica

import android.content.Intent
import android.graphics.Paint.Style
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                /*Para que ocupe completamente el ancho*/
                /*unidad relativa pixeles*/
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                        .border(
                            width = 2.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(bottomEnd = 50.dp, bottomStart = 50.dp),
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.simbolo),
                        contentDescription = stringResource(id = R.string.logo),
                        modifier = Modifier
                            .offset(x = 100.dp, y = 40.dp)
                            .size(80.dp, 80.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.titulo_clinica),
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.offset(x = 180.dp, y = 64.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.portadaclinica),
                        contentDescription = stringResource(id = R.string.portada),
                        modifier = Modifier
                            .offset(x = 70.dp, y = 115.dp)
                            .size(270.dp, 270.dp),
                    )
                    Text(
                        text = stringResource(id = R.string.bienvenido),
                        style = TextStyle(
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier
                            .offset(x = 110.dp, y = 420.dp)
                    )
                }

            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {

                Button(
                    onClick = {
                        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.clinica)),
                    modifier = Modifier

                        .offset(y = -50.dp)
                ) {
                    Text(text = stringResource(id = R.string.login), color = Color.Black)
                }
                Text(text = "No tienes cuenta? " + stringResource(id = R.string.registrar),
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = Color.DarkGray
                    ),
                    modifier = Modifier
                        .offset(y = -20.dp)
                        .clickable {
                            mostrarRegistro(0)
                        }
                        .padding(bottom = 10.dp))
                TextButton(
                    onClick = {
                        startActivity(
                            Intent(
                                this@MainActivity,
                                LoginActivity::class.java
                            )
                        )
                    },
                    modifier = Modifier
                        .offset(x = -105.dp, y = 72.dp)
                        .width(211.dp)
                        .border(2.dp, Color.LightGray, RoundedCornerShape(topStart = 20.dp))
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.whastapp),
                        contentDescription = stringResource(
                            id = R.string.whatsapp
                        ),
                        modifier = Modifier
                            .size(40.dp)
                            .offset(x = -10.dp)
                    )
                    Text("Contactenos",color = Color.Black)
                }
                TextButton(
                    onClick = {

                    },
                    modifier = Modifier
                        .offset(x = 105.dp)
                        .width(211.dp)
                        .border(2.dp, Color.LightGray, RoundedCornerShape(topEnd = 20.dp))
                        .padding(8.dp)


                ) {
                    Image(
                        painter = painterResource(id = R.drawable.emergencyservices),
                        contentDescription = stringResource(
                            id = R.string.whatsapp
                        ),
                        modifier = Modifier
                            .size(40.dp)
                            .offset(x = -10.dp)
                    )
                    Text("Emergencia", color = Color.Red)
                }

            }


        }
    }

    private fun mostrarRegistro(it: Any) {
        when (it) {
            0 -> startActivity(Intent(this, RegistrarseActivity::class.java))
        }
    }
}
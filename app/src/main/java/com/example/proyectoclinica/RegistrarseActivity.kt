package com.example.proyectoclinica

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.proyectoclinica.ui.theme.ProyectoClinicaTheme
import com.example.proyectoclinica.utils.Total

class RegistrarseActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var textEmail by remember {
                mutableStateOf(TextFieldValue(""))
            }
            var textPassword by remember {
                mutableStateOf(TextFieldValue(""))
            }
            var textName by remember {
                mutableStateOf(TextFieldValue(""))
            }
            var textDni by remember {
                mutableStateOf(TextFieldValue(""))
            }

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
                        .height(140.dp)
                        .border(
                            width = 2.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(bottomEnd = 50.dp, bottomStart = 50.dp),
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.simbolo),
                        contentDescription = stringResource(id = R.string.logo),
                        modifier = Modifier.offset(x = 100.dp, y = 40.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.titulo_clinica),
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.offset(x = 180.dp, y = 64.dp)
                    )
                    Text(text = "N° de DNI", modifier = Modifier.offset(x = 70.dp, y = 200.dp))
                    TextField(
                        value = textDni,
                        onValueChange = { textDni = it },
                        label = { Text(stringResource(id = R.string.ingresar_dni)) },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        modifier = Modifier.offset(x = 70.dp, y = 230.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(24.dp)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedLabelColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedTextColor = Color.Black,
                            cursorColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        shape = RoundedCornerShape(24.dp),
                    )
                    Text(
                        text = "Nombre Completo",
                        modifier = Modifier.offset(x = 70.dp, y = 320.dp)
                    )
                    TextField(
                        value = textName,
                        onValueChange = { textName = it },
                        label = { Text(stringResource(id = R.string.ingresar_nombre)) },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        modifier = Modifier.offset(x = 70.dp, y = 350.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(24.dp)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedLabelColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedTextColor = Color.Black,
                            cursorColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        shape = RoundedCornerShape(24.dp),
                    )
                    Text(
                        text = "Correo Electrónico",
                        modifier = Modifier.offset(x = 70.dp, y = 430.dp)
                    )
                    TextField(
                        value = textEmail,
                        onValueChange = { textEmail = it },
                        label = { Text(stringResource(id = R.string.ingresar_correo)) },
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        modifier = Modifier
                            .offset(x = 70.dp, y = 460.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(24.dp)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedLabelColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedTextColor = Color.Black,
                            cursorColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        shape = RoundedCornerShape(24.dp),
                    )
                    Text(text = "Contraseña", modifier = Modifier.offset(x = 70.dp, y = 550.dp))
                    TextField(
                        value = textPassword, // Aquí puedes establecer el valor inicial del campo
                        onValueChange = { textPassword = it },
                        label = { Text(stringResource(id = R.string.ingresar_contraseña)) }, // Cambia el texto según tu necesidad
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done), // Esto es opcional, define qué acción del teclado aparecerá (puede ser "Done", "Next", etc.)
                        modifier = Modifier.offset(x = 70.dp, y = 580.dp)
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                shape = RoundedCornerShape(24.dp)
                            ),
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.White,
                            focusedLabelColor = Color.Black,
                            unfocusedTextColor = Color.Black,
                            focusedTextColor = Color.Black,
                            cursorColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        shape = RoundedCornerShape(24.dp),
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {

                val checkedState = remember { mutableStateOf(false) }
                Checkbox(
                    checked = checkedState.value,
                    modifier = Modifier
                        .offset(x = -120.dp, y = -27.dp),
                    onCheckedChange = { isChecked ->
                        checkedState.value = isChecked
                    }
                )

                Text(
                    text = "Acepto los términos y condiciones",
                    modifier = Modifier
                        .offset(x = 10.dp, y = -60.dp)
                        .clickable(onClick = {
                            startActivity(Intent(this@RegistrarseActivity, TerminosActivity2::class.java))
                        })
                )
                Button(
                    onClick = {
                        startActivity(Intent(this@RegistrarseActivity, LoginActivity::class.java))
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.clinica)),
                    modifier = Modifier
                        .offset(y = -20.dp)
                ) {
                    Text(text = stringResource(id = R.string.registrar), color = Color.Black)
                }
            }
        }

    }
}
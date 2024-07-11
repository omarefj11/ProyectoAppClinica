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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.proyectoclinica.ui.theme.ProyectoClinicaTheme
import com.example.proyectoclinica.utils.Total
import org.json.JSONArray

class LoginActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var textEmail by remember {mutableStateOf("")}
            var textPassword by remember {mutableStateOf("")}
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

                }

            }
            Text(text = "Correo electrónico", modifier = Modifier.offset(x = 70.dp, y = 270.dp),)
            TextField(
                value = textEmail,
                onValueChange = { textEmail = it },
                label = { Text(stringResource(id = R.string.ingresar_correo)) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                modifier = Modifier.offset(x = 70.dp, y = 300.dp)
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

            Text(text = "Contraseña", modifier = Modifier.offset(x = 70.dp, y = 370.dp))
            TextField(
                value = textPassword,
                onValueChange = { textPassword = it },
                label = { Text(stringResource(id = R.string.ingresar_contraseña)) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                modifier = Modifier.offset(x = 70.dp, y = 400.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(24.dp)
                    ),
                visualTransformation = PasswordVisualTransformation(),
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
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    onClick = {
                        leerServicioInicioSesion(textEmail,textPassword)//leerServicioInicioSesion(textEmail,textPassword)
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
                        .offset(y = -10.dp)
                        .clickable {
                            mostrarRegistro(0)
                        }
                        .padding(bottom = 10.dp))
                TextButton(
                    onClick = {
                        startActivity(
                            Intent(
                                this@LoginActivity,
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
                    Text("Contactenos", color = Color.Black)
                }
                TextButton(
                    onClick = {
                        startActivity(
                            Intent(
                                this@LoginActivity,
                                UserPrincipalActivity::class.java
                            )
                        )
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

    private fun leerServicioInicioSesion(usuario: String, contraseña: String) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = Total.rutaServices + "iniciarsesion.php"
        // Request a string response from the provided URL.
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                Log.d("DATOS", response)
                verificarInicioSesion(response)
            },
            {
            }) {
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params.put("dni", usuario)
                params.put("contraseña", contraseña)
                return params
            }
        }
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    private fun verificarInicioSesion(response: String?) {
        when (response) {
            "-1" -> Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show()
            "-2" -> Toast.makeText(this, "La contraseña es incorrecta", Toast.LENGTH_SHORT).show()
            else -> {
                Total.UsuarioActivo = JSONArray(response).getJSONObject(0)
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, UserPrincipalActivity::class.java))
            }
        }
    }

    private fun mostrarRegistro(it: Any) {
        when (it) {
            0 -> startActivity(Intent(this, RegistrarseActivity::class.java))
        }
    }
}

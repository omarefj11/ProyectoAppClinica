package com.example.proyectoclinica.Menu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.proyectoclinica.R
import com.example.proyectoclinica.utils.Total

class ActPacienteActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        val vdni = bundle!!.getString("dni").toString()
        val vnombre_completo = bundle.getString("nombre_completo").toString()
        val vcorreo = bundle.getString("correo").toString()
        val vtelefono = bundle.getString("telefono").toString()
        setContent {
            val dni by remember { mutableStateOf(vdni) }
            var nombre_completo by remember { mutableStateOf(vnombre_completo) }
            var correo by remember { mutableStateOf(vcorreo) }
            var telefono by remember { mutableStateOf(vtelefono) }
            //mutablStatof: es todo momento queda actualizado el valor de la variables
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
                        modifier = Modifier.offset(x = 10.dp, y = 40.dp)
                            .size(80.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.titulo_clinica),
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.offset(x = 60.dp, y = 64.dp)
                    )
                    Canvas(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        val canvasWidth = size.width
                        val canvasHeight = size.height
                        val percentage = 0.3f // Ajustar este valor para controlar la reducción
                        drawLine(
                            start = Offset(x = canvasWidth / 2, y = percentage * canvasHeight), // Reducir inicio y final
                            end = Offset(x = canvasWidth / 2, y = (1 - percentage) * canvasHeight), // Reducir inicio y final
                            color = Color.Black,
                            strokeWidth = 3.dp.toPx()
                        )
                    }

                    Text(
                        text = "Actualizar",
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.offset(x = 250.dp, y = 64.dp)
                    )
                }
            }
            Column (
                modifier = Modifier.offset(x = 0.dp, y = 160.dp)
            ){
                TextField(value=dni,
                    modifier = Modifier
                        .fillMaxWidth()
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
                    label ={Text(text="Codigo")},
                    onValueChange = {
                        //no colocamos nada para no poder modificar el codigo
                    })
                Spacer(modifier= Modifier.padding(all= dimensionResource(id = R.dimen.espacio_min)))
                TextField(value=nombre_completo,
                    modifier = Modifier
                        .fillMaxWidth()
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
                    label ={Text(text="Nombre Completo")},
                    onValueChange = {
                        nombre_completo=it
                    })
                Spacer(modifier= Modifier.padding(all= dimensionResource(id = R.dimen.espacio_min)))
                TextField(value=correo,
                    modifier = Modifier
                        .fillMaxWidth()
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
                    label ={Text(text="Correo")},
                    onValueChange = {
                        correo=it
                    })
                Spacer(modifier= Modifier.padding(all= dimensionResource(id = R.dimen.espacio_min)))
                TextField(value=telefono,
                    modifier = Modifier
                        .fillMaxWidth()
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
                    label ={Text(text="Telefono")},
                    onValueChange = {
                        telefono=it
                    })
                Spacer(modifier= Modifier.padding(all= dimensionResource(id = R.dimen.espacio_min)))
                Button(onClick = {
                    leerServicioActualizar(dni,nombre_completo,correo,telefono)
                }) {
                    Text(text = "Actualizar")
                }
                Button(onClick = {
                    leerServicioEliminar(dni,nombre_completo,correo,telefono)
                }) {
                    Text(text = "Eliminar")
                }
            }
        }
    }

    private fun leerServicioActualizar(dni: String, nombreCompleto: String, correo: String, telefono: String) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = Total.rutaServices+"pacientesupdate.php"
        // Request a string response from the provided URL.
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                Log.d("DATOS",response)
                Toast.makeText(this,"Se ha actualizado paciente con codigo:"+response,
                    Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,BuscarPacienteActivity::class.java))
            },
            {
            }) {
            override fun getParams():MutableMap<String,String>{
                val params =HashMap<String,String>()
                params.put("dni",dni)
                params.put("nombre_completo",nombreCompleto)
                params.put("correo",correo)
                params.put("telefono",telefono)
                return params
            }
        }
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
    private fun leerServicioEliminar(dni: String, nombreCompleto: String, correo: String, telefono: String) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = Total.rutaServices+"pacientesdelete.php"
        // Request a string response from the provided URL.
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                Log.d("DATOS",response)
                Toast.makeText(this,"Se ha eliminado paciente con codigo:"+response,
                    Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,BuscarPacienteActivity::class.java))
            },
            {
            }) {
            override fun getParams():MutableMap<String,String>{
                val params =HashMap<String,String>()
                params.put("dni",dni)
                params.put("nombre_completo",nombreCompleto)
                params.put("correo",correo)
                params.put("telefono",telefono)
                return params
            }
        }
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
    }

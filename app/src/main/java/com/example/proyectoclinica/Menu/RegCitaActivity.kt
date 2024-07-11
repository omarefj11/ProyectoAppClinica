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
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.proyectoclinica.R
import com.example.proyectoclinica.utils.Total
import org.json.JSONArray

class RegCitaActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var fecha   by remember { mutableStateOf("") }
            var dni by remember { mutableStateOf("") }
            var nombre_completo   by remember { mutableStateOf("") }
            var telefono by remember { mutableStateOf("") }
            var SoliEspecialidad by remember { mutableStateOf("") }
            var nomDoc by remember { mutableStateOf("") }
            var fechaCita by remember { mutableStateOf("") }
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
                        text = "Registrar",
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

                TextField(value = fecha,
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
                    label= { Text(text = "Fecha Actual") },
                    onValueChange = {
                        fecha=it

                    })
                Spacer(modifier= Modifier.padding(all= dimensionResource(id = R.dimen.espacio_min)))
                TextField(value = dni,
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
                    label= { Text(text = "DNI del Paciente") },
                    onValueChange = {
                        dni=it
                    })
                Spacer(modifier= Modifier.padding(all= dimensionResource(id = R.dimen.espacio_min)))
                TextField(value = nombre_completo,
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
                    label= { Text(text = "Nombre completo") },
                    onValueChange = {
                        nombre_completo=it

                    })
                Spacer(modifier= Modifier.padding(all= dimensionResource(id = R.dimen.espacio_min)))
                TextField(value = telefono,
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
                    label= { Text(text = "Telefono del paciente") },
                    onValueChange = {
                        telefono=it
                    })
                Spacer(modifier= Modifier.padding(all= dimensionResource(id = R.dimen.espacio_min)))
                TextField(value = SoliEspecialidad,
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
                    label= { Text(text = "Especialidad Solicitada") },
                    onValueChange = {
                        SoliEspecialidad=it
                    })
                Spacer(modifier= Modifier.padding(all= dimensionResource(id = R.dimen.espacio_min)))
                TextField(value = nomDoc,
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
                    label= { Text(text = "Nombre del Doctor") },
                    onValueChange = {
                        nomDoc=it
                    })
                Spacer(modifier= Modifier.padding(all= dimensionResource(id = R.dimen.espacio_min)))
                TextField(value = fechaCita,
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
                    label= { Text(text = "Fecha programada para la Cita") },
                    onValueChange = {
                        fechaCita=it
                    })
                Spacer(modifier= Modifier.padding(all= dimensionResource(id = R.dimen.espacio_min)))
                Button( onClick = {
                    leerServicio(fecha,dni,nombre_completo,telefono,SoliEspecialidad,nomDoc,fechaCita)
                }) {
                    Text(text="Guardar")
                }
            }
        }
    }

    private fun leerServicio(fecha: String, dni: String, nombreCompleto: String, telefono: String,
                             soliEspecialidad: String, nomDoc: String, fechaCita: String) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = Total.rutaServices+"citasinsert.php"
        // Request a string response from the provided URL.
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                Log.d("DATOS",response)
                Toast.makeText(this,"Se ha registrado citas con Codigo de Citas:"+response,
                    Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,BuscarCitasActivity::class.java))
            },
            {
            }) {
            override fun getParams():MutableMap<String,String>{
                val params =HashMap<String,String>()
                params.put("fecha",fecha)
                params.put("dni",dni)
                params.put("nombreCompleto",nombreCompleto)
                params.put("telefono",telefono)
                params.put("soliEspecialidad",soliEspecialidad)
                params.put("nomDoc",nomDoc)
                params.put("fechaCita",fechaCita)
                return params
            }
        }
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}

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

class RegMedicinaActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var nombre_medicina   by remember { mutableStateOf("") }
            var descripcion by remember { mutableStateOf("") }
            var precio   by remember { mutableStateOf("") }
            var cantidad by remember { mutableStateOf("") }
            var foto_medicamento by remember { mutableStateOf("") }
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
                TextField(value = nombre_medicina,
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
                    label= { Text(text = "Nombre medicina") },
                    onValueChange = {
                        nombre_medicina=it

                    })
                Spacer(modifier= Modifier.padding(all= dimensionResource(id = R.dimen.espacio_min)))
                TextField(value = descripcion,
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
                    label= { Text(text = "Descripcion") },
                    onValueChange = {
                        descripcion=it
                    })
                Spacer(modifier= Modifier.padding(all= dimensionResource(id = R.dimen.espacio_min)))
                TextField(value = precio,
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
                    label= { Text(text = "Precio") },
                    onValueChange = {
                        precio=it

                    })
                Spacer(modifier= Modifier.padding(all= dimensionResource(id = R.dimen.espacio_min)))
                TextField(value = cantidad,
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
                    label= { Text(text = "Cantidad") },
                    onValueChange = {
                        cantidad=it
                    })
                Spacer(modifier= Modifier.padding(all= dimensionResource(id = R.dimen.espacio_min)))
                TextField(value = foto_medicamento,
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
                    label= { Text(text = "Foto medicamento") },
                    onValueChange = {
                        foto_medicamento=it
                    })
                Spacer(modifier= Modifier.padding(all= dimensionResource(id = R.dimen.espacio_min)))
                Button( onClick = {
                    leerServicio( nombre_medicina,descripcion,precio,cantidad,foto_medicamento)
                }) {
                    Text(text="Guardar")
                }
            }
        }
    }

    private fun leerServicio(nombreMedicina: String, descripcion: String, precio: String, cantidad: String, foto_medicamento:String) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = Total.rutaServices+"medicinasinsert.php"
        // Request a string response from the provided URL.
        val stringRequest = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                Log.d("DATOS",response)
                Toast.makeText(this,"Se ha registrado medicina con codigo:"+response,
                    Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,BuscarMedicinaActivity::class.java))
            },
            {
            }) {
            override fun getParams():MutableMap<String,String>{
                val params =HashMap<String,String>()
                params.put("nombre_medicamento",nombreMedicina)
                params.put("descripcion",descripcion)
                params.put("precio",precio)
                params.put("cantidad",cantidad)
                params.put("foto_medicamento",foto_medicamento)
                return params
            }
        }
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}

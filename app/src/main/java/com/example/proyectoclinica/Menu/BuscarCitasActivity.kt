package com.example.proyectoclinica.Menu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.proyectoclinica.Componente.ListarCitas
import com.example.proyectoclinica.Menu.ui.theme.ProyectoClinicaTheme
import com.example.proyectoclinica.R
import com.example.proyectoclinica.utils.Total
import org.json.JSONArray

class BuscarCitasActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        leerServicio();
        setContent {
        }
    }

    private fun leerServicio() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = Total.rutaServices + "citas.php"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.d("DATOS", response)
                llenarArreglo(response)
            },
            Response.ErrorListener { error ->
                Log.e("Volley", "Error:" + error.toString())
                Toast.makeText(this, "Error al obtener Citas", Toast.LENGTH_SHORT).show()
            })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    private fun llenarArreglo(response: String) {
        val jsonArray = JSONArray(response)
        val arrayList = ArrayList<HashMap<String, String>>()

        for (i in 0 until jsonArray.length()) {
            val codCitas = jsonArray.getJSONObject(i).getString("codCitas")
            val fecha = jsonArray.getJSONObject(i).getString("fecha")
            val dni = jsonArray.getJSONObject(i).getString("dni")
            val nombreCompleto = jsonArray.getJSONObject(i).getString("nombreCompleto")
            val telefono   = jsonArray.getJSONObject(i).getString("telefono")
            val soliEspecialidad = jsonArray.getJSONObject(i).getString("soliEspecialidad")
            val nomDoc = jsonArray.getJSONObject(i).getString("nomDoc")
            val fechaCita = jsonArray.getJSONObject(i).getString("fechaCita")
            val map = HashMap<String, String>()
            map.put("codCitas", codCitas)
            map.put("fecha", fecha)
            map.put("dni", dni)
            map.put("nombreCompleto", nombreCompleto)
            map.put("telefono", telefono)
            map.put("soliEspecialidad", soliEspecialidad)
            map.put("nomDoc", nomDoc)
            map.put("fechaCita", fechaCita)

            arrayList.add(map)
        }
        dibujar(arrayList)
    }

    private fun dibujar(arrayList: ArrayList<HashMap<String, String>>) {
        setContent {
            Box {
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
                            text = "Citas",
                            style = TextStyle(
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.offset(x = 250.dp, y = 64.dp)
                        )
                    }
                }
                Column (modifier = Modifier.offset(x = 0.dp, y = 160.dp).padding(bottom = 160.dp)){
                    LazyColumn(content = {
                        items(items = arrayList, itemContent = { Citas ->
                            Column(
                                modifier = Modifier
                                    .background(color = Color.White)
                                    .padding(all = dimensionResource(id = R.dimen.espacio_general))
                                    .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(bottomEnd = 50.dp, bottomStart = 50.dp),)
                                    .clickable {
                                        seleccionarCita(Citas)
                                    }
                            ) {
                                ListarCitas(Citas)
                            }
                        })
                    })
                }
                FloatingActionButton(onClick = {
                    startActivity(Intent(this@BuscarCitasActivity,RegCitaActivity::class.java))
                },modifier = Modifier.padding(all= dimensionResource(id = R.dimen.espacio_general))
                    .align(Alignment.BottomEnd)){
                    Icon(imageVector = Icons.Filled.Add,contentDescription=null)
                }
            }
        }
    }

    private fun seleccionarCita(citas: HashMap<String, String>) {
        //recuperand los datos
        val codCitas = citas["codCitas"]
        val fecha = citas["fecha"]
        val dni = citas["dni"]
        val nombreCompleto = citas["nombreCompleto"]
        val telefono = citas["telefono"]
        val soliEspecialidad = citas["soliEspecialidad"]
        val nomDoc = citas["nomDoc"]
        val fechaCita = citas["fechaCita"]
        Toast.makeText(this, nombreCompleto, Toast.LENGTH_SHORT).show()
        //cargar en un objeto bundle
        val bundle = Bundle().apply {
            putString("codCitas", codCitas)
            putString("fecha", fecha)
            putString("dni", dni)
            putString("nombreCompleto", nombreCompleto)
            putString("telefono", telefono)
            putString("soliEspecialidad", soliEspecialidad)
            putString("nomDoc", nomDoc)
            putString("fechaCita", fechaCita)
        }
        val intent = Intent(this,ActCitasActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}
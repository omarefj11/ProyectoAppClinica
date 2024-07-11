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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.example.proyectoclinica.Componente.ListarDoctor
import com.example.proyectoclinica.Menu.ui.theme.ProyectoClinicaTheme
import com.example.proyectoclinica.R
import com.example.proyectoclinica.utils.Total
import org.json.JSONArray

class BuscarDoctorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        leerServicio();
        setContent {

        }
    }
    private fun leerServicio() {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = Total.rutaServices + "doctores.php"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.d("DATOS", response)
                llenarArreglo(response)
            },
            Response.ErrorListener { error ->
                Log.e("Volley", "Error:" + error.toString())
                Toast.makeText(this, "Error al obtener doctores", Toast.LENGTH_SHORT).show()
            })
        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    private fun llenarArreglo(response: String) {
        val jsonArray = JSONArray(response)
        val arrayList = ArrayList<HashMap<String, String>>()

        for (i in 0 until jsonArray.length()) {
            val dni = jsonArray.getJSONObject(i).getString("dni")
            val nombre_completo = jsonArray.getJSONObject(i).getString("nombre_completo")
            val correo = jsonArray.getJSONObject(i).getString("correo")
            val telefono = jsonArray.getJSONObject(i).getString("telefono")
            val especialidad_id = jsonArray.getJSONObject(i).getString("especialidad_id")
            val horario_id = jsonArray.getJSONObject(i).getString("horario_id")
            val foto = jsonArray.getJSONObject(i).getString("foto")
            val map = HashMap<String, String>()
            map.put("dni", dni)
            map.put("nombre_completo", nombre_completo)
            map.put("correo", correo)
            map.put("telefono", telefono)
            map.put("especialidad_id", especialidad_id)
            map.put("horario_id", horario_id)
            map.put("foto", foto)
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
                            text = "Doctores",
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
                        items(items = arrayList, itemContent = { doctores ->
                            Column(
                                modifier = Modifier
                                    .background(color = Color.White)
                                    .padding(all = dimensionResource(id = R.dimen.espacio_general))
                                    .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(bottomEnd = 50.dp, bottomStart = 50.dp),)
                                    .clickable {
                                        seleccionarDoctor(doctores)
                                    }
                            ) {
                                ListarDoctor(doctores)
                            }
                        })
                    })
                }
                FloatingActionButton(onClick = {
                    startActivity(Intent(this@BuscarDoctorActivity,RegDoctoresActivity::class.java))
                },modifier = Modifier.padding(all= dimensionResource(id = R.dimen.espacio_general))
                    .align(Alignment.BottomEnd)){
                    Icon(imageVector = Icons.Filled.Add,contentDescription=null)
                }
            }
        }
    }

    private fun seleccionarDoctor(doctores: HashMap<String, String>) {
        //recuperand los datos
        val dni = doctores["dni"]
        val nombre_completo = doctores["nombre_completo"]
        val correo = doctores["correo"]
        val telefono = doctores["telefono"]
        val especialidad_id = doctores["especialidad_id"]
        val horario_id = doctores["horario_id"]
        val foto = doctores["foto"]
        Toast.makeText(this, nombre_completo, Toast.LENGTH_SHORT).show()
        //cargar en un objeto bundle
        val bundle = Bundle().apply {
            putString("dni", dni)
            putString("nombre_completo", nombre_completo)
            putString("correo", correo)
            putString("telefono", telefono)
            putString("especialidad_id",especialidad_id)
            putString("horario_id",horario_id)
            putString("foto",foto)
        }
        val intent = Intent(this,ActDoctoresActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}


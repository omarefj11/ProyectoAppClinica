package com.example.proyectoclinica

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectoclinica.Menu.BuscarCitasActivity
import com.example.proyectoclinica.Menu.BuscarDoctorActivity
import com.example.proyectoclinica.Menu.BuscarMedicinaActivity
import com.example.proyectoclinica.Menu.BuscarPacienteActivity
import com.example.proyectoclinica.Menu.PagoActivity
import com.example.proyectoclinica.Menu.RegCitaActivity
import com.example.proyectoclinica.Menu.RegDoctoresActivity
import com.example.proyectoclinica.Menu.RegPacietesActivity

class UserPrincipalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val etiquetas= arrayOf(
            "Buscar Doctor","Buscar Paciente", "Buscar Medicina","Buscar Cita","Salir"
        )
        val iconos= intArrayOf(
            R.drawable.equipo_medico,R.drawable.search,R.drawable.informe_medico, R.drawable.informe_medico, R.drawable.informe_medico
        )

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
                        text = stringResource(id = R.string.escritorio),
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
                LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
                    items(etiquetas.size) {
                        Card (modifier = Modifier
                            .padding(24.dp)
                            .border(
                                width = 2.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(10.dp),
                            )
                            .clickable {
                                mostrarVentana(it)
                            }
                            .wrapContentSize(Alignment.Center)
                            ,colors = CardDefaults.cardColors(containerColor = Color.White)){
                            Icon(painter = painterResource(id = iconos[it]), contentDescription = null)
                            Text(text = etiquetas[it], textAlign = TextAlign.Center)
                        }
                    }
                })

            }
        }
    }
    private fun mostrarVentana(it: Int) {
        when(it){
            0 -> startActivity(Intent(this, BuscarDoctorActivity::class.java))
            1 -> startActivity(Intent(this, BuscarPacienteActivity::class.java))
            2 -> startActivity(Intent(this, BuscarMedicinaActivity::class.java))
            3 -> startActivity(Intent(this, BuscarCitasActivity::class.java))
            4 -> finish()
        }
    }
}

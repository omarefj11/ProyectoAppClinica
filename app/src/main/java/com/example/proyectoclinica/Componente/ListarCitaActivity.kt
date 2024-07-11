package com.example.proyectoclinica.Componente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectoclinica.Componente.ui.theme.ProyectoClinicaTheme
import com.example.proyectoclinica.R

@Composable
public fun ListarCitas(citas: java.util.HashMap<String, String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(id = R.dimen.espacio_general))
    ){
        /*AsyncImage(
            model = Total.rutaServices+"img/"+doctores["foto"],
            contentDescription = null,
            modifier = Modifier
                //.fillMaxWidth()
                //.height(300.dp)
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )*/
        Text(text = citas["codCitas"].toString() + " - " + citas["nombreCompleto"].toString(),
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )
        Text(text = citas["soliEspecialidad"].toString() + " - " + citas["fechaCita"].toString(),
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            color = Color.Gray)

    }
}
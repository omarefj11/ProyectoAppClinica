package com.example.proyectoclinica.Componente

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.proyectoclinica.Componente.ui.theme.ProyectoClinicaTheme
import com.example.proyectoclinica.R
import com.example.proyectoclinica.utils.Total

@Composable
public fun ListarMedicina(medicinas: java.util.HashMap<String, String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = dimensionResource(id = R.dimen.espacio_general))
    ){
        Text(text = medicinas["nombre_medicamento"].toString(),
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )
        AsyncImage(
            model = Total.rutaServices+"foto_medicamento/"+medicinas["foto_medicamento"],
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(12.dp)
                ),
            contentScale = ContentScale.Crop
        )
        Text(text = medicinas["descripcion"].toString(),
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
        )
        Text(text = medicinas["precio"].toString(),
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
            color = Color.Gray)

    }
}
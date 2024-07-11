package com.example.proyectoclinica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.proyectoclinica.ui.theme.ProyectoClinicaTheme

class TerminosActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier
                    .padding(all = dimensionResource(id = R.dimen.espacio_general))
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = stringResource(id = R.string.vision_clinica),
                    style = TextStyle(fontSize = 48.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(all = dimensionResource(id = R.dimen.espacio_general))
                )
                Text(text = stringResource(id = R.string.vision_text_clinica))

                Text(
                    text = stringResource(id = R.string.mision_clinica),
                    style = TextStyle(fontSize = 48.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(all = dimensionResource(id = R.dimen.espacio_general))
                )
                Text(text = stringResource(id = R.string.mision_text_clinica))

                Text(
                    text = stringResource(id = R.string.terminos_clinica),
                    style = TextStyle(fontSize = 48.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(all = dimensionResource(id = R.dimen.espacio_general))
                )
                Text(text = stringResource(id = R.string.termino_text_clinica))
                Button(onClick = {
                    finish()//cierra el activity actual
                }) {
                    Text(text = stringResource(id = R.string.close_activity))
                }
            }
        }
    }
}

package com.example.jetpackpruebas

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackpruebas.ui.theme.JetpackPruebasTheme

data class Recipe(
    @DrawableRes
    val imageResource: Int,
    val title: String,
    val ingredients: List<String>
)

@Composable
private fun RecipeCard(recipe: Recipe, onRecipeClick: (Recipe) -> Unit) {
    val image = painterResource(id = R.mipmap.header)
    Card(shape = RoundedCornerShape(8.dp), elevation = 8.dp, modifier = Modifier.padding(8.dp)){
        Column(modifier = Modifier
            .padding(16.dp)
            .clickable { onRecipeClick(recipe) }) {
            val imageModifier = Modifier
                .requiredHeight(150.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))
            Image(painter = image, modifier = imageModifier, contentScale = ContentScale.Crop, contentDescription = "Foto receta" )
  Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(text = recipe.title, style = MaterialTheme.typography.h6)
            for (ingredient in recipe.ingredients){
                Text(text = "* $ingredient", style = MaterialTheme.typography.body2)
            }
        }
    }
    Spacer(modifier = Modifier.padding(top = 10.dp))
}
val recipeList = listOf(
    Recipe(R.mipmap.header, "Arroz", listOf("Arroz", "tomate", "Crema")),
    Recipe(R.mipmap.header, "Pasta", listOf("Ingr1", "Ingr2", "Ingr3")),
    Recipe(R.mipmap.header, "Arroz", listOf("Arroz", "tomate", "Crema")),
    Recipe(R.mipmap.header, "Pasta", listOf("Ingr1", "Ingr2", "Ingr3")),
    Recipe(R.mipmap.header, "Arroz", listOf("Arroz", "tomate", "Crema")),
)

@Composable
private fun RecipeColumnList (recipeList: List<Recipe>) {
    LazyColumn {
        items(recipeList) { recipe ->
            RecipeCard(recipe = recipe, onRecipeClick = {
                Log.d("Recipe", "RecipeColumnList: ${it.title}")
            })
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackPruebasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    Greeting("Android")
//                    NewsStory()
                    RecipeColumnList(recipeList = recipeList)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackPruebasTheme {
        Greeting("Android")
    }
}


@Composable
private fun NewsStory() {
    Spacer(modifier = Modifier.padding(top = 10.dp))
    Row(modifier = Modifier.padding(16.dp)) {
        Image(
            painter = painterResource(R.mipmap.header),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 40 dp
                .size(75.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
        )
        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.padding(16.dp)) {

            Text(text = "Título Noticia", style = MaterialTheme.typography.h6)
            Text(text = "Descripcion", style = MaterialTheme.typography.body1)
            Text(text = "Pie de página", style = MaterialTheme.typography.body2)
        }
    }
    Spacer(modifier = Modifier.padding(top = 10.dp))
}

@Composable
@Preview
private fun ListaComidas(){
    RecipeCard(recipeList[0], onRecipeClick = {})
    }



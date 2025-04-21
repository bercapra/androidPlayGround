package com.example.androidplayground.ui.util.preview

import com.example.domain.entity.MarvelCharacter

val previewCharacterDefault = MarvelCharacter(
    id = 9292,
    name = "MuriMan",
    description = "¿Qué es eso? ¡Es MuriMan!",
    img = ""
)

val previewCharacterWithImage = MarvelCharacter(
    id = 1001,
    name = "IronCoder",
    description = "Codea hasta en sus sueños. Ni los bugs se le escapan.",
    img = "https://via.placeholder.com/300x400.png?text=IronCoder"
)

val previewCharacterNoDescription = MarvelCharacter(
    id = 404,
    name = "InvisibleNull",
    description = "",
    img = ""
)

val previewCharacterLongDescription = MarvelCharacter(
    id = 777,
    name = "LoreMan",
    description = "Este personaje tiene una descripción extremadamente larga, digna de Wikipedia. Su historia se remonta a tiempos inmemoriales, donde cada línea de código escrita en su nombre generaba una leyenda urbana en los pasillos del backend.",
    img = ""
)
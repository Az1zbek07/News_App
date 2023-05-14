package com.example.data.mapper

import com.example.data.model.InformDTO
import com.example.domain.model.Inform

fun InformDTO.toInform(): Inform {
    return Inform(title = title, content = content, url = urlToImage)
}
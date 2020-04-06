package com.example.ocadochallenge.domain

interface Mapper<in InputType, out OutputType> {
    fun map(model: InputType): OutputType

    fun mapList(list: List<InputType>): List<OutputType> = list.map { map(it) }
}
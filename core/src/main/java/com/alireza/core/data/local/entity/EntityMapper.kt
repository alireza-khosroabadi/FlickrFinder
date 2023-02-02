package com.alireza.core.data.local.entity

/**
 * mapper for convert data model to entity model for save in database
 * @param DATA_MODEL is class extended from DataModel interface
 * */
interface EntityMapper<out ENTITY_MODEL, DATA_MODEL> {
    fun toEntityModel(dataModel: DATA_MODEL): ENTITY_MODEL
}
package com.alireza.core.domain.model

import com.alireza.core.data.local.entity.EntityModel

/**
 * mapper for convert entity model to domain model for ui
 * @param ENTITY_MODEL is class extended from DomainModel interface
 * */
interface DomainModelMapper<out DOMAIN_MODEL : DomainModel, ENTITY_MODEL : EntityModel> {
    fun toDomainModel(dataModel: ENTITY_MODEL): DOMAIN_MODEL
}
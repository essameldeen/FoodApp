package com.example.jetpackcomposemvvm.Network.model

import com.example.jetpackcomposemvvm.domain.model.Food
import com.example.jetpackcomposemvvm.domain.utils.EntityMapper

class FoodNetworkMapper : EntityMapper<FoodNetworkEntity, Food> {
    override fun mapFromEntity(entity: FoodNetworkEntity): Food {
        return Food(
            id = entity.pk,
            title = entity.title,
            featuredImage = entity.featuredImage,
            rating = entity.rating,
            publisher = entity.publisher,
            sourceUrl = entity.sourceUrl,
            description = entity.description,
            cookingInstructions = entity.cookingInstructions,
            ingredients = entity.ingredients ?: listOf(),
            dateAdded = entity.dateAdded,
            dateUpdated = entity.dateUpdated,
        )
    }

    override fun mapToEntity(domainModel: Food): FoodNetworkEntity {
        return FoodNetworkEntity(
            pk = domainModel.id,
            title = domainModel.title,
            featuredImage = domainModel.featuredImage,
            rating = domainModel.rating,
            publisher = domainModel.publisher,
            sourceUrl = domainModel.sourceUrl,
            description = domainModel.description,
            cookingInstructions = domainModel.cookingInstructions,
            ingredients = domainModel.ingredients,
            dateAdded = domainModel.dateAdded,
            dateUpdated = domainModel.dateUpdated,
        )
    }

    fun fromEntityList(initial: List<FoodNetworkEntity>): List<Food> {
        return initial.map { mapFromEntity(it) }
    }

    fun toEntityList(initial: List<Food>): List<FoodNetworkEntity> {
        return initial.map { mapToEntity(it) }
    }

}
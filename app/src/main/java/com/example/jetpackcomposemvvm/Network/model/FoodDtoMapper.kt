package com.example.jetpackcomposemvvm.Network.model

import com.example.jetpackcomposemvvm.domain.model.Food
import com.example.jetpackcomposemvvm.domain.utils.DomainMapper

class FoodDtoMapper : DomainMapper<FoodDto, Food> {
    override fun mapToDomainModel(model: FoodDto): Food {
        return Food(
            id = model.pk,
            title = model.title,
            featuredImage = model.featuredImage,
            rating = model.rating,
            publisher = model.publisher,
            sourceUrl = model.sourceUrl,
            description = model.description,
            cookingInstructions = model.cookingInstructions,
            ingredients = model.ingredients ?: listOf(),
            dateAdded = model.dateAdded,
            dateUpdated = model.dateUpdated,
        )
    }

    override fun mapFromDomainModel(domainModel: Food): FoodDto {
        return FoodDto(
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

    fun toDomainList(initial: List<FoodDto>): List<Food> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Food>): List<FoodDto> {
        return initial.map { mapFromDomainModel(it) }
    }

}
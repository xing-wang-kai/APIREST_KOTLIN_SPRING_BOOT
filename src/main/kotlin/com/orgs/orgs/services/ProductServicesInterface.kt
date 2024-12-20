package com.orgs.orgs.services

import com.orgs.orgs.dao.ProductDao
import com.orgs.orgs.dto.ProductDTO
import com.orgs.orgs.models.Product
import org.springframework.data.domain.Pageable
import java.util.*

interface ProductServicesInterface {

    fun create(productDto: ProductDTO): ProductDao

    fun getAll(pagination: Pageable): List<ProductDao>

    fun getById(id: Long) : ProductDao

    fun update(id: Long, productDto: ProductDTO): ProductDao

    fun delete(id: Long)

}
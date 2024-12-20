package com.orgs.orgs.services

import com.orgs.orgs.dao.ProductDao
import com.orgs.orgs.dao.toProduct
import com.orgs.orgs.dao.toProductDao
import com.orgs.orgs.dao.toUser
import com.orgs.orgs.dto.ProductDTO
import com.orgs.orgs.extensions.toUserDto
import com.orgs.orgs.models.Product
import com.orgs.orgs.repository.ProductsRepository
import jakarta.transaction.Transactional
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import java.time.LocalDateTime

@Service
@Transactional
class ProductServicesImpl(
    private val repository: ProductsRepository,
    private val userService: UserService
) : ProductServicesInterface {
    override fun create(productDto: ProductDTO): ProductDao {

        Assert.hasLength(productDto.title, "[Title] Can't be empty!")
        Assert.isTrue(productDto.title.length >= 5, "[Title] this field need have 5 or more Characteres!")

        val userDao = userService.getById(productDto.userId!!)
        val productList = userDao.products?.map { repository.findById(it).get() }?.toMutableList()
        val user = userDao.toUser(productList!!)

        var modelProduct = Product(
            title = productDto.title,
            description = productDto.description,
            price = productDto.price,
            imgUrl = productDto.imgUrl,
            user =  user
        )
        println(modelProduct)

        var productSaved = repository.save(modelProduct)

        user.products?.add(productSaved)

        userService.update(user.id!!, user.toUserDto())

        var returnProduct = productSaved.toProductDao()

        return returnProduct
    }

    override fun getAll(pagination:Pageable): List<ProductDao> {
        var products = repository.findAll(pagination)
        return products.toList().map { it.toProductDao() }
    }

    override fun getById(id: Long): ProductDao {
        var products = repository.findById(id)
        return products.get().toProductDao()
    }

    override fun update(id: Long, productDto: ProductDTO): ProductDao {
        val productDao = this.getById(id)
        val userDao = userService.getById(productDto.userId!!)
        val productList = userDao.products?.map { repository.findById(it).get() }?.toMutableList()
        val user = userDao.toUser(productList!!)
        val transformedProduct = productDao.toProduct(user)

        transformedProduct.title = productDto.title
        transformedProduct.description = productDto.description
        transformedProduct.price = productDto.price
        transformedProduct.imgUrl = productDto.imgUrl
        transformedProduct.updatedAt = LocalDateTime.now()

        repository.save(transformedProduct)

        return transformedProduct.toProductDao()
    }

    override fun delete(id: Long) {
        val productDao = this.getById(id)

        val userDao = userService.getById(productDao.userId!!)
        val productList = userDao.products?.map { repository.findById(it).get() }?.toMutableList()
        val user = userDao.toUser(productList!!)

        val persistenceProduct = productDao.toProduct(user)
        persistenceProduct.isDeleted = true
        persistenceProduct.deletedAt = LocalDateTime.now()
        repository.save(persistenceProduct)
    }
}
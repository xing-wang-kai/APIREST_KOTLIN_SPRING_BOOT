package com.orgs.orgs.controller

import com.orgs.orgs.dao.ProductDao
import com.orgs.orgs.dto.ProductDTO
import com.orgs.orgs.models.Product
import com.orgs.orgs.repository.ProductsRepository
import com.orgs.orgs.services.ProductServicesImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class ProductsController(private val service: ProductServicesImpl) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody product: ProductDTO): ProductDao = service.create(product)

    @GetMapping
    fun getAll(): List<ProductDao> = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) : ResponseEntity<ProductDao> {
        return try {
            val productDao = service.getById(id) // Agora retorna um ProductDao diretamente
            ResponseEntity.ok(productDao)
        } catch (e: NoSuchElementException) { // Trate casos onde o ID não existe
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody productDTO: ProductDTO): ResponseEntity<ProductDao> {
        return try {
            val updatedProductDao = service.update(id, productDTO)
            ResponseEntity.ok(updatedProductDao)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        } catch (e: Exception) {
            ResponseEntity.status(500).build()
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity<Void>(HttpStatus.OK)
    }


}
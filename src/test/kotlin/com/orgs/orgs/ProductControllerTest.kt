//package com.orgs.orgs;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper
//import com.orgs.orgs.models.Product
//import com.orgs.orgs.models.User
//import com.orgs.orgs.repository.ProductsRepository;
//import org.hamcrest.Matchers
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
//import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import java.math.BigDecimal
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class ProductControllerTest {
//
//    @Autowired lateinit var sockMvc:MockMvc
//    @Autowired lateinit var productsRepository: ProductsRepository
//
//    @Test
//    fun `Quando FINDALL products Então deve retornar lista de products e Status 200`(){
//
//
//
//        var usuario = User(1L, "email@email.com", "antonio Luis", "secretário", "123", null)
//        var mockProduct = Product(0L, "Um producto Genérico", "Somente um produto Genérico", BigDecimal.ZERO, null, usuario)
//
//        productsRepository.save(mockProduct)
//
//        sockMvc.perform(MockMvcRequestBuilders.get("/products"))
//                .andExpect(MockMvcResultMatchers.status().isOk)
//                .andExpect(MockMvcResultMatchers.jsonPath("\$").isArray)
//                .andExpect(MockMvcResultMatchers.jsonPath("\$[0].id").isNumber)
//                .andExpect(MockMvcResultMatchers.jsonPath("\$[0].title").isString)
//                .andExpect(MockMvcResultMatchers.jsonPath("\$[0].description").isString)
//                .andExpect(MockMvcResultMatchers.jsonPath("\$[0].imgUrl").isString)
//                .andExpect(MockMvcResultMatchers.jsonPath("\$[0].price").isNumber)
//                .andDo(MockMvcResultHandlers.print())
//    }
//
//    @Test
//    fun `Quando FINDBYID produto Então retorna um product e Status 200`(){
//
//        var usuario = User(1L, "email@email.com", "antonio Luis", "secretário", "123", null)
//        var mockProduct = Product(0L, "Um producto Genérico", "Somente um produto Genérico", BigDecimal.ZERO, null, usuario)
//
//
//        var product = productsRepository.save(mockProduct)
//
//        sockMvc.perform(MockMvcRequestBuilders.get("/products/${product.id}"))
//                .andExpect(MockMvcResultMatchers.status().isOk)
//                .andExpect(MockMvcResultMatchers.jsonPath("\$.id").value(product.id))
//                .andExpect(MockMvcResultMatchers.jsonPath("\$.title").value(product.title))
//                .andExpect(MockMvcResultMatchers.jsonPath("\$.description").value(product.description))
//                .andExpect(MockMvcResultMatchers.jsonPath("\$.imgUrl").value(product.imgUrl))
//                .andExpect(MockMvcResultMatchers.jsonPath("\$.price").value(Matchers.closeTo(22.9, 0.001)))
//                .andExpect(MockMvcResultMatchers.jsonPath("\$.userId").value(product.user))
//                .andDo(MockMvcResultHandlers.print())
//    }
//
//    @Test
//    fun `Quando CREATE um product Então cria produto, retorna product criado e Status 201`(){
//
//        var usuario = User(1L, "email@email.com", "antonio Luis", "secretário", "123", null)
//        var mockProduct = Product(0L, "Um producto Genérico", "Somente um produto Genérico", BigDecimal.ZERO, null, usuario)
//
//
//        var product = productsRepository.save(mockProduct)
//
//        var json = ObjectMapper().writeValueAsString(product)
//
//        sockMvc.perform(MockMvcRequestBuilders.post("/products")
//            .accept(MediaType.APPLICATION_JSON)
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(json)
//             )
//            .andExpect(MockMvcResultMatchers.status().isCreated)
//            .andDo(MockMvcResultHandlers.print())
//        Assertions.assertFalse(productsRepository.findAll().isEmpty())
//    }
//
//    @Test
//    fun `Quando UPDATE Product Então product deverá ser alterado retorna status 200`(){
//
//        var usuario = User(1L, "email@email.com", "antonio Luis", "secretário", "123", null)
//        var mockProduct = Product(0L, "Um producto Genérico", "Somente um produto Genérico", BigDecimal.ZERO, null, usuario)
//
//
//        var product = productsRepository.save(mockProduct).copy(title = "UPDTATED")
//
//        var json = ObjectMapper().writeValueAsString(product)
//
//        sockMvc.perform(MockMvcRequestBuilders.put("/products/${product.id}")
//            .accept(MediaType.APPLICATION_JSON)
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(json)
//        )
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andDo(MockMvcResultHandlers.print())
//
//        var productFound = productsRepository.findById(product.id!!)
//        Assertions.assertTrue(productFound.isPresent)
//        Assertions.assertEquals(product.title, productFound.get().title)
//    }
//
//
//    @Test
//    fun `Quando DELETE product Então ele deverá ser deletado retorna Status 200`(){
//
//        var usuario = User(1L, "email@email.com", "antonio Luis", "secretário", "123", null)
//        var mockProduct = Product(0L, "Um producto Genérico", "Somente um produto Genérico", BigDecimal.ZERO, null, usuario)
//
//
//        var product = productsRepository.save(mockProduct).copy(title = "UPDTATED")
//
//        sockMvc.perform(MockMvcRequestBuilders.delete("/products/${product.id}"))
//            .andExpect(MockMvcResultMatchers.status().isOk)
//            .andDo(MockMvcResultHandlers.print())
//
//        var productFound = productsRepository.findById(product.id!!)
//        Assertions.assertFalse(productFound.isPresent)
//    }
//
//    @Test
//    fun `Quando CREATE um product Então title não pode ser empity Status 400`(){
//
//        var usuario = User(1L, "email@email.com", "antonio Luis", "secretário", "123", null)
//        var mockProduct = Product(0L, "Um producto Genérico", "Somente um produto Genérico", BigDecimal.ZERO, null, usuario)
//
//
//        var product = productsRepository.save(mockProduct)
//
//        var json = ObjectMapper().writeValueAsString(product)
//
//        sockMvc.perform(MockMvcRequestBuilders.post("/products")
//            .accept(MediaType.APPLICATION_JSON)
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(json)
//        )
//            .andExpect(MockMvcResultMatchers.jsonPath("\$.statusCode").isNumber)
//            .andExpect(MockMvcResultMatchers.jsonPath("\$.message").isString)
//            .andExpect(MockMvcResultMatchers.jsonPath("\$.statusCode").value(400))
//            .andExpect(MockMvcResultMatchers.jsonPath("\$.message").value("[Title] Can't be empty!"))
//            .andExpect(MockMvcResultMatchers.status().isBadRequest)
//            .andDo(MockMvcResultHandlers.print())
//        Assertions.assertFalse(productsRepository.findAll().isEmpty())
//    }
//
//    @Test
//    fun `Quando CREATE um product Então title não pode ter menos que 5 characters Status 400`(){
//
//        var usuario = User(1L, "email@email.com", "antonio Luis", "secretário", "123", null)
//        var mockProduct = Product(0L, "Um producto Genérico", "Somente um produto Genérico", BigDecimal.ZERO, null, usuario)
//
//
//        var product = productsRepository.save(mockProduct)
//
//        var json = ObjectMapper().writeValueAsString(product)
//
//        sockMvc.perform(MockMvcRequestBuilders.post("/products")
//            .accept(MediaType.APPLICATION_JSON)
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(json)
//        )
//            .andExpect(MockMvcResultMatchers.jsonPath("\$.statusCode").isNumber)
//            .andExpect(MockMvcResultMatchers.jsonPath("\$.message").isString)
//            .andExpect(MockMvcResultMatchers.jsonPath("\$.statusCode").value(400))
//            .andExpect(MockMvcResultMatchers.jsonPath("\$.message").value("[Title] this field need have 5 or more Characteres!"))
//            .andExpect(MockMvcResultMatchers.status().isBadRequest)
//            .andDo(MockMvcResultHandlers.print())
//        Assertions.assertFalse(productsRepository.findAll().isEmpty())
//    }
//}
//

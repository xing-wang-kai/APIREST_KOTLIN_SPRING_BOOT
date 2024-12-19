package com.orgs.orgs.controller

import com.orgs.orgs.dao.UserDao
import com.orgs.orgs.dao.UserDto
import com.orgs.orgs.models.User
import com.orgs.orgs.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val service: UserService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody userDto: UserDto): UserDao = service.create(userDto)

    @GetMapping
    fun getAll(): List<UserDao> = service.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) : ResponseEntity<UserDao> {
        return try {
            val userDao = service.getById(id) // Agora retorna um ProductDao diretamente
            ResponseEntity.ok(userDao)
        } catch (e: NoSuchElementException) { // Trate casos onde o ID não existe
            ResponseEntity.notFound().build()
        }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody userDto: UserDto): ResponseEntity<UserDao> {
        return try {
            val userDao = service.update(id, userDto)
            ResponseEntity.ok(userDao)
        }catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity<Void>(HttpStatus.OK)
    }
}
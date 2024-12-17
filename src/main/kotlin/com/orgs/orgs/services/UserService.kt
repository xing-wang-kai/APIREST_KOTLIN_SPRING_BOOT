package com.orgs.orgs.services
import com.orgs.orgs.models.User
import com.orgs.orgs.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import java.util.*


@Service
@Transactional
class UserService(private val repository: UserRepository): UserServiceInterface {
    override fun create(user: User): User {

        val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$".toRegex()

        Assert.hasLength(user.email, "[User] Can't be empty!")
        Assert.isTrue(user.email.length >= 5, "[User] this field needs to have 5 or more characters!")
        Assert.isTrue(user.email.matches(emailRegex), "[User] Invalid email format!")

        return repository.save(user)
    }

    override fun getAll(): List<User> {
        return repository.findAll()
    }

    override fun getById(id: Long): Optional<User> {
        return repository.findById(id)
    }

    override fun update(id: Long, user: User): Optional<User> {
        val optional = this.getById(id)
        if (optional.isEmpty) {
            return Optional.empty()
        }
        return optional.map {
            // Crie um novo objeto de User com os dados alterados manualmente
            val userToUpdate = User(
                id = it.id, // mantém o id original
                email = user.email,
                name = user.name,
                role = user.role,
                password = user.password
            )
            repository.save(userToUpdate)
        }
    }

    override fun delete(id: Long) {
        this.getById(id).map {
            repository.delete(it)
        }.orElseThrow{throw RuntimeException("Id $id Não foi encontrado")}
    }
}
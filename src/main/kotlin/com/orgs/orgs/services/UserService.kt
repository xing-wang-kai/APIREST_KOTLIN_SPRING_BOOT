package com.orgs.orgs.services
import com.orgs.orgs.dao.UserDao
import com.orgs.orgs.dao.UserDto
import com.orgs.orgs.dao.toUser
import com.orgs.orgs.dao.toUserDao
import com.orgs.orgs.extensions.toUser
import com.orgs.orgs.models.Product
import com.orgs.orgs.repository.ProductsRepository
import com.orgs.orgs.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import java.time.LocalDateTime


@Service
@Transactional
class UserService(
    private val repository: UserRepository,
    private val ProductRepository: ProductsRepository
): UserServiceInterface {
    override fun create(userDto: UserDto): UserDao {

        val emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$".toRegex()

        Assert.hasLength(userDto.email, "[User] Can't be empty!")
        Assert.isTrue(userDto.email.length >= 5, "[User] this field needs to have 5 or more characters!")
        Assert.isTrue(userDto.email.matches(emailRegex), "[User] Invalid email format!")

        var listOfProduct: MutableList<Product> = mutableListOf()
        var checkedUser = userDto.toUser(listOfProduct)

        var savedUser = repository.save(checkedUser)

        return savedUser.toUserDao()
    }

    override fun getAll(): List<UserDao> {
        var users =  repository.findByIsDeleted(false)
        var returnUsers = users.map { it.toUserDao() }
        return returnUsers
    }

    override fun getById(id: Long): UserDao {
        var findUser = repository.findByIdAndIsDeletedFalse(id).get()
        return findUser.toUserDao()
    }

    override fun update(id: Long, userDto: UserDto): UserDao {
        val userDao = this.getById(id)


        userDao.email = userDto.email ?: userDao.email
        userDao.name = userDto.name ?: userDao.name
        userDao.password = userDto.password ?: userDao.password
        userDao.role = userDto.role ?: userDao.role
        userDao.updatedAt = LocalDateTime.now()


        val productList = userDao.products?.map { ProductRepository.findById(it).get() }?.toMutableList()

        val currentUser = userDao.toUser(productList!!)


        val savedProduct = repository.save(currentUser)

        return savedProduct.toUserDao()
    }

    override fun delete(id: Long) {
        val userDao = this.getById(id)
        userDao.isDeleted = true
        val productList = userDao.products?.map { ProductRepository.findById(it).get() }?.toMutableList()
        repository.save(userDao.toUser(productList!!))
    }
}
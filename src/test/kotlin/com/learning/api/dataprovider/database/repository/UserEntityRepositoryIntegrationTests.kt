package com.learning.api.dataprovider.database.repository

import com.learning.api.BaseTest.Companion.USERNAME_TEST
import com.learning.api.dataprovider.database.entity.UserEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class UserEntityRepositoryIntegrationTests {

    @Autowired
    private val repository: UserEntityRepository? = null

    @DisplayName(
        "Deve buscar na base o usuario a partir do username, " +
        "mas nao deve encontrar nada."
    )
    @Test
    fun shouldFindByUserNameWithNotFound() {
        // GIVEN | WHEN
        val userEntityOptional = this.repository!!.findByUsername(USERNAME_TEST)

        //THEN
        assertThat(userEntityOptional).isEmpty
    }

    @DisplayName(
        "Deve checar se o usuário existe pelo username, " +
        "mas deve retornar FALSE."
    )
    @Test
    fun shouldCheckUsernameExistsWithResponseFalse() {
        // GIVEN | WHEN
        val existsByUsername = this.repository!!.existsByUsername(USERNAME_TEST)

        //THEN
        assertThat(existsByUsername).isFalse
    }

    @DisplayName(
        "Deve salvar o usuario, e para garantir deve validar se o username retornado n" +
        "o objeto contém o mesmo username passado como parametro."
    )
    @Test
    fun shouldSave() {
        // GIVEN | WHEN
        val userEntity = this.repository!!.save(UserEntity(USERNAME_TEST))

        //THEN
        assertThat(userEntity.username).isEqualTo(USERNAME_TEST)
        assertThat(userEntity.uuid).isNotNull
    }

    @DisplayName(
        "Deve salvar e buscar o usuario na base e deve retornar o objeto."
    )
    @Test
    fun shouldSaveAndFindByUserNameWithSuccess() {
        // GIVEN
        this.repository!!.save(UserEntity(USERNAME_TEST))

        // WHEN
        val userEntityOptional = this.repository.findByUsername(USERNAME_TEST)

        // THEN
        assertThat(userEntityOptional).isNotEmpty
        assertThat(userEntityOptional.get().username).isEqualTo(USERNAME_TEST)
        assertThat(userEntityOptional.get().uuid).isNotNull
    }

    @DisplayName(
        "Deve checar se o usuário existe pelo username, " +
         "e deve retornar TRUE."
    )
    @Test
    fun shouldCheckUsernameExistsWithResponseTrue() {
        // GIVEN
        this.repository!!.save(UserEntity(USERNAME_TEST))

        // WHEN
        val existsByUsername = this.repository.existsByUsername(USERNAME_TEST)

        // THEN
        assertThat(existsByUsername).isTrue
    }


}
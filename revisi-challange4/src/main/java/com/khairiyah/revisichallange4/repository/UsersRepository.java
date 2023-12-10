package com.khairiyah.revisichallange4.repository;

import com.khairiyah.revisichallange4.model.Merchant;
import com.khairiyah.revisichallange4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UsersRepository extends JpaRepository<User, UUID> {

    @Procedure("addUser")
    User addUser(@Param("p_username") String nameUser,
                          @Param("p_password") String password);

    @Procedure("editUser")
    User editUser(@Param("p_user_id") UUID id,
                     @Param("p_username") String nameUser,
                     @Param("p_password") String password);

    @Procedure("deleteUserById")
    void deleteUserById(@Param("user_id") UUID id);



}
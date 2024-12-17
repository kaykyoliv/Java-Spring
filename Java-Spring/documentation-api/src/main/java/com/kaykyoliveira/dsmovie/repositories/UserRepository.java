package com.kaykyoliveira.dsmovie.repositories;

import java.util.List;
import java.util.Optional;

import com.kaykyoliveira.dsmovie.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kaykyoliveira.dsmovie.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsername(String username);
	
	@Query(nativeQuery = true, value = """
			SELECT tb_user.username, tb_user.password, tb_role.id AS roleId, tb_role.authority
			FROM tb_user
			INNER JOIN tb_user_role ON tb_user.id = tb_user_role.user_id
			INNER JOIN tb_role ON tb_role.id = tb_user_role.role_id
			WHERE tb_user.username = :username
		""")
	List<UserDetailsProjection> searchUserAndRolesByUsername(String username);
}

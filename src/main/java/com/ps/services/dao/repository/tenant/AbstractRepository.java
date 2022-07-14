package com.ps.services.dao.repository.tenant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<T, ID> extends JpaRepository<T, ID> {

}

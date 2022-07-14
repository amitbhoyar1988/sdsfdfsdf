package com.ps.services.dao.repository.master;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@SuppressWarnings("hiding")
@NoRepositoryBean
public interface AbstractRepository<T,ID> extends JpaRepository<T, ID>{

}

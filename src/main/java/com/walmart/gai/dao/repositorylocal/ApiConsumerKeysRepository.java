package com.walmart.gai.dao.repositorylocal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walmart.gai.dao.ApiConsumerKeys;

@Repository
public interface ApiConsumerKeysRepository extends JpaRepository <ApiConsumerKeys, String> {

	ApiConsumerKeys findByProcessId(String processId);
}
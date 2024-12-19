package com.example.fieldworks.jpetstore_boot.dao;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SequenceDao extends ListCrudRepository<Sequence, String>, CustomSequenceDao {

}

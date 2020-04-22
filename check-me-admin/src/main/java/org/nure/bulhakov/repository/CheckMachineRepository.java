package org.nure.bulhakov.repository;

import org.nure.bulhakov.model.CheckMachine;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CheckMachineRepository extends MongoRepository<CheckMachine, String> {
}

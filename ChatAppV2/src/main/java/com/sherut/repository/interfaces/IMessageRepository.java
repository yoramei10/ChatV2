package com.sherut.repository.interfaces;

import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMessageRepository extends MongoRepository<IAppMessageDTO,String>{

}

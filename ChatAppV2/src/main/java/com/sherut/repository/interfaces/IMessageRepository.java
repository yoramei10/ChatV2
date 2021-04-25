package com.sherut.repository.interfaces;

import com.sherut.models.DTO.interfaces.IAppMessageDTO;
import com.sherut.models.DTO.interfaces.IChatUserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.annotation.PostConstruct;


public interface IMessageRepository extends MongoRepository<IAppMessageDTO,String>{

}

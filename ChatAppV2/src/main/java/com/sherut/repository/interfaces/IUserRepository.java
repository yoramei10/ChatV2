package com.sherut.repository.interfaces;

import com.sherut.models.DTO.interfaces.IChatUserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserRepository extends MongoRepository<IChatUserDTO,String> {

    public IChatUserDTO getById(String id);
    public IChatUserDTO getByUserName(String userName);
    public IChatUserDTO getByNickName(String nickName);
    public void deleteById(String delete);


}

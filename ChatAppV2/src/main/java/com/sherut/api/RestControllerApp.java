package com.sherut.api;

import com.sherut.mappers.interfaces.IMapAppMessage;
import com.sherut.mappers.interfaces.IMapChatUser;
import com.sherut.models.ResourceDM.AppMessage;
import com.sherut.models.ResourceDM.ChatUser;
import com.sherut.models.ResourceDM.TopicParams;
import com.sherut.repository.interfaces.IMessageRepository;
import com.sherut.repository.interfaces.IUserRepository;
import com.sherut.services.applicationServices.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;


@RestController
public class RestControllerApp {

    @Autowired
    private ILoginApplicationService login;
    @Autowired
    private IGetAllNickNamesApplicationService getAllNickNamesApplicationService;
    @Autowired
    private IGetAllUsersApplicationService getAllUsersApplicationService;
    @Autowired
    private ILogOutApplicationService removeUserApplicationService;
    @Autowired
    private IPublishMessageApplicationService publishMessageApplicationService;
    @Autowired
    private IMapChatUser mapChatUser;
    @Autowired
    private IMapAppMessage mapAppMessage;
    @Autowired
    private IGetTopicApplicationService getTopicApplicationService;
    @Autowired
    private IGetAllMessagesApplicationService getAllMessagesApplicationService;
    @Autowired
    private IMessageRepository messageRepository;
    @Autowired
    private IUserRepository userRepository;

    @Value("${configuration.mongoDB.clearDBInSetup}")
    boolean clearDBInSetup;


    @Value("${url.baseUrl}")
    String BASEAPI1;
    final String BASEAPI = "/chatApp";


    @RequestMapping(
            method = RequestMethod.POST,
            value = BASEAPI + "/login",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ChatUser> login(@RequestBody ChatUser user) {

        ChatUser createUser = login.loginApp(user);

        return new ResponseEntity<ChatUser>(createUser, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = BASEAPI + "/{id}/logout",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> logOut(@PathVariable("id") String id) {

        ChatUser userToRemove = removeUserApplicationService.logoutUser(id);

        return new ResponseEntity<String>(String.format("user %s was removed", userToRemove.getName()), HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = BASEAPI + "/{id}/getAllNickNames",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getAllNickNames(@PathVariable("id") String id) {

        List<String> allNickNames = getAllNickNamesApplicationService.getAllNickNames(id);

        return new ResponseEntity<List<String>>(allNickNames, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = BASEAPI + "/{id}/getAllUsers",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ChatUser>>getAllUsers(@PathVariable("id") String id) {

        List<ChatUser> allUsers = getAllUsersApplicationService.getAllUsers(id);

        return new ResponseEntity<List<ChatUser>>(allUsers, HttpStatus.OK);
    }

    @RequestMapping(value = BASEAPI + "/{id}/publishNewMessage",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.POST)
    public ResponseEntity<String> publishNewMessage(@PathVariable("id") String id,
                                            @RequestBody Object messageContext) {

        publishMessageApplicationService.publish(id, messageContext);

        return new ResponseEntity<String>( "message was published successfully", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = BASEAPI + "/getTopic",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicParams> getTopic() {

        TopicParams topicParams = getTopicApplicationService.getTopicParams();
        return new ResponseEntity<TopicParams>(topicParams, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = BASEAPI + "/{id}/getAllMessages",
            produces = "application/json")
    public ResponseEntity<List<AppMessage>> getAllMessages(@PathVariable("id") String id) {

        List<AppMessage> aLlMessages = getAllMessagesApplicationService.getALlMessages(id);

        return new ResponseEntity<List<AppMessage>>(aLlMessages, HttpStatus.OK);
    }

    @PostConstruct
    void postConstructCleanRepo(){
        System.out.println("clean all messages");
        messageRepository.deleteAll();

        if (clearDBInSetup){
            userRepository.deleteAll();
        }
    }

}

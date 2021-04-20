package com.sherut.api;

import com.sherut.mappers.interfaces.IMapAppMessage;
import com.sherut.mappers.interfaces.IMapAppMessageToMessagingAppMessage;
import com.sherut.mappers.interfaces.IMapChatUserToChatUserDM;
import com.sherut.models.ResourceModels.AppMessage;
import com.sherut.models.ResourceModels.ChatUser;
import com.sherut.models.DModels.interfaces.IChatUserDM;
import com.sherut.services.applicationServices.interfaces.*;
import com.sherut.models.ResourceModels.MessageAppMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class RestControllerApp {

    @Autowired
    private ILoginApplicationService login;
    @Autowired
    private IGetAllNickNamesApplicationService getAllNickNames;
    @Autowired
    private IGetAllUsersApplicationService getAllUsers;
    @Autowired
    private IRemoveUserApplicationService removeUserApplicationService;
    @Autowired
    private IPublishMessageApplicationService publishMessageApplicationService;
    @Autowired
    private IMapChatUserToChatUserDM mapChatUserToChatUserDM;
    @Autowired
    private IMapAppMessage mapAppMessage;
    @Autowired
    private IMapAppMessageToMessagingAppMessage mapAppMessageToMessagingAppMessage;

//    @Value("${spring.kafka.producer.key-serializer}")
//    private String key_serializer;

    List<ChatUser> allUsers = new ArrayList<>();
    static final String BASEAPI = "/chatApp";

    @RequestMapping(value = BASEAPI + "/login",
            produces = "application/json",
            method = RequestMethod.POST)
    public ResponseEntity<ChatUser> login(@RequestParam(value = "userName") @PathVariable("userName") String userName,
                                          @RequestParam(value = "password") @PathVariable("password") String password,
                                          @RequestParam(value = "nickName", required = false) @PathVariable("name") String nickName) {

        ChatUser user = login.loginApp(allUsers, userName, password, nickName);

            return new ResponseEntity<ChatUser>(user, HttpStatus.OK);
    }

    @RequestMapping(value = BASEAPI +"/removeUser",
            produces = "application/json",
            method = RequestMethod.DELETE)
    public ResponseEntity<String> removeUser(@RequestParam(value = "id") @PathVariable("id") String id) {

        ChatUser userToRemove = removeUserApplicationService.removeUser(allUsers, id);

        if (null != userToRemove) {
            return new ResponseEntity<String>(String.format("user %s was removed", userToRemove.getName()), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(String.format("user %s was not found", id), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = BASEAPI + "/getAllNickNames",
            produces = "application/json",
            method = RequestMethod.GET)
    public ResponseEntity<List<String>> getAllNickNames(@RequestParam(value = "id") @PathVariable("id") String id) {

        List<String> allNickNames = getAllNickNames.getAllNickNames(id, allUsers);

        return new ResponseEntity<List<String>>(allNickNames, HttpStatus.OK);
    }

    @RequestMapping(value = BASEAPI + "/getAllUsers",
            produces = "application/json",
            method = RequestMethod.GET)
    public ResponseEntity<List<ChatUser>>getAllUsers(@RequestParam(value = "id") @PathVariable("id") String id) {

        List<ChatUser> allUsersDMToResponse = getAllUsers.getAllUsers(id, allUsers);

        if (null == allUsersDMToResponse) {
            return new ResponseEntity<List<ChatUser>>((List<ChatUser>) null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<List<ChatUser>>(allUsers, HttpStatus.OK);
    }

    @RequestMapping(value = BASEAPI + "/publishNewMessage",
            produces = "application/json",
            consumes = "application/json",
            method = RequestMethod.POST)
    public ResponseEntity<String> publishNewMessage(@RequestParam(value = "id") @PathVariable("id") String id,
                                            @RequestBody AppMessage appMessage) {

        publishMessageApplicationService.publish(allUsers, id, appMessage);

        return new ResponseEntity<String>( "message was published successfully", HttpStatus.BAD_REQUEST);
    }

}

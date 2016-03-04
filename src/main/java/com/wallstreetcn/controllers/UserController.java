package com.wallstreetcn.controllers;

import com.wallstreetcn.models.User;
import com.wallstreetcn.models.UserDao;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * A class to test interactions with the MySQL database using the UserDao class.
 *
 * @author netgloo,srjiang
 */
@RestController
public class UserController {

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    /**
     * /create  --> Create a new user and save it in the database.
     *
     * @param email User's email
     * @param name User's name
     * @return A string describing if the user is succesfully created or not.
     */
    @ResponseBody
    @ApiOperation(value = "create a user", nickname = "create a user")
    @RequestMapping(method = RequestMethod.POST, path="/v1/users", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "email", value = "User's email", required = true, dataType = "string", paramType = "query", defaultValue=""),
            @ApiImplicitParam(name = "name", value = "User's name", required = true, dataType = "string", paramType = "query", defaultValue="")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = User.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public User create(String email, String name) {
        try {
            User user = new User(email, name);
            userDao.save(user);
            return user;
        }
        catch (Exception ex) {
            return null;
        }
    }

    /**
     * /delete  --> Delete the user having the passed id.
     *
     * @param id The id of the user to delete
     * @return A string describing if the user is succesfully deleted or not.
     */
    @ResponseBody
    @ApiOperation(value = "delete a user", nickname = "delete a user")
    @RequestMapping(method = RequestMethod.DELETE, path="/v1/users", produces = "application/json")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "User's ID", required = true, dataType = "integer", paramType = "query", defaultValue="")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = User.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public User delete(long id) {
        try {
            User user = new User(id);
            userDao.delete(user);
            return user;
        }
        catch (Exception ex) {
            return null;
        }
    }

    /**
     * /get-by-email  --> Return the id for the user having the passed email.
     *
     * @param email The email to search in the database.
     * @return The user id or a message error if the user is not found.
     */
    @ResponseBody
    @ApiOperation(value = "Get a user by email", nickname = "Get a user by email")
    @RequestMapping(method = RequestMethod.GET, path="/v1/users", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User's ID", required = false, dataType = "integer", paramType = "query", defaultValue=""),
            @ApiImplicitParam(name = "email", value = "User's email", required = false, dataType = "string", paramType = "query", defaultValue=""),
            @ApiImplicitParam(name = "mobile", value = "User's mobile", required = false, dataType = "string", paramType = "query", defaultValue="")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = User.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public User getUser(Integer id, String email, String mobile) {
        try {
            if (id != null && id > 0) {
                return userDao.findByEmail(email);
            }

            if (email != null && !email.isEmpty()) {
                return userDao.findByEmail(email);
            }

            if (mobile != null && !mobile.isEmpty()) {
                return userDao.findByEmail(email);
            }
        }
        catch (Exception ex) {
            return null;
        }
        return null;
    }

    /**
     * /update  --> Update the email and the name for the user in the database
     * having the passed id.
     *
     * @param id The id for the user to update.
     * @param email The new email.
     * @param name The new name.
     * @return A string describing if the user is succesfully updated or not.
     */
    @ResponseBody
    @ApiOperation(value = "Update a user", nickname = "Update a user")
    @RequestMapping(method = RequestMethod.PUT, path="/v1/users", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "User's ID", required = true, dataType = "integer", paramType = "query", defaultValue="")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = User.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public User updateUser(long id, String email, String name) {
        try {
            User user = userDao.findOne(id);
            user.setEmail(email);
            user.setUsername(name);
            userDao.save(user);
            return user;
        }
        catch (Exception ex) {
            return null;
        }
    }

    // ------------------------
    // PRIVATE FIELDS
    // ------------------------

    @Autowired
    private UserDao userDao;

} // class UserController

package com.wallstreetcn.controllers;

import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
public class BasicErrorController {

    // ------------------------
    // PUBLIC METHODS
    // ------------------------

    @ResponseBody
    @ApiOperation(value = "show error messages", nickname = "show error")
    @RequestMapping(method = RequestMethod.GET, path="/error", produces = "application/json")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "email", value = "User's email", required = true, dataType = "string", paramType = "query", defaultValue=""),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")})
    public String get() {
        return "{\"success\":false}";
    }

} // class ErrorController

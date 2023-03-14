package com.jwt.employee.controller;

import com.jwt.employee.core.AavasConstants;
import com.jwt.employee.entity.Employee;
import com.jwt.employee.model.ServiceMessage;
import com.jwt.employee.model.ServiceResult;
import com.jwt.employee.model.TokenDetails;
import com.jwt.employee.repository.EmployeeRepository;

import com.jwt.employee.security.JwtAuthenticationProvider;
import com.jwt.employee.security.TokenExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequestMapping(value = "/employee/", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
public class EmployeeController {

    @Autowired
    private TokenExtractor tokenExtractor;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EmployeeRepository employeeRepository;



    @PostMapping("/createemployee")
    public Employee saveEmployee(@RequestBody Employee employee){
        Employee employee1 = employeeRepository.save(employee);
        return employee;
    }

    @GetMapping("/getempliyeelist")
    public Object getEmployee(@RequestHeader(AavasConstants.AUTHORIZATION) String authToken){
        try {

            authToken = tokenExtractor.extract(authToken);
            TokenDetails tokendetails = jwtAuthenticationProvider.getUserDetailsFromToken(authToken);
            if (tokendetails.getId() != null && tokendetails.getId() > 0) {
                List<Employee> employee = employeeRepository.findAll();
                return employee;
            } else {
                return new ServiceResult(ServiceResult.StatusCode.UNAUTHORIZED,
                        new ServiceMessage(messageSource, "msg.authentication.failed"), "please try again !");
            }
        }catch (Exception ex) {
            return new ServiceResult(ServiceResult.StatusCode.INTERNAL_SERVER_ERROR,
                    new ServiceMessage(messageSource, "msg.internal.server.error"), null);
        }
    }
}

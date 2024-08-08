package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Doctor;
import com.techelevator.model.Patient;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("hasRole('Doctor')")
public class DoctorController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(path = "/doctor/register", method = RequestMethod.POST)
    public Doctor createDoctorForUser(@RequestBody Doctor doctor, Principal principal){
        Doctor newDoctor = null;
        int userId = userDao.getUserByUsername(principal.getName()).getId();

        try{
            if (userDao.getUserById(userId) != null) {
                throw new DaoException("Doctor already exists for this user");
            }
            doctor.setDoctorId(userId);
            newDoctor = userDao.createUser(doctor);

            //check if doctorId exist in doctor table, if so throw error

            //add doctor with user_id

        }catch(DaoException error){
            System.out.println("Error creating user" + error.getMessage());
            throw new RuntimeException("Failed to create Doctor", error);
        }

        return newDoctor;
    }

    @RequestMapping(path= "/doctor/appointments", method = RequestMethod.GET)
    public Doctor getAllAppointments(@RequestBody Doctor doctor, Principal principal) {

    }






}

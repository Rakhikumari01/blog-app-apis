package com.codewithrakhi.traffic.services.Seviceimpl;

import com.codewithrakhi.traffic.entity.User;
import com.codewithrakhi.traffic.entity.Violation;
import com.codewithrakhi.traffic.exception.ResourceNotFoundException;
import com.codewithrakhi.traffic.payload.ViolationDto;
import com.codewithrakhi.traffic.repositories.UserRepo;
import com.codewithrakhi.traffic.repositories.ViolationRepo;
import com.codewithrakhi.traffic.services.ViolationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ViolationServiceImpl implements ViolationService {

    @Autowired
    private ViolationRepo violationRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Override
    public ViolationDto addViolation(ViolationDto violationDto, Integer userId)
    {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id", userId));

        Violation violation = this.modelMapper.map(violationDto, Violation.class);
        violation.setViolationImage("default.jpg");
        violation.setAddedDate(new Date());
        violation.setUser(user);
        violation.setViolationTitle(violationDto.getViolationTitle());

        Violation newViolation = this.violationRepo.save(violation);
        return this.modelMapper.map(newViolation,ViolationDto.class);
    }

    @Override
    public ViolationDto updateViolation(ViolationDto violationDto, Integer violationId) {

       Violation violation = this.violationRepo.findById(violationId).orElseThrow(()-> new ResourceNotFoundException("Violation","violation id", violationId));

       violation.setViolationTitle(violationDto.getViolationTitle());
       violation.setViolationImage(violationDto.getViolationImage());

     Violation newviolation = this.violationRepo.save(violation);
     return this.modelMapper.map(newviolation,ViolationDto.class);

    }

    @Override
    public void deleteViolation(Integer violationId) {

        Violation violation = this.violationRepo.findById(violationId).orElseThrow(()-> new ResourceNotFoundException("Violation","violation id", violationId));

        this.violationRepo.delete(violation);

    }


    @Override
    public ViolationDto getViolationById(Integer violationId) {

        Violation violation = this.violationRepo.findById(violationId).orElseThrow(()->new ResourceNotFoundException("Violation", "violation id", violationId));

        return this.modelMapper.map(violation,ViolationDto.class);

    }

    @Override
    public List<ViolationDto> getViolationByUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," user id", userId));
        List<Violation> violations = this.violationRepo.findByUser(user);

        List<ViolationDto> violationDtos = violations.stream().map((violation)-> this.modelMapper.map(violation,ViolationDto.class)).collect(Collectors.toList());

        return violationDtos;
    }
}

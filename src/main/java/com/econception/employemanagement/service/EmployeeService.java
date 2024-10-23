package com.econception.employemanagement.service;

import com.econception.employemanagement.domain.Employee;
import com.econception.employemanagement.domain.EmployeeBillsClaim;
import com.econception.employemanagement.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.matcher.ElementMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {


    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee) {
        log.debug("Request to save employee : {}", employee);
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll() {
        log.debug("Request to get all employee");
        return employeeRepository.findAll();
    }

    public void delete(Long id) {
        log.debug("Request to delete employee : {}", id);
        employeeRepository.deleteById(id);
    }

    public Employee findByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    public Employee findById(long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        return null;
    }


//    public  boolean saveNewEmployee(NewEmployeeDTO newEmployeeDTO){
//
//        try {
//            Employee employee = new Employee();
//            employee.setFirstName(newEmployeeDTO.getFirstName());
//            employee.setMiddleName(newEmployeeDTO.getMiddleName());
//            employee.setLastName(newEmployeeDTO.getLastName());
//            employee.setGender(newEmployeeDTO.getGender());
//            employee.setDOB(newEmployeeDTO.getDOB());
//            employee.setContactNo(newEmployeeDTO.getContactNo());
//            employee.setCnic(newEmployeeDTO.getCnic());
//           // employee.setEmail(newEmployeeDTO.getEmail());
//            employee.setAddress(newEmployeeDTO.getAddress());
//            employee.setLine2(newEmployeeDTO.getLine2());
//            employee.setCity(newEmployeeDTO.getCity());
//            Department department = new Department();
//            department.setId(newEmployeeDTO.getDepartmentId());
//            employee.setDepartment(department);
//            String pictureFileName = StringUtils.cleanPath(Objects.requireNonNull(newEmployeeDTO.getMultipartFile().getOriginalFilename()));
//            employee.setPicture(pictureFileName);
//            String resumeFileName = StringUtils.cleanPath(newEmployeeDTO.getMultipartFile().getOriginalFilename());
//            employee.setResume(resumeFileName);
//            Employee employeeSaved = save(employee);
//            String uploadDir = "photos/" + employeeSaved.getId();
//            FileUploadUtill.saveFile(uploadDir, pictureFileName, newEmployeeDTO);
//            FileUploadUtill.saveFile(uploadDir, resumeFileName, newEmployeeDTO);
//            return true;
//        }catch (Exception e){
//            log.error("Add Employee Error", e);
//            return false;
//        }
//
//    }
}

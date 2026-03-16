package com.coffeecafe.coffee.service;

import com.coffeecafe.coffee.dto.StaffRegisterRequest;
import com.coffeecafe.coffee.entity.Cafe;
import com.coffeecafe.coffee.entity.StaffProfile;
import com.coffeecafe.coffee.entity.User;

import com.coffeecafe.coffee.repository.CafeRepository;
import com.coffeecafe.coffee.repository.StaffProfileRepository;
import com.coffeecafe.coffee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Service
public class StaffService {

    @Autowired
    private StaffProfileRepository staffProfileRepository;

    @Autowired
    private CafeRepository cafeRepository;
 
    @Autowired
    private UserRepository userRepository;
 
    @Autowired
    private PasswordEncoder passwordEncoder;


    public void completeStaffRegistration(StaffRegisterRequest request) {
        StaffProfile profile = new StaffProfile();

        // 1. Personal Details
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setEmail(request.getEmail());
        profile.setPassword(request.getPassword());
        profile.setDob(request.getDob());
        profile.setGender(request.getGender());
        profile.setPhone(request.getPhone());

        // 2. Address & Location
        profile.setPlotNo(request.getPlotNo());
        profile.setArea(request.getArea());
        profile.setCity(request.getCity());
        profile.setPincode(request.getPincode());

        // 3. Academic Info
        profile.setInstitution(request.getInstitution());
        profile.setDegree(request.getDegree());
        profile.setPassingYear(request.getYear());

        // 4. Work Experience
        profile.setJobTitle(request.getJobTitle());
        profile.setCompanyName(request.getCompanyName());
        profile.setEmploymentType(request.getEmploymentType());
        profile.setTotalYearsOfExperience(request.getTotalYears());
        profile.setStartDate(request.getStartDate());
        profile.setEndDate(request.getEndDate());
        profile.setCurrentlyWorking(request.isCurrentlyWorking());
        profile.setResponsibilities(request.getResponsibilities());
        profile.setAchievements(request.getAchievements());

        // 5. Linking the Cafe
        Cafe cafe = cafeRepository.findById(request.getCafeId())
                .orElseThrow(() -> new RuntimeException("Cafe not found with ID: " + request.getCafeId()));
        profile.setCafe(cafe);

        // 5a. Create or Update a User (Account) for the Staff
        User user = userRepository.findByEmail(request.getEmail())
                .orElse(new User());
        
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // ENCODED PASSWORD
        user.setPhoneNumber(request.getPhone());
        user.setRole(request.getRole()); // CHEF or WAITER
        user.setStatus("ACTIVE"); // Default to ACTIVE so they can log in
        user.setCafe(cafe);
        
        // Save the User first
        User savedUser = userRepository.save(user);
        profile.setUser(savedUser);
 

        // 6. File Upload Logic
        if (request.getGovtProof() != null && !request.getGovtProof().isEmpty()) {
            try {
                String fileName = UUID.randomUUID() + "_" + request.getGovtProof().getOriginalFilename();
                Path path = Paths.get("uploads/proofs/" + fileName);
                Files.createDirectories(path.getParent());
                Files.copy(request.getGovtProof().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                profile.setGovtProofPath(fileName);
            } catch (Exception e) {
                throw new RuntimeException("File upload failed", e);
            }
        }

        // 7. SAVE StaffProfile

        staffProfileRepository.save(profile);
    }

    public List<StaffProfile> getStaffByCafeId(Long cafeId) {
        // This calls the repository method you just created
        return staffProfileRepository.findByCafeId(cafeId);
    }


    // Fix for image_be98b5.png
    public void deleteStaffById(Long id) {
        if (staffProfileRepository.existsById(id)) {
            staffProfileRepository.deleteById(id);
        } else {
            throw new RuntimeException("Staff not found with id: " + id);
        }
    }

}
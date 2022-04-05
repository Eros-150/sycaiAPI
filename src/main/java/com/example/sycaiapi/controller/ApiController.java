package com.example.sycaiapi.controller;

import com.example.sycaiapi.config.*;
import com.example.sycaiapi.dto.*;
import com.example.sycaiapi.model.*;
import com.example.sycaiapi.persistence.entity.*;
import com.example.sycaiapi.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class ApiController {

    private StudyService studyService;

    public ApiController(StudyService studyService) {
        this.studyService = studyService;
    }

    @GetMapping("/stats")
    public StatisticsDTO stats() {
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        List<Study> studies = studyService.getAllStudies();
        List<StatsDTO> statsDTOList = new ArrayList<>();
        List<StudyDTO> studiesDTO = new ArrayList<>();
        Map<String, Integer[]> lesions = new HashMap<>();

        for (LesionType lesionType : studyService.getAllLesionTypes()) {
            lesions.put(lesionType.getType(), new Integer[]{0, 0, 0/*,0,0*/});/*For each label we put a 0 in the array of integers*/
        }

        for (Study study : studies) {
            List<Integer> labels = new ArrayList<>();
            labels.add(study.getLabeler1Id());
            labels.add(study.getLabeler2Id());
            labels.add(study.getLabeler3Id());

            /* TO FUTURE 5 LABELERS, ALSO CHANGE IN StatsDTO THE ARRAY 3 to 5
            labels.add(study.getLabeler4Id());
            labels.add(study.getLabeler5Id());
            */

            StudyDTO sDTO = studyService.studyToDTO(study);
            studiesDTO.add(sDTO);
            StatsDTO statsDTO = new StatsDTO();
            statsDTO.setSycaiId(study.getSycaiId());
            statsDTO.setLesionName(sDTO.getFinalDiagnosis());

            for (int i = 0; i < statsDTO.getLabeler().length; i++) {
                if (labels.get(i) != null) {
                    statsDTO.getLabeler()[i] = true;
                } else {
                    statsDTO.getLabeler()[i] = false;
                }
            }

            statsDTOList.add(statsDTO);
            labels.removeIf(Objects::isNull);
            Integer[] counts = lesions.get(sDTO.getFinalDiagnosis());

            if (labels.size() != 0) {
                counts[labels.size() - 1] = counts[labels.size() - 1] + 1;
            }
            lesions.put(sDTO.getFinalDiagnosis(), counts);
        }

        List<LabelsDTO> labelCounts = new ArrayList<>();

        for (Map.Entry<String, Integer[]> stringEntry : lesions.entrySet()) {
            Integer[] counts = stringEntry.getValue();
            labelCounts.add(new LabelsDTO(stringEntry.getKey(), counts[0], counts[1], counts[2]));
        }
        statisticsDTO.setStatsDTOList(statsDTOList);
        statisticsDTO.setLabelers(labelCounts);
        statisticsDTO.setLesions(lesions);

        return statisticsDTO;
    }



    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }







}

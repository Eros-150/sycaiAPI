package com.example.sycaiapi.service;

import com.example.sycaiapi.dto.*;
import com.example.sycaiapi.persistence.entity.*;
import com.example.sycaiapi.persistence.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudyService {

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private LabelerRepository labelerRepository;

    @Autowired
    private LesionTypeRepository lesionTypeRepository;

    @Autowired
    private ContrastTypeRepository contrastTypeRepository;

    @Autowired
    private StudyDescriptionRepository studyDescriptionRepository;

    // *** GETTERS ***

    public Integer totalStudies() {
        return studyRepository.getTotalStudies();
    }

    public List<LesionDTO> getLesionCount() {
        List<LesionType> types = lesionTypeRepository.findAll();
        List<LesionDTO> lesions = new ArrayList<>();
        for (int i = 0; i < types.size(); i++) {
            LesionDTO lesion = new LesionDTO(types.get(i).getType(),
                    studyRepository.getLesionCounter(types.get(i).getId()));
            lesions.add(lesion);
        }
        return lesions;
    }

    public List<Study> getAllStudies() {
        return studyRepository.findAll();
    }

    public List<StudyDescription> getAllStudyDescriptions() {
        return studyDescriptionRepository.findAll();
    }

    public List<Labeler> getAllLabelers() {
        return labelerRepository.findAll();
    }

    public List<LesionType> getAllLesionTypes() {
        return lesionTypeRepository.findAll();
    }


    public List<ContrastType> getAllContrastType() {
        return contrastTypeRepository.findAll();
    }

    // *** FINDERS ***

    public Study findStudyById(String id) {
        return studyRepository.findById(id).orElse(null);
    }

    public Study findStudyByPatientId(String patId) {
        return studyRepository.findStudyByPatientId(patId).orElse(null);
    }

    public String findLabelerName(Integer id) {
        return labelerRepository.findLabelerName(id).orElse(null);
    }

    public Integer findLabelerNameId(String name) {
        return labelerRepository.findLabelerNameId(name).orElse(null);
    }

    public String findLesionType(Integer id) {
        return lesionTypeRepository.findLesionType(id).orElse(null);
    }

    public Integer findLesionTypeId(String type) {
        return lesionTypeRepository.findLesionTypeId(type).orElse(null);
    }

    public String findContrastType(Integer id) {
        return contrastTypeRepository.findContrastType(id).orElse(null);
    }

    public Integer findContrastTypeId(String type) {
        return contrastTypeRepository.findContrastTypeId(type).orElse(null);
    }

    public String findStudyDescription(Integer id) {
        return studyDescriptionRepository.findStudyDescription(id).orElse(null);
    }

    public Integer findStudyDescriptionId(String description) {
        return studyDescriptionRepository.findStudyDescriptionId(description).orElse(null);
    }

    public void deleteStudy(String sycaiId) {
        studyRepository.deleteById(sycaiId);
    }

    /**
     * Transforms StudyDTO received into persistence Study and adds it to the database
     *
     * @param studyDTO study body
     * @return Saved study
     */
    public Study saveStudy(StudyDTO studyDTO) {
        Study study = new Study(studyDTO);
        if (studyDTO.getStudyDescription() != null && findStudyDescriptionId(studyDTO.getStudyDescription()) == null) {        //If null, create it
            studyDescriptionRepository.save(new StudyDescription(studyDTO.getStudyDescription()));
        }
        study.setStudyDescriptionId(findStudyDescriptionId(studyDTO.getStudyDescription()));

        if (studyDTO.getContrastType() != null && findContrastTypeId(studyDTO.getContrastType()) == null) {
            contrastTypeRepository.save(new ContrastType(studyDTO.getContrastType()));
        }
        study.setContrastTypeId(findContrastTypeId(studyDTO.getContrastType()));

        study.setRadiologist1Id(labelerCheck(studyDTO.getRadiologist1()));
        study.setRadiologist2Id(labelerCheck(studyDTO.getRadiologist2()));
        study.setRadiologist3Id(labelerCheck(studyDTO.getRadiologist3()));
        study.setLabeler1Id(labelerCheck(studyDTO.getLabeler1()));
        study.setLabeler2Id(labelerCheck(studyDTO.getLabeler2()));
        study.setLabeler3Id(labelerCheck(studyDTO.getLabeler3()));
        study.setDiagnosis1Id(lesionCheck(studyDTO.getDiagnosis1()));
        study.setDiagnosis2Id(lesionCheck(studyDTO.getDiagnosis2()));
        study.setDiagnosis3Id(lesionCheck(studyDTO.getDiagnosis3()));
        study.setFinalDiagnosisId(lesionCheck(studyDTO.getFinalDiagnosis()));
        study.setPaafResultId(lesionCheck(studyDTO.getPaafResult()));
        return studyRepository.save(study);
    }

    /**
     * Checks if labeler/radiologist exists and adds it otherwise.
     *
     * @param labeler labeler/rad name
     * @return Id of the added/found labeler
     */
    private Integer labelerCheck(String labeler) {

        if (labeler != null && findLabelerNameId(labeler) == null) {
            labelerRepository.save(new Labeler(labeler));
        }
        return findLabelerNameId(labeler);
    }

    /**
     * Checks if lesion type exists and adds it otherwise.
     *
     * @param lesion lesion name
     * @return Id of the added/found lesion type
     */
    private Integer lesionCheck(String lesion) {
        if (lesion != null && findLesionTypeId(lesion) == null) {
            lesionTypeRepository.save(new LesionType(lesion));
        }
        return findLesionTypeId(lesion);
    }

    public StudyDTO studyToDTO(Study study) {
        StudyDTO studyDTO = new StudyDTO(study);
        studyDTO.setStudyDescription(findStudyDescription(study.getStudyDescriptionId()));
        studyDTO.setContrastType(findContrastType(study.getContrastTypeId()));
        studyDTO.setRadiologist1(findLabelerName(study.getRadiologist1Id()));
        studyDTO.setRadiologist2(findLabelerName(study.getRadiologist2Id()));
        studyDTO.setRadiologist3(findLabelerName(study.getRadiologist3Id()));
        studyDTO.setLabeler1(findLabelerName(study.getLabeler1Id()));
        studyDTO.setLabeler2(findLabelerName(study.getLabeler2Id()));
        studyDTO.setLabeler3(findLabelerName(study.getLabeler3Id()));
        studyDTO.setDiagnosis1(findLesionType(study.getDiagnosis1Id()));
        studyDTO.setDiagnosis2(findLesionType(study.getDiagnosis2Id()));
        studyDTO.setDiagnosis3(findLesionType(study.getDiagnosis3Id()));
        studyDTO.setFinalDiagnosis(findLesionType(study.getFinalDiagnosisId()));
        studyDTO.setPaafResult(findLesionType(study.getPaafResultId()));
        studyDTO.setIsTrain("No");
        return studyDTO;
    }
}

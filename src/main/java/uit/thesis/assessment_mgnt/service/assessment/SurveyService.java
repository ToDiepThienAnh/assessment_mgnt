package uit.thesis.assessment_mgnt.service.assessment;

import javassist.NotFoundException;
import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.assessment.survey.*;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.utils.survey.Status;
import uit.thesis.assessment_mgnt.utils.survey.StatusForm;

import java.math.BigDecimal;
import java.util.List;

public interface SurveyService extends GenericService<Survey, Long> {
    public Survey addNewSurvey(CreateSurveyDto dto) throws NotFoundException;

    public List<Status> getAllStatus();

    public Survey updateEsimatePrice(String surveyCode, BigDecimal esimatePrice) throws NotFoundException;

    public Survey findByCode(String code);

    public Survey requestCancelation(String surveyCode) throws NotFoundException;

    List<Survey> getSurveyWithCurrentUsername();

    List<Survey> getInDoingSurveysWithUsername(String username) throws NotFoundException;

    List<Survey> getDoneSurveysWithUsername(String username) throws NotFoundException;

    public List<ResponseSurvey> getAllSurveyCode();

    public List<Survey> mockupData();

    public List<SurveyWithUsers> getAll();

    public Survey changeUserOfSurvey(String fromUser ,String toUser, String surveyCode) throws Exception;

    public Survey assignInspectors(String[] usernames, String surveyCode) throws NotFoundException;

    public Survey assigneeApproval(String[] arrUsername, String surveyCode) throws NotFoundException;

    public Survey updateSurvey(UpdateSurveyDto dto, String code) throws Exception;

    public List<ReportSurveyStatus> reportByStatus();
}

package uit.thesis.assessment_mgnt.controller.workflow;

import io.swagger.models.Response;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.workflow.CreatePhaseDto;
import uit.thesis.assessment_mgnt.dto.workflow.UpdatePhaseDto;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.model.workflow.Phase;
import uit.thesis.assessment_mgnt.service.workflow.PhaseService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.workflow.PhaseDomain;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(PhaseDomain.PHASE)
public class PhaseController {
    private PhaseService phaseService;

    @GetMapping("")
    public ResponseEntity<Object> findAll(){
        List<Phase> list = phaseService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> findByNodeOrder(@RequestParam(name = "nodeOrder") int nodeOrder){
        Phase phase = phaseService.findByNodeOrder(nodeOrder);
        if(phase == null)
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(phase, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addPhase(@Valid @RequestBody CreatePhaseDto dto,
                                           BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        try {
            Phase phase = phaseService.addPhase(dto);
            return ResponseObject.getResponse(phase, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/generate")
    public ResponseEntity<Object> addPhase(@RequestParam(name = "name") String workflowName
                                           ){
        try {
            Phase phase = phaseService.generateStartPhase(workflowName);
            return ResponseObject.getResponse(phase, HttpStatus.CREATED);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateRole")
    public ResponseEntity<Object> updatePhaseRole(@RequestParam("role") String roleName,
                                                  @RequestParam("phase") String phaseName){
        try {
            Phase phase = phaseService.updatePhaseRole(phaseName, roleName);
            return ResponseObject.getResponse(phase, HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/decline-assign")
    public ResponseEntity<Object> declineAssignment(@RequestParam("survey") String surveyCode){
        try {
            Survey survey = phaseService.declineAssignInspector(surveyCode);
            return ResponseObject.getResponse(survey, HttpStatus.OK);
        } catch (NotFoundException e) {
            e.printStackTrace();
            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

//    @PutMapping("/submit-phase")
//    public ResponseEntity<Object> submitPhase(@RequestBody UpdatePhaseDto dto,
//                                              BindingResult errors){
//        if(errors.hasErrors())
//            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
//        try {
//            Survey res = phaseService.submitPhase(dto);
//            return ResponseObject.getResponse(res, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//
//    }
//
//    @PutMapping("/return-phase")
//    public ResponseEntity<Object> returnPhase(@RequestBody UpdatePhaseDto dto,
//                                              BindingResult errors){
//        if(errors.hasErrors())
//            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
//        try {
//            Survey res = phaseService.returnPhase(dto);
//            return ResponseObject.getResponse(res, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//
//    }

}

package uit.thesis.assessment_mgnt.controller.workflow;

import io.swagger.models.Response;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.workflow.CreateCommentDto;
import uit.thesis.assessment_mgnt.model.workflow.Comment;
import uit.thesis.assessment_mgnt.service.workflow.CommentService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.Domain;
import uit.thesis.assessment_mgnt.utils.domain.workflow.CommentDomain;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(Domain.API)
public class CommentController {
    private CommentService commentService;

    @GetMapping(CommentDomain.COMMENT_DOMAIN)
    public ResponseEntity<Object> findAll(){
        List<Comment> comments = commentService.findAll();
        if(comments.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(comments, HttpStatus.OK);
    }

//    @GetMapping(CommentDomain.COMMENT_DOMAIN + "/search/survey")
//    public ResponseEntity<Object> findBySurvey(@RequestParam("survey") String surveyCode){
//        List<Comment> comments = null;
//        try {
//            comments = commentService.findBySurvey(surveyCode);
//        } catch (NotFoundException e) {
//            e.printStackTrace();
//            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//        if(comments.isEmpty())
//            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
//        return ResponseObject.getResponse(comments, HttpStatus.OK);
//    }
//
//    @PostMapping(CommentDomain.COMMENT_DOMAIN)
//    public ResponseEntity<Object> addComment(@RequestBody CreateCommentDto dto,
//                                             BindingResult errors){
//        if(errors.hasErrors())
//            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
//        try {
//            Comment comment = commentService.addComment(dto);
//            return ResponseObject.getResponse(comment, HttpStatus.CREATED);
//        } catch (NotFoundException e) {
//            e.printStackTrace();
//            return ResponseObject.getResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//
//    }
}

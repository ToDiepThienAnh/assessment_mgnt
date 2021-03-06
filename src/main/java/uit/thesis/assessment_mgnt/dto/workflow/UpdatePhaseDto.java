package uit.thesis.assessment_mgnt.dto.workflow;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdatePhaseDto {
    @NotBlank
    private String content;

    @NotBlank
    private String surveyCode;

    @NotBlank
    private String phaseName;


}

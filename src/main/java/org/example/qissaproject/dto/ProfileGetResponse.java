package org.example.qissaproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.qissaproject.model.ChildProfile;
import org.example.qissaproject.model.Story;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileGetResponse {

    private String email;
    private String firstname;
    private String lastname;
    private List<ChildProfile> children;
    private List<Story> stories;
}

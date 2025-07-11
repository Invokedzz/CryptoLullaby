package org.cryptolullaby.controller;

import jakarta.validation.Valid;
import org.cryptolullaby.model.dto.general.ImageDTO;
import org.cryptolullaby.model.dto.general.PagedResponseDTO;
import org.cryptolullaby.model.dto.general.SystemResponseDTO;
import org.cryptolullaby.model.dto.users.EditProfileDTO;
import org.cryptolullaby.model.dto.users.ProfileDTO;
import org.cryptolullaby.orchestration.ProfileOrchestrationFacade;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/domain/profile")
public class ProfileController {

    private final ProfileOrchestrationFacade orchestrationFacade;

    public ProfileController (ProfileOrchestrationFacade orchestrationFacade) {

        this.orchestrationFacade = orchestrationFacade;

    }

    @GetMapping("/{id}")
    public ResponseEntity <PagedResponseDTO<ProfileDTO>> findProfileById (

            @PathVariable String id,

            @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable

    )

    {

        var user = orchestrationFacade.getProfile(id, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @GetMapping("/{username}/find")
    public ResponseEntity <PagedResponseDTO<ProfileDTO>> findProfileByUsername (

            @PathVariable String username,

            @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable

    )

    {

        var user = orchestrationFacade.getProfileByUsername(username, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    @PutMapping("/edit/{id}")
    public ResponseEntity <SystemResponseDTO> editProfileById (

            @PathVariable String id,

            @Valid @ModelAttribute EditProfileDTO profileDTO

    )

    {

        orchestrationFacade.editUserById(id, profileDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new SystemResponseDTO("Profile edited successfully!"));

    }

    @PutMapping("/edit/pfp/{id}")
    public ResponseEntity <Void> editProfileImageById (

            @PathVariable String id,

            @Valid @ModelAttribute ImageDTO imageDTO

    )

    {

        orchestrationFacade.editUserImage(id, imageDTO.file());

        return ResponseEntity.status(HttpStatus.OK).build();

    }

    @PutMapping("/visibility/{id}")
    public ResponseEntity <Void> changeProfileVisibilityById (@PathVariable String id) {

        orchestrationFacade.changeProfileVisibilityById(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}

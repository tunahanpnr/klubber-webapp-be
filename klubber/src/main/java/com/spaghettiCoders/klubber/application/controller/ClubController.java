package com.spaghettiCoders.klubber.application.controller;

import com.spaghettiCoders.klubber.application.entity.Users;
import com.spaghettiCoders.klubber.application.entity.Club;
import com.spaghettiCoders.klubber.application.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClubController {
    private final ClubService clubService;

    @PostMapping("/createclub")
    @PreAuthorize("permitAll()")
    public String createClub(@Valid @RequestBody final Club club, final Users user) {
        return clubService.createClub(club, user);
    }

    @DeleteMapping("/deleteclub/{id}")
    @PreAuthorize("permitAll()")
    public String deleteClub(final Users user, @PathVariable Long id) {
        return clubService.deleteClub(user, id);
    }

    @GetMapping("/listclub")
    @PreAuthorize("permitAll()")
    public List<Club> listClub() {
        return clubService.listClub();
    }

    /*@GetMapping("/searchclub")
    @PreAuthorize("permitAll()")
    public String searchClub(String clubName) {
        return clubService.searchClub(clubName);
    }*/

    /*@PostMapping("/searchclub")
    @PreAuthorize("permitAll()")
    public String searchClub(@Valid @RequestBody final Club club) {
        return clubService.searchClub(club);
    }*/
}

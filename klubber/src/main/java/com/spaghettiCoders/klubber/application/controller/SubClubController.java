
package com.spaghettiCoders.klubber.application.controller;

import com.spaghettiCoders.klubber.application.dto.SubClubDTO;
import com.spaghettiCoders.klubber.application.dto.request.SubClubCreateReqDTO;
import com.spaghettiCoders.klubber.application.entity.SubClub;
import com.spaghettiCoders.klubber.application.entity.Users;
import com.spaghettiCoders.klubber.application.service.SubClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class SubClubController {
    private final SubClubService subClubService;

    @PostMapping("/createsubclub")
    @PreAuthorize("permitAll()")
    public String createSubClub(@Valid @RequestBody final SubClubCreateReqDTO subclub) {
        return subClubService.createSubClub(subclub);
    }

    @DeleteMapping("/deletesubclub/{name}/{username}")
    @PreAuthorize("permitAll()")
    public String deleteSubClub(@PathVariable String username, @PathVariable String name) {
        return subClubService.deleteSubClub(username, name);
    }

//    @DeleteMapping("/deletesubclub/{id}")
//    @PreAuthorize("permitAll()")
//    public String deleteSubClub(final Users user, @PathVariable Long id) {
//        return subClubService.deleteSubClub(user, id);
//    }

//    @GetMapping("/listsubclub")
//    @PreAuthorize("permitAll()")
//    public List<SubClub> listSubClub(@Valid @RequestBody final String clubName){
//        return subClubService.listSubClub(clubName);
//    }

    @GetMapping("/listsubclub/{clubName}")
    @PreAuthorize("permitAll()")
    public List<SubClubDTO> listSubClub(@PathVariable String clubName){
        return subClubService.listSubClub(clubName);
    }
//    public List<ClubDTO> listClub() {
//        return clubService.listClub();
//    }


    @PostMapping("/joinsubclub")
    @PreAuthorize("permitAll()")
    public String joinSubClub(@Valid @RequestBody final SubClub subclub, final Users user){
        return subClubService.joinSubClub(subclub, user);
    }

    @PostMapping("/leaveSubClub")
    @PreAuthorize("permitAll()")
    public String leaveSubClub(@Valid @RequestBody final SubClub subclub, final Users user){
        return subClubService.leaveSubClub(subclub, user);
    }

    @PostMapping("/updatesubclubname")
    @PreAuthorize("permitAll()")
    public String updateSubClubName(@Valid @RequestBody final SubClub subclub, final Users user, final String newName) {
        return subClubService.updateSubClubName(subclub, user, newName);
    }

    @PostMapping("/updatesubclubid/{id}")
    @PreAuthorize("permitAll()")
    public String updateClubId(@Valid @RequestBody final SubClub subclub, final Users user, @PathVariable Long id) {
        return subClubService.updateSubClubId(subclub, user, id);
    }
}


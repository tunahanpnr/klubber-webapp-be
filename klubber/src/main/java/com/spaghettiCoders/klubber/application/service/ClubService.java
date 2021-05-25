package com.spaghettiCoders.klubber.application.service;

import com.spaghettiCoders.klubber.application.dto.ClubDTO;
import com.spaghettiCoders.klubber.application.dto.UserDTO;
import com.spaghettiCoders.klubber.application.dto.request.JoinClubReqDTO;
import com.spaghettiCoders.klubber.application.entity.*;
import com.spaghettiCoders.klubber.application.mapper.ClubMapper;
import com.spaghettiCoders.klubber.application.mapper.UsersMapper;
import com.spaghettiCoders.klubber.application.repository.AnswerRepository;
import com.spaghettiCoders.klubber.application.repository.ClubRepository;
import com.spaghettiCoders.klubber.application.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final UsersRepository usersRepository;
    private final AnswerRepository answerRepository;
    private final ClubMapper clubMapper;
    private final UsersMapper usersMapper;

    public String createClub(Club club, String username){
        Users user =  usersRepository.findByUsername(username);

        if(!user.getRole().toString().equals("ADMIN")){
            return "Only users with the role of ADMIN can open a club!";
        }

        if(clubRepository.existsClubByName(club.getName())) {
            return "this club is already exist!";
        }

        if(containsIllegals(club.getName())){
            return "Club Name can not contain illegal character such as \"@ ? ! | ~ ^ € % &\"";
        }

        clubRepository.save(club);

        return "club added to the system successfully";
    }

    public String deleteClub(String username, String name){
        Users users = usersRepository.findByUsername(username);

        if(!users.getRole().toString().equals("ADMIN")){
            return "Only users with the role of ADMIN can delete a club!";
        }

        if(!clubRepository.existsClubByName(name)){
            return "club not found!";
        }

        Club club = clubRepository.getClubByName(name);

        clubRepository.delete(club);

        return "club deleted from the system successfully";
    }

    public String updateClubName(Club club, Users user, String newName){
        if(!user.getRole().toString().equals("ADMIN")){

            return "Only users with the role of ADMIN can open a club!";
        }
        if(clubRepository.existsClubByName(newName)) {

            return "this club is already exist!";
        }

        if(containsIllegals(club.getName())){

            return "Club Name can not contain illegal character such as \"@ ? ! | ~ ^ € % &\"";
        }
        club.setName(newName);

        return "Club updated sucessfully.";
    }

    public String updateClubId(Club club, Users user, Long id){
        if(!user.getRole().toString().equals("ADMIN")){

            return "Only users with the role of ADMIN can open a club!";
        }
        if(!clubRepository.existsById(club.getId())){

            return "club can not found!";
        }
        if(clubRepository.existsById(id)){

            return "this id used for another club!";
        }if(!idChecker(String.valueOf(id))){
            return "clud id can contain only numbers.";
        }
        club.setId(id);


        return "Club updated sucessfully.";
    }

    public List<ClubDTO> listClub(){
        List<Club> clubs = clubRepository.getClubs();
        return clubMapper.mapToDto(clubs);
    }

    public String joinClub(JoinClubReqDTO joinclubReqDTO){
        if(!clubRepository.existsClubByName(joinclubReqDTO.getClubname()))
            return "Wrong class name!";

        if(!usersRepository.existsByUsername(joinclubReqDTO.getUsername()))
            return "Wrong username!";

        Users user = usersRepository.findByUsername(joinclubReqDTO.getUsername());
        int score = 0;
        for (Answer answer:joinclubReqDTO.getAnswers()) {
            user.getAnswers().add(answerRepository.findById(answer.getId()).get());
            score+=answer.getScore();
        }
        usersRepository.save(user);

        if(score > 12){
            Club club = clubRepository.getClubByName(joinclubReqDTO.getClubname());
            club.getUsers().add(user);
            clubRepository.save(club);
            return "Joined club successfully.";
        }


        return "You fail the questionnaire!";
    }

    public String leaveClub(Club club, Users user){
        List<Users> temporaryClubUsers = club.getUsers();
        List<Club> temporaryUserClubs = user.getClubs();
        boolean isUserExistAnySubClub = false;

        if(!temporaryClubUsers.contains(user)){
            return "user not found.";
        }

        for(SubClub sb: club.getSubClubs()){
            if(sb.getUsers().contains(user)){
                isUserExistAnySubClub = true;
            }
        }
        if(isUserExistAnySubClub) {
            return "user cannot leave club because user inside one of the Club's SubClub.";
        }

        temporaryClubUsers.remove(user);
        club.setUsers(temporaryClubUsers);

        temporaryUserClubs.remove(club);
        user.setClubs(temporaryUserClubs);

        return "user leaved club successfully.";
    }

    public boolean containsIllegals(String toExamine) {
        Pattern pattern = Pattern.compile("[~#@*+%{}<>\\[\\]|\"\\_^]");
        Matcher matcher = pattern.matcher(toExamine);
        return matcher.find();
    }

    public boolean idChecker(String toExamine) {
        Pattern pattern = Pattern.compile("[0123456789]");
        Matcher matcher = pattern.matcher(toExamine);
        return matcher.find();
    }

    public List<UserDTO> getUsers(String clubname) {
        if(clubRepository.existsClubByName(clubname))
            return usersMapper.mapToDto(clubRepository.getClubByName(clubname).getUsers());

        return null;
    }

    /*public String searchClub(Club club){  TAM İSİM BEKLENMEKSİZİN BENZER İSİMLER İÇEREN KULÜPLERİ GETİRECEK.
        Club dbClub = clubRepository.findClubByName(club.getName());

        if(dbClub == null)
            return "There is not a club such as " + club.getName();

        return dbClub.getName() + " " + dbClub.getSubClubs();
    }*/
}


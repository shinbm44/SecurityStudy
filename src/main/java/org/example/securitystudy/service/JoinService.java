package org.example.securitystudy.service;


import lombok.RequiredArgsConstructor;
import org.example.securitystudy.dto.JoinDTO;
import org.example.securitystudy.entity.UserEntity;
import org.example.securitystudy.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void JoinProcess(JoinDTO joinDTO) {

        //db에 동일한 username이 존재하는지 확인 필요
        boolean isExists = userRepository.existsByUsername(joinDTO.getUsername());
        if(isExists) {
            return;
        }


        UserEntity data = new UserEntity();
        data.setUsername(joinDTO.getUsername());
        data.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
        data.setRole("ROLE_ADMIN");


        userRepository.save(data);
    }

}

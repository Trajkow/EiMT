package mk.ukim.finki.backend.service.application.Impl;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.dto.inputDTO.LoginRequestDTO;
import mk.ukim.finki.backend.model.dto.inputDTO.RegisterRequestDTO;
import mk.ukim.finki.backend.model.dto.outputDTO.AuthResponseDTO;
import mk.ukim.finki.backend.model.enitites.AppUser;
import mk.ukim.finki.backend.repository.UserRepository;
import mk.ukim.finki.backend.security.JwtUtil;
import mk.ukim.finki.backend.service.application.AuthApplicationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthApplicationServiceImpl implements AuthApplicationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO request) {
        if (userRepository.findByUsername(request.username()).isPresent()) {
            throw new IllegalArgumentException("Username already taken");
        }
        AppUser user = new AppUser(
                null,
                request.username(),
                passwordEncoder.encode(request.password()),
                request.resolvedRole()
        );
        userRepository.save(user);
        String token = jwtUtil.generateToken(user);
        return new AuthResponseDTO(token, user.getUsername(), user.getRole());
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        AppUser user = (AppUser) userDetailsService.loadUserByUsername(request.username());
        String token = jwtUtil.generateToken(user);
        return new AuthResponseDTO(token, user.getUsername(), user.getRole());
    }
}

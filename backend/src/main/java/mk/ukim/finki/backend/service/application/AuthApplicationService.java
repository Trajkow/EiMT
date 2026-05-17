package mk.ukim.finki.backend.service.application;

import mk.ukim.finki.backend.model.dto.inputDTO.LoginRequestDTO;
import mk.ukim.finki.backend.model.dto.inputDTO.RegisterRequestDTO;
import mk.ukim.finki.backend.model.dto.outputDTO.AuthResponseDTO;

public interface AuthApplicationService {
    AuthResponseDTO register(RegisterRequestDTO request);
    AuthResponseDTO login(LoginRequestDTO request);
}

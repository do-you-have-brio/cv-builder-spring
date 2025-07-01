package dyhb.cv.auth.dto;

public record LoginResponse(String accessToken, Long expiresIn) {
}

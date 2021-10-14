package ga.healtCare.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    // 1000 : 요청 성공
    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    SUCCESS_READ_USERS(true, 1010, "회원 전체 정보 조회에 성공하였습니다."),
    SUCCESS_READ_USER(true, 1011, "회원 정보 조회에 성공하였습니다."),
    SUCCESS_POST_USER(true, 1012, "회원가입에 성공하였습니다."),
    SUCCESS_LOGIN(true, 1013, "로그인에 성공하였습니다."),
    SUCCESS_JWT(true, 1014, "JWT 검증에 성공하였습니다."),
    SUCCESS_DELETE_USER(true, 1015, "회원 탈퇴에 성공하였습니다."),
    SUCCESS_PATCH_USER(true, 1016, "회원정보 수정에 성공하였습니다."),
    SUCCESS_READ_SEARCH_USERS(true, 1017, "회원 검색 조회에 성공하였습니다."),
    SUCCESS_POST_GROUP_USER(true, 1018, "유저 등록에 성공하였습니다."),
    SUCCESS_READ_GROUP_USER(true, 1019, "그룹별 회원 정보 조회에 성공하였습니다."),
    SUCCESS_POST_MESSAGE(true, 1020, "게시글 등록에 성공하였습니다."),
    SUCCESS_PATCH_MESSAGE(true, 1021, "게시글 수정에 성공하였습니다."),
    SUCCESS_DELETE_MESSAGE(true, 1022, "게시글 삭제에 성공하였습니다."),
    SUCCESS_READ_MESSAGES(true, 1023, "게시글 목록 조회에 성공하였습니다."),
    SUCCESS_READ_MESSAGE(true, 1024, "게시글 조회에 성공하였습니다."),
    SUCCESS_POST_COMMENT(true, 1025, "댓글 등록에 성공하였습니다."),
    SUCCESS_DELETE_COMMENT(true, 1026, "댓글 삭제에 성공하였습니다."),
    SUCCESS_READ_COMMENT(true, 1027, "댓글 조회에 성공하였습니다."),

    // 2000 : Request 오류
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_USERID(false, 2001, "유저 아이디 값을 확인해주세요."),
    EMPTY_JWT(false, 2010, "JWT를 입력해주세요."),
    INVALID_JWT(false, 2011, "유효하지 않은 JWT입니다."),
    EMPTY_ID(false, 2020, "아이디를 입력해주세요."),
    INVALID_EMAIL(false, 2021, "이메일 형식을 확인해주세요."),
    EMPTY_PASSWORD(false, 2030, "비밀번호를 입력해주세요."),
    EMPTY_CONFIRM_PASSWORD(false, 2031, "비밀번호 확인을 입력해주세요."),
    WRONG_PASSWORD(false, 2032, "비밀번호를 다시 입력해주세요."),
    DO_NOT_MATCH_PASSWORD(false, 2033, "비밀번호와 비밀번호확인 값이 일치하지 않습니다."),
    EMPTY_NICKNAME(false, 2040, "닉네임을 입력해주세요."),
    EMPTY_MESSAGE(false, 2041, "내용을 입력해주세요."),
    // 3000 : Response 오류
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),
    NOT_FOUND_USER(false, 3010, "존재하지 않는 회원입니다."),
    DUPLICATED_USER(false, 3011, "이미 존재하는 회원입니다."),
    FAILED_TO_GET_USER(false, 3012, "회원 정보 조회에 실패하였습니다."),
    FAILED_TO_POST_USER(false, 3013, "회원가입에 실패하였습니다."),
    FAILED_TO_LOGIN(false, 3014, "로그인에 실패하였습니다."),
    FAILED_TO_DELETE_USER(false, 3015, "회원 탈퇴에 실패하였습니다."),
    FAILED_TO_PATCH_USER(false, 3016, "개인정보 수정에 실패하였습니다."),
    DUPLICATED_NICKNAME(false, 3017, "이미 존재하는 닉네임 입니다."),
    NOT_FOUND_MESSAGE_BOARD(false, 3018, "존재하지 않는 게시글입니다."),
    NOT_MATCH_MESSAGE_BOARD(false, 3019, "게시글 작성자와 다른 회원입니다."),
    NOT_FOUND_COMMENT(false, 3020, "존재하지 않는 댓글입니다."),
    NOT_MATCH_COMMENT(false, 3021, "댓글 작성자와 다른 회원입니다."),
    NOT_MATCH_MESSAGE_BOARD_COMMENT(false, 3022, "해당 게시글에 존재하지 않는 댓글입니다."),

    // 4000 : Database 오류
    SERVER_ERROR(false, 4000, "서버와의 통신에 실패하였습니다."),
    DATABASE_ERROR(false, 4001, "데이터베이스 연결에 실패하였습니다.");

    // 5000 : 필요시 만들어서 쓰세요
    // 6000 : 필요시 만들어서 쓰세요

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}

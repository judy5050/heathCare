package com.example.healthcare;

import com.example.healthcare.data.GroupData;
import com.example.healthcare.data.GroupUserListData;
import com.example.healthcare.data.JwtData;
import com.example.healthcare.data.PostGroupData;
import com.example.healthcare.data.LoginData;
import com.example.healthcare.data.PostLoginData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface APIInterface {

    // 회원가입 O
    @POST("group")
    Call<GroupData> groupAPI(@Body PostGroupData data);

    // 로그인 O
    @POST("login")
    Call<LoginData>  loginAPI(@Body PostLoginData data);

    // 자동 로그인
    @GET("/jwt")
    Call<JwtData> jwtAPI(@Header("X-ACCESS-TOKEN") String X_ACCESS_TOKEN);

    // 그룹별 유저 등록

    // 그룹별 유저 조회
    @GET("group/userList")
    Call<GroupUserListData> groupuserlistAPI(@Header("X-ACCESS-TOKEN") String X_ACCESS_TOKEN);

    // 게시글 등록

    // 게시글 수정

    // 게시글 삭제

    // 게시글 목록조회

    // 화장실정보 조회

    // 게시글 조회

    // 내가 쓴 게시글 조회

    // 댓글 등록

    // 댓글 삭제

    // 댓글 조회

    //회원 정보수정

    // 회원탈퇴

    // 병원정보조회

    // 청소 정보 등록

    // 청소 정보 조회

    // 화장실 이용시간 조회

    // 화장실 이용시간 등록

    // 유저별 화장실 사용시간 체크

    // 유저별 화장실 사용시간 조회회
}

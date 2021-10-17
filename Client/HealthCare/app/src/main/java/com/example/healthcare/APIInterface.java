package com.example.healthcare;

import com.example.healthcare.data.CleaningInfoGData;
import com.example.healthcare.data.CleaningInfoPData;
import com.example.healthcare.data.GroupData;
import com.example.healthcare.data.GroupUserData;
import com.example.healthcare.data.GroupUserListData;
import com.example.healthcare.data.GroupsUsersData;
import com.example.healthcare.data.JwtData;
import com.example.healthcare.data.MessageBoardPData;
import com.example.healthcare.data.MessageBoardsCommentPData;
import com.example.healthcare.data.MessageBoardsCommentsGData;
import com.example.healthcare.data.MessageBoardsGetListData;
import com.example.healthcare.data.MessageBoardsGetMsgData;
import com.example.healthcare.data.PostCleaningInfoData;
import com.example.healthcare.data.PostGroupData;
import com.example.healthcare.data.LoginData;
import com.example.healthcare.data.PostGroupUserData;
import com.example.healthcare.data.PostLoginData;
import com.example.healthcare.data.PatchGroupsUsersData;
import com.example.healthcare.data.PostMessageBoardPData;
import com.example.healthcare.data.PostMessageBoardsCommentPData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {

    // 회원가입 O
    @POST("group")
    Call<GroupData> groupAPI(@Body PostGroupData data);

    // 로그인 O
    @POST("login")
    Call<LoginData>  loginAPI(@Body PostLoginData data);

    // 자동 로그인
    @GET("jwt")
    Call<JwtData> jwtAPI(@Header("X-ACCESS-TOKEN") String X_ACCESS_TOKEN);

    // 그룹별 유저 등록 O
    @POST("group/user")
    Call<GroupUserData> groupuserAPI(@Header("X-ACCESS-TOKEN") String X_ACCESS_TOKEN,
                                     @Body PostGroupUserData data);
    // 그룹별 유저 조회 O
    @GET("group/userList")
    Call<GroupUserListData> groupuserlistAPI(@Header("X-ACCESS-TOKEN") String X_ACCESS_TOKEN);

    // 그룹별 유저 수정
    @PATCH("groups/users/{userIdx}")
    Call<GroupsUsersData> groupsusersPAPI(@Path("userIdx") int userIdx,
                                         @Body PatchGroupsUsersData data);
    // 그룹별 유저 삭제
    @DELETE("groups/users/{userIdx}")
    Call<GroupsUsersData> groupsusersDAPI(@Path("userIdx") int userIdx);

    // 청소 정보 등록 O
    @POST("cleaningInfo")
    Call<CleaningInfoPData> cleaninginfoPAPI(@Header("X-ACCESS-TOKEN") String X_ACCESS_TOKEN,
                                             @Body PostCleaningInfoData data);
    // 청소 정보 조회 O
    @GET("cleaningInfo")
    Call<CleaningInfoGData> cleaninginfoGAPI(@Header("X-ACCESS-TOKEN") String X_ACCESS_TOKEN);

    // 게시글 목록조회 O
    @GET("messageBoards")
    Call<MessageBoardsGetListData> messageboardsGLAPI(@Header("X-ACCESS-TOKEN") String X_ACCESS_TOKEN);

    // 게시글 등록 O
    @POST("messageBoard/{userIdx}")
    Call<MessageBoardPData> messageboardPAPI(@Path("userIdx") int userIdx,
                                             @Header("X-ACCESS-TOKEN") String X_ACCESS_TOKEN,
                                             @Body PostMessageBoardPData data);
    // 게시글 조회 O
    @GET("messageBoards/{messageBoardIdx}")
    Call<MessageBoardsGetMsgData> messageboardsGMAPI(@Path("messageBoardIdx") int messageBoardIdx,
                                                     @Header("X-ACCESS-TOKEN") String X_ACCESS_TOKEN);
    // 게시글 수정

    // 게시글 삭제



    // 내가 쓴 게시글 조회

    // 댓글 등록 O
    @POST("messageBoards/{messageBoardIdx}/comment/{userIdx}")
    Call<MessageBoardsCommentPData> messageboardscommentPAPI(@Path("messageBoardIdx") int messageBoardIdx,
                                                             @Path("userIdx") int userIdx,
                                                             @Header("X-ACCESS-TOKEN") String X_ACCESS_TOKEN,
                                                             @Body PostMessageBoardsCommentPData data);
    // 댓글 조회 O
    @GET("messageBoards/{messageBoardIdx}/comments")
    Call<MessageBoardsCommentsGData> messageboardscommentsGAPI(@Path("messageBoardIdx") int messageBoardIdx,
                                                               @Header("X-ACCESS-TOKEN") String X_ACCESS_TOKEN);



    // 댓글 삭제



    // =========Develop later=========

    // 병원정보 조회

    // 화장실 이용시간 조회

    // 화장실 이용시간 등록

    // 유저별 화장실 사용시간 체크

    // 유저별 화장실 사용시간 조회
}

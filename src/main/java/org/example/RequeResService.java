package org.example;

import retrofit2.Call;
import retrofit2.http.*;

public interface RequeResService {

    @GET("api/users/{id}")
    Call<User> getUser(@Path("id") int userId);

    @GET("api/users")
    Call<Users> getUsers(@Query("page") int number);

    @POST("api/users")
    Call<CreateUserResponse> createUser(@Body CreateUser user);
}


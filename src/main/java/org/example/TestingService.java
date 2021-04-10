package org.example;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.Optional;

public class TestingService {
    // This was created only for testing purposes
    private Retrofit retrofit;
    private RequeResService service;

    public TestingService() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        this.service = retrofit.create(RequeResService.class);
    }

    public void getUser() {
        final Call<User> userCall = this.service.getUser(2);
        final Optional<Response<User>> userResponse = _apiCall(userCall);

        userResponse.ifPresent(user -> {
            System.out.println(user.body().getData().getFirstName()+ " " + user.body().getData().getLastName());
        });

    }

    public void getUsers() {
        final Call<Users> usersCall = service.getUsers(2);
        final Optional<Response<Users>> usersResponse = _apiCall(usersCall);

        usersResponse.ifPresent( listUsers -> {
            listUsers.body().getData().forEach(user -> {
                System.out.println(user.getFirstName());
            });
        });

    }

    public void createUser() {
        CreateUser user = new CreateUser();
        user.setName("Milan");
        user.setJob("Developer");

        final Call<CreateUserResponse> createCall = service.createUser(user);
        final Optional<Response<CreateUserResponse>> createCallResponse = _apiCall(createCall);
        createCallResponse.ifPresent(createdUser -> {
            System.out.println(createdUser.body().getId()+ " " +createdUser.body().getCreatedAt());
        });

    }

    private <T> Optional<Response<T>> _apiCall(Call<T> call) {
        try {
            final Response<T> response = call.execute();
            if(response.isSuccessful()){
                return Optional.of(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

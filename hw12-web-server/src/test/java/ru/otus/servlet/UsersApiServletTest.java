package ru.otus.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.otus.core.model.User;
import ru.otus.core.service.UserDBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UsersApiServletTest {

    private static final List userList = List.of(
            new User(1L, "User1", 20),
            new User(2L, "User2", 21),
            new User(3L, "User3", 22)
    );

    private static final long queryId = 1L;

    private static final long savedId = 5L;

    private static final User userById = new User(1L, "User1", 20);

    private static User SAVED_USER = new User(savedId, "CREATED_NAME", 23);

    private HttpServletRequest request;
    private HttpServletResponse response;
    private UserDBService userDBService;
    private Gson gson;

    private StringWriter stringWriter;
    private PrintWriter writer;


    private UsersApiServlet servlet;

    @BeforeEach
    public void setUp() throws IOException {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        userDBService = mock(UserDBService.class);
        gson = new GsonBuilder().serializeNulls().create();

        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(writer);

        servlet = new UsersApiServlet(userDBService, gson);
    }

    @Test
    public void shouldReturnAllUsers() throws IOException, ServletException, URISyntaxException {
        when(request.getPathInfo()).thenReturn("users");
        when(userDBService.getAll()).thenReturn(userList);

        Path path = new File(getClass().getClassLoader().getResource("expectedUserList.json").toURI()).toPath();
        final String expectedJson = Files.readString(path);

        servlet.doGet(request, response);

        verify(request, atLeast(1)).getPathInfo();
        verify(userDBService, atLeast(1)).getAll();
        writer.flush();
        assertEquals(expectedJson, stringWriter.toString());
    }

    @Test
    public void shouldReturnLookingForUser() throws URISyntaxException, IOException, ServletException {
        when(request.getPathInfo()).thenReturn("users/1");
        when(userDBService.getModel(queryId)).thenReturn(Optional.of(userById));

        Path path = new File(getClass().getClassLoader().getResource("expectedUserById.json").toURI()).toPath();
        final String expectedJson = Files.readString(path);

        servlet.doGet(request, response);

        verify(request, atLeast(1)).getPathInfo();
        verify(userDBService, atLeast(1)).getModel(queryId);
        writer.flush();
        assertEquals(expectedJson, stringWriter.toString());
    }

    @Test
    public void shouldReturnBadRequestForLookingForUser() throws URISyntaxException, IOException, ServletException {
        when(request.getPathInfo()).thenReturn("users/1");
        when(userDBService.getModel(queryId)).thenReturn(Optional.empty());

        servlet.doGet(request, response);

        verify(request, atLeast(1)).getPathInfo();
        verify(userDBService, atLeast(1)).getModel(queryId);
        verify(response, atLeast(1)).setStatus(HttpStatus.Code.NOT_FOUND.getCode());
    }

    @Test
    public void shouldReturnCreatedUser() throws IOException, ServletException {
        User.Dto userDto = createUserDto();
        StringReader stringReader = new StringReader(gson.toJson(userDto));
        BufferedReader reader = new BufferedReader(stringReader);

        when(request.getReader()).thenReturn(reader);
        when(userDBService.save(any())).thenReturn(savedId);
        when(userDBService.getModel(savedId)).thenReturn(Optional.of(SAVED_USER));
        final String expectedJson = gson.toJson(SAVED_USER);

        servlet.doPost(request, response);

        verify(userDBService, atLeast(1)).save(any());
        verify(userDBService, atLeast(1)).getModel(savedId);
        verify(response, atLeast(1)).setStatus(HttpStatus.Code.CREATED.getCode());
        writer.flush();
        assertEquals(expectedJson, stringWriter.toString());
    }
    @Test
    public void shouldReturnBadRequest() throws IOException, ServletException {
        StringReader stringReader = new StringReader("");
        BufferedReader reader = new BufferedReader(stringReader);

        when(request.getReader()).thenReturn(reader);

        servlet.doPost(request, response);
        verify(response, atLeast(1)).setStatus(HttpStatus.Code.BAD_REQUEST.getCode());
    }


    private User.Dto createUserDto() {
        User.Dto dto = new User.Dto();
        dto.setName(SAVED_USER.getName());
        dto.setLogin(SAVED_USER.getLogin());
        dto.setPassword(SAVED_USER.getPassword());
        dto.setAge(SAVED_USER.getAge());
        return dto;
    }

}
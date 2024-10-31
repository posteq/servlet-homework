package by.clevertec.servlet;

import by.clevertec.dto.UserDto;
import by.clevertec.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import static java.lang.Integer.parseInt;
import static javax.servlet.http.HttpServletResponse.SC_CREATED;
import static javax.servlet.http.HttpServletResponse.SC_NO_CONTENT;
import static javax.servlet.http.HttpServletResponse.SC_OK;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if(id != null) {
            int userId = parseInt(id);
            try(PrintWriter writer = resp.getWriter()) {
                Optional<UserDto> userDto = userService.getById(userId);
                if(userDto.isPresent()){
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.writeValue(writer,userDto.get());
                    resp.setStatus(SC_OK);
                }else
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"Users not found");
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid users ID");
            }
        }else{
            try(PrintWriter writer = resp.getWriter()) {
                ObjectMapper mapper = new ObjectMapper();
                List<UserDto> allUsers = userService.getAllUsers();
                mapper.writeValue(writer,allUsers);
                resp.setStatus(SC_OK);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String username = req.getParameter("username");
            String phoneNumber = req.getParameter("phoneNumber");
            String email = req.getParameter("email");
            UserDto newUser = new UserDto().builder()
                    .username(username).
                    email(email).
                    phoneNumber(phoneNumber).
                    build();

            userService.createUser(newUser);
            resp.setStatus(SC_CREATED);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid users data");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        if(id != null) {
            int userId = parseInt(id);
            try {
                userService.delete(userId);
                resp.setStatus(SC_NO_CONTENT);
            }catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Users not found");
            }
        } else{
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST,"Invalid users ID");
        }
    }

}

package com.app.APFinal.aplication.rest.resource;

import com.app.APFinal.aplication.rest.request.UserRequest;
import com.app.APFinal.aplication.rest.response.ListUsersResponse;
import com.app.APFinal.aplication.service.abstraction.IUserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserResource {

  @Autowired
  public IUserService userService;

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListUsersResponse> listActiveUsers() {
    return ResponseEntity.ok().body(userService.listActiveUsers());
  }

  @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> update(@PathVariable Long id,
      @Valid @RequestBody UserRequest updateUserRequest) {
    userService.update(id, updateUserRequest);
    return ResponseEntity.noContent().build();
  }

}

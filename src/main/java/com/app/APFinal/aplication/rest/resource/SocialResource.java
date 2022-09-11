package com.app.APFinal.aplication.rest.resource;

import com.app.APFinal.aplication.rest.request.SocialRequest;
import com.app.APFinal.aplication.rest.response.ListSocialResponse;
import com.app.APFinal.aplication.rest.response.SocialResponse;
import com.app.APFinal.aplication.service.abstraction.ISocialService;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("socials")
@CrossOrigin(origins = "http://localhost:4200/")
public class SocialResource {

  @Autowired
  private ISocialService socialService;

  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SocialResponse> create(
      @Valid @RequestBody SocialRequest socialRequest){
    SocialResponse socialResponse = socialService.save(socialRequest);
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(socialResponse.getId())
        .toUri();

    return ResponseEntity.created(location).body(socialResponse);
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    socialService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SocialResponse> getBy(@PathVariable Long id) {
    return ResponseEntity.ok().body(socialService.getBy(id));
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ListSocialResponse> listActiveSocials() {
    return ResponseEntity.ok().body(socialService.getListSocial());
  }

  @PutMapping("/{id}")
  public ResponseEntity<SocialResponse> update(@PathVariable long id,
      @Valid @RequestBody SocialRequest socialRequest) {
    return ResponseEntity.ok(socialService.update(id, socialRequest));
  }
}

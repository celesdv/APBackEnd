package com.app.APFinal.aplication.service.abstraction;

import com.app.APFinal.aplication.rest.request.SocialRequest;
import com.app.APFinal.aplication.rest.response.ListSocialResponse;
import com.app.APFinal.aplication.rest.response.SocialResponse;

public interface ISocialService {

  SocialResponse save(SocialRequest social);

  void delete(long id);

  SocialResponse getBy(long id);

  ListSocialResponse getListSocial();

  SocialResponse update(long id, SocialRequest social);

}

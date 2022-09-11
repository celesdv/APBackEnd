package com.app.APFinal.aplication.service;

import com.app.APFinal.aplication.exception.EntityNotFoundException;
import com.app.APFinal.aplication.rest.request.SocialRequest;
import com.app.APFinal.aplication.rest.response.ListSocialResponse;
import com.app.APFinal.aplication.rest.response.SocialResponse;
import com.app.APFinal.aplication.service.abstraction.ISocialService;
import com.app.APFinal.infrastructure.database.entity.Professional;
import com.app.APFinal.infrastructure.database.entity.Social;
import com.app.APFinal.infrastructure.database.mapper.ISocialMapper;
import com.app.APFinal.infrastructure.database.repository.IProfessionalRepository;
import com.app.APFinal.infrastructure.database.repository.ISocialRepository;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SocialService implements ISocialService {

  @Autowired
  public ISocialRepository socialRepository;

  @Autowired
  public ISocialMapper socialMapper;

  @Autowired
  public IProfessionalRepository professionalRepository;

  @Override
  public SocialResponse save(SocialRequest request) {
    Social social = socialMapper.toSocial(request);
    social.setSoftDeleted(false);
    social.setProfessional(findProfessional());
    return socialMapper.toSocialResponse(socialRepository.save(social));
  }

  @Override
  public void delete(long id) {
    Social social = findBy(id);
    social.setSoftDeleted(true);
    socialRepository.save(social);
  }

  @Override
  public SocialResponse getBy(long id) {
    Social social = findBy(id);
    return socialMapper.toSocialResponse(social);
  }

  @Override
  public ListSocialResponse getListSocial() {
    List<Social> socialList = socialRepository.findAllBySoftDeletedFalse();
    ListSocialResponse listSocialResponse = new ListSocialResponse();
    listSocialResponse.setSocials(socialMapper.toListSocialResponse(socialList));
    return listSocialResponse;
  }

  @Override
  public SocialResponse update(long id, SocialRequest request) {
    Social social = findBy(id);
    Social socialUpdated = updateValues(request, social);
    return socialMapper.toSocialResponse(socialRepository.save(socialUpdated));
  }

  private Social findBy(long id) {
    Social social = socialRepository.findByIdAndSoftDeletedFalse(id);
    if (social == null) {
      throw new EntityNotFoundException("Social not found.");
    }
    return social;
  }

  private Social updateValues(SocialRequest socialRequest, Social social) {
    String image = socialRequest.getImage();
    if (image != null) {
      social.setImage(image);
    }

    String link = socialRequest.getLink();
    if (link != null) {
      social.setLink(link);
    }

    return social;
  }

  private Professional findProfessional(){
    long id = 1;
    Optional<Professional> result = professionalRepository.findById(id);
    if (result.isEmpty()) {
      throw new EntityNotFoundException("Professional not found.");
    }
    return result.get();
  }

}
